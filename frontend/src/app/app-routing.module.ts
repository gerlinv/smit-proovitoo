import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProcedureTableComponent } from './procedure/components/procedure-table/procedure-table.component';
import { ProcedureFormComponent } from './procedure/components/procedure-form/procedure-form.component';

const routes: Routes = [
  { path: '', component: ProcedureTableComponent },
  { path: 'form', component: ProcedureFormComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
