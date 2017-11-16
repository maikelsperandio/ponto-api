package com.sperandio.ponto.api.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.sperandio.ponto.api.enums.TipoEnum;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="lancamento")
@ToString(exclude= {"funcionario"},callSuper=false)
public class Lancamento implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="lancamento_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private @Getter @Setter Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data", nullable=false)
	private @Getter @Setter Date data;

	@Column(name="descricao", nullable=false)
	private @Getter @Setter String descricao;

	@Column(name="localizacao", nullable=false)
	private @Getter @Setter String localizacao;

	@Column(name="data_criacao", nullable=false)
	private @Getter @Setter Date dataCriacao;
	
	@Column(name="data_atualizacao", nullable=false)
	private @Getter @Setter Date dataAtualizacao;

	@Enumerated(EnumType.STRING)
	@Column(name="tipo", nullable=false)
	private @Getter @Setter TipoEnum tipo;

	@ManyToOne(fetch=FetchType.EAGER)
	private @Getter @Setter Funcionario funcionario;

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
