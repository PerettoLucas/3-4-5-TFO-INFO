import { StationValley } from './../../shared/station-valley';
import { WeatherService } from './../../shared/weather-service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {Observable, of} from 'rxjs';

@Component({
  selector: 'ws-station-list',
  templateUrl: './station-list.component.html',
  styleUrls: ['./station-list.component.scss']
})
export class StationListComponent implements OnInit {

  public stations!: Array<StationValley>;
  public stationsCopy!: Array<StationValley>;
  public sortOrder = 'name';

  constructor(private route: ActivatedRoute, private ws: WeatherService) { }

  ngOnInit(): void {
    this.route.params.subscribe(
      params => {
         this.sortOrder = params.sortOrder;

         this.ws.getAll(this.sortOrder).subscribe(stations => {
           this.stations = stations;
         });
      });

  }

}
