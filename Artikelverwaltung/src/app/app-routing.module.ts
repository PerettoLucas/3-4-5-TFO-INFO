import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ArticleListComponent} from './article-list/article-list.component';
import {NewArticleComponent} from './new-article/new-article.component';
import {DeleteAllArticlesComponent} from './delete-all-articles/delete-all-articles.component';
import {GenerateAllArticlesNewComponent} from './generate-all-articles-new/generate-all-articles-new.component';

const routes: Routes = [
  {path: '', component: ArticleListComponent, pathMatch: 'full'},
  {path: 'ArticleList', component: ArticleListComponent},
  {path: 'NewArticle/:id', component: NewArticleComponent},
  {path: 'DeleteAllArticles', component: DeleteAllArticlesComponent},
  {path: 'GenerateAllArticlesNewComponent', component: GenerateAllArticlesNewComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
