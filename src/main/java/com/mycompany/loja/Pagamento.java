package com.mycompany.loja;

public class Pagamento {

    private String formaPagamento;
    private double valor;
    private String dataPagamento;
    private String status;
    private Pedido pedido;

    public Pagamento(String formaPagamento, double valor, String dataPagamento, String status, Pedido pedido) {
        this.formaPagamento = formaPagamento;
        this.valor = valor;
        this.dataPagamento = dataPagamento;
        this.status = status;
        this.pedido = pedido;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(String dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

}
