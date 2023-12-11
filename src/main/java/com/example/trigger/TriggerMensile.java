package com.example.trigger;

import com.example.ifttt.Trigger;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalTime;


public class TriggerMensile extends TriggerTemporali {

    private LocalDate giornoMese;


    public TriggerMensile(LocalTime tempo, LocalDate giornoMese) {
        super(tempo);
        this.giornoMese = giornoMese;
    }


    @Override
    public boolean verificaCondizione() {

        LocalDate oggi = LocalDate.now();
        LocalTime ora = LocalTime.now();

        return oggi.equals(giornoMese) && ora.getHour() == tempo.getHour() && ora.getMinute() == tempo.getMinute();

    }


    @Override
    public String toString() {
        return "Attiva questo mese: il " + giornoMese.getDayOfMonth();
    }


    @Override
    public JSONObject toJSONObject() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tipo", getTipo());
        jsonObject.put("tempo", tempo.toString());
        jsonObject.put("giornomese", giornoMese);

        return jsonObject;

    }

    public static Trigger deserialize(JSONObject jsonTrigger) {

        LocalTime tempo = LocalTime.parse(jsonTrigger.getString("tempo"));
        LocalDate giorno = LocalDate.parse(jsonTrigger.getString("giornomese"));
        return new TriggerMensile(tempo, giorno);

    }


    @Override
    public String getTipo() {
        return "TriggerMensile";
    }

}
