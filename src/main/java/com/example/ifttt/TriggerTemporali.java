package com.example.ifttt;

import java.time.LocalTime;
import java.util.Timer;

public abstract class TriggerTemporali implements Trigger {

    LocalTime tempo;

    public TriggerTemporali(LocalTime tempo) {
        this.tempo = tempo;
    }


    @Override
    public boolean verificaCondizione() {
        return false;
    }

}
