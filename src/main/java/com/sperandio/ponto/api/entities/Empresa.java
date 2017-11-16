package com.sperandio.ponto.api.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="tbempresa")
@ToString(callSuper=false)
public class Empresa implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="empresa_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private @Getter @Setter Long id;

	@Column(name="razao_social", nullable=false)
	private @Getter @Setter String razaoSocial;

	@Column(name="cnpj", nullable=false)
	private @Getter @Setter String cnpj;

	@Column(name="data_criacao", nullable=false)
	private @Getter @Setter Date dataCriacao;

	@Column(name="data_atualizacao", nullable=false)
	private @Getter @Setter Date dataAtualizacao;

	@OneToMany(mappedBy="empresa", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private @Getter @Setter List<Funcionario> funcionarios;

	@PreUpdate
	public void preUpdate() {
		this.dataAtualizacao = new Date();
	}

	@PrePersist
	public void prePersist() {
		final Date now = new Date();
		this.dataCriacao = now;
		this.dataAtualizacao = now;
	}
}
