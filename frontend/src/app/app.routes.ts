import { Routes } from '@angular/router';
import { ListComponent } from './features/events/list/list.component';
import { CreateComponent } from './features/events/create/create.component';
import { EditComponent } from './features/events/edit/edit.component';
import { DetailComponent } from './features/events/detail/detail.component';

export const routes: Routes = [
  { path: 'events', component: ListComponent, title: 'Event List' },
  { path: 'events/new', component: CreateComponent, title: 'Create Event' },
  { path: 'events/:id/edit', component: EditComponent, title: 'Edit Event' },
  { path: 'events/:id', component: DetailComponent, title: 'Event Details' },
  { path: '', redirectTo: 'events', pathMatch: 'full' },
  { path: '**', redirectTo: 'events' }
];
