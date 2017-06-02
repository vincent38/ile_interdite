package model;

import java.awt.Color;
import java.util.ArrayList;
import util.Utils;

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

    @Override
    public Color getColor() {
        return Utils.Pion.NOIR.getCouleur();
    }
}