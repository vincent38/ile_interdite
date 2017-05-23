package model;

import java.util.*;

public abstract class Aventurier {
    public ArrayList<Tresor> tresorsObtenus = new ArrayList<Tresor>();
    public ArrayList<CarteTresor> cartes = new ArrayList<CarteTresor>();
    public Tuile positionCourante;

    public Aventurier(Tuile tuile){
        this.positionCourante = tuile;
    }
    
    public void assecher(Tuile t) {
            throw new UnsupportedOperationException();
    }

    public void donnerCarte() {
            throw new UnsupportedOperationException();
    }

    public void getTuile() {
            throw new UnsupportedOperationException();
    }

    public Collection<Tuile> getTuilesPossibles(Grille aGrille) {
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