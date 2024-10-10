package com.ezbid.config;

import com.ezbid.service.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final HandlerExceptionResolver handlerExceptionResolver;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(HandlerExceptionResolver handlerExceptionResolver, JwtService jwtService, UserDetailsService userDetailsService) {
        this.handlerExceptionResolver = handlerExceptionResolver;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");

        // Check for missing or invalid Authorization header
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            logger.warn("Missing or invalid Authorization header.");
            filterChain.doFilter(request, response); // Continue with the filter chain if there's no valid header
            return;
        }

        try {
            final String jwt = authHeader.substring(7); // Extract the JWT from the Bearer token
            final String username = jwtService.extractUsername(jwt); // Extract the username from the JWT

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            // Check if username is valid and the user is not yet authenticated
            if (username != null && authentication == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                // Validate the token
                if (jwtService.isTokenValid(jwt, userDetails)) {
                    // Create and set the authentication token
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                } else {
                    // Invalid token, set 401 Unauthorized and return
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("Invalid token. Please log in again.");
                    response.getWriter().flush();
                    return; // Stop further processing
                }
            }

            // If everything is successful, proceed with the filter chain
            filterChain.doFilter(request, response);

        } catch (ExpiredJwtException e) {
            // Token expired, send 401 response
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token expired. Please log in again.");
            response.getWriter().flush();
            return; // Stop further processing

        } catch (JwtException e) {
            // Invalid JWT, send 401 response
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid token. Please log in again.");
            response.getWriter().flush();
            return; // Stop further processing

        } catch (Exception e) {
            // General exception handling, resolve it using the handlerExceptionResolver
            handlerExceptionResolver.resolveException(request, response, null, e);
        }
    }
}
