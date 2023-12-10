package com.example.azioni;
import com.example.ifttt.Azione;
import org.json.JSONObject;

import java.io.Serializable;

public abstract class AzioniSuFile implements Azione, Serializable {


    String percorso;



    //-----COSTRUTTORE------//

    public AzioniSuFile(String percorso) {
        this.percorso = percorso;
    }

    //---------------------//

    public abstract JSONObject toJSONObject();

    public abstract String getTipo();

}
