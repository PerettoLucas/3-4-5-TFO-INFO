import {Component, Input, OnInit} from '@angular/core';
import {StationValley} from '../../shared/station-valley';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'ws-measurement',
  templateUrl: './measurement.component.html',
  styleUrls: ['./measurement.component.scss']
})
export class MeasurementComponent implements OnInit {

  @Input() foundStation!: StationValley;

  public sortOrder!: string;
  public sortOrderNr!: number;

  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.params.subscribe(
      params => {
        this.sortOrder = params.sortOrder;
        switch (this.sortOrder){
          case 'name':
            this.sortOrderNr = 10;
            break;
          case 'temperature':
            this.sortOrderNr = 0;
            break;
          case 'precipitation':
            this.sortOrderNr = 1;
            break;
          case 'airpressure':
            this.sortOrderNr = 5;
            break;
          default:
            this.sortOrderNr = 0;
            break;
        }
    });

  }

}
