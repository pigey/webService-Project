package com.grupp17.webserviceproject.service;

import com.grupp17.webserviceproject.Card;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface CardService {
    ResponseEntity<List<Card>> showCards();
    ResponseEntity <Optional<Card>>getCardId(long cardid);

    ResponseEntity <List<Card>> orderByAge();
}
