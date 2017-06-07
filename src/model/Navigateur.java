package model;

import java.awt.Color;
import util.Utils;

public class Navigateur extends Aventurier {

    public Navigateur(String nom) {
        super(nom);
    }

    public Navigateur(Tuile tuile, String nom){
        super(tuile, nom);
    }

    @Override
    public Color getColor() {
        return Color.YELLOW;
    }
    
    @Override
    public Color getFontColor(){
        return Color.BLACK;
    }
    
    
}