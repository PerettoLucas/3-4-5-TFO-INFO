import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {NewComponent} from './new/new.component';
import {ListComponent} from './list/list.component';

const routes: Routes = [
  {path: '', component: ListComponent},
  {path: 'new', component: NewComponent},
  {path: 'list/:sort', component: ListComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
