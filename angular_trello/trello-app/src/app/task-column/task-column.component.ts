import { Component, Input, OnInit } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { CardComponent } from '../card/card.component';
import { TaskColumn } from '../models/task-column';
import { MatDialog } from '@angular/material/dialog';
import { CardService } from '../services/card.service';
import { Card } from '../models/card';
import { NgFor, NgIf } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { State } from '../enums/state';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-task-column',
  templateUrl: './task-column.component.html',
  styleUrls: ['./task-column.component.css'],
  standalone: true,
  imports: [MatButtonModule, CardComponent, NgFor, NgIf, FormsModule]
})
export class TaskColumnComponent implements OnInit {
  @Input() column: TaskColumn | undefined;
  cards: Card[] = [];
  showCreateForm = false;
  taskName!: string;
  description!: string;
  taskHandler!: string;
  cardState?: State;

  constructor(public dialog: MatDialog,
  private cardService: CardService) {}

  ngOnInit(): void {
      this.getCards();
    }
  
  getCards(): void {
    this.cardService.getCards().subscribe(
      (response: Card[]) => {
        this.cards = response.filter(card => card.cardState === this.column?.columnState);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  cancelForm(): void {
    this.taskName = '';
    this.description = '';
    this.taskHandler = '';
    this.cardState = undefined;
    this.showCreateForm = false;
}

  createCard(): void {
    const newCard: Card = {
      cardName: this.taskName,
      cardDescription: this.description,
      taskHandler: this.taskHandler,
      cardState: this.column!.columnState
    };

    this.cardService.createCard(newCard).subscribe(
      (response: Card) => {
        this.cards.push(response);
        console.log('Card created successfully:', response);
      },
      (error) => {
        console.error('Error creating card:', error);
      }
    );

    this.taskName = '';
    this.description = '';
    this.taskHandler = '';
    this.cardState = undefined;
    this.showCreateForm = false;
  }

  onCardDeleted(deletedCard: Card): void {
    this.cards = this.cards.filter(card => card.cardId !== deletedCard.cardId);
  }

  onCardEdited(editedCard: Card): void {
    const editedCardIndex = this.cards.findIndex(card => card.cardId === editedCard.cardId);
    if (editedCardIndex !== -1) {
      this.cards[editedCardIndex] = editedCard;
    } else {
      console.error('Edited card not found in the cards array.');
    }
  }

  deleteColumn(): void {
    console.log("TODO");
  }
}