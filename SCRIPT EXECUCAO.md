## alterar a tabela migration banco de dados ##

Caso ocorra erro no flyway ao iniciar o projeto, devera ser apagado o historico na tabela do banco de dados.

delete from flyway_schema_history where success = 0;