package modele;

import java.util.Objects;

public abstract class Paiement {
	
	private Boolean realise;
	private Facture facture;
	
	
	public Paiement(Boolean realise) {
		super();
		this.realise = realise;
	}

	
	public void ajouterPaie(Facture f) {
		if(!f.equals(null)&& !(this.getFacture() != null)) {
			ajouterFacture(f);
		}
	}
	
	public String getTypeSiRealise() {
		String a = "oui";
		
		if (this.getFacture().equals(null)) {
			return a;
		}
		return a;
		
	};

	public Boolean getRealise() {
		return realise;
	}


	public Facture getFacture() {
		return facture;
	}

	

	public void setFacture(Facture facture) {
		this.facture = facture;
	}


	@Override
	public int hashCode() {
		return Objects.hash(facture, realise);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paiement other = (Paiement) obj;
		return Objects.equals(facture, other.facture) && Objects.equals(realise, other.realise);
	}


	@Override
	public String toString() {
		return "Paiement [realise=" + realise + ", facture=" + this.getFacture().afficheSaufPaiement() + "]";
	}
	
	public String afficheSaufFacture() {
		
		return "Paiement [realise=" + realise + "]";
		
	}
	
	public boolean isRealise() {
		
		return realise;
		
	}
	
	//////////////////////////////////////////////////////FACTURE ////////////////////////////////////////////////////////////
	
	public void ajouterFacture(Facture f) {
		
		this.facture = f;
		
	}
	
	public void modifierFacture(Facture f) {
		
		this.setFacture(f);
		
	}

}
