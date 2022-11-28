package com.grupp17.webserviceproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CardController {
    private final CardRepository cardRepository;
    @Autowired
    public CardController(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }
    @PostMapping("/cards")
    public ResponseEntity<Card> createCard(@RequestBody final Card card){
        try {
            Card _card = cardRepository.save(card);

            return new ResponseEntity<>(_card, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
