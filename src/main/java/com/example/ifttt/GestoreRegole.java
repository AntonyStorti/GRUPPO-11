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
import java.time.Instant;
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
            return TempoDelGiorno.deserialize(jsonTrigger);
        }
        if ("TriggerSettimanale".equals(tipoTrigger)) {
            return TriggerSettimanale.deserialize(jsonTrigger);
        }
        if ("TriggerMensile".equals(tipoTrigger)) {
            return TriggerMensile.deserialize(jsonTrigger);
        }
        if ("TriggerSuData".equals(tipoTrigger)) {
            return TriggerSuData.deserialize(jsonTrigger);
        }
        if ("FileEsiste".equals(tipoTrigger)) {
            return FileEsiste.deserialize(jsonTrigger);
        }
        if ("DimensioneFile".equals(tipoTrigger)) {
            return DimensioneFile.deserialize(jsonTrigger);
        }
        if ("ExitStatus".equals(tipoTrigger)) {
            return ExitStatus.deserialize(jsonTrigger);
        }
        if ("ContatoreIntero".equals(tipoTrigger))
        {
            return ContatoreIntero.deserialize(jsonTrigger);
        }
        if ("ConfrontoContatori".equals(tipoTrigger))
        {
            return ConfrontoContatori.deserialize(jsonTrigger);
        }
        if ("TriggerComposto".equals(tipoTrigger))
        {
            return TriggerComposto.deserialize(jsonTrigger);
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
        if("AzioneComposta".equals(tipoAzione)) {
            return AzioneComposta.deserialize(jsonAzione);
        }

        return null;

    }

}

