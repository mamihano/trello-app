import { Injectable } from '@angular/core';
import { TaskColumn } from '../models/task-column';
import { environment } from 'src/environment/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TaskColumnService {
  private apiServerUrl = environment.apiUrl + "/taskColumns";
  constructor(private http: HttpClient) {}

  getTaskColumns(): Observable<TaskColumn[]> {
    return this.http.get<TaskColumn[]>(this.apiServerUrl);
  }
}
