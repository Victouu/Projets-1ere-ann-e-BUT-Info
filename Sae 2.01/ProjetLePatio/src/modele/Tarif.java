package modele;

import java.util.Objects;

public class Tarif {

	private double pleinTarif;

	public Tarif(double pleinTarif) {
		super();
		this.pleinTarif = pleinTarif;
	}

	public double getPleinTarif() {
		return pleinTarif;
	}

	@Override
	public int hashCode() {
		return Objects.hash(pleinTarif);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarif other = (Tarif) obj;
		return Double.doubleToLongBits(pleinTarif) == Double.doubleToLongBits(other.pleinTarif);
	}

	@Override
	public String toString() {
		return "Tarif [pleinTarif=" + pleinTarif + "]";
	}

	public void ajouterTarif(Zone z, Spectacle s) {

		if (z.equals(null)) {
			System.out.println("La zone est null");
		} else if (s.equals(null)) {
			System.out.println("Le spectacle est null");
		} else if (z.getAssociation().containsKey(s)) {
			System.out.println("Le spectacle est déjà ajouté");
		} else if (s.getAssos().containsKey(z)) {
			System.out.println("Impossible, la zone est déjà ajoutée");
		} else {
			z.ajouterZoneTarif(s, this);
			s.ajouterSpectacleTarif(z, this);
			System.out.println("C'est bien");
		}

	}
}