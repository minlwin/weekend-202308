import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CardComponent } from './card/card.component';
import { FormGroupComponent } from './form-group/form-group.component';
import { InputGroupComponent } from './input-group/input-group.component';

@NgModule({
  declarations: [
    CardComponent,
    FormGroupComponent,
    InputGroupComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    CardComponent,
    FormGroupComponent,
    InputGroupComponent
  ]
})
export class WidgetsModule { }
