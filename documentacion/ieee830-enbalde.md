---

# Especificación de requisitos de software

<p style="text-align: right" >Proyecto Final: "Enbalde"
</p>

---

<p style="text-align: right">
Año: 2024
</p>

---

### Ficha del Documento

| Fecha | Revisión | Autor | Verificado dep. Calidad
| - | - | - | - |
| 240427 | | Pamela Sol Pilotti - Lidio A. Guedez - Yuliana Paula Capdevila - Leonardo Vivas - Lucia Ailen Leonetti - Roberto Alfonso - Facundo M. Díaz C. | |
| 240513 | | Idem | Actualización Sprint 1 |

---

## Contenido

- [1 - Introducción](#1-introducción)

  - [1.1 - Propósito](#11-propósito)

  - [1.2 - Alcance](#12-alcance)

  - [1.3 - Personal Involucrado](#13-personal-involucrado)

  - [1.4 - Definiciones, acrónimos y abreviaturas](#14-definiciones-acrónimos-y-abreviaturas)

  - [1.5 - Referencias](#15-referencias)

  - [1.6 - Resumen](#16-resumen)

- [2 - Descripción General](#2-descripción-general)

  - [2.1 - Perspectiva del producto](#21-perspectiva-del-producto)

  - [2.2 - Características de los usuarios](#22-características-de-los-usuarios)

  - [2.3 - Restricciones](#23-restricciones)

  - [2.4 - Suposiciones y dependencias](#24-suposiciones-y-dependencias)

- [3. Requisitos Específicos](#3-requisitos-específicos)

  - [3.1 - Product Backlog](#31-product-backlog)

    - [3.1.1 - Historias de usuarios y sus tareas](#311-historias-de-usuarios-y-sus-tareas)

    - [3.1.2 - Requerimientos Funcionales](#312-requerimientos-funcionales)

    - [3.1.3 - Requerimientos No Funcionales*](#313-requerimientos-no-funcionales)

    - [3.1.4 - Sprints](#314-sprints)

- [4. Anexo de bitácora](#4-anexo-de-bitácora)

  - [4.1 Sprint 0](#41-sprint-0)

  - [4.2 Sprint 1](#42-sprint-1)

---

### 1 Introducción
Este documento es una Especificación de Requisitos Software (ERS) para la aplicación **"Enbalde - Web y Mobile"** para la gestión de procesos y control de inventarios de una heladería, permitiendo a su vez el registro de usuario y compras mediante la interfaz dada. Esta especificación se ha estructurado basándose en las directrices dadas por el estándar IEEE Práctica Recomendada para Especificaciones de Requisitos Software ANSI/IEEE 830, 1998.

#### 1.1 Propósito
Definir las especificaciones funcionales para el desarrollo de un sistema de gestión web/mobile que permitirá al usuario (administrador, [ver Definiciones, acrónimos y abreviaturas](#14-definiciones-acrónimos-y-abreviaturas)) tener conocimiento de cuales son los pedidos, acceder al historial de ventas, dar de alta productos y poner en oferta ciertos productos por el tiempo que el mismo considere necesario.

A su vez, permitirá al usuario (cliente, [ver Definiciones, acrónimos y abreviaturas](#14-definiciones-acrónimos-y-abreviaturas)) registrarse en el sitio, navegar por un catálogo, seleccionar productos para realizar el pedido y elegir la forma de pago deseada.

#### 1.2 Alcance
Enbalde está dirigido a empresas que desean llevar un control de stock y administrar sus ventas mediante el software ofrecido. Al mismo tiempo, creando un lugar donde sus clientes pueden conectar con la marca en mayor profundidad y realizar compras.

La posibilidad de capacitar a sus empleados para el manejo del sistema y llevar un registro claro y completo de los servicios.

Aquellos que requieran capacitarse en el uso de las plataformas ofrecidas no necesitarán poseer conocimiento previo sobre las herramientas de desarrollo.

#### 1.3 Personal involucrado

| Nombre | Rol | Categoria Profesional | Responsabilidad | Información de contacto
| - | - | - | - | - |
| Pamela Pilotti| Desarrolladora / Scrum Master | FullStack | Backend - Frontend - Base de Datos - API REST - Mobile | pamelasol13@hotmail.com |
| Leonardo Vivas | Desarrollador | FullStack | Backend - Frontend - Base de Datos - API REST - Mobile | leonardoa173@gmail.com |
| Lucia Ailen Leonetti | Desarrolladora | FullStack | Backend - Frontend - Base de Datos - API REST - Mobile | leonettil152@gmail.com |
| Yuliana Paula Capdevila | Desarrolladora | FullStack | Backend - Frontend - Base de Datos - API REST - Mobile | yulicapdevila92@gmail.com |
| Lidio A. Guedez | Desarrollador | FullStack | Backend - Frontend - Base de Datos - API REST - Mobile | g.abelardo@gmail.com |
| Roberto Alfonso | Desarrollador | FullStack | Backend - Frontend - Base de Datos - API REST - Mobile | rpgrca@gmail.com |
| Facundo Manuel Díaz Córdoba | Desarrollador | FullStack | Backend - Frontend - Base de Datos - API REST - Mobile | facudiaz1738@hotmail.com |

#### 1.4 Definiciones, acrónimos y abreviaturas

| Nombre | Descripción |
| - | - |
| QA | *Question/Answer* |
| Administrador | Persona que utilizará el sistema para gestionar altas, bajas, modificaciones y consultas de productos |
| Cliente | Persona que utilizará el sistema para realizar compras de los productos |
| *Product Backlog* | Pila de Producto |
| CRUD / ABMC | *create read update delete* / Alta, Baja, Modificación y Consulta |
| Artículo/Producto | Un artículo representa un objeto presentado por el Administrador, ingresado al sistema para su venta (Stock) |
| Oferta | Artículos puestos a un precio menor por tiempo determinado |
| Venta | Transacción por la cual el cliente, luego de elegir el artículo, confirmar el pedido y realizar el pago |
| Mobile | Dispositivo Telefónico |
| Login | Iniciar Sesión |
| SQL | Base de Datos Relacional |
| DB | *Database* / Base de Datos |
| DER | Diagrama Entidad Relación |
| *Branch* | Rama |
| GIT | Controlador de Versiones |
| POO | Programación Orientada a Objetos |
| *Project* | Proyecto |
| *Activity* | Actividad (Pantalla) |

#### 1.5 Referencias

| Título del Documento | Referencia |
| - | - |
| Standard IEEE 830 - 1998 | IEEE |

#### 1.6 Resumen

"Enbalde" busca facilitar el control y administración de pedidos, historial de ventas, stock y ofertas por parte de usuarios administradores. También permite el registro de clientes, su navegación por el catálogo, selección y compra de productos, mediante el medio de pago deseado por el mismo.

Este documento consta de 3 (tres) secciones. Esta sección es la Introducción y proporciona una visión general de la ERS. En la sección 2 se da una descripción general del sistema, con el fin de conocer la perspectiva del producto, las características de los usuarios, restricciones, supuestos y dependencias que afectan al desarrollo, sin entrar en excesivos detalles. En la sección 3 se definen los requisitos específicos que debe satisfacer el sistema, detalle de historias de usuarios (US) y de los diferentes Sprint.

---

### 2 Descripción General

#### 2.1 Perspectiva del producto
El sistema Enbalde será un producto diseñado para trabajar tanto en entornos web como mobile, de utilización rápida y eficaz. Contendrá alta, baja, modificacion y consulta de productos, registro de ventas, autenticación de usuarios y pasarela de pagos.

![image](https://github.com/enbalde-ispc/enbalde-ispc/assets/15602473/a3fad65d-75b8-4368-b1df-9f211e177077)

#### 2.2 Características de los usuarios

| Tipo de usuario | Formación | Actividades |
| - | - | - |
| Administrador | Manejo básico de herramientas informáticas | Control y manejo del sistema en general |
| Cliente | Manejo y acceso a entornos web y mobile | Compras en cualquier volumen |
| Visitante | Acceso a entornos web y mobile | Navegación básica de la interfaz |

#### 2.3 Restricciones

- Interfaces para ser usadas con internet (solamente).
- Lenguajes y tecnologías en uso: Angular, Django/Python, Android, Java, MySQL y/o Firebase.
- El sistema se diseñará según un modelo cliente/servidor.
- El sistema deberá tener un diseño e implementación sencillos.
- Límite de tiempo para su desarrollo.
- No se contemplan operaciones paralelas por fuera de las descriptas en el sistema.
- El sistema debe adecuarse a lo que el cliente solicita.

#### 2.4 Suposiciones y Dependencias

- Se asume que los requisitos aquí descritos son estables.
- Los equipos en los que se vaya a ejecutar el sistema deben cumplir los requisitos antes indicados para garantizar una ejecución correcta de la misma, suponiendo continua comunicación y colaboración en pos de crear robustez, escalabilidad y mantenibilidad.

---

### 3 Requisitos específicos

#### 3.1 Product Backlog

##### 3.1.1 Historias de Usuarios y sus Tareas

- US/TK’s - Sprint 0

  - #US00 Conformar grupo definiendo organización de repositorio en GitHub
    - #TK32 Actualizar el documento IEEE830
    - #TK33 Organizacion del respositorio y wiki
    - #TK34 Definir roles en el equipo
    - #TK35 Crear historia de usuarios

- US/TK’s - Sprint 1
  - #US22 Como cliente quiero poder utilizar un sistema de pagos externo
    - #TK85 Implementar sistema de pago Stripe
  - #US23 Como administrador quisiera configurar la aplicación
    - #TK82 Agregar pantalla de configuración
  - Arreglos:
    #TK56 Consolidar look & feel del sitio web
    #TK57 Subir portada al README
    #TK59 Crear script de Postman para probar la registración
    #TK83 Actualizar diagramas
    #TK84 Actualizar diagrama de entidad relación
    #TK86 Actualizar el documento IEEE830

##### 3.1.2 Requerimientos Funcionales

| Identificación del Requerimiento | Nombre del Requerimiento | Características | Descripción del Requerimiento | Requerimiento No Funcional | Prioridad del Requerimiento |
| - | - | - | - | - | - |
| RF01 | Catálogo de Productos | Para evaluar opciones | Sección del stock disponible | RNF01 - RNF03 - RNF04 | Alta |
| RF02 | Login/Registro de Usuarios | Posibilitar acceso y compras en la plataforma | Formulario de Login/Registro | RNF03 - RNF05 | Alta |
| RF03 | Creación de ofertas | Beneficios al usuario | Permite crear ofertas por tiempo determinado | RNF01 - RNF04 | Alta |
| RF04 | Perfil | Información del Usuario | Para su acceso y modificación | RNF05 | Media/Alta
| RF05 | Detalles del Producto | Información del producto | Para que el usuario sepa que esta comprando | RNF01 - RNF04 | Alta |
| RF06 | Compra del Productos | Facilidad de pago por medio de pago deseado | Permite al usuario adquirir productos una vez elegidos | RNF01 - RNF04 | Alta |
| RF07 | Registro de Ventas | Llevar contabilidad de las ventas realizadas | Permite saber que productos tienen mayor demanda | RNF03 - RNF02 | Alta |

##### 3.1.3 Requerimientos No Funcionales

| Identificación del Requerimiento | Nombre del Requerimiento | Características | Descripción del Requerimiento | Prioridad del Requerimiento |
| - | - | - | - | - |
| RNF01 | Confiabilidad en el Sistema | Manejo adecuado de errores de red y caídas del API | Evitar disgustos al usuario | Media |
| RNF02 | Mantenimiento | Usar las prácticas correctas y testing | Facilitar los futuros cambios | Alta |
| RNF03 | Diseño UI/UX | Facilitar la navegación al usuario | Que la interfaz sea sencilla | Media |
| RNF04 | Desempeño | El sistema debe reaccionar bien en las llamadas a la API | Evitar que largos tiempos de espera | Alta |
| RNF05 | Seguridad de la Información | Proteger información sensible | La información del usuario no debe verse comprometida | Alta |

##### 3.1.4 Sprints

| Nro de sprint | Calendario | Sprint Backlog | Responsabilidades | Inconvenientes: |
| - | - | - | - | - |
| 0 | Fecha de inicio: 11/04/2024 (Aproximadamente) / Fecha Cierre: 28/04/2024 | #US00, #TK32, #TK33, #TK34, #TK35 | - Definir los roles de los miembros del equipo Scrum (Scrum master y Developer team). Documentar en la Wiki del Proyecto - Evaluar el contenido y distribución previa de la tienda para que se adecue al nuevo desarrollo de e-commerce / carrito de compras, analizando los requerimientos necesarios. Lo mismo ocurrirá con la App - Plantear Historias de Usuarios en base a los requerimientos del proyecto teniendo en cuenta una redacción y nomenclatura adecuada, ej “#US01 Como usuario quiero registrarme en el sitio web para comprar uno o varios productos” y su respectivos criterios de aceptación. Pueden utilizar la siguiente plantilla: Plantilla Historias de Usuario - Definir la documentación del proyecto mediante el documento IEEE830 - Crear un proyecto estilo kanban en Github - Definir la estructura de páginas en la Wiki del repositorio en github a fin de poder documentar: Nombre y apellido de los integrantes del equipo como así también los roles de cada quién, registro de ceremonias de scrum: planning, review y retrospective (para esta última es importante publicar además el plan de mejora a ejecutar en la siguiente iteración), documento IEEE830 del proyecto | problemas con la accesibilidad y el funcionamiento de la versión web de del sistema planteado en un principio y la dificultad que supone el mejorar dicho producto, además los reducidos tiempo llevaron a la necesidad de cambiar de dirección y trabajar sobre otra idea que nos permitiera cumplir con los requisitos solicitados por el cliente |
| 1 | Fecha de inicio: 28/04/2024 (Aproximadamente) / Fecha Cierre: 13/05/2024 | #US22, #US23, #TK56, #TK57, #TK59, #TK82, #TK83, #TK84, #TK85, #TK86 | Se actualiza logo de la aplicación. Crear script de Postman para probar registración. Actualizar script de inicialización de base de datos. Implementar sistema de pago Stripe. Actualizar diagrama de entidad-relación, diagrama relacional y diagrama de clases. Implementación de configuración del sistema. Creación de archivo para pruebas de registración en Postman. | Problemas con la accesibilidad y el funcionamiento de la versión web de del sistema planteado en un principio y la dificultad que supone el mejorar dicho producto. Debido al corto plazo se implementaron únicamente dos mejoras superadoras ya que se tuvo que avanzar con la aplicación mobile al mismo tiempo. Actualizar documentación IEEE830. |


### 4. Anexo de Bitácora

#### 4.1 Sprint 0

El cliente ha pedido conexión tanto de la versión web como mobile del sistema. Se ha decidido cambiar de producto, y no continuar con BootcampAr (Sistema de venta de cursos) debido a las limitaciones de tiempo para corregir la parte web. Es por ello que se ha propuesto Enbalde (Sistema de venta para heladerías) como la nueva alternativa a seguir, dejando mayor margen para realizar la parte mobile desde 0 (cero) y asi poder llegar con los tiempos para la entrega final.

### 4.2 Sprint 1

Nuevos features para la aplicación web:

Se agregó stripe al sistema de pago, para las compras dentro del sistema por parte del usuario:

![image](https://github.com/enbalde-ispc/enbalde-ispc/assets/95236196/3c95711d-f5bb-427f-920d-8656edf05b36)
![image](https://github.com/enbalde-ispc/enbalde-ispc/assets/95236196/d6d48b74-0aa8-4f63-95d7-60d5956738b9)

Por otro lado, se ha incluido un nuevo feature para el sistema de administrador, mediante el cual puede personalizar el sitio (Cambiar logo, título y descripcion principal e información de contacto):

![image](https://github.com/enbalde-ispc/enbalde-ispc/assets/15602473/f8fb14de-3c54-4d9e-903f-b3bd8e46bb67)

A su vez, se han actualizado los diagramas y la base de datos para que incluyeran las mejoras añadidas.

Se continúa trabajando en el look and feel de la página web, y en cuanto a la versión móvil, se han creado algunas pantallas (login, registro, perfil, catalogo) y se conecta a la base de datos que utiliza el backend de django.

![image](https://github.com/enbalde-ispc/enbalde-ispc/assets/95236196/d03dea73-c685-4acb-9ea0-daa8d9564387)

![image](https://github.com/enbalde-ispc/enbalde-ispc/assets/95236196/74c6a0fd-194e-417b-b759-0bed84299796)
