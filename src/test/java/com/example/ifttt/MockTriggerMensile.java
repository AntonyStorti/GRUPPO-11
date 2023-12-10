package com.example.ifttt;

import com.example.trigger.TriggerMensile;

import java.time.LocalDate;
import java.time.LocalTime;


public class MockTriggerMensile extends TriggerMensile {


    // Costruttore che richiama il costruttore della superclasse
    public MockTriggerMensile(LocalTime tempo, LocalDate giornoMese) {
        super(tempo, giornoMese);
    }


    @Override
    public boolean verificaCondizione() {
        // Simula il comportamento desiderato nel test
        return true;
    }


}
