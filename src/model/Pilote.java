package model;

import java.awt.Color;
import java.util.ArrayList;
import util.Utils;

public class Pilote extends Aventurier {

    /**
     * Constructeur basique
     * @param nom 
     */
    

    /**
     * Constructeur assignant le nom ainsi que la tuile de départ 
     * @param tuile
     * @param nom 
     */
    
    public Pilote(Tuile tuile, String nom){
        super(tuile, nom);
        setType("Pilote");
    }

    /**
     * Rends le pouvoir de déplacmeent du pilote indisponible
     */
    

    
    /**
     * Permet de retourner toutes les tuiles encore en jeu pôur le pouvoir du pilote
     * @param g
     * @return 
     */
        
    @Override
    public ArrayList<Tuile> getDeplacementsPossibles(Grille g){
        if (pouvoirDispo){
            return g.getToutesLesTuiles();
        }
        else
            return super.getDeplacementsPossibles(g);
    }
    
    /**
     * Vérifie que le pouvoir du pilote est disponible avant son déplacement
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
     * Rends le pouvoir disponible pour le prochain tour
     */
    
    /**
     * Retourne la couleur Bleu du pilote
     * @return 
     */

    @Override
    public Color getColor() {
        return Utils.Pion.BLEU.getCouleur();
    }
}