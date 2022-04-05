import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {NotesComponent} from './notes/notes.component';
import {ThemeComponent} from './theme/theme.component';
import {NoteComponent} from './note/note.component';
import {ThemesComponent} from './themes/themes.component';

const routes: Routes = [
  {path: '', component: NotesComponent},
  {path: 'notes/:sortOrder', component: NotesComponent},
  {path: 'note/:id', component: NoteComponent},
  {path: 'themes', component: ThemesComponent},
  {path: 'theme', component: ThemeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
