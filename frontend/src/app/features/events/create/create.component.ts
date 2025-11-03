import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { EventService } from '../../../core/services/event.service';
import { Event } from '../../../core/models/event.model';

@Component({
  selector: 'app-event-create',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.scss'],
})
export class CreateComponent {
  event: Event = { title: '', description: '', eventDate: '', location: '' };
  errorMessage: string | null = null;

  constructor(private eventService: EventService, private router: Router) {}

  save(form: NgForm) {
    if (!form.valid) return;

    if (this.event.eventDate) {
      this.event.eventDate = new Date(this.event.eventDate).toISOString();
    }

    this.eventService.createEvent(this.event).subscribe({
      next: () => {
        this.errorMessage = null;
        if (confirm('Evento salvo com sucesso! Clique OK para voltar à lista de eventos.')) {
          this.router.navigate(['/events']);
        }
      },
      error: (err) => {
        if (err.status === 400 && err.error) {
          this.errorMessage = Object.values(err.error).join(', ');
        } else {
          this.errorMessage = 'Erro inesperado ao criar evento.';
        }
      },
    });
  }
}
