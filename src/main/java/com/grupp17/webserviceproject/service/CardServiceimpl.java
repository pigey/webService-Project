package com.grupp17.webserviceproject.service;

import com.grupp17.webserviceproject.Card;
import com.grupp17.webserviceproject.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

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
    @Override
    public void deleteCard(@PathVariable("cardId") Long cardId) {
        try {
            cardRepository.deleteById(cardId);
            System.out.println(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Override
    public ResponseEntity<Card> updateCard(@PathVariable Long cardId, @RequestBody final Card card){
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

    @Override
    public ModelAndView index () {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("testa");
        modelAndView.addObject("allCards", cardRepository.orderByFirstName());
        return modelAndView;
    }
    @Override
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

    @Override
    public ModelAndView showCardSortedByAge () {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("sortedByAge");
        modelAndView.addObject("allCardsByAge", cardRepository.orderByAge());
        return modelAndView;
    }

    @Override
    public String addNewCard(Card card) {
        try {
            cardRepository.save(card);
            return "Success!" + "<form th:action=\"@{/}\">\n" +
                    "  <input  type=\"submit\" value=\"Back\"/>\n" +
                    "</form>";
        } catch (Exception e) {
            return "Oops! Error!" + "<form th:action=\"@{/cards/create}\">\n" +
                    "  <input  type=\"submit\" value=\"Back\"/>\n" +
                    "</form>";
        }
    }

    @Override
    public ResponseEntity<List<Card>>isPersonOfAge(@PathVariable int cardAge){
        try{
            if (cardAge < 18){
                return ResponseEntity.ok(this.cardRepository.isNotByAge());
            }
            else {
                return ResponseEntity.ok(this.cardRepository.isByAge());
            }
            //return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.I_AM_A_TEAPOT);
        }

    }

    @Override
    public ResponseEntity<?> getRandomQuote(){
        try {
            String uri = "https://api.quotable.io/random";
            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(uri, String.class);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
