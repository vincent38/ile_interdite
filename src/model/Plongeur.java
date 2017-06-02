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
        if(this.pouvoirDispo)
            return g.getDeplacementsPlongeur(this.tuile);
        else
            return super.getDeplacementsPossibles(g);
    }
    
        @Override
    public void deplacement(Tuile nvTuile, Grille g){
        if (!super.getDeplacementsPossibles(g).contains(nvTuile)){
            this.pouvoirDispo = false;
        }
        super.deplacement(nvTuile, g);
    }
}