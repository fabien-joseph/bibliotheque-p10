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

    public List<Livre> findLivresFilters(String search) {
        return repository.findLivresByFilters(search);
    }
}
