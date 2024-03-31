import { Component, input } from '@angular/core';

@Component({
  selector: 'app-nav-link',
  templateUrl: './nav-link.component.html',
  styles: ``
})
export class NavLinkComponent {

  icon = input.required<string>()
  title = input.required<string>()
  path = input.required<string[]>()
  show = input.required<boolean>()

}
