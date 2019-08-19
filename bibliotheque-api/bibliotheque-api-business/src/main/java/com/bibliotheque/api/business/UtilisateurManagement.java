package com.bibliotheque.api.business;

import com.bibliotheque.api.consumer.UtilisateurRepository;
import com.bibliotheque.api.model.Utilisateur;
import org.joda.time.DateTime;
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
        DateTime date = new DateTime(System.currentTimeMillis());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        utilisateur.setDateCreation(date);
        utilisateur.setMotDePasse(bCryptPasswordEncoder.encode(utilisateur.getMotDePasse()));
        repository.save(utilisateur);
    }

    public List<Utilisateur> findUtilisateurByMail(String mail) {
        return repository.findUtilisateursByMail(mail);
    }
}
