# Challenge Java

![Java](https://img.shields.io/badge/Java-17-ED8B00?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3.5-6DB33F?logo=springboot&logoColor=white)
![Security](https://img.shields.io/badge/Auth-JWT-0a7ea4)
![Banco](https://img.shields.io/badge/MySQL-8+-4479A1?logo=mysql&logoColor=white)

## Forum Hub API

API REST em Java com Spring Boot para gerenciamento de tópicos de fórum, com autenticação JWT, controle de acesso, paginação, validação e documentação OpenAPI.

O repositório agora inclui uma base executável de MVP, em vez de apenas descrever o projeto.

## Visão Geral

O objetivo do projeto é simular o backend de uma plataforma de fórum onde usuários autenticados podem:

- criar tópicos;
- listar tópicos com paginação;
- visualizar um tópico específico;
- atualizar seus próprios tópicos;
- excluir seus próprios tópicos;
- autenticar via JWT para consumir endpoints protegidos.

## Funcionalidades

- Cadastro de usuários
- Login com geração de token JWT
- CRUD completo de tópicos
- Proteção de rotas com Spring Security
- Validação de payloads com Bean Validation
- Paginação e ordenação
- Migrações com Flyway
- Documentação Swagger/OpenAPI
- Tratamento global de exceções

## Stack Utilizada

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- MySQL
- Flyway
- JWT
- SpringDoc OpenAPI
- Maven

## Fluxo da Aplicação

```text
Cliente envia cadastro ou login
-> API valida os dados
-> usuário é persistido ou autenticado
-> JWT é gerado
-> cliente envia token nas rotas protegidas
-> API identifica o usuário autenticado
-> tópicos são criados, consultados, atualizados ou removidos
```

## Estrutura do Projeto

```text
.
├── pom.xml
├── README.md
└── src
    └── main
        ├── java
        │   └── br/com/vicfmartins/forumhub
        │       ├── config
        │       ├── controller
        │       ├── domain
        │       ├── dto
        │       ├── exception
        │       ├── repository
        │       ├── security
        │       ├── service
        │       └── ForumHubApplication.java
        └── resources
            ├── application.properties
            ├── application-example.properties
            └── db/migration
```

## Como Executar

### 1. Clone o repositório

```bash
git clone https://github.com/VicFmartins/Challenge-java.git
cd Challenge-java
```

### 2. Crie o banco no MySQL

```sql
CREATE DATABASE forum_hub;
```

### 3. Configure as credenciais

Você pode configurar por variáveis de ambiente:

- `DB_URL`
- `DB_USERNAME`
- `DB_PASSWORD`
- `JWT_SECRET`
- `JWT_EXPIRATION_MS`

Ou copiar o arquivo exemplo:

```bash
cp src/main/resources/application-example.properties src/main/resources/application-local.properties
```

No PowerShell:

```powershell
Copy-Item src/main/resources/application-example.properties src/main/resources/application-local.properties
```

### 4. Execute a aplicação

```bash
mvn spring-boot:run
```

API padrão:

- `http://localhost:8080`

Swagger UI:

- `http://localhost:8080/swagger-ui/index.html`

## Endpoints Principais

### Autenticação

- `POST /auth/register`
- `POST /auth/login`

### Tópicos

- `POST /topicos`
- `GET /topicos`
- `GET /topicos/{id}`
- `PUT /topicos/{id}`
- `DELETE /topicos/{id}`

## Exemplo de Requisição

### Cadastro

```json
{
  "name": "Victor Martins",
  "email": "victor@email.com",
  "password": "123456"
}
```

### Login

```json
{
  "email": "victor@email.com",
  "password": "123456"
}
```

### Criação de tópico

```json
{
  "title": "Dúvida com Spring Security",
  "message": "Como configurar autenticação stateless com JWT?",
  "course": "Spring Boot"
}
```

## Regras Atuais do MVP

- apenas usuários autenticados acessam rotas de tópicos;
- atualização e exclusão só podem ser feitas pelo autor do tópico;
- senhas são armazenadas com hash BCrypt;
- tokens JWT são assinados com segredo configurável.

## Próximos Passos

- adicionar testes de integração;
- incluir refresh token;
- permitir filtros por curso e autor;
- implementar respostas HAL ou paginação mais rica;
- adicionar perfil Docker para ambiente local.

## Observação

Este projeto foi fortalecido como base prática de estudo para o Challenge Java/Forum Hub. Ele serve tanto para portfólio quanto como ponto de partida para evoluções mais completas.
