package modele;

public class Senior extends Tarification{
	private static double reduction;

	public static double remise() {
		
		return Senior.reduction;
		
	}

	public Senior() {
		super();
		this.reduction = 0.25;
	}
	
	
}
