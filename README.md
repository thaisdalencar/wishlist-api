# Wish list API

## Sobre o serviço

A `Wish List Api` trata-se de um serviço resposável por gerenciar os produtos favoritos de clientes. 

#### Especificações
 
 O serviço disponibiliza uma API REST contendo:
 
 - Cadastro, edição, visualização e remoção de clientes;
 - Cadastro, visualização e remoção de produtos favoritos dos clientes;
 - Checagem por duplicação de email de clientes e produto favoritado;
 - Consulta da lista de clientes realizada de forma paginada;
 - Consulta da lista de produtos favoritos de um cliente realizada de forma paginada;
 - Consumo de uma `api de produtos` para buscar as informações de um dado produto;
 - Validação da existencia do produto antes de adiciona-lo a lista de favoritos;
 - Checagem de token válido nas requisições
 
Considerações e decisões de implementações:
 
 - Foi dada uma [`api de produtos`](https://gist.github.com/Bgouveia/9e043a3eba439489a35e70d1b5ea08ec) 
 que fornece suas descrições, diante disso foi optado por não realizar o armazenamento redundante na base da wishlist das informações dos produtos, evitando ter informações desatualizadas sobre os produtos, principalmente devido ao fato da wishlist api informar, dentre outras coisas, o preço dos produtos, uma informação volátil, e evitando também o armazenamento desnecessário de dados, visto que cada cliente pode ter uma lista infinita de produtos favoritos.
 - Uma oportunidade de melhoria seria realizar consultas [`api de produtos`](https://gist.github.com/Bgouveia/9e043a3eba439489a35e70d1b5ea08ec) pelas informações de uma dada lista de productsId(endpoint não foi fornecido), dessa forma poderia ser realizada apenas uma ou poucas requisições à api de produtos para buscar as informações dos produtos da lista de uma cliente.
 - Como a lista de clientes e seus produtos favoritos pode ter quantidade infinita, optou-se retornar a lista paginada, cada página contendo 20 items por default, mas esse tamanho pode ser alterado na request.  
 
 
 
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