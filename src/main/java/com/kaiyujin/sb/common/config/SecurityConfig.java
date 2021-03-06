package com.kaiyujin.sb.common.config;

import com.kaiyujin.sb.common.ApplicationSettings;
import com.kaiyujin.sb.common.Constants;
import com.kaiyujin.sb.common.security.*;
import com.kaiyujin.sb.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.GenericFilterBean;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationSettings settings;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                // AUTHORIZE
                .authorizeRequests()
                .mvcMatchers(Constants.CUSTEMER_API_BASE_URL + "/**",
                        "/v2/api-docs", "/configuration/ui", "/swagger-resources",
                        "/configuration/security", "/swagger-ui.html", "/webjars/**",
                        "/swagger-resources/configuration/ui", "/swagge‌​r-ui.html",
                        "/swagger-resources/configuration/security", "/actuator/health")
                .permitAll()
                .mvcMatchers(Constants.CLIENT_API_BASE_URL + "/**")
                .hasRole("USER")
                .mvcMatchers(Constants.ADMIN_API_BASE_URL + "/**")
                .hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                // EXCEPTION
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint())
                .accessDeniedHandler(accessDeniedHandler())
                .and()
                // LOGIN
                .formLogin()
                .loginProcessingUrl("/api/auth/login").permitAll()
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(authenticationSuccessHandler())
                .failureHandler(authenticationFailureHandler())
                .and()
                // CSRF
                .csrf()
                .disable()
                // AUTHORIZE
                .addFilterBefore(tokenFilter(), UsernamePasswordAuthenticationFilter.class)
                // SESSION
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ;
        // @formatter:on
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.eraseCredentials(true)
                .userDetailsService(simpleUserDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    @Bean("simpleUserDetailsService")
    UserDetailsService simpleUserDetailsService() {
        return new SimpleUserDetailsService(userRepository);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    GenericFilterBean tokenFilter() {
        return new SimpleTokenFilter(userRepository, settings.getSecretKey());
    }

    AuthenticationEntryPoint authenticationEntryPoint() {
        return new SimpleAuthenticationEntryPoint();
    }

    AccessDeniedHandler accessDeniedHandler() {
        return new SimpleAccessDeniedHandler();
    }

    AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new SimpleAuthenticationSuccessHandler(settings.getSecretKey());
    }

    AuthenticationFailureHandler authenticationFailureHandler() {
        return new SimpleAuthenticationFailureHandler();
    }

}
