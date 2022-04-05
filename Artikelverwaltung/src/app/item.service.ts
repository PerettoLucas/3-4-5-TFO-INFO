import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {forkJoin, Observable, Subscription} from 'rxjs';
import {Item} from '../shared/item';
import {switchMap} from 'rxjs/operators';
import {ItemFactory} from '../shared/Item-factory';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class ItemService {

  private URL = 'http://localhost:3000';

  constructor(private http: HttpClient, private router: Router) { }

  getAllItems(): Observable<Item[]> {
    return this.http.get<Item[]>(`${this.URL}/api/items`);
  }

  getItem(id: string): Observable<Item> {
    id = id.toUpperCase();
    return this.http.get<Item>(`${this.URL}/api/items/${id}`);
  }

  async checkIdExists(id: string): Promise<boolean> {

    let idItem = '';
    await this.http.get<Item>(`${this.URL}/api/items/${id}`).toPromise().then(
      value => idItem = value.id
    ).catch(reason => false);

    return idItem === id ? true : false;

  }

  createItem(item: Item): Observable<HttpResponse<any>> {
    return this.http.post<Item>(`${this.URL}/api/items`, item , {observe: 'response'});
  }

  updateItem(item: Item): Observable<HttpResponse<any>> {
    return this.http.put<Item>(`${this.URL}/api/items/${item.id}`, item,  {observe: 'response'});
  }

  deleteItem(id: string): Observable<HttpResponse<any>> {
    return this.http.delete<Item>(`${this.URL}/api/items/${id}`, {observe: 'response'});
  }

  deleteAllItems(): Observable<any> {
    return this.getAllItems()
      .pipe(
        switchMap(
          items => {
            if (items != null && items.length > 0) {
              return forkJoin(items.map(item => this.deleteItem(item.id)));
            } else {
              return new Observable(obs => {
                obs.next(null);
                obs.complete();
              });
            }
          }
        )
      );
  }


  createAllItems(): HttpResponse<Item> | HttpErrorResponse {

    const respon: HttpResponse<Item> = new HttpResponse<Item>();

    this.deleteAllItems().subscribe(
      value => {
        ItemFactory.items().forEach(
          value1 => this.http.post<Item>(`${this.URL}/api/items`, value1, {observe: 'response'}).subscribe(
            value2 => {
              this.router.navigate(['/']);
              return value2;
            },
            error => error
          )
        );
      },
      error => error
    );

    return respon;

  }

}
