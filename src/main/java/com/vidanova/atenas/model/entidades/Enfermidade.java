package com.vidanova.atenas.model.entidades;

import javax.validation.constraints.*;
import java.util.Objects;

public class Enfermidade {
    private int id;

    @NotBlank(message = "CID não pode estar em branco")
    @Pattern(regexp = "^[A-Z]\\d{2}$",message = "CID Inválido")
    private String CID;

    @NotBlank(message = "Nome não pode estar em branco")
    @Size(min = 6,max = 260, message = "Tamanho nome inválido")
    private String nome;
    public Enfermidade(){
    }
    public Enfermidade(int id, String CID, String nome){
       this.id = id;
       this.CID = CID;
       this.nome = nome;
    }
    public int getId(){
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Enfermidade)) return false;
        Enfermidade that = (Enfermidade) o;
        return getId() == that.getId() &&
                getCID().equals(that.getCID()) &&
                getNome().equals(that.getNome());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCID(), getNome());
    }
}
