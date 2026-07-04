# Postgres HA + Kafka Fallback
Estudo de caso sobre fallback na camada de persistência usando PostgreSQL em alta disponibilidade e Apache Kafka.

## O problema
Quando o banco primário cai, escritas ficam bloqueadas. A maioria dos sistemas não tem uma estratégia clara para esse cenário além de retornar erro pro cliente.

## A solução
- PostgreSQL com primary + replica via WAL streaming replication
- Quando o INSERT falha por indisponibilidade do banco primário, o evento é publicado no Kafka
- Um consumer processa as mensagens pendentes quando o banco volta
- Idempotência garantida via tabela de eventos processados, usando o traceId como correlationId

## Fluxo
Cliente → API Gateway → users-service
→ tenta salvar no banco primário
→ banco indisponível → publica evento no Kafka
→ banco volta → consumer processa e salva
