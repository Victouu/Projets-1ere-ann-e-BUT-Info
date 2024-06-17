package vue;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FenAffichage extends Stage {
	private CtrlAffichage controller;

	public FenAffichage() throws IOException {
		this.setTitle("Affichage facture");
		Scene laScene = new Scene(creerSceneGraph());

		this.setScene(laScene);

	}

	private Pane creerSceneGraph() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/affichage_facture_org.fxml"));
		Pane root = loader.load();
		controller = loader.getController();
		return root;
	}

	public CtrlAffichage getController() {
		return controller;
	}
}