package com.bibliotheque.api.service.configuration;

import com.bibliotheque.api.business.UtilisateurManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter {
    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    private final UtilisateurManagement utilisateurManagement;

    @Autowired
    public SecurityJavaConfig(RestAuthenticationEntryPoint restAuthenticationEntryPoint, UtilisateurManagement utilisateurManagement) {
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
        this.utilisateurManagement = utilisateurManagement;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(utilisateurManagement);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/reservations/expired").permitAll()
                .antMatchers(HttpMethod.GET, "/livres").permitAll()
                .antMatchers(HttpMethod.GET, "/livres/{livreId}").permitAll()
                .antMatchers(HttpMethod.GET, "/utilisateurs").permitAll()
                .antMatchers(HttpMethod.GET, "/utilisateurs/connexion").permitAll()
                .antMatchers(HttpMethod.GET, "/utilisateurs/{utilisateurId}").permitAll()
                .antMatchers(HttpMethod.GET, "/reservations").hasAnyAuthority("utilisateur", "bibliothecaire")
                .antMatchers(HttpMethod.GET, "/reservations/{reservationId}").hasAnyAuthority("utilisateur", "bibliothecaire")
                .antMatchers(HttpMethod.PATCH, "/reservations/{reservationId}/renew").hasAnyAuthority("utilisateur", "bibliothecaire")
                .antMatchers(HttpMethod.POST, "/livres").hasAnyAuthority("bibliothecaire")
                .antMatchers(HttpMethod.PUT, "/livres/{livreId}").hasAnyAuthority("bibliothecaire")
                .antMatchers(HttpMethod.DELETE, "/livres/{livreId}").hasAnyAuthority("bibliothecaire")
                .antMatchers(HttpMethod.POST, "/reservations").hasAnyAuthority("utilisateur", "bibliothecaire")
                .antMatchers(HttpMethod.DELETE, "/reservations/{reservationId}").hasAnyAuthority("bibliothecaire")
                .antMatchers(HttpMethod.PATCH, "/reservations/{reservationId}/toggleRendu").hasAnyAuthority("bibliothecaire")
                .antMatchers(HttpMethod.POST, "/utilisateurs").hasAnyAuthority("bibliothecaire")
                .antMatchers(HttpMethod.PUT, "/utilisateurs/{utilisateurId}").hasAnyAuthority("bibliothecaire")
                .antMatchers(HttpMethod.DELETE, "/utilisateurs/{utilisateurId}").hasAnyAuthority("bibliothecaire")
                .and()
                .httpBasic()
                .authenticationEntryPoint(restAuthenticationEntryPoint);
    }
}
