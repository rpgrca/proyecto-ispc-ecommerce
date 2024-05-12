import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { constantes } from 'src/environment/constantes';
import { Configuracion } from 'src/app/models/modelo.configuracion';
import { ConfiguracionesService } from 'src/app/services/configuraciones.service';

@Component({
  selector: 'app-item-configuracion',
  templateUrl: './item-configuracion.component.html',
  styleUrls: ['./item-configuracion.component.css']
})

export class ItemConfiguracionComponent {
  editarItemConfiguracionForm!: FormGroup;
  editando?: Configuracion

  @Input() configuracion?: Configuracion;
  @Input() odd: boolean;
  @Output() refrescar: EventEmitter<any> = new EventEmitter<any>();

  constructor(private formBuilder: FormBuilder, private configuracionesService: ConfiguracionesService) {
    this.editando = undefined;
    this.configuracion = undefined;
    this.odd = false;
  }

  ngOnInit(): void {
    this.editarItemConfiguracionForm = this.formBuilder.group({
      nuevoNombre: ["", [Validators.required, Validators.minLength(constantes.MINIMO_NOMBRE_CONFIGURACION), Validators.maxLength(constantes.MAXIMO_NOMBRE_CONFIGURACION)]],
      nuevoValor: ["", [Validators.required, Validators.minLength(constantes.MINIMO_VALOR_CONFIGURACION), Validators.maxLength(constantes.MAXIMO_VALOR_CONFIGURACION)]]
    })
  }

  get nuevoNombre() { return this.editarItemConfiguracionForm.get('nuevoNombre'); }

  get nuevoValor() { return this.editarItemConfiguracionForm.get('nuevoValor'); }

  editar(configuracion: Configuracion) {
    this.editarItemConfiguracionForm.get("nuevoNombre")?.setValue(configuracion.nombre);
    this.editarItemConfiguracionForm.get("nuevoValor")?.setValue(configuracion.valor);
    this.editando = configuracion;
  }

  borrar(configuracion: Configuracion) {
    this.configuracionesService.borrar(configuracion)
      .subscribe((_: any) => this.refrescar.emit());
  }

  grabar(configuracion: Configuracion, value: any) {
    this.configuracionesService.modificar(configuracion, value.nuevoNombre, value.nuevoValor)
      .subscribe((nuevaConfiguracion: Configuracion) => {
        this.editando = undefined;
        this.refrescar.emit();
        this.configuracionesService.cambioConfiguracion(nuevaConfiguracion);
    });
  }

  cancelar(configuracion: Configuracion) {
    this.editando = undefined;
  }
}
