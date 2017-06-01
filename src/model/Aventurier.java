package model;

import java.util.ArrayList;

public class Aventurier {
	public ArrayList<Tresor> tresorsObtenus = new ArrayList<>();
	public ArrayList<CarteTresor> cartes = new ArrayList<>();
	public Tuile tuile;
        public ArrayList<Tuile> tuilesPossibles = new ArrayList();
        private String nom;
        
    public Aventurier(String nom) {
        this.nom = nom;
    }

    public Aventurier(Tuile tuile, String nom){
        this.tuile = tuile;
        this.nom = nom;
    }
    
    public void assecher(Tuile t, Grille g) {
        g.setTuile(t.getX(), t.getY(), Tuile.ETAT_TUILE_SECHE);
    }


    

    public void donnerCarte() {
            throw new UnsupportedOperationException();
    }

    public Tuile getTuile() {
        return tuile;
    }

    public void setTuile(Tuile tuile) {
        this.tuile = tuile;
        tuile.addAventurier(this);
        
    }

    public ArrayList<CarteTresor> getCartes() {
        return cartes;
    }

    

    public boolean getInondee() {
            throw new UnsupportedOperationException();
    }

    
    public void deplacement(Tuile nvTuile){
        this.tuile.rmAventurier(this);
        this.setTuile(nvTuile);
        
    }

    public ArrayList<Tuile> getDeplacementsPossibles(Grille grille) {
        //ArrayList<Tuile> r = new ArrayList();
           // Tuile tuileC = this.getTuile();
           System.out.println("Dbug");
            return grille.getTuilesAdjacentes(this.tuile);
            
            
            
    }

    public String getNom() {
        return nom;
    }
    
    public void traiterFinDeTour(){}
}