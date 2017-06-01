package model;

public class Pilote extends Aventurier {
	private boolean pouvoirDispo;

    public Pilote(String nom) {
        super(nom);
    }

    public Pilote(Tuile tuile, String nom){
        super(tuile, nom);
    }

	public void setPouvoirNonDispo() {
		throw new UnsupportedOperationException();
	}
}