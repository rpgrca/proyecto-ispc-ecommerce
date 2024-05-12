import { Component, OnInit } from '@angular/core';
import { Configuracion } from 'src/app/models/modelo.configuracion';
import { ConfiguracionesService } from 'src/app/services/configuraciones.service';
import { HomeService } from 'src/app/services/home.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})

export class HomeComponent implements OnInit {
  itemCarrusel1 = '../assets/img/heladoCarousel.jpg';
  itemCarrusel2 = '../assets/img/heladoCarousel1.jpg';
  itemCarrusel3 = '../assets/img/heladoCarousel2.jpg';
  itemFernet = '../assets/img/Fernet.jpg';
  itemCaramelo = '../assets/img/Caramelo.jpg';
  itemPritiado = '../assets/img/Pritiado.jpg';
  itemDarkChocolate = '../assets/img/Dark_Chocolate.jpg';

  data: any[] = [];
  titulo: string = '';
  descripcion: string = '';

  constructor(private apiService: HomeService, private configuracionesService: ConfiguracionesService) { }

  ngOnInit(): void {
    this.llenarData();

    this.configuracionesService.obtenerConfiguraciones()
      .subscribe((configuraciones: Configuracion[]) => {
        let c: { [id: string] : string } = {};
        configuraciones.forEach((el, index) => c[el.nombre] = el.valor);

        this.titulo = c["titulo"];
        this.descripcion = c["descripcion"].replaceAll("{{titulo}}", this.titulo);
      });
  }

  llenarData() {
    this.apiService.getData().subscribe(response => {
      this.data = response.results;
    });
  }
}
