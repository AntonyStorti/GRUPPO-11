package com.example.controllerGUI;

import com.example.azioni.*;
import com.example.ifttt.*;
import com.example.trigger.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class HelloController {

    @FXML
    public Button compostaButton;
    @FXML
    private Button creaRegolaButton;
    @FXML
    private Button attivaButton;
    @FXML
    private Button eliminaRegolaButton;
    @FXML
    private TableView<Regola> tabellaRegole;
    @FXML
    private TableColumn<Regola, Integer> idRegola;
    @FXML
    private TableColumn<Regola, String> nomeRegola;
    @FXML
    private TableColumn<Regola, Trigger> triggerRegola;
    @FXML
    private TableColumn<Regola, Azione> azioneRegola;
    @FXML
    private TableColumn<Regola, Boolean> ripetibilita;
    @FXML
    private TableColumn<Regola, Boolean> stato;
    @FXML
    private TableView<Contatore> tabellaContatori;
    @FXML
    private TableColumn<Contatore, String> colonnaNome;
    @FXML
    private TableColumn<Contatore, Integer> colonnaValore;
    @FXML
    private TextField nomeContatore;
    @FXML
    private TextField valoreContatore;
    @FXML
    private Button creaButton;
    @FXML
    public Button eliminaButton;
    @FXML
    public Button modificaButton;


    private GestoreRegole gestoreRegole;

    private SharedMemory sharedDataModel = new SharedMemory();

    @FXML
    private void initialize() {
        idRegola.setCellValueFactory(new PropertyValueFactory<>("ID"));
        nomeRegola.setCellValueFactory(new PropertyValueFactory<>("nome"));
        triggerRegola.setCellValueFactory(new PropertyValueFactory<>("trigger"));
        azioneRegola.setCellValueFactory(new PropertyValueFactory<>("azione"));
        ripetibilita.setCellValueFactory(new PropertyValueFactory<>("ripetibile"));
        stato.setCellValueFactory(new PropertyValueFactory<>("stato"));

        gestoreRegole = new GestoreRegole(tabellaRegole.getItems());
        caricaDatiDaFile();

        creaRegolaButton.setOnAction(event -> apriFinestraCreazioneRegola());
        attivaButton.setOnAction(event -> attivaDisattiva());
        eliminaRegolaButton.setOnAction(event -> eliminaRegola());
        compostaButton.setOnAction(event -> creaRegolaComposta());

        // Crea un timeline per l'aggiornamento periodico
        Timeline refreshTabella = new Timeline(new KeyFrame(Duration.millis(250), event -> tabellaRegole.refresh()));
        refreshTabella.setCycleCount(Timeline.INDEFINITE);
        refreshTabella.play();

        valoreContatore.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                valoreContatore.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        colonnaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colonnaValore.setCellValueFactory(new PropertyValueFactory<>("valore"));



        tabellaContatori.setItems(GestoreContatori.listaContatori);
        initTabellaContatori();

        creaButton.setOnAction(event -> creaContatore());
        eliminaButton.setOnAction(event -> eliminaContatore());
        modificaButton.setOnAction(event -> modificaContatore());

        caricaContatoriDaFile();

        Timeline refreshTabellaC = new Timeline(new KeyFrame(Duration.millis(250), event -> tabellaContatori.refresh()));
        refreshTabellaC.setCycleCount(Timeline.INDEFINITE);
        refreshTabellaC.play();

    }

    @FXML
    private void apriFinestraCreazioneRegola() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ifttt/OperaSullaRegola.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Crea la tua Regola...");
            stage.setScene(new Scene(root));

            CreaRegolaController controller = loader.getController();
            controller.setHelloController(this);

            stage.show();
        } catch (Exception e) {
            e.printStackTrace(); // Gestisci l'eccezione in modo adeguato
        }
    }


    public void eliminaRegola() {
        Regola r = tabellaRegole.getSelectionModel().getSelectedItem();

        if (r != null) {
            // Rimuovi dalla tabella
            tabellaRegole.getItems().remove(r);

            // Rimuovi dalla lista di GestoreRegole
            GestoreRegole.listaRegole.remove(r);

            for (int i = tabellaRegole.getSelectionModel().getSelectedIndex() + 1; i < tabellaRegole.getItems().size(); i++) {

                Regola regolaSuccessiva = tabellaRegole.getItems().get(i);
                regolaSuccessiva.setID(regolaSuccessiva.getID() - 1);
                // Aggiorna la lista
                GestoreRegole.listaRegole.set(i, regolaSuccessiva);

            }
            System.out.println(GestoreRegole.listaRegole);

            // Rimuovi dal file JSON
            rimuoviRegolaDaFile(r);
        }
    }


    private void rimuoviRegolaDaFile(Regola regolaDaRimuovere) {
        Path filePath = Paths.get("ListaRegole.json");

        try {
            // Leggi il contenuto del file JSON specificando la codifica UTF-8
            String fileContent = new String(Files.readAllBytes(filePath), StandardCharsets.UTF_8);

            // Crea un oggetto JSONObject dal contenuto del file
            JSONObject jsonContainer = new JSONObject(fileContent);

            // Estrai l'array di regole dal JSONObject
            JSONArray jsonArray = jsonContainer.getJSONArray("regole");

            // Trova e rimuovi la regola dal JSONArray
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonRegola = jsonArray.getJSONObject(i);
                Regola regola = deserializeRegola(jsonRegola);

                if (regola.equals(regolaDaRimuovere)) {
                    System.out.println("Regola trovata per la rimozione. ID: " + regola.getID());
                    jsonArray.remove(i);

                    // Decrementa gli ID delle regole successive
                    for (int j = i; j < jsonArray.length(); j++) {
                        JSONObject jsonRegolaSuccessiva = jsonArray.getJSONObject(j);
                        Regola regolaSuccessiva = deserializeRegola(jsonRegolaSuccessiva);

                        // Aggiorna l'ID decrementandolo di 1
                        int nuovoID = regolaSuccessiva.getID() - 1;
                        regolaSuccessiva.setID(nuovoID);

                        // Aggiorna il JSON object nell'array
                        jsonArray.put(j, regolaSuccessiva.toJSONObject());
                    }

                    System.out.println("JSONArray dopo la rimozione: " + jsonArray.toString(2));

                    // Esci dal loop dopo aver rimosso e aggiornato gli ID
                    break;
                }
            }

            // Sovrascrivi il file JSON con il JSONArray aggiornato
            Files.write(filePath, jsonContainer.toString(2).getBytes(StandardCharsets.UTF_8));

            // Verifica che la lista di regole sia aggiornata correttamente
            System.out.println("ListaRegole dopo la rimozione: " + GestoreRegole.listaRegole.toString());

            System.out.println("Regola rimossa con successo dal file JSON.");

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Errore durante la rimozione della regola dal file JSON.");
        }
    }


    public void aggiornaTabella(List<Regola> nuoveRegole) {
        ObservableList<Regola> observableList = FXCollections.observableArrayList(nuoveRegole);
        tabellaRegole.getItems().addAll(observableList);
    }


    public void attivaDisattiva() {
        Regola r;
        if (tabellaRegole.getSelectionModel().selectedItemProperty().isNotNull().get()) {
            r = tabellaRegole.getSelectionModel().getSelectedItem();
            if (r.getStato() == "Attiva") {
                r.setStato(false);
            }
            else {
                r.setStato(true);
                r.setEseguito(false);
            }

            modificaStatoRegolaSuFile(r);
        }

    }

    public void modificaStatoRegolaSuFile(Regola regolaDaModificare) {
        Path filePath = Paths.get("ListaRegole.json");

        try {
            // Leggi il contenuto del file JSON specificando la codifica UTF-8
            String fileContent = new String(Files.readAllBytes(filePath), StandardCharsets.UTF_8);

            // Crea un oggetto JSONObject dal contenuto del file
            JSONObject jsonContainer = new JSONObject(fileContent);

            // Estrai l'array di regole dal JSONObject
            JSONArray jsonArray = jsonContainer.getJSONArray("regole");

            // Trova e rimuovi la regola dal JSONArray
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonRegola = jsonArray.getJSONObject(i);
                Regola regola = deserializeRegola(jsonRegola);

                if (regola.equals(regolaDaModificare)) {
                    if (regola.getStato() == "Attiva") {
                        regola.setStato(false);
                    }
                    else {
                        regola.setStato(true);
                        regola.setEseguito(false);
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



    private void caricaDatiDaFile() {
        tabellaRegole.getItems().clear();

        Path filePath = Paths.get("ListaRegole.json");

        try {
            // Leggi il contenuto del file JSON
            String fileContent = new String(Files.readAllBytes(filePath));

            // Crea un oggetto JSONObject dal contenuto del file
            JSONObject jsonContainer = new JSONObject(fileContent);

            // Estrai l'array di regole dal JSONObject
            JSONArray jsonArray = jsonContainer.getJSONArray("regole");

            // Crea una lista di regole
            List<Regola> regole = new ArrayList<>();

            // Itera sull'array e crea oggetti Regola
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonRegola = jsonArray.getJSONObject(i);
                Regola regola = deserializeRegola(jsonRegola);
                // Aggiungi la regola alla lista
                regole.add(regola);
            }
            GestoreRegole.listaRegole.addAll(regole);

            // Associa le colonne ai campi degli oggetti Regola
            idRegola.setCellValueFactory(new PropertyValueFactory<>("ID"));
            nomeRegola.setCellValueFactory(new PropertyValueFactory<>("nome"));

            // Personalizza le celle per Trigger e Azione
            triggerRegola.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getTrigger()));
            azioneRegola.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getAzione()));

            ripetibilita.setCellValueFactory(new PropertyValueFactory<>("ripetibile"));
            stato.setCellValueFactory(new PropertyValueFactory<>("stato"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Regola deserializeRegola(JSONObject jsonRegola) {
        int id = jsonRegola.getInt("ID");
        String nome = jsonRegola.getString("nome");
        boolean stato = jsonRegola.getBoolean("stato");
        boolean eseguito = jsonRegola.getBoolean("eseguito");

        // Deserializza il Trigger
        JSONObject jsonTrigger = jsonRegola.optJSONObject("trigger");
        Trigger trigger = deserializeTrigger(jsonTrigger);

        // Deserializza l'Azione
        JSONObject jsonAzione = jsonRegola.optJSONObject("azione");
        Azione azione = deserializeAzione(jsonAzione);

        boolean ripetibile = jsonRegola.getBoolean("ripetibile");
        Regola regola = null;
        if (ripetibile) {
            String p = jsonRegola.getString("ibernazione");
            java.time.Duration periodoIbernazione = java.time.Duration.parse(p);

            String w = jsonRegola.getString("ultimaEsecuzione");
            Instant ultimaEsecuzione;
            if(w!= null)
                ultimaEsecuzione = Instant.parse(w);
            else
                ultimaEsecuzione = Instant.EPOCH;
            regola = new Regola(id, nome, trigger, azione, stato, eseguito, ripetibile, periodoIbernazione, ultimaEsecuzione);
        }
        else
            // Chiamata al nuovo costruttore con ID
            regola = new Regola(id, nome, trigger, azione, stato, eseguito);

        regola.setStato(stato);
        regola.setEseguito(eseguito);

        return regola;
    }

    private Trigger deserializeTrigger(JSONObject jsonTrigger) {

        String tipoTrigger = jsonTrigger.getString("tipo");

        if ("TempoDelGiorno".equals(tipoTrigger)) {
            // Deserializza il TempoDelGiorno
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
            // Deserializza la FinestraDialogo
            return FinestraDialogo.deserialize(jsonAzione);
        }
        if ("RiproduciAudio".equals(tipoAzione)) {
            // Deserializza il RiproduciAudio
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
        // Se nessuna corrispondenza, potresti restituire null o generare un'eccezione a seconda della tua logica.
        return null;
    }

    @FXML
    private void creaContatore() {
        String nome = nomeContatore.getText();
        Integer valore = Integer.parseInt(valoreContatore.getText());

        // Crea un nuovo contatore
        Contatore nuovoContatore = new Contatore(nome, valore);

        // Aggiungi il nuovo contatore alla lista
        GestoreContatori.listaContatori.add(nuovoContatore);

        // Salva tutti i contatori nel file
        salvaContatoriSuFile();

        // Pulisci i campi di testo
        nomeContatore.clear();
        valoreContatore.clear();

    }

    private void caricaContatoriDaFile() {
        Path path = Paths.get("contatori.dat");

        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path))) {
            // Leggi la lista di contatori direttamente dal file
            List<Contatore> contatori = (List<Contatore>) ois.readObject();
            GestoreContatori.listaContatori.addAll(contatori);
            System.out.println("Contatori caricati con successo!");
            System.out.println(GestoreContatori.listaContatori);
        } catch (IOException | ClassNotFoundException e) {
            // Gestire le eccezioni, ad esempio, se il file non esiste
            System.err.println("Errore durante il caricamento dei contatori: " + e.getMessage());
            e.printStackTrace();
        }
    }



    private void initTabellaContatori() {
        // Inizializza la tabella e le colonne
        colonnaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colonnaValore.setCellValueFactory(new PropertyValueFactory<>("valore"));

        // Aggiungi i contatori alla tabella
        tabellaContatori.setItems(GestoreContatori.listaContatori);

        // Aggiungi un listener per gestire le modifiche ai contatori
        tabellaContatori.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Puoi gestire la selezione del contatore qui
                System.out.println("Contatore selezionato: " + newSelection);
            }
        });
    }


    public void salvaContatoriSuFile() {
        Path path = Paths.get("contatori.dat");

        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path))) {
            // Salva la lista di contatori nel file
            List<Contatore> contatori = new ArrayList<>(GestoreContatori.listaContatori);
            oos.writeObject(contatori);
        } catch (IOException e) {
            System.err.println("Errore durante la scrittura sul file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void eliminaContatore() {
        Contatore c = tabellaContatori.getSelectionModel().getSelectedItem();

        if (c != null) {
            // Rimuovi dalla tabella
            tabellaContatori.getItems().remove(c);

            // Rimuovi dalla lista di GestoreRegole
            GestoreContatori.listaContatori.remove(c);

            // Rimuovi dal file JSON
            salvaContatoriSuFile();
        }
    }

    public void modificaContatore() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ifttt/ModificaContatore.fxml"));
            Parent root = loader.load();
            ModificaContatoreController controller = loader.getController();
            controller.setSharedDataModel(sharedDataModel);
            Stage stage = new Stage();
            stage.setTitle("Scegli il nuovo valore del contatore...");
            stage.setScene(new Scene(root));
            sharedDataModel.setTabellaContatori(tabellaContatori);
            /*stage.setOnHidden(event -> {
                nuovoValoreC = sharedDataModel.getNuovoValoreContatore();

            });*/

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void creaRegolaComposta() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ifttt/RegoleRipetute.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Crea una regola composta...");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
