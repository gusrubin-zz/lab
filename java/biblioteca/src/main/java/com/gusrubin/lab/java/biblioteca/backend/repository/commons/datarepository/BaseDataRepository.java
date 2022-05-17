package com.gusrubin.lab.java.biblioteca.backend.repository.commons.datarepository;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gusrubin.lab.java.biblioteca.backend.repository.commons.utils.FileUtils;
import com.gusrubin.lab.java.biblioteca.backend.repository.commons.utils.LocalDateAdapter;
import com.gusrubin.lab.java.biblioteca.backend.repository.commons.utils.LocalDateDeserializer;

import jline.internal.Nullable;

public abstract class BaseDataRepository<T extends EntityModel> implements DataRepository<T> {

	private static final String FILE_PATH = "data/";
	private static final String FILE_NAME_PREFIX = "biblioteca-";
	private final String dataFile;

	protected abstract Type getTypeToken();

	private final Logger log = Logger.getLogger(this.getClass().getName());

	protected BaseDataRepository(@Nullable Boolean testDatabase) {
		String pathComplement = Boolean.TRUE.equals(testDatabase) ? "-test" : "";
		this.dataFile = FILE_PATH + FILE_NAME_PREFIX + this.getClass().getSimpleName() + pathComplement + ".txt";

		FileUtils.checkIfFileExistsOrCreatesIt(dataFile);
	}

	private void writeDataModel(DataModel<T> dataModel) {
		Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).setPrettyPrinting()
				.create();
		Type typeList = getTypeToken();
		FileUtils.writeFileInLine(dataFile, gson.toJson(dataModel, typeList));
	}

	private DataModel<T> readDataModel() {
		String json = FileUtils.readFileAsString(dataFile);
		Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
				.setPrettyPrinting().create();
		Type typeList = getTypeToken();
		DataModel<T> dataModel = gson.fromJson(json, typeList);
		if (dataModel == null) {
			dataModel = new DataModel<>();
			dataModel.setEntityList(new ArrayList<>());
		}
		return dataModel;
	}

	public T save(T entity) {
		DataModel<T> dataModel = readDataModel();
		List<T> allEntityRecords = dataModel.getEntityList();

		List<T> allEntityRecordsWithSameId = dataModel.getEntityList().stream()
				.filter(r -> r.getId().equals(entity.getId())).collect(Collectors.toList());

		if (allEntityRecordsWithSameId.isEmpty()) {
			entity.setId(dataModel.nextId());
			allEntityRecords.add(entity);
		} else {
			checaInconsistenciaNaBaseDeDados(allEntityRecordsWithSameId);
			T persistedEntity = allEntityRecordsWithSameId.get(0);
			allEntityRecords.remove(persistedEntity);
			allEntityRecords.add(entity);
		}

		writeDataModel(dataModel);
		return entity;
	}

	public List<T> findAll() {
		DataModel<T> dataModel = readDataModel();
		List<T> entities = dataModel.getEntityList();
		return entities != null ? entities : new ArrayList<>();
	}

	public Optional<T> findById(Integer id) {
		DataModel<T> dataModel = readDataModel();

		T entity = null;

		List<T> allEntityRecordsWithSameId = dataModel.getEntityList().stream().filter(r -> r.getId().equals(id))
				.collect(Collectors.toList());

		if (!allEntityRecordsWithSameId.isEmpty()) {
			checaInconsistenciaNaBaseDeDados(allEntityRecordsWithSameId);

			entity = allEntityRecordsWithSameId.get(0);
		}

		return Optional.ofNullable(entity);
	}

	public void delete(Integer id) {
		DataModel<T> dataModel = readDataModel();
		List<T> allEntityRecords = dataModel.getEntityList();

		List<T> allEntityRecordsWithSameId = dataModel.getEntityList().stream().filter(r -> r.getId().equals(id))
				.collect(Collectors.toList());

		if (!allEntityRecordsWithSameId.isEmpty()) {
			checaInconsistenciaNaBaseDeDados(allEntityRecordsWithSameId);

			T entity = allEntityRecordsWithSameId.get(0);
			allEntityRecords.remove(entity);

			writeDataModel(dataModel);
		}
	}

	private void checaInconsistenciaNaBaseDeDados(List<T> entitiesWithSameId) {
		if (entitiesWithSameId.size() > 1) {
			log.severe("Inconsistencia na base de dados, há mais de um registro com o mesmo id.");
			throw new IllegalStateException("Inconsistencia na base de dados, há mais de um registro com o mesmo id.");
		}
	}

}
