package com.sperandio.ponto.api.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {

	public static String gerarBCrypt(String senha) {
		if(senha == null || senha.isEmpty()) {
			return senha;
		}
		BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
		return enc.encode(senha);
	}
}
