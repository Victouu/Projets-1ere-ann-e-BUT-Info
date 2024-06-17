package modele;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public abstract class Tarification {
	
	private Collection<Billet> billet = new ArrayList<>();
	private Jeune jeune;
	private Groupe groupe;
	private Senior senior;
	private AbonneT aboT;
	
	public Groupe getGroupe() {
		return groupe;
	}

	public Senior getSenior() {
		return senior;
	}

	public AbonneT getAboT() {
		return aboT;
	}

	public Collection<Billet> getBillet() {
		return billet;
	}
	
	public Jeune getJeune() {
		
		return jeune;
		
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(billet);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarification other = (Tarification) obj;
		return Objects.equals(billet, other.billet);
	}


	
	//////////////////////////////////////////////////////BILLET ////////////////////////////////////////////////////////////
	
	
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
	
}