import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { environment } from 'src/environment/environment';
import { Configuracion, ConfiguracionDefault } from '../models/modelo.configuracion';

@Injectable({
  providedIn: 'root'
})

export class ConfiguracionesService {
  private API_URL = environment.API_URL;
  private configuracionesUrl: string = `${this.API_URL}/configuraciones/`;

  private estado$: BehaviorSubject<Configuracion> = new BehaviorSubject(new ConfiguracionDefault());
  public estado: Observable<Configuracion> = this.estado$.asObservable();

  constructor(private http: HttpClient) {
  }

  obtenerConfiguraciones() : Observable<Configuracion[]> {
    return this.http.get<Configuracion[]>(this.configuracionesUrl);
  }

  crear(nombre: string, valor: string): Observable<Configuracion> {
    return this.http.post<Configuracion>(this.configuracionesUrl, { nombre, valor });
  }

  borrar(configuracion: Configuracion): Observable<any> {
    return this.http.delete(`${this.configuracionesUrl}${configuracion.id}/`);
  }

  modificar(configuracion: Configuracion, nombre: string, valor: string): Observable<Configuracion> {
    return this.http.put<Configuracion>(`${this.configuracionesUrl}${configuracion.id}/`, { nombre, valor })
      .pipe(tap((c: Configuracion) =>
      {
        this.estado$.next(c);
      }));
  }
}
