package com.wellsfargo.srca.auth.config;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class UnAuthorizedAuthenticationEntryPoint  implements AuthenticationEntryPoint, Serializable {

	private static final long serialVersionUID = -8970718410437077606L;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			org.springframework.security.core.AuthenticationException authException) throws IOException {
		System.out.println(authException.getMessage());
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
				"*** You would need to provide the valid Token to Access This resource ** ");
	}

}