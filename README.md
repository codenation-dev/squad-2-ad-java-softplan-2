#Backend Spring Boot + OAuth2

Jean @ghanizadev / Igor @igorcacalcantedb / Jovani @JtMegazord / Eduardo @spiandorello / Roberta @robertaavila / Túlio @yandiro

**Tablea de conteúdos**

[TOCM]

[TOC]

###Configuração

Confirguação básica de autenticação e requisição de dados (para efeito de testes)

| Propriedade    | Valor          |
|----------------|----------------|
| Client ID      | squad2         |
| Client Secret  | secret         |
| Port           | 8080 (default) |
| Usuário Padrão | admin          |
| Senha Padrão   | secret         |

###Padrões
####Usuário (User)
O formato de dados para usuário é:
```json
{
	"name": "Nome do usuário",  - String
	"email": "email@email.com",  - String
	"password": "coxinha123",   - String
	"accessLevel": 0 - Integer
}
```
####Log (Log)
O formato de dados para usuário é:
```json
{
	"title": "Nome do erro",  - String
	"detail": "Detalhes do erro (Stack)",  - String
	"level": "Nível do erro (DEBUG, WARNING, ERROR)",  - Integer
	"origin": "Origem do erro (IP)",  - String
	"env": "Ambiente de desenvolvimento (DEVELOP, RELEASE, PRODUCTION)",  - String
	"createdAt": "Data de recolhimento",  - Date()
	"generatedBy": "Quem o reportou",  - String
	"stored": "Armazenado "  - Boolean
}
```
###Rotas
####"/oauth/token"
######**POST**
Requesita uma nova chave de acesso ao sevidor. Para isso, é necessáio ter um usuário registrado. Requer autenticação básica.
_____________________
####"/swagger-ui"
######**GET**
Mostra a documentação da API gerada automaticamente pelo Swagger

______________
####"/user"
######**POST**
Salva um novo usuário caso este seja válido.
######**GET**
Mostra todos os usuários cadastrados.
_______
####"/user/{id}"
######**GET**
Mostra o detalhamento do usuário pelo ID.
____
####"/log"
######**POST**
Salva um novo log.
######**GET**
Retorna uma lista com todos o logs cadastrados
_________
####"/log/{id}"
######**GET**
Retorna o detalhamento do Log pelo ID.
######**PATCH**
Arquiva o log juntamento com o parâmetro "strore".
######**DELETE**
Apaga o registro do banco de dados.
____
####"/log?search="
######**GET**
Realiza uma consulta a partir de uma query.
______




