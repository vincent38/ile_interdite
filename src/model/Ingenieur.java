package model;

import java.awt.Color;
import util.Utils;

public class Ingenieur extends Aventurier {

    /**
     * Constructeur basique
     * @param nom 
     */
    
    public Ingenieur(String nom) {
        super(nom);
    }
    
    /**
     * Constructeur assignant le nom ainsi que la tuile de départ
     * @param tuile
     * @param nom 
     */

    public Ingenieur(Tuile tuile, String nom){
        super(tuile, nom);
    }
    
    /**
     * Retourne la couleur Rouge pour l'ingénieur
     * @return 
     */
    
    @Override
    public Color getColor() {
        return Utils.Pion.ROUGE.getCouleur();
    }
}