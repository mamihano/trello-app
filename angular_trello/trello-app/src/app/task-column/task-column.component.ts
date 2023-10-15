import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
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
import { TaskColumnService } from '../services/task-column.service';

@Component({
  selector: 'app-task-column',
  templateUrl: './task-column.component.html',
  styleUrls: ['./task-column.component.css'],
  standalone: true,
  imports: [MatButtonModule, CardComponent, NgFor, NgIf, FormsModule]
})
export class TaskColumnComponent implements OnInit {
  @Input() column: TaskColumn | undefined;
  @Output() columnDeleted: EventEmitter<TaskColumn> = new EventEmitter<TaskColumn>();
  cards: Card[] = [];
  showCreateForm = false;
  taskName!: string;
  description!: string;
  taskHandler!: string;
  cardState?: State;

  constructor(public dialog: MatDialog,
  private cardService: CardService,
  private columnService: TaskColumnService) {}

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

  onCardEdited(): void {
    //TODO: refresh page to show card in correct column
    this.getCards();
  }

  deleteColumn(column: TaskColumn): void {
    if (column.columnId) {
      this.columnService.deleteColumn(column.columnId).subscribe(
        (response: void) => {
          this.columnDeleted.emit(column);
          //TODO: Fix orphan delete
          console.log('Column successfully deleted:', response);
        },
        (error) => {
          console.error('Error deleting column:', error);
        }
      );
    } else {
      console.error('Column is undefined.');
    }
  }
}