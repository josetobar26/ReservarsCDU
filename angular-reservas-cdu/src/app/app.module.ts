import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule }     from './app-routing.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppComponent } from './app.component';
import { EspacioDeportivosComponent } from './espacio-deportivos/espacio-deportivos.component';
import { MessagesComponent } from './messages/messages.component';
import { MessageService } from './message.service';
import { EspaciodeportivoService } from './espaciodeportivo.service';
import { AgregarescenarioComponent } from './agregarescenario/agregarescenario.component';
import { MostrarFotoComponent } from './mostrar-foto/mostrar-foto.component';
import { HorarioFijoComponent } from './horario-fijo/horario-fijo.component';
import { EdicionReservaComponent } from './edicion-reserva/edicion-reserva.component';



@NgModule({
  declarations: [
    AppComponent,
    EspacioDeportivosComponent,
    MessagesComponent,
    AgregarescenarioComponent,
    MostrarFotoComponent,
    HorarioFijoComponent,
    EdicionReservaComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    NgbModule.forRoot()


  ],
  providers: [MessageService,EspaciodeportivoService],
  bootstrap: [AppComponent]
})
export class AppModule {
	title = 'Reservas CDU';
 }
