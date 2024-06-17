package modele;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class Fauteuil {

	private String rangee;
	private String numero;
	private Collection<Billet> billet = new ArrayList<>();
	private Zone zone;
	
	public Fauteuil(String rangee, String numero, Zone zone) {
		super();
		this.rangee = rangee;
		this.numero = numero;
		this.zone = zone;
		zone.affecterFauteuil(this);
	}

	public String getRangee() {
		return rangee;
	}

	public String getNumero() {
		return numero;
	}

	public Collection<Billet> getBillet() {
		return billet;
	}
	
	public Zone getZone() {
		
		return zone;
		
	}
	
	

	public void setZone(Zone zone) {
		this.zone = zone;
	}

	@Override
	public int hashCode() {
		return Objects.hash(billet, numero, rangee);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fauteuil other = (Fauteuil) obj;
		return Objects.equals(billet, other.billet) && Objects.equals(numero, other.numero)
				&& Objects.equals(rangee, other.rangee) && Objects.equals(zone, other.zone);
	}

	@Override
	public String toString() {
		return "Fauteuil [rangee=" + rangee + ", numero=" + numero + ", zone=" + this.getZone().sansFauteuil() + billet + "]";
	}
	
	public String afficheSaufZone() {
		
		return "Fauteuil [rangee=" + rangee + ", numero=" + numero + "]";
		
	}
	
	public void sansBillet() {
		
		for (Billet b : (billet)) {
			b.sansFauteuil();
			
		}
		
	}
	public void afficheTotal() {
		
		System.out.println(toString());
		System.out.println("oui");
		
	}
	
	//////////////////////////////////////////////////////BILLET ////////////////////////////////////////////////////////////
	
	protected void ajouterBillet(Billet b) {
		
		this.getBillet().add(b);
		
	}
	
	protected void retirerBillet(Billet b) {
		
		this.getBillet().remove(b);
		
	}
	
	public void affecterBilletF(Billet b) {
		
			
			ajouterBillet(b);
		
	}
	
	public void retirerAffecterBilletF(Billet b) {
		
		if (this.getBillet().equals(b) == this.getBillet().contains(b)) {
			
			System.out.println("Deux fois le même billet présent, on retire");
			retirerBillet(b);
			
		}
		
	}
	
	
	//////////////////////////////////////////////////////ZONE ////////////////////////////////////////////////////////////
	
	public void modifierZone(Zone z) {
		
		this.setZone(z);
		
	}
	
}
