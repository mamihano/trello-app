import { Injectable } from '@angular/core';
import { Board } from '../models/board';
import { environment } from 'src/environment/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BoardService {

  private apiServerUrl = environment.apiUrl + "/boards";
  constructor(private http: HttpClient) {}

  public getBoards() : Observable<Board[]> {
    return this.http.get<Board[]>(this.apiServerUrl);
  }
}