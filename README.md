Avaliação Prática P1 - Reutilização de API de backend
Quando: 25/04/2022

Onde: Github

O que:

- API REST de backend desenvolvida implementada sobre a API REST do Keycloak, executando em um cluster ECS na AWS, implementando, no mínimo, as seguintes rotas
  - Rotas padrão para autenticação OAUTH2 conforme implementação do Keycloak (login e geração do token OAUTH2)
    - POST /users: criação de um usuário - ok
    - GET /users: recuperação dos dados de todos os usuários - ok
    - GET /users/{id}: recuperação de um usuário - ok
    - PUT /users/{id}: atualização de um usuário - ok
    - PATCH /users/{id}: atualização da senha de um usuário - ok
    - DELETE /users/{id}: exclusão lógica de um usuário