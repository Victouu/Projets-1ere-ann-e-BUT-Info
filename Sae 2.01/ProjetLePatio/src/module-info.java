/**
 * 
 */
/**
 * @author vroue
 *
 */
module ProjetLePatio {
	requires javafx.graphics;
	requires javafx.controls;
	requires javafx.fxml;
	requires java.desktop;
	requires javafx.base;
	
	opens vue to javafx.graphics , javafx.controls, javafx.fxml ,javafx.base;
	opens controlleur to javafx.graphics , javafx.controls, javafx.fxml ,javafx.base;
	opens modele to javafx.graphics , javafx.controls, javafx.fxml ,javafx.base;
}