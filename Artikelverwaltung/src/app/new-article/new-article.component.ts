import { Component, OnInit } from '@angular/core';
import {Form, FormArray, FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Item} from '../../shared/item';
import {ItemFactory} from '../../shared/Item-factory';
import {ActivatedRoute, Router} from '@angular/router';
import {ItemService} from '../item.service';
import {HttpErrorResponse} from '@angular/common/http';
import {Subscription} from 'rxjs';
import {ItemValidators} from '../../shared/ItemValidators';
import {CR} from '@angular/compiler/src/i18n/serializers/xml_helper';

@Component({
  selector: 'av-new-article',
  templateUrl: './new-article.component.html',
  styleUrls: ['./new-article.component.scss']
})
export class NewArticleComponent implements OnInit {

  public registerForm!: FormGroup;
  public id!: string;
  public error!: HttpErrorResponse;
  public item = ItemFactory.empty();
  public Idexists: boolean | Subscription | HttpErrorResponse = false;

  constructor(private fb: FormBuilder, private route: ActivatedRoute, private is: ItemService, private router: Router) { }

  // TODO disable ID when not new
  // TODO async validator for ID
  public bool = true;

  ngOnInit(): void {
    this.bool = true;

    this.route.params.subscribe(
      params => {
        this.id = params.id;


        if (this.id === 'new') {


          const imageGroup = this.fb.group({
            url: ['', Validators.required],
            title: ['']
          });

          this.registerForm = this.fb.group({
            id: [this.item.id, [Validators.required, ItemValidators.id]],
            description: [this.item.description, Validators.required],
            number: [this.item.number, [Validators.min(0), ItemValidators.decimalNum]],
            einverkaufspreise: this.fb.group({
              purchasingPrice: [this.item.purchasingPrice, Validators.min(0)],
              retailPrice: [this.item.retailPrice, Validators.min(0)]
            }, {validators: ItemValidators.prices}),
            launchDate: [this.item.launchDate],
            images: this.fb.array([imageGroup])
          });

        } else {

          this.is.getItem(this.id).subscribe(
            value1 => {
              this.item = value1;
              const imageGroup = this.fb.group({
                url: [this.item.images[0].url, Validators.required],
                title: [this.item.images[0].title]
              });

              this.registerForm = this.fb.group({
                id: [this.item.id],
                description: [this.item.description, Validators.required],
                number: [this.item.number, [Validators.min(0), ItemValidators.decimalNum]],
                einverkaufspreise: this.fb.group({
                  purchasingPrice: [this.item.purchasingPrice, Validators.min(0)],
                  retailPrice: [this.item.retailPrice, Validators.min(0)]
                }, {validators: ItemValidators.prices}),
                launchDate: [this.item.launchDate],
                images: this.fb.array([imageGroup])
              });


              let index = 0;
              this.item.images.forEach(value => {
                if (index === 0){index++; } else {
                  this.addImageControlValues(this.item.images[index].url.toString(), this.item.images[index].title);
                }
              });

            }
          );

        }
      }
    );

  }

  get images(): FormArray {
    return this.registerForm.get('images') as FormArray;
  }

  get ItemForm(): Item {
   return this.registerForm.value;
  }

  get einverkaufspreise(): FormGroup {
    return this.registerForm.get('einverkaufspreise') as FormGroup;
  }

  get number(): FormControl {
    return this.registerForm.get('number') as FormControl;
  }

  addImageControl(): void {
    const imageGroup = this.fb.group({
      url: [''],
      title: ['']
    });
    this.images.push(imageGroup);
  }

  addImageControlValues(url: string, title: string): void {
    const imageGroup = this.fb.group({
      url: [url],
      title: [title]
    });
    this.images.push(imageGroup);
  }

  removeImageControl(i: number): void {
    this.images.removeAt(i);
  }


  itemAendern(): void {
    const CreatedItem = ItemFactory.empty();

    CreatedItem.id = this.ItemForm.id;
    CreatedItem.description = this.ItemForm.description;
    CreatedItem.number = this.ItemForm.number;
    CreatedItem.purchasingPrice = this.einverkaufspreise.value.purchasingPrice;
    CreatedItem.retailPrice = this.einverkaufspreise.value.retailPrice;
    CreatedItem.launchDate = this.ItemForm.launchDate;
    CreatedItem.images = this.ItemForm.images;

    this.is.updateItem(CreatedItem).subscribe(
      value => {this.router.navigate(['/']); },
      error1 => this.error = error1
    );
  }

  itemLoeschen(): void {
    this.is.deleteItem(this.ItemForm.id).subscribe(
      value => {this.router.navigate(['/']); },
      error => this.error = error
    );
  }

  createItem(): void {

    const CreatedItem = ItemFactory.empty();

    CreatedItem.id = this.ItemForm.id;
    CreatedItem.description = this.ItemForm.description;
    CreatedItem.number = this.ItemForm.number;
    CreatedItem.purchasingPrice = this.einverkaufspreise.value.purchasingPrice;
    CreatedItem.retailPrice = this.einverkaufspreise.value.retailPrice;
    CreatedItem.launchDate = this.ItemForm.launchDate;
    CreatedItem.images = this.ItemForm.images;

    this.is.createItem(CreatedItem).subscribe(
      value => {
        this.router.navigate(['/']);
      },
      error1 => this.error = error1
    );
  }

  checkIdExists(id: string): void {
    this.is.checkIdExists(id).then(
     value => this.Idexists = value
    );
  }
}
