package com.vidanova.atenas.Model.Entidades;

public class Enfermidade {
    int idEnfermidade;
    String CID;
    String nome;

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
