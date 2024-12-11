package com.mycompany.loja;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class GerenciarPessoa {

    private ArrayList<Pessoa> pessoas;

    public GerenciarPessoa(ArrayList<Pessoa> p) {
        Scanner sc = new Scanner(System.in);
        this.pessoas = p;
    }

    public String inserirPessoa(String nome, String endereco, String telefone, String cpf,
            LocalDate dataNascimento) {
        String log = "";
        if (nome.isBlank() || endereco.isBlank() || telefone.isBlank() || cpf.isBlank()
                || dataNascimento == null) {
            log = "Preencha todos os campos";
        }
        Pessoa pessoa = new PessoaFisica(nome, endereco, telefone, cpf, dataNascimento);
        pessoas.add(pessoa);

        return log;
    }

    public String inserirPessoaJuridica(String nome, String endereco, String telefone, String cnpj,
            String razaoSocial) {
        String log = "";
        if (pessoas.isEmpty()) {
            log = "Lista de pessoas vazia";
        } else if (nome.isBlank() || endereco.isBlank() || telefone.isBlank() || cnpj.isBlank()
                || razaoSocial.isBlank()) {
            log = "Preencha todos os campos";
        } else {
            Pessoa pessoa = new PessoaJuridica(nome, endereco, telefone, cnpj, razaoSocial);
            pessoas.add(pessoa);
        }
        return log;
    }

    public Pessoa consultarPessoas(String cpfcnpj) {
        for(Pessoa p:pessoas){
            if(p instanceof PessoaFisica){
                PessoaFisica pf = (PessoaFisica) p;
                if(cpfcnpj.equals(pf.getCpf())){
                    return pf;
                }
            }else if(p instanceof PessoaJuridica){
                PessoaJuridica pj = (PessoaJuridica) p;
                if(cpfcnpj.equals(pj.getCnpj())){
                    return pj;
                }
            }
        }

        return null;
    }
    

}
