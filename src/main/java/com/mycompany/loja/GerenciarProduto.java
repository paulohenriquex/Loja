package com.mycompany.loja;

import java.util.ArrayList;

public class GerenciarProduto {
    private ArrayList<Produto> produtos;

    public GerenciarProduto(ArrayList<Produto> p) {
        this.produtos = p;
    }

    public String inserirProduto(String nome, String descricao, float preco, float quantidade) {
        String log = "";

        if (nome.isBlank() || descricao.isBlank() || preco <= 0 || quantidade <= 0) {
            log = "Preencha todos os campos";
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

        if (nome.isBlank() || descricao.isBlank() || preco <= 0 || quantidade <= 0 || pos < 0
                || pos >= produtos.size()) {
            log = "Preencha todos os campos";
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
