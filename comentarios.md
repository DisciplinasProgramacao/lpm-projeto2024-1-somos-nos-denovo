# Comentários - Revisão de 12/06 (fim da Sprint 3)

Todos os comentários referem-se ao código do ramo "master" ou "main". É obrigação do grupo manter o código principal neste ramo. Problemas relatados podem ser:

  - ❕❕  - observações e melhorias
  - ⚠️ - médios. Erros de lógica, regras mal implementadas... Descontos de até 1 ponto.
  - 🚨 - sérios. Regras faltando, problemas de modularidade... Descontos de até 3 pontos.
  - 💣 - graves. Falta de classes, requisitos ignorados ... Descontos de 5 ou mais pontos.

## Revisão
  
  - ⚠️ o método add produto do Pedido deve ser abstrato e implementado nas filhas. (ARRUMADO)
  - ⚠️ vários if --> switch case no PedidoFechado (SEMI-ARRUMADO)
  - ⚠️ Pedido não tem requisição: requisição já contém pedido(ARRUMADO)
  - ⚠️ Por que uma requisição de vocês está com vários pedidos? (ARRUMADO)
  - 🚨 requisição não pode ter "get pedidos" (para que isso serve?) (ARRUMADO)
  - 🚨 como inserimos um produto em uma requisiçao atendida? Vocês estão confundindo pedido com produto (e complicando uma regra) (ARRUMADO)
  - ❕❕ apesar disso tudo, já melhorou bastante do que era antes!
  - 💣 já sabemos, faltando Spring / controllers
