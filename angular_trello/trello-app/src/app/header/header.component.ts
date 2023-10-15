import { Component, OnInit } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { BoardService } from '../services/board.service';
import { Board } from '../models/board';
import { HttpErrorResponse } from '@angular/common/http';
import { NgFor, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
  standalone: true,
  imports: [MatButtonModule, NgFor, NgIf, FormsModule]
})
export class HeaderComponent implements OnInit{
  boards: Board[] = [];
  boardTitle: string = "";
  showCreateForm: boolean = false;
  
  constructor (private boardService: BoardService){}

  ngOnInit(): void {
    this.getBoards();
  }

  getBoards(): void {
    this.boardService.getBoards().subscribe(
      (response: Board[]) => {
        this.boards = response;
        if (this.boards && this.boards.length > 0) {
          this.boardTitle = this.boards[0].boardName;
        }
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  createBoard(): void {
    const newBoard: Board = {
      boardName: this.boardTitle
    };

    this.boardService.createBoard(newBoard).subscribe(
      (response: Board) => {
        this.boards.push(response);
        console.log('Board created successfully:', response);
      },
      (error) => {
        console.error('Error creating board:', error);
      }
    );

    this.boardTitle = '';
    this.showCreateForm = false;
  }

  cancelForm(): void {
    this.boardTitle = '';
    this.showCreateForm = false;
}
}
