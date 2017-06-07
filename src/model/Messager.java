package model;

import java.awt.Color;
import util.Utils;

public class Messager extends Aventurier {

    /**
     * Constructeur basique 
     * @param nom 
     */
    
    public Messager(String nom) {
        super(nom);
        setType("Messager");
    }
    
    /**
     * Constructeur assignant le nom ainsi que la tuile de départ
     * @param tuile
     * @param nom 
     */

    public Messager(Tuile tuile, String nom){
        super(tuile, nom);
        setType("Messager");
    }

    /**
     * Retourne la couleur Blanc du Messager
     * @return 
     */
    
    @Override
    public Color getColor() {
        return Utils.Pion.BLANC.getCouleur();
    }
    
    /**
     * Retourne la couleur Noire pour l'affiche sur l'IHM
     * @return 
     */
    
    @Override
    public Color getFontColor(){
        return Color.BLACK;
    }
}