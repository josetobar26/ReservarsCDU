import { Component, OnInit, ViewChild,TemplateRef,ChangeDetectionStrategy } from '@angular/core';
import {
  startOfDay,
  endOfDay,
  subDays,
  addDays,
  endOfMonth,
  isSameDay,
  isSameMonth,
  addHours
} from 'date-fns';
import { ReservaEspacio } from '../reservaespacio';
import {
  CalendarEvent,
  CalendarEventAction,
  CalendarEventTimesChangedEvent
} from 'angular-calendar';
import { DemoUtilsModule } from '../../demo-utils/module';
import { Subject } from 'rxjs/Subject';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { EspacioDeportivo } from '../espaciodeportivo';
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
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.css']
})
export class CalendarComponent implements OnInit {

   @ViewChild('modalContent') modalContent: TemplateRef<any>;
   view: string = 'month';
   eventActual: CalendarEvent[];
   eventAct:CalendarEvent;
   option1 = false;
   option2 = false;
   espacio34:EspacioDeportivo;
   reservaAct:ReservaEspacio=new ReservaEspacio(null,new Date(),new Date(),'','','',null);
  

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

  events: CalendarEvent[] = [
    {
      start: subDays(startOfDay(new Date()), 1),
      end: addDays(new Date(), 1),
      title: 'A 3 day event',
      color: colors.red,
      actions: this.actions
    },
    {
      start: startOfDay(new Date()),
      title: 'An event with no end date',
      color: colors.yellow,
      actions: this.actions
    },
    {
      start: subDays(endOfMonth(new Date()), 3),
      end: addDays(endOfMonth(new Date()), 3),
      title: 'A long event that spans 2 months',
      color: colors.blue
    },
    {
      start: addHours(startOfDay(new Date()), 2),
      end: new Date(),
      title: 'A draggable and resizable event',
      color: colors.yellow,
      actions: this.actions,
      resizable: {
        beforeStart: true,
        afterEnd: true
      },
      draggable: true
    }
  ];

  constructor(private modal: NgbModal) { }

  ngOnInit() {
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
  addReserva(event){

    this.eventAct={
      title: 'New event',
      start: startOfDay(new Date()),
      end: endOfDay(new Date()),
       color: colors.red,
        draggable: true,
        resizable: {
        beforeStart: true,
        afterEnd: true
        }
      };
    
  	
  	

  }
  onFilteroption1(ischecked:boolean){
   this.option1=true;
   this.option2=false;
  }
  onFilteroption2(ischecked:boolean){
    this.option2=true;
    this.option1=false;

  }
  guardarReserva(event){
    const inicio=this.eventAct.start;
    const final=this.eventAct.end;
    console.log("inicio1"+inicio.getDate());
    console.log("inicio1"+final);
    inicio.setDate(inicio.getDate()+7);
    console.log("inicio"+inicio.getDate());
    if(inicio>=final){
      while(inicio<=final){
        //aqui se crea una reserva cada 8 dias
        this.events.push({
          title: 'New event',
          start: inicio,
          end: inicio,
          color: colors.red,
          draggable: true,
          resizable: {
            beforeStart: true,
            afterEnd: true
          }
        });
        inicio.setDate(inicio.getDate()+7);
      }

    }else{
     //la reserva no puede ser fija
     
    }

    this.refresh.next;

  }

}
