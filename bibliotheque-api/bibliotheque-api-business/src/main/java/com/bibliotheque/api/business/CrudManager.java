package com.bibliotheque.api.business;

public interface CrudManager<T, ID extends Number> {
    T get(ID id);
    void save(T t);
    void delete(ID id);
}
