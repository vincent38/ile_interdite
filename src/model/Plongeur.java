package model;

import java.util.ArrayList;

public class Plongeur extends Aventurier {

    public Plongeur(String nom) {
        super(nom);
    }

    public Plongeur(Tuile tuile, String nom){
        super(tuile, nom);
    }

    @Override
    public ArrayList<Tuile> getDeplacementsPossibles(Grille g){
        return g.getDeplacementsPlongeur(this.tuile);
    }
}