import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { EspacioDeportivosComponent } from './espacio-deportivos/espacio-deportivos.component';
import { AgregarescenarioComponent } from './agregarescenario/agregarescenario.component';

const routes: Routes = [
  { path: '', redirectTo: '/espaciodeportivos', pathMatch: 'full' },	
  { path: 'espaciodeportivos', component: EspacioDeportivosComponent },
   { path: 'agregarescenario', component: AgregarescenarioComponent }

];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [ RouterModule ],
  declarations: []
})
export class AppRoutingModule { }
