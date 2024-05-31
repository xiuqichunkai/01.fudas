package com.example.SpringSecurity.config;

import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.*;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.PostMapping;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//                        .requestMatchers("/","/my/**","articlesht/**","/main","/idx","/index").hasAnyRole("ADMIN", "USER")
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/fuda/**" , "/login", "loginProc", "/join", "/joinProc" , "/images/**" ,"/SimpleSidebar/**", "/bootstrap-5.2.3/**", "/bootstrap20240416/**","/layouts/**","/btsFrontpage/**", "/cleanblog/**").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers( "/articlesht/**","/articlesxw/**", "/main","/idx","/index").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated()
                );

        http
                .formLogin((auth) -> auth.loginPage("/login")
                        .loginProcessingUrl("/loginProc")
                        .permitAll()
                );

        // login user 3 maximum
        http
                .sessionManagement((auth) -> auth
                        .maximumSessions(3)
                        .maxSessionsPreventsLogin(true));


        http
                .sessionManagement((auth) -> auth
                        .sessionFixation().changeSessionId());

        // logout GetMapping
        http
                .logout((auth) -> auth.logoutUrl("/logout")
                        .logoutSuccessUrl("/"));


//        http
//                .csrf((auth) -> auth.disable());

        return http.build();
    }
}
