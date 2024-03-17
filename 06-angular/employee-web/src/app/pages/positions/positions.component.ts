import { Component, signal } from '@angular/core';
import { WidgetsModule } from '../../widgets/widgets.module';
import { PositionService } from '../../service/position.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-positions',
  standalone: true,
  imports: [WidgetsModule, RouterLink],
  templateUrl: './positions.component.html',
  styles: ``
})
export class PositionsComponent {

  list = signal<any[]>([])

  form:FormGroup

  constructor(builder:FormBuilder ,private service:PositionService) {
    this.form = builder.group({
      code: "",
      name: ""
    })

    this.search()
  }

  search() {
    this.service.search(this.form.value).subscribe(result => {
      this.list.set(result.payload)
    })
  }
}
