import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpClientModule, HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {Book} from '../../shared/book';
import {BookStoreServiceService} from '../book-store-service.service';
import {Observable} from 'rxjs';
import {BookFactory} from '../../shared/book-factory';



@Component({
  selector: 'app-book-store-service-test-component',
  templateUrl: './book-store-service-test-component.component.html',
  styleUrls: ['./book-store-service-test-component.component.scss']
})
export class BookStoreServiceTestComponentComponent implements OnInit {

  public books: Array<Book> ;
  public error: HttpErrorResponse;
  public response: HttpResponse<Book>;
  public book: Book;
  public checkString: string;
  private ranBook: Book;

  constructor(private http: HttpClient, private bss: BookStoreServiceService) { }

  ngOnInit(): void {
  }

  resetStore(): void {
    this.clearView();

    this.bss.resetStore().subscribe(
      response => this.response = response,
      error => this.error = error
    );
  }

  getAllBooks(): void{
    this.clearView();

    this.bss.getAllBooks().subscribe(
      value => this.books = value,
      error => this.error = error
    );
  }

  getAllBooksSearchTerm(searchTerm: string): void {
    this.clearView();

    this.bss.getAllBooksSearchTerm(searchTerm).subscribe(
      value => this.books = value,
      error => this.error = error
    );
  }

  createBook(): void {
    this.clearView();

    this.bss.createBook(BookFactory.random()).subscribe(
      value => this.response = value,
      error => this.error = error
    );
  }

  deleteBook(isbn: string): void {
    this.clearView();

    this.bss.deleteBook(isbn).subscribe(
      value => this.response = value,
      error1 => this.error = error1
    );
  }

  getBook(isbn: string): void {
    this.clearView();

    this.bss.getBook(isbn).subscribe(
      response => this.book = response,
      error1 => this.error = error1
    );
  }

  updateBook(isbn: string): void {
    this.clearView();
    this.ranBook = BookFactory.random();
    this.ranBook.isbn = isbn;

    this.bss.updateBook(this.ranBook).subscribe(
      value => this.response = value,
      error1 => this.error = error1
    );
  }

  checkBook(isbn: string): void {
    this.clearView();

    this.bss.checkBook(isbn).subscribe(
      value => {
        this.response = value;
      },
      error1 => this.error = error1
    );
  }

  rateBook(isbn: string, rating: number): void {
    this.clearView();

    this.bss.rateBook(isbn, rating).subscribe(
      value => this.response = value,
      error1 => this.error = error1
    );
  }

  clearView(): void {

    if (this.book != null) {
      this.book = null;
    }

    if (this.books != null) {
      this.books = null;
    }

    if (this.error != null) {
      this.error = null;
    }

    if (this.response != null) {
      this.response = null;
    }

  }

}
