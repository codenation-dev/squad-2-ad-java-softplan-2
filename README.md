# Central de Erros


[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=ghanizadev_squad-2-ad-java-softplan-2&metric=alert_status)](https://sonarcloud.io/dashboard?id=ghanizadev_squad-2-ad-java-softplan-2)



Projeto Desenvolvido durante o programa de Aceleração Java da [Codenation](https://www.codenation.dev/) patrocionado pela [Softplan](https://www.softplan.com.br/). 

Central de log, como o próprio nome diz centraliza os logs de uma ou mais aplicações. Facilita a manutenção e agiliza o processo de manutenção e correção de erros.

 [Clique Aqui](https://dashboard.heroku.com/apps) e veja a aplicação funcionando!


## Tecnologia

* [Java 8](https://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html) - Linguagem d eProgramação
* [Heroku](https://dashboard.heroku.com) - Servidor Deploy
* [Maven](https://maven.apache.org/) - Gerenciador de Dependencia
* [Postman](https://www.getpostman.com/) - Plataforma de desenvolvimento de API
* [Postgres](https://www.postgresql.org/) - Banco de dados
* [Springboot](https://spring.io/projects/spring-boot) - Framework Java
* [AssertJ](https://joel-costigliola.github.io/assertj/) - Framework de testess
* [Swagger](https://swagger.io/) - Documentação automática





## Configuração Autenticação

Confirguação básica de autenticação e requisição de dados:

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
O formato de dados para usuário é:
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
## Recursos
#### "/oauth/token"
###### **POST**
Requesita uma nova chave de acesso ao sevidor. Para isso, é necessáio ter um usuário registrado. Requer autenticação básica.

#### "/swagger-ui"
###### **GET**
Mostra a documentação da API gerada automaticamente pelo Swagger

#### "api/v1/user"
###### **POST**
Salva um novo usuário caso este seja válido.
###### **GET**
Mostra todos os usuários cadastrados.

#### "api/v1/user/{id}"
###### **GET**
Mostra o detalhamento do usuário pelo ID.

#### "api/v1/log"
###### **POST**
Salva um novo log.
###### **GET**
Retorna uma lista com todos o logs cadastrados

#### "api/v1/log/{id}"
###### **GET**
Retorna o detalhamento do Log pelo ID.
###### **PATCH**
Arquiva o log juntamento com o parâmetro "strore".
###### **DELETE**
Apaga o registro do banco de dados.

#### "api/v1/log?search="
###### **GET**
Realiza uma consulta a partir de uma query.



### Rodar Aplicação

Como rodar a aplicação

```bash
mvn springboot:run
```






### Teste




```
mvn test
```


## Autores

![Jean](https://avatars1.githubusercontent.com/u/50720434?s=40&v=4)Jean [@ghanizadev](https://github.com/ghanizadev)
/ ![Igor](https://avatars0.githubusercontent.com/u/12281063?s=40&v=4) Igor [@igorcavalcantedb](https://github.com/igorcavalcantedb) / ![Jovani](https://avatars3.githubusercontent.com/u/49798215?s=40&v=4)Jovani [@JtMegazord](https://github.com/JtMegazord) / 
![Eduardo](https://avatars0.githubusercontent.com/u/27643901?s=40&v=4) Eduardo [@spiandorello](https://github.com/spiandorello) /![Roberta](https://avatars0.githubusercontent.com/u/27643901?s=40&v=4) Roberta [@robertaavila](https://github.com/robertaavila) / ![Túlio](https://avatars0.githubusercontent.com/u/27643901?s=40&v=4)Túlio [@yandiro](https://github.com/yandiro)




