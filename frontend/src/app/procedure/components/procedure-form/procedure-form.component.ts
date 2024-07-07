import { Component } from '@angular/core';
import { ProcedureService } from '../../services/procedure.service';
import { Router } from '@angular/router';
import { ProcedureForm } from '../../models/procedureForm';
import { Modal } from 'bootstrap';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'procedure-form',
  templateUrl: './procedure-form.component.html',
  styleUrls: ['./procedure-form.component.scss'],
})
export class ProcedureFormComponent {
  procedure: ProcedureForm = new ProcedureForm('', '', '', '');
  modalTitle: string = '';
  modalMessage: string = '';
  procedureForm = new FormGroup({
    name: new FormControl('', [
      Validators.required
    ]),
    email: new FormControl('', [
      Validators.required,
      Validators.email
    ]),
    identityCode: new FormControl('', [
      Validators.required,
      Validators.minLength(11), 
      Validators.maxLength(11),
      Validators.pattern(/^[0-9]+$/)
    ]),
    reason: new FormControl('', [Validators.required])
  });

  constructor(private service: ProcedureService, private router: Router) {}

  goBack() {
    this.router.navigate(['/']);
  }

  openModal(title: string, message: string) {
    this.modalTitle = title;
    this.modalMessage = message;
    let modal = document.getElementById('modal');
    if (modal) {
        var newModal = new Modal(modal);
        newModal.show();
    }
  }

  onSubmit(): void {
    this.service.createProcedure(this.procedure)
      .subscribe(
        procedure => {
          if (procedure) {
            this.openModal("Salvestamine õnnestus", "Menetlus on edukalt salvestatud ja e-kiri on saadetud teele!");
          } else {
            this.openModal("Salvestamine ebaõnnestus", "Menetluse salvestamine ebaõnnestus!");
          }
        },
        error => {
          console.error('Error saving procedure:', error);
          this.openModal("Salvestamine ebaõnnestus", "Menetluse salvestamine ebaõnnestus!");
        }
      );
  }

}
