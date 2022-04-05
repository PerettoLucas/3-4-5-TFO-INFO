export class Movie {
  title: string;
  year: Date;
  published: boolean;
  owner: number;
  fullname: string;

  constructor(title: string, year: Date, published: boolean, owner: number, fullname: string) {
    this.title = title;
    this.year = year;
    this.published = published;
    this.owner = owner;
    this.fullname = fullname;
  }


}
