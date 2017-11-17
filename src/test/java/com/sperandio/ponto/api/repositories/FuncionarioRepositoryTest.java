package com.sperandio.ponto.api.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.sperandio.ponto.api.entities.Empresa;
import com.sperandio.ponto.api.entities.Funcionario;
import com.sperandio.ponto.api.enums.PerfilEnum;
import com.sperandio.ponto.api.utils.PasswordUtils;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource(locations= {"classpath:test.properties"})
public class FuncionarioRepositoryTest {

	private @Autowired FuncionarioRepository funcionarioRepository;
	private @Autowired EmpresaRepository empresaRepository;

	private static final String CPF = "12345678900";
	private static final String EMAIL = "teste@sperandio.com";

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
		bas.setEmail(EMAIL);
		bas.setCpf(CPF);
		bas.setEmpresa(emp);
		return bas;
	}

	@Before
	public void setup() {
		Empresa emp = empresaRepository.save(this.createEmpresa());
		funcionarioRepository.save(this.createFuncionario(emp));
	}

	@After
	public void tearDown() {
		empresaRepository.deleteAll();
	}

	@Test
	public void testBuscarFuncionarioPorEmail() {
		Funcionario fun = funcionarioRepository.findByEmail(EMAIL);
		assertNotNull(fun);
		assertEquals(EMAIL, fun.getEmail());
	}

	@Test
	public void testBuscarFuncionarioPorCpf() {
		Funcionario fun = funcionarioRepository.findByCpf(CPF);
		assertNotNull(fun);
		assertEquals(CPF, fun.getCpf());
	}

	@Test
	public void testBuscarFuncionarioPorEmailOrCpfComCpfNull() {
		assertNotNull(funcionarioRepository.findByCpfOrEmail(null, EMAIL));
	}

	@Test
	public void testBuscarFuncionarioPorEmailOrCpfComEmailNull() {
		assertNotNull(funcionarioRepository.findByCpfOrEmail(CPF, null));
	}

	@Test
	public void testBuscarFuncionarioPorEmailOrCpfComTudoOk() {
		assertNotNull(funcionarioRepository.findByCpfOrEmail(CPF, EMAIL));
	}
}
