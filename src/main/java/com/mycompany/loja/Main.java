package com.mycompany.loja;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Pessoa> pessoas = new ArrayList<>();
        ArrayList<Produto> produtos = new ArrayList<>();
        ArrayList<Pedido> pedidos = new ArrayList<>();
        ArrayList<Pagamento> pagamentos = new ArrayList<>();
        GerenciarPessoa gp = new GerenciarPessoa(pessoas);
        GerenciarProduto gpr = new GerenciarProduto(produtos);
        GerenciarPedido gpe = new GerenciarPedido(pedidos);
        GerenciarPagamento gpa = new GerenciarPagamento(pagamentos);
        Scanner scn = new Scanner(System.in);
        Scanner scs = new Scanner(System.in);
        int opcao, resp;
        String log = "";
        Pessoa pessoa;
        Pedido pedido;
        Pagamento pagamento;

        // Menu de opções
        do {
            System.out.println("1 - Gerenciar Pessoa");
            System.out.println("2 - Gerenciar Produto");
            System.out.println("3 - Gerenciar Pedido");
            System.out.println("4 - Gerenciar Pagamento");
            System.out.println("0 - Sair");
            opcao = scn.nextInt();

            switch (opcao) {
                case 1: // Gerenciar Pessoa
                    System.out.println("1 - Inserir cliente");
                    System.out.println("2 - Alterar cliente");
                    System.out.println("3 - Excluir cliente");
                    System.out.println("4 - Buscar cliente");
                    System.out.println("0 - Relatório");
                    opcao = scn.nextInt();

                    switch (opcao) {
                        case 1: // Inserir cliente
                            System.out.println("Digite o nome do cliente: ");
                            String nome = scs.nextLine();

                            System.out.println("Digite o endereço do cliente: ");
                            String endereco = scs.nextLine();

                            System.out.println("Digite o telefone do cliente: ");
                            String telefone = scs.nextLine();

                            System.out.println("Digite (1-Pessoa Física / 2-Pessoa Jurídica): ");
                            int tipo = scn.nextInt();
                            if (tipo == 1) {
                                System.out.println("Digite o CPF do cliente: ");
                                String cpf = scs.nextLine();

                                try {
                                    System.out.println("Digite a data de nascimento do cliente no formato (yyyy-MM-dd) ");
                                    String data = scs.nextLine();
                                    LocalDate dataNascimento = LocalDate.parse(data);
                                    gp.inserirPessoa(nome, endereco, telefone, cpf, dataNascimento);

                                } catch (Exception e) {
                                    System.out.println("Data inválida" + e);
                                }
                            }else if(opcao == 2){
                                System.out.println("Digite o CNPJ do cliente: ");
                                String cnpj = scs.nextLine();

                                System.out.println("Digite a razão social do cliente: ");
                                String razaoSocial = scs.nextLine();

                                log = gp.inserirPessoaJuridica(nome, endereco, telefone, cnpj, razaoSocial);
                                if(!log.isEmpty()){
                                    System.out.println("Erro: " + log + "Tente novamente.");
                                }
                            }
                            break;
                        case 2: // Alterar cliente
                            break;
                        case 3: // Excluir cliente
                            break;
                        case 4: // Buscar cliente
                        System.out.println("Digite o CPF/CNPJ do cliente: ");
                        String cpfCnpj = scs.nextLine();
                        pessoa = gp.consultarPessoas(cpfCnpj);
                        if(pessoa != null){
                            log = "Cliente encontrado";
                            mostrarClientes(pessoa);
                        }else{
                            log = "Cliente não encontrado";
                        }
                        System.out.println(log);
                            break;
                        case 5: // Relatório
                            break;
                        case 0: // Sair
                            break;
                        default:
                            System.out.println("Opção inválida");
                            break;
                    }
                    break;
                case 2: // Gerenciar Produto
                    System.out.println("1 - Inserir produto");
                    System.out.println("2 - Alterar produto");
                    System.out.println("3 - Excluir produto");
                    System.out.println("4 - Buscar produto");
                    System.out.println("0 - Relatório");
                    opcao = scn.nextInt();
                    switch (opcao) {
                        case 1: // Inserir produto
                            break;
                        case 2: // Alterar produto
                            break;
                        case 3: // Excluir produto
                            break;
                        case 4: // Buscar produto
                            break;
                        case 5: // Relatório
                            break;
                        case 0: // Sair
                            break;
                        default:
                            System.out.println("Opção inválida");
                            break;
                    }
                    break;
                case 3: // Gerenciar Pedido
                    System.out.println("1 - Inserir pedido");
                    System.out.println("2 - Alterar pedido");
                    System.out.println("3 - Excluir pedido");
                    System.out.println("4 - Buscar pedido");
                    System.out.println("0 - Relatório");
                    opcao = scn.nextInt();
                    switch (opcao) {
                        case 1: // Inserir pedido
                            break;
                        case 2: // Alterar pedido
                            break;
                        case 3: // Excluir pedido
                            break;
                        case 4: // Buscar pedido
                            break;
                        case 5: // Relatório
                            break;
                        case 0: // Sair
                            break;
                        default:
                            System.out.println("Opção inválida");
                            break;
                    }
                    break;
                case 4: // Gerenciar Pagamento
                    System.out.println("1 - Inserir pagamento");
                    System.out.println("2 - Alterar pagamento");
                    System.out.println("3 - Excluir pagamento");
                    System.out.println("4 - Buscar pagamento");
                    System.out.println("0 - Relatório");
                    opcao = scn.nextInt();
                    switch (opcao) {
                        case 1: // Inserir pagamento
                            break;
                        case 2: // Alterar pagamento
                            break;
                        case 3: // Excluir pagamento
                            break;
                        case 4: // Buscar pagamento
                            break;
                        case 5: // Relatório
                            break;
                        case 0: // Sair
                            break;
                        default:
                            System.out.println("Opção inválida");
                            break;
                    }
                    break;
                case 0: // Sair
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        } while (opcao != 0);
        scn.close();
        scs.close();
    }

    public static void mostrarClientes(Pessoa pessoa) {
        System.out.println("\n--- Dados do Cliente ---");
        if (pessoa instanceof PessoaFisica pf) {
            System.out.println("Nome: " + pf.getNome());
            System.out.println("Endereço: " + pf.getEndereco());
            System.out.println("Telefone: " + pf.getTelefone());
            System.out.println("CPF: " + pf.getCpf());
            System.out.println("Data de Nascimento: " + pf.getDataDeNascimento());
        } else if (pessoa instanceof PessoaJuridica pj) {
            System.out.println("Nome: " + pj.getNome());
            System.out.println("Endereço: " + pj.getEndereco());
            System.out.println("Telefone: " + pj.getTelefone());
            System.out.println("CNPJ: " + pj.getCnpj());
            System.out.println("Razão Social: " + pj.getRazaoSocial());
        }
        System.out.println("-------------------------\n");
    }
    
}
