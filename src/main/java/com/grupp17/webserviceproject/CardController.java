package com.grupp17.webserviceproject;

import com.grupp17.webserviceproject.service.CardServiceimpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
<<<<<<< HEAD
      return cardServiceimpl.orderByFirstName();
=======
        return cardServiceimpl.orderByFirstName();
>>>>>>> adb9c5024655349fd2ec76cbd2501098e3e55a5d
    }

    @RequestMapping("/testa")
    public ModelAndView index () {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("testa");
        modelAndView.addObject("allCards", cardServiceimpl.orderByFirstName());
        return modelAndView;
    }

    @DeleteMapping("/cards/{cardId}")
    public void deleteCard(@PathVariable("cardId") Long cardId) {
        try {
            cardRepository.deleteById(cardId);
            System.out.println(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/cards/{cardId}")
    public ResponseEntity<Card> updateCardFirstName(@PathVariable Long cardId, @RequestBody final Card card){
        try{
            Optional<Card> cardOptional = cardRepository.findById(cardId);
            Card cardEntity = cardOptional.get();

            cardEntity.setFirstName(card.getFirstName());
            cardEntity.setLastName(card.getLastName());
            cardEntity.setAge(card.getAge());
            cardEntity.setDescription(card.getDescription());
            cardRepository.save(cardEntity);
            return new ResponseEntity<>(cardEntity, HttpStatus.ACCEPTED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
