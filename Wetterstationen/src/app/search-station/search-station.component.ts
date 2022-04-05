import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {StationValley} from '../../shared/station-valley';
import {WeatherService} from '../../shared/weather-service';
import {debounceTime, distinctUntilChanged, map, startWith, switchMap} from 'rxjs/operators';
import {FormControl} from '@angular/forms';
import {Observable} from 'rxjs';
import {ActivatedRoute, Router} from '@angular/router';
import {MatOption} from '@angular/material/core';

@Component({
  selector: 'ws-search-station',
  templateUrl: './search-station.component.html',
  styleUrls: ['./search-station.component.scss']
})
export class SearchStationComponent implements OnInit {

  myControl = new FormControl();
  filteredOptions!: Observable<string[]>;
  stationNames!: Array<string>;
  stations!: StationValley[];
  sortOrder!: string;
  stationCode!: string;

  constructor(private route: ActivatedRoute, private ws: WeatherService, private router: Router) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.sortOrder = params.sortOrder;
      this.ws.getAll(this.sortOrder).subscribe(stations => {
        this.stations = stations;
        this.stationNames = this.stations.map(st => st.name);

        this.filteredOptions = this.myControl.valueChanges
          .pipe(
            startWith(''),
            map(value => this._filter(value))
          );
      });
    });
  }

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();
    return this.stationNames.filter(option => option.toLocaleLowerCase().includes(filterValue));
  }

  reRoute(matOption: MatOption): void {
    const matOptionStationCode = this.ws.getStation(matOption.value).code;
    this.router.navigate([`/station/${this.sortOrder}/${matOptionStationCode}`]);
  }
}
