package modele;

public class AbonneT extends Tarification{

	private static double reduction;

	public static double remise() {
		
		return AbonneT.reduction;
		
	}

	public AbonneT() {
		super();
		this.reduction = 0.30;
	}

	
	
}
