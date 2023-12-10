package com.example.ifttt;


import com.example.trigger.DimensioneFile;

public class MockDimensioneFile extends DimensioneFile {

    // Aggiungi eventuali campi necessari per il test

    // Costruttore che richiama il costruttore della superclasse
    public MockDimensioneFile(String percorso, int dimensione, String unita) {
        super(percorso, dimensione, unita);
    }

    // Sovrascrivi il metodo verificaCondizione per controllare il comportamento nel test
    @Override
    public boolean verificaCondizione() {
        // Simula il comportamento desiderato nel test
        // Ad esempio, restituisci sempre true per il test
        return true;
    }

    // Puoi anche aggiungere altri metodi o campi necessari per il test

}
