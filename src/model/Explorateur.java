package model;

import java.awt.Color;
import java.util.ArrayList;
import util.Utils;

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
            return g.getTuilesAdjEtDiag(tuileCourante);
        else
            return g.getTuilesAdjacentes(tuileCourante);
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
        return Utils.Pion.VERT.getCouleur();
    }
    
    
}