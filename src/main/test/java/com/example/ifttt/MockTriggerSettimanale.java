package com.example.ifttt;



import com.example.trigger.TriggerSettimanale;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class MockTriggerSettimanale extends TriggerSettimanale {



    // Costruttore che richiama il costruttore della superclasse
    public MockTriggerSettimanale(LocalTime tempo, DayOfWeek gSettimana) {
        super(tempo, gSettimana);
    }

    // Sovrascrivi il metodo verificaCondizione per controllare il comportamento nel test
    @Override
    public boolean verificaCondizione() {
        // Simula il comportamento desiderato nel test

        return true;
    }



}