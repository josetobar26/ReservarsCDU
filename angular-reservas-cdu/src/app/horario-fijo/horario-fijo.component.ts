import { Component, OnInit } from '@angular/core';
import { Deporte } from '../deporte';
import { EspaciodeportivoService } from '../espaciodeportivo.service';
import { EspacioDeportivo} from '../espaciodeportivo';

@Component({
  selector: 'app-horario-fijo',
  templateUrl: './horario-fijo.component.html',
  styleUrls: ['./horario-fijo.component.css']
})
export class HorarioFijoComponent implements OnInit {

  private deportes:Deporte[];
   selectedDeporte:Deporte ;
   selectedEspacio:EspacioDeportivo;
   private escenarios: EspacioDeportivo[];

  constructor(private espacioService:EspaciodeportivoService) { }
 
  ngOnInit() {
  	this.getDeportes();
  }

  getDeportes(): void {
    this.espacioService.getDeportes()
    .subscribe(deportes => this.deportes = deportes);
    
  }
  selectDeporte(event){
  console.log(event.nombre);
  this.getEscenarioDeportivos(event.idDeporte)    

  }
  getEscenarioDeportivos(idDeporte):void{
    this.espacioService.getEscenariosforid(idDeporte).subscribe(escenarios=> this.escenarios = escenarios);
  }

}
