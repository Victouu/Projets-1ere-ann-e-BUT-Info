package modele;

public class CB extends Paiement{

	public CB(Boolean realise) {
		super(realise);
	}

	public String getTypeSiRealise(){
		
		return "CB";
		
	}

	public CB() {
		super(null);
	}

	
	
}
