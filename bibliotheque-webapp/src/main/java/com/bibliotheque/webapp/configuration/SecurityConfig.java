package com.bibliotheque.webapp.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/profile**").authenticated()
                .antMatchers("/*").permitAll()
                .antMatchers("/connexion").permitAll()
                .antMatchers("/inscription").permitAll()
                .antMatchers("/livres").permitAll()
                .antMatchers("/livres/{id}").permitAll()
                .antMatchers("/reservation").permitAll()
                .antMatchers("/contact").permitAll()
                .antMatchers("/css/*").permitAll()
                .antMatchers("/css/images/*").permitAll()
                .and()
                .formLogin()
                .loginPage("/connexion")
                .loginProcessingUrl("/connexion")
                .defaultSuccessUrl("/", true)
                .failureUrl("/connexion")
                .and()
                .logout().invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").permitAll();
    }
}
