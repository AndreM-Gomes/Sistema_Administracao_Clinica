package com.vidanova.atenas.model.entidades;

public class PlanoSaude{
    int id_Plan_Saude;
    String num_Plan;
    String nome;
    String tipo;

    public PlanoSaude(int id_Plan_Saude,String num_Plan,String nome,String tipo){
        this.id_Plan_Saude = id_Plan_Saude;
        this.num_Plan = num_Plan;
        this.nome = nome;
        this.tipo = tipo;
    }
    public int getId_Plan_Saude() {
        return id_Plan_Saude;
    }

    public void setId_Plan_Saude(int id_Plan_Saude) {
        this.id_Plan_Saude = id_Plan_Saude;
    }

    public String getNum_Plan() {
        return num_Plan;
    }

    public void setNum_Plan(String num_Plan) {
        this.num_Plan = num_Plan;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
