import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { abcForms } from '../../../../../../../environments/generals';
import { Client } from '../../models/cliente';
import { MatIconModule } from "@angular/material/icon";
import { MatButtonModule } from "@angular/material/button";
import { MatSlideToggleModule } from "@angular/material/slide-toggle";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from "@angular/material/input";
import { MatDialogRef } from "@angular/material/dialog";
import { ReactiveFormsModule } from '@angular/forms'; // Asegúrate de importar ReactiveFormsModule

@Component({
    selector: 'app-client-edit',
    standalone: true,
    imports: [
        MatIconModule,
        MatButtonModule,
        ReactiveFormsModule,  // Asegúrate de que ReactiveFormsModule está importado
        MatSlideToggleModule,
        MatFormFieldModule,
        MatInputModule
    ],
    template: `
        <div class="flex flex-col max-w-240 md:min-w-160 max-h-screen -m-6">
            <!-- Header -->
            <div class="flex flex-0 items-center justify-between h-16 pr-3 sm:pr-5 pl-6 sm:pl-8 bg-primary text-on-primary">
                <div class="text-lg font-medium" [innerHTML]="title"></div>
                <button mat-icon-button (click)="cancelForm()" [tabIndex]="-1">
                    <mat-icon
                        class="text-current"
                        [svgIcon]="'heroicons_outline:x-mark'">
                    </mat-icon>
                </button>
            </div>

            <!-- Compose form -->
            <form class="flex flex-col flex-auto p-6 sm:p-8 overflow-y-auto" [formGroup]="clientForm">
                <mat-form-field>
                    <mat-label>Nombre</mat-label>
                    <input matInput formControlName="nombre" />
                </mat-form-field>
                <mat-form-field>
                    <mat-label>Apellidos</mat-label>
                    <input matInput formControlName="apellido" />
                </mat-form-field>
                <mat-form-field>
                    <mat-label>Correo</mat-label>
                    <input matInput formControlName="email" />
                </mat-form-field>
                <mat-form-field>
                    <mat-label>Telefono</mat-label>
                    <input matInput formControlName="telefono" />
                </mat-form-field>

                <!-- Actions -->
                <div class="flex flex-col sm:flex-row sm:items-center justify-between mt-4 sm:mt-6">
                    <div class="flex space-x-2 items-center mt-4 sm:mt-0 ml-auto">
                        <button mat-stroked-button [color]="'warn'" (click)="cancelForm()">Cancelar</button>
                        <button mat-stroked-button [color]="'primary'" (click)="saveForm()">Guardar</button>
                    </div>
                </div>
            </form>
        </div>
    `
})
export class ClientEditComponent implements OnInit {
    clientForm = new FormGroup({
        nombre: new FormControl('', [Validators.required]),
        apellido: new FormControl('', [Validators.required]),
        email: new FormControl('', [Validators.required, Validators.email]),
        telefono: new FormControl('', [Validators.required]),
        direccion: new FormControl(''), // Puedes agregar este campo si deseas incluirlo
    });

    @Input() title: string = '';
    @Input() client: Client = new Client();
    abcForms: any;

    constructor(
        private _matDialog: MatDialogRef<ClientEditComponent>
    ) {}

    ngOnInit() {
        this.abcForms = abcForms;

        if (this.client) {
            this.clientForm.patchValue({
                nombre: this.client.nombre,
                apellido: this.client.apellido,
                email: this.client.email,
                telefono: this.client.telefono,
                direccion: this.client.direccion || '',  // Si no se pasa una dirección, se pone una cadena vacía
            });
        }
    }

    public saveForm(): void {
        if (this.clientForm.valid) {
            this._matDialog.close(this.clientForm.value); // Envía el valor del formulario
        }
    }

    public cancelForm(): void {
        this._matDialog.close(''); // Cancela la acción y cierra el diálogo
    }
}
