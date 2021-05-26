# Sistema de Folha de Pagamento Refatorado

<center>O objetivo do projeto é construir um sistema de folha de pagamento. O sistema consiste do
gerenciamento de pagamentos dos empregados de uma empresa. Além disso, o sistema deve
gerenciar os dados destes empregados, a exemplo os cartões de pontos. Empregados devem receber
o salário no momento correto, usando o método que eles preferem, obedecendo várias taxas e
impostos deduzidos do salário.
  
   - Alguns empregados são horistas. Eles recebem um salário por hora trabalhada. Eles
       submetem "cartões de ponto" todo dia para informar o número de horas trabalhadas naquele
       dia. Se um empregado trabalhar mais do que 8 horas, deve receber 1.5 a taxa normal
       durante as horas extras. Eles são pagos toda sexta-feira.
       
   - Alguns empregados recebem um salário fixo mensal. São pagos no último dia útil do mês
       (desconsidere feriados). Tais empregados são chamados "assalariados".
       
   - Alguns empregados assalariados são comissionados e portanto recebem uma comissão, um
       percentual das vendas que realizam. Eles submetem resultados de vendas que informam a
       data e valor da venda. O percentual de comissão varia de empregado para empregado. Eles
       são pagos a cada 2 sextas-feiras; neste momento, devem receber o equivalente de 2 semanas
       de salário fixo mais as comissões do período.
       
           o Empregados podem escolher o método de pagamento.
           o Podem receber um cheque pelos correios
           o Podem receber um cheque em mãos
           o Podem pedir depósito em conta bancária
   
   -  Alguns empregados pertencem ao sindicato (para simplificar, só há um possível sindicato).
        O sindicato cobra uma taxa mensal do empregado e essa taxa pode variar entre
        empregados. A taxa sindical é deduzida do salário. Além do mais, o sindicato pode
        ocasionalmente cobrar taxas de serviços adicionais a um empregado. Tais taxas de serviço
        são submetidas pelo sindicato mensalmente e devem ser deduzidas do próximo
        contracheque do empregado. A identificação do empregado no sindicato não é a mesma da
        identificação no sistema de folha de pagamento.
   
   - A folha de pagamento é rodada todo dia e deve pagar os empregados cujos salários vencem
        naquele dia. O sistema receberá a data até a qual o pagamento deve ser feito e calculará o
        pagamento para cada empregado desde a última vez em que este foi pago.
        
        
        
---

#### CODE SMELLS

Problemas de Design presentem no código:


**1. Duplicated Code.**

> Na classe _main existem três métodos estáticos que possuem objetivos e código similares, porem trabalham com classes diferentes, Line: 94 á 126; METODOS: find_employee_comissioned(), find_employee_hourly(), find_any_employee().


**2. Duplicated Code.**

> Vários métodos presentes na classe _main trabalham com entrada e saída de dados, como o método adicionar_empregado(), lancar_venda(),alterar_dados() etc. Tais         métodos utilizam trechos de código muito similares para tratar o problema mencionado.

**3. Long Method.**

> No método alterar_classe() da classe _main é usado alguns valores de um objeto Employee como parâmetro para o métodos construtores. Line 520, 529, 538.

**4. Long Method.**

> Muitas variáveis temporárias são usadas nos métodos estático lancar_venda() e adicionar_empregado() da classe _main. 

**5. Long Method.**

> O método adicionar_empregado() da classe _main possui ao todo 5 ifs, onde cada condicional possui uma grande quantidade de código e similar. Line 129.

**6. Long Method.**

> Os metodos processar_pagamento() e alterar_classe() na classe _main possui muitos condicionais que trabalham com diferentes interfaces.

**7. Long Method.**

> O método alterar_classe() usa diferentes condicionais para logica de retorno 

**8. Large Class.**

> A classe _main possui diversos métodos e dados com diferentes propósitos. No qual possui em torno de 575 linhas.

**9. Large Class.**

> A classe Employee possui ao todo 6 atributos
    
**10. Feature Envy.**

> Classe _main realiza diversas tarefas que incluem a classe Employee
    
**11. Message chains e Long Method.**

> O método processor_pagamento() possui longos encadeamentos na linha 471, 472. Possui também na linha 441 a 448.
    
**12- Data class.**
> Há no projeto três classes que são usadas basicamente para organizar dados, são elas: 
Sale.java, Union_service.java e Tme_card.

  
---

#### PATTERNS
  
  
</center>
