import { CommonModule } from '@angular/common';
import { Component, input } from '@angular/core';

@Component({
  selector: 'app-change-history',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './change-history.component.html',
  styles: ``
})
export class ChangeHistoryComponent {

  list = input.required<any[]>()
}
