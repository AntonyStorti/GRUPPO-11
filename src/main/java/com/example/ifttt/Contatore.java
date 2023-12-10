package com.example.ifttt;

import org.json.JSONObject;

import java.io.Serializable;

public class Contatore implements Serializable {
    private String nome;
    private int valorec;

    public Contatore(String nome, int valorec) {
        this.nome = nome;
        this.valorec = valorec;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getValore() {
        return valorec;
    }

    public void setValore(int valore) {
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