package com.sperandio.ponto.api.repositories;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.sperandio.ponto.api.entities.Empresa;
import com.sperandio.ponto.api.entities.Funcionario;
import com.sperandio.ponto.api.entities.Lancamento;
import com.sperandio.ponto.api.enums.PerfilEnum;
import com.sperandio.ponto.api.enums.TipoEnum;
import com.sperandio.ponto.api.utils.PasswordUtils;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource(locations= {"classpath:test.properties"})
public class LancamentoRepositoryTest {

	private @Autowired EmpresaRepository empresaRepository;
	private @Autowired LancamentoRepository lancamentoRepository;
	private @Autowired FuncionarioRepository funcionarioRepository;

	private Long funcionarioId;

	private Empresa createEmpresa() {
		Empresa emp = new Empresa();
		emp.setRazaoSocial("Empresa do funcionário de teste.");
		emp.setCnpj("12345678000188");
		return emp;
	}

	private Funcionario createFuncionario(Empresa emp) {
		Funcionario bas = new Funcionario();
		bas.setNome("Bastião da Silva");
		bas.setPerfil(PerfilEnum.ROLE_USUARIO);
		bas.setSenha(PasswordUtils.gerarBCrypt("123456"));
		bas.setEmail("mail@sperandio.com");
		bas.setCpf("12345678900");
		bas.setEmpresa(emp);
		return bas;
	}

	private Lancamento createLancamento(Funcionario fun) {
		Lancamento lanc = new Lancamento();
		lanc.setData(new Date());
		lanc.setDescricao("Lancamento de teste");
		lanc.setLocalizacao("Matriz");
		lanc.setTipo(TipoEnum.INICIO_TRABALHO);
		lanc.setFuncionario(fun);
		return lanc;
	}

	@Before
	public void setup() {
		Empresa emp = empresaRepository.save(this.createEmpresa());
		Funcionario fun = funcionarioRepository.save(this.createFuncionario(emp));
		this.funcionarioId = fun.getId();
		lancamentoRepository.save(this.createLancamento(fun));
		lancamentoRepository.save(this.createLancamento(fun));
	}

	@After
	public void tearDown() {
		empresaRepository.deleteAll();
	}

	@Test
	public void testBuscarLancamentosPorFuncionarioId() {
		assertEquals(2, lancamentoRepository.findByFuncionarioId(funcionarioId).size());
	}

	@Test
	public void testBuscarLancamentosPorFuncionarioPageable() {
		PageRequest page = new PageRequest(0, 20);
		Page<Lancamento> lanc = lancamentoRepository.findByFuncionarioId(funcionarioId, page);
		assertEquals(2, lanc.getTotalElements());
	}

}
