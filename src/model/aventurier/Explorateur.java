package model.aventurier;

import model.aventurier.Aventurier;
import java.awt.Color;
import java.util.ArrayList;
import model.Grille;
import model.Tuile;
import util.Utils;

public class Explorateur extends Aventurier {
    
    /**
     * Constructeur basique
     * @param nom 
     */
    
    public Explorateur(String nom) {
        super(nom);
        setType("Explorateur");
    }
    
    /**
     * Constructeur assignant nom et tuile de départ
     * @param tuile
     * @param nom 
     */

    public Explorateur(Tuile tuile, String nom) {
        super(tuile, nom);
        setType("Explorateur");
    }
    
    /**
     * Retourne les déplacements possibles propre à l'explorateur
     * @param g
     * @return 
     */
    @Override
    public ArrayList<Tuile> getDeplacementsPossibles(Grille g) {
        return g.getTuilesAdjEtDiag(tuileCourante);

    }
    
    /**
     * déplace l'explorateur
     * @param nvTuile
     * @param g 
     */
    
    @Override
    public void deplacement(Tuile nvTuile, Grille g){
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

    @Override
    public ArrayList<Tuile> getAssechablesParJoueur(Grille g) {
        Tuile t = getTuile();
        ArrayList<Tuile> buffer = new ArrayList<>();
        for (int i = t.getX() - 1; i <= t.getX() + 1; i++) {
            for (int j = t.getY() - 1; j <= t.getY() + 1; j++) {
                Tuile tuBuffer = g.getTuile(i, j);
                if (tuBuffer != null && tuBuffer.getEtatTuile() == Tuile.ETAT_TUILE_INONDEE) {
                    buffer.add(tuBuffer);
                }
            }
        }
        return buffer;
    }
}

