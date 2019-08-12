package com.bibliotheque.api.business;

import com.bibliotheque.api.consumer.LivreRepository;
import com.bibliotheque.api.model.Livre;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class LivreManagement extends JpaCrudManager<Livre, LivreRepository> {
    public LivreManagement(LivreRepository repository) {
        super(repository);
    }
}
