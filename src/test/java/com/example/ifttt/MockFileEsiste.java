package com.example.ifttt;

import com.example.trigger.FileEsiste;

public class MockFileEsiste extends FileEsiste {

    // Aggiungi eventuali campi necessari per il test

    // Costruttore che richiama il costruttore della superclasse
    public MockFileEsiste(String percorso, String nomeFile) {
        super(percorso, nomeFile);
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
