package model;

import java.awt.Color;
import java.util.ArrayList;
import util.Utils;

public class Explorateur extends Aventurier {
    
    /**
     * Constructeur basique
     * @param nom 
     */
    
    public Explorateur(String nom) {
        super(nom);
    }
    
    /**
     * Constructeur assignant nom et tuile de départ
     * @param tuile
     * @param nom 
     */

    public Explorateur(Tuile tuile, String nom){
        super(tuile, nom);
    }
    
    /**
     * Retourne les déplacements possibles propre à l'explorateur
     * @param g
     * @return 
     */
    
    @Override
    public ArrayList<Tuile> getDeplacementsPossibles(Grille g){
        if(this.pouvoirDispo)
            return g.getTuilesAdjEtDiag(tuileCourante);
        else
            return g.getTuilesAdjacentes(tuileCourante);
    }
    
    /**
     * déplace l'explorateur
     * @param nvTuile
     * @param g 
     */
    
    @Override
    public void deplacement(Tuile nvTuile, Grille g){
        if (!super.getDeplacementsPossibles(g).contains(nvTuile)){
            this.pouvoirDispo = false;
        }
        super.deplacement(nvTuile, g);
    }
    
    /**
     * Retourne Vert (couleur de l'explorateur)
     * @return 
     */

    @Override
    public Color getColor() {
        return Utils.Pion.VERT.getCouleur();
    }
}