import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {DbServiceService} from '../db-service.service';
import {Note} from '../../shared/note';
import {not} from 'rxjs/internal-compatibility';

@Component({
  selector: 'app-notes',
  templateUrl: './notes.component.html',
  styleUrls: ['./notes.component.scss']
})
export class NotesComponent implements OnInit {

  public notes!: Array<Note> ;

  constructor(private activatedRoute: ActivatedRoute, private dbservice: DbServiceService, public router: Router) { }

  ngOnInit(): void {

    this.router.navigate(['/notes/title']);

    this.notes = new Array<Note>();

    this.activatedRoute.params.subscribe( params => {
      if (params.sortOrder === 'title') {

        this.dbservice.getNotesByTitle().then(value => {
          this.notes = value as Array<Note>;
        }).catch();

      } else if (params.sortOrder === 'themes') {

        this.dbservice.getNotesByTheme().then(value => {
          this.notes = value as Array<Note>;
        }).catch();

      } else if (params.sortOrder === 'modDate') {

        this.dbservice.getNotesByModificationDate().then(value => {
          this.notes = value as Array<Note>;
        }).catch();

      }
    });

  }

  addNote(): void {
    this.router.navigate(['/note/new']);
  }
}
