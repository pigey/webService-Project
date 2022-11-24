package com.grupp17.webserviceproject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long>{

    List<Card> findAllCards(Long id);


}
