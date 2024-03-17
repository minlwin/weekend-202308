import { Routes } from '@angular/router';
import { DepartmentsComponent } from './pages/departments/departments.component';
import { PositionsComponent } from './pages/positions/positions.component';
import { EmployeesComponent } from './pages/employees/employees.component';
import { DepartmentDetailsComponent } from './pages/departments/department-details/department-details.component';
import { DepartmentEditComponent } from './pages/departments/department-edit/department-edit.component';
import { PositionDetailsComponent } from './pages/positions/position-details/position-details.component';
import { PositionEditComponent } from './pages/positions/position-edit/position-edit.component';

export const routes: Routes = [
  {path: 'departments', children: [
    {path: 'list', component: DepartmentsComponent},
    {path: 'details', component: DepartmentDetailsComponent},
    {path: 'edit', component: DepartmentEditComponent},
    {path: '', redirectTo: '/departments/list', pathMatch: 'full'}
  ]},
  {path: 'positions', children: [
    {path: 'list', component: PositionsComponent},
    {path: 'details', component: PositionDetailsComponent},
    {path: 'edit', component: PositionEditComponent},
    {path: '', redirectTo: '/positions/list', pathMatch: 'full'}
  ]},
  {path: 'employees', component: EmployeesComponent},
  {path: '', redirectTo: '/departments', pathMatch: 'full'}
];
