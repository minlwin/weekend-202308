import { Component, input } from '@angular/core';

@Component({
  selector: 'app-page-title',
  templateUrl: './page-title.component.html',
  styles: ``
})
export class PageTitleComponent {

  icon = input.required<string>()
  title = input.required<string>()
}
