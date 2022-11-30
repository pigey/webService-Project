package com.grupp17.webserviceproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

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
    @GetMapping("/cards")
    public ResponseEntity<List<Card>> showCards(){
        try{
            return ResponseEntity.ok(this.cardRepository.findAll());
        }
        catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/cards/{cardid}")
    public ResponseEntity<Optional<Card>> showCards(@PathVariable Long cardid){
        try{
            return ResponseEntity.ok(this.cardRepository.findById(cardid));
        }
        catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cards/age")
    public ResponseEntity<List<Card>>orderByAge(){
        try{
            return ResponseEntity.ok(this.cardRepository.orderByAge());
        }
        catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/cards/lastName")
    public ResponseEntity<List<Card>>orderByLastName(){
        try {
            return ResponseEntity.ok(this.cardRepository.orderByLastName());
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/cards/firstName")
    public ResponseEntity<List<Card>>orderByFirstName(){
        try {
            return ResponseEntity.ok(this.cardRepository.orderByFirstName());
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/testa")
    public ModelAndView index () {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("testa");
        modelAndView.addObject("allCards", cardRepository.orderByFirstName());
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

    @GetMapping("/cards/create")
    public ModelAndView addNewCardView () {
        try{
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("addcard");
            modelAndView.addObject("oneCard", new Card());
            return modelAndView;
        } catch (Exception e) {
            return new ModelAndView().addObject(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/cards/create")
    public String addNewCard(Card card) {

        try {
            cardRepository.save(card);
            return "Success!" + "<form th:action=\"@{/testa}\">\n" +
                    "  <input  type=\"submit\" value=\"Back\"/>\n" +
                    "</form>";
        } catch (Exception e) {
            return "Oops! Error!" + "<form th:action=\"@{/cards/create}\">\n" +
                    "  <input  type=\"submit\" value=\"Back\"/>\n" +
                    "</form>";
        }
    }

}