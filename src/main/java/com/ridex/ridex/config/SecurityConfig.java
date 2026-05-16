package com.ridex.ridex.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.ridex.ridex.security.JwtFilter;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter){

        this.jwtFilter = jwtFilter;

    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        http

                .csrf(csrf -> csrf.disable())

                .cors(cors -> {})

                .sessionManagement(session ->

                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )

                )

               .authorizeHttpRequests(auth -> auth

        // PUBLIC ROUTES

        .requestMatchers(

                "/auth/**",

                "/css/**",

                "/images/**",

                "/js/**",

                "/",

                "/login.html",

                "/signup.html",

                "/driver-login.html",

                "/dashboard.html",

                "/rides.html",

                "/myrides.html",

                "/admin-dashboard.html",

                "/admin-customers.html",

                "/admin-drivers.html",

                "/admin-analytics.html"

        ).permitAll()

        // SECURED APIs

        .requestMatchers(

                "/rides/**",

                "/drivers/**",

                "/customers/**"

        ).authenticated()

        // EVERYTHING ELSE

        .anyRequest().permitAll()

)

                .addFilterBefore(

                        jwtFilter,

                        UsernamePasswordAuthenticationFilter.class

                );

        return http.build();

    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){

        CorsConfiguration configuration =
                new CorsConfiguration();

        configuration.setAllowedOrigins(
                List.of("*")
        );

        configuration.setAllowedMethods(
                List.of("GET","POST","PUT","DELETE","OPTIONS")
        );

        configuration.setAllowedHeaders(
                List.of("*")
        );

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration(
                "/**",
                configuration
        );

        return source;

    }

}
