import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Movie } from '../Samples/Movie';
import {Mock} from 'protractor/built/driverProviders';

@Injectable({
  providedIn: 'root'
})
export class BackendService {

  private url = 'http://localhost:8080/movie';

  constructor(private http: HttpClient) { }

  async getAll(sort = 'asc'): Promise<Array<Movie>> {
    return this.http.get<Array<Movie>>(`${this.url}/published?sort=${sort}`).toPromise();
  }

}
