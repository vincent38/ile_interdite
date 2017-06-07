package model;

import java.awt.Color;
import util.Utils;

public class Ingenieur extends Aventurier {
    
    public Ingenieur(String nom) {
        super(nom);
        setType("Ingenieur");
    }

    public Ingenieur(Tuile tuile, String nom){
        super(tuile, nom);
        setType("Ingenieur");
    }

    @Override
    public Color getColor() {
        return Utils.Pion.ROUGE.getCouleur();
    }
    
}