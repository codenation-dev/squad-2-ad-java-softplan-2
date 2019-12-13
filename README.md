# Backend Spring Boot + OAuth2

Jean @ghanizadev / Igor @igorcavalcantedb / Jovani @JtMegazord / Eduardo @spiandorello / Roberta @robertaavila / Túlio @yandiro

### Configuração

Confirguação básica de autenticação e requisição de dados (para efeito de testes)

| Propriedade    | Valor          |
|----------------|----------------|
| Client ID      | squad2         |
| Client Secret  | secret         |
| Port           | 8080 (default) |
| Usuário Padrão | admin          |
| Senha Padrão   | secret         |

### Padrões
#### Usuário (User)
O formato de dados para usuário é:
```json
{
	"name": "Nome do usuário",
	"email": "email@email.com",
	"password": "coxinha123
}
```
#### Log (Log)
O formato de dados para usuário é:
```json
{
	"title": "Nome do erro",
	"detail": "Detalhes do erro (Stack)",
	"level": "Nível do erro (DEBUG, WARNING, ERROR)",
	"origin": "Origem do erro (IP)",
	"environment": "Ambiente de desenvolvimento (DEVELOP, RELEASE, PRODUCTION)",
	"createdAt": "Data de recolhimento",
	"generatedBy": "Quem o reportou",
	"stored": "Armazenado "
}
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

