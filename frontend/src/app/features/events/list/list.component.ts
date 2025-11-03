import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { MatListModule } from '@angular/material/list';
import { MatPaginatorModule, PageEvent } from '@angular/material/paginator';
import { EventService } from '../../../core/services/event.service';
import { Event } from '../../../core/models/event.model';

@Component({
  selector: 'app-event-list',
  standalone: true,
  imports: [CommonModule, RouterLink, MatListModule, MatPaginatorModule],
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss'],
})
export class ListComponent {
  events: Event[] = [];
  totalElements = 0;
  pageSize = 10;
  pageIndex = 0;

  constructor(private eventService: EventService) {
    this.loadEvents();
  }

  loadEvents(page: number = 0, size: number = this.pageSize) {
    this.eventService.listEvents(page, size).subscribe((res) => {
      this.events = res.content || res;
      this.totalElements = res.totalElements || this.events.length;
    });
  }

  onPageChange(event: PageEvent) {
    this.pageIndex = event.pageIndex;
    this.pageSize = event.pageSize;
    this.loadEvents(this.pageIndex, this.pageSize);
  }

  deleteEvent(id: number) {
    if (confirm('Are you sure you want to delete this event?')) {
      this.eventService.deleteEvent(id).subscribe(() => {
        this.loadEvents(this.pageIndex, this.pageSize);
      });
    }
  }
}
