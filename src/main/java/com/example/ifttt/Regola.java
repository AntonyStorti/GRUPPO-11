package com.example.ifttt;

import java.time.LocalTime;

public class Regola {

    Trigger t;
    Azione a;
    boolean stato;
    LocalTime quiescenza;


    //---COSTRUTTORE---//
    public Regola(Trigger t, Azione a, boolean stato, LocalTime quiescenza) {
        this.t = t;
        this.a = a;
        this.stato = stato;
        this.quiescenza = quiescenza;
    }
    //-----------------//


}
