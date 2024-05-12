import { Component, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { constantes } from 'src/environment/constantes';
import { ConfiguracionesService } from 'src/app/services/configuraciones.service';
import { Configuracion } from 'src/app/models/modelo.configuracion';

@Component({
  selector: 'app-configuraciones',
  templateUrl: './configuraciones.component.html',
  styleUrls: ['./configuraciones.component.css']
})

export class ConfiguracionesComponent {
  readonly constantes = constantes;
  crearConfiguracionForm!: FormGroup;

 @Input() configuraciones: Configuracion[];

  constructor(private formBuilder: FormBuilder, public configuracionesService : ConfiguracionesService) {
    this.configuraciones = [];
  }

  ngOnInit(): void {
    this.configuracionesService.obtenerConfiguraciones().subscribe((configuraciones: Configuracion[]) => this.configuraciones = configuraciones);
    this.crearConfiguracionForm = this.formBuilder.group({
      nombre: ["", [Validators.required, Validators.minLength(constantes.MINIMO_NOMBRE_CONFIGURACION), Validators.maxLength(constantes.MAXIMO_NOMBRE_CONFIGURACION)]],
      valor: ["", [Validators.required, Validators.minLength(constantes.MINIMO_VALOR_CONFIGURACION), Validators.maxLength(constantes.MAXIMO_VALOR_CONFIGURACION)]]
    });
  }

  get nombre() { return this.crearConfiguracionForm.get('nombre'); }
  get valor() { return this.crearConfiguracionForm.get('valor'); }

  crear(value: any) {
    this.configuracionesService.crear(value.nombre, value.valor)
      .subscribe((configuracion: Configuracion) => {
        this.refrescar();
        this.crearConfiguracionForm.reset();
      })
  }

  refrescar(): void {
    this.configuracionesService.obtenerConfiguraciones()
      .subscribe((configuraciones: Configuracion[]) => this.configuraciones = configuraciones);
  }
}
