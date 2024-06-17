package controlleur;

import java.time.LocalDate;

import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modele.Data;
import modele.Reservation;
import vue.CtrlAffichage;
import vue.CtrlAffichageEye;
import vue.CtrlFacturePatio;
import vue.FenAffichage;
import vue.FenAffichageEye;
import vue.FenCreationFacture;
import vue.FenFacturePatio;
import vue.FenFiltres;

public class Main extends Application {

	static private FenFacturePatio fFac;
	static private FenCreationFacture fCrea;
	static private FenFiltres fFiltres;
	static private FenAffichage fAff;
	static private FenAffichageEye fAffEye;

	@Override
	public void start(Stage primaryStage) throws Exception {
		Data.chargementDonnees();
		fFac = new FenFacturePatio();
		fCrea = new FenCreationFacture();
		fFiltres = new FenFiltres();
		fAff = new FenAffichage();
		fAffEye = new FenAffichageEye();
		fCrea.setResizable(false);
		fFac.initModality(Modality.APPLICATION_MODAL);
		fCrea.initModality(Modality.APPLICATION_MODAL);
		fFiltres.initModality(Modality.APPLICATION_MODAL);
		fAff.initModality(Modality.APPLICATION_MODAL);
		fAffEye.initModality(Modality.APPLICATION_MODAL);
		fFac.show();

	}

	static public void miseAJour() {
		CtrlFacturePatio controller = fFac.getController();
		controller.updateTableau();
	}

	public static void filtrerReservations(String nom, String numero, String ville, LocalDate date, String paiement) {
		CtrlFacturePatio controller = fFac.getController();
		controller.filtrerEtAfficher(nom, numero, ville, date, paiement);
	}

	static public void ouvrirFiltres() {
		fFiltres.show();
	}

	static public void fermerFiltres() {
		fFiltres.close();
	}

	static public void ouvrirCreationFacture() {

		fCrea.show();
	}

	static public void fermerCreationFacture() {
		fCrea.close();
		;
	}

	static public void ouvrirAffichage(Reservation r) {
		CtrlAffichage controller = fAff.getController();
		controller.initialize(r);
		fAff.show();
	}

	static public void ouvrirAffichageSimple() {
		fAff.show();
	}

	static public void fermerAffichage() {

		fAff.close();
	}

	public static void ouvrirAffichageEye(Reservation r) {
		CtrlAffichageEye controller = fAffEye.getController();
		controller.initialize(r);
		fAffEye.show();
	}

	static public void fermerAffichageEye() {
		fAffEye.close();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
