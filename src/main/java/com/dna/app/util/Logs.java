package com.dna.app.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Logs {

	private Logs() {
		super();
	}
	
	private static Logger logger = LoggerFactory.getLogger(Logs.class);

	public static void debug(String mensaje) {
		logger.debug(mensaje);
	}

	public static void error(String mensaje) {
		logger.error(mensaje);
	}

	public static void warn(String mensaje) {
		logger.warn(mensaje);
	}

	public static void info(String mensaje) {
		logger.info(mensaje);
	}

}
