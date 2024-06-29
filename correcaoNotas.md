# SSJ5

## Sprint 1 - Até 07/abril
  - Nota de grupo (8 pontos)
    - Modelo UML - restaurante, mesas, requisicoes, cliente (nota de grupo, 8 pontos)
	
  - Nota individual (12 pontos)
    - Implementações e testes + app
    - Documentação das classes.

### Commit	cb50813 (10/04)

Diagrama - sem métodos na mesa. mesa x requisicoes. - 6,5

Sem classe cliente - em outra branch - sem documentacao. - Lucas - 7,2

Sem classe mesa - em outra branch - sem teste. set sem validacao. - Gabrielly - 7,2

Sem main - em outra branch - "código partido" -  Gabriel + Arthur - 10 

Requisicao - construtor com parâmetros indevidos. set sem validação. fazendo busca de mesa. - Pedro Braga - 7,2

Restaurante - ok - Gustavo - 12

## Sprint 2 - Até 19/maio
  - Nota de grupo (6 pontos)
    - Modelo UML atualizado - cardápio e pedidos
	- Estrutura Spring
  
  - Nota individual (14 pontos)	
    - Implementações cardápio e pedidos
    - Controllers
    - Correções anteriores

### Commit 	8c090f6 (22/05)

Diagrama - métodos do menu - pedido x produto x menu - 4,8

Estrutura Spring - ok 

Menu - somente 'entidade', sem lógica de negócio - Arthur + Gabriel

Pedido - sem documentacao - Arthur  + Gabriel

Requisicao - com restaurante - sets desnecessarios - Gustavo - 7,2

Produto - sem validar valor - Arthur + Gabriel 7,2

Controllers - somente estrutura. - Lucas - 7,2

Main - confusão app x controllers - Arthur 

Restaurante - Sem cardápio - Arthur - 8,4

## Sprint 3 - Até 05/junho
  - Nota de grupo (6 pontos)
    - Modelo atualizado - menu fechado
  
  - Nota individual (14 pontos)	
    - Implementações menu fechado e app
    - Correções anteriores

### Revisão 12 junho
**Nota coletiva: 8,4**

Diagrama - desatualizado, sem menu fechado - Todos - 0 

Pedido - addProduto deve ser abstrato e implementado nas filhas. Não tem requisicao

PedidoFechado - vários if --> switch case

Requisição não pode ter "get pedidos". não insere produto  

Ainda sem Spring / controllers

Main - melhorou. Menu fechado x aberto (OCP/LSP)

## Sprint 4 - Apresentação em 26/06
  - Nota de grupo 6/6 pontos
	- Modelo atualizado
	- Apresentação
	
  - Nota individual 11,2/14 pontos
    - Contribuições na última sprint

### Comentários
- Modelo atualizado e ok
- PedidoFechado não valida os itens inseridos.
- Exceção muito básica e provavelmente em lugar errado (requisicaoInfo)
- Tratamento de exceções no main.
- confusão de model/controller em alguns momentos(p.ex, restaurante chamando repositório de mesas; criarAberto e criarFechado com código igual, atualizar sem info específica)


	
