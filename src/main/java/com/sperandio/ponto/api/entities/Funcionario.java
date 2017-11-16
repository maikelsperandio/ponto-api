package com.sperandio.ponto.api.entities;

import java.beans.Transient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.sperandio.ponto.api.enums.PerfilEnum;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="tbfuncionario")
@ToString(exclude= {"empresa"},callSuper=false)
public class Funcionario implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="funcionario_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private @Getter @Setter Long id;

	@Column(name="nome", nullable=false)
	private @Getter @Setter String nome;

	@Column(name="email", nullable=false)
	private @Getter @Setter String email;

	@Column(name="senha", nullable=false)
	private @Getter @Setter String senha;

	@Column(name="cpf", nullable=false)
	private @Getter @Setter String cpf;

	@Column(name="valor_hora", nullable=true)
	private @Getter @Setter BigDecimal valorHora;

	@Column(name="qtde_horas_trabalhadas_dia", nullable=true)
	private @Getter @Setter Float qtdeHorasTrabalhadasDia;

	@Column(name="qtde_horas_almoco", nullable=true)
	private @Getter @Setter Float qtdeHorasAlmoco;

	@Column(name="data_criacao", nullable=false)
	private @Getter @Setter Date dataCriacao;
	
	@Column(name="data_atualizacao", nullable=false)
	private @Getter @Setter Date dataAtualizacao;

	@Enumerated(EnumType.STRING)
	@Column(name="perfil", nullable=false)
	private @Getter @Setter PerfilEnum perfil;

	@ManyToOne(fetch=FetchType.EAGER)
	private @Getter @Setter Empresa empresa;

	@OneToMany(mappedBy="funcionario", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private @Getter @Setter List<Lancamento> lancamentos;

	@Transient
	public Optional<BigDecimal> getValorHoraOpt(){
		return Optional.ofNullable(this.valorHora);
	}

	@Transient
	public Optional<Float> getQtdHorasTrabalhadasDiaOpt(){
		return Optional.ofNullable(this.qtdeHorasTrabalhadasDia);
	}

	@Transient
	public Optional<Float> getQtdeHorasAlmocoOpt(){
		return Optional.ofNullable(this.qtdeHorasAlmoco);
	}

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
