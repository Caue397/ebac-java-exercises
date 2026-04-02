# Java Store JDBC

Projeto de estudos desenvolvido durante o curso **Desenvolvedor Full Stack Java** pela EBAC (Escola Britânica de Artes Criativas e Tecnologia).

---

## Sobre o Projeto

Este projeto simula o back-end de um sistema de gerenciamento de loja, com cadastro de clientes e produtos. O objetivo principal foi praticar a conexão e manipulação de banco de dados relacionais em Java **sem o uso de frameworks ORM** (como Hibernate ou Spring Data JPA), utilizando diretamente a API **JDBC** (Java Database Connectivity).

Trabalhar com JDBC exige que o desenvolvedor escreva e gerencie manualmente as queries SQL, o mapeamento dos dados e o ciclo de vida das conexões com o banco — habilidades fundamentais para compreender o que acontece "por baixo dos panos" em aplicações Java.

O projeto foi desenvolvido seguindo a metodologia **TDD (Test-Driven Development)**, onde os testes automatizados foram escritos antes da implementação das funcionalidades. Essa abordagem garante que o código produzido atenda exatamente aos requisitos definidos e facilita a manutenção e evolução do sistema.

---

## O que foi desenvolvido

### Entidades de Domínio

Duas entidades foram modeladas para representar o negócio:

- **Cliente (`Customer`)** — armazena nome, CPF e e-mail, com controle automático de data de criação e atualização.
- **Produto (`Product`)** — armazena nome, descrição, preço (com precisão decimal), quantidade em estoque e status de ativo/inativo.

### Camada de Acesso a Dados (DAO)

Foi implementado o padrão de projeto **DAO (Data Access Object)** para cada entidade, composto por:

- **Interface** — define o contrato das operações disponíveis.
- **Implementação** — executa as operações diretamente no banco de dados via JDBC.

As operações implementadas para cada entidade foram:

| Operação | Descrição |
|----------|-----------|
| `create` | Inserir um novo registro no banco |
| `getById` | Buscar um registro pelo ID |
| `getAll` | Listar todos os registros |
| `update` | Atualizar um registro existente |
| `delete` | Remover um registro pelo ID |

### Conexão com o Banco de Dados

Foi criada uma **fábrica de conexões (`ConnectionFactory`)** seguindo o padrão Singleton, que gerencia a conexão com o banco de dados PostgreSQL, reutilizando-a quando disponível e recriando-a quando necessária.

Todos os pontos críticos de segurança foram observados:

- Uso de **`PreparedStatement`** em todas as queries, prevenindo ataques de **SQL Injection**.
- Gerenciamento correto do ciclo de vida dos recursos JDBC (`Connection`, `PreparedStatement`, `ResultSet`), evitando vazamentos de memória e conexões abertas desnecessariamente.

---

## Testes Automatizados

O projeto conta com uma suíte de testes automatizados utilizando **JUnit 4**, cobrindo os seguintes cenários para cada entidade:

- Buscar um registro existente pelo ID
- Buscar um registro inexistente (deve retornar `null`)
- Listar todos os registros
- Inserir um novo registro com sucesso
- Atualizar um registro existente com sucesso
- Tentar atualizar um ID inexistente (deve retornar `0` linhas afetadas)
- Remover um registro existente com sucesso
- Tentar remover um ID inexistente (deve retornar `0` linhas afetadas)

Os testes são executados contra um banco de dados real, garantindo que as queries SQL estejam corretas e que o comportamento da aplicação seja validado de ponta a ponta.

---

## Tecnologias Utilizadas

| Tecnologia | Finalidade |
|------------|-----------|
| **Java** | Linguagem principal |
| **JDBC** | API nativa do Java para acesso a banco de dados |
| **PostgreSQL** | Banco de dados relacional |
| **Gradle** | Ferramenta de build e gerenciamento de dependências |
| **JUnit 4** | Framework de testes automatizados |

---

## Conceitos e Habilidades Demonstrados

- Modelagem de entidades de domínio em Java
- Padrão de projeto **DAO** com separação por interface e implementação
- Escrita manual de queries **SQL** (SELECT, INSERT, UPDATE, DELETE)
- Prevenção de **SQL Injection** com `PreparedStatement`
- Gerenciamento de **conexões JDBC** e recursos do banco de dados
- Mapeamento de tipos: `BigDecimal` para valores monetários, `LocalDateTime` para timestamps
- Padrão **Singleton** para gerenciamento de conexão
- Desenvolvimento orientado a testes com **TDD** (Test-Driven Development)
- Testes automatizados com **JUnit** cobrindo cenários de sucesso e falha
- Uso de **Gradle** para build e dependências

---

## Estrutura do Projeto

```
java-store-jdbc/
├── src/
│   ├── main/java/dev/cauegallizzi/
│   │   ├── domain/
│   │   │   ├── Customer.java        # Entidade Cliente
│   │   │   └── Product.java         # Entidade Produto
│   │   └── dao/
│   │       ├── ICustomerDao.java    # Interface DAO de Cliente
│   │       ├── CustomerDao.java     # Implementação JDBC de Cliente
│   │       ├── IProductDao.java     # Interface DAO de Produto
│   │       ├── ProductDao.java      # Implementação JDBC de Produto
│   │       └── jdbc/
│   │           └── ConnectionFactory.java  # Fábrica de conexão com o banco
│   └── test/java/dev/cauegallizzi/
│       ├── CustomerDaoTest.java     # Testes de Cliente
│       ├── ProductDaoTest.java      # Testes de Produto
│       └── IntegrationTests.java    # Suite de testes integrados
├── build.gradle
└── settings.gradle
```

---

## Contexto de Aprendizado

Este projeto faz parte de um portfólio de exercícios práticos da formação Back-End Java da EBAC. O foco foi entender como o Java se comunica com bancos de dados em nível fundamental, antes de avançar para abstrações de alto nível como Spring Data JPA ou Hibernate.

Dominar JDBC é um diferencial importante para desenvolvedores Java, pois permite diagnosticar problemas de performance, otimizar queries e entender profundamente o comportamento de frameworks ORM mais complexos.
