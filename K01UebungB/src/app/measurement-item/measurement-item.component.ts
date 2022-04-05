import { Component, Input, OnInit } from '@angular/core';
import { StationValley } from './../../../shared/station-valley';
import { Measurement } from 'shared/measurement';

@Component({
  selector: 'ub-measurement-item',
  templateUrl: './measurement-item.component.html',
  styleUrls: ['./measurement-item.component.scss']
})
export class MeasurementItemComponent implements OnInit {

  @Input() station!: StationValley;

  public measurement!: Measurement;

  constructor() { }

  ngOnInit(): void {
  }

}
