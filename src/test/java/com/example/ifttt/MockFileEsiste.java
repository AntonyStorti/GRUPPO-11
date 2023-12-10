package com.example.ifttt;

import com.example.trigger.FileEsiste;

public class MockFileEsiste extends FileEsiste {


    public MockFileEsiste(String percorso, String nomeFile) {
        super(percorso, nomeFile);
    }


    @Override
    public boolean verificaCondizione() {
        // Simula il comportamento desiderato
        return true;
    }



}
