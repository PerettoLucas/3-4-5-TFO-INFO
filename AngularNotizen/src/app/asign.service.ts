import { Injectable } from '@angular/core';
import {Theme} from '../shared/theme';
import {DbServiceService} from './db-service.service';

@Injectable({
  providedIn: 'root'
})
export class AsignService {

  private setTheme!: boolean;

  public theme!: Theme;
  private themes!: Array<Theme>;

  constructor(private dbservice: DbServiceService) { }

  async checkThemeExists(theme: string): Promise<boolean> {

    await this.dbservice.getThemesByDescription().then(value => this.themes = value as Array<Theme>).catch();

    for (const t of this.themes) {
      if (t.description === theme) {
        return true;
      }
    }

    return false;
  }

}


