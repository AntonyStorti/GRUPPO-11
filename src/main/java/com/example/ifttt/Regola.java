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



    private boolean valutaAttivazione(){

        return false;

    }


}
