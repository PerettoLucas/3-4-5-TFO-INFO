import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators} from '@angular/forms';
import { Movie } from '../Samples/Movie';
import { MovieFactory } from '../Samples/MovieFactory';

interface PublishedSample {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-new',
  templateUrl: './new.component.html',
  styleUrls: ['./new.component.scss']
})

export class NewComponent implements OnInit {

  public registerForm!: FormGroup;

  constructor(private fb: FormBuilder) { }

  selectedValue!: string;

  published: PublishedSample[] = [
    {value: 'true', viewValue: 'Ja'},
    {value: 'false', viewValue: 'Nein'}
  ];



  ngOnInit(): void {
    const movie = new MovieFactory().empty();

    this.fb.group({
      title: [movie.title, Validators.required],
      year: [movie.year, Validators.required],
      published: [movie.published]
    });

  }

  register(): void {
    const movie = new MovieFactory().empty();
    Object.assign(movie, this.registerForm.value);


    this.registerForm.reset(new MovieFactory().empty());
  }

}
