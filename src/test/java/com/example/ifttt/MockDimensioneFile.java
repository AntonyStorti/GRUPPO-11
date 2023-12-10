package com.example.ifttt;

import com.example.trigger.DimensioneFile;


public class MockDimensioneFile extends DimensioneFile {

    // Costruttore che richiama il costruttore della superclasse
    public MockDimensioneFile(String percorso, int dimensione, String unita) {
        super(percorso, dimensione, unita);
    }

    @Override
    public boolean verificaCondizione() {
        // Simula il comportamento desiderato
        return true;
    }



}
