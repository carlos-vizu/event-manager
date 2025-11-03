import { importProvidersFrom } from '@angular/core';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatListModule } from '@angular/material/list';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';

export const materialProviders = importProvidersFrom(
  MatPaginatorModule,
  MatListModule,
  MatButtonModule,
  MatCardModule
);
