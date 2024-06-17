package vue;

import java.io.IOException;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


public class FenFiltres extends Stage {
	
	
	public FenFiltres() throws IOException {
		this.setTitle("Filtres");
		Scene laScene = new Scene(creerSceneGraph());
		this.setScene(laScene);
		
	}

	private Pane creerSceneGraph() throws IOException {
     	FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Filtres.fxml"));
        Pane root = loader.load();
     	return root;
	}
	


}
	
	