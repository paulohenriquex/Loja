package com.mycompany.loja;

import java.time.LocalDate;

public class PessoaFisica extends Pessoa {
    private String cpf;
    private LocalDate dataDeNascimento;

    public PessoaFisica(String nome, String endereco, String telefone, String cpf, LocalDate dataDeNascimento) {
        super(nome,endereco,telefone);
        this.cpf = cpf;
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }
}
