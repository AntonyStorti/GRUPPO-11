package com.example.ifttt;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.FileChooser;
import java.io.*;




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
    private void initialize() {
        sceltaTrigger.setOnAction(event -> apriFinestraSceltaTrigger());
        sceltaAzione.setOnAction(event -> apriFinestraSceltaAzioni());
        inviaButton.setOnAction(event -> creaRegola());
    }



    public void apriFinestraSceltaTrigger() {

        String selectedItem = sceltaTrigger.getSelectionModel().getSelectedItem();

        String sceltaOra = "Seleziona un orario";

        if (selectedItem.equals(sceltaOra)){

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("SceltaOra.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setTitle("Seleziona un orario...");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace(); // Gestisci l'eccezione in modo adeguato
            }

        }




    }




    @FXML
    private void apriFinestraSceltaAzioni() {

        String selectedItem = sceltaAzione.getSelectionModel().getSelectedItem();

        String sceltaMes = "Visualizza messaggio";
        String sceltaAud = "Riproduci un audio";
        
        
        if (selectedItem.equals(sceltaMes)) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FinestraDialogo.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setTitle("Scrivi il tuo messaggio...");
                stage.setScene(new Scene(root));
                stage.show();



            } catch (Exception e) {
                e.printStackTrace(); // Gestisci l'eccezione in modo adeguato
            }

            
        } else if (selectedItem.equals(sceltaAud)) {

            try {
                // Creare un oggetto FileChooser
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Seleziona un file audio (.mp3, .waw, .mp4...");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("File audio", "*.mp3", "*.wav", "*.mp4")
                );

                Stage stage = (Stage) sceltaAzione.getScene().getWindow();
                File fileSelezionato = fileChooser.showOpenDialog(stage);


                // Verifica se è stato selezionato un file
                if (fileSelezionato != null) {
                    // Salva il percorso del file selezionato
                    String percorsoFileAudio = fileSelezionato.getAbsolutePath();
                    RiproduciAudio stereo = new RiproduciAudio(percorsoFileAudio);
                }

            } catch (Exception e) {
                e.printStackTrace(); // Gestisci l'eccezione in modo adeguato
            }

        }
    }


    @FXML
    private void creaRegola(){

        String nome = sceltaNome.getText();
        String selectedTrigger = sceltaTrigger.getValue();
        String selectedAzione = sceltaAzione.getValue();

        //Tipi di Trigger;
        String sceltaOra = "Seleziona un orario";

        //Tipi di Azione:
        String sceltaMes = "Visualizza messaggio";
        String sceltaAud = "Riproduci un audio";



        if(selectedTrigger.equals(sceltaOra) && selectedAzione.equals(sceltaMes)) {

            //Recupero l'Azione:
            FinestraDialogo messaggioAllert = null;
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("messaggioAllert.ser"))) {
                messaggioAllert = (FinestraDialogo) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            TempoDelGiorno trigger1 = null;
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("trigger1.ser"))) {
                trigger1 = (TempoDelGiorno) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }


            //CREA REGOLA
            Regola oraMessaggio = new Regola(nome, trigger1, messaggioAllert, true);


            File fileMessaggioAllert = new File("messaggioAllert.ser");
            File fileTrigger1 = new File("trigger1.ser");

            fileMessaggioAllert.delete();
            fileTrigger1.delete();


            Stage stage = (Stage) inviaButton.getScene().getWindow();
            stage.close();

        }

    }

}


