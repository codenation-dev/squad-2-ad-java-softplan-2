# Central de Erros


[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=ghanizadev_squad-2-ad-java-softplan-2&metric=alert_status)](https://sonarcloud.io/dashboard?id=ghanizadev_squad-2-ad-java-softplan-2)



Projeto Desenvolvido durante o programa de Aceleração Java da [Codenation](https://www.codenation.dev/) patrocionado pela [Softplan](https://www.softplan.com.br/). 

Central de log, como o próprio nome diz, centraliza os logs de uma ou mais aplicações. Facilita o monitoramento e agiliza o processo de manutenção e correção de erros.

 [Clique Aqui](https://dashboard.heroku.com/apps) e veja a aplicação funcionando!


## Tecnologia

* [Java 8](https://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html) - Linguagem d eProgramação
* [Heroku](https://dashboard.heroku.com) - Servidor Deploy
* [Maven](https://maven.apache.org/) - Gerenciador de Dependencia
* [Postman](https://www.getpostman.com/) - Plataforma de desenvolvimento de API
* [Postgres](https://www.postgresql.org/) - Banco de dados
* [Springboot](https://spring.io/projects/spring-boot) - Framework Java
* [AssertJ](https://joel-costigliola.github.io/assertj/) - Framework de testes
* [Swagger](https://swagger.io/) - Documentação automática





## Configuração Autenticação

Configuração básica de autenticação e requisição de dados:

 Propertie    | Value          
:----------------|:----------------
 Client ID      | squad2
 Client Secret  | secret         
 Port           | 8080 
 Default User | admin          
 Default Password   | secret         

## Padrão de recebimento de dados
#### Usuário (User)
O formato de dados para usuário é:
```json
{	"name": "adm",
	"email": "adm@email.com",
	"password": "password" }
```
#### Log (Log)
O formato de dados para log é:
```json
{	"title": "Nome do erro",
	"detail": "Detalhes do erro (Stack)",
	"level": "Nível do erro (DEBUG, WARNING, ERROR)",
	"origin": "Origem do erro (IP)",
	"env": "Ambiente de desenvolvimento (DEVELOP, RELEASE, PRODUCTION)",
	"createdAt": "Data de recolhimento",
	"generatedBy": "Quem o reportou",
	"stored": "Armazenado" }
```


### Rodar Aplicação

Como rodar a aplicação

```bash
mvn springboot:run
```



### Deploy





Para ver o passo a passo de como fazer o deploy com Heroku [Clique aqui](https://devcenter.heroku.com/categories/deployment).



### Teste




```
mvn test
```


## Autores

Jean [@ghanizadev](https://github.com/ghanizadev)
/  Igor [@igorcacalcantedb](https://github.com/igorcavalcantedb) / Jovani [@JtMegazord](https://github.com/JtMegazord) 

Eduardo [@spiandorello](https://github.com/spiandorello) / Roberta [@robertaavila](https://github.com/robertaavila) / Túlio [@yandiro](https://github.com/yandiro)




