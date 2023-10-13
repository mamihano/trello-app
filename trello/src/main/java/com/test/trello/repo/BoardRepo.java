package com.test.trello.repo;

import com.test.trello.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepo extends JpaRepository<Board, Long> {
    Optional<Board> findByBoardName(String name);
}
