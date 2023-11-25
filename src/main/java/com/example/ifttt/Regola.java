package com.example.ifttt;

import java.sql.Time;



public class Regola {


    private final int ID;
    private final String nome;
    private Trigger t;
    private Azione a;
    private boolean stato;
    private boolean eseguito;

    private static int contatore = 1;



    //-----COSTRUTTORE-----//
    public Regola(String nome, Trigger t, Azione a, boolean stato) {
        this.ID = contatore;
        this.nome = nome ;
        this.t = t;
        this.a = a;
        this.stato = true; //Di default attiva dopo la creazione!

        contatore++;
        eseguito=false;
    }
    //---------------------//



    public boolean valutaEsecuzione(){

        if(t.verificaCondizione()){

            if(!eseguito){

                a.eseguiAzione();
                eseguito = true;

                return true;

            }
        }

                else{
                        eseguito = false;
                    }

                return false;

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

    public Boolean getStato() {
        return stato;
    }

    public boolean isEseguito() {
        return eseguito;
    }

    public static int getContatore() {
        return contatore;
    }

    public void setT(Trigger t) {
        this.t = t;
    }

    public void setStato(boolean stato) {
        this.stato = stato;
    }

    public void setEseguito(boolean eseguito) {
        this.eseguito = eseguito;
    }


}