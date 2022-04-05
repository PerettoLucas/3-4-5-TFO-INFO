import {Component, Input, OnInit} from '@angular/core';
import {StationValley} from '../../shared/station-valley';

@Component({
  selector: 'ws-station-item',
  templateUrl: './station-item.component.html',
  styleUrls: ['./station-item.component.scss']
})
export class StationItemComponent implements OnInit {

  @Input() foundStation!: StationValley;

  constructor() { }

  ngOnInit(): void {
  }

}
