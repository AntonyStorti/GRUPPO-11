package com.example.trigger;

import org.json.JSONObject;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public class TriggerSettimanale extends TriggerTemporali {

    private DayOfWeek gSettimana;

    public TriggerSettimanale(LocalTime tempo, DayOfWeek gSettimana) {
        super(tempo);
        this.gSettimana = gSettimana;
    }

    @Override
    public boolean verificaCondizione() {

        DayOfWeek oggi = LocalDate.now().getDayOfWeek();
        LocalTime oraCorrente = LocalTime.now();

        return oggi == gSettimana && oraCorrente.getHour() == tempo.getHour() && oraCorrente.getMinute() == tempo.getMinute();

    }

    @Override
    public String toString() {

        String g;

        switch (gSettimana) {
            case DayOfWeek.MONDAY:
                g = "Lunedì";
                break;
            case DayOfWeek.TUESDAY:
                g = "Martedì";
                break;
            case DayOfWeek.WEDNESDAY:
                g = "Mercoledì";
                break;
            case DayOfWeek.THURSDAY:
                g = "Giovedì";
                break;
            case DayOfWeek.FRIDAY:
                g = "Venerdì";
                break;
            case DayOfWeek.SATURDAY:
                g = "Sabato";
                break;
            case DayOfWeek.SUNDAY:
                g = "Domenica";
                break;
            default:
                g = "Giorno non valido";
                break;
        }
        return "Attiva il: " + g;
    }

    /**
     * @return
     */
    @Override
    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tipo", getTipo());  // Supponiamo che getTipo() restituisca una stringa che identifica il tipo di trigger
        jsonObject.put("tempo", tempo.toString());
        jsonObject.put("giornosettimana", gSettimana);
        return jsonObject;
    }


    @Override
    public String getTipo() {
        return "TriggerSettimanale";
    }
}
