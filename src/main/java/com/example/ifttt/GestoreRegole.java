package com.example.ifttt;

import javafx.collections.ObservableList;
import java.util.logging.Logger;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;



public class GestoreRegole implements Runnable {


    private ObservableList<Regola> listaRegole;
    private final int ripetiVerifica = 3;



    //---COSTRUTTORE---//
    public GestoreRegole(ObservableList<Regola> listaRegole) {
        this.listaRegole = listaRegole;
    }
    //----------------//



    @Override
    public void run() {

        while(!Thread.currentThread().isInterrupted()){

            synchronized(listaRegole){

                //Itero sugli elementi della lista//
                for(Regola a : listaRegole)
                    a.valutaEsecuzione();

            }

            try{

                Thread.sleep(ripetiVerifica*1000);

                }
            catch (InterruptedException ex) {

                 Logger.getLogger(GestoreRegole.class.getName());

            }

        }

    }




    //-----METODI PER GESTIRE LE REGOLE-----//
    public void addRegola(Regola nuovaRegola) {
        listaRegole.add(nuovaRegola);
    }

    // Metodo per rimuovere una regola dalla lista
    public void removeRegola(Regola regolaDaRimuovere) {
        listaRegole.remove(regolaDaRimuovere);
    }



    public void attivaRegola(Regola regolaDaAttivare) {
        regolaDaAttivare.setStato(true);
    }

    public void disattivaRegola(Regola regolaDaDisattivare) {
        regolaDaDisattivare.setStato(false);
    }


}