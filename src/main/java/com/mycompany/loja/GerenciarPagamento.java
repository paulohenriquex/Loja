package com.mycompany.loja;

import java.time.LocalDate;
import java.util.ArrayList;

public class GerenciarPagamento {
    private ArrayList<Pagamento> pagamentos;

    public GerenciarPagamento(ArrayList<Pagamento> p) {
        this.pagamentos = p;
    }

    public String inserirPagamento(String formaDePagamento, float valorTotal, LocalDate data, boolean status,
            Pedido pedido, float troco) {
        String log = "";

        if (formaDePagamento.isEmpty()) {
            log += "Forma de pagamento não pode ser vazia\n";
        } else if (valorTotal == 0) {
            log += "Valor total não pode ser 0\n";
        } else if (data == null) {
            log += "Data não pode ser nula\n";
        } else if (status == false) {
            log += "Status não pode ser vazio\n";
        } else if (pedido == null) {
            log += "Pedido não pode ser nulo\n";
        } else if (troco < 0) {
            log = "Troco não pode ser negativo\n";
        } else {
            Pagamento pagamento = new Pagamento(formaDePagamento, valorTotal, data, status, pedido, troco);
            pagamentos.add(pagamento);
        }
        return log;
    }

    public String alterarPagamento(String novaFormaDePagamento, float novoValorTotal, LocalDate novaDataDePagamento,
            boolean novoStatus, int posPagamento, float troco) {
        String log = "";
        if (novaFormaDePagamento.isEmpty()) {
            log = "Forma de pagamento não pode ser vazia\n";
        } else if (novoValorTotal == 0) {
            log = "Valor total não pode ser 0\n";
        } else if (novaDataDePagamento == null) {
            log = "Data não pode ser nula\n";
        } else if (novoStatus == false) {
            log = "Status não pode ser vazio\n";
        } else if (posPagamento < 0 || posPagamento >= pagamentos.size()) {
            log = "Posição inválida\n";
        } else if (troco < 0) {
            log = "Troco não pode ser negativo\n";
        }

        Pagamento pagamento = pagamentos.get(posPagamento);
        pagamento.setFormaPagamento(novaFormaDePagamento);
        pagamento.setValor(novoValorTotal);
        pagamento.setDataPagamento(novaDataDePagamento);
        pagamento.setStatus(novoStatus);
        pagamento.setTroco(troco);

        return log;
    }

    public String excluirPagamento(int posPagamento) {
        String log = "";
        if (posPagamento < 0 || posPagamento >= pagamentos.size()) {
            log += "Posição inválida\n";
        } else {
            pagamentos.remove(posPagamento);
        }
        return log;
    }

    public Pagamento consultarPagamento(int posPagamento) {
        if (posPagamento < 0 || posPagamento >= pagamentos.size())
            return null;
        else
            return pagamentos.get(posPagamento);
    }

    public String efetuarPagamento() {
        String log = "";
        return log;
    }

    public ArrayList<Pagamento> relatorio() {
        return pagamentos;
    }
}
