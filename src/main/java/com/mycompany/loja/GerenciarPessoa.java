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
        if (nome.isEmpty()) {
            log = "Nome não pode ser vazio";
        } else if (endereco.isEmpty()) {
            log = "Endereço não pode ser vazio";
        } else if (telefone.isEmpty()) {
            log = "Telefone não pode ser vazio";
        } else if (cpf.isEmpty()) {
            log = "CPF não pode ser vazio";
        } else if (dataNascimento == null) {
            log = "Data de nascimento não pode ser vazia";
        } else {
            Pessoa pessoa = new PessoaFisica(nome, endereco, telefone, cpf, dataNascimento);
            pessoas.add(pessoa);
        }
        return log;
    }

    public String inserirPessoaJuridica(String nome, String endereco, String telefone, String cnpj,
            String razaoSocial) {
        String log = "";
        if (nome.isEmpty()) {
            log = "Nome não pode ser vazio";
        } else if (endereco.isEmpty()) {
            log = "Endereço não pode ser vazio";
        } else if (telefone.isEmpty()) {
            log = "Telefone não pode ser vazio";
        } else if (cnpj.isEmpty()) {
            log = "CNPJ não pode ser vazio";
        } else if (razaoSocial.isEmpty()) {
            log = "Razão social não pode ser vazia";
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

        if (cpfcnpj.isBlank()) {
            log = "Preencha o campo";
        }

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

    public String alterarPessoaFisica(String buscarCpfCnpj, String cpfCnpj, String nome, String endereco,
            String telefone,
            LocalDate dataNascimento) {
        String log = "";
        boolean achou = false;

        if (buscarCpfCnpj.isBlank()) {
            log = "Preencha o campo";
        } else if (cpfCnpj.isEmpty()) {

        } else if (nome.isEmpty()) {
            log = "Nome não pode ser vazio";
        } else if (endereco.isEmpty()) {
            log = "Endereço não pode ser vazio";
        } else if (telefone.isEmpty()) {
            log = "Telefone não pode ser vazio";
        } else if (dataNascimento == null) {
            log = "Data de nascimento não pode ser vazia";
        }

        for (Pessoa p : pessoas) {
            if (p instanceof PessoaFisica) {
                PessoaFisica pf = (PessoaFisica) p;
                if (buscarCpfCnpj.equals(pf.getCpf())) {
                    pf.setNome(nome);
                    pf.setEndereco(endereco);
                    pf.setTelefone(telefone);
                    pf.setDataDeNascimento(dataNascimento);
                    pf.setCpf(cpfCnpj);
                    achou = true;
                }
            }
        }
        if (!achou) {
            log = "Pessoa não encontrada";
        }

        return log;
    }

    public String alterarPessoaJuridica(String buscarCpfCnpj, String nome, String endereco, String telefone,
            String cpfCnpj,
            String razaoSocial) {
        String log = "";

        for (Pessoa p : pessoas) {
            if (p instanceof PessoaJuridica) {
                PessoaJuridica pj = (PessoaJuridica) p;
                if (buscarCpfCnpj.equals(pj.getCnpj())) {
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
