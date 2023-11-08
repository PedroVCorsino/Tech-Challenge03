package br.com.grupo27.techchallenge03.external.security;

import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.JwkProviderBuilder;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class JwtTokenFilter extends OncePerRequestFilter {

    private static final String COGNITO_JWKS_URL = "https://techchallenge.auth.us-east-1.amazoncognito.com/.well-known/jwks.json";
    private final JwkProvider jwkProvider;

    public JwtTokenFilter() throws MalformedURLException {
        this.jwkProvider = new JwkProviderBuilder(new URL(COGNITO_JWKS_URL)).build();
    }

    @Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
    String requestURI = request.getRequestURI();
    
    if (requestURI != null && requestURI.startsWith("/public/")) {
        filterChain.doFilter(request, response);
        return;
    }

    String authHeader = request.getHeader("Authorization");
    if (authHeader != null && authHeader.startsWith("Bearer ")) {
        String jwtToken = authHeader.substring(7);
        try {
            Claims claims = validateToken(jwtToken);
            // Aqui, você pode adicionar as 'claims' ao contexto da aplicação ou fazer outras verificações necessárias
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
    }
    filterChain.doFilter(request, response);
}

    private Claims validateToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKeyResolver(new CognitoSigningKeyResolver(jwkProvider))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}