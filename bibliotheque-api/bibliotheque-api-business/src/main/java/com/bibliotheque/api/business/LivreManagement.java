package com.bibliotheque.api.business;

import com.bibliotheque.api.consumer.LivreRepository;
import com.bibliotheque.api.model.Livre;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class LivreManagement extends JpaCrudManager<Livre, LivreRepository> {
    public LivreManagement(LivreRepository repository) {
        super(repository);
    }

    public List<Livre> findAll() {
        return repository.findAll();
    }

 /*   public com.bibliotheque.api.service.model.Livre convertApi(Livre livre) {
        com.bibliotheque.api.service.model.Livre livreApi = new com.bibliotheque.api.service.model.Livre();

        livreApi.setId(livre.getId());
        livreApi.setNom(livre.getNom());
        livreApi.setAnnee(livre.getAnnee());
        livreApi.setAuteur(livre.getAuteur());
        //livreApi.genre(livre.getGenre());
        livreApi.setImgUrl(livre.getImgUrl());
        livreApi.setQuantite(livre.getQuantite());
        livreApi.setResume(livre.getResume());
        return livreApi;
    }

    public List<com.bibliotheque.api.service.model.Livre> convertListApi(List<Livre> livres) {
        List<com.bibliotheque.api.service.model.Livre> listApi = new ArrayList<>();

        for (int i = 0; i < livres.size(); i++) {
            listApi.add(convertApi(livres.get(i)));
        }

        return listApi;
    }*/
}
