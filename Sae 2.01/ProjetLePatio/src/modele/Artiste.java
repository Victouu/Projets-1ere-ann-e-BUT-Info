package modele;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class Artiste {
	
	private String nom;
	private Collection<Spectacle> spect = new ArrayList<Spectacle>();
	
	public Artiste(String nom) {
		super();
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public Collection<Spectacle> getSpect() {
		return spect;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nom);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artiste other = (Artiste) obj;
		return Objects.equals(nom, other.nom) && Objects.equals(spect, other.spect);
	}

	@Override
	public String toString() {
		return "Artiste [nom=" + nom + "]";
	}
	
	public void test() {
		
		for (Spectacle spectacle : (spect)){
			spectacle.afficheSansArtiste();
			
		}
		
	}
	
	public void afficheTotal() {
		
		test();
		toString();		
	}
	
	protected void ajouterSpectacleA(Spectacle s) {
		
		this.getSpect().add(s);
		
	}
	
	protected void retirerSpectacleA(Spectacle s) {
		
		this.getSpect().remove(s);
		
	}
	
	public void affecterSpectacleA(Spectacle s) {
		
		ajouterSpectacleA(s);
		System.out.println("ahah");
	}
	
	public void retirerAffecterSpectacle(Spectacle s) {
		
		retirerSpectacleA(s);
		
	}

}