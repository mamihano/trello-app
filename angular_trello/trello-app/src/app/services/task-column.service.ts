import { Injectable } from '@angular/core';
import { TaskColumn } from '../models/task-column';
import { environment } from 'src/environment/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { State } from '../enums/state';

@Injectable({
  providedIn: 'root'
})
export class TaskColumnService {
  private apiServerUrl = environment.apiUrl + "/taskColumns";
  constructor(private http: HttpClient) {}

  getTaskColumns(): Observable<TaskColumn[]> {
    return this.http.get<TaskColumn[]>(this.apiServerUrl);
  }

  createColumn(column: TaskColumn): Observable<TaskColumn> {
    return this.http.post<TaskColumn>(this.apiServerUrl, column);
  }

  filterByState(state: State): Observable<TaskColumn[]> {
    return this.http.get<TaskColumn[]>(this.apiServerUrl + "/" + state);
  }

  deleteColumn(columnId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/${columnId}`);
  }
}
