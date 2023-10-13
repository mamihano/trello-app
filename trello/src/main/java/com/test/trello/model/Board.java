package com.test.trello.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long boardId;
    private String boardName;
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TaskColumn> taskColumns = new ArrayList<>();

    public Board() {
    }

    public Board(String boardName, List<TaskColumn> taskColumns) {
        this.boardName = boardName;
        this.taskColumns = taskColumns;
    }

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public List<TaskColumn> getTaskColumns() {
        return taskColumns;
    }

    public void setTaskColumns(List<TaskColumn> taskColumns) {
        this.taskColumns = taskColumns;
    }

    @Override
    public String toString() {
        return "Board{" +
                "boardName='" + boardName + '\'' +
                ", taskColumns=" + taskColumns +
                '}';
    }
}
