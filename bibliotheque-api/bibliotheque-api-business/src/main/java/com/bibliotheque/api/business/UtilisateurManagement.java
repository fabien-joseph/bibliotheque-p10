package com.bibliotheque.api.business;

import com.bibliotheque.api.consumer.UtilisateurRepository;
import com.bibliotheque.api.model.Utilisateur;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurManagement extends JpaCrudManager<Utilisateur, UtilisateurRepository> {
    public UtilisateurManagement(UtilisateurRepository repository) {
        super(repository);
    }
}
