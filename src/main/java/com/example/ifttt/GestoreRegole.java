package com.example.ifttt;

import javafx.collections.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;


public class GestoreRegole {

    private ObservableList<Regola> listaRegole;


    //---COSTRUTTORE---//
    public GestoreRegole(ObservableList<Regola> listaRegole) {
        this.listaRegole = listaRegole;
    }
    //----------------//



    // Metodo per aggiungere una regola alla lista
    public void addRegola(Regola nuovaRegola) {
        listaRegole.add(nuovaRegola);
    }

    // Metodo per rimuovere una regola dalla lista
    public void removeRegola(Regola regolaDaRimuovere) {
        listaRegole.remove(regolaDaRimuovere);
    }

    // Metodo per attivare una regola
    public void attivaRegola(Regola regolaDaAttivare) {
        regolaDaAttivare.setStato(true);
    }

    // Metodo per disattivare una regola
    public void disattivaRegola(Regola regolaDaDisattivare) {
        regolaDaDisattivare.setStato(false);
    }

    // Metodo per modificare una regola esistente
    public void modificaRegola(Regola regolaDaModificare, Trigger nuovoTrigger, Azione nuovaAzione, boolean nuovoStato, boolean nuovaRipetibilita, LocalTime nuovaQuiescenza) {
        regolaDaModificare.setTrigger(nuovoTrigger);
        regolaDaModificare.setAzione(nuovaAzione);
        regolaDaModificare.setStato(nuovoStato);
        regolaDaModificare.setRipetibile(nuovaRipetibilita);
        regolaDaModificare.setQuiescenza(nuovaQuiescenza);
    }

    // Metodo per salvare le regole su un file di testo
    public void salvaSuFile(String nomeFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeFile))) {
            for (Regola regola : listaRegole) {
                // Scrivi le informazioni della regola nel file
                writer.write(regola.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}