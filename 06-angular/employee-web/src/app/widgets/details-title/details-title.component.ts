import { Component, input } from '@angular/core';

@Component({
  selector: 'app-details-title',
  templateUrl: './details-title.component.html',
  styles: ``
})
export class DetailsTitleComponent {

  icon = input.required<string>()
  code = input.required<string>()
  name = input.required<string>()
  description = input.required<string>()

}
