package modele;

public class Cheque extends Paiement{

	public Cheque(Boolean realise) {
		super(realise);
		// TODO Auto-generated constructor stub
	}
	public String getTypeSiRealise(){
		return "Cheque";
	}

}
