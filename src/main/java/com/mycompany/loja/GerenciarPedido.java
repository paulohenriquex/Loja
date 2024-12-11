package com.mycompany.loja;

import java.util.ArrayList;

public class GerenciarPedido {

    private ArrayList<Pessoa> clientes;
    private ArrayList<Produto> produtos;
    private ArrayList<Pedido> pedidos;


    public GerenciarPedido(ArrayList<Pedido> ped, ArrayList<Produto> pro, ArrayList<Pessoa> cli) {
        this.pedidos = ped;
        this.produtos = pro;
        this.clientes = cli;
    }

    public String inserirPedido() {
        String log = "";
        return log;
    }

    public ArrayList<Pedido> relatorio() {
        return pedidos;
    }

}
