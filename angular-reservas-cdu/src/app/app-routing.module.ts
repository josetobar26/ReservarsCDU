import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { EspacioDeportivosComponent } from './espacio-deportivos/espacio-deportivos.component';

const routes: Routes = [
  { path: 'espaciodeportivos', component: EspacioDeportivosComponent }
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  declarations: []
})
export class AppRoutingModule { }
