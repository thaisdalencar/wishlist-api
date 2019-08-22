# Wish list API

A `Wish List Api` trata-se de um serviço resposável por gerenciar os produtos favoritos de clientes. 

## Especificações
 
 O serviço disponibiliza uma API REST contendo:
 
 - Cadastro, edição, visualização e remoção de clientes;
 - Cadastro, visualização e remoção de produtos favoritos aos clientes;
 - Checagem por duplicação de email de cliente e produto favoritado;
 - Consulta da lista de clientes é realizada de forma paginada;
 - Consulta da lista de produtos favoritos de um cliente é realizada de forma paginada;
 - Consumo de uma `api de produtos` para buscar as informações de um dado produto;
 - Validação da existencia do produto antes de adiciona-lo a lista de favoritos;
 
 O serviço foi implementado utilizando:
 
 - java
 
## Tecnologias

 - [`Java`](https://www.java.com/download/) 
 - [`Spring boot`](https://spring.io) - Framework base para a API
 - [`Flyway`](https://flywaydb.org) - Controle de versão e migração para banco de dados
 - [`Postgres`](https://www.mysql.com) - Banco de dados
 - [`Swagger`](https://swagger.io) - Documentação de API de forma dinâmica
 - [`Docker`](https://www.docker.com) - Executa e gerencia aplicações dentro de containers
 - [`jUnit e Mockito`](https://junit.org/junit5/) - Execução de testes
 
#### Prerequisites
- [OpenJDK 11](http://jdk.java.net/11/)
- [Maven 3.2+](https://maven.apache.org/install.html)
- [Docker](https://docs.docker.com/install/)
- [Docker-compose](https://docs.docker.com/compose/install/)

#### Running
```
mvn clean compile spring-boot:run
```

#### Testing
```
mvn test
```

# API DOC
--------

### Authentication

```
http://localhost:8080/authenticate
```

JSON Request:

```json
{
	"username": "admin",
	"password": "admin"
}
```

### Add Client

```
http://localhost:8080/update?name=Kyoto
```

JSON Response:

```json
[{"id":1,"name":"Kyoto"},{"id":2,"name":"New York"},{"id":3,"name":"London"}]