package com.jedos.jedosbackend.config;

import java.io.IOException;
import jakarta.servlet.ServletException;  // Cambia a 'jakarta' en lugar de 'javax'
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        // Redirigir a la página del frontend o realizar alguna acción adicional
        response.sendRedirect("http://localhost:4200"); // Redirige al frontend después del login exitoso
    }
}
