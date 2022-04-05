export class User {
  constructor(
    public username?: string,
    public password?: string
  ) {}

  static emptySepp(): User {
    return new User('sepp@hintner.com');
  }
}
