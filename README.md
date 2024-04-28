[![contrib][contrib-img]][contrib-url]
[![commit][commit-img]][commit-url]
[![discuss][discuss-img]][discuss-url]
[![issues][issues-img]][issues-url]
[![Python package][pipeline-img]][pipeline-url]

## EnBalde - Heladería

Venta de productos cerrados de Heladería. El usuario tendrá la posibilidad, mediante un catálogo, de elegir helados por pote de diversos pesos. Habrá variedad de gustos. Cada pote será de sabores fijos. Una vez finalizada la elección la página lo dirigirá a la sección carrito en donde podrá agregar o quitar artículos, decidir la forma de abonar y si solicita delivery o retira en sucursal. Concluida la compra se emitirá un aviso de transacción efectuada y el administrador recibirá el aviso de la compra.

## Modo de uso

El proyecto consta de una parte `Web` y otra `Mobile`

La parte Web esta dividida en tres partes:
- Una parte de front-end ubicada en el directorio _front-end/enbalde_ realizada en Typescript con el framework Angular que corre en el puerto 4200.
- Una parte de back-end ubicada en el directorio _back-end/django_ realizada en Python con el framework Django que corre en el puerto 8000.
- Un servicio de pago ubicado en el directorio _front-end/enbaldePago_ realizado en Javascript con el framework Node que corre en el puerto 3000.

Para poder instalar y ejecutar el sitio web es necesario seguir las instrucciones indicadas en los archivos README.md de cada uno de estos directorios. Simplificando:
- Ingresar al directorio _front-end/enbalde_, ejecutar _npm install_ para instalar las dependencias y luego _ng serve_ para levantar el servidor de Angular.
- Ingresar al directorio _front-end/enbaldePago_, ejecutar _npm install_ para instalar las dependencias y luego _npm start_ para levantar el servidor de pago.
- Ingresar al directorio _back-end/django_, ejecutar _pip install -r requirements.txt_ para instalar las dependencias, crear la base de datos en MySQL, configurar la cuenta en _back-end/django/server/settings.py_, correr las migraciones y finalmente ejecutar _python manage.py runserver_ para levantar el servidor de back-end.

Luego es posible utilizar el sitio navegando a http://localhost:4200.

Para instrucciones más precisas leer los archivos README de cada directorio.

## Documentación

* [IEEE830](https://github.com/enbalde-ispc/enbalde-ispc/wiki/Documento-IEEE830)
* [Diagrama de casos de uso](https://github.com/enbalde-ispc/enbalde-ispc/wiki/Diagramas-de-caso-de-uso)
* [Diagrama de clases](https://github.com/enbalde-ispc/enbalde-ispc/wiki/Diagrama-de-clases)
* [Diagrama Entidad Relación](https://github.com/enbalde-ispc/enbalde-ispc/wiki/Diagrama-Entidad-Relaci%C3%B3n)
* [Diagrama Relacional](https://github.com/enbalde-ispc/enbalde-ispc/wiki/Diagrama-Relacional)
* [Mapa del sitio](https://github.com/enbalde-ispc/enbalde-ispc/wiki/Mapa-del-sitio)


[commit-img]: https://img.shields.io/github/commit-activity/w/enbalde-ispc/enbalde-ispc/dev
[commit-url]: https://github.com/enbalde-ispc/enbalde-ispc/graphs/code-frequency
[contrib-img]: https://img.shields.io/github/contributors/enbalde-ispc/enbalde-ispc
[contrib-url]: https://github.com/NataliaAlvarezIspc/proyecto-ispc-ecommerce/graphs/contributors
[issues-img]: https://img.shields.io/github/issues/enbalde-ispc/enbalde-ispc
[issues-url]: https://github.com/enbalde-ispc/enbalde-ispc/issues
[discuss-img]: https://img.shields.io/github/discussions/enbalde-ispc/enbalde-ispc
[discuss-url]: https://github.com/enbalde-ispc/enbalde-ispc/discussions
[pipeline-img]: https://github.com/rpgrca/proyecto-ispc-ecommerce/actions/workflows/python.yml/badge.svg
[pipeline-url]: https://github.com/rpgrca/proyecto-ispc-ecommerce/actions/workflows/python.yml
