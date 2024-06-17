package modele;

import java.util.Objects;

public class Billet {

	private String numero;
	private Reservation reserv;
	private Fauteuil fauteuil;
	private Tarification tarification;
	
	
	public Billet(String numero, Fauteuil fauteuil, Tarification tarification) {
		super();
		this.numero = numero;
		this.fauteuil= fauteuil;
		fauteuil.affecterBilletF(this);
		this.tarification = tarification;
		tarification.affecterBillet(this);
		
	}


	protected void setFauteuil(Fauteuil fauteuil) {
		this.fauteuil = fauteuil;
	}


	public String getNumero() {
		return numero;
	}
	
	public Fauteuil getFauteuil() {
		return fauteuil;
	}

	public Reservation getReserv() {
		return reserv;
	}
	
	public Tarification getTarification() {
		return tarification;
	}


	protected void setTarification(Tarification tarification) {
		this.tarification = tarification;
	}


	@Override
	public int hashCode() {
		return Objects.hash(numero, reserv);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Billet other = (Billet) obj;
		return Objects.equals(numero, other.numero) && Objects.equals(reserv, other.reserv);
	}


	@Override
	public String toString() {
		return "Billet [numero=" + numero + ", tarification="
				+ tarification + "]";
	}
	
	public String sansFauteuil() {
		
		return "Billet [numero=" + numero + ", reserv=" + reserv + ", tarification="
				+ tarification + "]";
		
	}
	
	public String sansReserv() {
		
		return "Billet [numero=" + numero + ", fauteuil=" + fauteuil + ", tarification="
				+ tarification + "]";
		
	}
	
	public String sansTarification() {
		
		return "Billet [numero=" + numero + ", reserv=" + reserv + ", fauteuil=" + fauteuil + "]";
		
	}
	
	////////////////////////////////////////////////////// RESERVATION ////////////////////////////////////////////////////////////
	
	protected void ajouterReserv(Reservation r) {
		
		this.reserv = r;
		
	}
	
	protected void retirerReserv(Reservation r) {
		
		this.reserv = null;
		
	}
	
	public void affecterReserv(Reservation r) {
		
		if (this.getReserv().equals(null)) {
			System.out.println("Nul");
		}else {
			
			ajouterReserv(r);
			System.out.println("Bravo.");
			
		}
		
	}
	
	public void retirerAffectReserv(Reservation r) {
		
		if (this.getReserv() == this.getReserv()) {
			
			System.out.println("Réservation en double, on retire");
			retirerReserv(r);
			
		}
		
	}
	
	//////////////////////////////////////////////////////FAUTEUIL ////////////////////////////////////////////////////////////
	
	public void modifierFauteuil(Fauteuil f) {
		
		this.setFauteuil(f);
		
	}



	
	//////////////////////////////////////////////////////TARIFICATION ////////////////////////////////////////////////////////////

	public void modifierTarification(Tarification t) {
		
		this.setTarification(t);
		
	}
	
}
