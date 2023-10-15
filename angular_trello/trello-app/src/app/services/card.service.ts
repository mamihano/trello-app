import { Injectable } from '@angular/core';
import { Card } from '../models/card';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { environment } from 'src/environment/environment';

@Injectable({
  providedIn: 'root'
})
export class CardService {
  private apiServerUrl = environment.apiUrl + "/cards";
  constructor(private http: HttpClient) {}

  getCards(): Observable<Card[]> {
    return this.http.get<Card[]>(this.apiServerUrl);
  }

  createCard(card: Card): Observable<Card> {
    return this.http.post<Card>(this.apiServerUrl, card);
  }

  deleteCard(cardId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/${cardId}`);
  }

  editCard(card: Card): Observable<Card> {
    return this.http.put<Card>(`${this.apiServerUrl}/${card.cardId}`, card);
  }
}