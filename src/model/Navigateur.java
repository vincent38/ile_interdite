package model;

public class Navigateur extends Aventurier {

    public Navigateur(String nom) {
        super(nom);
    }

    public Navigateur(Tuile tuile, String nom){
        super(tuile, nom);
    }
}