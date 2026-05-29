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

├── accounts
│   │
│   ├── application
│   │   │
│   │   ├── dto
│   │   │   ├── AsientoDisponibleResponse.java
│   │   │   └── CrearFuncionCommand.java
│   │   │
│   │   ├── port
│   │   │   └── output
│   │   │       └── NotificationPort.java
│   │   │
│   │   └── usecase
│   │       ├── ConsultarFuncionesDisponiblesUseCase.java
│   │       └── CrearFuncionUseCase.java
│   │
│   ├── domain
│   │   │
│   │   ├── exception
│   │   │   ├── PeliculaNoEncontradaException.java
│   │   │   └── SalaNoEncontradaException.java
│   │   │
│   │   ├── model
│   │   │   ├── EstadoFuncion.java
│   │   │   ├── FuncionCine.java
│   │   │   └── TipoFuncion.java
│   │   │
│   │   └── repository
│   │       ├── FuncionRepository.java
│   │       ├── PeliculaRepository.java
│   │       └── SalaRepository.java
│   │
│   ├── infrastructure
│   │   │
│   │   ├── notification
│   │   │   └── ConsoleNotificationAdapter.java
│   │   │
│   │   ├── persistence
│   │   │   │
│   │   │   ├── adapter
│   │   │   │   ├── FuncionRepositoryAdapter.java
│   │   │   │   ├── PeliculaRepositoryAdapter.java
│   │   │   │   └── SalaRepositoryAdapter.java
│   │   │   │
│   │   │   ├── entity
│   │   │   │   ├── FuncionCineEntity.java
│   │   │   │   ├── PeliculaEntity.java
│   │   │   │   └── SalaEntity.java
│   │   │   │
│   │   │   ├── mapper
│   │   │   │   └── FuncionMapper.java
│   │   │   │
│   │   │   └── repository
│   │   │       ├── JpaFuncionRepository.java
│   │   │       ├── JpaPeliculaRepository.java
│   │   │       └── JpaSalaRepository.java
│   │   │
│   │   └── web
│   │       │
│   │       ├── controller
│   │       │   └── FuncionController.java
│   │       │
│   │       └── dto
│   │           ├── CrearFuncionRequest.java
│   │           └── FuncionResponse.java
│   │
│   └── README.md
│
└── shared
│
├── domain
│   └── exception
│       ├── DomainException.java
│       └── GlobalExceptionHandler.java
│
├── infrastructure
│   └── config
│       └── BeanConfiguration.java
│
└── README.md
