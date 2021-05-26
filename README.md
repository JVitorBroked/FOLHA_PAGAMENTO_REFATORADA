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

  
**INTERPRETER**

  A classe _main contia os métodos find_employee_Commissioned(), find_employee_hourly() e find_employee_hourly()([aqui](https://github.com/JVitorBroked/Folha_Pagamento_P.S/blob/02b469339d2899e7da3183d137caafff7ff7cc6f/_main.java#L94)) que realizavam a mesma tarefa, entretanto aplicavam uma verificação diferente para buscar uma instancia de um objeto. O design pattern intepreter foi usado para solucionar esse problema. Foi criado a interface Specification([aqui](https://github.com/JVitorBroked/REFACTORED_PAYROLL/blob/main/Interfaces/Specification.java)) que contém o método abstrato IsSatisfied(), além de 3 classes([1](https://github.com/JVitorBroked/REFACTORED_PAYROLL/blob/a96e480278c1916e82993aa7fccb094f1176dad9/Class/NotSpec.java), [2](https://github.com/JVitorBroked/REFACTORED_PAYROLL/blob/a96e480278c1916e82993aa7fccb094f1176dad9/Class/HourlySpec.java), [3](https://github.com/JVitorBroked/REFACTORED_PAYROLL/blob/a96e480278c1916e82993aa7fccb094f1176dad9/Class/CommissionedSpec.java)) que implementam essa interface, no qual cada uma realiza uma condição diferente. E por fim, os métodos do smell foram substituídos pelo método find_employee() adicionado na classe UHIF() ([aqui](https://github.com/JVitorBroked/REFACTORED_PAYROLL/blob/a96e480278c1916e82993aa7fccb094f1176dad9/Class/UHIF.java#L11)).

  
**STRATEGY**

  O Pattern  Strategy foi usado para solucionar o smell contido no método processar_pagamento() que usava alguns ifs para verificar uma instancia([aqui](https://github.com/JVitorBroked/Folha_Pagamento_P.S/blob/02b469339d2899e7da3183d137caafff7ff7cc6f/_main.java#L436)). Logo foi criado o método abstrato ValueOfPayment() na Classe Employee([aqui](https://github.com/JVitorBroked/REFACTORED_PAYROLL/blob/b06a5995dec68eba24035a2880a27e9e45f79899/Class/Employee.java#L27)) e então implementado por cada subclasse([1](https://github.com/JVitorBroked/REFACTORED_PAYROLL/blob/b06a5995dec68eba24035a2880a27e9e45f79899/Class/Hourly.java#L48), [2](https://github.com/JVitorBroked/REFACTORED_PAYROLL/blob/b06a5995dec68eba24035a2880a27e9e45f79899/Class/Salaried.java#L21) , [3](https://github.com/JVitorBroked/REFACTORED_PAYROLL/blob/b06a5995dec68eba24035a2880a27e9e45f79899/Class/Commissioned.java#L59)). E então usado no novo método processar_pagamento()([aqui](https://github.com/JVitorBroked/REFACTORED_PAYROLL/blob/b06a5995dec68eba24035a2880a27e9e45f79899/Class/ManagerEmployee.java#L136));
  
  
**STRATEGY**

  Na classe PaymentSchedule foi usado o Pattern Strategy para solucionar o problema das condições no método processar_new_date_to_pay()(aqui). Portanto foi criado a interface Schedule(aqui) e os 3 métodos que a implementam (1, 2, 3), logo o método o processar_new_date_to_pay() foi reescrito(aqui). 

 
**MOVE METHOD**

  Os diversos metodos da classe _main(aqui) que realizam o gerenciamento dos empregados foram movidos para a classe ManagerEmployee(aqui) e os metodos handle’s e find’s movidos para classe UHIF que lida com métodos uteis para in/out(aqui).

  
**MOVE ACCUMULATION TO COLLECTING PARAMETER**
  
  A classe _main possuía diversos métodos que acumulava variáveis locais para utilizar em determinados métodos(aqui), portanto essas variáveis foram removidas e adicionado métodos que tratam diretamente essas variáveis como argumentos dos métodos(aqui).
  
</center>
