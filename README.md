# Carrinho de Compras em Java

Este projeto é uma aplicação de gerenciamento de carrinho de compras, permitindo que usuários adicionem, removam e atualizem produtos no carrinho, bem como gerenciem o estoque de produtos. A aplicação é desenvolvida em Java e utiliza JDBC para a manipulação do banco de dados.

## Funcionalidades

- **Gerenciar Estoque:**
  - Listar produtos em estoque.
  - Adicionar novos produtos ao estoque.
  - Atualizar informações de produtos no estoque.
  - Remover produtos do estoque.

- **Gerenciar Carrinho de Compras:**
  - Listar produtos no carrinho.
  - Adicionar produtos ao carrinho.
  - Atualizar quantidade de produtos no carrinho.
  - Remover produtos do carrinho.

## Tecnologias Utilizadas

- Java 17
- JDBC para conexão com o banco de dados
- MySQL (ou outro SGBD de sua escolha)

## Estrutura do Projeto    
```plaintext
Carrinho-de-Compras-Java/
│
├── app/                # Classe principal e menu da aplicação
├── modelo/             # Entidades do modelo
├── dao/                # Interfaces
├── impl/               # Implementações de acesso a dados
├── entidades           # Classes que representam as entidades do sistema
├── db.properties       # Arquivo de conexão com o banco de dados
└── README.md           # Este arquivo
```

## Estrutura do Banco de Dados

### Tabelas

- **Tabela Carrinho de Compras**:
    - `id`: Identificador único do produto no carrinho.
    - `nome`: Nome do produto.
    - `categoria`: Categoria do produto.
    - `valor`: Preço unitário do produto.
    - `quantidade`: Quantidade de produtos no carrinho.
    - `valor_total`: Valor total por produto (quantidade x valor).

- **Tabela Estoque**:
    - `id`: Identificador único do produto no estoque.
    - `nome`: Nome do produto.
    - `categoria`: Categoria do produto.
    - `valor`: Preço unitário do produto.
    - `quantidade`: Quantidade disponível no estoque.

## Como Executar

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/germanomp/Carrinho-de-Compras-Java.git
   cd Carrinho-de-Compras-Java

## Configurar o Banco de Dados:

-- Criação do banco de dados

CREATE DATABASE desafio;

-- Seleciona o banco de dados recém-criado

USE desafio;

-- Tabela Carrinho de Compras

CREATE TABLE carrinho (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    categoria VARCHAR(100) NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    quantidade INT NOT NULL,
    valor_total DECIMAL(10, 2) AS (valor * quantidade) STORED
);

-- Tabela Estoque

CREATE TABLE estoque (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    categoria VARCHAR(100) NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    quantidade INT NOT NULL
);

## Configuração da Conexão com o Banco de Dados

Antes de executar a aplicação, você precisa configurar a conexão com o banco de dados. Siga os passos abaixo para ajustar a classe de conexão conforme suas configurações:

1. **Arquivo de Propriedades**
   - Certifique-se de que o arquivo `db.properties` esteja na pasta correta do seu projeto. Esse arquivo deve conter as seguintes configurações:

   ```properties
   user="Seu usuário"
   password="Sua senha"
   url=jdbc:mysql://127.0.0.1:3306/desafio
   useSSL=false

1. **Arquivo de Conexão do Java com o MySQL**
   - Certifique-se de que o projeto tenha o arquivo de conexão do Java com o MySQL nas dependências.
     
## Executar a Aplicação:

Compile e execute a classe Programa.java.
