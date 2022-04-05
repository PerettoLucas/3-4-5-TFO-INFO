import { Measurement } from './measurement';

export class Station {
  // Auf statische Variablen bzw. Methoden kann nur über den Klassennamen
  // zugegriffen werden und nicht über den Variablennamen. Auch in der Klasse
  // selbst erfolgt der zugriff nur über den Klassennamen
  // Der Zugriff auf die nachfolgenden Assoziativen Arrays kann über
  // description.dd oder description[dd] erfolgen
  public static readonly descriptions = {
    dd: 'Windrichtung',
    ff: 'Mittlere Windgeschwindigkeit',
    bb: 'Windböe',
    hs: 'Schneehöhe',
    n: 'Niederschlagssumme seit Mitternacht',
    p: 'Luftdruck',
    q: 'Durchfluss',
    rh: 'Relative Luftfeuchtigkeit',
    t: 'Lufttemperatur',
    w: 'Wasserstand',
    sd: 'Sonnenscheindauer',
    gs: 'Globalstrahlung',
    wt: 'Wassertemperatur'
  };
  public static readonly units = {
    dd: null,
    ff: 'km/h',
    bb: 'km/h',
    hs: 'cm',
    n: 'mm',
    p: 'hPa',
    q: 'm³/s',
    rh: '%',
    t: '°C',
    w: 'cm',
    sd: 'h',
    gs: 'W/m²',
    wt: '°C'
  };

  constructor(
    public altitude: number,
    public code: string,
    public id: number,
    public lastUpdated: Date,
    public latitude: number,
    public longitude: number,
    public name: string,
    public measurements: Array<Measurement>
  ) {  }
}
