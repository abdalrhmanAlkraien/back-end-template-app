package com.marinabits.energietechs.infra.security;

import com.marinabits.energietechs.repository.entity.Role;
import com.marinabits.energietechs.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import static org.springframework.security.core.context.SecurityContextHolder.MODE_INHERITABLETHREADLOCAL;
import static org.springframework.security.core.context.SecurityContextHolder.setStrategyName;

@Log4j2
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
        jsr250Enabled = true
)
public class SecurityConfig {

    public SecurityConfig() {
        // Inherit security context in async function calls
        setStrategyName(MODE_INHERITABLETHREADLOCAL);
    }

    // Configure DaoAuthenticationProvider for username and password
    @Bean
    public DaoAuthenticationProvider authenticationProvider(final PasswordEncoder passwordEncoder,
                                                            final UserService userService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder);

        return authProvider;
    }

    // Expose authentication manager bean
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    // Core Security configurations
    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http,
                                           final AuthJwtTokenFilter authJwtTokenFilter,
                                           final AuthJwtEntryPoint unauthorizedHandler)
            throws Exception {

        // List of Swagger URLs, root page, Our public endpoints, images
        final var authWhitelist = new String[]{
                "/api-docs/**", "/webjars/**", "/auth/refresh_token",
                "/auth/signin", "/auth/signup", "/swagger-ui/**",
                "/doc/**", "/", "/index.html", "/assets/**", "/error/**", "/organization/**", "/languages/**", "/file/**",
                "/pre-defined-profiles/**",
                "/draft/load-configuration/load/upload-load/**",
                "/draft/load-configuration/draw-component/**"};

        http
                // Enable CORS
//                .cors(AbstractHttpConfigurer::disable)
//                .cors(httpSecurityCorsConfigurer ->
//                        httpSecurityCorsConfigurer.configurationSource(request ->
//                                new CorsConfiguration().applyPermitDefaultValues()
//                        ))
//                //Disable CSRF
//                .csrf(AbstractHttpConfigurer::disable)
                // Enable CORS
                .cors(withDefaults())
                //Disable CSRF
                .csrf(AbstractHttpConfigurer::disable)
                // Set session management to stateless
                .sessionManagement(customizer -> customizer.sessionCreationPolicy(STATELESS))
                // Set unauthorized requests exception handler
                .exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(unauthorizedHandler))
                // Set permission to allow open db-console
//                .authorizeHttpRequests(auth ->
//                        auth.requestMatchers(PathRequest.toH2Console()).permitAll())
                // This will allow frames with same origin which is much more safe
                .headers(headers ->
                        headers.frameOptions(FrameOptionsConfig::sameOrigin).disable())

                // Enable all whitelisted pages
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers(authWhitelist).permitAll())

                // Only Admin Allowed to do the following
                .authorizeHttpRequests(auth ->
                        // Upload files and manage countries
                        auth.requestMatchers(POST, "/auth/change_password", "/auth/signout")
                                .hasAnyAuthority(Role.ADMIN, Role.CLIENT))
                .authorizeHttpRequests(auth ->
                        // Upload files and manage countries
                        auth.requestMatchers(GET, "/organization/**")
                                .hasAnyAuthority(Role.ADMIN))
                .authorizeHttpRequests(auth ->
                        // Upload files and manage countries
                        auth.requestMatchers(GET, "/organization/{ID}")
                                .hasAnyAuthority(Role.ADMIN))

                //Our private endpoints
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())

                // Add JWT token filter
                .addFilterBefore(authJwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    // Used by spring security if CORS is enabled.
    @Bean
    public CorsFilter corsFilter() {
        var source = new UrlBasedCorsConfigurationSource();
        var config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setAllowedOriginPatterns(List.of("*"));

        config.addExposedHeader("Authorization");
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
