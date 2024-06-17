package modele;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public  class Client {

	private String nom;
	private String prenom;
	private String adresse;
	private String tel;
	private String mail;
	private String numero;
	private Collection<Reservation> reservation = new ArrayList<>();
	
	public Client(String nom, String prenom, String adresse, String tel, String mail, String numero) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.tel = tel;
		this.mail = mail;
		this.numero = numero;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public String getTel() {
		return tel;
	}

	public String getMail() {
		return mail;
	}

	public String getNumero() {
		return numero;
	}

	public Collection<Reservation> getReservation() {
		return reservation;
	}

	@Override
	public int hashCode() {
		return Objects.hash(adresse, mail, nom, numero, prenom, reservation, tel);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(adresse, other.adresse) && Objects.equals(mail, other.mail)
				&& Objects.equals(nom, other.nom) && Objects.equals(numero, other.numero)
				&& Objects.equals(prenom, other.prenom) && Objects.equals(reservation, other.reservation)
				&& Objects.equals(tel, other.tel);
	}

	@Override
	public String toString() {
		return "Client [nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", tel=" + tel + ", mail=" + mail
				+ ", numero=" + numero + "]";
	}
	
	public void afficheSansReserv() {
		
		for (Reservation r : (reservation)) {
			r.sansClient();
			
		}
		
	}
	
	public void afficheTotal() {
		
		toString();
		afficheSansReserv();
		
	}
	
	//////////////////////////////////////////////////////RESERVATION ////////////////////////////////////////////////////////////
	
	protected void ajouterReservation(Reservation r) {
		
		this.getReservation().add(r);
		
	}
	
	protected void retirerReservation(Reservation r) {
		
		this.getReservation().remove(r);
		
	}
	
	public void affecterReservation(Reservation r) {
		
		if (this.getReservation().equals(null)) {
			System.out.println("Nul");
		}else {
			
			ajouterReservation(r);
			System.out.println("Bravo.");
			
		}
		
	}
	
	public void retirerAffecterReservation(Reservation r) {
		
		retirerReservation(r);
		System.out.println("ouii");
		
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
}
