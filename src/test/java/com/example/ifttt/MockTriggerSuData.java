package com.example.ifttt;



import com.example.trigger.TriggerSuData;

import java.time.LocalDate;
import java.time.LocalTime;

public class MockTriggerSuData extends TriggerSuData {



    // Costruttore che richiama il costruttore della superclasse
    public MockTriggerSuData(LocalTime tempo, LocalDate data) {
        super(tempo, data);
    }

    // Sovrascrivi il metodo verificaCondizione per controllare il comportamento nel test
    @Override
    public boolean verificaCondizione() {
        // Simula il comportamento desiderato nel test
        // Ad esempio, restituisci sempre true per il test
        return true;
    }



}
