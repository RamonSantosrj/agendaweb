create database bd_agendaweb;
use bd_agendaweb;

create table usuario(
	idusuario		integer			auto_increment,
    nome			varchar(150)	not null,
    email			varchar(100)	not null unique,
    senha			varchar(40)		not null,
    primary key(idusuario));
    
create table tarefa(
	idtarefa		integer			auto_increment,
    nome			varchar(150)	not null,
    data			date			not null,
    hora			time			not null,
    descricao		varchar(500)	not null,
    prioridade		integer			not null,
    idusuario		integer			not null,
    primary key(idtarefa),
    foreign key(idusuario) references usuario(idusuario),
    check(prioridade in(1, 2, 3)));
    
show tables;
    
    


