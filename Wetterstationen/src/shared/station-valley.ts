import { Station } from './station';
import { Measurement } from './measurement';

export class StationValley extends Station {
  constructor(
    altitude: number,
    code: string,
    id: number,
    lastUpdated: Date,
    latitude: number,
    longitude: number,
    name: string,
    measurements: Array<Measurement>,
    public dd: string,
    public ff: number,
    public bb: number,
    public n: number,
    public p: number,
    public rh: number,
    public t: number,
    public sd: string,
    public gs: number
  ) {
    super(altitude, code, id, lastUpdated, latitude, longitude, name, measurements);
  }
}
