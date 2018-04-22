import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
import { MessageService } from './message.service';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import { EspacioDeportivo } from './espaciodeportivo';
import { Deporte } from './deporte';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class EspaciodeportivoService {
    private espaciosUrl = 'http://localhost:8084/CRUD_Escenarios/proyectoCDU/Escenario';
    private deportesUrl = 'http://localhost:8084/CRUD_Escenarios/proyectoCDU/Escenario/deportes';

    //------------------------------------------------------------------------------

    constructor(private http: HttpClient, private messageService: MessageService) { }

    //------------------------------------------------------------------------------

    getEspaciosDeportivos(): Observable<EspacioDeportivo[]> {
        return this.http.get<EspacioDeportivo[]>(this.espaciosUrl).pipe(tap(espacios => this.log(`fetched heroes`)), catchError(this.handleError('getEspaciodeportivos', [])));
    }

    //------------------------------------------------------------------------------

    getDeportes(): Observable<Deporte[]> {
        return this.http.get<Deporte[]>(this.deportesUrl).pipe(tap(deportes => this.log(`fetched deportes`)), catchError(this.handleError('getDeportes', [])));
    }

    //------------------------------------------------------------------------------

    guardarEspacioDeportivo(newEspacio: EspacioDeportivo): Observable<EspacioDeportivo> {
        //let json = JSON.stringify(newEspacio);
        console.log(newEspacio);
        //const url= '${this.espaciosUrl}/Agregar';
        return this.http.post<EspacioDeportivo>(this.espaciosUrl, newEspacio, httpOptions).pipe(
            tap((newEspacio: EspacioDeportivo) => this.log('added hero w/ id=${newEspacio.idEspacio}')),
            catchError(this.handleError<EspacioDeportivo>('addHero'))
        );
    }

    //------------------------------------------------------------------------------

    private handleError<T>(operation = 'operation', result?: T) {
        return (error: any): Observable<T> => {
            // TODO: send the error to remote logging infrastructure
            console.error(error); // log to console instead

            // TODO: better job of transforming error for user consumption
            this.log(`${operation} failed: ${error.message}`);

            // Let the app keep running by returning an empty result.
            return of(result as T);
        };
    }

    private log(message: string) {
        this.messageService.add('HeroService: ' + message);
    }
    getEscenariosforid(id: number): Observable<EspacioDeportivo[]> {
        const url = `${this.espaciosUrl}/EspacioDeporte/${id}`;
        return this.http.get<EspacioDeportivo[]>(url)
            .pipe(
                tap(escenarios => this.log(`fetched hero id=${id}`)),
                catchError(this.handleError('getEscenariosforid', []))
            );
    }

}
