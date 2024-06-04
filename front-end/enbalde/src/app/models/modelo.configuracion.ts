export interface Configuracion {
  id: number;
  nombre: string;
  valor: string;
}

export class ConfiguracionDefault implements Configuracion {
  id = 1;
  nombre = "Default";
  valor = "Default";
}
