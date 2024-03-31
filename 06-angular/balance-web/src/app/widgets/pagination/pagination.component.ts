import { Component, input } from '@angular/core';
import { Pager } from '../../model/pager';

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styles: ``
})
export class PaginationComponent {

  pager = input<Pager | undefined>()
}
