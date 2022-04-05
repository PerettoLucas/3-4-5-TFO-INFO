import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {Book} from '../shared/book';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {BookFactory} from '../shared/book-factory';

@Injectable({
  providedIn: 'root'
})
export class BookStoreServiceService {

  private URL = 'http://localhost:3000';

  constructor(private http: HttpClient) { }

  resetStore(): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.URL}/books`, { observe: 'response' });
  }

  getAllBooks(): Observable<Book[]> {
    return this.http.get<Book[]>(`${this.URL}/books`);
  }

  getAllBooksSearchTerm(searchTerm: string): Observable<Book[]> {
    return this.http.get<Book[]>(`${this.URL}/books/search/${searchTerm}`);
  }

  createBook(book: Book): Observable<HttpResponse<any>> {
    return this.http.post<Book>(`${this.URL}/book`, book, {observe: 'response'});
  }

  deleteBook(isbn: string): Observable<HttpResponse<any>> {
    return this.http.delete<Book>(`${this.URL}/book/${isbn}`, {observe: 'response'});
  }

  getBook(isbn: string): Observable<Book> {
    return this.http.get<Book>(`${this.URL}/book/${isbn}`);
  }

  updateBook(book: Book): Observable<HttpResponse<any>> {
    return this.http.put(`${this.URL}/book/${book.isbn}`, book, {observe: 'response'});
  }

  checkBook(isbn: string): Observable<HttpResponse<any>> {
    return this.http.get<any>(`${this.URL}/book/${isbn}/check`);
  }

  rateBook(isbn: string, rating: number): Observable<HttpResponse<any>> {
    return this.http.post<Book>(`${this.URL}/book/${isbn}/rate`, { rating: `${rating}`}, {observe: 'response'});
  }

  /*


  *
  * */
}
