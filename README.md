# impl-cache

API em Spring Boot 4.x (JDK 21+) que serve conteúdos usando um cache próprio construído com `HashMap`, com limite de itens (LRU), expiração por TTL, invalidação e estatísticas de hits/misses/evictions.

## Pré-requisitos

- JDK 21 ou superior
- O projeto usa o Maven Wrapper (`mvnw` / `mvnw.cmd`), não é preciso instalar o Maven

## Como rodar

Na raiz do projeto:

```bash
# Linux/macOS
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

A aplicação sobe em `http://localhost:8080` e usa H2 em memória.

Configurações do cache em `src/main/resources/application.properties`:

```properties
app.cache.max-itens=5
app.cache.ttl-segundos=60
```

## Testando com a collection

O arquivo `collection.json` na raiz do projeto contém todas as requisições da API, prontas para importar no Postman, Insomnia ou outro client http de preferência

Ela inclui:

| Requisição | Rota |
|---|---|
| Buscar conteúdo | `GET /conteudos/{id}` (header `X-Cache: HIT/MISS`) |
| Criar conteúdo | `POST /conteudos` |
| Atualizar conteúdo | `PUT /conteudos/{id}` |
| Excluir conteúdo | `DELETE /conteudos/{id}` |
| Estatísticas do cache | `GET /cache/estatisticas` |
| Limpar cache | `DELETE /cache` |

## Teste rápido via curl

```bash
curl -X POST http://localhost:8080/conteudos \
  -H "Content-Type: application/json" \
  -d '{"titulo":"Teste","corpo":"Corpo","autor":"Autor"}'

curl -i http://localhost:8080/conteudos/1   # primeira vez: X-Cache: MISS (~2s)
curl -i http://localhost:8080/conteudos/1   # segunda vez: X-Cache: HIT

curl http://localhost:8080/cache/estatisticas
```