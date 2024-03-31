import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { CategoryComponent } from './pages/category/category.component';
import { BalanceListComponent } from './pages/balance-list/balance-list.component';
import { BalanceReportComponent } from './pages/balance-report/balance-report.component';
import { BalanceEditComponent } from './pages/balance-edit/balance-edit.component';
import { BalanceDetailsComponent } from './pages/balance-details/balance-details.component';
import { CategoryEditComponent } from './pages/category-edit/category-edit.component';
import { EmployeeComponent } from './pages/employee/employee.component';
import { EmployeeEditComponent } from './pages/employee-edit/employee-edit.component';

export const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'category', component: CategoryComponent},
  {path: 'category-edit', component: CategoryEditComponent},
  {path: 'employee', component: EmployeeComponent},
  {path: 'employee-edit', component: EmployeeEditComponent},
  {path: 'incomes', component: BalanceListComponent, data: {type: 'Income'}},
  {path: 'expenses', component: BalanceListComponent, data: {type: 'Expense'}},
  {path: 'report', component: BalanceReportComponent},
  {path: 'edit', component: BalanceEditComponent},
  {path: 'details', component: BalanceDetailsComponent},
  {path: '', redirectTo: '/home', pathMatch: 'full'}
];
