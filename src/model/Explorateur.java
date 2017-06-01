package model;

public class Explorateur extends Aventurier {

    public Explorateur(String nom) {
        super(nom);
    }

    public Explorateur(Tuile tuile, String nom){
        super(tuile, nom);
    }
}