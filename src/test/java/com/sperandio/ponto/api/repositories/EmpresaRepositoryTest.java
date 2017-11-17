package com.sperandio.ponto.api.repositories;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.sperandio.ponto.api.entities.Empresa;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource(locations= {"classpath:test.properties"})
public class EmpresaRepositoryTest {

	private @Autowired EmpresaRepository empresaRepository;
	private static final String CNPJ = "12345678000199";

	@Before
	public void setup() {
		Empresa emp = new Empresa();
		emp.setRazaoSocial("Empresa de teste");
		emp.setCnpj(CNPJ);
		empresaRepository.save(emp);
	}

	@After
	public void tearDown() {
		empresaRepository.deleteAll();
	}

	@Test
	public void testBuscarPorCnpj() {
		Empresa emp = empresaRepository.findByCnpj(CNPJ);
		assertEquals(CNPJ, emp.getCnpj());
	}
}
