# Exemplo de Geração de Histórico de Operações

### Requisitos
Coloquei os seguintes requisitos para desenvolver:

* Criar uma anotação de nível de método no Spring para ativar o registro de histórico das chamadas àquele método;
* Deve registrar todas as chamadas ao método, tanto as executadas com sucesso quanto as tentativas que lançaram exceções;
* Verificar se é necessário que essa anotação receba parâmetros customizáveis de tipo de registro, etc;
* A anotação tem que ser usada na camada de domínio pois a consulta dos registros será disponibilizada pelo domínio;
* A implementação da persistência do registro deve que ser agnóstica de tecnologia e a execução deve ser assíncrona para não onerar a thread do método chamado.

### Configuração
Criar banco de dados postgres local para desenvolvimento:


docker run --name lab-postgres -p 5455:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=lab-postgres -d postgres


createdb -h localhost -p 5432 -U postgres crud-history

