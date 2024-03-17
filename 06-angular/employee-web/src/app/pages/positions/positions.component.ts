import { Component, signal } from '@angular/core';
import { WidgetsModule } from '../../widgets/widgets.module';

@Component({
  selector: 'app-positions',
  standalone: true,
  imports: [WidgetsModule],
  templateUrl: './positions.component.html',
  styles: ``
})
export class PositionsComponent {

  list = signal<any[]>([])
}
