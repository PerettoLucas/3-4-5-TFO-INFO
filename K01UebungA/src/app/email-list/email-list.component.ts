import { Component, Input, OnInit } from '@angular/core';
import { Person } from 'shared/person';

@Component({
  selector: 'ua-email-list',
  templateUrl: './email-list.component.html',
  styleUrls: ['./email-list.component.scss']
})
export class EmailListComponent implements OnInit {

  // tslint:disable-next-line: ban-types
  @Input() emailadressen!: Array<String>;

  constructor() { }

  ngOnInit(): void {
  }

}
