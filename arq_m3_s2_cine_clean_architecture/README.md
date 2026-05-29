#  Sistema de Reserva de Cine - Clean Architecture + DDD 

Sistema de Reserva de Cine que implementa **Domain-Driven Design (DDD)** y **Clean Architecture** con Spring Boot

## Funcionalidades

- Gestion de Funciones
    Funcion Creada
    Consultar Funciones

- Gestion de Reservas
- Gestion de Clientes
- Gestion de Pagos
- Notificacionesa

## Estructura del Proyecto

src
└── main
└── java
└── com
└── banco

                ├── CineDigitalApplication.java

                ├── accounts
                │
                │   ├── application
                │   │
                │   │   ├── dto
                │   │   │
                │   │   │   ├── CrearFuncionCommand.java
                │   │   │   ├── FuncionDisponibleResponse.java
                │   │   │   ├── AsientoDisponibleResponse.java
                │   │   │   ├── CrearReservaCommand.java
                │   │   │   ├── ReservaResponse.java
                │   │   │   └── CancelarReservaCommand.java
                │   │   │
                │   │   ├── port
                │   │   │
                │   │   │   └── output
                │   │   │       └── NotificationPort.java
                │   │   │
                │   │   └── usecase
                │   │
                │   │       ├── CrearFuncionUseCase.java
                │   │       ├── ConsultarFuncionesDisponiblesUseCase.java
                │   │       ├── ConsultarAsientosDisponiblesUseCase.java
                │   │       ├── CrearReservaUseCase.java
                │   │       ├── CancelarReservaUseCase.java
                │   │       └── ConfirmarReservaUseCase.java
                │   │
                │   ├── domain
                │   │
                │   │   ├── exception
                │   │   │
                │   │   │   ├── DomainException.java
                │   │   │   ├── SalaNoEncontradaException.java
                │   │   │   ├── PeliculaNoEncontradaException.java
                │   │   │   ├── FuncionNoEncontradaException.java
                │   │   │   ├── FuncionDuplicadaException.java
                │   │   │   ├── HorarioFuncionInvalidoException.java
                │   │   │   ├── SalaSinCapacidadException.java
                │   │   │   ├── PrecioFuncionInvalidoException.java
                │   │   │   ├── ReservaNoEncontradaException.java
                │   │   │   ├── AsientoNoDisponibleException.java
                │   │   │   ├── FuncionCanceladaException.java
                │   │   │   ├── FechaFuncionPasadaException.java
                │   │   │   └── CapacidadSalaExcedidaException.java
                │   │   │
                │   │   ├── model
                │   │   │
                │   │   │   ├── FuncionCine.java
                │   │   │   ├── Reserva.java
                │   │   │   ├── Sala.java
                │   │   │   ├── Pelicula.java
                │   │   │   ├── EstadoFuncion.java
                │   │   │   ├── EstadoReserva.java
                │   │   │   ├── TipoFuncion.java
                │   │   │   └── TipoAsiento.java
                │   │   │
                │   │   └── repository
                │   │
                │   │       ├── FuncionRepository.java
                │   │       ├── SalaRepository.java
                │   │       ├── PeliculaRepository.java
                │   │       └── ReservaRepository.java
                │   │
                │   ├── infrastructure
                │   │
                │   │   ├── notification
                │   │   │
                │   │   │   ├── ConsoleNotificationAdapter.java
                │   │   │   └── EmailNotificationAdapter.java
                │   │   │
                │   │   ├── persistence
                │   │   │
                │   │   │   ├── adapter
                │   │   │   │
                │   │   │   │   ├── FuncionRepositoryAdapter.java
                │   │   │   │   ├── SalaRepositoryAdapter.java
                │   │   │   │   ├── PeliculaRepositoryAdapter.java
                │   │   │   │   └── ReservaRepositoryAdapter.java
                │   │   │   │
                │   │   │   ├── entity
                │   │   │   │
                │   │   │   │   ├── FuncionCineEntity.java
                │   │   │   │   ├── SalaEntity.java
                │   │   │   │   ├── PeliculaEntity.java
                │   │   │   │   └── ReservaEntity.java
                │   │   │   │
                │   │   │   ├── mapper
                │   │   │   │
                │   │   │   │   ├── FuncionMapper.java
                │   │   │   │   ├── SalaMapper.java
                │   │   │   │   ├── PeliculaMapper.java
                │   │   │   │   └── ReservaMapper.java
                │   │   │   │
                │   │   │   └── repository
                │   │   │
                │   │   │       ├── JpaFuncionRepository.java
                │   │   │       ├── JpaSalaRepository.java
                │   │   │       ├── JpaPeliculaRepository.java
                │   │   │       └── JpaReservaRepository.java
                │   │   │
                │   │   └── web
                │   │
                │   │       ├── controller
                │   │       │
                │   │       │   ├── FuncionController.java
                │   │       │   └── ReservaController.java
                │   │       │
                │   │       └── dto
                │   │
                │   │           ├── CrearFuncionRequest.java
                │   │           ├── FuncionResponse.java
                │   │           ├── CrearReservaRequest.java
                │   │           ├── ReservaResponse.java
                │   │           ├── AsientoDisponibleDto.java
                │   │           └── ErrorResponse.java
                │   │
                │   └── README.md
                │
                └── shared
                    │
                    ├── domain
                    │
                    │   └── exception
                    │
                    │       ├── GlobalExceptionHandler.java
                    │       └── ApiErrorResponse.java
                    │
                    ├── infrastructure
                    │
                    │   └── config
                    │
                    │       ├── BeanConfiguration.java
                    │       ├── OpenApiConfiguration.java
                    │       └── H2Configuration.java
                    │
                    └── README.md