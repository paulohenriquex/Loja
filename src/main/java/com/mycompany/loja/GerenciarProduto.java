package com.mycompany.loja;

import java.util.ArrayList;

public class GerenciarProduto {
    private ArrayList<Produto> produtos;

    public GerenciarProduto(ArrayList<Produto> p) {
        this.produtos = p;
    }

    public String inserirProduto(String nome, String descricao, float preco, float quantidade) {
        String log = "";

        if (nome.isEmpty()) {
            log = "Nome não ṕde ser vazio";
        } else if (descricao.isEmpty()) {
            log = "Descrição não pode ser vazia";
        } else if (preco <= 0) {
            log = "Preço não pode ser menor ou igual a zero";
        } else if (quantidade <= 0) {
            log = "Quantidade não pode ser menor ou igual a zero";
        } else {
            Produto produto = new Produto(nome, preco, descricao, quantidade);
            produtos.add(produto);
        }

        return log;
    }

    public String excluirProduto(int pos) {
        String log = "";

        if (pos < 0 || pos >= produtos.size()) {
            log = "Produto não encontrado";
        } else {
            produtos.remove(pos);
        }

        return log;
    }

    public String alterarProduto(String nome, String descricao, float preco, float quantidade, int pos) {
        String log = "";
        Produto produto = produtos.get(pos);
        if (nome.isEmpty()) {
            log = "Nome não pode ser vazio";
        } else if (descricao.isEmpty()) {
            log = "Descrição não pode ser vazia";
        } else if (preco <= 0) {
            log = "Preço não pode ser menor ou igual a zero";
        } else if (quantidade <= 0) {
            log = "Quantidade não pode ser menor ou igual a zero";
        } else {
            produto.setNome(nome);
            produto.setDescricao(descricao);
            produto.setPreco(preco);
            produto.setQuantidade(quantidade);
        }

        return log;
    }

    public Produto consultarProduto(int pos) {
        if (pos < 0 || pos >= produtos.size()) {
            return null;
        } else {
            return produtos.get(pos);
        }

    }

    public ArrayList<Produto> relatorio() {
        return produtos;
    }

}
