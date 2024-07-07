import { Routes } from '@angular/router';
import { ProcedureTableComponent } from './procedure/components/procedure-table/procedure-table.component';
import { ProcedureFormComponent } from './procedure/components/procedure-form/procedure-form.component';

export const routes: Routes = [
  { path: '', component: ProcedureTableComponent },
  { path: 'form', component: ProcedureFormComponent }
];
