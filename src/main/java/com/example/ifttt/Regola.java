package com.example.ifttt;

import org.json.JSONObject;

import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;
import java.util.Objects;


public class Regola implements Serializable {

    private int ID;
    private final String nome;
    private Trigger t;
    private Azione a;
    private boolean stato;
    private boolean eseguito;
    private boolean ripetibile;
    private Duration periodoIbernazione;
    private Instant ultimaEsecuzione;

    private static int contatore = 1;



    //-----COSTRUTTORE-----//
    public Regola(String nome, Trigger t, Azione a, boolean stato, boolean eseguito, boolean ripetibile) {
        this.ID = contatore;
        this.nome = nome ;
        this.t = t;
        this.a = a;
        this.stato = stato;
        contatore++;
        this.eseguito = eseguito;
        this.ripetibile = ripetibile;
    }
    //---------------------//

    public Regola(String nome, Trigger t, Azione a, boolean stato, boolean eseguito, boolean ripetibile, Duration periodoIbernazione, Instant ultimaEsecuzione) {
        this.ID = ID;
        this.nome = nome ;
        this.t = t;
        this.a = a;
        this.stato = stato;
        this.eseguito = eseguito;
        this.ripetibile = ripetibile;
        this.periodoIbernazione = periodoIbernazione;
        this.ultimaEsecuzione = ultimaEsecuzione;
    }

    public Regola(int ID, String nome, Trigger t, Azione a, boolean stato, boolean eseguito, boolean ripetibile, Duration periodoIbernazione, Instant ultimaEsecuzione) {
        this.ID = ID;
        this.nome = nome ;
        this.t = t;
        this.a = a;
        this.stato = stato;
        this.eseguito = eseguito;
        this.ripetibile = ripetibile;
        this.periodoIbernazione = periodoIbernazione;
        this.ultimaEsecuzione = ultimaEsecuzione;
    }

    public Regola(int ID, String nome, Trigger t, Azione a, boolean stato, boolean eseguito) {
        this.ID = ID;
        this.nome = nome;
        this.t = t;
        this.a = a;
        this.stato = stato;
        this.eseguito = eseguito;
    }



    public boolean valutaEsecuzione(){

        if(t.verificaCondizione()){

            if(!eseguito && stato){

                a.eseguiAzione();
                if (isRipetibile()) {

                    setUltimaEsecuzione(Instant.now());

                }
                return true;

            }

            return false;
        }

        return false;

    }



    public JSONObject toJSONObject() {

        JSONObject regolaJson = new JSONObject();
        regolaJson.put("ID", ID);
        regolaJson.put("nome", nome);
        regolaJson.put("trigger", t.toJSONObject());
        regolaJson.put("azione", a.toJSONObject());
        regolaJson.put("stato", stato);
        regolaJson.put("eseguito", eseguito);
        regolaJson.put("ripetibile", ripetibile);

        if (ripetibile) {

            regolaJson.put("ibernazione", periodoIbernazione);
            regolaJson.put("ultimaEsecuzione", ultimaEsecuzione);

        }

        return regolaJson;

    }



    //-----GETTER & SETTER-----//

    public int getID() {
        return ID;
    }

    public String getNome() {
        return nome;
    }

    public Trigger getTrigger() {
        return t;
    }

    public Azione getAzione() {
        return a;
    }


    public String getStato() {

        if (stato == true)
            return "Attiva";
        else
            return "Disattiva";
    }

    public String getRipetibile() {
        if (ripetibile == true) {

            return "Dopo: " + String.valueOf(periodoIbernazione.toDays()) + "g " + String.valueOf(periodoIbernazione.toHoursPart()) + "h " + String.valueOf(periodoIbernazione.toMinutesPart()) + "m";

        }
        else
            return "No";
    }

    public Instant getUltimaEsecuzione() {
        return ultimaEsecuzione;
    }

    public Duration getPeriodoIbernazione() {
        return periodoIbernazione;
    }

    public void setUltimaEsecuzione(Instant ultimaEsecuzione) {
        this.ultimaEsecuzione = ultimaEsecuzione;
    }

    public static int getContatore() {
        return contatore;
    }

    public boolean isRipetibile() {
        return ripetibile;
    }

    public void setStato(boolean stato) {
        this.stato = stato;
    }

    public void setEseguito(boolean eseguito) {
        this.eseguito = eseguito;
    }

    public static void setContatore(int contatore) {
        Regola.contatore = contatore;
    }
    public void setID(int ID) {
        this.ID = ID;
    }



    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Regola regola = (Regola) o;
        return ID == regola.ID;

    }

    @Override
    public int hashCode() {

        return Objects.hash(ID);

    }


    @Override
    public String toString() {
        return "Regola: {" +
                "ID = " + ID +
                ", Nome = '" + nome + '\'' +
                ", Trigger = " + t +
                ", Azione = " + a +
                ", Stato =" + stato +
                ", Eseguito = " + eseguito +
                ", Ripetibile = " +ripetibile +
                '}';
    }

}