# Projeto J – Sistema de Priorização de Tarefas (SPT)

Projeto final da disciplina **Algoritmos e Estruturas de Dados I**
Engenharia da Computação – UNIVAP · Prof. Wagner Santos C. de Jesus

**Autores:** Diogo Gil de Oliveira, Eduardo Peron, Gustavo Albano Nunes

## Problema

Uma empresa de software mantém um backlog de tarefas, cada uma com um ID e uma
prioridade de 1 a 10 (1 = mais crítica). O backlog deve estar sempre ordenado da
tarefa mais crítica para a menos crítica.

A função `adicionarTarefa(backlog, novaTarefa)` insere uma nova tarefa na posição
correta seguindo a lógica do **Insertion Sort**:

1. Compara a nova tarefa com as do backlog, da direita para a esquerda.
2. Desloca uma posição para a direita as tarefas menos críticas.
3. Insere a nova tarefa onde a anterior tem prioridade menor ou igual.

Exemplo: `[P1, P3, P5, P8]` + `P4` → `[P1, P3, P4, P5, P8]`

## Estrutura

| Classe | Função |
|---|---|
| `Dados` | Guarda uma tarefa (ID, prioridade, descrição) |
| `Lista` | Lista genérica sequencial (`Object[]`) com alocação inteligente |
| `SPT` | Método `adicionarTarefa` (Insertion Sort) |
| `TesteEstr` | Teste em console do exemplo do enunciado |
| `TelaSPT` | Interface gráfica (Swing) |
| `Principal` | Abre a interface gráfica |

**Complexidade por inserção:** tempo O(n) no pior caso, memória extra O(1).

## Como executar

```bash
cd src
javac *.java
java Principal
```

Na tela, clique em **Exemplo do enunciado** e depois em **Adicionar** (prioridade 4).