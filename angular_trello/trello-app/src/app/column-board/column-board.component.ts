import { Component, OnInit } from '@angular/core';
import { TaskColumnService } from '../services/task-column.service';
import { TaskColumn } from '../models/task-column';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-column-board',
  templateUrl: './column-board.component.html',
  styleUrls: ['./column-board.component.css'],
  providers: [TaskColumnService]
})
export class ColumnBoardComponent implements OnInit {

  taskColumns: TaskColumn[] = [];

  constructor (private taskColumnService: TaskColumnService) {}
  
  ngOnInit(): void {
    this.getTaskColumns();
  }
  
  public getTaskColumns(): void {
    this.taskColumnService.getTaskColumns().subscribe(
      (response: TaskColumn[]) => {
        this.taskColumns = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
}

