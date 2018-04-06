import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { EspacioDeportivosComponent } from './espacio-deportivos/espacio-deportivos.component';
import { AgregarescenarioComponent } from './agregarescenario/agregarescenario.component';
import { HorarioFijoComponent} from './horario-fijo/horario-fijo.component';
import { MostrarFotoComponent} from './mostrar-foto/mostar-foto.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

const routes: Routes = [
  { path: '', redirectTo: '/espaciodeportivos', pathMatch: 'full' },	
  { path: 'espaciodeportivos', component: EspacioDeportivosComponent },
   { path: 'agregarescenario', component: AgregarescenarioComponent },
   { path: 'horariofijo', component: HorarioFijoComponent },
   { path: 'mostrarfoto', component: MostrarFotoComponent }
   
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes),
    NgbModule	
  ],
  exports: [ RouterModule ],
  declarations: []
})
export class AppRoutingModule { }
