import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {ItemService} from '../item.service';
import {Item} from '../../shared/item';
import {ItemFactory} from '../../shared/Item-factory';

@Component({
  selector: 'av-generate-all-articles-new',
  templateUrl: './generate-all-articles-new.component.html',
  styleUrls: ['./generate-all-articles-new.component.scss']
})
export class GenerateAllArticlesNewComponent implements OnInit {

  public error!: HttpErrorResponse;
  public response!: HttpResponse<Item>;

  constructor(private http: HttpClient, private is: ItemService) { }

  ngOnInit(): void {
  }

  generateItems(): void {
    this.is.createAllItems();
  }
}
