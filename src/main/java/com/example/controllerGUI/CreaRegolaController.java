package com.example.controllerGUI;

import com.example.azioni.*;
import com.example.ifttt.*;
import com.example.trigger.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.time.*;
import java.util.Collections;


public class CreaRegolaController {


    @FXML
    private ChoiceBox<String> sceltaTrigger;
    @FXML
    private ChoiceBox<String> sceltaAzione;
    @FXML
    private TextField sceltaNome;
    @FXML
    private Button inviaButton;
    @FXML
    private CheckBox ripetibile;


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


    private HelloController helloController;



    //Tipi di Trigger;
    private String sceltaOra = "Seleziona un orario";
    private String sceltaGiorno = "Seleziona un giorno";
    private String sceltaGMese = "Seleziona un giorno del mese";
    private String sceltaData = "Seleziona una data";
    private String esisteFile = "Se esiste un File";
    private String dimFile = "Se la dimensione del file è > di";
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
    private void initialize() {
        sceltaTrigger.setOnAction(event -> apriFinestraSceltaTrigger());
        sceltaAzione.setOnAction(event -> apriFinestraSceltaAzioni());
        inviaButton.setOnAction(event -> creaRegola());
        ripetibile.setOnAction(event -> ripetiAzione());
    }


    public void setHelloController(HelloController helloController) {
        this.helloController = helloController;
    }

    public void apriFinestraSceltaTrigger() {

        String selectedItem = sceltaTrigger.getSelectionModel().getSelectedItem();


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



    public void apriFinestraSceltaAzioni() {

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
                // Creare un oggetto FileChooser
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Seleziona un file audio (.mp3, .waw, .mp4...)");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("File audio", "*.wav")
                );

                Stage stage = (Stage) sceltaAzione.getScene().getWindow();
                File fileSelezionato = fileChooser.showOpenDialog(stage);


                // Verifica se è stato selezionato un file
                if (fileSelezionato != null) {
                    // Salva il percorso del file selezionato
                    String percorsoFileAudio = fileSelezionato.getAbsolutePath();
                    tracciaAudio = new RiproduciAudio(percorsoFileAudio);
                }

            } catch (Exception e) {
                e.printStackTrace(); // Gestisci l'eccezione in modo adeguato
            }

        } else if (selectedItem.equals(sceltaPro)){

            try {
                // Creare un oggetto FileChooser
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Seleziona il programma da eseguire...");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("File eseguibile", "*.exe", "*.app")
                );

                Stage stage = (Stage) sceltaAzione.getScene().getWindow();
                File fileSelezionato = fileChooser.showOpenDialog(stage);


                // Verifica se è stato selezionato un file
                if (fileSelezionato != null) {
                    // Salva il percorso del file selezionato
                    String percorsoAPP = fileSelezionato.getAbsolutePath();
                    app = new EseguiProgramma(percorsoAPP);
                }
            } catch (Exception e) {
                e.printStackTrace(); // Gestisci l'eccezione in modo adeguato
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
                // Creare un oggetto FileChooser
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Seleziona il file da eliminare...");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("File di testo", "*.txt")
                );

                Stage stage = (Stage) sceltaAzione.getScene().getWindow();
                File fileSelezionato = fileChooser.showOpenDialog(stage);


                // Verifica se è stato selezionato un file
                if (fileSelezionato != null) {
                    // Salva il percorso del file selezionato
                    String percorso = fileSelezionato.getAbsolutePath();
                    path = new EliminaFile(percorso);
                }
            } catch (Exception e) {
                e.printStackTrace(); // Gestisci l'eccezione in modo adeguato
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



    public void creaRegola(){

        String nome = sceltaNome.getText();
        String selectedTrigger = sceltaTrigger.getValue();
        String selectedAzione = sceltaAzione.getValue();


        Trigger t = null;
        Azione a = null;


        //Trigger:
        if (selectedTrigger.equals(sceltaOra)) {
            t = new TempoDelGiorno(orarioScelto);
        }
        if (selectedTrigger.equals(sceltaGiorno)){
            t = new TriggerSettimanale(LocalTime.of(0, 0), giornoSettimana);
        }
        if (selectedTrigger.equals(sceltaGMese)) {
            t = new TriggerMensile(LocalTime.of(0, 0), giornoMese);
        }
        if (selectedTrigger.equals(sceltaData)) {
            t = new TriggerSuData(LocalTime.of(0, 0), dataCompleta);
        }
        if (selectedTrigger.equals(esisteFile)){
            t = new FileEsiste(cartella, nomeFileE);
        }
        if (selectedTrigger.equals(dimFile)){
            t = new DimensioneFile(nomeFile,dimensione, unita);
        }
        if (selectedTrigger.equals(contatoreInt)){
            t = new ContatoreIntero(contatore, intero,confronto);
        }
        if (selectedTrigger.equals(confrontaContatori)) {
            t = new ConfrontoContatori(contatore1, confronto2, contatore2);
        }




        //Azioni:
        if (selectedAzione.equals(sceltaMes)) {
            a = new FinestraDialogo(testoUtente);
        }
        if (selectedAzione.equals(sceltaAud)) {
            a = tracciaAudio;
        }
        if (selectedAzione.equals(sceltaPro)) {
            a = app;
        }
        if (selectedAzione.equals(sceltaStringa)){
            a = new AggiungiStringa(percorsoFile, stringa);
        }
        if (selectedAzione.equals(sceltaEliminaFile)) {
            a = path;
        }
        if (selectedAzione.equals(sceltaCopiaSposta)){
            a = new CopiaSposta(sorgente, destinazione, scelta);
        }
        if (selectedAzione.equals(sceltaCambiaValore)) {
            a = new CambiaValoreContatore(contatoreDaAggiornare, nuovoValore);
        }
        if (selectedAzione.equals(sceltaSommaValore)) {
            a = new SommaValoreContatore(contatorePiuIntero, valoreDaSommare);
        }
        if (selectedAzione.equals(sceltaSommaContatori)) {
            a = new SommaContatori(contatoreSomma1, contatoreSomma2);
        }

        Regola r = null;
        if (ripetibile.isSelected()) {

            r = new Regola(nome, t, a, true, false, true, periodoIbernazione, Instant.EPOCH);
        }
        else {

            r = new Regola(nome, t, a, true, false, false);
        }


        gestore.aggiungiRegola(r);
        gestore.saveObjectsToFile();

        if (helloController != null) {
            helloController.aggiornaTabella(Collections.singletonList(r));
        }

        Stage stage = (Stage) inviaButton.getScene().getWindow();
        stage.close();
    }


    @FXML
    private void ripetiAzione() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ifttt/Ibernazione.fxml"));
            Parent root = loader.load();
            IbernazioneController controller = loader.getController();
            controller.setSharedDataModel(sharedDataModel);

            Stage stage = new Stage();
            stage.setTitle("Seleziona il tempo di ibernazione...");
            stage.setScene(new Scene(root));

            stage.setOnHidden(event -> {
                periodoIbernazione = sharedDataModel.getPeriodoIbernazione();

            });

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}


