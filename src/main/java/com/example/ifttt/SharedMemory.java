package com.example.ifttt;

import javafx.scene.control.TableView;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;



public class SharedMemory {

    private LocalTime orarioScelto;
    private DayOfWeek giornoSettimana;
    private LocalDate giornoMese;
    private LocalDate dataCompleta;
    private String nomeFileE;
    private String cartella;
    private String nomeFile;
    private int dimensione;
    private String unita;
    private String testoUtente;
    private String percorsoFile;
    private String stringa;
    private String sorgente;
    private String destinazione;
    private String scelta;
    private String percorsoAPP;
    private Duration periodoIbernazione;
    private Contatore contatore;
    private Integer intero;
    private String operazione;
    private TableView<Contatore> tabellaContatori;
    private Contatore contatore1;
    private Contatore contatore2;
    private String operazione2;
    private Contatore contatoreDaAggiornare;
    private Integer nuovoValore;
    private Contatore contatorePiuIntero;
    private Integer valoreDaSommare;
    private Contatore contatoreSomma1;
    private Contatore contatoreSomma2;
    private Integer exitStatus;






    public LocalTime getOrarioScelto() {
        return orarioScelto;
    }

    public void setOrarioScelto(LocalTime orarioScelto) {
        this.orarioScelto = orarioScelto;
    }

    public DayOfWeek getGiornoSettimana() {
        return giornoSettimana;
    }

    public void setGiornoSettimana(DayOfWeek giornoSettimana) {
        this.giornoSettimana = giornoSettimana;
    }

    public LocalDate getGiornoMese() {
        return giornoMese;
    }

    public void setGiornoMese(LocalDate giornoMese) {
        this.giornoMese = giornoMese;
    }

    public LocalDate getDataCompleta() {
        return dataCompleta;
    }

    public void setDataCompleta(LocalDate dataCompleta) {
        this.dataCompleta = dataCompleta;
    }

    public String getNomeFileE() {
        return nomeFileE;
    }

    public void setNomeFileE(String nomeFileE) {
        this.nomeFileE = nomeFileE;
    }

    public String getNomeFile() {
        return nomeFile;
    }

    public void setNomeFile(String nomeFile) {
        this.nomeFile = nomeFile;
    }

    public String getCartella() {
        return cartella;
    }

    public void setCartella(String cartella) {
        this.cartella = cartella;
    }

    public int getDimensione() {
        return dimensione;
    }

    public void setDimensione(int dimensione) {
        this.dimensione = dimensione;
    }

    public String getUnita() {
        return unita;
    }

    public void setUnita(String unita) {
        this.unita = unita;
    }

    public String getTestoUtente() {
        return testoUtente;
    }

    public void setTestoUtente(String testoUtente) {
        this.testoUtente = testoUtente;
    }

    public String getPercorsoFile() {
        return percorsoFile;
    }

    public void setPercorsoFile(String percorsoFile) {
        this.percorsoFile = percorsoFile;
    }

    public String getStringa() {
        return stringa;
    }

    public void setStringa(String stringa) {
        this.stringa = stringa;
    }

    public String getSorgente() {
        return sorgente;
    }

    public void setSorgente(String sorgente) {
        this.sorgente = sorgente;
    }

    public String getDestinazione() {
        return destinazione;
    }

    public void setDestinazione(String destinazione) {
        this.destinazione = destinazione;
    }

    public String getScelta() {
        return scelta;
    }

    public void setScelta(String scelta) {
        this.scelta = scelta;
    }

    public Duration getPeriodoIbernazione() {
        return periodoIbernazione;
    }

    public void setPeriodoIbernazione(Duration periodoIbernazione) {
        this.periodoIbernazione = periodoIbernazione;
    }

    public Contatore getContatore() {
        return contatore;
    }

    public void setContatore(Contatore contatore) {
        this.contatore = contatore;
    }

    public Integer getIntero() {
        return intero;
    }

    public void setIntero(Integer intero) {
        this.intero = intero;
    }

    public String getOperazione() {
        return operazione;
    }

    public void setOperazione(String operazione) {
        this.operazione = operazione;
    }

    public TableView<Contatore> getTabellaContatori() {
        return tabellaContatori;
    }

    public void setTabellaContatori(TableView<Contatore> tabellaContatori) {
        this.tabellaContatori = tabellaContatori;
    }

    public Contatore getContatore1() {
        return contatore1;
    }

    public void setContatore1(Contatore contatore1) {
        this.contatore1 = contatore1;
    }

    public Contatore getContatore2() {
        return contatore2;
    }

    public void setContatore2(Contatore contatore2) {
        this.contatore2 = contatore2;
    }

    public String getOperazione2() {
        return operazione2;
    }

    public void setOperazione2(String operazione2) {
        this.operazione2 = operazione2;
    }

    public Contatore getContatoreDaAggiornare() {
        return contatoreDaAggiornare;
    }

    public void setContatoreDaAggiornare(Contatore contatoreDaAggiornare) {
        this.contatoreDaAggiornare = contatoreDaAggiornare;
    }

    public String getPercorsoAPP() {
        return percorsoAPP;
    }

    public void setPercorsoAPP(String percorsoAPP) {
        this.percorsoAPP = percorsoAPP;
    }

    public Integer getNuovoValore() {
        return nuovoValore;
    }

    public void setNuovoValore(Integer nuovoValore) {
        this.nuovoValore = nuovoValore;
    }

    public Contatore getContatorePiuIntero() {
        return contatorePiuIntero;
    }

    public void setContatorePiuIntero(Contatore contatorePiuIntero) {
        this.contatorePiuIntero = contatorePiuIntero;
    }

    public Integer getValoreDaSommare() {
        return valoreDaSommare;
    }

    public void setValoreDaSommare(Integer valoreDaSommare) {
        this.valoreDaSommare = valoreDaSommare;
    }

    public Contatore getContatoreSomma1() {
        return contatoreSomma1;
    }

    public void setContatoreSomma1(Contatore contatoreSomma1) {
        this.contatoreSomma1 = contatoreSomma1;
    }

    public Contatore getContatoreSomma2() {
        return contatoreSomma2;
    }

    public Integer getExitStatus() {
        return exitStatus;
    }

    public void setExitStatus(Integer exitStatus) {
        this.exitStatus = exitStatus;
    }

    public void setContatoreSomma2(Contatore contatoreSomma2) {
        this.contatoreSomma2 = contatoreSomma2;
    }

}
