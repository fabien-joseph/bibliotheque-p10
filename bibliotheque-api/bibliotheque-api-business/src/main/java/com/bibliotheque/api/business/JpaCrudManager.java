package com.bibliotheque.api.business;

import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Optional;

public class JpaCrudManager<T, C extends JpaRepository<T, Long>> {
    C repository;

    public JpaCrudManager (C repository) {
        this.repository = repository;
    }

    public Optional<T> findById(Long id) {
        return repository.findById(id);
    }

    public void save(T object) {
        repository.save(object);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public void delete(T object) {
        repository.delete(object);
    }
}
