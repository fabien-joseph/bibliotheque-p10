package com.bibliotheque.api.business;

import com.bibliotheque.api.consumer.UtilisateurRepository;
import com.bibliotheque.api.model.Utilisateur;
import org.joda.time.DateTime;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurManagement extends JpaCrudManager<Utilisateur, UtilisateurRepository> {
    public UtilisateurManagement(UtilisateurRepository repository) {
        super(repository);
    }

    @Override
    public void save(Utilisateur utilisateur) {
        if (findUtilisateurByMail(utilisateur.getMail()) == null) {
            DateTime date = new DateTime(System.currentTimeMillis());
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            utilisateur.setDateCreation(date);
            utilisateur.setMotDePasse(bCryptPasswordEncoder.encode(utilisateur.getMotDePasse()));
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
}
