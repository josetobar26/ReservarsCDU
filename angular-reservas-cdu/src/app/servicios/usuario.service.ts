import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
 
import { Usuario } from '../modelos/index';
 
@Injectable()
export class UsuarioService {
    constructor(private http: HttpClient) { }
 
    getAll() {
        return this.http.get<Usuario[]>('/api/usuarios');
    }
 
    getById(id: number) {
        return this.http.get('/api/usuarios/' + id);
    }
 
    create(Usuario: Usuario) {
        return this.http.post('/api/usuarios', Usuario);
    }
 
    update(Usuario: Usuario) {
        return this.http.put('/api/usuarios/' + Usuario.idUsuario, Usuario);
    }
 
    delete(id: number) {
        return this.http.delete('/api/usuarios/' + id);
    }
}