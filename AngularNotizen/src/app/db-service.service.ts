import { Injectable } from '@angular/core';
import {Theme} from '../shared/theme';

import Dexie, {PromiseExtended} from 'dexie';
import { v4 as uuid } from 'uuid';
import * as moment from 'moment';
import {Note} from '../shared/note';
import {User} from '../shared/user';
import {not} from 'rxjs/internal-compatibility';

@Injectable({
  providedIn: 'root'
})
export class DbServiceService extends Dexie{

  private notes!: Dexie.Table<Note, string>;
  private themes!: Dexie.Table<Theme, string>;

  constructor() {
    super('notes-db1');
    this.version(1).stores({
      notes:
        'id, title,[theme.description+title],theme.id,[modificationDate+creationDate]',
      themes: 'id, &description',
    });
    this.on('populate', async () => {
      try {
        const t1 = new Theme(uuid(), 'Bananen');
        const t2 = new Theme(uuid(), 'Birnen');
        const t3 = new Theme(uuid(), 'Ananas');
        await this.themes.bulkAdd([t1, t2, t3]);
        const u = new User('sepp@hintner.com', 'sepp');
        const n1 = new Note(
          uuid(),
          'Titel1',
          'Text1',
          moment().valueOf(),
          null,
          t2,
          u,
          false
        );
        const n2 = new Note(
          uuid(),
          'Titel2',
          'Text2',
          moment().valueOf(),
          null,
          t1,
          u,
          false
        );
        await this.addNote(n1);
        await this.addNote(n2);
      } catch (err) {
        console.log(err);
      }
    });
  }
  async getThemesByDescription(): Promise<Array<Theme>> {
    return this.themes.orderBy('description').toArray();
  }

  async getThemeByDescription(description: string): Promise<Theme> {
    const theme = await this.themes
      .where('description')
      .equals(description)
      .first();
    return theme ? theme : Promise.reject('Theme not found');
  }

  async getNotesByID(id: string): Promise<Note | undefined> {
    return this.notes.where('id').equals(id).first();
  }

  async getNotesByTheme(): Promise<Array<Note>> {
    return this.notes.orderBy('theme.description').toArray();
  }

  async getNotesByTitle(): Promise<Array<Note>> {
    return this.notes.orderBy('title').toArray();
  }

  async getNotesByModificationDate(): Promise<Array<Note>> {
    return this.notes.orderBy('modificationDate').toArray();
  }

  async deleteNote(id: string): Promise<void> {
    return this.notes.delete(id);
  }

  async getNotes(description: string): Promise<Array<Note>> {
    return this.notes
      .where('theme.description')
      .equals(description)
      .sortBy('title');
  }

  async addTheme(theme: Theme): Promise<any> {
    return this.themes.add(theme);
  }

  async deleteTheme(theme: Theme): Promise<void> {
    const notes = await this.getNotes( theme.description as string);
    return notes.length
      ? Promise.reject('Theme in use')
      : this.themes.delete(theme.id as string);
  }

  async updateTheme(theme: Theme): Promise<any> {
    await this.themes.update(theme.id as string, theme);
    return this.notes.where('theme.id').equals(theme.id as string).modify({
      'theme.description': theme.description,
      modificationDate: moment().valueOf(),
    });
  }

  async addNote(note: Note): Promise<any>  {
    note.creationDate = moment().valueOf();
    note.modificationDate = 0;
    return this.notes.add(note);
  }

  async updateNote(note: Note): Promise<any> {
    await this.notes.update(note.id as string, note);
    return this.notes.where('id').equals(note.id as string).modify({
      title: note.title,
      text: note.text,
      modificationDate: moment().valueOf()
    });
  }

}
