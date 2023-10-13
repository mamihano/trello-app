package com.test.trello.repo;

import com.test.trello.enums.State;
import com.test.trello.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CardRepo extends JpaRepository<Card, Long> {
    Optional<Card> findByCardName(String name);

    Optional<List<Card>> findByTaskHandler(String name);

    Optional<List<Card>> findByCardState(State state);
}
