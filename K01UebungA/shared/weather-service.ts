import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Injectable } from '@angular/core';

import { map } from 'rxjs/operators';

import { StationValley } from './station-valley';
import { StationFactory } from './station-factory';

const API = 'http://daten.buergernetz.bz.it/services/weather/station?categoryId=1&lang=de&format=json';

@Injectable()
export class WeatherService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<StationValley[]> {
    return this.http.get<any>(API)
      // Webservice liefert ein Json-Objekt mit dem Namen rows zurück in dem das Array
      // gespeichert ist
      .pipe(
        map(response => response.rows),
        map(rawStations => rawStations.map(rawStation => StationFactory.fromObject(rawStation))));
  }

}
