# Wish list API

## Sobre o serviço

A `Wish List Api` trata-se de um serviço responsável por gerenciar os produtos favoritos de clientes. 

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
 
#### Considerações e decisões técnicas
 
 - Foi dada uma [`api de produtos`](https://gist.github.com/Bgouveia/9e043a3eba439489a35e70d1b5ea08ec) que fornece suas descrições, diante disso foi, optado por não realizar o armazenamento redundante das informações dos produtos na base da wishlist, evitando ter informações desatualizadas sobre os produtos, principalmente devido ao fato da wishlist api informar, dentre outras coisas, o preço dos produtos, uma informação volátil, e evitando também o armazenamento desnecessário de dados, visto que cada cliente pode ter uma lista infinita de produtos favoritos.
 
 - Uma oportunidade de melhoria seria realizar consultas [`api de produtos`](https://gist.github.com/Bgouveia/9e043a3eba439489a35e70d1b5ea08ec) pelas informações de uma dada lista de productsId(endpoint não foi fornecido), dessa forma poderia ser realizada apenas uma ou poucas requisições à api de produtos para buscar as informações dos produtos da lista de uma cliente.
 
 - Como a lista de clientes e seus produtos favoritos pode ter tamanho infinito, optou-se retornar a lista paginada, cada página contendo 20 items por default, mas esse tamanho pode ser alterado na request.  
 
 - Considerou-se que a `wishlist api` é uma serviço de base para outros, ou seja, que um Backend for Frontend (BFF) ou serviço que interage diretamente com os clientes e/ou o com o setor no Marketing é responsável por repassa o token valido e requisitar os endpoints da wishlist baseado no perfil que esteja solicitando.
 
 - Uma oportunidade de melhoria seria uma seviço unico para autenticação, que gerasse um token válido a partir de um user e password e retornasse um token que poderia ser utilizado por vários serviço. Na `wishlist api` foi implementada uma validação simples de login que retorna um token para ser utilizados nas requisições, mas devido ao fato de não haver um outro serviço para isso, pois acreditasse que dependendo do escopo não é interessante cada produto implementar sua própria autenticação e autorização.
 
### Tecnologias

 - [`Java`](https://www.java.com/download/) 
 - [`Spring boot`](https://spring.io) - Framework base para a API
 - [`Flyway`](https://flywaydb.org) - Controle de versão e migração para banco de dados
 - [`Postgres`](https://www.mysql.com) - Banco de dados
 - [`Swagger`](https://swagger.io) - Documentação de API de forma dinâmica
 - [`Docker`](https://www.docker.com) - Executa e gerencia aplicações dentro de containers
 - [`jUnit e Mockito`](https://junit.org/junit5/) - Execução de testes
 
### Prerequisitos
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

## Endpoints 
--------

### Authentication to get token

```
POST http://{address}:8080/authenticate
```

    Body Request:

```json
{
	"username": "admin",
	"password": "admin"
}
```

### Clientes

#### Adicionar
```
POST http://{address}:8080/api/v1/customers
```
Body Request:
```
{
	"name": "name",
	"email": "email@abc.com"
}
```

#### Atualizar
```
PUT http://{address}:8080/api/v1/customers/<CUSTOMER_ID>
```
<CUSTOMER_ID> representa o número do id do cliente para ser editado

Body Request:

```
{
	"name": "name",
	"email": "email@abc.com"
}
```

#### Deletar

```
DELETE http://{address}:8080/api/v1/customers/<CUSTOMER_ID>
```
<CUSTOMER_ID> representa o número do id do cliente para ser deletado


#### Visualizar

```
GET http://{address}:8080/api/v1/customers/<CUSTOMER_ID>
```
<CUSTOMER_ID> representa o número do id do cliente para ser ver
```
GET http://{address}:8080/api/v1/customers/?page=<PAGINA>
```
<PAGINA> representa o número da página requisitada

### Produtos favoritos

#### Adicionar
```
POST http://{address}:8080/api/v1/customers/<CUSTOMER_ID>/wish-list
```
<CUSTOMER_ID> representa o número do id do cliente 

Body Request:
```
{
	"productId": "XXXXXX"
}
```

#### Deletar

```
DELETE http://{address}:8080/api/v1/customers/<CUSTOMER_ID>/wish-list/<PRODUCT_ID>
```
<CUSTOMER_ID> representa o número do id do cliente 
<PRODUCT_ID> representa o número do id do produto 


#### Visualizar

```
GET http://{address}:8080/api/v1/customers/<CUSTOMER_ID>/wish-list/<PRODUCT_ID>
```
<CUSTOMER_ID> representa o número do id do cliente 
<PRODUCT_ID> representa o número do id do produto 
```
GET http://{address}:8080/api/v1/customers/wish-list?page=<PAGINA>
```
<CUSTOMER_ID> representa o número do id do cliente 
<PAGINA> representa o número da página requisitada



