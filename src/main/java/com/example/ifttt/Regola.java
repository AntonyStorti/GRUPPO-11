package com.example.ifttt;

import java.time.LocalTime;

public class Regola {

    Trigger t;
    Azione a;
    boolean stato;
    boolean ripetibile;
    LocalTime quiescenza;



    //---COSTRUTTORE---//
    public Regola(Trigger t, Azione a, boolean stato, boolean ripetibile, LocalTime quiescenza) {
        this.t = t;
        this.a = a;
        this.stato = stato;
        this.ripetibile = ripetibile;
        this.quiescenza = quiescenza;
    }
    //-----------------//



    // Metodo per impostare lo stato della regola
    public void setStato(boolean stato) {
        this.stato = stato;
    }

    private boolean validazioneRegola(){

        return false;

    }



    public void setTrigger(Trigger nuovoTrigger) {
    }

    public void setAzione(Azione nuovaAzione) {
    }

    public void setRipetibile(boolean nuovaRipetibilita) {
    }

    public void setQuiescenza(LocalTime nuovaQuiescenza) {
    }


    @Override
    public String toString() {
        return "Regola{" +
                "t=" + t +
                ", a=" + a +
                ", stato=" + stato +
                ", ripetibile=" + ripetibile +
                ", quiescenza=" + quiescenza +
                '}';
    }


}
