import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule }     from './app-routing.module';

import { AppComponent } from './app.component';
import { EspacioDeportivosComponent } from './espacio-deportivos/espacio-deportivos.component';
import { MessagesComponent } from './messages/messages.component';
import { MessageService } from './message.service';
import { EspaciodeportivoService } from './espaciodeportivo.service';
import { AgregarescenarioComponent } from './agregarescenario/agregarescenario.component';



@NgModule({
  declarations: [
    AppComponent,
    EspacioDeportivosComponent,
    MessagesComponent,
    AgregarescenarioComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule


  ],
  providers: [MessageService,EspaciodeportivoService],
  bootstrap: [AppComponent]
})
export class AppModule {
	title = 'Reservas CDU';
 }
