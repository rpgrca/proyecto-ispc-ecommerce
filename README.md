
![70747abe-f397-402e-8f14-fbf12301cf22](https://github.com/enbalde-ispc/enbalde-ispc/assets/90878370/18b9ad44-3ed2-4169-97cb-81def6c67268)


# EnBalde - Heladería 🍨

## Descripción
EnBalde es una plataforma para la venta de helados en pote de diversos pesos y sabores. El usuario tendrá la posibilidad, mediante un catálogo, de elegir helados por pote de diversos pesos. Habrá variedad de gustos. Cada pote será de sabores fijos. Una vez finalizada la elección la página lo dirigirá a la sección carrito en donde podrá agregar o quitar artículos, decidir la forma de abonar y si solicita delivery o retira en sucursal. Concluida la compra se emitirá un aviso de transacción efectuada y el administrador recibirá el aviso de la compra.

## Nombre de los integrantes: 
- Pamela Sol Pilotti
- Leonardo Daniel Vivas
- Roberto Alfonso
- Lucia Ailen Leonetti
- Facundo Manuel Díaz
- Yuliana Paula Capdevilla
- Lidio Guedez


## Emblemas
[![Contribuidores][contrib-img]][contrib-url]
[![Actividad de commits][commit-img]][commit-url]
[![Discusiones][discuss-img]][discuss-url]
[![Issues][issues-img]][issues-url]
[![Paquete Python][pipeline-img]][pipeline-url]

## Modo de Uso

### Requisitos
El proyecto tiene componentes web y móvil, divididos en:

- **Frontend Angular** (`front-end/enbalde`): Corre en el puerto 4200.
- **Backend Django** (`back-end/django`): Corre en el puerto 8000.
- **Servicio de Pago Node** (`front-end/enbaldePago`): Corre en el puerto 3000.

### Instalación y Ejecución
1. **Frontend Angular**:
   - `cd front-end/enbalde`
   - `npm install`
   - `ng serve`
2. **Servicio de Pago**:
   - `cd front-end/enbaldePago`
   - `npm install`
   - `npm start`
3. **Backend Django**:
   - `cd back-end/django`
   - `pip install -r requirements.txt`
   - Configurar MySQL y ajustes en `server/settings.py`
   - Ejecutar migraciones `python manage.py migrate`
   - Ejecutar servidor con `python manage.py runserver`

Acceder al sitio en `http://localhost:4200`.

## Documentación Adicional
- [IEEE830](https://github.com/enbalde-ispc/enbalde-ispc/wiki/Documento-IEEE830)
- [Diagrama de casos de uso](https://github.com/enbalde-ispc/enbalde-ispc/wiki/Diagramas-de-caso-de-uso)
- [Diagrama de clases](https://github.com/enbalde-ispc/enbalde-ispc/wiki/Diagrama-de-clases)
- [Diagrama Entidad Relación](https://github.com/enbalde-ispc/enbalde-ispc/wiki/Diagrama-Entidad-Relación)
- [Diagrama Relacional](https://github.com/enbalde-ispc/enbalde-ispc/wiki/Diagrama-Relacional)
- [Mapa del sitio](https://github.com/enbalde-ispc/enbalde-ispc/wiki/Mapa-del-sitio)

<!-- Emblemas -->
[contrib-img]: https://img.shields.io/github/contributors/enbalde-ispc/enbalde-ispc
[contrib-url]: https://github.com/NataliaAlvarezIspc/proyecto-ispc-ecommerce/graphs/contributors
[commit-img]: https://img.shields.io/github/commit-activity/w/enbalde-ispc/enbalde-ispc/dev
[commit-url]: https://github.com/enbalde-ispc/enbalde-ispc/graphs/code-frequency
[issues-img]: https://img.shields.io/github/issues/enbalde-ispc/enbalde-ispc
[issues-url]: https://github.com/enbalde-ispc/enbalde-ispc/issues
[discuss-img]: https://img.shields.io/github/discussions/enbalde-ispc/enbalde-ispc
[discuss-url]: https://github.com/enbalde-ispc/enbalde-ispc/discussions
[pipeline-img]: https://github.com/rpgrca/proyecto-ispc-ecommerce/actions/workflows/python.yml/badge.svg
[pipeline-url]: https://github.com/rpgrca/proyecto-ispc-ecommerce/actions/workflows/python.yml
