import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProcedureService } from '../../services/procedure.service';
import { Procedure } from '../../models/procedure';

@Component({
  selector: 'procedure-table',
  templateUrl: './procedure-table.component.html',
  styleUrls: ['./procedure-table.component.scss'],
})
export class ProcedureTableComponent implements OnInit {
  procedures?: Procedure[];

  constructor(
    private router: Router,
    private service: ProcedureService
  ) {
  }
  ngOnInit(): void {
    this.getData();
  }

  goToProcedureForm() {
    this.router.navigate(['form']);
  }

  getData(): void {
    this.service.getAll().subscribe(procedureList => {
        this.procedures = procedureList;
      },
      error => {
        console.error('Error fetching procedures:', error);
      }
    );
  }

}
