import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {StationValley} from '../../shared/station-valley';
import {WeatherService} from '../../shared/weather-service';

@Component({
  selector: 'ws-station-detail',
  templateUrl: './station-detail.component.html',
  styleUrls: ['./station-detail.component.scss']
})
export class StationDetailComponent implements OnInit {

  public stations!: Array<StationValley>;
  public foundStation!: StationValley;
  public sortOrder = 'name';
  public code = '';

  constructor(private route: ActivatedRoute, private ws: WeatherService) { }

  ngOnInit(): void {
    this.route.params.subscribe(
      params => {
        this.sortOrder = params.sortOrder;
        this.code = params.code;


        this.ws.getAll(this.sortOrder).subscribe(stations => {
          this.stations = stations;
          this.foundStation = this.ws.get(this.code);
        });
      });
  }

}
