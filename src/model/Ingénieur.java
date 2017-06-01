package model;

public class Ingénieur extends Aventurier {

    public Ingénieur(String nom) {
        super(nom);
    }

    public Ingénieur(Tuile tuile, String nom){
        super(tuile, nom);
    }
}