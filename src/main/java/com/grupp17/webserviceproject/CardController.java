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
    public ModelAndView orderByAge(){
        return cardServiceimpl.showCardSortedByAge();
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
        return cardServiceimpl.updateCardFirstName(cardId, card);
    }

    @GetMapping("/cards/create")
    public ModelAndView addNewCardView () {
        return cardServiceimpl.addNewCardView();
    }

    @PostMapping("/cards/create")
    public String addNewCard(Card card) {
        return cardServiceimpl.addNewCard(card);
    }

    @GetMapping("/cards/age/{cardAge}")
    public ResponseEntity<List<Card>>isPersonOfAge(@PathVariable int cardAge){
        return cardServiceimpl.isPersonOfAge(cardAge);
    }

}