package model;

import java.awt.Color;
import util.Utils;

public class Messager extends Aventurier {

    public Messager(String nom) {
        super(nom);
        setType("Messager");
    }

    public Messager(Tuile tuile, String nom){
        super(tuile, nom);
        setType("Messager");
    }

    @Override
    public Color getColor() {
        return Utils.Pion.BLANC.getCouleur();
    }
    
    @Override
    public Color getFontColor(){
        return Color.BLACK;
    }
}