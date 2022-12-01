package com.grupp17.webserviceproject.service;

import com.grupp17.webserviceproject.Card;
import com.grupp17.webserviceproject.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service

public class CardServiceimpl implements CardService {
    private final CardRepository cardRepository;
    @Autowired
    public CardServiceimpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public ResponseEntity<Optional<Card>> getCardId(long cardid) {
        try{
            return ResponseEntity.ok(this.cardRepository.findById(cardid));
        }
        catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Card>> showCards() {
        try {
            return ResponseEntity.ok(this.cardRepository.findAll());
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Override
    public ResponseEntity <List<Card>> orderByAge (){
        try{
            return ResponseEntity.ok(this.cardRepository.orderByAge());
        }
        catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    public ResponseEntity<Card> createCard(@RequestBody final Card card){
        try {
            Card _card = cardRepository.save(card);
            return new ResponseEntity<>(_card, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Override
    public ResponseEntity<List<Card>>orderByLastName(){
        try {
            return ResponseEntity.ok(this.cardRepository.orderByLastName());
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Override
    public ResponseEntity<List<Card>>orderByFirstName(){
        try {
            return ResponseEntity.ok(this.cardRepository.orderByFirstName());
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
