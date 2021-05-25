package Class;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class ManagerEmployee {
 
    Scanner input;
    ArrayList<Employee> list_of_employees;


    public ManagerEmployee(){
        this.list_of_employees = new ArrayList<Employee>();
        this.input = new Scanner(System.in);
    }

    public ArrayList<Employee> getList_of_employees() {
        return list_of_employees;
    }

    public void adicionar_empregado(){
    
        String nome = UHIF.handle_input_string("Digite o nome do empregado: ");
        String endereco = UHIF.handle_input_string("Digite o endereco do empregado: ");

        System.out.print("Qual o tipo de empregado \n1 : Salariado\n2 : Hourly\n3 : Commissioned\n");
        int op = input.nextInt();
        input.nextLine();

        if(op == 1){
            
            Salaried new_employee = new Salaried(nome,endereco, 
                            UHIF.handle_input_double("QUAL O SALARIO DO EMPREGADO?: "));
            list_of_employees.add(new_employee);            
        
        }else if(op == 2){
            System.out.println();

            Hourly new_employee = new Hourly(nome,endereco, 
                            UHIF.handle_input_double("QUAL O VALOR DA HORA DE TRABALHO DO EMPREGADO?: "));
            list_of_employees.add(new_employee);

        }else if(op == 3){
                                    
            Commissioned new_employee = new Commissioned(nome,endereco,
                                UHIF.handle_input_double("QUAL O SALARIO DO EMPREGADO?: "), 
                                        UHIF.handle_input_double("QUAL A COMISSAO DO EMPREGADO?: "));
            list_of_employees.add(new_employee);

        }else{
            System.out.print("ENTRADA INVALIDA! EMPREGADO NAO CADASTRADO");
            return;
        }

        String opcao = UHIF.handle_input_string("O EMPREGADO FAZ PARTE DO SINDICATO?(S/N): ");

        if(opcao.equals("S") || opcao.equals("s")){
            list_of_employees.get(list_of_employees.size()-1).add_syndicate(UHIF.handle_input_double("DIGITE O VALOR DA TAXA SINDICAL MENSAL: "));
        }

        //adicao de metodo de pagamento
        String pm = UHIF.handle_input_string("QUAL O METODO DE PAGAMENTO (correios, deposito, cheque)?: ");
        change_payment_method((list_of_employees.get(list_of_employees.size()-1)), pm);

        System.out.printf("ID gerado para %s: %s \n", nome, (list_of_employees.get(list_of_employees.size()-1)).getId().toString());

        String o = UHIF.handle_input_string(nome + " ADICIONADO COM SUCESSO\nDESEJA ADICIONAR OUTRO EMPREGADO S/N: ");
        if(o.equals("S")|| o.equals("s")){
            adicionar_empregado();
        }
    }

    public void change_payment_method(Employee emp, String pm){
        emp.setPaymentMethod(pm);
    }

    public void Remove_employee(){

        String nome =  UHIF.handle_input_string("DIGITE NOME DO EMPREGADO: ");
        for (Employee employee : list_of_employees) {
            if(employee.getNome().equals(nome)){
                list_of_employees.remove(employee);
                break;
            }
        }
    }

    public void lancar_taxa_de_servico(){

        String nome = UHIF.handle_input_string("DIGITE O NOME DO EMPREGADO PARA LANCAR TAXA: ");
        
        for (Employee employee : list_of_employees) {
            if(employee.getNome().equals(nome)){
                if(employee.getSyndicate() != null){
                    String name_service = UHIF.handle_input_string("DIGITE O NOME DO SERVICO USADO: ");                 
                    double value_taxe = UHIF.handle_input_double("DIGITE O VALOR DA TAXA: ");
                    employee.getSyndicate().add_union_service_taxe(name_service, value_taxe);
                }else{
                    System.out.println("EMPREGADO NAO FAZ PARTE DO SINDICATO!");
                }
                break;
            }           
        }

        UHIF.handle_interface();
    }

    public void run_payment_today(){

        int number_emp_today = 0;
        for (Employee employee : list_of_employees) {
            if(employee.getPaymentschedule().getNextDatePayment().equals(LocalDate.now())){
                number_emp_today++;
                System.out.println("EMPREGAO A SER PAGO HOJE: " + employee.getNome());
                processar_pagamento(employee);
            }
        }
        if(number_emp_today == 0){
            System.out.println("NAO HA EMPREGADO PARA PAGAR HOJE");
        }

        UHIF.handle_interface();
    }


    public static void processar_pagamento(Employee employee){

        double syndicate_taxe = 0;
        double syndicate_taxe_month = 0;
        double value = 0;
        if(employee.getSyndicate() != null){
            syndicate_taxe += employee.getSyndicate().total_value_to_pay();
            syndicate_taxe_month = employee.PayMonthlyFee();
        }
            value += employee.valueOfPayment();

            value -= syndicate_taxe;
            value -= syndicate_taxe_month;
            System.out.println("Valor das taxas dos servicos usados do sindicato: " + syndicate_taxe + "\nValor da taxa mensal sindicato: " + syndicate_taxe_month);
        
            employee.getPaymentschedule().setLast_payment(employee.getPaymentschedule().getNextDatePayment());
            employee.getPaymentschedule().setNextDatePayment(employee.getPaymentschedule().Processar_new_date_to_pay());

            employee.getPaymentMethod().do_payment(value);
    }

    public void alterar_dados(String nome){

        for (Employee employee : list_of_employees) {
            if(employee.getNome().equals(nome)){

                System.out.println("EMPREGADO " + employee.getNome() + " encontrado");
                System.out.print("OQUE DESEJA ALTERAR!\n1 - Nome\n2 - Endereco\n3 - Tipo de Empregado\n4 - Metodo de pagamento\n5 - Pertencimento ao sindicato\n6 - Taxa sindical\nDigite a opção: ");                
                int opcao = input.nextInt();
                input.nextLine();
                switch (opcao) {
                    case 1:
                        String new_name = UHIF.handle_input_string("DIGITE O NOVO NOME: ");
                        employee.setNome(new_name); 
                        System.out.println("NOME ALTERADO COM SUCESSO PARA " + new_name);
                        break;
                    case 2:
                        employee.setEndereco(UHIF.handle_input_string("DIGITE O NOVO ENDEREÇO: "));
                        break;
                    case 3:
                        String new_type = UHIF.handle_input_string("ALTERAR PARA QUAL TIPO?(h - hourly, c - commissioned, s - salaried): ");
                        Employee new_em = alterar_classe(employee, new_type);
                        if(new_em != null){
                            list_of_employees.remove(employee);
                            list_of_employees.add(new_em);
                        }
                        break;
                    case 4:
                        change_payment_method(employee, 
                                        UHIF.handle_input_string("ALTERAR PARA QUAL METODO(correios, deposito, cheque)?: "));
                        break;
                    case 5:
                        change_syndicate_on(employee);
                        break;
                    case 6:
                        change_monthly_fee(employee);
                        break;
                    default:
                        System.out.println("OPCAO INVALIDA");
                        break;
                }
            break;
            }
        }
        UHIF.handle_interface();
    }

    public void change_syndicate_on(Employee employee){
        
        if(employee.getSyndicate() != null){
            employee.setSyndicate(null);
            System.out.println("AGORA O EMPREGADO " + employee.getNome() + " NAO PERTENCE MAIS AO SINDICATO");    
        }else{
            Double taxe = UHIF.handle_input_double("DIGITE A TAXA SINDICAL: ");
            employee.add_syndicate(taxe);
            System.out.println("AGORA O EMPREGADO " + employee.getNome() + " PERTENCE AO SINDICATO");
        }
    }

    public void lancar_cartao_ponto(){
        
        Hourly emp = (Hourly) UHIF.find_Employee(list_of_employees, new HourlySpec(),
                        UHIF.handle_input_string("De quem é o cartao de ponto? "));

        if(emp != null){

            System.out.print("DIGITE HORA DE ENTRADA: ");
            int time_in = input.nextInt();
            System.out.print("DIGITE HORA DE SAIDA: ");
            int time_out = input.nextInt();
            input.nextLine();
            
            if(time_in >= 0 && time_in < 24 && time_out >= 0 && time_out < 24 && time_in < time_out){
                emp.add_time_card(time_in, time_out);
                System.out.println("CARTAO ADICIONADO COM SUCESSO!");
            }else{
                System.out.println("HORARIO INVALIDO!");
            }
        }else{
            System.out.println("EMPREGADO NAO ENCONTRADO!");
        }
        UHIF.handle_interface();
    }

    public void change_monthly_fee(Employee employee){

        if(employee.getSyndicate() != null){
            employee.getSyndicate().setMonthly_fee(UHIF.handle_input_double("DIGITE A NOVA TAXA SINDICAL: "));
        }else{
            System.out.println("EMPREGADO NAO FAZ PARTE DO SINDICATO");
        }
        UHIF.handle_interface();       
    }


    public Employee alterar_classe(Employee employee, String new_type){

        //add new object and remove the old one

        if(new_type.equals("h") && !(employee instanceof Hourly)){
            
            Hourly new_emp = new Hourly(employee.getNome(), employee.getEndereco(), 
                                        UHIF.handle_input_double("DIGITE O VALOR DA HORA DO EMPREGADO: "));

            new_emp.setSyndicate(employee.getSyndicate());
            new_emp.setPaymentMethod(employee.getPaymentMethod());
            return new_emp;

        }else if(new_type.equals("c") && !(employee instanceof Commissioned)){

            Commissioned new_emp = new Commissioned(employee.getNome(), employee.getEndereco(),
                                                        UHIF.handle_input_double("Qual o salario do empregado?: ")
                                                                ,UHIF.handle_input_double("Qual a comissao do empregado?: "));
            new_emp.setSyndicate(employee.getSyndicate());
            new_emp.setPaymentMethod(employee.getPaymentMethod());
            return new_emp;

        }else if(new_type.equals("s") && !(employee instanceof Salaried)){

            Salaried new_emp = new Salaried(employee.getNome(), employee.getEndereco(),
                                                UHIF.handle_input_double("QUAL O SALARIO DO EMPREGADO?: "));

            new_emp.setSyndicate(employee.getSyndicate());
            new_emp.setPaymentMethod(employee.getPaymentMethod());
            return new_emp;

        }else{
            System.out.println("EMPREGADO JA É DO TIPO ESCOLHIDO");
            return null;
        }
    }

    public void lancar_venda(){
    
        Commissioned employee = (Commissioned) UHIF.find_Employee(list_of_employees,
                                    new CommissionedSpec(), 
                                    UHIF.handle_input_string("DIGITE NOME DO EMPREGADO QUE REALIZOU A VENDA: "));
                    
        //VERIFICA SE EMPREGADO ENCONTRADO
        if(employee != null){
            
            String produto = UHIF.handle_input_string("Digite o nome do produto vendido: ");
            System.out.print("Digite a quantidade de " + produto + " vendido: ");
            int quantidade = input.nextInt();
            double preco_unitario = UHIF.handle_input_double("Digite o valor unitario do produto " + produto + ": ");
            String data = UHIF.handle_input_string("Digite a data da venda: ");
            employee.add_sale(produto, quantidade, preco_unitario, data);
            System.out.println("Produto " + produto + " adicionado com sucesso ao empregado " + employee.getNome());

        }else{
            System.out.println("EMPREGADO NAO ENCONTRADO");
        }
        UHIF.handle_interface();
    }

    public void change_payment_schedule(){

        Employee emp = UHIF.find_Employee(list_of_employees, new NotSpec(), 
                            UHIF.handle_input_string("DIGITE O EMPREGADO PARA MUDAR A AGENDA DE PAGAMENTO: "));
        
        if(emp != null){
            emp.change_payment_schedule(UHIF.handle_input_string("QUAL A NOVA AGENDA DE PAGAMENTO(semanal, bisemanal, mensal): "));
            System.out.println("AGENDA DE PAGAMENTO ALTERADA");
        }else{
            System.out.println("EMPREGADO NAO ENCONTRADO");
        }
        UHIF.handle_interface();
    }

    public void listar(){

        if(list_of_employees.size() <= 0){
            System.out.println("NAO HA EMPREGADOS CADASTRADOS!");
        }else{
             for(Employee emp : list_of_employees){
                System.out.printf("%s\n", emp.getNome());
            }
        }
        UHIF.handle_interface();
    }

 
    public void listar_cartao_pointo(){

        Hourly emp = (Hourly) UHIF.find_Employee(list_of_employees, new HourlySpec(), 
                                UHIF.handle_input_string("DIGITE O NOME DO EMPREGADO HOURLY: "));   
        
        if(emp != null){
            emp.list_time_card();
        }else{
            System.out.println("USUARIO NAO ENCONTRADO");
        }
        
        UHIF.handle_interface();
    }

    public void listar_services_usados(){

        String nome = UHIF.handle_input_string("DIGITE O NOME DO EMPREGADO: ");

        for (Employee employee : list_of_employees){
            if(employee.getNome().equals(nome) && employee.getSyndicate() != null){
                employee.getSyndicate().listar_services();
            }
        }

        UHIF.handle_interface();
    }

    public void Next_Payment_day(){

        String nome = UHIF.handle_input_string("DIGITE NOME DO EMPREGADO PARA VISULIZAR PROXIMO DIA DE PAGAMENTO: ");

        for(Employee employee : list_of_employees) {
           if(employee.getNome().equals(nome)){
               System.out.println("Proximo dia de pagamento: " + employee.getPaymentschedule().getNextDatePayment());
           }    
        }
        UHIF.handle_interface();
    }

    public void show_method_payment(){

        String nome = UHIF.handle_input_string("DIGITE O NOME DO EMPREGADO: "); 
        
        Employee emp = UHIF.find_Employee(list_of_employees, new NotSpec(), nome);

        if(emp != null){
            emp.getPaymentMethod().do_payment(10000);
        }

        UHIF.handle_interface();
    }

    public void change_day_of_payment(){

        Employee emp = UHIF.find_Employee(list_of_employees, new NotSpec(), 
                        UHIF.handle_input_string("MUDAR PROXIMO DIA DE PAGAMENTO DO: "));

        emp.getPaymentschedule().setNextDatePayment(LocalDate.now());
        System.out.println("MUDADO COM SUCESSO PARA: " + LocalDate.now());
        UHIF.handle_interface();
    }    
}
