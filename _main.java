import Class.*;

public class _main{

     public static void main(String[] args){

        ManagerEmployee managerEmployee = new ManagerEmployee();
        while(true){    
            
            System.out.println("\n*******DIGITE O CODIGO DA FUNCAO DESEJADA:\n\nCODIGO |    FUNCAO ");
            System.out.println("   1   |  ADICAO DE EMPREGADO\n   2   |  REMOCAO DE EMPREGADO\n   3   |  LANCAR UM CARTAO DE PONTO\n   4   |  LANCAR UM RESULTADO VENDA\n   5   |  LANCAR UMA TAXA DE SERVICO\n   6   |  ALTERAR DADOS DE UM EMPREGADO\n   7   |  REALIZAR FOLHA DE PAGAMENTO PARA HOJE\n   8   |  UNDO/REDO\n   9   |  AGENDA DE PAGAMENTO\n   10  |  CRIACAO DE NOVA AGENDA DE PAGAMENTO\n");
            
            int funcao = UHIF.handle_input_int("DIGITE: ");
            //processar escolha
            switch (funcao) {
                case 1:
                    managerEmployee.adicionar_empregado();
                    break;
                case 2:
                    managerEmployee.Remove_employee();           
                    break;
                case 3:
                    managerEmployee.lancar_cartao_ponto();
                    break;
                case 4:
                    managerEmployee.lancar_venda();
                    break;
                case 5:
                    managerEmployee.lancar_taxa_de_servico();
                    break;
                case 6:
                    managerEmployee.alterar_dados(UHIF.handle_input_string("QUAL EMPREGADO DESEJA ALTERAR OS DADOS?\nDIGITE NOME DO EMPREGADO: "));
                    break;
                case 7:
                    managerEmployee.run_payment_today();
                    break;
                case 8:
                    System.out.println("Building...");
                    UHIF.handle_interface();
                    break;
                case 9:
                    managerEmployee.change_payment_schedule();                    
                    break;
                case 10:
                    System.out.println("Building...");
                    UHIF.handle_interface();
                    break;
                case 11:
                    managerEmployee.Next_Payment_day();
                    break;
                case 12:
                    managerEmployee.show_method_payment();
                    break;
                case 13:
                    managerEmployee.listar();
                    break;
                case 14:
                    managerEmployee.change_day_of_payment();
                    break;
                case 15:
                    Commissioned emp = (Commissioned) UHIF.find_Employee(managerEmployee.getList_of_employees()
                                                            , new CommissionedSpec() , 
                                                                    UHIF.handle_input_string("DIGITE EMPREGADO PRA VER VENDAS: "));                
                    if(emp != null){
                        emp.listar_vendas();
                    }else{
                        System.out.println("EMPREGADO NAO ENCONTRADO");
                    }              
                    UHIF.handle_interface();
                    break;
                default:
                    System.out.println("opcao invalida");
                    break;
             }   
        }
    }
}