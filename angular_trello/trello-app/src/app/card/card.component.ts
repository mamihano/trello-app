import { Component, EventEmitter, Input, Output } from '@angular/core';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {MatButtonModule} from '@angular/material/button';
import {MatDividerModule} from '@angular/material/divider';
import {MatCardModule} from '@angular/material/card';
import { Card } from '../models/card';
import { CardService } from '../services/card.service';
import { State } from '../enums/state';
import { FormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css'],
  standalone: true,
  imports: [MatCardModule, MatDividerModule, MatButtonModule, MatProgressBarModule, FormsModule, NgIf]
})

export class CardComponent {

  @Input() card: Card | undefined;
  @Output() cardDeleted: EventEmitter<Card> = new EventEmitter<Card>();
  @Output() cardEdited: EventEmitter<Card> = new EventEmitter<Card>();
  
  showEditForm: boolean = false;
  taskName!: string;
  description!: string;
  taskHandler!: string;
  cardState!: State;

  constructor(private cardService: CardService) { }

  fillInitialValues(card:Card){
    this.taskName = card.cardName;
    this.description = card.cardDescription;
    this.taskHandler = card.taskHandler;
    this.cardState = card.cardState;
  }

  toggleEditForm(card: Card): void {
    this.showEditForm = !this.showEditForm;
    if (this.showEditForm) {
      this.fillInitialValues(card);
    }
  }

  cancelForm(): void {
    this.showEditForm = false;
  }

  editCard(card: Card): void {

    card.cardName = this.taskName;
    card.cardDescription = this.description;
    card.taskHandler = this.taskHandler;
    card.cardState = this.cardState;
    this.cardService.editCard(card).subscribe(
      (response: Card) => {
        this.cardEdited.emit(response);
        console.log('Card updated successfully:', response);
      },
      (error) => {
        console.error('Error updating card:', error);
      }
    );
    this.showEditForm = false;
  }

  deleteCard(card: Card): void {
    if (card.cardId) {
      this.cardService.deleteCard(card.cardId).subscribe(
        (response: void) => {
          this.cardDeleted.emit(card);
          console.log('Card successfully deleted:', response);
        },
        (error) => {
          console.error('Error deleting card:', error);
        }
      );
    } else {
      console.error('Card is undefined.');
    }
  }
}