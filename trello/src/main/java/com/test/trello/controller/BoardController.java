package com.test.trello.controller;

import com.test.trello.model.Board;
import com.test.trello.model.Card;
import com.test.trello.model.TaskColumn;
import com.test.trello.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping()
    public ResponseEntity<List<Board>> getAllBoards() {
        List<Board> boards = boardService.getAllBoards();
        return new ResponseEntity<>(boards, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Board> getBoardByName(@PathVariable("name") String name) {
        Board board = boardService.getBoardByBoardName(name);
        return new ResponseEntity<>(board, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Board> createNewBoard(@RequestBody Board board) {
        Board newBoard = boardService.createBoard(board);
        return new ResponseEntity<>(newBoard,HttpStatus.CREATED);
    }

    @PutMapping("/{boardId}")
    public ResponseEntity<?> updateBoard(@PathVariable("boardId") Long id, @RequestBody Board board) {
        if (!board.getBoardId().equals(id)) {
            return new ResponseEntity<>("Path variable boardId and board id do not match", HttpStatus.BAD_REQUEST);
        }
        Board updatedBoard = boardService.updateBoard(board);
        if (updatedBoard != null) {
            return new ResponseEntity<>(updatedBoard, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Unable to update board", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<?> deleteBoard(@PathVariable("boardId") Long id) {
        boardService.deleteBoard(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{boardId}/taskColumns")
    public ResponseEntity<List<TaskColumn>> getAllTaskColumnsFromBoard(@PathVariable("boardId") Long id) {
        List<TaskColumn> taskColumns = boardService.getAllTaskColumns(id);
        return new ResponseEntity<>(taskColumns, HttpStatus.OK);
    }

    @GetMapping("/{boardId}/taskColumns/{taskColumnId}")
    public ResponseEntity<List<Card>> getAllTaskColumnsFromBoard(@PathVariable("boardId") Long boardId, @PathVariable("taskColumnId") Long taskColumnId) {
        List<Card> cards = boardService.getAllCards(boardId, taskColumnId);
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }
}
