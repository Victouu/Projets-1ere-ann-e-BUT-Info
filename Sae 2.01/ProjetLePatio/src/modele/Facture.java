package modele;

import java.util.Objects;

public class Facture {

	final static double TVA = 0.0210;

	private String numero;
	private double montantTTC;
	private double staticTauxTva = montantTTC / TVA;
	private Paiement paiement;
	private Reservation reservation;

	public Facture(String numero, double montantTTC, Paiement paiement) {
		super();
		this.numero = numero;
		this.montantTTC = montantTTC;
		this.paiement = paiement;
		paiement.ajouterFacture(this);
	}

	public Facture(double montantTTC) {
		super();
		this.numero = "";
		this.montantTTC = 50.00;
		this.paiement = null;
	}

	public double getStaticTauxTva() {
		return staticTauxTva;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setMontantTTC(double montantTTC) {
		this.montantTTC = montantTTC;
	}

	public void setPaiement(Paiement paiement) {
		this.paiement = paiement;
	}

	public String getNumero() {
		return numero;
	}

	public double getMontantTTC() {
		return montantTTC;
	}

	public Paiement getPaiement() {
		return paiement;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	// public double prixUnitaire(Reservation r) {

	// }

	public double calculTTC(Reservation r) {
		return Math.round((r.getBillet().size() * prixUnitaireReduit(r)) * 100.0) / 100.0;
	}

	public double calculTVA(Reservation r) {
		return Math.round(((r.getBillet().size() * montantTTC) * TVA) * 100.0) / 100.0;
	}

	public double calculRemise(Reservation r) {
		double var = 0;

		for (Billet b : this.getReservation().getBillet()) {
			double reduction = 0;
			String tarification = b.getTarification().getClass().getSimpleName();
			System.out.println(tarification);
			if (tarification.equals("Jeune")) {
				reduction = (Jeune.remise() * vue.CtrlCreationFacture.calculerTotalTarif(r))
						/ this.getReservation().getBillet().size();
				System.out.println("hsuzoegr" + reduction);
			} else if (tarification.equals("Groupe")) {
				reduction = (Groupe.remise() * vue.CtrlCreationFacture.calculerTotalTarif(r))
						/ this.getReservation().getBillet().size();
			} else if (tarification.equals("AbonneT")) {
				reduction = (AbonneT.remise() * vue.CtrlCreationFacture.calculerTotalTarif(r))
						/ this.getReservation().getBillet().size();
			} else if (tarification.equals("Senior")) {
				reduction = (Senior.remise() * vue.CtrlCreationFacture.calculerTotalTarif(r))
						/ this.getReservation().getBillet().size();
			}

			var += reduction;
			System.out.println(reduction);
		}

		return var;
	}

	public String affichagePourcentage(Reservation r) {
		int nb = 0;
		String pourc = "%";

		for (Billet b : this.getReservation().getBillet()) {
			double reduction = 0;
			String tarification = b.getTarification().getClass().getSimpleName();
			System.out.println(tarification);
			if (tarification.equals("Jeune")) {
				reduction = 30;
				System.out.println("hsuzoegr" + reduction);
			} else if (tarification.equals("Groupe")) {
				reduction = 15;
			} else if (tarification.equals("AbonneT")) {
				reduction = 30;
			} else if (tarification.equals("Senior")) {
				reduction = 25;
			}

			nb += reduction;
			System.out.println(reduction);
		}

		return nb / this.getReservation().getBillet().size() + pourc;
	}

	public double prixUnitaireReduit(Reservation r) {

		double var = 0;

		for (Billet b : this.getReservation().getBillet()) {
			double reduction = 0;
			String tarification = b.getTarification().getClass().getSimpleName();
			System.out.println(tarification);
			if (tarification.equals("Jeune")) {
				reduction = (vue.CtrlCreationFacture.calculerTotalTarif(r)
						- (Jeune.remise() * vue.CtrlCreationFacture.calculerTotalTarif(r)))
						/ this.getReservation().getBillet().size();
				System.out.println("hsuzoegr" + reduction);
			} else if (tarification.equals("Groupe")) {
				reduction = vue.CtrlCreationFacture.calculerTotalTarif(r)
						- (Groupe.remise() * vue.CtrlCreationFacture.calculerTotalTarif(r));
			} else if (tarification.equals("AbonneT")) {
				reduction = vue.CtrlCreationFacture.calculerTotalTarif(r)
						- (AbonneT.remise() * vue.CtrlCreationFacture.calculerTotalTarif(r));
			} else if (tarification.equals("Senior")) {
				reduction = vue.CtrlCreationFacture.calculerTotalTarif(r)
						- (Senior.remise() * vue.CtrlCreationFacture.calculerTotalTarif(r));
			}

			var += reduction;
			System.out.println(reduction);
		}

		return var;

	}

	public double calculHT(Reservation r) {
		return Math.round((calculTTC(r) - calculTVA(r)) * 100.0) / 100.0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(montantTTC, numero, paiement);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Facture other = (Facture) obj;
		return Double.doubleToLongBits(montantTTC) == Double.doubleToLongBits(other.montantTTC)
				&& Objects.equals(numero, other.numero) && Objects.equals(paiement, other.paiement);
	}

	@Override
	public String toString() {
		return "Facture [numero=" + numero + ", montantTTC=" + montantTTC + ", paiement="
				+ this.getPaiement().afficheSaufFacture() + "]";
	}

	public String afficheSaufPaiement() {

		return "Facture [numero=" + numero + ", montantTTC=" + montantTTC + "]";

	}

	////////////////////////////////////////////////////// PAIEMENT
	////////////////////////////////////////////////////// ////////////////////////////////////////////////////////////

	public void modifierPaiement(Paiement p) {

		if (this.getPaiement().equals(paiement) && this.getReservation().getFacture() != null) {
			System.out.println("Normalement ça fonctionne.");
			this.setPaiement(p);
		} else {
			System.out.println("zizi");
		}

	}

	////////////////////////////////////////////////////// RESERVATION
	////////////////////////////////////////////////////// ////////////////////////////////////////////////////////////

	public void ajouterReservation(Reservation r) {

		this.reservation = r;

	}

	public void modifierReservation(Reservation r) {

		if (this.getReservation().equals(reservation)) {
			System.out.println("Normalement ça fonctionne.");
			this.setReservation(r);
		} else {
			System.out.println("zizi");
		}

	}

}
