import { Book } from './book';

export class BookFactory {

  static empty(): Book {
    return new Book('', '', [''], new Date(), '', 0, [{url: '', title: ''}], '');
  }

  static random(): Book {
    const isbn = BookFactory.randomNumber();
    const title = 'Titel ' + BookFactory.randomString();
    const authors: Array<string> = BookFactory.randomStringArray();

    return new Book(isbn, title, authors, new Date(), '', 0, [{url: '', title: ''}], '');
  }

  private static randomString(): string {
    let ret = '';
    let maxSize = 6;
    const charset = 'abcdefghijklmnopqrstuvwxyz';
    while (maxSize--) {
      ret += charset[Math.floor(Math.random() * charset.length)];
    }
    return ret;
  }

  private static randomNumber(): string {
    let ret = '';
    let maxSize = 13;
    const charset = '123456789';
    while (maxSize--) {
      ret += charset[Math.floor(Math.random() * charset.length)];
    }
    return ret;
  }

  private static randomStringArray(): Array<string> {
    let maxSize = 3;
    const ret: Array<string> = new Array(maxSize);
    while (maxSize--) {
      ret[maxSize] = 'Autor ' + BookFactory.randomString();
    }
    return ret;
  }
}
