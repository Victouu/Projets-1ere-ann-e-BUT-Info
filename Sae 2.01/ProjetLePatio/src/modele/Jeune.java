package modele;

public class Jeune extends Tarification {

	private static double reduction;

	public static double remise() {

		return Jeune.reduction;

	}

	public Jeune() {
		super();
		this.reduction = 0.30;
	}

}
