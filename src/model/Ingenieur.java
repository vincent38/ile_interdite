package model;

import java.awt.Color;
import util.Utils;

public class Ingenieur extends Aventurier {

    public Ingenieur(String nom) {
        super(nom);
    }

    public Ingenieur(Tuile tuile, String nom){
        super(tuile, nom);
    }

    @Override
    public Color getColor() {
        return Utils.Pion.ROUGE.getCouleur();
    }
}