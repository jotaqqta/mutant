# MutantAdn-api
API RESTful solicitada por Magneto para que detecte si un humano es mutante basándose en su secuencia de ADN.

En esta API Magneto mediante Autenticación JWT podrá:
- Detectar si una secuencia de ADN corresponde o no a un Mutante y almacenar el resultado.
- Consultar las estadísticas de sus registros de secuencia de ADN.

## Estructura del proyecto
```
mutantdna-spring-boot-jwt/
 │
 ├── src/main/java/com/dna/
 │   └── app
 │       ├── config
 │       │   └── SwaggerConfiguration.java
 │       │
 │       ├── controller
 │       │   └── MutantController.java
 │       │   └── StatsController.java
 │       │   └── UsuarioController.java
 │       │   ├── model
 │       │       └── DnaDto.java
 │       │       └── LoginUserDto.java
 │       │       └── TokenDto.java
 │       │
 │       ├── repository
 │       │   └── IMutantDnaRepository.java
 │       │   └── IUserRepository.java
 │       │   ├── model
 │       │       └── CountDna.java
 │       │       └── Erol.java
 │       │       └── MutantDna.java
 │       │       └── MutantStats.java
 │       │       └── Rol.java
 │       │       └── Rol.java
 │       │
 │       ├── security
 │       │   └── SecurityConguration.java
 │       │   ├── jwtokens
 │       │       └── EntryPoint.java
 │       │       └── ProviderToken.java
 │       │       └── ValidateToken.java
 │       │       └── UserAuthorized.java 
 │       │
 │       ├── service
 │       │   └── IDnaService.java
 │       │   └── IMutantService.java
 │       │   └── IUserService.java
 │       │   ├── impl
 │       │       └── DnaServiceImpl.java
 │       │       └── MutantServiceImpl.java
 │       │       └── UserDetailsServiceImpl.java
 │       │       └── UserServiceImpl.java
 │       │
 │       ├── util
 │       │   └── ErrorMessage.java
 │       │   └── Logs.java
 │       │   └── ResponseMessage.java 
 │       │
 │       └── MutantApplication.java
 │
 ├── src/main/resources/
 │   │   └── dnaTest.csv
 │
 ├── src/test/java/com/dna
 │   └── app
 │       ├── controller
 │       │   └── MutantControllerTest.java 
 │       │   └── StatsControllerTest.java 
 │       ├── repository
 │       │   └── MutantStatsTest.java
 │       ├── service 
 │       │   └── DnaServiceTest.java
 │       │   └── MutantServiceTest.java
 │       │   └── ObjectProvider.java 
 │       │   
 │       MutantApplicationTests
 │       │    
 ├──
 ├── Dokerfile
 ├── .gitignore
 ├── mvnw/mvnw.cmd
 ├── README.md
 └── pom.xml
```

## Comandos para ejecucion
Compilacion de artefacto, el cual queda ubicado en la carpeta target
- mvn package     [Genera el jar mutantdna0.0.1-SNAPSHOT.jar ]
- docker build -t mutantdna-docker .  [Genera la imagen basada en jdk 8]
- docker run -p 8080:8090 mutantdna-docker [Corre la imagen en el puerto 8090]

## Funcionamiento de la API
Para acceder a la documentación del API mutantdna por favor utilice el siguiente link: 
<a href="https://mutantjota.herokuapp.com/swagger-ui.html">Documentacion API wishlist</a>

Para acceder al repositorio del proyecto se debe solicitar permiso al rpositorio provado en Github link: 
<a href="https://github.com/jotaqqta/mutant">Repositorio Git API wishlist</a>

### Autenticacion Bearer Token
Peticion Post usuarios/login por favor revisa la documentacion en swagger 

## License

**Free Software**
