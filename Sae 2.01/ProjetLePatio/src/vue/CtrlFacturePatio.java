package vue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import controlleur.Main;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import modele.Data;
import modele.Reservation;

public class CtrlFacturePatio {
	@FXML
	private Label labFiltre1;

	@FXML
	private Label labFiltre2;

	@FXML
	private Label labFiltre3;

	@FXML
	private Label labFiltre4;

	@FXML
	private Label labFiltre5;
	@FXML
	private TableView<Reservation> clients; // c'est reservation plutot je pense
	@FXML
	private TableColumn<Reservation, Date> date;
	@FXML
	private TableColumn<Reservation, String> numClient;
	@FXML
	private TableColumn<Reservation, String> ville;
	@FXML
	private ImageView bnFiltres;
	@FXML
	private TableColumn<Reservation, Double> prix;
	@FXML
	private TableColumn<Reservation, String> modePaiement;
	@FXML
	private Button bnCreerFacture;
	@FXML
	private Button bnFermer;

	@FXML
	private TableColumn<Reservation, String> nom;
	@FXML
	private Button bnTrierDate;
	@FXML
	private TableColumn<Reservation, Void> facture;
	private final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	public void initialize() {
		labFiltre1.setVisible(false);
		labFiltre2.setVisible(false);
		labFiltre3.setVisible(false);
		labFiltre4.setVisible(false);
		labFiltre5.setVisible(false);

		clients.getColumns().forEach(column -> column.setSortable(false));
		numClient.setResizable(false);
		nom.setResizable(false);
		ville.setResizable(false);
		date.setResizable(false);
		prix.setResizable(false);
		facture.setResizable(false);
		modePaiement.setResizable(false);
		clients.setEditable(false);

		List<Reservation> filteredReservations = Data.reservations.stream()
				.filter(reservation -> reservation.getFacture() != null)
				.collect(Collectors.toList());
		clients.getItems().addAll(filteredReservations);

		numClient.setCellValueFactory(
				cellData -> new SimpleObjectProperty<String>(cellData.getValue().getClient().getNumero()));
		nom.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getClient().getNom()));
		ville.setCellValueFactory(
				cellData -> new SimpleObjectProperty<String>(cellData.getValue().getClient().getAdresse()));

		date.setCellValueFactory(cellData -> new SimpleObjectProperty<Date>(cellData.getValue().getDate()));
		date.setCellFactory(column -> new TableCell<Reservation, Date>() {
			@Override
			protected void updateItem(Date item, boolean empty) {
				super.updateItem(item, empty);
				if (item == null || empty) {
					setText(null);
				} else {
					setText(formatter.format(item));
				}
			}
		});
		prix.setCellValueFactory(
				cellData -> new SimpleObjectProperty<Double>(cellData.getValue().getFacture().getMontantTTC()));

		modePaiement.setCellValueFactory(
				cellData -> new SimpleObjectProperty<>(cellData.getValue().getFacture().getPaiement().getTypeSiRealise()));

		facture.setCellFactory(param -> new TableCell<Reservation, Void>() {
			private final Button btn = new Button();
			private final Image eyes = new Image("/eye.png");
			{
				ImageView eyesView = new ImageView(eyes);
				eyesView.setFitWidth(25);
				eyesView.setFitHeight(25);
				btn.setGraphic(eyesView);
				btn.setOnAction(event -> {
					Reservation reservation = getTableView().getItems().get(getIndex());
					Main.ouvrirAffichageEye(reservation);
				});
			}

			protected void updateItem(Void item, boolean empty) {
				super.updateItem(item, empty);
				if (empty) {
					setGraphic(null);
				} else {
					setGraphic(btn);
				}
			}
		});
	}

	public void updateTableau() {
		clients.getItems().clear();

		List<Reservation> updatedReservations = Data.reservations.stream()
				.filter(reservation -> reservation.getFacture() != null)
				.collect(Collectors.toList());

		clients.getItems().addAll(updatedReservations);
	}

	public void filtrerEtAfficher(String nom, String numero, String ville, LocalDate date, String paiement) {
		clients.getItems().clear();

		List<Reservation> filteredReservations = Data.reservations.stream()
				.filter(reservation -> reservation.getFacture() != null)
				.filter(reservation -> nom == null || nom.isEmpty() || reservation.getClient().getNom().contains(nom))
				.filter(
						reservation -> numero == null || numero.isEmpty() || reservation.getClient().getNumero().contains(numero))
				.filter(reservation -> ville == null || ville.isEmpty() || reservation.getClient().getAdresse().contains(ville))
				.filter(reservation -> date == null
						|| reservation.getDate().equals(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant())))
				.filter(reservation -> paiement == null || paiement.isEmpty()
						|| (paiement.equals("Cheque")
								? reservation.getFacture().getPaiement().getClass().getSimpleName().equals("Cheque")
								: paiement.equals("ChequeVacance")
										? reservation.getFacture().getPaiement().getClass().getSimpleName().equals("ChequeVacance")
										: reservation.getFacture().getPaiement().getTypeSiRealise().contains(paiement)))
				.collect(Collectors.toList());

		clients.getItems().addAll(filteredReservations);
		// Assuming afficherValeursFiltres can handle LocalDate directly. If not,
		// convert date to Date before passing.
		afficherValeursFiltres(nom, numero, ville, date, paiement);
	}

	@FXML
	void clicFermer(ActionEvent event) {
		System.exit(0);
	}

	@FXML
	void clicCreerFacture(ActionEvent event) {
		Main.ouvrirCreationFacture();
	}

	@FXML
	void clicTrierDate(ActionEvent event) {
		FXCollections.sort(clients.getItems(), Comparator.comparing(Reservation::getDate));

		if (date.getSortType() == TableColumn.SortType.ASCENDING) {
			FXCollections.reverse(clients.getItems());
		}

		date.setSortType(date.getSortType() == TableColumn.SortType.ASCENDING ? TableColumn.SortType.DESCENDING
				: TableColumn.SortType.ASCENDING);

		clients.setItems(clients.getItems());
	}

	@FXML
	void clicFiltres(ActionEvent event) {
		Main.ouvrirFiltres();
	}

	public void afficherValeursFiltres(String filtre1, String filtre2, String filtre3, LocalDate filtre4,
			String filtre5) {
		List<String> filtresActifs = new ArrayList<>();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		if (filtre1 != null && !filtre1.isEmpty()) {
			filtresActifs.add(filtre1);
		}
		if (filtre2 != null && !filtre2.isEmpty()) {
			filtresActifs.add(filtre2);
		}
		if (filtre3 != null && !filtre3.isEmpty()) {
			filtresActifs.add(filtre3);
		}
		if (filtre4 != null) {
			filtresActifs.add(dateFormat.format(Date.from(filtre4.atStartOfDay(ZoneId.systemDefault()).toInstant())));
		}
		if (filtre5 != null && !filtre5.isEmpty()) {
			filtresActifs.add(filtre5);
		}

		Label[] labFiltres = { labFiltre1, labFiltre2, labFiltre3, labFiltre4, labFiltre5 };
		for (int i = 0; i < labFiltres.length; i++) {
			if (i < filtresActifs.size()) {
				labFiltres[i].setText(filtresActifs.get(i));
				labFiltres[i].setVisible(true);
			} else {
				labFiltres[i].setVisible(false);
			}
		}
	}
}
