package com.bdc.desafio.core.util;

import org.apache.logging.log4j.util.Strings;

public class Utils {

	public static final String removerFormatacao(String numero) {
		if (Strings.isNotBlank(numero)) {
			numero = numero.replaceAll("\\D", Strings.EMPTY);
		}

		return numero;
	}
}
