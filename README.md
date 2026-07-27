# spring-todo-list

# 📝 Spring Boot To-Do List API

Uma API RESTful para gerenciamento de tarefas (To-Do List) construída com Java e Spring Boot. O projeto permite a criação de usuários, gerenciamento de tarefas individuais e conta com um sistema de autenticação customizado.

## 🚀 Tecnologias Utilizadas

* **Java 21**
* **Spring Boot** (Web, Data JPA)
* **BCrypt** (Criptografia de senhas)
* **Banco de Dados** (H2/PostgreSQL/MySQL - *[Nota: Ajuste para o banco que está usando]*)
* **Docker** (Conteinerização)
* **Render** (Deploy em nuvem)

## ✨ Funcionalidades

* **Gestão de Usuários:** Cadastro de usuários com senhas criptografadas (BCrypt).
* **Gestão de Tarefas (CRUD):** Criação, listagem e atualização de tarefas.
* **Autenticação:** Proteção de rotas sensíveis utilizando Basic Auth interceptado via `Filter`.
* **Segurança e Isolamento:** Um usuário só pode visualizar e editar as suas próprias tarefas.
* **Validação de Regras de Negócio:**
  * O título da tarefa tem um limite máximo de 50 caracteres.
  * A data de início não pode ser no passado.
  * A data de término não pode ser anterior à data de início.
* **Atualizações Parciais:** Utilização de `BeanUtils` para permitir a atualização apenas dos campos enviados na requisição (PATCH/PUT inteligente).

## 🛣️ Endpoints da API

### Usuários (Não requer autenticação)
| Método | Rota | Descrição |
| :--- | :--- | :--- |
| `POST` | `/users/` | Cria um novo usuário. |
| `GET` | `/users` | Retorna a lista de usuários cadastrados. |

### Tarefas (Requer Autenticação - Basic Auth)
| Método | Rota | Descrição |
| :--- | :--- | :--- |
| `POST` | `/tasks/` | Cria uma nova tarefa para o usuário autenticado. |
| `GET` | `/tasks/` | Retorna todas as tarefas do usuário autenticado. |
| `PUT` | `/tasks/{id}` | Atualiza os dados de uma tarefa existente (valida propriedade). |
