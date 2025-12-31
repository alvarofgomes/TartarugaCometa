🐢 Tartaruga Cometa – Sistema de Gerenciamento de Entregas

O Tartaruga Cometa é uma aplicação web desenvolvida para o gerenciamento de entregas de uma transportadora.
O sistema permite cadastrar clientes, endereços, produtos e entregas, além de acompanhar o status e os valores envolvidos em cada entrega.

O projeto tem como foco organização, regras de negócio bem definidas e aprendizado prático com Java Web.

⚙️ Tecnologias Utilizadas

- Backend

Java 8+

Java Servlets

Apache Tomcat 8+

JDBC

Gradle

Padrão DAO

Camada BO (Business Object) para regras de negócio

- Frontend

JSP (JavaServer Pages)

HTML5

CSS3

- Banco de Dados

PostgreSQL

Modelagem relacional

Uso de chaves estrangeiras para garantir integridade dos dados

📌 Funcionalidades Principais

Cadastro, edição e listagem de Clientes

Cadastro e gerenciamento de Endereços

Cadastro de Produtos

Criação de Entregas

Associação de produtos às entregas

Controle de status da entrega (pendente, em caminho, entregue)

Cálculo de valor total da entrega (produtos + frete)

Tratamento de erros de integridade (ex: exclusão bloqueada por entregas vinculadas)

⚠️ Regras Importantes

Clientes e endereços não podem ser removidos caso existam entregas vinculadas

Entregas entregues não podem ser alteradas

Validações são feitas na camada de negócio (BO)

Mensagens de erro amigáveis são exibidas ao usuário

📌 Atualizações Contínuas

Este projeto está em constante evolução.
Novas funcionalidades, melhorias de código, ajustes visuais e refatorações são aplicadas conforme o avanço do desenvolvimento e aprendizado.
