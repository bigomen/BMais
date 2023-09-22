package com.bmais.gestao.restapi.security;

import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Profile(value = "!local")
@EnableWebSecurity
public class JWTConfiguracao extends WebSecurityConfigurerAdapter {

    private final AuthUserService usuarioService;
    private final PasswordEncoder passwordEncoder;

    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v3/api-docs",
            "/webjars/**"
    };


    public JWTConfiguracao(AuthUserService usuarioService, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .configurationSource(corsConfigurationSource())
                .and()
                .csrf()
                .disable()
                .authorizeRequests()
//                .anyRequest().permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.GET, "/documentos/v1/download/**").permitAll() //FIXME: REMOVER PERMIT ALL PARA DOWNLOADS
                .antMatchers(HttpMethod.GET,"/esqueceuSenha/v1/**").permitAll()
                .antMatchers(HttpMethod.POST,"/esqueceuSenha/v1/**").permitAll()
                .antMatchers(HttpMethod.GET,"/swagger-ui/**").permitAll()
                .antMatchers(HttpMethod.GET, "/swagger-resources/**").permitAll()
                .antMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll()
                .antMatchers(HttpMethod.GET, "/v3/api-docs/**").permitAll()
                .antMatchers(HttpMethod.GET, "/webjars/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAutenticarFilter(authenticationManager()))
                .addFilter(new JWTValidarFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }


//    @Bean
//    CorsConfigurationSource corsConfigurationSource(){
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("*"));
//        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

    @Bean
    CorsConfigurationSource corsConfigurationSource()
    {
        CorsConfiguration configAdmin = new CorsConfiguration().applyPermitDefaultValues();
        configAdmin.setAllowCredentials(true);
        configAdmin.setAllowedHeaders(Arrays.asList("GET", "POST", "PATCH", "DELETE", "OPTIONS", "PUT"));
        configAdmin.addAllowedHeader("X-Requested-With");
        configAdmin.addAllowedHeader("Origin");
        configAdmin.addAllowedHeader("Content-Type");
        configAdmin.addAllowedHeader("Accept");
        configAdmin.addAllowedHeader("Authorization");
        configAdmin.addAllowedHeader("Access-Control-Allow-Origin");
        configAdmin.addAllowedHeader("Access-Control-Request-Method");
        configAdmin.addAllowedHeader("Access-Control-Request-Headers");
        configAdmin.addExposedHeader("Authorization");
        configAdmin.addExposedHeader("Content-Type");
        configAdmin.addExposedHeader("Access-Control-Allow-Headers");
        configAdmin.addExposedHeader("Access-Control-Request-Method");
        configAdmin.addExposedHeader("Access-Control-Request-Headers");
        configAdmin.addAllowedMethod(HttpMethod.PUT);
        configAdmin.addAllowedMethod(HttpMethod.PATCH);
        configAdmin.addAllowedMethod(HttpMethod.DELETE);
        configAdmin.addAllowedMethod(HttpMethod.GET);
        configAdmin.addAllowedMethod(HttpMethod.POST);
        configAdmin.addAllowedMethod(HttpMethod.OPTIONS);
        configAdmin.addAllowedOrigin("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configAdmin);
        return source;
    }
}
