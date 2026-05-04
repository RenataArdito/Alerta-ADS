# Alerta - Sistema de Gestão de Solicitações

## Sobre o Projeto
O **Alerta** é um sistema web desenvolvido para gerenciar solicitações de usuários, permitindo o acompanhamento completo do ciclo de vida de cada requisição.

A aplicação foi projetada com foco em **organização, controle e transparência**, oferecendo funcionalidades tanto para usuários comuns quanto para administradores.

O sistema centraliza informações e facilita o processo de análise, validação e acompanhamento de solicitações.

---

## Objetivo
Criar uma solução eficiente para:
- Registro de solicitações
- Acompanhamento de status
- Gerenciamento de usuários
- Controle administrativo
- Organização de processos

---

## Funcionalidades

### Usuário
- Cadastro e login
- Criação de solicitações
- Visualização de histórico
- Edição de dados do perfil

### Administrador
- Visualização de todas as solicitações
- Atualização de status (ex: em validação, aprovado, rejeitado)
- Gestão de processos
- Controle geral do sistema

---

## Tecnologias Utilizadas

### Backend
- Java
- Spring Boot
- Spring MVC
- Spring Data JPA

### Frontend
- HTML
- CSS
- JavaScript

### Banco de Dados
- PostgreSQL

### Outros
- Git & GitHub
- Arquitetura MVC

---

## Arquitetura

O sistema segue o padrão **MVC (Model-View-Controller)**:

- **Model** → Representa entidades como `Usuario`, `Solicitacao` e `Historico`
- **View** → Interface com o usuário (páginas web)
- **Controller** → Lógica de negócio e controle das requisições

---

## Controle de Acesso

O sistema possui dois níveis de acesso:

- **Usuário comum**
  - Cria e acompanha solicitações
- **Administrador**
  - Gerencia e valida solicitações

O controle é feito via sessão, garantindo segurança na navegação.

---

## Estrutura do Projeto

``` Bash
src/
 ├── controller/
 ├── model/
 ├── repository/
 ├── service/
 └── resources/
      ├── templates/
      └── static/
```

---

## Fluxo do Sistema

- Usuário realiza login
- Cria uma solicitação
- Solicitação é registrada no banco
- Administrador analisa
- Status é atualizado
- Usuário acompanha pelo histórico

---
## ⚙️ Como Executar o Projeto

```bash
# Clone o repositório
git clone https://github.com/RenataArdito/Alerta-ADS.git

# Acesse a pasta
cd Alerta-ADS

# Execute o projeto
./mvnw spring-boot:run
