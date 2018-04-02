import { Component, OnInit} from '@angular/core';
import { Deporte } from '../deporte';
import { EspaciodeportivoService } from '../espaciodeportivo.service';
import { EdicionReservaService } from '../edicion-reserva.service';
import { EspacioDeportivo} from '../espaciodeportivo';


@Component({
  selector: 'app-horario-fijo',
  templateUrl: './horario-fijo.component.html',
  styleUrls: ['./horario-fijo.component.css'],
  providers: [EdicionReservaService]
})
export class HorarioFijoComponent implements OnInit {

  private deportes:Deporte[];

  private horaI = "7 am";
  private horaF = "9 am";
  selectedDeporte:Deporte ;
   selectedEspacio:EspacioDeportivo;
   private escenarios: EspacioDeportivo[];


  constructor(private espacioService:EspaciodeportivoService, 
    private edicionReservaService:EdicionReservaService) { }
   

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

  history: string[] = [];
  
  announce() {
    this.edicionReservaService.cambiarHoraReserva(this.horaI, this.horaF);
    this.history.push(`Hora Inicial: "${this.horaI}"`);
    this.history.push(`Hora Final: "${this.horaF}"`);

  }

}