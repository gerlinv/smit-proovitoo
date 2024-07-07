import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Procedure } from '../models/procedure';
import { ProcedureForm } from '../models/procedureForm';
import { environment } from '../../../environment';

@Injectable({
  providedIn: "root"
})
export class ProcedureService {

  private readonly procedureApiUrl = environment.procedureApiUrl

  constructor(private http: HttpClient) {}

  getAll(): Observable<Procedure[]> {
    return this.http.get<Procedure[]>(this.procedureApiUrl);
  }

  createProcedure(procedure: ProcedureForm): Observable<Procedure> {
    return this.http.post<Procedure>(this.procedureApiUrl, procedure);
  }

}
