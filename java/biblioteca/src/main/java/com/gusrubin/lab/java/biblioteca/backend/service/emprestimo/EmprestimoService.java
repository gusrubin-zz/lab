package com.gusrubin.lab.java.biblioteca.backend.service.emprestimo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.gusrubin.lab.java.biblioteca.backend.repository.emprestimo.Emprestimo;
import com.gusrubin.lab.java.biblioteca.backend.repository.emprestimo.EmprestimoRepository;
import com.gusrubin.lab.java.biblioteca.backend.repository.livro.Livro;
import com.gusrubin.lab.java.biblioteca.backend.repository.pessoa.Pessoa;
import com.gusrubin.lab.java.biblioteca.backend.service.livro.LivroService;
import com.gusrubin.lab.java.biblioteca.backend.service.pessoa.PessoaService;

public class EmprestimoService {

	private static final int MAXIMO_DE_LIVROS_EM_POSSE_POR_PESSOA = 5;

	private static final int MAXIMO_DE_DIAS_DE_EMPRESTIMO = 7;

	private static final int MULTA_POR_DIA_DE_ATRASO_NA_DEVOLUCAO = 5;

	private final EmprestimoRepository repository;
	private final LivroService livroService;
	private final PessoaService pessoaService;

	private final Logger log = Logger.getLogger(this.getClass().getName());

	public EmprestimoService(EmprestimoRepository emprestimoRepository, LivroService livroService,
			PessoaService pessoaService) {
		this.repository = emprestimoRepository;
		this.livroService = livroService;
		this.pessoaService = pessoaService;
	}

	public Emprestimo novoEmprestimo(Integer idPessoa, Integer idLivro) {
		Pessoa pessoa = pessoaService.buscaPorId(idPessoa);

		List<Emprestimo> emprestimosAtivos = validaRestricoesERetornaDeEmprestimosAtivos(idPessoa);

		if (emprestimosAtivos.size() >= MAXIMO_DE_LIVROS_EM_POSSE_POR_PESSOA) {
			throw new IllegalStateException("A pessoa já está com o numero máximo de livros em posse permitido.");
		}

		Livro livro = livroService.buscaPorId(idLivro);

		if (emprestimosAtivos.stream().filter(e -> e.getIdLivro().equals(idLivro)).collect(Collectors.toList())
				.size() >= livro.getExemplaresEmAcervo()) {
			throw new IllegalStateException("Todos os exemplares deste livro já estão emprestados.");
		}

		Emprestimo emprestimo = new Emprestimo(livro.getId(), pessoa.getId());
		emprestimo = repository.save(emprestimo);
		log.info("Novo empréstimo de livro " + emprestimo.showId());
		return emprestimo;
	}

	public Emprestimo renovaEmprestimo(Integer idEmprestimo) {
		Optional<Emprestimo> result = repository.findById(idEmprestimo);
		if (result.isEmpty()) {
			throw new IllegalStateException("Não existe emprestimo cadastradado com este id.");
		}
		Emprestimo emprestimo = result.get();

		validaRestricoesERetornaDeEmprestimosAtivos(emprestimo.getIdPessoa());

		if (emprestimo.getDataRenovacao() != null) {
			throw new IllegalStateException(
					"Este empréstimo já foi renovado, a pessoa não pode fazer uma nova renovação.");
		}

		emprestimo.setDataRenovacao(LocalDate.now());
		emprestimo = repository.save(emprestimo);
		log.info("Empréstimo renovado " + emprestimo.showId());
		return emprestimo;
	}

	public List<LivroEmprestado> livrosEmprestados() {
		List<LivroEmprestado> livrosEmprestados = new ArrayList<>();

		List<Emprestimo> emprestimosAtivos = repository.findAllEmprestimosAtivos();

		emprestimosAtivos.stream().forEach(e -> {
			LivroEmprestado livroEmprestado = new LivroEmprestado(e.getId(),
					livroService.buscaPorId(e.getIdLivro()).getTitulo(),
					pessoaService.buscaPorId(e.getIdPessoa()).getNome());
			livrosEmprestados.add(livroEmprestado);
		});

		return livrosEmprestados;
	}

	public List<LivroEmprestado> livrosEmprestadosPorPessoa(Integer idPessoa) {
		List<LivroEmprestado> livrosEmprestados = new ArrayList<>();

		List<Emprestimo> emprestimosAtivos = repository.findEmprestimosAtivosByIdPessoa(idPessoa);

		emprestimosAtivos.stream().forEach(e -> {
			LivroEmprestado livroEmprestado = new LivroEmprestado(e.getId(),
					livroService.buscaPorId(e.getIdLivro()).getTitulo(),
					pessoaService.buscaPorId(e.getIdPessoa()).getNome());
			livrosEmprestados.add(livroEmprestado);
		});

		return livrosEmprestados;
	}

	public List<LivroEmprestado> livrosEmprestadosPorLivro(Integer idLivro) {
		List<LivroEmprestado> livrosEmprestados = new ArrayList<>();

		List<Emprestimo> emprestimosAtivos = repository.findEmprestimosAtivosByIdLivro(idLivro);

		emprestimosAtivos.stream().forEach(e -> {
			LivroEmprestado livroEmprestado = new LivroEmprestado(e.getId(),
					livroService.buscaPorId(e.getIdLivro()).getTitulo(),
					pessoaService.buscaPorId(e.getIdPessoa()).getNome());
			livrosEmprestados.add(livroEmprestado);
		});

		return livrosEmprestados;
	}

	public Integer devolucao(Integer idEmprestimo) {
		Optional<Emprestimo> result = repository.findById(idEmprestimo);
		if (result.isEmpty()) {
			throw new IllegalStateException("Não existe emprestimo cadastradado com este id.");
		}
		Emprestimo emprestimo = result.get();

		if (emprestimo.getDataDevolucao() != null) {
			throw new IllegalStateException("Já foi registrada a devolução desse empréstimo.");
		}

		Integer diasAtraso = 0;
		if (emprestimo.getDataRenovacao() != null) {
			Integer diasEmprestimo = (int) ChronoUnit.DAYS.between(emprestimo.getDataRenovacao(), LocalDate.now());
			Integer diasDeMulta = diasEmprestimo - MAXIMO_DE_DIAS_DE_EMPRESTIMO;
			diasAtraso = diasDeMulta > 0 ? diasDeMulta : 0;
		} else {
			Integer diasEmprestimo = (int) ChronoUnit.DAYS.between(emprestimo.getDataEmprestimo(), LocalDate.now());
			Integer diasDeMulta = diasEmprestimo - MAXIMO_DE_DIAS_DE_EMPRESTIMO;
			diasAtraso = diasDeMulta > 0 ? diasDeMulta : 0;
		}

		emprestimo.setDataDevolucao(LocalDate.now());
		repository.save(emprestimo);

		return diasAtraso * MULTA_POR_DIA_DE_ATRASO_NA_DEVOLUCAO;
	}

	public List<ScorePessoa> rankingEmprestimos(Long tamanhoDaLista) {
		long numeroPessoas = tamanhoDaLista == null ? 10L : tamanhoDaLista;

		List<ScorePessoa> scorePessoaList = new ArrayList<>();
		pessoaService.listaTodas().stream().forEach(
				p -> scorePessoaList.add(new ScorePessoa(p, repository.findAllByIdPessoa(p.getId()).size(), null)));

		return scorePessoaList.stream().sorted(Comparator.comparingInt(ScorePessoa::getNumeroEmprestimos).reversed())
				.limit(numeroPessoas).collect(Collectors.toList());
	}

	public List<ScorePessoa> rankingAtrasos() {
		List<ScorePessoa> scorePessoaList = new ArrayList<>();
		pessoaService.listaTodas().stream()
				.forEach(p -> scorePessoaList.add(new ScorePessoa(p, null, getRecordDiasAtrasoByIdPessoa(p.getId()))));

		return scorePessoaList.stream().sorted(Comparator.comparingInt(ScorePessoa::getRecordDiasAtraso).reversed())
				.collect(Collectors.toList());
	}

	private Integer getRecordDiasAtrasoByIdPessoa(Integer idPessoa) {
		int diasAtrasoAcumulados = 0;

		for (int i = 0; i < repository.findAllByIdPessoa(idPessoa).size(); i++) {
			Emprestimo emprestimo = repository.findAllByIdPessoa(idPessoa).get(i);
			if (emprestimo.getDataRenovacao() != null) {
				Integer diasEmprestimo = (int) ChronoUnit.DAYS.between(emprestimo.getDataRenovacao(), LocalDate.now());
				Integer diasDeMulta = diasEmprestimo - MAXIMO_DE_DIAS_DE_EMPRESTIMO;
				diasAtrasoAcumulados += diasDeMulta > 0 ? diasDeMulta : 0;
			} else {
				Integer diasEmprestimo = (int) ChronoUnit.DAYS.between(emprestimo.getDataEmprestimo(), LocalDate.now());
				Integer diasDeMulta = diasEmprestimo - MAXIMO_DE_DIAS_DE_EMPRESTIMO;
				diasAtrasoAcumulados += diasDeMulta > 0 ? diasDeMulta : 0;
			}
		}
		return diasAtrasoAcumulados;
	}

	private List<Emprestimo> validaRestricoesERetornaDeEmprestimosAtivos(Integer idPessoa) {
		List<Emprestimo> emprestimosAtivosDaPessoa = repository.findEmprestimosAtivosByIdPessoa(idPessoa);

		if (emprestimosAtivosDaPessoa.stream().anyMatch(e -> (e.getDataRenovacao() == null
				&& ChronoUnit.DAYS.between(e.getDataEmprestimo(), LocalDate.now()) > MAXIMO_DE_DIAS_DE_EMPRESTIMO)
				|| (e.getDataRenovacao() != null && ChronoUnit.DAYS.between(e.getDataRenovacao(),
						LocalDate.now()) > MAXIMO_DE_DIAS_DE_EMPRESTIMO))) {
			throw new IllegalStateException(
					"A pessoa não pode renovar ou fazer um novo empréstimo porque está com devolução em atraso.");
		}

		return emprestimosAtivosDaPessoa;
	}

}
