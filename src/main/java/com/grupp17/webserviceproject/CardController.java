package com.grupp17.webserviceproject;

import com.grupp17.webserviceproject.service.CardServiceimpl;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
public class CardController {
    private final CardServiceimpl cardServiceimpl;

    public CardController(CardServiceimpl cardServiceimpl) {
        this.cardServiceimpl = cardServiceimpl;
    }

    @PostMapping("/cards")
    public ResponseEntity<Card> createCard(@RequestBody final Card card){
       return cardServiceimpl.createCard(card);
    }
    @GetMapping("/cards")
    public ResponseEntity<List<Card>> showCards(){
     return cardServiceimpl.showCards();
    }

    @GetMapping("/cards/{cardid}")
    public ResponseEntity<Optional<Card>> showCards(@PathVariable Long cardid){
      return cardServiceimpl.getCardId(cardid);
    }

    @GetMapping("/cards/age")
    public ResponseEntity<List<Card>>orderByAge(){
        return cardServiceimpl.orderByAge();
    }
    @GetMapping("/cards/lastName")
    public ResponseEntity<List<Card>>orderByLastName(){
       return cardServiceimpl.orderByLastName();
    }
    @GetMapping("/cards/firstName")
    public ResponseEntity<List<Card>>orderByFirstName(){
        return cardServiceimpl.orderByFirstName();
    }

    @RequestMapping("/")
    public ModelAndView index () {
        return cardServiceimpl.index();
    }

    @DeleteMapping("/cards/{cardId}")
    public void deleteCard(@PathVariable("cardId") Long cardId) {
        cardServiceimpl.deleteCard(cardId);
    }

    @PutMapping("/cards/{cardId}")
    public ResponseEntity<Card> updateCardFirstName(@PathVariable Long cardId, @RequestBody final Card card){
        return cardServiceimpl.updateCard(cardId, card);
    }

    @GetMapping("/cards/create")
    public ModelAndView addNewCardView () {
        return cardServiceimpl.addNewCardView();
    }

    @PostMapping("/cards/create")
    public String addNewCard(Card card) {
        return cardServiceimpl.addNewCard(card);
    }

    @GetMapping("/cards/age/{cardage}")
    public ResponseEntity<List<Card>>isPersonOfAge(@PathVariable int cardAge){
        return cardServiceimpl.isPersonOfAge(cardAge);
    }

    @PatchMapping("/cards/updateFirstName/{cardId}")
    public ResponseEntity<Card> updateFirstName(@PathVariable("cardId") long cardId, @RequestParam String firstName){
        return cardServiceimpl.updateFirstName(cardId, firstName);
    }

    @PatchMapping("/cards/updateLastName/{cardId}")
    public ResponseEntity<Card> updateLastName(@PathVariable("cardId") long cardId, @RequestParam String lastName){
        return cardServiceimpl.updateLastName(cardId, lastName);
    }

    @PatchMapping("/cards/updateAge/{cardId}")
    public ResponseEntity<Card> updateAge(@PathVariable("cardId") long cardId, @RequestParam int age){
        return cardServiceimpl.updateAge(cardId, age);
    }

    @PatchMapping("/cards/updateDescription/{cardId}")
    public ResponseEntity<Card> updateDescription(@PathVariable("cardId") long cardId, @RequestParam String description){
        return cardServiceimpl.updateDescription(cardId, description);
    }
}