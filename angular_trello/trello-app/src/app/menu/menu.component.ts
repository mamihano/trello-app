import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { State } from '../enums/state';
import { TaskColumnService } from '../services/task-column.service';
import { TaskColumn } from '../models/task-column';
import { FormsModule } from '@angular/forms';
import { NgIf, NgFor } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css'],
  standalone: true,
  imports: [MatButtonModule, MatDialogModule, FormsModule, NgIf, NgFor]
})
export class MenuComponent {

  columns: TaskColumn[] = [];
  showCreateColumnForm: boolean = false;
  showFilterColumnForm: boolean = false;
  columnName!: string;
  columnState!: State;

  constructor(private columnService: TaskColumnService) {}

  cancelForm(): void {
    this.columnName = '';
    this.columnState = State.TO_DO;
    this.showCreateColumnForm = false;
    this.showFilterColumnForm = false;
  }

  createColumn() {
    const newColumn: TaskColumn = {
      columnName: this.columnName,
      columnState: this.columnState
      //TODO: assign to board
    };

    this.columnService.createColumn(newColumn).subscribe(
      (response: TaskColumn) => {
        this.columns.push(response);
        console.log('Column created successfully:', response);
      },
      (error) => {
        console.error('Error creating column:', error);
      }
    );

    this.columnName = '';
    this.columnState = State.TO_DO;
    this.showCreateColumnForm = false;
  }

  filterColumn(state: State): void {
    //TODO fix filter method
    this.columnService.filterByState(state).subscribe(
      (response: TaskColumn[]) => {
        this.columns = response;
        console.log(response);    
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
    this.showFilterColumnForm = false;
  }
}