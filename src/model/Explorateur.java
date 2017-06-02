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
        if(this.pouvoirDispo)
            return g.getTuilesAdjEtDiag(tuile);
        else
            return g.getTuilesAdjacentes(tuile);
    }
    
        @Override
    public void deplacement(Tuile nvTuile, Grille g){
        if (!super.getDeplacementsPossibles(g).contains(nvTuile)){
            this.pouvoirDispo = false;
        }
        super.deplacement(nvTuile, g);
    }
}