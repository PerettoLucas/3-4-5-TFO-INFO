import { StationValley } from './../../../shared/station-valley';
import { WeatherService } from './../../../shared/weather-service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'ub-station-list',
  templateUrl: './station-list.component.html',
  styleUrls: ['./station-list.component.scss']
})
export class StationListComponent implements OnInit {


  public stations!: Array<StationValley>;
  public stationcount!: number;


  constructor(private ws: WeatherService) { }



  ngOnInit(): void {
    this.ws.getAll().subscribe(res => {
      this.stations = res;
      // nach namen sortieren
      this.stations.sort((s1, s2) => s1.name.localeCompare(s2.name));
      // Es werden nur jene stationen angezeit die mit S beginnen
      // this.stations = this.stations.filter(s => s.name.startsWith(this.stationNameEvent));
    });

  }

  onEventCecked(stationName: string): void{
     this.ws.getAll().subscribe(res => {
      this.stations = res;
      // nach namen sortieren
      this.stations.sort((s1, s2) => s1.name.localeCompare(s2.name));
      // Es werden nur jene stationen angezeit die mit S beginnen
      this.stations = this.stations.filter(s => s.name.startsWith(stationName.toLocaleUpperCase().charAt(0)));

      this.stationcount = this.stations.filter(s => s.name.startsWith(stationName.toLocaleUpperCase().charAt(0))).length;

    });
  }
}
