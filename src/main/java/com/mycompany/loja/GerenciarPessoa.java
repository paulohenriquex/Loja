package com.mycompany.loja;

import java.time.LocalDate;
import java.util.ArrayList;

public class GerenciarPessoa {

    private ArrayList<Pessoa> pessoas;

    public GerenciarPessoa(ArrayList<Pessoa> p) {
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
        for (Pessoa p : pessoas) {
            if (p instanceof PessoaFisica pf && cpfcnpj.equals(pf.getCpf())) {
                return pf;
            } else if (p instanceof PessoaJuridica pj && cpfcnpj.equals(pj.getCnpj())) {
                return pj;
            }
        }
        return null;    
    }

    public String excluirPessoa(String cpfcnpj) {
        String log = "";
        for (int i = 0; i < pessoas.size(); i++) {
            Pessoa p = pessoas.get(i);
            if (p instanceof PessoaFisica pf && cpfcnpj.equals(pf.getCpf())) {
                pessoas.remove(i);
            } else if (p instanceof PessoaJuridica pj && cpfcnpj.equals(pj.getCnpj())) {
                pessoas.remove(i);
            } else {
                log = "Pessoa não encontrada";
            }
        }
        return log;
    }

    public String alterarPessoaFisica(String pos,String cpfCnpj, String nome, String endereco, String telefone,
            LocalDate dataNascimento) {
        String log = "";
        boolean achou = false;

        if (cpfCnpj.isBlank() || nome.isBlank() || endereco.isBlank() || telefone.isBlank() || dataNascimento == null) {
            log = "Preencha todos os campos";
        }

        for (Pessoa p : pessoas) {
            System.out.println("Entrou no for");
            if (p instanceof PessoaFisica) {
                System.out.println("Entrou no if");
                PessoaFisica pf = (PessoaFisica) p;
                if(pos.equals(pf.getCpf())){

                    pf.setNome(nome);
                    pf.setEndereco(endereco);
                    pf.setTelefone(telefone);
                    pf.setDataDeNascimento(dataNascimento);
                    pf.setCpf(cpfCnpj);
                    achou = true;
                    System.out.println("Alterou os dados");
                }
            }
        }
        if (!achou) {
            log = "Pessoa não encontrada";
        }

        return log;
    }

    public String alterarPessoaJuridica(String nome, String endereco, String telefone, String cpfCnpj,
            String razaoSocial) {
        String log = "";

        for (Pessoa p : pessoas) {
            if (p instanceof PessoaJuridica) {
                PessoaJuridica pj = (PessoaJuridica) p;
                if (cpfCnpj.equals(pj.getCnpj())) {
                    pj.setNome(nome);
                    pj.setEndereco(endereco);
                    pj.setTelefone(telefone);
                    pj.setRazaoSocial(razaoSocial);
                } else {
                    log = "Pessoa não encontrada";
                }
            }
        }

        return log;
    }

    public ArrayList<Pessoa> relatorio() {
        return pessoas;
    }

}
