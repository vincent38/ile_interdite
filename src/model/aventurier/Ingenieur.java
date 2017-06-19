package model.aventurier;

import model.aventurier.Aventurier;
import java.awt.Color;
import model.Tuile;
import util.Utils;

public class Ingenieur extends Aventurier {


    /**
     * Constructeur basique
     * @param nom 
     */
    
    /**
     * Constructeur assignant le nom ainsi que la tuile de départ
     * @param tuile
     * @param nom 
     */

    public Ingenieur(Tuile tuile, String nom){
        super(tuile, nom);
        setType("Ingenieur");
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