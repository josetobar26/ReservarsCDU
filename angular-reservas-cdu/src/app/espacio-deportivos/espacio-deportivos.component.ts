import { Component, OnInit } from '@angular/core';
import { EspacioDeportivo } from '../espaciodeportivo';
import { Deporte } from '../deporte';
import { EspaciodeportivoService } from '../espaciodeportivo.service';

//******************************************************************************

@Component({
    selector: 'app-espacio-deportivos',
    templateUrl: './espacio-deportivos.component.html',
    styleUrls: ['./espacio-deportivos.component.css']
})

//******************************************************************************

export class EspacioDeportivosComponent implements OnInit {
    espacios: EspacioDeportivo[];
    espacioSelected: EspacioDeportivo;
    deporteSelected: Deporte;
    obj: Deporte;

    deportesSelect: Deporte[];
    deportesAnexados = [];

    ubicacionesSelect = [
        { value: 'CDU', text: 'CDU' },
        { value: 'Diamante', text: 'Diamante' },
    ];
 
    estadosSelect = [
        { value: 'En Servicio', text: 'En Servicio' },
        { value: 'Mantenimiento', text: 'Mantenimiento' },
    ];
    
    required:boolean = true;
    accion:string = 'Detalle';
    iconBtnSubmit:string = '';

//------------------------------------------------------------------------------

    constructor(private espacioService:EspaciodeportivoService) { }

//------------------------------------------------------------------------------

    ngOnInit() {
        this.espacioSelected = new EspacioDeportivo(0, '', '', '', [],'');
        this.deporteSelected = new Deporte(0, '');
        this.getEspaciosDeportivos();
    }

//------------------------------------------------------------------------------

    getEspaciosDeportivos() {
        this.espacioService.getEspaciosDeportivos().subscribe(espacios => this.espacios = espacios);
    }

//------------------------------------------------------------------------------        

    setNuevo() {
        this.espacioSelected = new EspacioDeportivo(0, '', '', '',[], '');
        this.deporteSelected = new Deporte(0, '');
        this.deportesAnexados = [];
        this.espacioService.getDeportes().subscribe(deportes => this.deportesSelect = deportes);
        this.accion = 'Registrar';
        this.required = true;
        this.iconBtnSubmit = 'fa fa-save';
    }

//------------------------------------------------------------------------------

    verEspacioDeportivo(espaciodeportivo, accion) {
        this.espacioSelected = new EspacioDeportivo(espaciodeportivo.idEspacio, espaciodeportivo.nombre, espaciodeportivo.estado, espaciodeportivo.ubicacion, espaciodeportivo.descripcion);
        this.deporteSelected = new Deporte(0, '');
        this.deportesAnexados = [];
        this.accion = accion;
        switch(accion) {
            case 'Actualizar':
                this.required = true;
                this.iconBtnSubmit = 'fa fa-save';
                break;
            case 'Detalle':
                this.required = false;
                break;
            case 'Eliminar':
                this.required = false;
                this.iconBtnSubmit = 'fa fa-times-circle';
                break;
        }
    }

//------------------------------------------------------------------------------        

    anexarDeporte() {
        if (this.deporteSelected.idDeporte !== 0) {
            for (let x = 0; x < this.deportesAnexados.length; x++) {
                if (this.deportesAnexados[x].idDeporte === this.deporteSelected.idDeporte) {
                    alert("EL DEPORTE << " + this.deporteSelected.nombre.toUpperCase() + " >> YA SE ENCUENTRA SELECCIONADO");
                    return false;
                }
            }
            this.deportesAnexados.push(this.deporteSelected);
        }
        return false;
    }
    
//------------------------------------------------------------------------------        
    
    quitarDeporte(deporte) {
        for (let x = 0; x < this.deportesAnexados.length; x++) {
            if (this.deportesAnexados[x].idDeporte === deporte.idDeporte) {
                this.deportesAnexados.splice(x, 1);
                return false;
            }
        }
    }
    
//------------------------------------------------------------------------------        
    
    enviarFormulario(): boolean {
        if (this.deportesAnexados.length === 0) {
            alert("DEBE ANEXAR AL MENOS UN DEPORTE");
            return false;
        }
        this.espacioSelected.deportelist=this.deportesAnexados;
        this.espacioService.guardarEspacioDeportivo(this.espacioSelected);
        return confirm("¿ DESEA " + this.accion.toUpperCase() + " ESTE ESPACIO DEPORTIVO ?");
        
    }

//------------------------------------------------------------------------------        

    deportesIguales(deporte1, deporte2): boolean {
        if(deporte1.idDeporte == deporte2.idDeporte) {
            return true;
        } else {
            return false;
        }
    }
    
//------------------------------------------------------------------------------        

//------------------------------------------------------------------------------        

}
