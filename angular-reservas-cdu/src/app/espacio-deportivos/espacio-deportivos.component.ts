import { Component, OnInit } from '@angular/core';
import { EspacioDeportivo } from '../espaciodeportivo';
import { EspaciodeportivoService } from '../espaciodeportivo.service';

@Component({
  selector: 'app-espacio-deportivos',
  templateUrl: './espacio-deportivos.component.html',
  styleUrls: ['./espacio-deportivos.component.css']
})
export class EspacioDeportivosComponent implements OnInit {

  private espacios:EspacioDeportivo[];

  constructor(private espacioService:EspaciodeportivoService) { }

  ngOnInit() {
  	this.getEspaciodeportivos();
  }

  getEspaciodeportivos(): void {
    
    this.espacioService.getEspaciodeportivos()
    .subscribe(espacios => this.espacios = espacios);
    
  }

}
