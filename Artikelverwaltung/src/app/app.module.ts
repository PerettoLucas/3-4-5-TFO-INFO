import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule} from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatTabsModule} from '@angular/material/tabs';
import { ArticleListComponent } from './article-list/article-list.component';
import { NewArticleComponent } from './new-article/new-article.component';
import {MatTableModule} from '@angular/material/table';
import {MatFormFieldModule} from '@angular/material/form-field';
import {ReactiveFormsModule} from '@angular/forms';

import {registerLocaleData} from '@angular/common';
import localeDe from '@angular/common/locales/de';
import {LOCALE_ID} from '@angular/core';
registerLocaleData(localeDe);

import {MatInputModule} from '@angular/material/input';
import { DeleteAllArticlesComponent } from './delete-all-articles/delete-all-articles.component';
import { GenerateAllArticlesNewComponent } from './generate-all-articles-new/generate-all-articles-new.component';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {ErrorStateMatcher, ShowOnDirtyErrorStateMatcher} from '@angular/material/core';


@NgModule({
  declarations: [
    AppComponent,
    ArticleListComponent,
    NewArticleComponent,
    DeleteAllArticlesComponent,
    GenerateAllArticlesNewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatTabsModule,
    MatTableModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule
  ],
  providers: [
    {
      provide: ErrorStateMatcher,
      useClass: ShowOnDirtyErrorStateMatcher
    },
    {provide: LOCALE_ID, useValue: 'de'}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
