# Sistema de Lista de Filmes (CRUD em Java)

Este projeto é um sistema simples de CRUD (Criar, Ler, Atualizar e Excluir) feito em Java, utilizando boas práticas de POO e uma estrutura organizada em camadas.  
O tema escolhido foi uma Lista de Filmes para Assistir (ou que já foi assistido), onde o usuário pode cadastrar filmes, listar todos, editar e excluir.

O programa roda no console, seguindo o menu interativo definido pelo professor.

---

# Funcionalidades

Cada filme possui:

- id — inteiro, autoincrementado  
- título — nome do filme  
- descrição — sinopse, gênero, comentários  
- data de conclusão — data planejada para assistir (LocalDate)

Menu principal:

1. Cadastrar filme  
2. Listar filmes  
3. Atualizar filme  
4. Excluir filme  
5. Sair  

---

# Estrutura do Projeto

Estrutura solicitada na atividade:

## model/
Tem as classes de entidades (objeto Filme).

## repository/
Contém a camada responsável por armazenar os dados em memória e fornecer as operações CRUD.

## service/
Contém regras de negócio e validações.

## app/
Contém o arquivo Main.java, responsável pelo menu e interação com o usuário.

---

# Tecnologias Utilizadas

- Java 17+  
- API java.time (LocalDate)  
- POO (Programação Orientada a Objetos)  
- Separação por camadas  
- Princípios SOLID (Single Responsibility)

---

# Como Executar o Projeto

## Pré-requisitos
- Java JDK instalado (versão 11 ou superior)  
- IntelliJ, Eclipse ou VS Code (opcional)  
- Git (opcional, para clonar o repositório)

---

## Clonar o repositório

git clone https://github.com/Guilherme-Davi/CRUD_console
cd CRUD_console

## Executar o Projeto
Abra o projeto em uma IDE como IntelliJ.
Navegue até app/Main.java.
Clique em Run para executar o programa.
O menu CRUD aparecerá no console da sua IDE.
