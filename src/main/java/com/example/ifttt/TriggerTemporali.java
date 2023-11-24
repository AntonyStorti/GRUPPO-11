package com.example.ifttt;

import java.time.LocalTime;



public abstract class TriggerTemporali implements Trigger {


    LocalTime tempo;


    //-----COSTRUTTORE------//
    public TriggerTemporali(LocalTime tempo) {
        this.tempo = tempo;
    }
    //---------------------//



}
