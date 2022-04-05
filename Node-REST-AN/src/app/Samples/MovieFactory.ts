import {Movie} from './Movie';

export class MovieFactory {

  constructor() {
  }

  public empty(): Movie {
    return new Movie('', new Date(), false, -1, '');
  }

}
