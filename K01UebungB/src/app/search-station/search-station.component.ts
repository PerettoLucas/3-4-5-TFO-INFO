import { StationValley } from 'shared/station-valley';
import { WeatherService } from './../../../shared/weather-service';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'ub-search-station',
  templateUrl: './search-station.component.html',
  styleUrls: ['./search-station.component.scss']
})
export class SearchStationComponent implements OnInit {

  @Output() notify: EventEmitter<string> = new EventEmitter<string>();

  @Input() stationcount!: number;

  public stationName = '';


  constructor(private ws: WeatherService) { }

  ngOnInit(): void {
  }


  KeyUp(): void {
    this.notify.emit(this.stationName);
  }

}
