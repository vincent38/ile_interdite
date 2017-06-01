package model;

public class Plongeur extends Aventurier {

    public Plongeur(String nom) {
        super(nom);
    }

    public Plongeur(Tuile tuile, String nom){
        super(tuile, nom);
    }

	public void addTuilesPossibles(Tuile aCollec) {
		throw new UnsupportedOperationException();
	}
}