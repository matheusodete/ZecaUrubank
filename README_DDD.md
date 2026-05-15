# ZecaUrubank - Refatoração com DDD

## 1. Descrição do Case

O projeto ZecaUrubank representa um aplicativo bancário mobile.
A refatoração foi realizada utilizando conceitos de Domain-Driven Design (DDD)
para separar regras de negócio da infraestrutura e melhorar a organização do sistema.

---

## 2. Linguagem Ubíqua

| Termo | Significado |
|---|---|
| Conta Bancária | Conta do usuário dentro do banco |
| Saldo | Valor disponível na conta |
| Transferência | Envio de dinheiro entre contas |
| Depósito | Entrada de dinheiro |
| Saque | Retirada de dinheiro |

---

## 3. Módulos

### Accounts

Responsável pelas regras relacionadas às contas bancárias.

Classes:
- BankAccount
- Money
- TransferService
- BankAccountFactory
- IBankAccountRepository

---

## 4. Entities

### BankAccount

Identidade:
- id

Responsabilidades:
- Depositar dinheiro
- Sacar dinheiro
- Manter consistência do saldo

Regras:
- Não permitir saque maior que o saldo.

Motivo para ser Entity:
- Possui identidade e ciclo de vida próprio.

---

## 5. Value Objects

### Money

Atributos:
- amount
- currency

Validações:
- Valor não pode ser negativo.
- Moeda obrigatória.

Motivo:
- Definido apenas pelos valores.

---

## 6. Aggregate

### Aggregate: BankAccount

Aggregate Root:
- BankAccount

Objetos internos:
- Money

Invariantes:
- Saldo nunca pode ficar negativo.

---

## 7. Factories

### BankAccountFactory

Objetivo:
- Criar contas válidas já com saldo inicial zerado.

---

## 8. Domain Services

### TransferService

Regra:
- Realiza transferência entre contas.

Motivo:
- A operação depende de duas entidades diferentes.

---

## 9. Repositories

### IBankAccountRepository

Aggregate persistido:
- BankAccount

Implementação:
- InMemoryBankAccountRepository

---

## 10. Regras de negócio centralizadas

| Regra | Classe |
|---|---|
| Saldo não pode ficar negativo | BankAccount |
| Valor não pode ser negativo | Money |
| Transferência entre contas | TransferService |

---

## 11. Estrutura em camadas

src/main/java/com/example/zecaurubank

- Application
- Domain
- Infrastructure

---

## 12. Trade-offs

Foi utilizada uma implementação simples em memória para facilitar o entendimento
dos conceitos de DDD sem acoplar o domínio ao banco de dados.