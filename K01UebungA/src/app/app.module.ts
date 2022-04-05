import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FlexLayoutModule } from '@angular/flex-layout';
import { PersonListComponent } from './person-list/person-list.component';
import {MatCardModule} from '@angular/material/card';
import {MatListModule} from '@angular/material/list';
import { PersonItemComponent } from './person-item/person-item.component';
import { EmailListComponent } from './email-list/email-list.component';
import {MatIconModule} from '@angular/material/icon';
import { AutoListComponent } from './auto-list/auto-list.component';
import {MatInputModule} from '@angular/material/input';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    AppComponent,
    PersonListComponent,
    PersonItemComponent,
    EmailListComponent,
    AutoListComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FlexLayoutModule,
    MatCardModule,
    MatListModule,
    MatIconModule,
    MatInputModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
