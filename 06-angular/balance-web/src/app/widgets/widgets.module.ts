import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavLinkComponent } from './nav-link/nav-link.component';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { PageTitleComponent } from './page-title/page-title.component';
import { ListViewComponent } from './list-view/list-view.component';
import { SearchFormGroupComponent } from './search-form-group/search-form-group.component';



@NgModule({
  declarations: [
    NavLinkComponent,
    PageTitleComponent,
    ListViewComponent,
    SearchFormGroupComponent
  ],
  imports: [
    CommonModule,
    RouterLink,
    RouterLinkActive
  ],
  exports: [
    NavLinkComponent,
    PageTitleComponent,
    ListViewComponent,
    SearchFormGroupComponent
  ]
})
export class WidgetsModule { }
