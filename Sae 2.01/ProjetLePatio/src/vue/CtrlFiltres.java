package vue;

import java.time.LocalDate;

import controlleur.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class CtrlFiltres {

    @FXML
    private Button bnAnnuler;

    @FXML
    private Button bnClear;

    @FXML
    private ImageView bnSupprimerF;

    @FXML
    private Button bnSupprimerF1;

    @FXML
    private Button bnSupprimerF2;

    @FXML
    private Button bnSupprimerF3;

    @FXML
    private Button bnSupprimerF4;

    @FXML
    private Button bnSupprimerF5;

    @FXML
    private Button bnValider;

    @FXML
    private Label labDate;

    @FXML
    private Label labNom;

    @FXML
    private Label labNumero;

    @FXML
    private Label labPaiement;

    @FXML
    private Label labVille;

    @FXML
    private ChoiceBox<String> liste_moy_paiement;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtNom;

    @FXML
    private TextField txtNumero;

    @FXML
    private TextField txtVille;

    public void initialize() {
        liste_moy_paiement.getItems().addAll("CB", "Cheque", "Liquide", "ChequeVacance");

    }

    @FXML
    void clicValider(ActionEvent event) {
        String nom = txtNom.getText();
        String numero = txtNumero.getText();
        String ville = txtVille.getText();
        LocalDate date = txtDate.getValue();
        String paiement = liste_moy_paiement.getValue();

        Main.filtrerReservations(nom, numero, ville, date, paiement);
    }

    @FXML
    void clicAnnuler(ActionEvent event) {
        Main.fermerFiltres();
    }

    @FXML
    void clicClear(ActionEvent event) {
        txtDate.setValue(null);
        txtNom.setText("");
        txtNumero.setText("");
        txtVille.setText("");

    }

    @FXML
    void clicSupprimerF1(ActionEvent event) {
        txtNumero.setText("");
    }

    @FXML
    void clicSupprimerF2(ActionEvent event) {
        txtNom.setText("");
    }

    @FXML
    void clicSupprimerF3(ActionEvent event) {
        txtVille.setText("");
    }

    @FXML
    void clicSupprimerF4(ActionEvent event) {
        liste_moy_paiement.setValue(null);

    }

    @FXML
    void clicSupprimerF5(ActionEvent event) {
        txtDate.setValue(null);
    }
}
