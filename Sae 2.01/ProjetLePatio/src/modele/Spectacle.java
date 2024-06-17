package modele;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;


public class Spectacle {
	
	private String nom;
	private int duree;
	private int nbreMaxSpect;
	private String genre;
	private Collection<Zone> zone = new ArrayList<>();
	private Collection<Artiste> artiste = new ArrayList<>();
	private Collection<Representation> representation = new ArrayList<>();
	private HashMap<Zone, Tarif> assos = new HashMap<>();
	private Tarif tarif;
	
	
	
	public Tarif getTarif() {
		return tarif;
	}


	public Spectacle(String nom, int duree, int nbreMaxSpect, String genre, Artiste artiste) {
		super();
		this.nom = nom;
		this.duree = duree;
		this.nbreMaxSpect = nbreMaxSpect;
		this.genre = genre;
		this.artiste.add(artiste);
		artiste.affecterSpectacleA(this);
	}


	public String getNom() {
		return nom;
	}


	public int getDuree() {
		return duree;
	}


	public int getNbreMaxSpect() {
		return nbreMaxSpect;
	}


	public String getGenre() {
		return genre;
	}


	public Collection<Zone> getZone() {
		return zone;
	}
	
	public Collection<Artiste> getArtiste() {
		return artiste;
	}


	public Collection<Representation> getRepresentation() {
		return representation;
	}

	



	public void setZone(Zone zone) {
		this.getZone().add(zone);
	}


	@Override
	public int hashCode() {
		return Objects.hash(artiste, duree, genre, nbreMaxSpect, nom, representation, zone);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Spectacle other = (Spectacle) obj;
		return Objects.equals(artiste, other.artiste) && duree == other.duree && Objects.equals(genre, other.genre)
				&& nbreMaxSpect == other.nbreMaxSpect && Objects.equals(nom, other.nom)
				&& Objects.equals(representation, other.representation) && Objects.equals(zone, other.zone);
	}


	public void ajouterSpectacleTarif(Zone z, Tarif t) {
		
		this.getAssos().put(z, t);
		
	}
	
	public HashMap<Zone, Tarif> getAssos() {
		return assos;
	}


	@Override
	public String toString() {
		return "Spectacle [nom=" + nom + ", duree=" + duree + ", nbreMaxSpect=" + nbreMaxSpect + ", genre=" + genre
				+ ", zone=" + zone + ", artiste=" + artiste + "]";
	}




	public String afficheSansZone() {
		
		return "Spectacle [nom=" + nom + ", duree=" + duree + ", nbreMaxSpect=" + nbreMaxSpect + ", genre=" + genre
				+ ", artiste=" + artiste + ", representation=" + representation + "]";
		
	}
	
	public String afficheSansArtiste() {
		
		return "Spectacle [nom=" + nom + ", duree=" + duree + ", nbreMaxSpect=" + nbreMaxSpect + ", genre=" + genre
				+ ", zone=" + zone + ", representation=" + representation + "]";
		
	}

	public String afficheSansRepresentation() {
	
		return "Spectacle [nom=" + nom + ", duree=" + duree + ", nbreMaxSpect=" + nbreMaxSpect + ", genre=" + genre
				+ ", zone=" + zone + ", artiste=" + artiste + "]";
	
}
	
	//////////////////////////////////////////////////////ZONE ////////////////////////////////////////////////////////////
	
	protected void ajouterZone(Zone z) {
		
		this.getZone().add(z);
		
	}
	
	protected void retirerZone(Zone z) {
		
		this.getZone().remove(z);
		
	}
	
	public void affecterZone(Zone z) {
		
		if (this.getZone() == null) {
			
			System.out.println("Erreur, un specatacle ne peut pas avoir aucune zone. On ajoute.");
			ajouterZone(z);
			System.out.println("Zone ajouté");
			
		}
		
	}
	
	public void retirerAffecterZone(Zone z) {
		if (this.getZone().equals(z) == this.getZone().contains(z)) {
			
			System.out.println("Erreur, le spectacle contient déjà une zone identique, suppression de la zone dupliquée.");
			retirerZone(z);
			
		}
	}
	
	//////////////////////////////////////////////////////REPRESENTATION ////////////////////////////////////////////////////////////
	
	protected void ajouterRepresentation(Representation r) {
		
		this.getRepresentation().add(r);
		
	}


	protected void retirerRepresentation(Representation r) {
		
		this.getRepresentation().remove(r);
		
	}


	public void affecterRepresentation(Representation r) {
		
		
		ajouterRepresentation(r);
		
	}
	
	public void retirerAffecterRepresentation(Representation r) {
		
		retirerRepresentation(r);
		
	}

	
	//////////////////////////////////////////////////////ARTISTE ////////////////////////////////////////////////////////////
	
protected void ajouterArtiste(Artiste a) {
		
		this.getArtiste().add(a);
		
	}


	protected void retirerArtiste(Artiste a) {
		
		this.getArtiste().remove(a);
		
	}


	public void affecterArtiste(Artiste a) {
		
		
		ajouterArtiste(a);
		System.out.println("eheh");
	}
	
	public void retirerAffecterArtiste(Artiste a) {
		
		retirerArtiste(a);
		
	}

}