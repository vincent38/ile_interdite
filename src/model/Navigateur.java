package model;

import java.awt.Color;
import util.Utils;

public class Navigateur extends Aventurier {

    /**
     * Constructeur basique
     * @param nom 
     */
    
    public Navigateur(String nom) {
        super(nom);
        setType("Navigateur");
    }
    
    /**
     * Constructeur assignant le nom ainsi que la tuile de départ
     * @param tuile
     * @param nom 
     */

    public Navigateur(Tuile tuile, String nom){
        super(tuile, nom);
        setType("Navigateur");
    }

    /**
     * Retourne la couleur Jaune du naviguateur
     * @return 
     */
    
    @Override
    public Color getColor() {
        return Color.YELLOW;
    }
    
    /**
     * Retourne la couleur de police noire
     * @return 
     */
    
    @Override
    public Color getFontColor(){
        return Color.BLACK;
    }
    
    
}