package model;

import java.util.ArrayList;

public class Explorateur extends Aventurier {

    public Explorateur(String nom) {
        super(nom);
    }

    public Explorateur(Tuile tuile, String nom){
        super(tuile, nom);
    }
    
    @Override
    public ArrayList<Tuile> getDeplacementsPossibles(Grille g){
        return g.getTuilesAdjEtDiag(tuile);
    }
}