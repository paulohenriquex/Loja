package com.mycompany.loja;

import java.time.LocalDate;
import java.util.ArrayList;

public class GerenciarPedido {

    private ArrayList<Pedido> pedidos;
    private ArrayList<Produto> produtos;
    private Pessoa cliente;

    public GerenciarPedido(ArrayList<Pedido> ped, ArrayList<Produto> pro) {
        this.pedidos = ped;
        this.produtos = pro;
    }

    public String inserirPedido(Pessoa cliente, ArrayList<Produto> produtosPedido, float quantidade,
            float valorTotal, LocalDate data) {
        String log = "";

        if (cliente == null) {
            log = "Cliente inválido";
        } else if (produtosPedido == null || produtosPedido.isEmpty()) {
            log = "Nenhum produto cadastrado";
        } else if (quantidade <= 0) {
            log = "Quantidade inválida";
        } else if (valorTotal <= 0) {
            log = "Valor total inválido";
        } else if (data == null) {
            log = "Data inválida";
        } else {
            ArrayList<Produto> produtosCopia = new ArrayList<>(produtosPedido);
            Pedido pedido = new Pedido(cliente, produtosCopia, quantidade, valorTotal, data);
            pedidos.add(pedido);
            log = "Pedido inserido com sucesso";
        }
        return log;
    }
    public Pedido consultarPedido(int pos) {
        if (pos < 0 || pos >= pedidos.size()) {
            return null;
        } else {
            return pedidos.get(pos);
        }

    }

    public ArrayList<Pedido> relatorio() {
        return pedidos;
    }

}
