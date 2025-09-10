# Desafio de API - Rocktseat <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" width="60" height="60" alt="Java"/>

## 📘 Sobre o Desafio

Neste desafio, você irá desenvolver uma API fictícia para uma empresa de cursos de programação. O objetivo principal é implementar operações básicas de CRUD para gerenciar cursos.

### ✅ Funcionalidades da API

- Criar um novo curso
- Listar todos os cursos
- Atualizar um curso pelo seu `id`
- Excluir um curso pelo seu `id`

---

## 📐 Estrutura e Regras de Negócio

Antes de explorar as rotas, é importante entender quais propriedades um curso deve possuir:

- `id` – Identificador único do curso
- `name` – Nome do curso
- `category` – Categoria do curso
- `active` – Indica se o curso está ativo ou não
- `created_at` – Data de criação do curso
- `updated_at` – Deve ser atualizada sempre que o curso for modificado

---

## 🚀 Rotas da API

### `POST - /courses`

Cria um novo curso no banco de dados.  
Campos obrigatórios no corpo da requisição: `name` e `category`.  
Os campos `id`, `created_at` e `updated_at` devem ser preenchidos automaticamente.

---

### `GET - /courses`

Lista todos os cursos cadastrados no banco de dados.  
Também deve permitir a busca por `name` e `category`.

---

### `PUT - /courses/:id`

Atualiza um curso existente pelo seu `id`.  
No corpo da requisição, apenas os campos `name` e/ou `category` devem ser enviados.  
Se apenas `name` for enviado, `category` não deve ser alterado, e vice-versa.  
O campo `active` deve ser ignorado nesta rota — sua atualização é feita via `PATCH`.

---

### `PATCH - /courses/:id/active`

Alterna o status de ativo do curso (`true` ou `false`).  
Essa rota é responsável exclusivamente por modificar o campo `active`.

---

## 🛠️ Tecnologias sugeridas

- Java 21
- Spring Boot
- JPA / Hibernate
- PostgreSQL ou MySQL
- Postman para testes
- Git e GitHub para versionamento

---

## 📫 Contato

[![Gmail](https://img.shields.io/badge/Email-Gmail-red?style=flat&logo=gmail)](mailto:pirescaiq@gmail.com)  
[![LinkedIn](https://img.shields.io/badge/LinkedIn-LinkedIn-blue?style=flat&logo=linkedin)](https://www.linkedin.com/in/caique-pires-8843aa332)
