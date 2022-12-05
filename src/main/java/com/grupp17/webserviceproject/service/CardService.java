package com.grupp17.webserviceproject.service;

import com.grupp17.webserviceproject.Card;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

public interface CardService {
    ResponseEntity<List<Card>> showCards();
    ResponseEntity <Optional<Card>>getCardId(long cardid);

    ResponseEntity <List<Card>> orderByAge();

    ResponseEntity<Card> createCard(@RequestBody Card card);

    ResponseEntity<List<Card>>orderByLastName();
    ResponseEntity<List<Card>>orderByFirstName();
    void deleteCard(@PathVariable("cardId") Long cardId);
    ResponseEntity<Card> updateCard(@PathVariable Long cardId, @RequestBody final Card card);
    public ModelAndView index ();
    public ModelAndView addNewCardView ();
    public ModelAndView showCardSortedByAge ();
    String addNewCard(Card card);

    ResponseEntity<List<Card>>isPersonOfAge(int cardAge);

    ResponseEntity<?> getRandomQuote();

}
