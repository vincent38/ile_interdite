package model.aventurier;

import model.aventurier.Aventurier;
import java.awt.Color;
import java.util.ArrayList;
import model.Tuile;
import util.Utils;

public class Messager extends Aventurier {

    /**
     * Constructeur basique 
     * @param nom 
     */
   
    
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
    
    @Override
    public ArrayList<Aventurier> getAventuriersDonPossible(ArrayList<Aventurier> aventuriers) {
        return aventuriers;
    }
}