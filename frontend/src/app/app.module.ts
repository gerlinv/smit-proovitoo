import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HttpClientXsrfModule } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ProcedureTableComponent } from './procedure/components/procedure-table/procedure-table.component';
import { ProcedureFormComponent } from './procedure/components/procedure-form/procedure-form.component';
import { ModalComponent } from './common/components/modal/modal.component';

@NgModule({
  declarations: [
    AppComponent,
    ProcedureTableComponent,
    ProcedureFormComponent,
    ModalComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    CommonModule,
    FormsModule,
    HttpClientModule,
    HttpClientXsrfModule,
    NgbModule,
    ReactiveFormsModule
  ],
  providers: [
    provideClientHydration(),
    HttpClientModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
