package com.vidanova.atenas.model.entidades;

import javax.validation.constraints.*;

public class Enfermidade {
    private int idEnfermidade;

    @NotBlank(message = "CID não pode estar em branco")
    @Pattern(regexp = "^[A-Z]\\d{2}$",message = "CID Inválido")
    private String CID;

    @NotBlank(message = "Nome não pode estar em branco")
    @Size(min = 6,max = 260, message = "Tamanho nome inválido")
    private String nome;

    public Enfermidade(int idEnfermidade,String CID,String nome){
       this.idEnfermidade = idEnfermidade;
       this.CID = CID;
       this.nome = nome;
    }
    public int getId(){
        return this.idEnfermidade;
    }
    public void setIdEnfermidade(int idEnfermidade) {
        this.idEnfermidade = idEnfermidade;
    }

    public String getCID() {
        return CID;
    }

    public void setCID(String CID) {
        this.CID = CID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
