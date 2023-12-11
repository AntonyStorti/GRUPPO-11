package com.example.ifttt;

import org.json.JSONObject;

import java.io.Serializable;

public class Contatore implements Serializable {

    private String nome;
    private Integer valorec;


    public Contatore(String nome, Integer valorec) {
        this.nome = nome;
        this.valorec = valorec;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getValore() {
        return valorec;
    }

    public void setValore(Integer valorec) {
        this.valorec = valorec;
    }

    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("nome", nome);
        jsonObject.put("valorec", valorec);
        return jsonObject;
    }

    public static Contatore deserializeContatore(JSONObject json) {
        String nome = json.getString("nome");
        Integer valorec = json.getInt("valorec");
        return new Contatore(nome, valorec);
    }

    @Override
    public String toString() {
        return "Contatore: " + nome;
    }
}