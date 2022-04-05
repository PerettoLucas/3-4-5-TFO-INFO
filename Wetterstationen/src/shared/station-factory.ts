import { Station } from './station';
import { StationValley } from './station-valley';

export class StationFactory {

  static fromObject(rawStation: any): any {
    switch (rawStation.categoryId) {
      case 1: {
        // StationValley wird angelegt
        const ret: StationValley = new StationValley(
          rawStation.altitude,
          rawStation.code,
          rawStation.id,
          new Date(rawStation.lastUpdated),
          parseFloat(rawStation.latitude.replace(',', '.')),
          parseFloat(rawStation.longitude.replace(',', '.')),
          rawStation.name,
          rawStation.measurements,
          rawStation.dd,
          parseFloat(rawStation.ff),
          rawStation.bb == null ? 0 : parseFloat(rawStation.bb),
          parseInt(rawStation.n, 10),
          parseFloat(rawStation.p),
          parseInt(rawStation.rh, 10),
          rawStation.t == null ? 0 : parseFloat(rawStation.t),
          rawStation.sd,
          parseInt(rawStation.gs, 10));
        return ret;
      }
      case 2: {
        // StationLevel wird angelegt
        break;
      }
      case (3): {
        // StationMountain wird angelegt
      }
    }
  }
}
