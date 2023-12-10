package com.example.trigger;

import org.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TriggerSuData extends TriggerTemporali {

    private LocalDate data;

    public TriggerSuData(LocalTime tempo, LocalDate data) {
        super(tempo);
        this.data = data;
    }

    @Override
    public boolean verificaCondizione() {

        LocalDate oggi = LocalDate.now();
        LocalTime ora = LocalTime.now();

        return oggi.equals(data) && ora.getHour() == tempo.getHour() && ora.getMinute() == tempo.getMinute();
    }

    @Override
    public String toString() {
        return "Attiva il: " + data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }


    @Override
    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tipo", getTipo());  // Supponiamo che getTipo() restituisca una stringa che identifica il tipo di trigger
        jsonObject.put("tempo", tempo.toString());
        jsonObject.put("data", data);
        return jsonObject;
    }


    @Override
    public String getTipo() {
        return "TriggerSuData";
    }
}
