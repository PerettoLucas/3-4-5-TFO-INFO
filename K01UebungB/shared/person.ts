import { Car } from './car';
import { Address } from './address';
export { Car } from './car';
export { Address } from './address';

export class Person {

  // Optionale Parameter müssen am Ende der Parameterliste stehen
  constructor(
    public name: string,
    public birthdate: Date,
    public height: number,
    public male: boolean,
    public emailaddresses: string[],
    public cars: Array<Car>,
    public address: Address,
    public homepage: string,
    public nickname?: string,
    public imagelink?: string
  ) { }

  get ageInYears(): number {
    return Math.trunc((new Date().valueOf() - this.birthdate.valueOf()) / (365 * 60 * 60 * 24 * 1000));
  }

  carsYoungerThan(registrationyear: number): Array<Car> {
    return this.cars.filter(
       value => value.registrationyear > registrationyear);
  }
}
