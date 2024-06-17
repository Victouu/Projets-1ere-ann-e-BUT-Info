package modele;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;

public class Zone {

	private String nom;
	private Collection<Spectacle> spec = new ArrayList<>();
	private Collection<Fauteuil> fauteuil = new ArrayList<>();
	private HashMap<Spectacle, Tarif> association = new HashMap<>();
	private Tarif t;

	public Collection<Fauteuil> getFauteuil() {
		return fauteuil;
	}

	public Zone(String nom, Spectacle s) {
		super();
		this.nom = nom;
		s.setZone(this);
	}

	public String getNom() {
		return nom;
	}
	
	

	public Collection<Spectacle> getSpec() {
		return spec;
	}
	
	

	public HashMap<Spectacle, Tarif> getAssociation() {
		return association;
	}

	
	
	public Tarif getT() {
		return t;
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
		Zone other = (Zone) obj;
		return Objects.equals(nom, other.nom) && Objects.equals(spec, other.spec);
	}

	public void ajouterZoneTarif(Spectacle s, Tarif t) {
		
		this.getAssociation().put(s, t); 
		
	}
	
	@Override
	public String toString() {
		return "Zone [nom=" + nom + ", spec=" + spec + ", fauteuil=" + fauteuil + "]";
	}

	public void sansSpect() {
		
		for (Spectacle spectacle : (spec)) {
			spectacle.afficheSansZone();
			
		}
		
	}
	
	public String sansFauteuil() {
		
		return "Zone [nom=" + nom + ", spec=" + spec + "]";
		
	}
	
	public void afficheTotalZone() {
		
		sansSpect();
		toString();
		
	}
	
	protected void test(Spectacle s, Tarif t) {
		
		association.put(s, t);
		
	}
	
	//////////////////////////////////////////////////////SPECTACLE ////////////////////////////////////////////////////////////
	
	protected void ajouterSpectacle(Spectacle s) {
		
		this.getSpec().add(s);
		
	}
	
	protected void retirerSpectacle(Spectacle s) {
		
		this.getSpec().remove(s);
		
	}
	
	public void affecterSpectacle(Spectacle s) {
		
		if (this.getSpec().equals(null)) {
			
			System.out.println("Erreur, le nb de spectacles ne peut être nul, on ajoute");
			ajouterSpectacle(s);
		}
			
		
	}
	
	public void retirerAffecterSpectacle(Spectacle s) {
		
		if (this.getSpec().equals(s) == this.getSpec().contains(s)) {
			
			System.out.println("Erreur, un spectacle avec les même caractéristiques existe déjà, suppression.");
			
			retirerSpectacle(s);
			
			System.out.println("Le spectacle en double a bien été supprimé. Bonne journée !");
			
		}
		
	}
	
	//////////////////////////////////////////////////////FAUTEUIL ////////////////////////////////////////////////////////////
	
	protected void ajouterFauteuil(Fauteuil f) {
		
		this.getFauteuil().add(f);
		
	}
	
	protected void retirerFauteuil(Fauteuil f) {
		
		this.getFauteuil().remove(f);
		
	}

	public void affecterFauteuil(Fauteuil f) {
		ajouterFauteuil(f);
		
	}
	
	public void retirerAffecterFauteuil(Fauteuil f) {
		
		retirerFauteuil(f);
		
	}
	
	
	
}
