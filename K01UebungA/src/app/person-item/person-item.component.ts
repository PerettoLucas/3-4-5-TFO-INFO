import { Component, Input, OnInit } from '@angular/core';
import { Person } from 'shared/person';

@Component({
  selector: 'ua-person-item',
  templateUrl: './person-item.component.html',
  styleUrls: ['./person-item.component.scss']
})
export class PersonItemComponent implements OnInit {

  @Input() person!: Person;

  constructor() { }

  ngOnInit(): void {
  }

}
