create table lancamento (
        lancamento_id bigint not null auto_increment,
        data datetime not null,
        data_atualizacao datetime not null,
        data_criacao datetime not null,
        descricao varchar(255) not null,
        localizacao varchar(255) not null,
        tipo varchar(255) not null,
        funcionario_funcionario_id bigint,
        primary key (lancamento_id)
);
create table tbempresa (
        empresa_id bigint not null auto_increment,
        cnpj varchar(255) not null,
        data_atualizacao datetime not null,
        data_criacao datetime not null,
        razao_social varchar(255) not null,
        primary key (empresa_id)
);
create table tbfuncionario (
        funcionario_id bigint not null auto_increment,
        cpf varchar(255) not null,
        data_atualizacao datetime not null,
        data_criacao datetime not null,
        email varchar(255) not null,
        nome varchar(255) not null,
        perfil varchar(255) not null,
        qtde_horas_almoco float,
        qtde_horas_trabalhadas_dia float,
        senha varchar(255) not null,
        valor_hora decimal(19,2),
        empresa_empresa_id bigint,
        primary key (funcionario_id)
);
alter table lancamento 
        add constraint FK8swdj6cxcl9u7akpy4hxvi7bt 
        foreign key (funcionario_funcionario_id) 
        references tbfuncionario (funcionario_id);
		
alter table tbfuncionario 
        add constraint FKjnb5x37tcu78n53pggfodb7vb 
        foreign key (empresa_empresa_id) 
        references tbempresa (empresa_id);
