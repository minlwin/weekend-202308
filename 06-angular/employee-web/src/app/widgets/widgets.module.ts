import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CardComponent } from './card/card.component';
import { FormGroupComponent } from './form-group/form-group.component';
import { InputGroupComponent } from './input-group/input-group.component';
import { NoDataComponent } from './no-data/no-data.component';

@NgModule({
  declarations: [
    CardComponent,
    FormGroupComponent,
    InputGroupComponent,
    NoDataComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    CardComponent,
    FormGroupComponent,
    InputGroupComponent,
    NoDataComponent
  ]
})
export class WidgetsModule { }
