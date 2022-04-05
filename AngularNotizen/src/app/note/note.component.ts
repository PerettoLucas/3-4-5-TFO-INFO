import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {DbServiceService} from '../db-service.service';
import {placeholdersToParams} from '@angular/compiler/src/render3/view/i18n/util';
import {Note} from '../../shared/note';
import { Location } from '@angular/common';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {v4 as uuid} from 'uuid';
import {User} from '../../shared/user';
import {Theme} from '../../shared/theme';
import {ThemeDialogComponent} from '../theme-dialog/theme-dialog.component';
import {MatDialog} from '@angular/material/dialog';
import {AsignService} from '../asign.service';

@Component({
  selector: 'app-note',
  templateUrl: './note.component.html',
  styleUrls: ['./note.component.scss']
})
export class NoteComponent implements OnInit {

  public note!: Note;
  public newTrue = false;
  public themes!: Array<Theme>;

  public formGroup!: FormGroup;

  constructor(private activatedRoute: ActivatedRoute, private dbService: DbServiceService,
              public location: Location, private fb: FormBuilder, private dialog: MatDialog,
              private asignS: AsignService) { }

  ngOnInit(): void {
    this.note = new Note();

    this.formGroup = new FormGroup({});

    this.dbService.getThemesByDescription().then(value =>  {
      this.themes = value;

    }).catch(reason => console.log(reason));

    this.formGroup = this.fb.group({
        title: [this.note.title, Validators.required],
        theme: [this.note.theme, Validators.required],
        text: [this.note.text, Validators.required]
    });

    this.activatedRoute.params.subscribe(params => {

      if (params.id === 'new') {
        this.newTrue = true;
      }else {

        this.dbService.getNotesByID(params.id).then(value => {
          this.note = value as Note;
          this.formGroup.patchValue(this.note);
          this.formGroup.get('theme')?.patchValue(this.note.theme?.description);
        }).catch();

      }
    });

  }

  delNote(): void {
    this.dbService.deleteNote(this.note.id as string).then(value => this.location.back()).catch();
  }

  async addNote(): Promise<void> {

    Object.assign(this.note, this.formGroup.value);

    this.note.id = uuid();
    this.note.user = new User('sepp@hintner.com', 'sepp');
    await this.dbService.getThemeByDescription(this.formGroup.get('theme')?.value).then(value => this.note.theme = value).catch();

    this.dbService.addNote(this.note).then(value => this.location.back()).catch(reason => console.log(reason));
  }

  submit(): void {

  }

  async updateNote(): Promise<void> {

    Object.assign(this.note, this.formGroup.value);


    this.note.user = new User('sepp@hintner.com', 'sepp');

    await this.dbService.getThemeByDescription(this.formGroup.get('theme')?.value).then(value => this.note.theme = value).catch();

    this.dbService.updateNote(this.note).then(value => value).catch();

    this.location.back();
  }

  openDialog(newWindow: boolean): void {

    const dialogRef = this.dialog.open(ThemeDialogComponent, {
      data: {
        newWindow: newWindow as boolean,
        inNote: true
      }
    });

    dialogRef.afterClosed().subscribe(result => {

      this.dbService.getThemesByDescription().then(value =>  {
        this.themes = value;

      }).catch(reason => console.log(reason));

      this.formGroup.get('theme')?.patchValue(this.asignS.theme?.description);
    });

  }





}
