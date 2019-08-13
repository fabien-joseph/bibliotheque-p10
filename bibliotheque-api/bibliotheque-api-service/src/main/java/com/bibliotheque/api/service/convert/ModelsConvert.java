package com.bibliotheque.api.service.convert;

import com.bibliotheque.api.service.model.Livre;

import java.util.ArrayList;
import java.util.List;

public class ModelsConvert {
    public Livre convertLivreApi(com.bibliotheque.api.model.Livre livre) {
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

    public List<com.bibliotheque.api.service.model.Livre> convertListLivreApi(List<com.bibliotheque.api.model.Livre> livres) {
        List<com.bibliotheque.api.service.model.Livre> listApi = new ArrayList<>();

        for (int i = 0; i < livres.size(); i++) {
            listApi.add(convertLivreApi(livres.get(i)));
        }

        return listApi;
    }
}
