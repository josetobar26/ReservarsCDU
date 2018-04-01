import { Component, OnInit } from '@angular/core';
import { Deporte } from '../deporte';
import { EspaciodeportivoService } from '../espaciodeportivo.service';

@Component({
  selector: 'app-horario-fijo',
  templateUrl: './horario-fijo.component.html',
  styleUrls: ['./horario-fijo.component.css']
})
export class HorarioFijoComponent implements OnInit {

  private deportes:Deporte[];
  constructor(private espacioService:EspaciodeportivoService) { }

  ngOnInit() {
  	this.getDeportes();
  }

  getDeportes(): void {
    this.espacioService.getDeportes()
    .subscribe(deportes => this.deportes = deportes);
    
  }

}
