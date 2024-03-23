import { Component, input } from '@angular/core';
import { WidgetsModule } from '../../../../widgets/widgets.module';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-position-info',
  standalone: true,
  imports: [WidgetsModule, CommonModule],
  templateUrl: './position-info.component.html',
  styles: ``
})
export class PositionInfoComponent {
  data = input.required<any>()
}
