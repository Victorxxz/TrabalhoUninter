# Baozi Store – API REST

Projeto acadêmico de Desenvolvimento Web Back-End. Java 17, Spring Boot, Spring Data JPA e H2 (banco relacional em arquivo).

## Como executar
1. Instale JDK 17 ou superior e Maven.
2. Abra esta pasta no IntelliJ IDEA, Eclipse, VS Code ou Pycharm.
3. Execute `mvn spring-boot:run` ou rode `BaoziStoreApplication`.
4. A API ficará disponível em `http://localhost:8080`.
5. Console H2: `http://localhost:8080/h2-console` — JDBC URL `jdbc:h2:file:./data/baozidb`, usuário `sa`, senha em branco.

## Endpoints
Para cada recurso (`clientes`, `produtos`, `pedidos`):
- `POST /recurso` – cadastrar
- `GET /recurso` – listar
- `GET /recurso/{id}` – consultar por ID
- `PUT /recurso/{id}` – atualizar (opcional, implementado)
- `DELETE /recurso/{id}` – excluir

Envie e receba JSON com `Content-Type: application/json`.

## Dados de demonstração
Cliente:
```json
{"nome":"João Victor de Almeida dos Santos - RU 5357530","clienteDesde":"2026-11-26"}
```
Produto:
```json
{"nome":"Pão Chinês Tradicional","preco":8.50,"estoque":true}
```
Pedido:
```json
{"clienteId":1,"produtoId":1,"quantidade":3}
```
Atenção: use os IDs efetivamente retornados pelo POST de cliente e produto. A API valida se ambos existem antes de registrar o pedido.

## Postman
Importe `postman/Baozi_Store.postman_collection.json`. Se necessário, altere a variável `baseUrl`. Execute os POSTs na ordem Cliente, Produto e Pedido; depois faça GET geral, GET por ID e DELETE. Capture telas reais do Postman com a requisição, URL, resposta e status visíveis. Os prints devem ser inseridos no relatório final.

## Estrutura
- `model`: entidades JPA
- `repository`: interfaces Spring Data JPA
- `controller`: endpoints REST
- `src/main/resources`: configuração do banco
