# Wish list API

## Requirements

* Maven 3.x
* OpenJDK 11
* Docker and Docker Compose

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Web Starter](https://docs.spring.io/spring-boot/docs/{bootVersion}/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Security](https://docs.spring.io/spring-boot/docs/{bootVersion}/reference/htmlsingle/#boot-features-security)


API DOC
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