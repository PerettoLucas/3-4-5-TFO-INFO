import {ItemService} from '../app/item.service';
import {FormArray, FormControl, FormGroup} from '@angular/forms';
import {valueReferenceToExpression} from '@angular/compiler-cli/src/ngtsc/annotations/src/util';


export class ItemValidators {

  constructor(private is: ItemService) {}

  static prices(fg: FormGroup): { [error: string]: any } | null {


    if (fg.value.retailPrice < fg.value.purchasingPrice) {
      return  { prices: { valid: false }};
    } else {
      return null;
    }
  }

  static decimalNum(fc: FormControl): { [error: string]: any} | null {
    const numPatter = new RegExp('^[0-9]*$');

    if (numPatter.test(fc.value)) {
      return null;

    } else {
      if (fc.value < 0 ){
        return null;
      }else {

        return {decimalNum: {valid: false}};
      }
    }
  }

  static imageURLSame(fa: FormArray): {[error: string]: any} | null {

    // let i = 0;
    if (fa) {
      alert('sers');
    }

    return null;
  }

  static id(fc: FormControl): { [error: string]: any} | null {
    const idPatternIT = new RegExp('^IT[/d0-9]{6}$');
    const idPatternDE = new RegExp('^DE[/d0-9]{8}$');

    if (idPatternIT.test(fc.value) || idPatternDE.test(fc.value)) {
      return null;
    }else {
      return {id: {valid: false}};
    }
  }

  // @ts-ignore
  static async(fc: FormControl, is: ItemService): { [error: string]: any } | null {

    is.checkIdExists(fc.value).then(
      value => {
        if (value) {
          return {async: {valid: false}};
        } else {
          return null;
        }
      }
    ).catch();


  }


}
