package modele;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class Reservation {
	private String numero;
	private Date date;
	private Date dateEnvoiConf;
	private Facture facture;
	private Collection<Billet> billet = new ArrayList<>();
	private Representation representation;
	private Client client;

	// il faut regler le probleme de la facture
	public Reservation(String numero, Date date, Date dateEnvoiConf, Billet billet,
			Representation representation, Client client, Facture f) {
		super();
		this.numero = numero;
		this.date = date;
		this.facture = f;
		this.dateEnvoiConf = dateEnvoiConf;
		facture.ajouterReservation(this);
		this.billet.add(billet);
		billet.ajouterReserv(this);
		this.representation = representation;
		representation.affecterReservation(this);
		this.client = client;
		client.ajouterReservation(this);
	}

	public Reservation(String numero, Date date, Date dateEnvoiConf, Billet billet,
			Representation representation, Client client) {
		super();
		this.numero = numero;
		this.date = date;
		this.dateEnvoiConf = dateEnvoiConf;
		this.billet.add(billet);
		billet.ajouterReserv(this);
		this.representation = representation;
		representation.affecterReservation(this);
		this.client = client;
		client.ajouterReservation(this);
	}

	public String getNumero() {
		return numero;
	}

	public Date getDate() {
		return date;
	}

	public Date getDateEnvoiConf() {
		return dateEnvoiConf;
	}

	public Facture getFacture() {
		return facture;
	}

	public Collection<Billet> getBillet() {
		return billet;
	}

	public Representation getRepresentation() {
		return representation;
	}

	public Client getClient() {
		return client;
	}

	public void setRepresentation(Representation representation) {
		this.representation = representation;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return "Reservation [numero=" + numero + ", date=" + date + ", dateEnvoiConf=" + dateEnvoiConf + ", facture="
				+ facture + ", billet=" + billet + ", representation=" + representation + ", client=" + client + "]";
	}

	public String afficheSaufFacture() {

		return "Reservation [numero=" + numero + ", date=" + date + ", dateEnvoiConf=" + dateEnvoiConf + ", billet="
				+ billet + ", representation=" + representation + ", client=" + client + "]";

	}

	public String sansClient() {

		return "Reservation [numero=" + numero + ", date=" + date + ", dateEnvoiConf=" + dateEnvoiConf + ", facture="
				+ facture + ", billet=" + billet + ", representation=" + representation + "]";

	}

	public void afficheBillet() {

		for (Billet b : billet) {
			b.sansReserv().toString();

		}

	}

	public void afficheTotal() {
		System.out.println(toString());
		toString();
		afficheBillet();
	}

	////////////////////////////////////////////////////// REPRESENTATION
	////////////////////////////////////////////////////// ////////////////////////////////////////////////////////////

	public void modifierRepresentation(Representation r) {

		this.representation = r;

	}

	////////////////////////////////////////////////////// BILLET
	////////////////////////////////////////////////////// ////////////////////////////////////////////////////////////

	protected void ajouterBillet(Billet b) {

		this.getBillet().add(b);

	}

	protected void retirerBillet(Billet b) {

		this.getBillet().remove(b);

	}

	public void affecterBillet(Billet b) {

		ajouterBillet(b);

	}

	public void retirerAffecterBillet(Billet b) {

		retirerBillet(b);

	}

	////////////////////////////////////////////////////// FACTURE
	////////////////////////////////////////////////////// ////////////////////////////////////////////////////////////

	protected void ajouterFacture(Facture f) {

		this.facture = f;

	}

	protected void retirerFacture(Facture f) {

		this.facture = null;

	}

	public void affecterFacture(Facture f) {

		ajouterFacture(f);

	}

	public void retirerAffecterFacture(Facture f) {
		if (this.facture != null) {
			retirerFacture(f);
		}

	}

	////////////////////////////////////////////////////// CLIENT
	////////////////////////////////////////////////////// ////////////////////////////////////////////////////////////

	public void modifierClient(Client c) {

		if (this.getClient().equals(client)) {
			System.out.println("ça aussi.");
			this.setClient(c);
		} else {
			System.out.println("caca");
		}

	}

	public Object correspondAuxCriteres(String nom, String numero2, String ville, Date dateFiltre) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'correspondAuxCriteres'");
	}

}
