import { Component, input } from '@angular/core';

@Component({
  selector: 'app-search-form-group',
  templateUrl: './search-form-group.component.html',
  styles: ``
})
export class SearchFormGroupComponent {

  label = input.required<string>()
}
