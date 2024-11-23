import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { CategoryService } from "../../../../../providers/services/setup/category.service";
import { ConfirmDialogService } from "../../../../../shared/confirm-dialog/confirm-dialog.service";
import { CategoryListComponent } from "../components";
import { CategoryNewComponent } from '../components/form/category-new.component';
import { CategoryEditComponent } from '../components/form/category-edit.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Category } from '../models/category';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { catchError, of, take } from 'rxjs'; // Import catchError and of

@Component({
    selector: 'app-categories-container',
    standalone: true,
    imports: [
        CommonModule,
        RouterOutlet,
        CategoryListComponent,
        CategoryNewComponent,
        CategoryEditComponent,
        FormsModule,
        ReactiveFormsModule,
    ],
    template: `
        <app-categories-list
            class="w-full"
            [categories]="categories"
            (eventNew)="eventNew($event)"
            (eventEdit)="eventEdit($event)"
            (eventDelete)="eventDelete($event)"
        ></app-categories-list>

        <!-- Display error message -->
        <div *ngIf="error" class="error-message">
            <p>{{ error }}</p>
        </div>
    `,
})
export class CategoryContainerComponent implements OnInit {
    public error: string = ''; // To store any error message
    public categories: Category[] = [];
    public category: Category = new Category();

    constructor(
        private _categoryService: CategoryService,
        private _confirmDialogService: ConfirmDialogService,
        private _matDialog: MatDialog,
    ) {}

    ngOnInit(): void {
        this.getCategories();
    }

    // Obtener todas las categorías
    getCategories(): void {
        this._categoryService.getAll$().pipe(
            take(1),  // Automatically unsubscribes after one emission
            catchError((error) => {
                this.error = 'Failed to load categories. Please try again later.'; // Set a user-friendly error message
                return of([]); // Return an empty array to avoid further errors
            })
        ).subscribe((response) => {
            this.categories = response;
        });
    }

    // Abrir modal para crear una nueva categoría
    public eventNew($event: boolean): void {
        if ($event) {
            const categoryForm = this._matDialog.open(CategoryNewComponent);
            categoryForm.componentInstance.title = 'Nueva Categoría';
            categoryForm.afterClosed().subscribe((result: Category) => {
                if (result) {
                    this.saveCategory(result);
                }
            });
        }
    }

    // Guardar una nueva categoría
    saveCategory(data: Category): void {
        this._categoryService.add$(data).pipe(
            take(1),  // Automatically unsubscribes after one emission
            catchError((error) => {
                this.error = 'Failed to save category. Please try again later.';
                return of(null); // Return null to prevent further errors
            })
        ).subscribe((response) => {
            if (response) {
                this.getCategories(); // Actualizar la lista de categorías después de agregar
            }
        });
    }

    // Abrir modal para editar una categoría
    eventEdit(idCategory: number): void {
        this._categoryService.getById$(idCategory).pipe(
            take(1),  // Automatically unsubscribes after one emission
            catchError((error) => {
                this.error = 'Failed to load category for editing.';
                return of(null); // Return null in case of an error
            })
        ).subscribe((response) => {
            if (response) {
                this.category = response;
                this.openModalEdit(this.category);
            }
        });
    }

    // Abrir el modal de edición
    openModalEdit(data: Category): void {
        if (data) {
            const categoryForm = this._matDialog.open(CategoryEditComponent);
            categoryForm.componentInstance.title = `Editar <b>${data.franchise || data.id}</b>`;
            categoryForm.componentInstance.category = { ...data }; // Hacer una copia de los datos
            categoryForm.afterClosed().subscribe((result: Category) => {
                if (result) {
                    this.editCategory(data.id, result);
                }
            });
        }
    }

    // Editar categoría
    editCategory(idCategory: number, data: Category): void {
        this._categoryService.update$(idCategory, data).pipe(
            take(1),  // Automatically unsubscribes after one emission
            catchError((error) => {
                this.error = 'Failed to edit category. Please try again later.';
                return of(null); // Return null to prevent further errors
            })
        ).subscribe((response) => {
            if (response) {
                this.getCategories(); // Actualizar la lista de categorías después de editar
            }
        });
    }

    // Eliminar categoría
    public eventDelete(idCategory: number): void {
        this._confirmDialogService.confirmDelete({
            message: '¿Seguro que deseas eliminar esta categoría?',
        }).then(() => {
            this._categoryService.delete$(idCategory).pipe(
                take(1),  // Automatically unsubscribes after one emission
                catchError((error) => {
                    this.error = 'Failed to delete category. Please try again later.';
                    return of(null); // Return null to prevent further errors
                })
            ).subscribe((response) => {
                if (response) {
                    this.getCategories(); // Recargar las categorías después de eliminar
                }
            });
        }).catch(() => {
            // Manejo de cancelación de la eliminación
        });
    }
}
