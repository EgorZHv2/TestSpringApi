package springApp.infrastructure.security.services;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import springApp.core.application.interfaces.services.ITokenService;


import java.io.IOException;
import java.util.concurrent.ExecutionException;


public class JwtAuthFilter extends OncePerRequestFilter {
    private static final String AUTHORIZATION = "Authorization";
    @Autowired
    private ITokenService tokenService;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain fc)
            throws IOException, ServletException {
        final String token = getTokenFromRequest(request);
        if (token != null && tokenService.validateJwtToken(token)) {
            var phone = tokenService.getUserPhoneFromJwtToken(token);
            var details = userDetailsService.loadUserByUsername(phone);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken
                    (details, null, details.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        fc.doFilter(request, response);
    }
    private String getTokenFromRequest(HttpServletRequest request) {
        final String bearer = request.getHeader(AUTHORIZATION);
        if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }

}
