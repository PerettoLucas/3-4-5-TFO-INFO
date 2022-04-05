import { Item } from './item';

export class ItemFactory {

  static empty(): Item {
    return new Item('', '', 0, 0.0, 0.0, ItemFactory.getActualDateAsString(), [{url: '', title: ''}]);
  }

  static items(): Item[] {
    return [
      new Item(
        'DE12345678',
        'Umwälzpumpe',
        200,
        25.3,
        77.9,
        '2019-02-01',
        [
          {
            url: 'https://upload.wikimedia.org/wikipedia/commons/0/08/Sindelfingen_Haus_%26_Energie_2018_by-RaBoe_056.jpg',
            title: 'Umwälzpumpe'
          }
        ]
      ),
      new Item(
        'IT123456',
        'Kupplung',
        100,
        5.3,
        7.9,
        '2019-02-02',
        [
          {
            url: 'https://upload.wikimedia.org/wikipedia/commons/c/cf/Clutch-c.jpg',
            title: 'Kraftfahrzeugkupplung'
          },
          {
            url: 'https://upload.wikimedia.org/wikipedia/commons/5/5b/Clutchdisc.jpg',
            title: 'Kupplungsscheibe'
          }
        ]
      )
    ];
  }

  private static getActualDateAsString() {
    const date = new Date();
    const year = date.getFullYear();
    const month = date.getUTCMonth() + 1;
    const day = date.getUTCDate();
    return year + '-' + (month < 10 ? '0' + month : month) + '-' +
      (day < 10 ? '0' + day : day);
  }
}
