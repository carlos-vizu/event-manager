import { NgModule } from '@angular/core';
import { MatListModule } from '@angular/material/list';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';

@NgModule({
  exports: [
    MatListModule,
    MatPaginatorModule,
    MatButtonModule,
    MatCardModule,
    MatIconModule
  ]
})
export class MaterialModule {}
