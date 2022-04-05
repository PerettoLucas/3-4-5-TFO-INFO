import { Component, OnInit } from '@angular/core';
import {ItemService} from '../item.service';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {Item} from '../../shared/item';

@Component({
  selector: 'av-article-list',
  templateUrl: './article-list.component.html',
  styleUrls: ['./article-list.component.scss']
})
export class ArticleListComponent implements OnInit {

  public items!: Array<Item>;
  public error!: HttpErrorResponse;
  displayedColumns: string[] = ['bild', 'id', 'beschreibung', 'anzahl', 'einkaufspreis', 'verkaufspreis', 'einführngsdatum'];

  constructor(private is: ItemService) {
  }

  ngOnInit(): void {

    this.is.getAllItems().subscribe(
      value => this.items = value,
      error => this.error = error
    );


  }

}
