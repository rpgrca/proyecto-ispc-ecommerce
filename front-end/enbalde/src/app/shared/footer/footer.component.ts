import { Component, ElementRef, Input } from '@angular/core';
import { filter, map } from 'rxjs';
import { Configuracion } from 'src/app/models/modelo.configuracion';
import { ConfiguracionesService } from 'src/app/services/configuraciones.service';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})

export class FooterComponent {
  @Input() itemFooter = '../assets/img/3-svg.png'
  instagram: string = '';
  instagramUrl: string = ''
  facebook: string = '';
  facebookUrl: string = '';
  whatsapp: string = '';

  constructor(private elementRef: ElementRef, private configuracionesService: ConfiguracionesService) {
     this.configuracionesService.estado
      .subscribe((c: Configuracion) =>
      {
        switch (c.nombre) {
          case 'logoFooter': this.itemFooter = c.valor; break;
          case 'instagram': this.instagram = c.valor; break;
          case 'facebook': this.facebook = c.valor; break;
          case 'whatsapp': this.whatsapp = c.valor; break;
        }
      });
  }


  ngOnInit(): void {
    this.configuracionesService.obtenerConfiguraciones()
      .subscribe((configuraciones: Configuracion[]) => {
        let c: { [id: string] : string } = {};
        configuraciones.forEach((el, index) => c[el.nombre] = el.valor);

        if ('instagram' in c) {
          this.instagram = c['instagram'];
          this.instagramUrl = `https://www.instagram.com/${this.instagram}`;
        }

        if ('facebook' in c) {
          this.facebook = c['facebook'];
          this.facebookUrl = `https://www.facebook.com/${this.facebook}`;
        }

        if ('whatsapp' in c) {
          this.whatsapp = c['whatsapp'];
          this.itemFooter = c['logoFooter'];
        }
      });
  }


  volverArriba() {
    this.elementRef.nativeElement.ownerDocument.documentElement.scrollTop = 0;
  }
}
