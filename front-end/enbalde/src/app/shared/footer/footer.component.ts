import { Component, ElementRef } from '@angular/core';
import { Configuracion } from 'src/app/models/modelo.configuracion';
import { ConfiguracionesService } from 'src/app/services/configuraciones.service';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent {

  itemEnbalde = '../assets/img/3-svg.png'
  instagram: string = '';
  instagramUrl: string = ''
  facebook: string = '';
  facebookUrl: string = '';
  whatsapp: string = '';

  constructor(private elementRef: ElementRef, private configuracionesService: ConfiguracionesService) {}


  ngOnInit(): void {
    this.configuracionesService.obtenerConfiguraciones()
      .subscribe((configuraciones: Configuracion[]) => {
        let c: { [id: string] : string } = {};
        configuraciones.forEach((el, index) => c[el.nombre] = el.valor);

        this.instagram = c['instagram'];
        this.instagramUrl = `https://www.instagram.com/${this.instagram}`;
        this.facebook = c['facebook'];
        this.facebookUrl = `https://www.facebook.com/${this.facebook}`;
        this.whatsapp = c['whatsapp'];
        this.itemEnbalde = c['logoFooter'];
      });
  }


  volverArriba() {
    this.elementRef.nativeElement.ownerDocument.documentElement.scrollTop = 0;
  }
}
