import { Component, OnInit } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { BoardService } from '../services/board.service';
import { Board } from '../models/board';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
  standalone: true,
  imports: [MatButtonModule]
})
export class HeaderComponent implements OnInit{
  boards: Board[] = [];
  boardTitle: string = "";
  
  constructor (private boardService: BoardService){}

  ngOnInit(): void {
    this.getBoards();
  }

  public getBoards(): void {
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
}
