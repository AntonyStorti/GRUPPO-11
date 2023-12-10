package com.example.ifttt;

import com.example.trigger.TriggerSettimanale;

import java.time.DayOfWeek;
import java.time.LocalTime;


public class MockTriggerSettimanale extends TriggerSettimanale {

    // Costruttore che richiama il costruttore della superclasse
    public MockTriggerSettimanale(LocalTime tempo, DayOfWeek gSettimana) {
        super(tempo, gSettimana);
    }


    @Override
    public boolean verificaCondizione() {

        return true;

    }



}