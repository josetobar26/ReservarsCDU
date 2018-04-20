import { Deporte } from './deporte';
export class EspacioDeportivo {
    constructor(
	public idEspacio: number,
	public nombre: string,
	public estado: string,
	public ubicacion: string,
	public deporteList: Deporte[],
	public descripcion?: string
    ) {  }
}