import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog} from '@angular/material/dialog';
import {Router} from '@angular/router';
import {Theme} from '../../shared/theme';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {DbServiceService} from '../db-service.service';
import {v4 as uuid} from 'uuid';
import {Location} from '@angular/common';
import {AsignService} from '../asign.service';


class DialogData {

  newWindow!: boolean;
  theme!: Theme;
  inNote!: boolean;

}

@Component({
  selector: 'app-theme-dialog',
  templateUrl: './theme-dialog.component.html',
  styleUrls: ['./theme-dialog.component.scss']
})
export class ThemeDialogComponent implements OnInit {

  public formGroup!: FormGroup;
  public exists = false;
  public color!: string;

  constructor(@Inject(MAT_DIALOG_DATA) public data: DialogData, public router: Router,
              private fb: FormBuilder, private dbservice: DbServiceService, private refDialog: MatDialog,
              private location: Location, private asignS: AsignService)
  {}

  ngOnInit(): void {

    this.color = 'primary';

    this.formGroup = this.fb.group({
      desc: [this.data.theme?.description, [Validators.required]]
    });

  }

  async check(): Promise<void> {
    await this.asignS.checkThemeExists(this.formGroup.value.desc as string).then(value => {

      this.exists = value;

      if (this.exists) {
        this.color = 'warn';
      } else {
        this.color = 'primary';
      }

    }).catch(reason => console.log(reason));

  }


  async addTheme(): Promise<void> {

    const theme = new Theme();
    theme.description = this.formGroup.value.desc;
    theme.id = uuid();

    await this.check();

    if (!this.exists) {

      if (this.data.inNote) {

        this.dbservice.addTheme(theme).then(async value => {

          this.asignS.theme = theme;
          this.refDialog.closeAll();
        }).catch();

      } else {

        this.dbservice.addTheme(theme).then(value => {
          this.redirectTo('/themes');
        }).catch();
      }

    }


  }

  async updateTheme(): Promise<void> {
    const theme = new Theme();
    theme.description = this.formGroup.value.desc;
    theme.id = this.data.theme.id;


    await this.check();

    this.dbservice.updateTheme(theme).then(value => this.redirectTo('/themes')).catch();
  }

  deleteTheme(): void {
    this.dbservice.deleteTheme(this.data.theme).then(value => this.redirectTo('/themes')).catch(reason => alert(reason));
  }

  async redirectTo(uri: string): Promise<void> {
    await this.router.navigateByUrl('/', {skipLocationChange: true}).then(() => this.router.navigate([uri]));
    this.refDialog.closeAll();
  }

}
