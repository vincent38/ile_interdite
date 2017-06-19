package model.aventurier;

import model.aventurier.Aventurier;
import java.awt.Color;
import java.util.ArrayList;
import model.Grille;
import model.Tuile;
import util.Utils;

public class Plongeur extends Aventurier {


    /**
     * Constructeur assignant le nom ainsi que la tuile de départ
     * @param tuile
     * @param nom 
     */
    
    public Plongeur(Tuile tuile, String nom){
        super(tuile, nom);
        setType("Plongeur");
    }
    
    /**
     * Demande les tuiles disponibles au déplacement du plongeur
     * @param g
     * @return 
     */

    @Override
    public ArrayList<Tuile> getDeplacementsPossibles(Grille g){
        if(this.pouvoirDispo)
            return g.getDeplacementsPlongeur(this.tuileCourante);
        else
            return super.getDeplacementsPossibles(g);
    }
    
    @Override
    public void deplacement(Tuile nvTuile, Grille g){
        if (!super.getDeplacementsPossibles(g).contains(nvTuile)){
            this.pouvoirDispo = false;
        }
        this.tuileCourante.rmAventurier(this);
        this.setTuile(nvTuile);
    }

    /**
     * Retourne la couleur Noir du plongeur
     * @return 
     */
    
    @Override
    public Color getColor() {
        return Utils.Pion.NOIR.getCouleur();
    }
}