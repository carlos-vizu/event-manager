import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { EventService } from '../../../core/services/event.service';
import { Event } from '../../../core/models/event.model';

@Component({
  selector: 'app-event-edit',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.scss'],
})
export class EditComponent implements OnInit {
  event: Event = { title: '', description: '', eventDate: '', location: '' };

  constructor(
    private route: ActivatedRoute,
    private eventService: EventService,
    private router: Router
  ) {}

  ngOnInit() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.eventService.getEvent(id).subscribe((res) => (this.event = res));
  }

  save(form: NgForm) {
    if (!form.valid) return;

    const id = Number(this.route.snapshot.paramMap.get('id'));

    if (this.event.eventDate) {
      this.event.eventDate = new Date(this.event.eventDate).toISOString();
    }

    this.eventService.updateEvent(id, this.event).subscribe({
      next: () => {
        if (confirm('Evento atualizado com sucesso! Clique OK para voltar à lista de eventos.')) {
          this.router.navigate(['/events']);
        }
      },
      error: (err) => console.error('Erro ao atualizar evento', err),
    });
  }
}
