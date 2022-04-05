import { Theme } from './theme';
import { User } from './user';
import { v4 as uuid } from 'uuid';

export class Note {
  constructor(
    public id?: string,
    public title?: string,
    public text?: string,
    public creationDate?: number,
    public modificationDate?: number | null,
    public theme?: Theme,
    public user?: User,
    public read?: boolean
  ) {}

  static empty(): Note {
    const ret = new Note();
    ret.id = uuid();
    return ret;
  }
}
