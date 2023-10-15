package com.test.trello.controller;

import com.test.trello.enums.State;
import com.test.trello.model.Card;
import com.test.trello.model.TaskColumn;
import com.test.trello.service.TaskColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/taskColumns")
public class TaskColumnController {
    private final TaskColumnService taskColumnService;

    @Autowired
    public TaskColumnController(TaskColumnService taskColumnService) {
        this.taskColumnService = taskColumnService;
    }

    @GetMapping()
    public ResponseEntity<List<TaskColumn>> getAllTaskColumns() {
        List<TaskColumn> taskColumns = taskColumnService.getAllTaskColumns();
        return new ResponseEntity<>(taskColumns, HttpStatus.OK);
    }

    @GetMapping("/{id}/cards")
    public ResponseEntity<List<Card>> getAllCardsFromTaskColumn(@PathVariable("id") Long id) {
        List<Card> cards = taskColumnService.getAllCards(id);
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    @GetMapping("/{state}")
    public ResponseEntity<List<TaskColumn>> getTaskColumnsByState(@PathVariable("state") State state) {
        List<TaskColumn> taskColumns = taskColumnService.getTaskColumnByState(state);
        return new ResponseEntity<>(taskColumns, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<TaskColumn> createNewTaskColumn(@RequestBody TaskColumn taskColumn) {
        TaskColumn newTaskColumn = taskColumnService.createTaskColumn(taskColumn);
        return new ResponseEntity<>(newTaskColumn, HttpStatus.CREATED);
    }

    @PutMapping("/{taskColumnId}")
    public ResponseEntity<?> updateTaskColumn(@PathVariable("taskColumnId") Long taskColumnId, @RequestBody TaskColumn taskColumn) {
        if (!taskColumn.getColumnId().equals(taskColumnId)) {
            return new ResponseEntity<>("Path variable taskColumnId and taskColumn id do not match", HttpStatus.BAD_REQUEST);
        }
        TaskColumn updatedTaskColumn = taskColumnService.updateTaskColumn(taskColumn);
        if (updatedTaskColumn != null) {
            return new ResponseEntity<>(updatedTaskColumn, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Unable to update task column", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/board/{boardId}")
    public ResponseEntity<TaskColumn> addTaskColumnToBoard(@PathVariable("boardId") Long boardId, @RequestParam Long taskColumnId) {
        TaskColumn updatedTaskColumn = taskColumnService.addTaskColumnToBoard(taskColumnId, boardId);
        return new ResponseEntity<>(updatedTaskColumn, HttpStatus.OK);
    }

    @DeleteMapping("/{taskColumnId}")
    public ResponseEntity<?> deleteTaskColumn(@PathVariable("taskColumnId") Long taskColumnId) {
        taskColumnService.deleteTaskColumn(taskColumnId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
