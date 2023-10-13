package com.test.trello.controller;

import com.test.trello.enums.State;
import com.test.trello.model.Card;
import com.test.trello.model.TaskColumn;
import com.test.trello.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController {
    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping()
    public ResponseEntity<List<Card>> getAllCards() {
        List<Card> cards = cardService.getAllCards();
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Card> getCardByCardName(@PathVariable("name") String name) {
        Card card = cardService.getCardByCardName(name);
        return new ResponseEntity<>(card, HttpStatus.OK);
    }

    @GetMapping("/{handler}")
    public ResponseEntity<List<Card>> getCardsByHandler(@PathVariable("handler") String handler) {
        List<Card> cards = cardService.getCardsByHandler(handler);
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    @GetMapping("/{state}")
    public ResponseEntity<List<Card>> getCardsByState(@PathVariable("state") State state) {
        List<Card> cards = cardService.getCardsByState(state);
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Card> createNewCard(@RequestBody Card card) {
        Card newCard = cardService.createNewCard(card);
        return new ResponseEntity<>(newCard, HttpStatus.CREATED);
    }

    @PutMapping("/{cardId}")
    public ResponseEntity<?> updateCard(@PathVariable("cardId") Long cardId, @RequestBody Card card) {
        if (!card.getCardId().equals(cardId)) {
            return new ResponseEntity<>("Path variable cardId and card id do not match", HttpStatus.BAD_REQUEST);
        }
        Card updatedCard = cardService.updateCard(card);
        if (updatedCard != null) {
            return new ResponseEntity<>(updatedCard, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Unable to update card", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/taskColumn/{taskColumnId}")
    public ResponseEntity<Card> addCardToTaskColumn(@PathVariable("taskColumnId") Long taskColumnId, @RequestParam Long cardId) {
        Card updatedCard = cardService.addCardToTaskColumn(cardId, taskColumnId);
        return new ResponseEntity<>(updatedCard, HttpStatus.OK);
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity<?> deleteCard(@PathVariable("cardId") Long cardId) {
        cardService.deleteCard(cardId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
