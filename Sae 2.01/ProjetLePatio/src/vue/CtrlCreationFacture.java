package vue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import controlleur.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import modele.Billet;
import modele.CB;
import modele.Cheque;
import modele.ChequeVacance;
import modele.Data;
import modele.Facture;
import modele.Fauteuil;
import modele.Liquide;
import modele.Paiement;
import modele.Reservation;
import modele.Spectacle;
import modele.Tarif;
import modele.Zone;

public class CtrlCreationFacture {

    @FXML
    private Button bnAnnuler;

    @FXML
    private Button bnValider;

    @FXML
    private Label labNumReserv;

    @FXML
    private ChoiceBox<String> list_moy_paiement;

    @FXML
    private ChoiceBox<String> liste_num_reservation;

    @FXML
    void bnAnnuler(ActionEvent event) {
        Main.fermerCreationFacture();
    }

    @FXML
    void clicValider(ActionEvent event) {
        for (Reservation r : Data.reservations) {
            if (r.getNumero().equals(liste_num_reservation.getValue())) {
                if (r.getFacture() == null) {
                    double montantRemise = calculerTotalTarif(r);
                    Facture f = new Facture(generateur(r), montantRemise, genererPaiement());
                    f.setReservation(r);
                    r.affecterFacture(f);
                    Main.ouvrirAffichage(r);
                    Main.fermerCreationFacture();
                    Main.miseAJour();
                } else {
                    System.out.println("La reservation a deja une facture.");
                }
                break;
            }
        }
    }

    public void initialize() {
        liste_num_reservation.getItems()
                .addAll(Data.reservations.filtered(reservation -> reservation.getNumero() != null)
                        .stream()
                        .map(Reservation::getNumero)
                        .collect(Collectors.toList()));
        list_moy_paiement.getItems().addAll("CB", "Cheque", "Liquide", "Cheque Vacances");
    }

    private Paiement genererPaiement() {
        Paiement paiement = null;
        switch (list_moy_paiement.getValue()) {
            case "CB":
                paiement = new CB(true);
                break;
            case "Cheque":
                paiement = new Cheque(false);
                break;
            case "Liquide":
                paiement = new Liquide(false);
                break;
            case "Cheque Vacances":
                paiement = new ChequeVacance(true);
                break;
            default:
                System.out.println("Moyen de paiement non reconnu.");
                break;
        }
        return paiement;
    }

    private static String generateur(Reservation r) {
        int counter = 1;
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.ENGLISH);
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.ENGLISH);

        Date date = r.getDate();

        String year = yearFormat.format(date);
        String month = monthFormat.format(date);

        String formattedCounter = String.format("%02d", counter);

        counter++;

        return year + month + formattedCounter;

    }

    public static double calculerTotalTarif(Reservation reservation) {
        double totalTarif = 0;

        List<Billet> billets = (List<Billet>) reservation.getBillet();

        if (billets != null) {
            for (Billet billet : billets) {
                double pleinTarif = 0;

                Fauteuil fauteuil = billet.getFauteuil();
                Zone zone = fauteuil.getZone();

                HashMap<Spectacle, Tarif> tarifs = zone.getAssociation();

                Spectacle spectacle = reservation.getRepresentation().getSpec();

                if (tarifs.containsKey(spectacle)) {
                    Tarif tarif = tarifs.get(spectacle);
                    pleinTarif += tarif.getPleinTarif();
                    System.out.println(
                            "Ajout du tarif pour le spectacle " + spectacle.getNom() + ": " + tarif.getPleinTarif());
                } else {
                    System.out.println("Pas de tarif trouvé pour le spectacle " + spectacle.getNom() + " dans la zone "
                            + zone.getNom());
                }

                totalTarif += pleinTarif;
                System.out.println("Tarif plein cumulé pour " + spectacle.getNom() + " dans la zone " + zone.getNom()
                        + ": " + pleinTarif);
            }
        } else {
            System.out.println("Aucun billet trouvé pour cette réservation.");
        }

        System.out.println("Tarif total pour la réservation: " + totalTarif);
        return totalTarif;
    }

    // private static String genererNumeroFacture(Reservation r) {
    // return "FAC" + (int) (Math.random() * 10000);
    // }
}