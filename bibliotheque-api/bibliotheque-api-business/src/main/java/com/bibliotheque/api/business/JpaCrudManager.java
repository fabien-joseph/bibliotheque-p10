package com.bibliotheque.api.business;

import org.springframework.data.jpa.repository.JpaRepository;

public class JpaCrudManager<T, C extends JpaRepository<T, Long>> {

}
