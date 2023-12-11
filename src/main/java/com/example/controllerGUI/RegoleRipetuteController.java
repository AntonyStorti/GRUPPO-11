package com.example.controllerGUI;

import com.example.azioni.*;
import com.example.ifttt.*;
import com.example.trigger.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;


public class RegoleRipetuteController {

    @FXML
    public ChoiceBox<String> Trigger1;
    @FXML
    public ChoiceBox<String> Trigger2;
    @FXML
    public ChoiceBox<String> Trigger3;
    @FXML
    public ChoiceBox<String> Azione1;
    @FXML
    public ChoiceBox<String> Azione2;
    @FXML
    public ChoiceBox<String> Azione3;
    @FXML
    public CheckBox nega1;
    @FXML
    public ChoiceBox<String> LogicaT1;
    @FXML
    public CheckBox nega2;
    @FXML
    public ChoiceBox<String> LogicaA1;
    @FXML
    public CheckBox nega3;
    @FXML
    public ChoiceBox<String> LogicaA2;
    @FXML
    public ChoiceBox<String> LogicaT2;
    @FXML
    public TextField sceltaNome;
    @FXML
    public Button creaButton;


    public static ObservableList<Regola> lista = FXCollections.observableArrayList(GestoreRegole.listaRegole);


    private SharedMemory sharedDataModel = new SharedMemory();
    private GestoreRegole gestore = new GestoreRegole(lista);
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
    private RiproduciAudio tracciaAudio;
    private EseguiProgramma app;
    private String percorsoFile;
    private String stringa;
    private EliminaFile path;
    private String sorgente;
    private String destinazione;
    private String percorsoAPP;
    private String scelta;
    private Duration periodoIbernazione;
    private Contatore contatore;
    private Integer intero;
    private String confronto;
    private Contatore contatore1;
    private String confronto2;
    private Contatore contatore2;
    private Contatore contatoreDaAggiornare;
    private Integer nuovoValore;
    private Contatore contatorePiuIntero;
    private Integer valoreDaSommare;
    private Contatore contatoreSomma1;
    private Contatore contatoreSomma2;
    private Integer exitStatusU;

    private HelloController helloController;



    //Tipi di Trigger;
    private String sceltaOra = "Seleziona un orario";
    private String sceltaGiorno = "Seleziona un giorno";
    private String sceltaGMese = "Seleziona un giorno del mese";
    private String sceltaData = "Seleziona una data";
    private String esisteFile = "Se esiste un File";
    private String dimFile = "Se la dimensione del file è > di";
    private String exitStatus = "Se un programma ha 'Exist status':";
    private String contatoreInt = "Confronta un contatore con intero";
    private String confrontaContatori = "Confronta due contatori";



    //Tipi di Azione:
    private String sceltaMes = "Visualizza messaggio";
    private String sceltaAud = "Riproduci un audio";
    private String sceltaPro = "Esegui un programma";
    private String sceltaStringa = "Aggiungi una stringa";
    private String sceltaEliminaFile = "Elimina un file";
    private String sceltaCopiaSposta = "Copia/Sposta un file";
    private String sceltaCambiaValore = "Settare contatore a un valore";
    private String sceltaSommaValore = "Sommare un intero a un contatore";
    private String sceltaSommaContatori = "Sommare due contatori";


    @FXML
    private void initialize(){

        creaButton.setOnAction(event -> creaRegola());
        Trigger1.setOnAction(this::apriFinestraSceltaTrigger);
        Azione1.setOnAction(this::apriFinestraSceltaAzioni);
        LogicaT1.setOnAction(event -> {
            Trigger2.setDisable(false);
            Trigger2.setOnAction(this::apriFinestraSceltaTrigger);
            nega2.setDisable(false);
            LogicaT2.setDisable(false);
        });
        LogicaT2.setOnAction(event -> {
            Trigger3.setDisable(false);
            Trigger3.setOnAction(this::apriFinestraSceltaTrigger);
            nega3.setDisable(false);
        });
        LogicaA1.setOnAction(event -> {
            Azione2.setDisable(false);
            Azione2.setOnAction(this::apriFinestraSceltaAzioni);
            LogicaA2.setDisable(false);
        });
        LogicaA2.setOnAction(event -> {
            Azione3.setDisable(false);
            Azione3.setOnAction(this::apriFinestraSceltaAzioni);
        });

    }

    public void setHelloController(HelloController helloController) {
        this.helloController = helloController;
    }

    public void apriFinestraSceltaTrigger(ActionEvent evento) {

        ChoiceBox<String> choiceBox = (ChoiceBox<String>) evento.getSource();
        String selectedItem = choiceBox.getSelectionModel().getSelectedItem();

        if (selectedItem.equals(sceltaOra)){

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ifttt/SceltaOra.fxml"));
                Parent root = loader.load();
                SceltaOraController controller = loader.getController();
                controller.setSharedDataModel(sharedDataModel);

                Stage stage = new Stage();
                stage.setTitle("Seleziona un orario...");
                stage.setScene(new Scene(root));

                stage.setOnHidden(event -> {
                    orarioScelto = sharedDataModel.getOrarioScelto();

                });

                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (selectedItem.equals(sceltaGiorno)){


            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ifttt/GiornoSettimana.fxml"));
                Parent root = loader.load();
                GiornoSettimanaController controller = loader.getController();
                controller.setSharedDataModel(sharedDataModel);

                Stage stage = new Stage();
                stage.setTitle("Seleziona un giorno...");
                stage.setScene(new Scene(root));

                stage.setOnHidden(event -> {
                    giornoSettimana = sharedDataModel.getGiornoSettimana();

                });

                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (selectedItem.equals(sceltaGMese)){

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ifttt/GiornoMese.fxml"));
                Parent root = loader.load();
                GiornoMeseController controller = loader.getController();
                controller.setSharedDataModel(sharedDataModel);

                Stage stage = new Stage();
                stage.setTitle("Seleziona un giorno del mese...");
                stage.setScene(new Scene(root));

                stage.setOnHidden(event -> {
                    giornoMese = sharedDataModel.getGiornoMese();

                });

                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        if (selectedItem.equals(sceltaData)){

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ifttt/DataCompleta.fxml"));
                Parent root = loader.load();
                DataCompletaController controller = loader.getController();
                controller.setSharedDataModel(sharedDataModel);

                Stage stage = new Stage();
                stage.setTitle("Seleziona una data...");
                stage.setScene(new Scene(root));

                stage.setOnHidden(event -> {
                    dataCompleta = sharedDataModel.getDataCompleta();

                });

                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        if (selectedItem.equals(esisteFile)){

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ifttt/FileEsiste.fxml"));
                Parent root = loader.load();
                FileEsisteController controller = loader.getController();
                controller.setSharedDataModel(sharedDataModel);

                Stage stage = new Stage();
                stage.setTitle("Se esiste questo file nella cartella...");
                stage.setScene(new Scene(root));

                stage.setOnHidden(event -> {
                    nomeFileE = sharedDataModel.getNomeFileE();
                    cartella = sharedDataModel.getCartella();

                });

                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        if (selectedItem.equals(dimFile)){

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ifttt/DimensioneFile.fxml"));
                Parent root = loader.load();
                DimensioneFileController controller = loader.getController();
                controller.setSharedDataModel(sharedDataModel);

                Stage stage = new Stage();
                stage.setTitle("Se questo file ha dimensioni superiori a...");
                stage.setScene(new Scene(root));

                stage.setOnHidden(event -> {
                    nomeFile = sharedDataModel.getNomeFile();
                    dimensione = sharedDataModel.getDimensione();
                    unita = sharedDataModel.getUnita();

                });

                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        if (selectedItem.equals(exitStatus)){

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ifttt/ExitStatus.fxml"));
                Parent root = loader.load();
                ExitStatusController controller = loader.getController();
                controller.setSharedDataModel(sharedDataModel);

                Stage stage = new Stage();
                stage.setTitle("Scegli l'App da eseguire...");
                stage.setScene(new Scene(root));

                stage.setOnHidden(event -> {

                    percorsoAPP = sharedDataModel.getPercorsoAPP();
                    exitStatusU = sharedDataModel.getExitStatus();

                });

                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        if (selectedItem.equals(contatoreInt)){

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ifttt/ConfrontaIntero.fxml"));
                Parent root = loader.load();
                ConfrontaInteroController controller = loader.getController();
                controller.setSharedDataModel(sharedDataModel);

                Stage stage = new Stage();
                stage.setTitle("Confronta contatore con intero...");
                stage.setScene(new Scene(root));

                stage.setOnHidden(event -> {

                    contatore = sharedDataModel.getContatore();
                    intero = sharedDataModel.getIntero();
                    confronto = sharedDataModel.getOperazione();
                });

                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        if (selectedItem.equals(confrontaContatori)){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ifttt/ConfrontaContatori.fxml"));
                Parent root = loader.load();
                ConfrontaContatoriController controller = loader.getController();
                controller.setSharedDataModel(sharedDataModel);

                Stage stage = new Stage();
                stage.setTitle("Confronta due contatori...");
                stage.setScene(new Scene(root));

                stage.setOnHidden(event -> {

                    contatore1 = sharedDataModel.getContatore1();
                    confronto2 = sharedDataModel.getOperazione2();
                    contatore2 = sharedDataModel.getContatore2();
                });

                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }



    public void apriFinestraSceltaAzioni(ActionEvent evento) {

        ChoiceBox<String> sceltaAzione = (ChoiceBox<String>) evento.getSource();
        String selectedItem = sceltaAzione.getSelectionModel().getSelectedItem();


        if (selectedItem.equals(sceltaMes)) {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ifttt/FinestraDialogo.fxml"));
                Parent root = loader.load();
                FinestraDialogoController controller = loader.getController();
                controller.setSharedDataModel(sharedDataModel);

                Stage stage = new Stage();
                stage.setTitle("Scrivi il tuo messaggio...");
                stage.setScene(new Scene(root));

                stage.setOnHidden(event -> {
                    testoUtente = sharedDataModel.getTestoUtente();

                });

                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }


        } else if (selectedItem.equals(sceltaAud)) {

            try {

                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Seleziona un file audio (.mp3, .waw, .mp4...)");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("File audio", "*.wav")
                );

                Stage stage = (Stage) sceltaAzione.getScene().getWindow();
                File fileSelezionato = fileChooser.showOpenDialog(stage);


                if (fileSelezionato != null) {
                    String percorsoFileAudio = fileSelezionato.getAbsolutePath();
                    tracciaAudio = new RiproduciAudio(percorsoFileAudio);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (selectedItem.equals(sceltaPro)){

            try {

                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Seleziona il programma da eseguire...");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("File eseguibile", "*.exe", "*.app")
                );

                Stage stage = (Stage) sceltaAzione.getScene().getWindow();
                File fileSelezionato = fileChooser.showOpenDialog(stage);


                if (fileSelezionato != null) {
                    String percorsoAPP = fileSelezionato.getAbsolutePath();
                    app = new EseguiProgramma(percorsoAPP);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (selectedItem.equals(sceltaStringa)) {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ifttt/AggiungiStringa.fxml"));
                Parent root = loader.load();
                AggiungiStringaController controller = loader.getController();
                controller.setSharedDataModel(sharedDataModel);

                Stage stage = new Stage();
                stage.setTitle("Scrivi la stringa da aggiungere...");
                stage.setScene(new Scene(root));

                stage.setOnHidden(event -> {
                    percorsoFile = sharedDataModel.getPercorsoFile();
                    stringa = sharedDataModel.getStringa();

                });

                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (selectedItem.equals(sceltaEliminaFile)){

            try {

                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Seleziona il file da eliminare...");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("File di testo", "*.txt")
                );

                Stage stage = (Stage) sceltaAzione.getScene().getWindow();
                File fileSelezionato = fileChooser.showOpenDialog(stage);


                if (fileSelezionato != null) {
                    String percorso = fileSelezionato.getAbsolutePath();
                    path = new EliminaFile(percorso);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (selectedItem.equals(sceltaCopiaSposta)) {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ifttt/SorgenteDestinazione.fxml"));
                Parent root = loader.load();
                SorgenteDestinazioneController controller = loader.getController();
                controller.setSharedDataModel(sharedDataModel);

                Stage stage = new Stage();
                stage.setTitle("Opera sui tuoi File...");
                stage.setScene(new Scene(root));

                stage.setOnHidden(event -> {
                    sorgente = sharedDataModel.getSorgente();
                    destinazione = sharedDataModel.getDestinazione();
                    scelta = sharedDataModel.getScelta();

                });

                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (selectedItem.equals(sceltaCambiaValore)) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ifttt/CambiaValoreContatore.fxml"));
                Parent root = loader.load();
                CambiaValoreContatoreController controller = loader.getController();
                controller.setSharedDataModel(sharedDataModel);

                Stage stage = new Stage();
                stage.setTitle("Cambia il valore di un contatore...");
                stage.setScene(new Scene(root));

                stage.setOnHidden(event -> {
                    contatoreDaAggiornare = sharedDataModel.getContatoreDaAggiornare();
                    nuovoValore = sharedDataModel.getNuovoValore();

                });

                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (selectedItem.equals(sceltaSommaValore)) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ifttt/SommaValoreContatore.fxml"));
                Parent root = loader.load();
                SommaValoreContatoreController controller = loader.getController();
                controller.setSharedDataModel(sharedDataModel);

                Stage stage = new Stage();
                stage.setTitle("Somma un valore ad un contatore...");
                stage.setScene(new Scene(root));

                stage.setOnHidden(event -> {
                    contatorePiuIntero = sharedDataModel.getContatorePiuIntero();
                    valoreDaSommare = sharedDataModel.getValoreDaSommare();

                });

                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (selectedItem.equals(sceltaSommaContatori)) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ifttt/SommaContatori.fxml"));
                Parent root = loader.load();
                SommaContatoriController controller = loader.getController();
                controller.setSharedDataModel(sharedDataModel);

                Stage stage = new Stage();
                stage.setTitle("Somma due contatori...");
                stage.setScene(new Scene(root));

                stage.setOnHidden(event -> {
                    contatoreSomma1 = sharedDataModel.getContatoreSomma1();
                    contatoreSomma2 = sharedDataModel.getContatoreSomma2();

                });

                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void creaRegola() {

        String nome = sceltaNome.getText();

        String selectedTrigger1 = Trigger1.getValue();
        String selectedTrigger2 = Trigger2.getValue();
        String selectedTrigger3 = Trigger3.getValue();
        String selectedAzione1 = Azione1.getValue();
        String selectedAzione2 = Azione2.getValue();
        String selectedAzione3 = Azione3.getValue();
        String logica1 = LogicaT1.getValue();
        String logica2 = LogicaT2.getValue();

        boolean nT1 = nega1.isSelected();
        boolean nT2 = nega2.isSelected();
        boolean nT3 = nega3.isSelected();


        //Trigger:
        Trigger t1 = null;
        Trigger t2 = null;
        Trigger t3 = null;

        if (selectedTrigger1.equals(sceltaOra) || selectedTrigger2 != null && selectedTrigger2.equals(sceltaOra) || selectedTrigger3 != null && selectedTrigger3.equals(sceltaOra)) {
            if (selectedTrigger1.equals(sceltaOra))
                t1 = new TempoDelGiorno(orarioScelto);
            if (selectedTrigger2 != null && selectedTrigger2.equals(sceltaOra))
                t2 = new TempoDelGiorno(orarioScelto);
            if (selectedTrigger3 != null && selectedTrigger3.equals(sceltaOra))
                t3 = new TempoDelGiorno(orarioScelto);
        }
        if (selectedTrigger1.equals(sceltaGiorno) || selectedTrigger2 != null && selectedTrigger2.equals(sceltaGiorno) || selectedTrigger3 != null && selectedTrigger3.equals(sceltaGiorno)){
            if (selectedTrigger1.equals(sceltaGiorno))
                t1 = new TriggerSettimanale(LocalTime.of(0, 0), giornoSettimana);
            if (selectedTrigger2 != null && selectedTrigger2.equals(sceltaGiorno))
                t2 = new TriggerSettimanale(LocalTime.of(0, 0), giornoSettimana);
            if (selectedTrigger3 != null && selectedTrigger3.equals(sceltaGiorno))
                t3 = new TriggerSettimanale(LocalTime.of(0, 0), giornoSettimana);
        }
        if (selectedTrigger1.equals(sceltaGMese) || selectedTrigger2 != null && selectedTrigger2.equals(sceltaGMese) || selectedTrigger3 != null && selectedTrigger3.equals(sceltaGMese)) {
            if(selectedTrigger1.equals(sceltaGMese))
                t1 = new TriggerMensile(LocalTime.of(0, 0), giornoMese);
            if(selectedTrigger2 != null && selectedTrigger2.equals(sceltaGMese))
                t2 = new TriggerMensile(LocalTime.of(0, 0), giornoMese);
            if(selectedTrigger3 != null && selectedTrigger3.equals(sceltaGMese))
                t3 = new TriggerMensile(LocalTime.of(0, 0), giornoMese);
        }
        if (selectedTrigger1.equals(sceltaData) || selectedTrigger2 != null && selectedTrigger2.equals(sceltaData) || selectedTrigger3 != null && selectedTrigger3.equals(sceltaData)) {
            if (selectedTrigger1.equals(sceltaData))
                t1 = new TriggerSuData(LocalTime.of(0, 0), dataCompleta);
            if (selectedTrigger2 != null && selectedTrigger2.equals(sceltaData))
                t2 = new TriggerSuData(LocalTime.of(0, 0), dataCompleta);
            if (selectedTrigger3 != null && selectedTrigger3.equals(sceltaData))
                t3 = new TriggerSuData(LocalTime.of(0, 0), dataCompleta);
        }
        if (selectedTrigger1.equals(esisteFile) || selectedTrigger2 != null && selectedTrigger2.equals(esisteFile) || selectedTrigger3 != null && selectedTrigger3.equals(esisteFile)){
            if (selectedTrigger1.equals(esisteFile))
                t1 = new FileEsiste(cartella, nomeFileE);
            if (selectedTrigger2 != null && selectedTrigger2.equals(esisteFile))
                t2 = new FileEsiste(cartella, nomeFileE);
            if (selectedTrigger3 != null && selectedTrigger3.equals(esisteFile))
                t3 = new FileEsiste(cartella, nomeFileE);
        }
        if (selectedTrigger1.equals(dimFile) || selectedTrigger2 != null && selectedTrigger2.equals(dimFile) || selectedTrigger3 != null && selectedTrigger3.equals(dimFile)){
            if(selectedTrigger1.equals(dimFile))
                t1 = new DimensioneFile(nomeFile,dimensione, unita);
            if(selectedTrigger2 != null && selectedTrigger2.equals(dimFile))
                t2 = new DimensioneFile(nomeFile,dimensione, unita);
            if(selectedTrigger3 != null && selectedTrigger3.equals(dimFile))
                t3 = new DimensioneFile(nomeFile,dimensione, unita);
        }
        if (selectedTrigger1.equals(exitStatus) || selectedTrigger2 != null && selectedTrigger2.equals(exitStatus) || selectedTrigger3 != null && selectedTrigger3.equals(exitStatus)){
            if (selectedTrigger1.equals(exitStatus))
                t1 = new ExitStatus(percorsoAPP, exitStatusU);
            if (selectedTrigger2 != null && selectedTrigger2.equals(exitStatus))
                t2 = new ExitStatus(percorsoAPP, exitStatusU);
            if (selectedTrigger3 != null && selectedTrigger3.equals(exitStatus))
                t3 = new ExitStatus(percorsoAPP, exitStatusU);
        }
        if (selectedTrigger1.equals(contatoreInt) || selectedTrigger2 != null && selectedTrigger2.equals(contatoreInt) || selectedTrigger3 != null && selectedTrigger3.equals(contatoreInt)){
            if (selectedTrigger1.equals(contatoreInt))
                t1 = new ContatoreIntero(contatore, intero,confronto);
            if (selectedTrigger2 != null && selectedTrigger2.equals(contatoreInt))
                t2 = new ContatoreIntero(contatore, intero,confronto);
            if (selectedTrigger3 != null && selectedTrigger3.equals(contatoreInt))
                t3 = new ContatoreIntero(contatore, intero,confronto);
        }
        if (selectedTrigger1.equals(confrontaContatori) || selectedTrigger2 != null && selectedTrigger2.equals(confrontaContatori) || selectedTrigger3 != null && selectedTrigger3.equals(confrontaContatori)) {
            if (selectedTrigger1.equals(confrontaContatori))
                t1 = new ConfrontoContatori(contatore1, confronto2, contatore2);
            if (selectedTrigger2 != null && selectedTrigger2.equals(confrontaContatori))
                t2 = new ConfrontoContatori(contatore1, confronto2, contatore2);
            if (selectedTrigger3 != null && selectedTrigger3.equals(confrontaContatori))
                t3 = new ConfrontoContatori(contatore1, confronto2, contatore2);
        }

        Trigger t;

        if (t1 != null && t2 != null && t3 != null)
            t = new TriggerComposto(t1, t2, t3, logica1, logica2, nT1, nT2, nT3);
        else if (t1 != null && t2 != null)
            t = new TriggerComposto(t1, t2, logica1, nT1, nT2);
        else
            t = new TriggerComposto(t1, nT1);

        //Azioni:
        Azione a1 = null;
        Azione a2 = null;
        Azione a3 = null;

        if (selectedAzione1.equals(sceltaMes) || selectedAzione2 != null && selectedAzione2.equals(sceltaMes) || selectedAzione3 != null && selectedAzione3.equals(sceltaMes)) {
            if (selectedAzione1.equals(sceltaMes))
                a1 = new FinestraDialogo(testoUtente);
            if (selectedAzione2 != null && selectedAzione2.equals(sceltaMes))
                a2 = new FinestraDialogo(testoUtente);
            if (selectedAzione3 != null && selectedAzione3.equals(sceltaMes))
                a3 = new FinestraDialogo(testoUtente);
        }
        if (selectedAzione1.equals(sceltaAud) || selectedAzione2 != null && selectedAzione2.equals(sceltaAud) || selectedAzione3 != null && selectedAzione3.equals(sceltaAud)) {
            if (selectedAzione1.equals(sceltaAud))
                a1 = tracciaAudio;
            if (selectedAzione2 != null && selectedAzione2.equals(sceltaAud))
                a2 = tracciaAudio;
            if (selectedAzione3 != null && selectedAzione3.equals(sceltaAud))
                a3 = tracciaAudio;
        }
        if (selectedAzione1.equals(sceltaPro) || selectedAzione2 != null && selectedAzione2.equals(sceltaPro) || selectedAzione3 != null && selectedAzione3.equals(sceltaPro)) {
            if (selectedAzione1.equals(sceltaPro))
                a1 = app;
            if (selectedAzione2 != null && selectedAzione2.equals(sceltaPro))
                a2 = app;
            if (selectedAzione3 != null && selectedAzione3.equals(sceltaPro))
                a3 = app;
        }
        if (selectedAzione1.equals(sceltaStringa) || selectedAzione2 != null && selectedAzione2.equals(sceltaStringa) || selectedAzione3 != null && selectedAzione3.equals(sceltaStringa)){
            if (selectedAzione1.equals(sceltaStringa))
                a1 = new AggiungiStringa(percorsoFile, stringa);
            if (selectedAzione2 != null && selectedAzione2.equals(sceltaStringa))
                a2 = new AggiungiStringa(percorsoFile, stringa);
            if (selectedAzione3 != null && selectedAzione3.equals(sceltaStringa))
                a3 = new AggiungiStringa(percorsoFile, stringa);
        }
        if (selectedAzione1.equals(sceltaEliminaFile) || selectedAzione2 != null && selectedAzione2.equals(sceltaEliminaFile) || selectedAzione3 != null && selectedAzione3.equals(sceltaEliminaFile)) {
            if (selectedAzione1.equals(sceltaEliminaFile))
                a1 = path;
            if (selectedAzione2 != null && selectedAzione2.equals(sceltaEliminaFile))
                a2 = path;
            if (selectedAzione3 != null && selectedAzione3.equals(sceltaEliminaFile))
                a3 = path;
        }
        if (selectedAzione1.equals(sceltaCopiaSposta) || selectedAzione2 != null && selectedAzione2.equals(sceltaCopiaSposta) || selectedAzione3 != null && selectedAzione3.equals(sceltaCopiaSposta)){
            if (selectedAzione1.equals(sceltaCopiaSposta))
                a1 = new CopiaSposta(sorgente, destinazione, scelta);
            if (selectedAzione2 != null && selectedAzione2.equals(sceltaCopiaSposta))
                a2 = new CopiaSposta(sorgente, destinazione, scelta);
            if (selectedAzione3 != null && selectedAzione3.equals(sceltaCopiaSposta))
                a3 = new CopiaSposta(sorgente, destinazione, scelta);
        }
        if (selectedAzione1.equals(sceltaCambiaValore) || selectedAzione2 != null && selectedAzione2.equals(sceltaCambiaValore) || selectedAzione3 != null && selectedAzione3.equals(sceltaCambiaValore)) {
            if (selectedAzione1.equals(sceltaCambiaValore))
                a1 = new CambiaValoreContatore(contatoreDaAggiornare, nuovoValore);
            if (selectedAzione2 != null && selectedAzione2.equals(sceltaCambiaValore))
                a2 = new CambiaValoreContatore(contatoreDaAggiornare, nuovoValore);
            if (selectedAzione3 != null && selectedAzione3.equals(sceltaCambiaValore))
                a3 = new CambiaValoreContatore(contatoreDaAggiornare, nuovoValore);
        }
        if (selectedAzione1.equals(sceltaSommaValore) || selectedAzione2 != null && selectedAzione2.equals(sceltaSommaValore) || selectedAzione3 != null && selectedAzione3.equals(sceltaSommaValore)) {
            if (selectedAzione1.equals(sceltaSommaValore))
                a1 = new SommaValoreContatore(contatorePiuIntero, valoreDaSommare);
            if (selectedAzione2 != null && selectedAzione2.equals(sceltaSommaValore))
                a2 = new SommaValoreContatore(contatorePiuIntero, valoreDaSommare);
            if (selectedAzione3 != null && selectedAzione3.equals(sceltaSommaValore))
                a3 = new SommaValoreContatore(contatorePiuIntero, valoreDaSommare);
        }
        if (selectedAzione1.equals(sceltaSommaContatori) || selectedAzione2 != null && selectedAzione2.equals(sceltaSommaContatori) || selectedAzione3 != null && selectedAzione3.equals(sceltaSommaContatori)) {
            if (selectedAzione1.equals(sceltaSommaContatori))
                a1 = new SommaContatori(contatoreSomma1, contatoreSomma2);
            if (selectedAzione2 != null && selectedAzione2.equals(sceltaSommaContatori))
                a2 = new SommaContatori(contatoreSomma1, contatoreSomma2);
            if (selectedAzione3 != null && selectedAzione3.equals(sceltaSommaContatori))
                a3 = new SommaContatori(contatoreSomma1, contatoreSomma2);
        }


        Azione a;

        if (a1 != null && a2 != null && a3 != null)
            a = new AzioneComposta(a1, a2, a3);
        else if (a1 != null && a2 != null)
            a = new AzioneComposta(a1, a2);
        else
            a = new AzioneComposta(a1);

        Regola r = new Regola(nome, t, a, true, false, false);

        gestore.aggiungiRegola(r);
        gestore.saveObjectsToFile();

        System.out.println(r);
        System.out.println(GestoreRegole.listaRegole);

        if (helloController != null) {
            helloController.aggiornaTabella(Collections.singletonList(r));
        }

        Stage stage = (Stage) creaButton.getScene().getWindow();
        stage.close();
    }

}