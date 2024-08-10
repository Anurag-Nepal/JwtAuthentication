package com.anurag.demo.config;

import com.anurag.demo.Entity.UserPrincipal;
import com.anurag.demo.Filter.JwtFilter;
import com.anurag.demo.Service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.net.http.HttpRequest;

@Configuration
@EnableWebSecurity
public class SecutiryConfig {


   @Autowired
    private JwtFilter jwtFilter;
@Autowired
private UserDetailsService userDetailService;





    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
   return  http
        .csrf(customizer -> customizer.disable())
             .authorizeHttpRequests(request -> request
                     .requestMatchers("register","login")
                     .permitAll()
                     .anyRequest()
                     .authenticated())
             // .formLogin(Customizer.withDefaults())
             .httpBasic(Customizer.withDefaults())
           .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
           .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
             .build();


    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService)
    {
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


@Bean
    public PasswordEncoder passwordEncoder()
    {
        return  new BCryptPasswordEncoder();
    }
}
