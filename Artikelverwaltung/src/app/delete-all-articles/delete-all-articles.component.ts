import { Component, OnInit } from '@angular/core';
import {ItemService} from '../item.service';
import {HttpClient, HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {Router} from '@angular/router';

@Component({
  selector: 'av-delete-all-articles',
  templateUrl: './delete-all-articles.component.html',
  styleUrls: ['./delete-all-articles.component.scss']
})
export class DeleteAllArticlesComponent implements OnInit {

  public error!: HttpErrorResponse;
  public response!: HttpResponse<any>;

  constructor(private is: ItemService, private router: Router) { }

  ngOnInit(): void {
  }

  deleteAllArticles(): void {
    this.is.deleteAllItems().subscribe(
      value => {
        this.response = value;
        this.router.navigate(['/']);
      },
      error1 => this.error = error1
    );

  }
}
