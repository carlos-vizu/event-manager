# Event Manager Application

O **Event Manager** é uma aplicação full-stack composta por um **backend em Spring Boot**, um **frontend em Angular** e um **banco de dados PostgreSQL**, todos orquestrados com **Docker Compose**.

---

## 🧩 Requisitos

- Docker instalado  
- Node.js e npm (caso queira rodar o frontend localmente)  
- Java 17+ (caso queira rodar o backend localmente)  
- Maven 3.8+ (para build manual do backend)

---

## ⚙️ Configuração do Ambiente

1. Copie o arquivo de exemplo `.env`:

```bash
cp .env.example .env

```

2. dite o arquivo .env e atualize suas credenciais:
```
# Configuração do banco de dados
POSTGRES_DB=eventdb
POSTGRES_USER=seu_usuario_aqui
POSTGRES_PASSWORD=sua_senha_aqui

# Configuração do datasource do Spring
SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/eventdb
SPRING_DATASOURCE_USERNAME=${POSTGRES_USER}
SPRING_DATASOURCE_PASSWORD=${POSTGRES_PASSWORD}
SPRING_JPA_HIBERNATE_DDL_AUTO=validate
```

ATENÇÃO: Substitua **seu_usuario_aqui** e **sua_senha_aqui** pelos valores desejados.


## Docker Compose

O projeto inclui um arquivo docker-compose.yml com três serviços principais:

- **db** → Banco de dados PostgreSQL

- **backend** → API desenvolvida em Spring Boot

- **frontend** → Aplicação Angular


## Executando a Aplicação

1) Suba todos os serviços com o Docker Compose:

```bash
docker-compose up -d --build
```

2) Verifique se os containers estão rodando:
```bash
docker ps
```



## Acessos

- Frontend (Angular) → http://localhost:4200

- Backend (Spring Boot API) → http://localhost:8080

- Swagger UI → http://localhost:8080/swagger-ui/index.html

O backend aplica automaticamente as migrações do Flyway no banco de dados ao iniciar.


## Parar a Aplicação
docker-compose down


## ATENÇÃO: Se alterar o .env, é necessário reconstruir os containers:

```bash
docker-compose up -d --build
```

- O frontend e o backend se comunicam internamente via rede Docker, dentro dos containers o backend é acessado pelo endereço http://backend:8080.

- Você também pode rodar o frontend ou backend individualmente fora do Docker, configurando as variáveis de ambiente adequadas.


## Testes

O projeto inclui testes unitários e de integração.
Para executá-los manualmente:

```bash
cd backend
mvn test
```
