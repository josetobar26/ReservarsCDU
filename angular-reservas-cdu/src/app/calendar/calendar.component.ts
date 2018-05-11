import { Component, OnInit, ViewChild, TemplateRef, ChangeDetectionStrategy, Input, ChangeDetectorRef } from '@angular/core';
import {
  startOfDay,
  endOfDay,
  subDays,
  addDays,
  endOfMonth,
  isSameDay,
  isSameMonth,
  addHours,
  getSeconds,
  getMinutes,
  getHours,
  setHours,
  setSeconds,
  setMinutes,
} from 'date-fns';
import { ReservaEspacio } from '../reservaespacio';
import {
  CalendarEvent,
  CalendarEventAction,
  CalendarEventTimesChangedEvent,
  CalendarDateFormatter,
  DAYS_OF_WEEK
} from 'angular-calendar';
import { DemoUtilsModule } from '../../demo-utils/module';
import { Subject } from 'rxjs/Subject';
import { NgbDateStruct, NgbTimeStruct } from '@ng-bootstrap/ng-bootstrap';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { EspacioDeportivo } from '../espaciodeportivo';
import { EspaciodeportivoService } from '../espaciodeportivo.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
const colors: any = {
  red: {
    primary: '#ad2121',
    secondary: '#FAE3E3'
  },
  blue: {
    primary: '#1e90ff',
    secondary: '#D1E8FF'
  },
  yellow: {
    primary: '#e3bc08',
    secondary: '#FDF1BA'
  }
};

@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.css']
})
export class CalendarComponent implements OnInit {

  inicioDiarioStruct = {
    hour: 2,
    minute: 23,
    second: 0

  };
  finalDiarioStruct = {
    hour: 2,
    minute: 23,
    second: 0

  };
  @ViewChild('modalContent') modalContent: TemplateRef<any>;
  view: string = 'month';
  name= "final";
  eventActual: CalendarEvent[];
  eventAct: CalendarEvent;
  option1 = false;
  option2 = false;
  locale: string = 'es';
  espacio34: EspacioDeportivo;
  @Input() selectEspacio: EspacioDeportivo;
  reservaSave: ReservaEspacio;
  reservaAct: ReservaEspacio = new ReservaEspacio(0, new Date(), new Date(), '', '', '', false, null);
  reservasActuales: ReservaEspacio[];

  
  tipoSelect = [
    { value: 'Academico', text: 'Academico' },
    { value: 'Normal', text: 'Normal' },
    { value: 'Evento', text: 'Evento' },
    { value: 'Seleccionados', text: 'Seleccionados' },
  ];
  viewDate: Date = new Date();
  actions: CalendarEventAction[] = [
    {
      label: '<i class="fa fa-fw fa-pencil"></i>',
      onClick: ({ event }: { event: CalendarEvent }): void => {
        this.handleEvent('Edited', event);
      }
    },
    {
      label: '<i class="fa fa-fw fa-times"></i>',
      onClick: ({ event }: { event: CalendarEvent }): void => {
        this.events = this.events.filter(iEvent => iEvent !== event);
        this.handleEvent('Deleted', event);
      }
    }
  ];
  modalData: {
    action: string;
    event: CalendarEvent;
  };


  activeDayIsOpen: boolean = true;

  refresh: Subject<any> = new Subject();

  events: CalendarEvent[] = [];
  weekStartsOn: number = DAYS_OF_WEEK.MONDAY;

  weekendDays: number[] = [DAYS_OF_WEEK.FRIDAY, DAYS_OF_WEEK.SATURDAY];

  
  constructor(private modal: NgbModal, private espacioService: EspaciodeportivoService, private cdr: ChangeDetectorRef) {
    this.inicioDiarioStruct = {
      hour: 2,
      minute: 23,
      second: 0
  
    };
    this.finalDiarioStruct = {
      hour: 2,
      minute: 23,
      second: 0
  
    };
  }

  formReserva: FormGroup;
  ngOnInit() {

    this.getReservasEspacio();
    this.formReserva = new FormGroup({
      'nombre':new FormControl(this.reservaAct.nombre,[
        Validators.required,
        Validators.maxLength(20)]),
      'tipo':new FormControl(this.reservaAct.tipo,  
      Validators.required),
      'inicioDiarioStruct': new FormControl(this.inicioDiarioStruct),
      'finalDiarioStruct': new FormControl(this.finalDiarioStruct),
    });
    



  }
  getReservasEspacio() {

    this.espacioService.getReservasEspacio(this.selectEspacio.idEspacio).subscribe(reservas => {
      this.reservasActuales = reservas;

      this.cargarReservas();
    });

  }
  cargarReservas() {
    console.log("lengt" + this.reservasActuales.length);
    for (let i = 0; i < this.reservasActuales.length; i++) {
      //let fechaIni= this.setFecha(this.reservasActuales[i].fechaini);
      //let fechafin = this.setFecha(this.reservasActuales[i].fechafin);
      console.log(this.reservasActuales[i].nombre);
      // console.log("fecha ini"+fechaIni);
      console.log("date" + new Date(this.reservasActuales[i].fechaini));
      this.events.push({
        title: this.reservasActuales[i].nombre.toString(),
        start: new Date(this.reservasActuales[i].fechaini),
        end: new Date(this.reservasActuales[i].fechafin),
        color: colors.red,
        draggable: true,
        resizable: {
          beforeStart: true,
          afterEnd: true
        }
      });

    }
    this.refresh.next();
  }

  setFecha(fecha: Date): Date {
    let fecha2 = new Date();
    fecha2.setMonth(fecha.getMonth());
    fecha2.setHours(fecha.getHours(), fecha.getMinutes(), fecha.getSeconds());
    return fecha2;

  }
  eventTimesChanged({
    event,
    newStart,
    newEnd
  }: CalendarEventTimesChangedEvent): void {
    event.start = newStart;
    event.end = newEnd;
    this.handleEvent('Dropped or resized', event);
    this.refresh.next();
  }
  handleEvent(action: string, event: CalendarEvent): void {
    this.modalData = { event, action };
    this.modal.open(this.modalContent, { size: 'lg' });
  }
  addReserva(event) {

    this.eventAct = {
      title: this.reservaAct.nombre.toString(),
      start: this.viewDate,
      end: this.viewDate,
      color: colors.red,
      draggable: true,
      resizable: {
        beforeStart: true,
        afterEnd: true
      }
    };

    console.log("reserva:" + this.reservasActuales.length);

  }
  onFilteroption1(ischecked: boolean) {
    this.option1 = true;
    this.option2 = false;
  }
  onFilteroption2(ischecked: boolean) {
    this.option2 = true;
    this.option1 = false;

    this.inicioDiarioStruct={second: getSeconds(this.viewDate),
    minute: getMinutes(this.viewDate),
    hour: getHours(this.viewDate)
    };

    this.finalDiarioStruct={second: getSeconds(this.viewDate),
      minute: getMinutes(this.viewDate),
      hour: getHours(this.viewDate)
      };

    


    // this.cdr.detectChanges();
  }
  guardarReserva(event) {
    console.log("espacio seleccionado" + this.selectEspacio.nombre);
    const inicio = this.eventAct.start;
    const final = this.eventAct.end;
    let reservaActual = this.reservaAct;
    reservaActual.idEspacio = this.selectEspacio;
    reservaActual.nombre=this.formReserva.get('nombre').value;
    reservaActual.tipo=this.formReserva.get('tipo').value;
    if(this.option1){
      reservaActual.esfija = this.option1;

      console.log("inicio1" + inicio);
      console.log("inicio2:" + final);
      //inicio.setDate(inicio.getDate()+7);
      console.log("inicio" + inicio);
      if (final >= inicio) {
        console.log("Entro en el if");
        while (inicio <= final) {
          console.log("Entro en el bucle");
          //aqui se crea una reserva cada 8 dias
          let inicioCopia = new Date();
          let inicioCopia2 = new Date();
          //final del evento
          inicioCopia.setDate(inicio.getDate());
          inicioCopia.setMonth(inicio.getMonth());
          inicioCopia.setHours(final.getHours(), final.getMinutes(), final.getSeconds());
          //inicio del evento
          inicioCopia2.setDate(inicio.getDate());
          inicioCopia2.setMonth(inicio.getMonth());
          inicioCopia2.setHours(inicio.getHours(), inicio.getMinutes(), inicio.getSeconds());
          console.log("Inicio de hora" + inicio);
          console.log("Final de Hora" + inicioCopia);
          console.log("Inicio del evento" + inicioCopia2);
          //aqui se reserva 
          reservaActual.fechaini = inicioCopia2;
          reservaActual.fechafin = inicioCopia;
  
  
          this.espacioService.guardarReservaEspacio(reservaActual).subscribe(reservaActual => { this.reservaSave = reservaActual });
  
  
          this.events.push({
            title: this.reservaAct.nombre.toString(),
            start: inicioCopia2,
            end: inicioCopia,
            color: colors.red,
            draggable: true,
            resizable: {
              beforeStart: true,
              afterEnd: true
            }
          });
          inicio.setDate(inicio.getDate() + 7);
        }
  
      } else {
        //la reserva no puede ser fija
  
      }
  
      this.refresh.next();
  
    }
    else{
      reservaActual.esfija=this.option2;
      const InicioDate: Date = setHours(
          setMinutes(
            setSeconds(inicio, this.inicioDiarioStruct.second),
            this.inicioDiarioStruct.minute
          ),
          this.inicioDiarioStruct.hour
        );

        const FinalDate: Date = setHours(
          setMinutes(
            setSeconds(final, this.finalDiarioStruct.second),
            this.finalDiarioStruct.minute
          ),
          this.finalDiarioStruct.hour
        );
        console.log("Inicio de Reserva"+InicioDate);
        console.log("Final de Reserva"+FinalDate); 

        reservaActual.fechaini = InicioDate;
        reservaActual.fechafin = FinalDate;

        this.espacioService.guardarReservaEspacio(reservaActual).subscribe(reservaActual => { this.reservaSave = reservaActual });
  
  
        this.events.push({
          title: this.reservaAct.nombre.toString(),
          start: InicioDate,
          end:FinalDate,
          color: colors.red,
          draggable: true,
          resizable: {
            beforeStart: true,
            afterEnd: true
          }
        });

    }
    
  }
  updateTimeInicio(): void {
      //AQUI DEBO HACER EL UPDATE A EL VIEWDATE
   
  }
  updateTimeFinal(): void {
    

  }

}
