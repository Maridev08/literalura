# Literalura Challenge
Este projeto é um desafio de programação proposto
pela Alura, focado na criação de uma aplicação Java 
com Spring Boot para interagir com a API Gutendex. 
A aplicação permite buscar livros por título, 
persistir os dados em um banco de dados PostgreSQL, e realizar diversas consultas sobre livros e autores.


![print-challenge-literalura.png](print-challenge-literalura.png)

## 🚀 Tecnologias Utilizadas
* Spring Boot: Framework para facilitar a criação de aplicações Java stand-alone.

* Maven: Ferramenta de automação de build e gerenciamento de dependências.

* PostgreSQL: Sistema de gerenciamento de banco de dados relacional.

* Spring Data JPA: Abstração para acesso a dados, facilitando a interação com o banco de dados.

* Jackson (ObjectMapper): Biblioteca para serialização/desserialização de JSON, utilizada para mapear os dados da API Gutendex para objetos Java.

## ✨ Funcionalidades
### A aplicação oferece as seguintes funcionalidades:

* Busca de Livros por Título na API: Permite ao usuário pesquisar livros na API Gutendex utilizando um título como critério.

* Persistência de Dados no Banco de Dados: Os livros e autores encontrados na API são salvos no banco de dados PostgreSQL para consultas futuras .

* Listagem de Autores: Exibe todos os autores registrados no banco de dados.

* Listagem de Livros: Exibe todos os livros registrados no banco de dados.

* Busca de Autores Vivos em Determinado Ano: Permite filtrar autores que estavam vivos em um ano específico.

* Listagem de Livros em Determinado Idioma: Permite filtrar livros por um idioma específico.

## ⚙️ Como Rodar o Projeto
Siga os passos abaixo para configurar e executar o projeto em sua máquina:

### Pré-requisitos
* Java 17 ou superior.

* Maven instalado.

* PostgreSQL instalado e configurado (certifique-se de ter um banco de dados e um usuário para a aplicação).

### Configuração do Banco de Dados
Crie um banco de dados PostgreSQL para o projeto (ex: literalura_db).

* Atualize o arquivo src/main/resources/application.properties com as credenciais do seu banco de dados:

### Executando a Aplicação
* Clone o repositório.
* Compile o projeto com Maven
* Execute a aplicação Spring Boot