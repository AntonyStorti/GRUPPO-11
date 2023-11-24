package com.example.ifttt;

import java.time.LocalTime;



public class TempoDelGiorno extends TriggerTemporali{



    //-----COSTRUTTORE-----//
    public TempoDelGiorno(LocalTime tempo) {
        super(tempo);
    }
    //---------------------//



    @Override
    public boolean verificaCondizione() {

        LocalTime oraCorrente = LocalTime.now();

        return oraCorrente.getHour() == tempo.getHour() && oraCorrente.getMinute() == tempo.getMinute();


    }


}
