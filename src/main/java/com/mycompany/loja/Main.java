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
        int opcao, resp,pos;
        String log = "", cpfCnpj;
        Pessoa pessoa;
        Pedido pedido;
        Produto produto;
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
                    System.out.println("5 - Relatório");
                    System.out.println("0 - Sair");
                    opcao = scn.nextInt();

                    switch (opcao) {
                        case 1: // Inserir cliente
                            System.out.println("-[Inclusão]-");
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
                                    System.out
                                            .println("Digite a data de nascimento do cliente no formato (yyyy-MM-dd) ");
                                    String data = scs.nextLine();
                                    LocalDate dataNascimento = LocalDate.parse(data);
                                    gp.inserirPessoa(nome, endereco, telefone, cpf, dataNascimento);

                                } catch (Exception e) {
                                    System.out.println("Data inválida" + e);
                                }
                            } else if (opcao == 2) {
                                System.out.println("Digite o CNPJ do cliente: ");
                                String cnpj = scs.nextLine();

                                System.out.println("Digite a razão social do cliente: ");
                                String razaoSocial = scs.nextLine();

                                log = gp.inserirPessoaJuridica(nome, endereco, telefone, cnpj, razaoSocial);
                                if (!log.isEmpty()) {
                                    System.out.println("Erro: " + log + "Tente novamente.");
                                }
                            }
                            break;
                        case 2: // Alterar cliente

                            System.out.println("-[Alteração]-");
                            System.out.println("Digite o CPF/CNPJ do cliente: ");
                            cpfCnpj = scs.nextLine();
                            pessoa = gp.consultarPessoas(cpfCnpj);

                            if (pessoa instanceof PessoaFisica) {
                                mostrarClientes(gp.consultarPessoas(cpfCnpj));

                                System.out.println("Confirmar alteração? (1-sim / 2-não)");
                                resp = scn.nextInt();

                                while (resp != 1 && resp != 2) {
                                    System.out.println("Opção inválida. Digite 1 para sim ou 2 para não");
                                    resp = scn.nextInt();
                                }

                                if (resp == 1) {
                                    System.out.println("Digite o novo nome do cliente: ");
                                    String novoNome = scs.nextLine();

                                    System.out.println("Digite o novo endereço do cliente: ");
                                    String novoEndereco = scs.nextLine();

                                    System.out.println("Digite o novo telefone do cliente: ");
                                    String novoTelefone = scs.nextLine();

                                    System.out.println("Digite o novo CPF do cliente: ");
                                    String novoCpf = scs.nextLine();

                                    try {
                                        System.out.println(
                                                "Digite a nova data de nascimento do cliente no formato (yyyy-MM-dd)");
                                        String dataStr = scs.nextLine();
                                        LocalDate dataDeNascimento = LocalDate.parse(dataStr);
                                        gp.alterarPessoaFisica(novoCpf, novoNome, novoEndereco, novoTelefone,
                                                dataDeNascimento);
                                    } catch (Exception e) {
                                        System.out.println("Data inválida" + e);
                                    }
                                } else if (resp == 2) {
                                    System.out.println("Operação cancelada");
                                }
                            } else if (pessoa instanceof PessoaJuridica) {
                                mostrarClientes(gp.consultarPessoas(cpfCnpj));

                                System.out.println("Confirmar alteração? (1-sim / 2-não)");
                                resp = scn.nextInt();

                                if (resp == 1) {
                                    System.out.println("Digite o novo nome do cliente: ");
                                    String novoNome = scs.nextLine();

                                    System.out.println("Digite o novo endereço do cliente: ");
                                    String novoEndereco = scs.nextLine();

                                    System.out.println("Digite o novo telefone do cliente: ");
                                    String novoTelefone = scs.nextLine();

                                    System.out.println("Digite o novo CNPJ do cliente: ");
                                    String novoCnpj = scs.nextLine();

                                    System.out.println("Digite a nova razão social do cliente: ");
                                    String novaRazaoSocial = scs.nextLine();

                                    gp.alterarPessoaJuridica(novoNome, novoEndereco, novoTelefone, novoCnpj,
                                            novaRazaoSocial);
                                } else if (resp == 2) {
                                    System.out.println("Operação cancelada");
                                }
                            }
                            break;
                        case 3: // Excluir cliente
                            System.out.println("-[Excluir]-");
                            System.out.println("Digite o CPF/CNPJ do cliente: ");
                            cpfCnpj = scs.nextLine();
                            mostrarClientes(gp.consultarPessoas(cpfCnpj));

                            System.out.println("Confirmar exclusão? (1-sim / 2-não)");
                            resp = scn.nextInt();

                            while (resp != 1 && resp != 2) {
                                System.out.println("Opção inválida. Digite 1 para sim ou 2 para não");
                                resp = scn.nextInt();
                            }

                            if (resp == 1) {
                                log = gp.excluirPessoa(cpfCnpj);
                                if (!log.isEmpty()) {
                                    System.out.println("Erro: " + log + "Tente novamente.");
                                } else {
                                    System.out.println("Cliente excluído com sucesso");
                                    break;
                                }
                            } else if (resp == 2) {
                                System.out.println("Operação cancelada");
                            }

                            log = gp.excluirPessoa(cpfCnpj);
                            if (!log.isEmpty()) {
                                System.out.println("Erro: " + log + "Tente novamente.");
                            } else {
                                System.out.println("Cliente excluído com sucesso");
                            }
                            break;
                        case 4: // Buscar cliente
                            System.out.println("-[Consulta]-");
                            System.out.println("Digite o CPF/CNPJ do cliente: ");
                            cpfCnpj = scs.nextLine();
                            pessoa = gp.consultarPessoas(cpfCnpj);
                            if (pessoa != null) {
                                log = "Cliente encontrado";
                                mostrarClientes(pessoa);
                            } else {
                                log = "Cliente não encontrado";
                            }
                            System.out.println(log);
                            break;
                        case 5: // Relatório
                            System.out.println("-[Relatório]-");
                            ArrayList<Pessoa> p = gp.relatorio();
                            for (Pessoa pe : p) {
                                mostrarClientes(pe);
                            }
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
                            System.out.println("-[Inclusão]-");
                            System.out.println("Digite o nome do produto: ");
                            String nome = scs.nextLine();

                            System.out.println("Digite a descrição do produto: ");
                            String descricao = scs.nextLine();

                            System.out.println("Digite o valor do produto: ");
                            float valor = scn.nextFloat();

                            System.out.println("Digite a quantidade do produto: ");
                            float quantidade = scn.nextInt();
                            gpr.inserirProduto(nome, descricao, valor, quantidade);
                            break;
                        case 2: // Alterar produto
                            System.out.println("-[Alteração]-");
                            System.out.println("Digite a posição do produto: entre 0 e " + (produtos.size()-1)+ " :");
                            pos = scn.nextInt();

                            produto = gpr.consultarProduto(pos);
                            mostrarProdutos(produto);

                            System.out.println("Confirmar alteração? (1-sim / 2-não)");
                            resp = scn.nextInt();

                            while (resp <0 || resp>2) {
                                System.out.println("Opção inválida. Digite 1 para sim ou 2 para não");
                                resp = scn.nextInt();
                            }

                            if(resp == 1){

                                System.out.println("Digite o novo nome do produto: ");
                                String novoNome = scs.nextLine();
    
                                System.out.println("Digite a nova descrição do produto: ");
                                String novaDescricao = scs.nextLine();
    
                                System.out.println("Digite o novo valor do produto: ");
                                float novoValor = scn.nextFloat();
    
                                System.out.println("Digite a nova quantidade do produto: ");
                                float novaQuantidade = scn.nextInt();
    
                                gpr.alterarProduto(novoNome, novaDescricao, novoValor, novaQuantidade,pos);
    
                            }else if(resp == 2){
                                System.out.println("Operação cancelada");
                            }

                            break;
                        case 3: // Excluir produto
                            System.out.println("-[Exclusão]-");
                            System.out.println("Digite a posição do produto: entre 0 e " + (produtos.size()-1)+ " :");
                            pos = scn.nextInt();

                            produto = gpr.consultarProduto(pos);
                            mostrarProdutos(produto);


                            System.out.println("Confirmar alteração? (1-sim / 2-não)");
                            resp = scn.nextInt();

                            while (resp <0 || resp>2) {
                                System.out.println("Opção inválida. Digite 1 para sim ou 2 para não");
                                resp = scn.nextInt();
                            }

                            if(resp == 1){
                                log = gpr.excluirProduto(pos);
                                System.out.println("Produto excluído com sucesso");
    
                            }else if(resp == 2){
                                System.out.println("Operação cancelada");
                            }
                            break;
                        case 4: // Consultar produto
                            System.out.println("-[Consulta]-");
                            System.out.println("Digite a posição do produto: entre 0 e " + (produtos.size()-1)+ " :");
                            pos = scn.nextInt();
                            produto = gpr.consultarProduto(pos);
                            if (produto != null) {
                                System.out.println("Produto encontrado");
                                mostrarProdutos(produto);
                            } else {
                                System.out.println("Produto não encontrado");
                            }
                            break;
                        case 5: // Relatório
                            System.out.println("-[Relatório]-");
                            ArrayList<Produto> p = gpr.relatorio();
                            if(!p.isEmpty()){
                                for(Produto pro:p){
                                    mostrarProdutos(pro);
                                }
                            }
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
                            System.out.println("-[Inclusão]-");
                            break;
                        case 2: // Alterar pedido
                            System.out.println("-[Alteração]-");
                            break;
                        case 3: // Excluir pedido
                            System.out.println("-[Exclusão]-");
                            break;
                        case 4: // Buscar pedido
                            System.out.println("-[Consulta]-");
                            break;
                        case 5: // Relatório
                            System.out.println("-[Relatório]-");
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
                            System.out.println("-[Inclusão]-");
                            break;
                        case 2: // Alterar pagamento
                            System.out.println("-[Alteração]-");
                            break;
                        case 3: // Excluir pagamento
                            System.out.println("-[Exclusão]-");
                            break;
                        case 4: // Buscar pagamento
                            System.out.println("-[Consultar]-");
                            break;
                        case 5: // Relatório
                            System.out.println("-[Relatório]-");
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
        System.out.println("-------------------------------------------\n");
    }

    public static void mostrarProdutos(Produto produto) {
        System.out.println("\n--- Dados do Produto ---");
        System.out.println("Nome: " + produto.getNome());
        System.out.println("Descrição: " + produto.getDescricao());
        System.out.println("Valor: " + produto.getPreco());
        System.out.println("Quantidade: " + produto.getQuantidade());
        System.out.println("-------------------------------------------\n");
    }

}
