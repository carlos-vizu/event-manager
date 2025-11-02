import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListComponent } from './list/list.component';
import { CreateComponent } from './create/create.component';
import { EditComponent } from './edit/edit.component';
import { DetailComponent } from './detail/detail.component';

const routes: Routes = [
  { path: 'events', component: ListComponent },
  { path: 'events/new', component: CreateComponent },
  { path: 'events/:id/edit', component: EditComponent },
  { path: 'events/:id', component: DetailComponent },
  { path: '', redirectTo: '/events', pathMatch: 'full' },
  { path: '**', redirectTo: '/events' }
];
