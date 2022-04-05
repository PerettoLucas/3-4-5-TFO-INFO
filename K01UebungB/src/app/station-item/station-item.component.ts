
import { Component, Input, OnInit } from '@angular/core';
import { StationValley } from 'shared/station-valley';

@Component({
  selector: 'ub-station-item',
  templateUrl: './station-item.component.html',
  styleUrls: ['./station-item.component.scss']
})
export class StationItemComponent implements OnInit {

  @Input() station!: StationValley;

  constructor() { }

  ngOnInit(): void {
  }

}
