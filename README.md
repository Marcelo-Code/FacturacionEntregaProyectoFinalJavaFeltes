# Entrega Final Java, CODERHOUSE

## Introducción:

Este proyecto de gestión de autos está diseñado como una aplicación utilizando el framework Spring Boot, implementando el patrón de diseño Modelo-Vista-Controlador (MVC). Su objetivo es proporcionar una plataforma eficiente y organizada para la administración de vehículos, permitiendo al cliente gestionar información sobre autos, categorías y usuarios de manera intuitiva.

La aplicación está estructurada en tres capas:

- **Repository** : encargada de la interacción directa con la base de datos, proporcionando métodos para realizar operaciones CRUD sobre las entidades del sistema.
- **Service** : contiene la lógica de negocio, donde se procesan y validan los datos antes de ser enviados a la capa de presentación o almacenados en la base de datos.
- **Controller** : actúa como intermediario entre la vista y el modelo, recibiendo las solicitudes del cliente, delegando tareas a la capa de servicio y devolviendo las respuestas adecuadas.

Las entidades principales del proyecto incluyen `Auto`, que tiene relaciones `@ManyToOne` con la entidad `Categoría `y la entidad `Usuario`, lo que permite una organización jerárquica y modular de la información. Además, se ha implementado documentación de la API utilizando Swagger, facilitando la comprensión y uso de los endpoints disponibles.

Este proyecto busca no solo ser una herramienta funcional, sino también un ejemplo de buenas prácticas en el desarrollo de software, asegurando un código limpio, mantenible y escalable.

## Estructura de Carpetas:

- **ApiResponse:** contiene la clase `ApiResponse<T>` que es una estructura genérica utilizada para encapsular las respuestas de la API. Proporciona un mensaje descriptivo y un objeto de datos de tipo genérico, lo que facilita la comunicación clara de resultados y errores entre el servidor y el cliente.
- **EntregaFinalJava**:
  - **Config**: contiene las clases de configuración:
    - `OpenApiConfig`: es una configuración de Spring que define la documentación de la API utilizando Swagger/OpenAPI. A través del método `springShopOpenAPI()`, se crea un objeto `OpenAPI` que contiene metadatos sobre la API, como el título, la descripción, la versión y la información de la licencia. También se incluye documentación externa que enlaza a una wiki relacionada. Esta configuración permite generar automáticamente la documentación de la API, facilitando a los desarrolladores y usuarios comprender y utilizar los servicios proporcionados por la aplicación.
    - `RestTemplateConfig` es una configuración de Spring que define un bean de tipo `RestTemplate`, el cual se utiliza para realizar solicitudes HTTP a servicios externos. Al declarar el método `restTemplate()` como un bean, se permite que Spring gestione su ciclo de vida y la inyección de dependencias. Esta configuración facilita la comunicación con APIs externas, permitiendo a la aplicación consumir recursos de manera sencilla y eficiente.
  - **Controller**:
    - El package `controller` agrupa las clases que manejan las solicitudes HTTP relacionadas con las entidades `Usuario`, `Auto` y `Categoría`. Cada controlador está diseñado para gestionar las operaciones CRUD (Crear, Leer, Actualizar, Eliminar) y proporciona una interfaz para interactuar con los recursos de la aplicación. Los controladores se encargan de recibir las peticiones del cliente, delegar la lógica de negocio a los servicios correspondientes y devolver las respuestas adecuadas, facilitando así la comunicación entre el cliente y el servidor.
  - **DTO**:
    - El package `DTO `(Data Transfer Object) contiene clases que facilitan la transferencia de datos entre capas de la aplicación. Estos objetos permiten desacoplar la estructura de datos de la base de datos de la presentada al cliente, mejoran la claridad y eficiencia del intercambio de datos.
  - **Mapper**:
    - El package `Mapper` contiene clases responsables de convertir entre entidades del modelo de dominio y sus representaciones en DTO. Estas clases simplifican el proceso de transformación de datos, permitiendo una separación clara entre las distintas capas de la aplicación.
  - **Model**:
    - El package `Model` contiene las entidades que representan la estructura de datos de la aplicación. Estas clases definen los atributos y comportamientos de los objetos que interactúan con la base de datos, así como las relaciones entre ellos.
  - **Repository**:
    - El package `Repository` contiene interfaces que gestionan la persistencia de datos en la base de datos. Estas interfaces extienden de Spring Data JPA, proporcionando métodos para realizar operaciones CRUD (crear, leer, actualizar y eliminar) sobre las entidades del modelo.
  - **Service**:
    - El package `Service` contiene clases que implementan la lógica de negocio de la aplicación. Estas clases actúan como intermediarias entre los controladores y los repositorios, gestionando la comunicación entre ambas capas.
  - **Utils**:
    - La clase `ApiResponseMsg` encapsula un mensaje y un objeto de datos para las respuestas de la API.

## Descripción de funcionalidades:

- **Gestión de Autos** : permite registrar, modificar, eliminar y consultar autos en la base de datos. Los autos están asociados a usuarios y categorías, lo que facilita organizar y filtrar la información según marca o id.
- **Gestión de Usuarios** : proporciona servicios para manejar información de los usuarios que pueden estar asociados a varios autos. Esto permite conocer los autos que posee un usuario específico y su información de contacto.
- **Gestión de Categorías** : Las categorías permiten clasificar los autos, por ejemplo, en categorías como "SUV", "Sedán", "Deportivo", etc., facilitando consultas específicas.

Por defecto, la aplicación consta de 30 registros de autos pre cargados, los cuales estarán asignados a 10 categorías y 10 usuarios.

La entidad `Auto` consta de las propiedades `id` (será asignada automáticamente por el sistema), `marca`, `modelo`, `anio`, `precio`, `usuario` que define una relación `@ManyToOne` con la entidad `Usuario` y `categoria` que define una relación `@ManyToOne` con la entidad `Categoria`.

La entidad `Usuario` consta de las propiedades `id` (generada de manera automática) `nombre`, `email`, `telefono` y `autos` la cual consta de una lista de tipo `List<Auto>` que define una relación `@OneToMany` con la entidad `Auto`.

La entidad `Categoria` consta de las propiedades `id` (generada automáticamente), `nombre`, `descripcion` y `autos` la cual consta de una lista de tipo `List<Auto>` que define una relación `@OneToMany` con la entidad `Auto`.

Aspectos a tener en cuenta:

- Pueden consultarse y modificarse todos los registros disponibles.
- En caso de necesitar modificar la lista de autos correspondiente a un usuario, para quitarlo de la lista y asignar un auto en particular a otro usuario, se modificará el auto en cuestión cambiando el id del usuario.
- En caso de necesitar modificar la lista de autos correspondiente a una categoría, para quitarlo de la lista y asignar un auto en particular a otra categoría, se modificará el auto en cuestión cambiando el id de la categoría.

## Acceso a interfaz Swagger:

Para acceder a la interfaz de Swagger de la aplicación, una vez ejecutada la misma, debe accederse a la siguiente URL:

`http://localhost:8080/swagger-ui/index.html`

## Conclusión:

Este proyecto me ha permitido aprender el uso de la arquitectura modelo vista-controlador (MVC) y en la estructuración de aplicaciones de backend con Spring Boot. La implementación de relaciones entre entidades y el manejo de operaciones CRUD ha reforzado conceptos clave de diseño de bases de datos y controladores REST. Además, la integración de Swagger para la documentación de la API el uso de DTOs y mappers han facilitado una comprensión de buenas prácticas en la gestión y presentación de datos.
