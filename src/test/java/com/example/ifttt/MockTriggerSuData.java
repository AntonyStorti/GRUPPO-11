package com.example.ifttt;

import com.example.trigger.TriggerSuData;

import java.time.LocalDate;
import java.time.LocalTime;


public class MockTriggerSuData extends TriggerSuData {


    // Costruttore che richiama il costruttore della superclasse
    public MockTriggerSuData(LocalTime tempo, LocalDate data) {
        super(tempo, data);
    }

    @Override
    public boolean verificaCondizione() {

        return true;

    }



}
