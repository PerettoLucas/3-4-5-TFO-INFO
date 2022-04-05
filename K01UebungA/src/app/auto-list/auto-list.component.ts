import { Person } from 'shared/person';
import { Car } from './../../../shared/car';
import { Component, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'ua-auto-list',
  templateUrl: './auto-list.component.html',
  styleUrls: ['./auto-list.component.scss']
})
export class AutoListComponent implements OnInit {

  @Input() person!: Person;

  registrationyear = 0;

  constructor() { }

  ngOnInit(): void {
  }

}
