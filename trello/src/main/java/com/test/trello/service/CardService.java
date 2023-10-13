package com.test.trello.service;

import com.test.trello.enums.State;
import com.test.trello.exceptions.ObjectNotFoundException;
import com.test.trello.model.Board;
import com.test.trello.model.Card;
import com.test.trello.model.TaskColumn;
import com.test.trello.repo.CardRepo;
import com.test.trello.repo.TaskColumnRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    private final CardRepo cardRepo;
    private final TaskColumnRepo taskColumnRepo;

    @Autowired
    public CardService(CardRepo cardRepo, TaskColumnRepo taskColumnRepo) {
        this.cardRepo = cardRepo;
        this.taskColumnRepo = taskColumnRepo;
    }

    public Card createNewCard (Card card) {
        return cardRepo.save(card);
    }

    public Card updateCard (Card card) {
        return cardRepo.save(card);
    }

    public List<Card> getAllCards () {
        return cardRepo.findAll();
    }

    public Card getCardByCardName (String name) {
        return cardRepo.findByCardName(name)
                .orElseThrow(() -> new ObjectNotFoundException("Card with name: " + name + " doesn't exist!"));
    }

    public List<Card> getCardsByHandler (String taskHandler) {
        return cardRepo.findByTaskHandler(taskHandler)
                .orElseThrow(() -> new ObjectNotFoundException("Card with handler: " + taskHandler + " doesn't exist!"));
    }

    public List<Card> getCardsByState (State state) {
        return cardRepo.findByCardState(state)
                .orElseThrow(() -> new ObjectNotFoundException("Card with state: " + state.toString() + " doesn't exist!"));
    }

    public void deleteCard (Long id) {
        cardRepo.deleteById(id);
    }

    public Card addCardToTaskColumn(Long cardId, Long taskColumnId) {
        Card card = cardRepo.findById(cardId)
                .orElseThrow(() -> new ObjectNotFoundException("Card with Id: " + cardId.toString() + " doesn't exist!"));
        TaskColumn taskColumn = taskColumnRepo.findById(taskColumnId)
                .orElseThrow(() -> new ObjectNotFoundException("Task column with Id: " + taskColumnId.toString() + " doesn't exist!"));
                card.setTaskColumn(taskColumn);
                card.setCardState();
        return cardRepo.save(card);
    }
}
