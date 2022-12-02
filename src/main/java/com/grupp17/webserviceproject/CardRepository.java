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

    @Query("SELECT s FROM Card s WHERE s.age<18")
    List<Card> isNotByAge();

    @Query("SELECT s FROM Card s WHERE s.age>17")
    List<Card> isByAge();

    //en som hämtar alla med samma förnamn (martin)
    //en som hämtar alla av samma efternamn

    // hämta alla över en viss ålder (robin)
}
