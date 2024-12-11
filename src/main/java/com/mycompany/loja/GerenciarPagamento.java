package com.mycompany.loja;

import java.util.ArrayList;

public class GerenciarPagamento {
    private ArrayList<Pagamento> pagamentos;
    private ArrayList<Pedido> pedidos;
    

    public GerenciarPagamento(ArrayList<Pagamento> p) {
        this.pagamentos = p;
    }
}
