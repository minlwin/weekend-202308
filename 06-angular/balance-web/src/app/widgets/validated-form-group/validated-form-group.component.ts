import { Component, input } from '@angular/core';

@Component({
  selector: 'app-validated-form-group',
  templateUrl: './validated-form-group.component.html',
  styles: ``
})
export class ValidatedFormGroupComponent {

  label = input.required<string>()
  valid = input<boolean | undefined>()
}
