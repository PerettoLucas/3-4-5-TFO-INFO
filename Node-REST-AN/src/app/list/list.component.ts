import { Component, OnInit } from '@angular/core';
import {BackendService} from '../Services/backend.service';
import {Movie} from '../Samples/Movie';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent implements OnInit {

  public filmList!: Array<Movie>;


  constructor(private backend: BackendService, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {

    this.activatedRoute.params.subscribe(result => {
        this.filmList = new Array<Movie>();

        this.backend.getAll(result.sort)
          .then(filmList => filmList.forEach(value => {
            this.filmList.push(new Movie(value.title, value.year, value.published, value.owner, value.fullname));
          }))
          .catch(error => console.log(error));

      });



  }


}
