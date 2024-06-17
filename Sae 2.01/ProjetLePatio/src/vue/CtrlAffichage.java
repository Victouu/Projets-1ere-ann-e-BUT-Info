package vue;

import java.text.SimpleDateFormat;
import java.util.Locale;

import controlleur.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import modele.Reservation;

public class CtrlAffichage {

    @FXML
    private Button bnAnnuler;

    @FXML
    private Button bnValider;

    @FXML
    private Label label_adresse_client;

    @FXML
    private Label label_code_postal_client;

    @FXML
    private Label label_date;

    @FXML
    private Label label_date_facture;

    @FXML
    private Label label_date_reglement;

    @FXML
    private Label label_heure;

    @FXML
    private Label label_mode_paiement;

    @FXML
    private Label label_nb_places;

    @FXML
    private Label label_nom_complet_client;

    @FXML
    private Label label_num_facture;

    @FXML
    private Label label_pestacle;

    @FXML
    private Label label_prix_tot_ttc;

    @FXML
    private Label label_prix_uni_redu;

    @FXML
    private Label label_prix_uni_ttc;

    @FXML
    private Label label_promo_qinz;

    @FXML
    private Label label_remise;

    @FXML
    private Label label_result_ht;

    @FXML
    private Label label_result_ttc;

    @FXML
    private Label label_result_tva;

    public void initialize(Reservation r) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

        label_adresse_client.setText(r.getClient().getAdresse());
        label_nom_complet_client.setText(r.getClient().getNom());
        label_code_postal_client.setText(r.getClient().getAdresse());
        label_date.setText(formatter.format(r.getDate()));
        label_date_facture.setText(formatter.format(r.getFacture().getReservation().getDate()));
        label_date_reglement.setText(formatter.format(r.getDateEnvoiConf()));
        label_heure.setText(r.getRepresentation().getHeure().toString());
        if (!r.getFacture().equals(null)) {
            label_mode_paiement.setText(r.getFacture().getPaiement().getTypeSiRealise());
        }
        label_nb_places.setText(String.valueOf(r.getBillet().size()));
        label_num_facture.setText(r.getFacture().getNumero());
        label_pestacle.setText(r.getRepresentation().getSpec().getNom());
        label_prix_tot_ttc.setText(String.valueOf(r.getFacture().calculTTC(r)));
        label_promo_qinz.setText(String.valueOf(r.getFacture().calculRemise(r)));
        label_remise.setText(String.valueOf(r.getFacture().affichagePourcentage(r)));
        label_prix_uni_redu.setText(String.valueOf(r.getFacture().prixUnitaireReduit(r)));
        label_prix_uni_ttc.setText(String.valueOf(r.getFacture().getMontantTTC()));
        label_result_ht.setText(String.valueOf(r.getFacture().calculHT(r)));
        label_result_tva.setText(String.valueOf(r.getFacture().calculTVA(r)));
        label_result_ttc.setText(String.valueOf(r.getFacture().calculTTC(r)));

    }

    @FXML
    void clicAnnuler(ActionEvent event) {
        Main.fermerAffichage();
    }

    @FXML
    void clicValider(ActionEvent event) {
        Main.fermerAffichage();
    }

}
