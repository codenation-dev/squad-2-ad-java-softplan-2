# Central de Erros

### Equipe

![Jean](https://avatars1.githubusercontent.com/u/50720434?s=40&v=4)Jean [@ghanizadev](https://github.com/ghanizadev)
/ ![Igor](https://avatars0.githubusercontent.com/u/12281063?s=40&v=4) Igor [@igorcacalcantedb](https://github.com/igorcavalcantedb) / ![Jovani](https://avatars3.githubusercontent.com/u/49798215?s=40&v=4)Jovani [@JtMegazord](https://github.com/JtMegazord) / 
![Eduardo](https://avatars0.githubusercontent.com/u/27643901?s=40&v=4) Eduardo [@spiandorello](https://github.com/spiandorello) /![Roberta](https://avatars0.githubusercontent.com/u/27643901?s=40&v=4) Roberta [@robertaavila](https://github.com/robertaavila) / ![Túlio](https://avatars0.githubusercontent.com/u/27643901?s=40&v=4)Túlio [@yandiro](https://github.com/yandiro)




[![Coverage Status](https://coveralls.io/repos/github/JtMegazord/squad-2-ad-java-softplan-2/badge.svg?branch=master)](https://coveralls.io/github/JtMegazord/squad-2-ad-java-softplan-2?branch=master) 

### Sobre

Projeto Desenvolvido durante o programa de Aceleração Java da [Codenation](https://www.codenation.dev/) patrocionado pela [Softplan](https://www.softplan.com.br/).


### Configuração

Confirguação básica de autenticação e requisição de dados:

 Propriedade    | Valor          
:----------------|:----------------
 Client ID      | squad2
 Client Secret  | secret         
 Port           | 8080 (default) 
 Usuário Padrão | admin          
 Senha Padrão   | secret         

### Padrões
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
### Rotas
#### "/oauth/token"
###### **POST**
Requesita uma nova chave de acesso ao sevidor. Para isso, é necessáio ter um usuário registrado. Requer autenticação básica.
_____________________
#### "/swagger-ui"
###### **GET**
Mostra a documentação da API gerada automaticamente pelo Swagger
______________
#### "api/v1/user"
###### **POST**
Salva um novo usuário caso este seja válido.
###### **GET**
Mostra todos os usuários cadastrados.
_______
#### "api/v1/user/{id}"
###### **GET**
Mostra o detalhamento do usuário pelo ID.
____
#### "api/v1/log"
###### **POST**
Salva um novo log.
###### **GET**
Retorna uma lista com todos o logs cadastrados
_________
#### "api/v1/log/{id}"
###### **GET**
Retorna o detalhamento do Log pelo ID.
###### **PATCH**
Arquiva o log juntamento com o parâmetro "strore".
###### **DELETE**
Apaga o registro do banco de dados.
____
#### "api/v1/log?search="
###### **GET**
Realiza uma consulta a partir de uma query.
______

