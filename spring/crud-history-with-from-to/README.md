# Exemplo de Geração de Histórico de Operações com DE-PARA

### Requisitos
Coloquei os seguintes requisitos para desenvolver:

* Criar uma classe utilitária com um método, de preferência estático, para retornar o diff de dois objetos do mesmo tipo, informado via generics;
* Criar uma interface para salvar o log no histórico, sendo um dos atributos o diff gerado pelo método anterior;
* Utilizar a interface de modo a registrar todas as chamadas ao método auditado, registrando chamadas executadas com sucesso quanto as tentativas que lançaram exceções;
* A implementação da persistência do registro deve que ser agnóstica de tecnologia e a execução deve ser assíncrona para não onerar a thread do método chamado.

Pendências:
* Capturar usuário da requisição;
* Verificar como transformar essa feature em pacote Maven/Gradle (SDK?)

### Configuração
Criar banco de dados postgres local para desenvolvimento:


docker run --name lab-postgres -p 5455:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=lab-postgres -d postgres


createdb -h localhost -p 5432 -U postgres crud-history

