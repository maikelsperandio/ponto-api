package com.sperandio.ponto.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.sperandio.ponto.api.entities.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

	@Transactional(readOnly=true)
	Funcionario findByCpf(String cof);
	Funcionario findByEmail(String email);
	Funcionario findByCpfOrEmail(String cpf, String email);
}
