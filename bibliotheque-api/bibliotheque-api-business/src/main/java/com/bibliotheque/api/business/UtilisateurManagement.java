package com.bibliotheque.api.business;

import com.bibliotheque.api.consumer.UtilisateurRepository;
import com.bibliotheque.api.model.Utilisateur;
import org.joda.time.DateTime;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UtilisateurManagement extends JpaCrudManager<Utilisateur, UtilisateurRepository> implements UserDetailsService {
    public UtilisateurManagement(UtilisateurRepository repository) {
        super(repository);
    }

    @Override
    public void save(Utilisateur utilisateur) throws Error {
        if (findUtilisateurByMail(utilisateur.getMail()) == null ||
        findById(utilisateur.getId()).isPresent()) {
            DateTime date = new DateTime(System.currentTimeMillis());
            utilisateur.setDateCreation(date);
            if (!findById(utilisateur.getId()).isPresent()) {
                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                utilisateur.setMotDePasse(bCryptPasswordEncoder.encode(utilisateur.getMotDePasse()));
            }
            repository.save(utilisateur);
        }
    }

    public Utilisateur findUtilisateurByMail(String mail) {
        return repository.findUtilisateurByMail(mail);
    }

    public boolean connexion(String mail, String password) {
        Utilisateur utilisateur = findUtilisateurByMail(mail);
        if (utilisateur != null) {
            return BCrypt.checkpw(password, utilisateur.getMotDePasse());
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Utilisateur utilisateur = repository.findUtilisateurByMail(s);
        if (utilisateur == null)
            throw new UsernameNotFoundException(s);
        return new UserDetails() {

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                if (!utilisateur.isBibliothecaire())
                    return Collections.singleton(new SimpleGrantedAuthority("utilisateur"));
                return Collections.singleton(new SimpleGrantedAuthority("bibliothecaire"));
            }

            @Override
            public String getPassword() {
                return utilisateur.getMotDePasse();
            }

            @Override
            public String getUsername() {
                return utilisateur.getMail();
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };
    }
}
