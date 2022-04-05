import {Component, Inject, OnInit} from '@angular/core';
import {DbServiceService} from '../db-service.service';
import {Theme} from '../../shared/theme';
import {MatDialog} from '@angular/material/dialog';
import {ThemeDialogComponent} from '../theme-dialog/theme-dialog.component';

@Component({
  selector: 'app-themes',
  templateUrl: './themes.component.html',
  styleUrls: ['./themes.component.scss']
})
export class ThemesComponent implements OnInit {

  public themes!: Array<Theme>;

  constructor(private dbservice: DbServiceService, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.dbservice.getThemesByDescription().then(value => this.themes = value).catch(reason => console.log(reason));
  }

  openDialog(newWindow: boolean, theme?: Theme): void {
    this.dialog.open(ThemeDialogComponent, {
      data: {
        newWindow: newWindow as boolean,
        theme: theme as Theme
      }
    });
  }

  addTheme(): void {

  }
}

