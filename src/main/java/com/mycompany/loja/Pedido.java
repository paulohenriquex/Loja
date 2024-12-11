package com.mycompany.loja;

import java.time.LocalDate;
import java.util.ArrayList;

public class Pedido {
    private Pessoa pessoa;
    private ArrayList<Produto> produtos;
    private float quantidade;
    private float valorTotal;
    private LocalDate data;

    public Pedido(Pessoa pessoa, ArrayList<Produto> produto, float quantidade, float valorTotal, LocalDate data) {
        this.pessoa = pessoa;
        this.produtos = produto;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
        this.data = data;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }

}
