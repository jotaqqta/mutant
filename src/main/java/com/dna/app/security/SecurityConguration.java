package com.dna.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.dna.app.security.jwtoken.EntryPoint;
import com.dna.app.security.jwtoken.ValidateToken;
import com.dna.app.service.impl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConguration extends WebSecurityConfigurerAdapter {
	
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private EntryPoint puntoEntrada;

    @Bean
    public ValidateToken validarToken(){
        return new ValidateToken();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/usuarios/**","/v2/api-docs","/swagger-resources/configuration/ui", 
                		     "/swagger-resources", "/swagger-resources/configuration/security", 
                		     "/swagger-ui.html", "/webjars/**")
                .permitAll()                
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(puntoEntrada)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(validarToken(), UsernamePasswordAuthenticationFilter.class);
    }

}
