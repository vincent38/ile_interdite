package model;

import java.awt.Color;
import java.util.ArrayList;
import util.Utils;

public class Explorateur extends Aventurier {

    public Explorateur(String nom) {
        super(nom);
        setType("Explorateur");
    }

    public Explorateur(Tuile tuile, String nom) {
        super(tuile, nom);
        setType("Explorateur");
    }

    @Override
    public ArrayList<Tuile> getDeplacementsPossibles(Grille g) {
        if (this.pouvoirDispo) {
            return g.getTuilesAdjEtDiag(tuileCourante);
        } else {
            return g.getTuilesAdjacentes(tuileCourante);
        }
    }

    @Override
    public void deplacement(Tuile nvTuile, Grille g) {
        if (!super.getDeplacementsPossibles(g).contains(nvTuile)) {
            this.pouvoirDispo = false;
        }
        super.deplacement(nvTuile, g);
    }

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
