import { v4 as uuid } from 'uuid';

export class Theme {
  constructor(
    public id?: string,
    public description?: string
  ) {}

  static empty(): Theme {
    const ret = new Theme();
    ret.id = uuid();
    return ret;
  }
}
