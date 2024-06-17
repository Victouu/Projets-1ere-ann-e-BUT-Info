package modele;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class Representation {

	private String jour;
	private String heure;
	private Boolean annulee;
	private Collection<Reservation> reserv = new ArrayList<>();
	private Spectacle spec;
	
	
	public Representation(String jour, String heure, Boolean annulee, Spectacle spec) {
		super();
		this.jour = jour;
		this.heure = heure;
		this.annulee = annulee;
		this.spec = spec;
	}


	public String getJour() {
		return jour;
	}


	public String getHeure() {
		return heure;
	}


	public Boolean getAnnulee() {
		return annulee;
	}


	public Collection<Reservation> getReserv() {
		return reserv;
	}


	public Spectacle getSpec() {
		return spec;
	}


	public void setSpec(Spectacle spec) {
		this.spec = spec;
	}


	@Override
	public int hashCode() {
		return Objects.hash(annulee, heure, jour, reserv, spec);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Representation other = (Representation) obj;
		return Objects.equals(annulee, other.annulee) && Objects.equals(heure, other.heure)
				&& Objects.equals(jour, other.jour) && Objects.equals(reserv, other.reserv)
				&& Objects.equals(spec, other.spec);
	}


	@Override
	public String toString() {
		return "Representation [jour=" + jour + ", heure=" + heure + ", annulee=" + annulee	+ ", spec=" + spec + "]";
	}
	
	public void afficheSansR() {
		
		for (Reservation r : (reserv)) {
			r.afficheBillet();
			
		}
		
	}
	
	public String afficheSaufSpect() {
		
		return "Representation [jour=" + jour + ", heure=" + heure + ", annulee=" + annulee	+ "]";
		
	}
	
	public void afficheTotal() {
		
		afficheSansR();
		afficheSaufSpect();
		
	}
	
	//////////////////////////////////////////////////////RESERVATION ////////////////////////////////////////////////////////////
	
	protected void ajouterReservation(Reservation r) {
		
		this.getReserv().add(r);
		
	}
	
	protected void retirerReservation(Reservation r) {
		
		this.getReserv().remove(r);
		
	}
	
	public void affecterReservation(Reservation r) {
		
		if (this.getReserv() == null) {
		
			ajouterReservation(r);
			
		}
		
	}
	
	public void retirerAffecterReservation(Reservation r) {
		
		retirerReservation(r);
		
	}
	
	//////////////////////////////////////////////////////SPECTACLE ////////////////////////////////////////////////////////////
	
	public void modifierSpectacle(Spectacle s) {
		
		this.setSpec(s);
		
	}
	
}
