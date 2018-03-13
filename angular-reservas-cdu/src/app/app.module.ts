import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { EspacioDeportivosComponent } from './espacio-deportivos/espacio-deportivos.component';
import { MessagesComponent } from './messages/messages.component';
import { MessageService } from './message.service';
import { EspaciodeportivoService } from './espaciodeportivo.service';



@NgModule({
  declarations: [
    AppComponent,
    EspacioDeportivosComponent,
    MessagesComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule


  ],
  providers: [MessageService,EspaciodeportivoService],
  bootstrap: [AppComponent]
})
export class AppModule {
	title = 'Reservas CDU';
 }
