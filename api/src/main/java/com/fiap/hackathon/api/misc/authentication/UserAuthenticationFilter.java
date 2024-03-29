package com.fiap.hackathon.api.misc.authentication;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fiap.hackathon.api.misc.SecurityConfiguration;
import com.fiap.hackathon.api.misc.token.TokenJwtUtils;
import com.fiap.hackathon.entity.employee.Employee;
import com.fiap.hackathon.entity.employee.EmployeeType;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class UserAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Autowired
    private TokenJwtUtils jwtTokenService; // Service que definimos anteriormente

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            // Verifica se o endpoint requer autenticação antes de processar a requisição
            if (checkIfEndpointIsAuthenticated(request)) {
                String token = recoveryToken(request); // Recupera o token do cabeçalho Authorization da requisição
                if (token != null) {
                    Employee employee = jwtTokenService.getEmployeeFromToken(token);
                    List<SimpleGrantedAuthority> authorities = jwtTokenService.getAuthoritiesFromToken(token);

                    // Cria um objeto de autenticação do Spring Security
                    Authentication authentication =
                            new UsernamePasswordAuthenticationToken(employee.getId(), employee, authorities);

                    // Define o objeto de autenticação no contexto de segurança do Spring Security
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    throw new JWTVerificationException("Token de autenticação não informado.");
                }
            }
            filterChain.doFilter(request, response); // Continua o processamento da requisição
        } catch (Exception e) {
            resolver.resolveException(request, response, null, new JWTVerificationException(e.getMessage(), e));
        }
    }

    // Recupera o token do cabeçalho Authorization da requisição
    private String recoveryToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }

    // Verifica se o endpoint requer autenticação antes de processar a requisição
    private boolean checkIfEndpointIsAuthenticated(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return Arrays.asList(SecurityConfiguration.ENDPOINTS_WITH_AUTHENTICATION_REQUIRED).contains(requestURI);
    }
}