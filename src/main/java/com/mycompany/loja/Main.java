package com.mycompany.loja;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Pessoa pessoa = null;
        ArrayList<Pessoa> pessoas = new ArrayList<>();
        ArrayList<Produto> produtos = new ArrayList<>();
        ArrayList<Pedido> pedidos = new ArrayList<>();
        ArrayList<Pagamento> pagamentos = new ArrayList<>();
        GerenciarPessoa gp = new GerenciarPessoa(pessoas);
        GerenciarProduto gpr = new GerenciarProduto(produtos);
        GerenciarPedido gpe = new GerenciarPedido(pedidos, produtos);
        GerenciarPagamento gpg = new GerenciarPagamento(pagamentos);
        Scanner sc = new Scanner(System.in);
        int opcao, resp, pos, escolha, opc = 0;
        String log = "", cpfCnpj, buscarCpfCnpj, formaPagamento = "";
        Pedido pedido;
        Produto produto;
        Pagamento pagamento;
        float quantidade = 0, valorTotal = 0;

        // Menu de opções
        do {
            System.out.println("1 - Gerenciar Pessoa");
            System.out.println("2 - Gerenciar Produto");
            System.out.println("3 - Gerenciar Pedido");
            System.out.println("4 - Gerenciar Pagamento");
            System.out.println("0 - Sair");
            escolha = sc.nextInt();
            sc.nextLine();

            switch (escolha) {
                case 1: // Gerenciar Pessoa
                    System.out.println("1 - Inserir cliente");
                    System.out.println("2 - Alterar cliente");
                    System.out.println("3 - Excluir cliente");
                    System.out.println("4 - Buscar cliente");
                    System.out.println("5 - Relatório");
                    System.out.println("0 - Sair");
                    opcao = sc.nextInt();
                    sc.nextLine();

                    switch (opcao) {
                        case 1: // Inserir cliente
                            System.out.println("-[Inclusão]-");
                            System.out.println("Digite o nome do cliente: ");
                            String nome = sc.nextLine();

                            System.out.println("Digite o endereço do cliente: ");
                            String endereco = sc.nextLine();

                            System.out.println("Digite o telefone do cliente: ");
                            String telefone = sc.nextLine();

                            System.out.println("Digite (1-Pessoa Física / 2-Pessoa Jurídica): ");
                            int tipo = sc.nextInt();
                            sc.nextLine();
                            if (tipo == 1) {
                                System.out.println("Digite o CPF do cliente: ");
                                String cpf = sc.nextLine();

                                try {
                                    System.out
                                            .println("Digite a data de nascimento do cliente no formato (yyyy-MM-dd) ");
                                    String data = sc.nextLine();
                                    LocalDate dataNascimento = LocalDate.parse(data);
                                    log = gp.inserirPessoa(nome, endereco, telefone, cpf, dataNascimento);
                                    if (!log.isEmpty()) {
                                        System.out.println("Erro: " + log + "Tente novamente.");
                                    }
                                } catch (Exception e) {
                                    System.out.println("Data inválida" + e);
                                }
                            } else if (tipo == 2) {
                                System.out.println("Digite o CNPJ do cliente: ");
                                String cnpj = sc.nextLine();

                                System.out.println("Digite a razão social do cliente: ");
                                String razaoSocial = sc.nextLine();

                                log = gp.inserirPessoaJuridica(nome, endereco, telefone, cnpj, razaoSocial);
                                if (!log.isEmpty()) {
                                    System.out.println("Erro: " + log + "Tente novamente.");
                                }
                            }
                            break;
                        case 2: // Alterar cliente
                            System.out.println("-[Alteração]-");
                            System.out.println("Digite o CPF/CNPJ do cliente: ");
                            buscarCpfCnpj = sc.nextLine();
                            pessoa = gp.consultarPessoas(buscarCpfCnpj);

                            if (pessoa instanceof PessoaFisica) {
                                mostrarClientes(gp.consultarPessoas(buscarCpfCnpj));

                                System.out.println("Confirmar alteração? (1-sim / 2-não)");
                                resp = sc.nextInt();
                                sc.nextLine();

                                while (resp != 1 && resp != 2) {
                                    System.out.println("Opção inválida. Digite 1 para sim ou 2 para não");
                                    resp = sc.nextInt();
                                    sc.nextLine();
                                }

                                if (resp == 1) {
                                    System.out.println("Digite o novo nome do cliente: ");
                                    String novoNome = sc.nextLine();

                                    System.out.println("Digite o novo endereço do cliente: ");
                                    String novoEndereco = sc.nextLine();

                                    System.out.println("Digite o novo telefone do cliente: ");
                                    String novoTelefone = sc.nextLine();

                                    System.out.println("Digite o novo CPF do cliente: ");
                                    String novoCpf = sc.nextLine();

                                    try {
                                        System.out.println(
                                                "Digite a nova data de nascimento do cliente no formato (yyyy-MM-dd)");
                                        String dataStr = sc.nextLine();
                                        LocalDate dataDeNascimento = LocalDate.parse(dataStr);
                                        log = gp.alterarPessoaFisica(buscarCpfCnpj, novoCpf, novoNome, novoEndereco,
                                                novoTelefone,
                                                dataDeNascimento);
                                    } catch (Exception e) {
                                        System.out.println("Data inválida" + e);
                                    }

                                    if (!log.isEmpty()) {
                                        System.out.println("Erro: " + log + " Tente novamente.");
                                    }

                                } else if (resp == 2) {
                                    System.out.println("Operação cancelada");
                                }
                            } else if (pessoa instanceof PessoaJuridica) {
                                mostrarClientes(gp.consultarPessoas(buscarCpfCnpj));

                                System.out.println("Confirmar alteração? (1-sim / 2-não)");
                                resp = sc.nextInt();
                                sc.nextLine();

                                if (resp == 1) {
                                    System.out.println("Digite o novo nome do cliente: ");
                                    String novoNome = sc.nextLine();

                                    System.out.println("Digite o novo endereço do cliente: ");
                                    String novoEndereco = sc.nextLine();

                                    System.out.println("Digite o novo telefone do cliente: ");
                                    String novoTelefone = sc.nextLine();

                                    System.out.println("Digite o novo CNPJ do cliente: ");
                                    String novoCnpj = sc.nextLine();

                                    System.out.println("Digite a nova razão social do cliente: ");
                                    String novaRazaoSocial = sc.nextLine();

                                    log = gp.alterarPessoaJuridica(buscarCpfCnpj, novoNome, novoEndereco, novoTelefone,
                                            novoCnpj,
                                            novaRazaoSocial);

                                    if (!log.isEmpty()) {
                                        System.out.println("Erro: " + log + " Tente novamente.");
                                    }

                                } else {
                                    System.out.println("Operação cancelada");
                                }
                            }
                            break;
                        case 3: // Excluir cliente
                            System.out.println("-[Excluir]-");
                            System.out.println("Digite o CPF/CNPJ do cliente: ");
                            cpfCnpj = sc.nextLine();
                            mostrarClientes(gp.consultarPessoas(cpfCnpj));

                            System.out.println("Confirmar exclusão? (1-sim / 2-não)");
                            resp = sc.nextInt();
                            sc.nextLine();
                            while (resp != 1 && resp != 2) {
                                System.out.println("Opção inválida. Digite 1 para sim ou 2 para não");
                                resp = sc.nextInt();
                                sc.nextLine();
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
                            break;
                        case 4: // Buscar cliente
                            System.out.println("-[Consulta]-");
                            System.out.println("Digite o CPF/CNPJ do cliente: ");
                            cpfCnpj = sc.nextLine();
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
                    System.out.println("5 - Relatório");
                    System.out.println("0 - Sair");
                    opcao = sc.nextInt();
                    sc.nextLine();
                    switch (opcao) {

                        case 1: // Inserir produto
                            System.out.println("-[Inclusão]-");
                            System.out.println("Digite o nome do produto: ");
                            String nome = sc.nextLine();

                            System.out.println("Digite a descrição do produto: ");
                            String descricao = sc.nextLine();

                            System.out.println("Digite o valor do produto: ");
                            float valor = sc.nextFloat();
                            sc.nextLine();

                            System.out.println("Digite a quantidade do produto: ");
                            quantidade = sc.nextInt();
                            sc.nextLine();

                            log = gpr.inserirProduto(nome, descricao, valor, quantidade);
                            if (!log.isEmpty()) {
                                System.out.println("Erro: " + log + "Tente novamente.");
                            } else {
                                System.out.println("Produto cadastrado com sucesso");
                            }
                            break;
                        case 2: // Alterar produto
                            System.out.println("-[Alteração]-");
                            System.out
                                    .println("Digite a posição do produto: entre 0 e " + (produtos.size() - 1) + " :");
                            pos = sc.nextInt();
                            sc.nextLine();

                            produto = gpr.consultarProduto(pos);
                            if (produto == null) {
                                System.out.println("Produto não encontrado");
                            } else {
                                mostrarProdutos(produto);

                                System.out.println("Confirmar alteração? (1-sim / 2-não)");
                                resp = sc.nextInt();
                                sc.nextLine();

                                while (resp < 0 || resp > 2) {
                                    System.out.println("Opção inválida. Digite 1 para sim ou 2 para não");
                                    resp = sc.nextInt();
                                    sc.nextLine();
                                }

                                if (resp == 1) {

                                    System.out.println("Digite o novo nome do produto: ");
                                    String novoNome = sc.nextLine();

                                    System.out.println("Digite a nova descrição do produto: ");
                                    String novaDescricao = sc.nextLine();

                                    System.out.println("Digite o novo valor do produto: ");
                                    float novoValor = sc.nextFloat();
                                    sc.nextLine();

                                    System.out.println("Digite a nova quantidade do produto: ");
                                    float novaQuantidade = sc.nextInt();
                                    sc.nextLine();

                                    log = gpr.alterarProduto(novoNome, novaDescricao, novoValor, novaQuantidade, pos);
                                    if (!log.isEmpty()) {
                                        System.out.println("Erro: " + log + "Tente novamente.");
                                    }

                                } else if (resp == 2) {
                                    System.out.println("Operação cancelada");
                                }
                            }
                            break;
                        case 3: // Excluir produto
                            System.out.println("-[Exclusão]-");
                            System.out
                                    .println("Digite a posição do produto: entre 0 e " + (produtos.size() - 1) + " :");
                            pos = sc.nextInt();
                            sc.nextLine();

                            produto = gpr.consultarProduto(pos);
                            if (produto == null) {
                                System.out.println("Produto não encontrado");
                            }
                            mostrarProdutos(produto);

                            System.out.println("Confirmar alteração? (1-sim / 2-não)");
                            resp = sc.nextInt();
                            sc.nextLine();

                            while (resp < 0 || resp > 2) {
                                System.out.println("Opção inválida. Digite 1 para sim ou 2 para não");
                                resp = sc.nextInt();
                                sc.nextLine();

                            }

                            if (resp == 1) {
                                log = gpr.excluirProduto(pos);
                                if (!log.isEmpty()) {
                                    System.out.println("Erro: " + log + "Tente novamente.");
                                } else {
                                    System.out.println("Produto excluído com sucesso");
                                }
                            } else if (resp == 2) {
                                System.out.println("Operação cancelada");
                            }
                            break;
                        case 4: // Consultar produto
                            System.out.println("-[Consulta]-");
                            System.out
                                    .println("Digite a posição do produto: entre 0 e " + (produtos.size() - 1) + " :");
                            pos = sc.nextInt();
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
                            if (!p.isEmpty()) {
                                for (Produto pro : p) {
                                    mostrarProdutos(pro);
                                }
                            } else {
                                System.out.println("Nenhum produto cadastrado");
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
                    System.out.println("5 - Relatório");
                    System.out.println("0 - Sair");
                    opcao = sc.nextInt();
                    sc.nextLine();
                    switch (opcao) {
                        case 1: // Inserir pedido

                            System.out.println("-[Inclusão]-");
                            System.out.println("Digite o CPF/CNPJ do cliente: ");
                            buscarCpfCnpj = sc.nextLine();
                            pessoa = gp.consultarPessoas(buscarCpfCnpj);

                            if (pessoa == null) {
                                System.out.println("Cliente não encontrado");
                                break;
                            }

                            mostrarClientes(pessoa);

                            System.out.println("Deseja adicionar este cliente ? (1-sim / 2-não)");
                            resp = sc.nextInt();
                            sc.nextLine();

                            while (resp != 1 && resp != 2) {
                                System.out.println("Opção inválida. Digite 1 para sim ou 2 para não");
                                resp = sc.nextInt();
                                sc.nextLine();
                            }

                            if (resp == 1) {

                                ArrayList<Produto> produtosDoPedido = new ArrayList<>();

                                do {

                                    int op;

                                    System.out.println("Digite a posição do produto: ");
                                    pos = sc.nextInt();
                                    produto = gpr.consultarProduto(pos);

                                    if (produto == null) {
                                        System.out.println("Produto não encontrado");
                                        break;
                                    }

                                    mostrarProdutos(produto);

                                    System.out.println("Confirmar produto? (1-sim / 2-não)");
                                    op = sc.nextInt();
                                    sc.nextLine();

                                    while (op != 1 && op != 2) {
                                        System.out.println("Opção inválida. Digite 1 para sim ou 2 para não");
                                        op = sc.nextInt();
                                        sc.nextLine();
                                    }

                                    System.out.println("Digite a quantidade do produto: ");
                                    quantidade = sc.nextFloat();
                                    sc.nextLine();

                                    produtosDoPedido.add(produto);
                                    valorTotal += (float) (produto.getPreco() * quantidade);

                                    System.out.println("Deseja adicionar outro produto? (1-sim / 2-não)");
                                    resp = sc.nextInt();
                                    sc.nextLine();

                                } while (resp == 1);

                                try {

                                    System.out.println("Digite a data do pedido: ");
                                    String dataStr = sc.nextLine();
                                    LocalDate data = LocalDate.parse(dataStr);

                                    log = gpe.inserirPedido(pessoa, produtosDoPedido, quantidade, valorTotal, data);

                                } catch (Exception e) {
                                    System.out.println("Data inválida" + e);
                                }

                            } else if (resp == 2) {
                                System.out.println("Operação cancelada");
                                break;
                            }
                            break;
                        case 2: // Alterar pedido
                            ArrayList<Produto> novosProdutos = new ArrayList<>();
                            System.out.println("-[Alteração]-");
                            System.out.println("Digite a posição do pedido");
                            int posPedido = sc.nextInt();
                            sc.nextLine();

                            pedido = gpe.consultarPedido(posPedido);
                            if (pedido == null) {
                                System.out.println("Pedido não encontrado.");
                                return;
                            }

                            mostrarPedidos(pedido);

                            System.out.println("Deseja alterar esse pedido? 1 - Sim ou 2 - Não: ");
                            resp = sc.nextInt();
                            sc.nextLine();

                            if (resp != 1) {
                                return;
                            }

                            System.out.println("Lista de clientes");
                            ArrayList<Pessoa> listaClientes = gp.relatorio();
                            for (Pessoa p : listaClientes) {
                                mostrarClientes(p);
                            }

                            // Alterar cliente
                            System.out.println("Digite o CPF/CNPJ do novo cliente:");
                            cpfCnpj = sc.nextLine();
                            pessoa = gp.consultarPessoas(cpfCnpj);
                            mostrarClientes(pessoa);
                            if (pessoa == null) {
                                System.out.println("Cliente não encontrado.");
                                return;
                            }

                            // Alterar produtos
                            do {
                                System.out.println("Digite a posição do produto:");
                                int posProduto = sc.nextInt();
                                sc.nextLine();
                                produto = gpr.consultarProduto(posProduto);

                                if (produto != null) {
                                    mostrarProdutos(produto);

                                    System.out.println("Deseja alterar esse produto? 1 - Sim ou 2 - Não: ");
                                    resp = sc.nextInt();
                                    sc.nextLine();

                                    if (resp == 1) {
                                        System.out.println("Digite o novo nome do produto:");
                                        String novoNome = sc.nextLine();
                                        System.out.println("Digite o novo preço:");
                                        float novoPreco = sc.nextFloat();
                                        sc.nextLine();
                                        System.out.println("Digite a nova descrição:");
                                        String novaDescricao = sc.nextLine();
                                        System.out.println("Digite a nova quantidade:");
                                        float novaQuantidade = sc.nextFloat();
                                        sc.nextLine();

                                        Produto novoProduto = new Produto(novoNome, novoPreco, novaDescricao,
                                                novaQuantidade);
                                        novosProdutos.add(novoProduto);
                                    }
                                }

                                System.out.println("Deseja alterar outro produto? 1 - Sim ou 2 - Não:");
                                resp = sc.nextInt();
                                sc.nextLine();
                            } while (resp == 1);

                            // Alterar data do pedido
                            System.out.println("Digite a nova data do pedido (AAAA-MM-DD):");
                            String novaDataStr = sc.nextLine();
                            LocalDate novaData = LocalDate.parse(novaDataStr);

                            // Atualizar o pedido
                            log = gpe.alterarPedido(pessoa, novosProdutos, novosProdutos.size(),
                                    calcularValorTotal(novosProdutos),
                                    posPedido, novaData);
                            if (log.isEmpty()) {
                                System.out.println("Pedido alterado com sucesso.");
                            } else {
                                System.out.println("Erro: " + log);
                            }
                            break;
                        case 3: // Excluir pedido
                            System.out.println("-[Exclusão]-");
                            System.out.println("Digite a posição do pedido: ");
                            pos = sc.nextInt();
                            sc.nextLine();
                            pedido = gpe.consultarPedido(pos);
                            if (pedido == null) {
                                System.out.println("Não existem pedidos cadastrados.");
                            } else {
                                mostrarPedidos(pedido);

                                System.out.println("Deseja excluir esse pedido? 1 - Sim ou 2 - Não");
                                resp = sc.nextInt();

                                while (resp != 1 && resp != 2) {
                                    System.out.println("Deseja excluir esse pedido? 1 - Sim ou 2 - Não");
                                    resp = sc.nextInt();
                                    sc.nextLine();
                                }

                                if (resp == 1) {
                                    log = gpe.excluirPedido(pos);
                                    if (log.isEmpty()) {
                                        System.out.println("Produto excluido com sucesso.");
                                    } else {
                                        System.out.println("Erro: " + log);
                                    }
                                } else {
                                    System.out.println("Operação cancelada.");
                                }

                            }
                            break;
                        case 4: // Buscar pedido
                            System.out.println("-[Consulta]-");
                            System.out.println("Digite a posição que deseja consultar:");
                            pos = sc.nextInt();
                            sc.nextLine();
                            pedido = gpe.consultarPedido(pos);
                            if (pedido != null) {
                                System.out.println("[Pedido encontrado:]");
                                mostrarPedidos(pedido);
                            } else {
                                System.out.println("Pedido não encontrado");
                            }
                            break;
                        case 5: // Relatório
                            System.out.println("-[Relatório]-");
                            ArrayList<Pedido> p = gpe.relatorio();
                            if (!p.isEmpty()) {
                                for (Pedido ped : p) {
                                    mostrarPedidos(ped);
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
                case 4: // Gerenciar Pagamento
                    System.out.println("1 - Inserir pagamento");
                    System.out.println("2 - Alterar pagamento");
                    System.out.println("3 - Excluir pagamento");
                    System.out.println("4 - Buscar pagamento");
                    System.out.println("5 - Relatório");
                    System.out.println("0 - Sair");
                    opcao = sc.nextInt();
                    sc.nextLine();
                    switch (opcao) {
                        case 1: // Inserir pagamento
                            System.out.println("-[Inclusão]-");

                            System.out.println("Escolha a forma de pagamento: ");
                            System.out.println("1 - Crédito");
                            System.out.println("2 - Débito");
                            System.out.println("3 - À vista");
                            int tipoPagamento = sc.nextInt();
                            sc.nextLine();

                            while (tipoPagamento != 1 && tipoPagamento != 2 && tipoPagamento != 3) {
                                System.out.println("Escolha a forma de pagamento: ");
                                System.out.println("1 - Crédito");
                                System.out.println("2 - Débito");
                                System.out.println("3 - À vista");
                                tipoPagamento = sc.nextInt();
                                sc.nextLine();
                            }
                            if (tipoPagamento == 1) {
                                formaPagamento = "Crédito";
                            } else if (tipoPagamento == 2) {
                                formaPagamento = "Débito";
                            } else if (tipoPagamento == 3) {
                                formaPagamento = "À vista";
                            }

                            System.out.println("Digite a data do pagamento: ");
                            String novadataPagamentoStr = sc.nextLine();
                            LocalDate novadataPagamento = LocalDate.parse(novadataPagamentoStr);

                            System.out.println("Digite a posição do pedido: ");
                            pos = sc.nextInt();
                            sc.nextLine();

                            pedido = gpe.consultarPedido(pos);
                            if (pedido == null) {
                                System.out.println("Pedido não encontrado.");
                                return;

                            }
                            mostrarPedidos(gpe.consultarPedido(pos));

                            System.out.println("Desjea adicionar este pedido? (1-sim / 2-não)");
                            resp = sc.nextInt();
                            sc.nextLine();

                            while (resp != 1 && resp != 2) {
                                System.out.println("Opção inválida. Digite 1 para sim ou 2 para não");
                                resp = sc.nextInt();
                                sc.nextLine();
                            }

                            if (resp == 1) {
                                System.err.println("Valor do pedido: " + pedido.getValorTotal());
                                System.out.println("Digite o valor a ser pago: ");
                                float valorPagamento = sc.nextFloat();
                                sc.nextLine();
                                float troco = 0;
                                troco = valorPagamento - pedido.getValorTotal();
                                if (troco < 0) {
                                    System.out.println("Valor insuficiente");
                                } else if (troco > 0) {
                                    System.out.println("Troco: " + troco);
                                } else {

                                    boolean status = true;
                                    log = gpg.inserirPagamento(formaPagamento, valorPagamento, novadataPagamento,
                                            status,
                                            pedido, troco);
                                    System.out.println("Pagamento realizado com sucesso.");
                                    if (!log.isEmpty()) {
                                        System.out.println("Erro: " + log);
                                    }
                                }
                            }

                            break;
                        case 2: // TODO Alterar pagamento
                            System.out.println("-[Alteração]-");
                            System.out.println("Digite a posição do pagamento: ");
                            pos = sc.nextInt();
                            sc.nextLine();

                            pagamento = gpg.consultarPagamento(pos);
                            mostrarPagamentos(gpg.consultarPagamento(pos));

                            if (pagamento == null) {
                                System.out.println("Pagamento não encontrado.");
                            } else {

                                System.out.println("Escolha a forma de pagamento: ");
                                System.out.println("1 - Crédito");
                                System.out.println("2 - Débito");
                                System.out.println("3 - À vista");
                                tipoPagamento = sc.nextInt();
                                sc.nextLine();

                                while (tipoPagamento != 1 && tipoPagamento != 2 && tipoPagamento != 3) {
                                    System.out.println("Escolha a forma de pagamento: ");
                                    System.out.println("1 - Crédito");
                                    System.out.println("2 - Débito");
                                    System.out.println("3 - À vista");
                                    tipoPagamento = sc.nextInt();
                                }
                                if (tipoPagamento == 1) {
                                    formaPagamento = "Crédito";
                                } else if (tipoPagamento == 2) {
                                    formaPagamento = "Débito";
                                } else if (tipoPagamento == 3) {
                                    formaPagamento = "À vista";
                                }

                                System.out.println("Digite a data do pagamento: ");
                                novadataPagamentoStr = sc.nextLine();
                                novadataPagamento = LocalDate.parse(novadataPagamentoStr);

                                System.out.println("Digite a posição do pedido: ");
                                pos = sc.nextInt();
                                sc.nextLine();

                                pedido = gpe.consultarPedido(pos);
                                mostrarPedidos(gpe.consultarPedido(pos));

                                System.out.println("Deseja realizar o pagamento desse pedido? 1 - Sim ou 2 - Não");
                                resp = sc.nextInt();

                                while (resp != 1 && resp != 2) {
                                    System.out.println("Deseja realizar o pagamento desse pedido? 1 - Sim ou 2 - Não");
                                    resp = sc.nextInt();
                                    sc.nextLine();
                                }

                                if (resp == 1) {

                                    Pagamento pag = pagamentos.get(pos);

                                    System.out.println("Digite o valor a ser pago: ");
                                    float valorPagamento = sc.nextFloat();
                                    sc.nextLine();
                                    float troco = 0;
                                    troco = valorPagamento - pedido.getValorTotal();
                                    if (troco < 0) {
                                        System.out.println("Valor insuficiente");
                                    } else if (troco > 0 || troco == 0) {
                                        System.out.println("Troco: " + troco);
                                        boolean status = true;
                                        gpg.alterarPagamento(formaPagamento, valorPagamento, novadataPagamento, status,
                                                pos, troco);
                                        System.out.println("Pagamento realuzado com sucesso.");
                                    }
                                } else {
                                    System.out.println("Operação cancelada.");
                                }

                            }
                            mostrarPagamentos(gpg.consultarPagamento(pos));

                            break;
                        case 3: // Excluir pagamento
                            System.out.println("-[Exclusão]-");
                            System.out.println("Digite a posição do pagamento: ");
                            pos = sc.nextInt();
                            sc.nextLine();

                            mostrarPagamentos(gpg.consultarPagamento(pos));

                            System.out.println("Confirmar exclusão? (1-sim / 2-não)");
                            resp = sc.nextInt();
                            sc.nextLine();

                            while (resp != 1 && resp != 2) {
                                System.out.println("Opção inválida. Digite 1 para sim ou 2 para não");
                                resp = sc.nextInt();
                                sc.nextLine();
                            }

                            if (resp == 1) {
                                log = gpg.excluirPagamento(pos);
                                System.out.println("Pagamento excluído com sucesso");
                            } else
                                System.out.println("Operação cancelada");

                            if (!log.isEmpty()) {
                                System.out.println("Erro: " + log + "Tente novamente.");
                            }
                            break;
                        case 4: // Buscar pagamento
                            System.out.println("-[Consultar]-");
                            System.out.println("Digite a posição do pagamento: ");
                            pos = sc.nextInt();
                            sc.nextLine();

                            pagamento = gpg.consultarPagamento(pos);
                            if (pagamento != null) {
                                System.out.println("Pagamento encontrado");
                                mostrarPagamentos(pagamento);
                            }
                            break;
                        case 5: // Relatório
                            System.out.println("-[Relatório]-");
                            ArrayList<Pagamento> p = gpg.relatorio();
                            if (!p.isEmpty()) {
                                System.out.println("Pagamentos encontrados:");
                                for (Pagamento pag : p) {
                                    mostrarPagamentos(pag);
                                }
                            } else {
                                System.out.println("Nenhum pagamento encontrado.");
                            }
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
        } while (escolha != 0);
        sc.close();

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

    public static void mostrarPedidos(Pedido pedido) {
        if (pedido == null) {
            System.out.println("Pedido inválido!");
            return;
        }
        System.out.println("\n--- Dados do Pedido ---");
        System.out.println("Cliente: " + pedido.getPessoa().getNome());
        System.out.println("Data do Pedido: " + pedido.getData());
        System.out.println("Valor Total: R$ " + pedido.getValorTotal());
        System.out.println("Quantidade total: " + pedido.getQuantidade());
        for (Produto pro : pedido.getProdutos()) {
            System.out.println("Nome: " + pro.getNome());
            System.out.println("Preço: R$ " + pro.getPreco());
        }
        System.out.println("-------------------------------------------\n");
    }

    private static float calcularValorTotal(ArrayList<Produto> produtos) {
        float total = 0;
        for (Produto produto : produtos) {
            total += produto.getPreco() * produto.getQuantidade();
        }
        return total;
    }

    public static void mostrarPagamentos(Pagamento pagamento) {
        if (pagamento == null) {
            System.out.println("Pagamento inválido.");
            return;
        }
        System.out.println("\n--- Dados do Pagamento ---");
        System.out.println("Forma de pagamento: " + pagamento.getFormaPagamento());
        System.out.println("Valor: " + pagamento.getValor());
        System.out.println("Data do pagamento: " + pagamento.getDataPagamento());
        System.out.println("Status do pagamento: " + pagamento.getStatus());
        System.out.println("-[Dados do pedido]");
        System.out.println("Valor Total: " + pagamento.getPedido().getValorTotal());
        System.out.println("-------------------------------------------\n");
    }

}
