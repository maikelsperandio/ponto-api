package com.sperandio.ponto.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource(locations= {"classpath:test.properties"})
public class PontoApiApplicationTests {

	@Test
	public void contextLoads() {
	}

}
