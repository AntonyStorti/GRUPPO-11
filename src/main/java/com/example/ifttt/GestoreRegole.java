package com.example.ifttt;

import com.example.azioni.*;
import com.example.trigger.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;



public class GestoreRegole implements Runnable {

    private static int contatore = 1;
    public static ObservableList<Regola> listaRegole = FXCollections.observableArrayList();
    private final int ripetiVerifica = 1;


    public GestoreRegole() {}

    public GestoreRegole(ObservableList<Regola> listaRegole) {
        this.listaRegole = listaRegole;
    }



    @Override
    public void run() {


            while (!Thread.currentThread().isInterrupted()) {

                synchronized (listaRegole) {

                    //Itero sugli elementi della lista:
                    for (Regola a : listaRegole) {

                        if (a.getStato() == "Attiva") {

                            if (a.valutaEsecuzione()) {
                                a.setEseguito(true);
                                a.setStato(false);
                                aggiornaFile(a);
                            }

                        }

                        if (a.isRipetibile()) {

                            if(Instant.now().isAfter(a.getUltimaEsecuzione().plus(a.getPeriodoIbernazione()))) {
                                a.setStato(true);
                                a.setEseguito(false);
                                aggiornaFile(a);
                            }

                        }

                    }
                }
            try {

                Thread.sleep(ripetiVerifica * 1000);

            } catch (InterruptedException ex) {

                Logger.getLogger(GestoreRegole.class.getName());

            }

        }

    }



    public void aggiungiRegola(Regola nuovaRegola) {

        listaRegole.add(nuovaRegola);

    }



    @Override
    public String toString() {
        return "GestoreRegole{" +
                "listaRegole=" + listaRegole +
                '}';
    }



    public void saveObjectsToFile() {


        Path filePath = Paths.get("ListaRegole.json");

        JSONObject jsonContainer;


        if (Files.exists(filePath)) {

            try {

                String fileContent = new String(Files.readAllBytes(filePath));
                jsonContainer = new JSONObject(fileContent);

                updateContatoreFromExistingRules(jsonContainer.getJSONArray("regole"));

            } catch (IOException e) {

                e.printStackTrace();
                System.err.println("Errore durante la lettura del file JSON.");
                return;

            }
        } else {

            // Se il file non esiste crealo:
            jsonContainer = new JSONObject();
            jsonContainer.put("regole", new JSONArray());

        }


        synchronized (listaRegole) {

            JSONArray jsonArray = jsonContainer.getJSONArray("regole");

            // Controlla se la regola è già presente:
            Set<String> existingRuleNames = new HashSet<>();

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject existingRule = jsonArray.getJSONObject(i);
                existingRuleNames.add(existingRule.getString("nome"));

            }


            for (Regola regola : listaRegole) {

                if (!existingRuleNames.contains(regola.getNome())) {

                    regola.setID(contatore);
                    jsonArray.put(regola.toJSONObject());
                    existingRuleNames.add(regola.getNome());

                    contatore++;

                }

            }

            try {

                Files.write(filePath, jsonContainer.toString(2).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);

            } catch (IOException e) {

                System.err.println("Errore durante la scrittura nel file JSON.");
                e.printStackTrace();
            }
        }
    }


    private void updateContatoreFromExistingRules(JSONArray existingRules) {

        for (int i = 0; i < existingRules.length(); i++) {

            JSONObject existingRule = existingRules.getJSONObject(i);
            int existingID = existingRule.getInt("ID");
            contatore = Math.max(contatore, existingID + 1);

        }

    }

    public void aggiornaFile(Regola r) {

        Path filePath = Paths.get("ListaRegole.json");

        try {

            String fileContent = new String(Files.readAllBytes(filePath), StandardCharsets.UTF_8);

            JSONObject jsonContainer = new JSONObject(fileContent);
            JSONArray jsonArray = jsonContainer.getJSONArray("regole");


            // Trova e rimuovi la regola dal JSONArray:
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonRegola = jsonArray.getJSONObject(i);
                Regola regola = deserializeRegola(jsonRegola);

                if (regola.equals(r)) {
                   ;
                    if (r.getStato() == "Disattiva") {

                        regola.setStato(false);
                        regola.setEseguito(true);

                    }
                    if (r.isRipetibile()) {

                        regola.setUltimaEsecuzione(Instant.now());

                    }

                    jsonArray.put(i, regola.toJSONObject());

                    break;

                }
            }

            Files.write(filePath, jsonContainer.toString(2).getBytes(StandardCharsets.UTF_8));

        } catch (IOException ex) {

            throw new RuntimeException(ex);

        }
    }


    private Regola deserializeRegola(JSONObject jsonRegola) {

        int id = jsonRegola.getInt("ID");
        String nome = jsonRegola.getString("nome");
        boolean stato = jsonRegola.getBoolean("stato");
        boolean eseguito = jsonRegola.getBoolean("eseguito");

        // Deserializza il Trigger:
        JSONObject jsonTrigger = jsonRegola.optJSONObject("trigger");
        Trigger trigger = deserializeTrigger(jsonTrigger);

        // Deserializza l'Azione:
        JSONObject jsonAzione = jsonRegola.optJSONObject("azione");
        Azione azione = deserializeAzione(jsonAzione);

        boolean ripetibile = jsonRegola.getBoolean("ripetibile");

        Regola regola = null;

        if (ripetibile) {

            String p = jsonRegola.getString("ibernazione");
            java.time.Duration periodoIbernazione = java.time.Duration.parse(p);

            String w = jsonRegola.getString("ultimaEsecuzione");
            Instant ultimaEsecuzione;
            if (w != null)
                ultimaEsecuzione = Instant.parse(w);
            else
                ultimaEsecuzione = Instant.EPOCH;
            regola = new Regola(id, nome, trigger, azione, stato, eseguito, ripetibile, periodoIbernazione, ultimaEsecuzione);
        }
        else

            regola = new Regola(id, nome, trigger, azione, stato, eseguito);


        regola.setStato(stato);
        regola.setEseguito(eseguito);

        return regola;

    }



    private Trigger deserializeTrigger(JSONObject jsonTrigger) {

        String tipoTrigger = jsonTrigger.getString("tipo");

        if ("TempoDelGiorno".equals(tipoTrigger)) {
            LocalTime tempo = LocalTime.parse(jsonTrigger.getString("tempo"));
            return new TempoDelGiorno(tempo);
        }
        if ("TriggerSettimanale".equals(tipoTrigger)) {
            LocalTime tempo = LocalTime.parse(jsonTrigger.getString("tempo"));
            DayOfWeek giorno = DayOfWeek.valueOf(jsonTrigger.getString("giornosettimana"));
            return new TriggerSettimanale(tempo, giorno);
        }
        if ("TriggerMensile".equals(tipoTrigger)) {
            LocalTime tempo = LocalTime.parse(jsonTrigger.getString("tempo"));
            LocalDate giorno = LocalDate.parse(jsonTrigger.getString("giornomese"));
            return new TriggerMensile(tempo, giorno);
        }
        if ("TriggerSuData".equals(tipoTrigger)) {
            LocalTime tempo = LocalTime.parse(jsonTrigger.getString("tempo"));
            LocalDate data = LocalDate.parse(jsonTrigger.getString("data"));
            return new TriggerSuData(tempo, data);
        }
        if ("FileEsiste".equals(tipoTrigger)) {
            String percorso = jsonTrigger.getString("percorso");
            String nomefile = jsonTrigger.getString("nomefile");
            return new FileEsiste(percorso, nomefile);
        }
        if ("DimensioneFile".equals(tipoTrigger)) {
            String percorso = jsonTrigger.getString("percorso");
            int dimensione = jsonTrigger.getInt("dimensione");
            String unita = jsonTrigger.getString("unita");
            return new DimensioneFile(percorso, dimensione, unita);
        }
        if ("ExitStatus".equals(tipoTrigger)) {
            String percorso = jsonTrigger.getString("percorso");
            Integer exitStatus = jsonTrigger.getInt("exitStatus");
            return new ExitStatus(percorso, exitStatus);
        }
        if ("ContatoreIntero".equals(tipoTrigger))
        {
            JSONObject c = jsonTrigger.getJSONObject("contatore");
            Contatore contatore = Contatore.deserializeContatore(c);
            Integer valore = jsonTrigger.getInt("valore");
            String confronto = jsonTrigger.getString("confronto");
            return new ContatoreIntero(contatore, valore, confronto);
        }
        if ("ConfrontoContatori".equals(tipoTrigger))
        {
            JSONObject c1 = jsonTrigger.getJSONObject("contatore1");
            Contatore contatore1 = Contatore.deserializeContatore(c1);
            String confronto = jsonTrigger.getString("confronto");
            JSONObject c2 = jsonTrigger.getJSONObject("contatore2");
            Contatore contatore2 = Contatore.deserializeContatore(c2);
            return new ConfrontoContatori(contatore1, confronto, contatore2);
        }

        return null;

    }


    private Azione deserializeAzione(JSONObject jsonAzione) {

        String tipoAzione = jsonAzione.getString("tipo");

        if ("FinestraDialogo".equals(tipoAzione)) {
            return FinestraDialogo.deserialize(jsonAzione);
        }
        if ("RiproduciAudio".equals(tipoAzione)) {
            return RiproduciAudio.deserialize(jsonAzione);
        }
        if ("AggiungiStringa".equals(tipoAzione)) {
            return AggiungiStringa.deserialize(jsonAzione);
        }
        if ("CopiaSposta".equals(tipoAzione)) {
            return CopiaSposta.deserialize(jsonAzione);
        }
        if ("EliminaFile".equals(tipoAzione)) {
            return EliminaFile.deserialize(jsonAzione);
        }
        if ("EseguiProgramma".equals(tipoAzione)) {
            return EseguiProgramma.deserialize(jsonAzione);
        }
        if("CambiaValoreContatore".equals(tipoAzione)) {
            return CambiaValoreContatore.deserialize(jsonAzione);
        }
        if("SommaValoreContatore".equals(tipoAzione)) {
            return SommaValoreContatore.deserialize(jsonAzione);
        }
        if("SommaContatori".equals(tipoAzione)) {
            return SommaContatori.deserialize(jsonAzione);
        }

        return null;

    }

}

