import { Routes } from '@angular/router';
import {CategoryContainerComponent} from "./containers/inventario-container.component";
import {CategoryComponent} from "./inventario.component";

export default [

  {
    path     : '',
    component: CategoryComponent,
    children: [
      {
        path: '',
        component: CategoryContainerComponent,
        data: {
          title: 'Categoría'
        }
      },
    ],
  },
] as Routes;
