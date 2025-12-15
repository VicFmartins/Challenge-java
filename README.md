# Challenge-java

# Forum Hub API

API REST desenvolvida para gerenciar tópicos de um fórum, permitindo operações CRUD completas com autenticação e autorização.

## Descrição do Projeto

Forum Hub é uma API REST que simula o back-end de um fórum online. A aplicação permite criar, listar, atualizar e deletar tópicos, além de implementar um sistema robusto de autenticação e autorização usando JWT. Todos os endpoints são documentados via Swagger/OpenAPI.

## Funcionalidades

- Cadastro e autenticação de usuários com JWT
- CRUD completo de tópicos
- Validação de dados de entrada
- Paginação e ordenação de resultados
- Tratamento global de exceções
- Documentação interativa da API
- Testes automatizados

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3.2.0
- Spring Security
- Spring Data JPA
- MySQL 8.0
- Flyway Migration
- JWT (JSON Web Token)
- Bean Validation
- Lombok
- SpringDoc OpenAPI (Swagger)
- JUnit 5 e Mockito

## Requisitos

- JDK 17 ou superior
- Maven 3.9+
- MySQL 8.0
- IDE de sua preferência (IntelliJ IDEA, Eclipse, VS Code)

## Configuração do Ambiente

### 1. Clone o repositório

```bash
git clone https://github.com/seu-usuario/forum-hub.git
cd forum-hub
```

### 2. Configure o banco de dados

Crie um banco de dados MySQL:

```sql
CREATE DATABASE forum_hub;
```

### 3. Configure as variáveis de ambiente

Edite o arquivo `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/forum_hub
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

### 4. Execute a aplicação

```bash
mvn spring-boot:run
```

A API estará disponível em `http://localhost:8080`

## Documentação da API

Acesse a documentação interativa em: `http://localhost:8080/swagger-ui.html`

### Endpoints Principais

#### Autenticação

- `POST /auth/register` - Cadastrar novo usuário
- `POST /auth/login` - Realizar login e obter token JWT

#### Tópicos

- `POST /topicos` - Criar novo tópico
- `GET /topicos` - Listar todos os tópicos (paginado)
- `GET /topicos/{id}` - Buscar tópico específico
- `PUT /topicos/{id}` - Atualizar tópico
- `DELETE /topicos/{id}` - Deletar tópico

## Estrutura do Projeto

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── forum/
│   │           └── hub/
│   │               ├── config/
│   │               ├── controller/
│   │               ├── dto/
│   │               ├── exception/
│   │               ├── model/
│   │               ├── repository/
│   │               ├── security/
│   │               └── service/
│   └── resources/
│       ├── db/
│       │   └── migration/
│       └── application.properties
└── test/
    └── java/
```

## Executando os Testes

```bash
mvn test
```

## Build do Projeto

```bash
mvn clean package
```

O arquivo JAR será gerado em `target/forum-hub-0.0.1-SNAPSHOT.jar`


## Licença

Este projeto foi desenvolvido como parte do Challenge ONE - Oracle Next Education em parceria com a Alura.
