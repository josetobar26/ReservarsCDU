import { Component, OnInit } from '@angular/core';
import { Deporte } from '../deporte';
import { EspaciodeportivoService } from '../espaciodeportivo.service';

@Component({
  selector: 'app-horario-fijo',
  templateUrl: './horario-fijo.component.html',
  styleUrls: ['./horario-fijo.component.css']
})
export class HorarioFijoComponent implements OnInit {
<<<<<<< HEAD
    
  constructor() { }
=======

  private deportes:Deporte[];
  constructor(private espacioService:EspaciodeportivoService) { }
>>>>>>> 2ee50a4bba173e9037ae74f6e31463a602c976fa

  ngOnInit() {
  	this.getDeportes();
  }

  getDeportes(): void {
    this.espacioService.getDeportes()
    .subscribe(deportes => this.deportes = deportes);
    
  }

}
