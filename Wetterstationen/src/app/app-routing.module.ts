import { StationListComponent } from './station-list/station-list.component';
import { HomeComponent } from './home/home.component';
import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {StationDetailComponent} from './station-detail/station-detail.component';
import {SearchStationComponent} from './search-station/search-station.component';

const routes: Routes = [
  { path: ' ', component: StationListComponent, pathMatch: 'full'},
  { path: 'home', component: HomeComponent },
  { path: 'stations/:sortOrder', component: StationListComponent },
  { path: 'station/:sortOrder/:code', component: StationDetailComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
