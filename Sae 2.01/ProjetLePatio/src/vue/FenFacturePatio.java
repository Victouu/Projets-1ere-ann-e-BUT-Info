package vue;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FenFacturePatio extends Stage {
	private CtrlFacturePatio controller;

	public FenFacturePatio() throws IOException {
		this.setTitle("Liste des factures");
		Scene laScene = new Scene(creerSceneGraph());

		this.setScene(laScene);

	}

	private Pane creerSceneGraph() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/FacturePatio.fxml"));
		Pane root = loader.load();
		controller = loader.getController();
		return root;
	}

	public CtrlFacturePatio getController() {
		return controller;
	}
}