package modele;

public class Groupe extends Tarification{

	private static double reduction;

	public static double remise() {
		
		return Groupe.reduction;
		
	}

	public Groupe() {
		super();
		this.reduction = 0.15;
	}
	
	
	
}
