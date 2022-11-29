package com.grupp17.webserviceproject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CardRepository extends JpaRepository<Card, Long>{
    @Query("SELECT s FROM Card s ORDER BY s.age desc")
    List<Card> orderByAge();

    @Query("SELECT s FROM Card s ORDER BY s.lastName asc")
    List<Card> orderByLastName();

    @Query("SELECT s FROM Card s ORDER BY s.firstName asc")
    List<Card> orderByFirstName();
}
