package com.test.trello.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.trello.enums.State;
import jakarta.persistence.*;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long cardId;
    private String cardName;
    private String cardDescription;
    private State cardState;
    private String taskHandler;
    @ManyToOne
    @JoinColumn(name = "columnId")
    @JsonIgnore
    private TaskColumn taskColumn;

    public Card() {
    }

    public Card(String cardName, String cardDescription, String taskHandler, TaskColumn taskColumn) {
        this.cardName = cardName;
        this.cardDescription = cardDescription;
        this.cardState = (taskColumn != null) ? taskColumn.getColumnState() : State.TO_DO;
        this.taskHandler = taskHandler;
        this.taskColumn = taskColumn;
    }

    public Long getCardId() {
        return cardId;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardDescription() {
        return cardDescription;
    }

    public void setCardDescription(String cardDescription) {
        this.cardDescription = cardDescription;
    }

    public State getCardState() {
        return cardState;
    }

    public void setCardState() {
        this.cardState = (this.taskColumn != null) ? this.taskColumn.getColumnState() : State.TO_DO;
    }

    public String getTaskHandler() {
        return taskHandler;
    }

    public void setTaskHandler(String taskHandler) {
        this.taskHandler = taskHandler;
    }

    public TaskColumn getTaskColumn() {
        return taskColumn;
    }

    public void setTaskColumn(TaskColumn taskColumn) {
        this.taskColumn = taskColumn;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardName='" + cardName + '\'' +
                ", cardDescription='" + cardDescription + '\'' +
                ", cardState=" + cardState +
                ", taskHandler='" + taskHandler + '\'' +
                ", taskColumn=" + taskColumn +
                '}';
    }
}
