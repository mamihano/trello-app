package com.test.trello.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.trello.enums.State;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class TaskColumn {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long columnId;
    private String columnName;
    private State columnState;
    @ManyToOne
    @JoinColumn(name = "boardId")
    @JsonIgnore
    private Board board;
    @OneToMany(mappedBy = "taskColumn", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Card> cards = new ArrayList<>();

    public TaskColumn() {}

    public TaskColumn(String columnName, State columnState, Board board, List<Card> cards) {
        this.columnName = columnName;
        this.columnState = columnState;
        this.board = board;
        this.cards = cards;
    }

    public Long getColumnId() {
        return columnId;
    }
    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public State getColumnState() {
        return columnState;
    }

    public void setColumnState(State columnState) {
        this.columnState = columnState;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public String toString() {
        return "TaskColumn{" +
                "columnName='" + columnName + '\'' +
                ", columnState=" + columnState +
                ", board=" + board +
                ", cards=" + cards +
                '}';
    }
}
