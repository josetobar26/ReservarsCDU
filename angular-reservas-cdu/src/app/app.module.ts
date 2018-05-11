import { registerLocaleData } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule }     from './app-routing.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CalendarModule } from 'angular-calendar';
import localeEs from '@angular/common/locales/es';

import { AppComponent } from './app.component';
import { EspacioDeportivosComponent } from './espacio-deportivos/espacio-deportivos.component';
import { MessagesComponent } from './messages/messages.component';
import { MessageService } from './message.service';
import { EspaciodeportivoService } from './espaciodeportivo.service';
import { AgregarescenarioComponent } from './escenario/agregarescenario/agregarescenario.component';
import { MostrarFotoComponent } from './mostrar-foto/mostrar-foto.component';
import { HorarioFijoComponent } from './horario-fijo/horario-fijo.component';
import { EdicionReservaComponent } from './edicion-reserva/edicion-reserva.component';
import { MenuNavegacionComponent } from './menu-navegacion/menu-navegacion.component';
import { CalendarComponent } from './calendar/calendar.component';
import { DemoUtilsModule } from '../demo-utils/module';
import { ReactiveFormsModule } from '@angular/forms';



registerLocaleData(localeEs);

@NgModule({
  declarations: [
    AppComponent,
    EspacioDeportivosComponent,
    MessagesComponent,
    AgregarescenarioComponent,
    MostrarFotoComponent,
    HorarioFijoComponent,
    EdicionReservaComponent,
    MenuNavegacionComponent,
    CalendarComponent
  ],
  imports: [
    BrowserModule,
    FormsModule, 
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule,
    NgbModule.forRoot(),
    CalendarModule.forRoot(),
    DemoUtilsModule
  ],
  providers: [MessageService,EspaciodeportivoService],
  bootstrap: [AppComponent]
})
export class AppModule {
	title = 'Reservas CDU';
 }
