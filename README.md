<h1 align="center">LiterAlura</h1>

<div align="center">
  <img width="608" height="396" alt="image" src="https://github.com/user-attachments/assets/a5ac38b7-70c7-4004-9a91-d84b4a7b04b2" />
</div>

Projeto **LiterAlura** é uma aplicação Java com Spring Boot que consome a API pública Gutendex para buscar, registrar e listar livros e autores literários. 

## Descrição 📖

O sistema permite consultar livros pelo título, armazenar informações de livros e autores no banco de dados, listar livros e autores já registrados, filtrar autores que estavam vivos em um determinado ano, e buscar livros por idioma.

A comunicação com a API externa é feita via HTTP, e os dados são convertidos e persistidos usando JPA/Hibernate.

## Funcionalidades🚀

- Buscar livro pelo título na API Gutendex e salvar no banco se ainda não existir.
- Listar todos os livros registrados no banco.
- Listar todos os autores registrados, incluindo seus livros.
- Listar autores vivos em um ano informado pelo usuário.
- Listar livros registrados por idioma (exemplo: es, en, fr, pt).
- Menu interativo via console para navegação das opções.

## Tecnologias utilizadas 💻

- Java 17+
- Spring Boot (Spring Data JPA, Spring ORM)
- Hibernate
- Banco de dados relacional PostgreSQL
- API REST externa: [Gutendex](https://gutendex.com/)

**LiterAlura** - sua biblioteca literária simplificada com Java e Spring Boot!
