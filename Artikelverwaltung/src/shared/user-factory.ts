import { User } from './user';

export class UserFactory {
  static empty(): User {
    return new User('', '', { firstname: '', lastname: '' }, ['', '', '']);
  }
}
