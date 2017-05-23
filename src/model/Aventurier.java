package model;

import java.util.ArrayList;

public class Aventurier {
	public ArrayList<Tresor> tresorsObtenus = new ArrayList<>();
	public ArrayList<CarteTresor> cartes = new ArrayList<>();
	public Tuile positionCourante;

    public Aventurier(Tuile tuile){
        this.positionCourante = tuile;
    }
    
    public void assecher(Tuile t) {
            throw new UnsupportedOperationException();
    }


	public ArrayList<Tuile> getTuilesPossibles(Grille aGrille) {
		throw new UnsupportedOperationException();
	}

    public void donnerCarte() {
            throw new UnsupportedOperationException();
    }

    public void getTuile() {
            throw new UnsupportedOperationException();
    }

    public void setTuile(Tuile aTuile) {
            throw new UnsupportedOperationException();
    }

    public boolean getInondee() {
            throw new UnsupportedOperationException();
    }

    public void getTuilesAssechables() {
            throw new UnsupportedOperationException();
    }
}