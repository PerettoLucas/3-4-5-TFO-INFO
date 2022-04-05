import { Image } from './image';

export class Item {
  constructor(
    public id: string,
    public description: string,
    public number: number,
    public purchasingPrice: number,
    public retailPrice: number,
    public launchDate: string,
    public images: Array<Image>
  ) { }
}
