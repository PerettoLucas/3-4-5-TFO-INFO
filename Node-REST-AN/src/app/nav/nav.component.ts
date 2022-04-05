import { Component } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import {Router} from '@angular/router';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.scss']
})
export class NavComponent {

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  sort = 'asc';

  constructor(private breakpointObserver: BreakpointObserver, private router: Router) {}

  filmList(): void {

    if (this.sort === 'asc') {
      this.sort = 'desc';
    } else {
      this.sort = 'asc';
    }

    this.router.navigate([`/list/${this.sort}`]);

  }
}
