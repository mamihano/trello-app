package com.test.trello.service;

import com.test.trello.exceptions.ObjectNotFoundException;
import com.test.trello.model.Board;
import com.test.trello.model.Card;
import com.test.trello.model.TaskColumn;
import com.test.trello.repo.BoardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
    @Autowired
    private final BoardRepo boardRepo;

    public BoardService(BoardRepo boardRepo) {
        this.boardRepo = boardRepo;
    }

    public Board createBoard(Board board) { return boardRepo.save(board); }

    public List<Board> getAllBoards() { return boardRepo.findAll(); }

    public Board updateBoard(Board board) {
        return boardRepo.save(board);
    }

    public void deleteBoard(Long id) { boardRepo.deleteById(id); }

    public Board getBoardByBoardName(String name) {
        return boardRepo.findByBoardName(name)
                .orElseThrow(() -> new ObjectNotFoundException("Board with name: " + name + " doesn't exist!"));
    }

    public List<TaskColumn> getAllTaskColumns(Long id) {
        Board board = boardRepo.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Board with id: " + id.toString() + " not found"));
        return board.getTaskColumns();
    }

    public List<Card> getAllCards(Long boardId, Long taskColumnId) {
        Board board = boardRepo.findById(boardId)
                .orElseThrow(() -> new ObjectNotFoundException("Board with id: " + boardId.toString() + " not found"));
        List<Card> cards = new ArrayList<>();
        for (TaskColumn taskColumn:board.getTaskColumns()) {
            if (taskColumn.getColumnId().equals(taskColumnId)) {
                cards.addAll(taskColumn.getCards());
            }
        }
        return cards;
    }
}
