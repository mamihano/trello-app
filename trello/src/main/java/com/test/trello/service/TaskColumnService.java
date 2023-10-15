package com.test.trello.service;

import com.test.trello.enums.State;
import com.test.trello.exceptions.ObjectNotFoundException;
import com.test.trello.model.Board;
import com.test.trello.model.Card;
import com.test.trello.model.TaskColumn;
import com.test.trello.repo.BoardRepo;
import com.test.trello.repo.TaskColumnRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskColumnService {
    private final TaskColumnRepo taskColumnRepo;
    private final BoardRepo boardRepo;

    @Autowired
    public TaskColumnService(TaskColumnRepo taskColumnRepo, BoardRepo boardRepo) {
        this.taskColumnRepo = taskColumnRepo;
        this.boardRepo = boardRepo;
    }

    public TaskColumn createTaskColumn (TaskColumn taskColumn) {
        return taskColumnRepo.save(taskColumn);
    }

    public List<TaskColumn> getTaskColumnByState (State state) {
        return taskColumnRepo.findByColumnState(state)
                .orElseThrow(() -> new ObjectNotFoundException("Task column with state: " + state.toString() + " doesn't exist!"));
    }

    public List<TaskColumn> getAllTaskColumns () {
        return taskColumnRepo.findAll();
    }

    public TaskColumn updateTaskColumn (TaskColumn taskColumn) {
        return taskColumnRepo.save(taskColumn);
    }

    public void deleteTaskColumn (Long id) {
        taskColumnRepo.deleteById(id);
    }

    public List<Card> getAllCards(Long id) {
        TaskColumn taskColumn = taskColumnRepo.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Task column with id: " + id + " not found"));
        return taskColumn.getCards();
    }

    public TaskColumn addTaskColumnToBoard(Long taskColumnId, Long boardId) {
        TaskColumn taskColumn = taskColumnRepo.findById(taskColumnId)
                .orElseThrow(() -> new ObjectNotFoundException("Task column with Id: " + taskColumnId + " doesn't exist!"));
        Board board = boardRepo.findById(boardId)
                .orElseThrow(() -> new ObjectNotFoundException("Board with Id: " + boardId + " doesn't exist!"));
        taskColumn.setBoard(board);
        return taskColumnRepo.save(taskColumn);
    }
}
