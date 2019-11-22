CREATE TABLE TB_Especialidade (
	id_Especialidade INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
	nome VARCHAR(50)  NULL  ,
	descricao VARCHAR(120)  NULL,
PRIMARY KEY(id_Especialidade));


CREATE TABLE TB_Enfermidade (
	id_Enfermidade INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
	CID VARCHAR(3),
	nome VARCHAR(50),
PRIMARY KEY(id_Enfermidade));


CREATE TABLE TB_Plan_Saude (
	id_Plan_Saude INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
	num_Plan VARCHAR(20) NOT NULL,
	nome VARCHAR(50)  NOT NULL  ,
	tipo VARCHAR(50)  NOT NULL    ,
PRIMARY KEY(id_Plan_Saude));



CREATE TABLE TB_Prontuario (
  id_Prontuario INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  descricao VARCHAR(320)  NOT NULL,
PRIMARY KEY(id_Prontuario));


CREATE TABLE TB_Aparelho (
  id_Aparelho INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  nome VARCHAR(60)  NOT NULL,
  descricao VARCHAR(80) NOT NULL,
  estado ENUM('DISPONÍVEL','OCUPADO','EM MANUTENÇÃO')  NOT NULL,
PRIMARY KEY(id_Aparelho));



CREATE TABLE TB_Departamento (
  id_Departamento INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  nome VARCHAR(50)  NOT NULL  ,
  descricao VARCHAR(120) NOT NULL    ,
PRIMARY KEY(id_Departamento));


CREATE TABLE TB_Endereco (
  id_Endereco INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  CEP VARCHAR(8)  NOT NULL,
  rua VARCHAR(40) NOT NULL  ,
  numero VARCHAR(5)  NOT NULL  ,
  complemento VARCHAR(20) NULL  ,
  cidade VARCHAR(40) NOT NULL  ,
  UF VARCHAR(2) NOT NULL    ,
PRIMARY KEY(id_Endereco));


CREATE TABLE TB_Cargo (
  id_Cargo INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  TB_Departamento_id_Departamento INTEGER UNSIGNED  NOT NULL  ,
  nome VARCHAR(50) NOT NULL  ,
  descricao VARCHAR(120) NOT NULL    ,
PRIMARY KEY(id_Cargo),
  FOREIGN KEY(TB_Departamento_id_Departamento)
    REFERENCES TB_Departamento(id_Departamento)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);


CREATE TABLE TB_Tratamento (
  id_Tratamento INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  TB_Aparelho_id_Aparelho INTEGER UNSIGNED  NOT NULL  ,
  nome VARCHAR(50)  NULL  ,
  descricao VARCHAR(120)  NULL    ,
PRIMARY KEY(id_Tratamento),
  FOREIGN KEY(TB_Aparelho_id_Aparelho)
    REFERENCES TB_Aparelho(id_Aparelho)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);


CREATE TABLE TB_Plan_Saude_TB_Tratamento (
  TB_Plan_Saude_id_Plan_Saude INTEGER UNSIGNED  NOT NULL  ,
  TB_Tratamento_id_Tratamento INTEGER UNSIGNED  NOT NULL    ,
PRIMARY KEY(TB_Plan_Saude_id_Plan_Saude, TB_Tratamento_id_Tratamento),
  FOREIGN KEY(TB_Plan_Saude_id_Plan_Saude)
    REFERENCES TB_Plan_Saude(id_Plan_Saude)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(TB_Tratamento_id_Tratamento)
    REFERENCES TB_Tratamento(id_Tratamento)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);


CREATE TABLE TB_Paciente (
	id_Paciente INTEGER  NOT NULL   AUTO_INCREMENT,
	TB_Plan_Saude_id_Plan_Saude INTEGER UNSIGNED  NOT NULL  ,
	TB_Endereco_id_Endereco INTEGER UNSIGNED  NOT NULL  ,
	nome VARCHAR(50) NOT NULL  ,
	CPF VARCHAR(11) NOT NULL  ,
	RG VARCHAR(9) NOT NULL  ,
	data_nasc DATE NOT NULL  ,
	e_mail VARCHAR(60) NULL  ,
	tipo_sang ENUM('A+','B+','AB+','O+','A-','B-','AB-','O-') NULL  ,
	sexo ENUM('M','F')  NULL    ,
	PRIMARY KEY(id_Paciente),	
	FOREIGN KEY(TB_Endereco_id_Endereco)
		REFERENCES TB_Endereco(id_Endereco)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION,
	FOREIGN KEY(TB_Plan_Saude_id_Plan_Saude)
		REFERENCES TB_Plan_Saude(id_Plan_Saude)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION);


CREATE TABLE TB_Paciente_TB_Enfermidade (
	TB_Paciente_id_Paciente INTEGER  NOT NULL  ,
	TB_Enfermidade_id_Enfermidade INTEGER UNSIGNED  NOT NULL    ,
	PRIMARY KEY(TB_Paciente_id_Paciente, TB_Enfermidade_id_Enfermidade),
	FOREIGN KEY(TB_Paciente_id_Paciente)
		REFERENCES TB_Paciente(id_Paciente)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION,
	FOREIGN KEY(TB_Enfermidade_id_Enfermidade)
		REFERENCES TB_Enfermidade(id_Enfermidade)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION);


CREATE TABLE TB_Funcionario (
	id_Funcionario INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
	TB_Departamento_id_Departamento INTEGER UNSIGNED  NOT NULL,
	TB_Endereco_id_Endereco INTEGER UNSIGNED  NOT NULL,
	TB_Especialidade_id_Especialidade INTEGER UNSIGNED  NOT NULL  ,
	nome VARCHAR(50) NOT NULL  ,
	CPF VARCHAR(11) NOT NULL  ,
	RG VARCHAR(9) NOT NULL  ,
	data_nasc DATE NOT NULL  ,
	e_mail VARCHAR(60)  NULL  ,
	tipo_sang ENUM('A+','B+','AB+','O+','A-','B-','AB-','O-') NOT NULL  ,
	sexo ENUM('F','M') NOT NULL  ,
	salario DECIMAL(8,2) NOT NULL    ,
	escolaridade VARCHAR(60) NOT NULL,
	PRIMARY KEY(id_Funcionario),
	FOREIGN KEY(TB_Especialidade_id_Especialidade)
		REFERENCES TB_Especialidade(id_Especialidade)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION,
	FOREIGN KEY(TB_Endereco_id_Endereco)
		REFERENCES TB_Endereco(id_Endereco)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION,
	FOREIGN KEY(TB_Departamento_id_Departamento)
		REFERENCES TB_Departamento(id_Departamento)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION);


CREATE TABLE TB_Telefone (
  id_Telefone INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  TB_Funcionario_id_Funcionario INTEGER UNSIGNED,
  TB_Paciente_id_Paciente INTEGER,
  tel_cel VARCHAR(12)  NULL    ,
PRIMARY KEY(id_Telefone),
  FOREIGN KEY(TB_Paciente_id_Paciente)
    REFERENCES TB_Paciente(id_Paciente)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(TB_Funcionario_id_Funcionario)
    REFERENCES TB_Funcionario(id_Funcionario)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);


CREATE TABLE TB_Sessao (
  id_Sessao INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  TB_Prontuario_id_Prontuario INTEGER UNSIGNED  NOT NULL  ,
  TB_Paciente_id_Paciente INTEGER  NOT NULL  ,
  hora_inicio TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL  ,
  hora_fim TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL  ,
  valor_final DECIMAL(8,2) NOT NULL  ,
  estado ENUM('MARCADA','ENCERRADA','CANCELADA') NOT NULL    ,
PRIMARY KEY(id_Sessao),
  FOREIGN KEY(TB_Paciente_id_Paciente)
    REFERENCES TB_Paciente(id_Paciente)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(TB_Prontuario_id_Prontuario)
    REFERENCES TB_Prontuario(id_Prontuario)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);


CREATE TABLE TB_Funcionario_TB_Sessao (
  TB_Funcionario_id_Funcionario INTEGER UNSIGNED  NOT NULL  ,
  TB_Sessao_id_Sessao INTEGER UNSIGNED  NOT NULL    ,
PRIMARY KEY(TB_Funcionario_id_Funcionario, TB_Sessao_id_Sessao),
  FOREIGN KEY(TB_Funcionario_id_Funcionario)
    REFERENCES TB_Funcionario(id_Funcionario)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(TB_Sessao_id_Sessao)
    REFERENCES TB_Sessao(id_Sessao)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);



