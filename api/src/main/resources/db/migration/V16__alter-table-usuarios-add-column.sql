ALTER TABLE usuarios DROP COLUMN cpf;
ALTER TABLE usuarios ADD cpf varchar(11) unique;