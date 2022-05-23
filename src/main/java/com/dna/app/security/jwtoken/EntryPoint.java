package com.dna.app.security.jwtoken;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.dna.app.util.Logs;

@Component
public class EntryPoint implements AuthenticationEntryPoint{

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			             AuthenticationException authException) throws IOException, ServletException {
		Logs.error("PuntoEntrada fallo metodo commence.");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Solicitud no autorizada.");
	}

}
