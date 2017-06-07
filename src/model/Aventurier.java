package model;

import java.awt.Color;
import java.util.ArrayList;

public abstract class Aventurier {
	public ArrayList<Tresor> tresorsObtenus = new ArrayList<>();
	public ArrayList<CarteTresor> cartes = new ArrayList<>();
	public Tuile tuileCourante;
        public ArrayList<Tuile> tuilesPossibles = new ArrayList();
        private String nom;
        protected boolean pouvoirDispo = true;
        
    public Aventurier(String nom) {
        this.nom = nom;
    }

    public Aventurier(Tuile tuile, String nom){
        this.tuileCourante = tuile;
        this.nom = nom;
    }
    
    public void assecher(Tuile t, Grille g) {
        g.setTuile(t.getX(), t.getY(), Tuile.ETAT_TUILE_SECHE);
    }


    

    public void donnerCarte() {
            throw new UnsupportedOperationException();
    }

    public Tuile getTuile() {
        return tuileCourante;
    }

    public void setTuile(Tuile tuile) {
        this.tuileCourante = tuile;
        tuile.addAventurier(this);
        
    }

    public ArrayList<CarteTresor> getCartes() {
        return cartes;
    }

    

    public boolean getInondee() {
            throw new UnsupportedOperationException();
    }

    
    public void deplacement(Tuile nvTuile, Grille g){
        this.tuileCourante.rmAventurier(this);
        this.setTuile(nvTuile);
    }

    public ArrayList<Tuile> getDeplacementsPossibles(Grille grille) {
        return grille.getTuilesAdjacentes(this.tuileCourante);
    }

    public String getNom() {
        return nom;
    }
    
    public abstract Color getColor();
    
    public Color getFontColor(){
        return Color.WHITE;
    }
    
    public void traiterFinDeTour(){}
}