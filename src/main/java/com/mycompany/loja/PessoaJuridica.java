package com.mycompany.loja;

public class PessoaJuridica extends Pessoa{
    private String razaoSocial;
    private String cnpj;

    public PessoaJuridica(String nome, String endereco, String telefone, String cnpj, String razaoSocial) {
        super(nome,endereco,telefone);
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }
}
