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
    
     /**
      * Constructeur
      * @param nom 
      */   
        
    public Aventurier(String nom) {
        this.nom = nom;
    }
    
    /**
     * Constructeur
     * @param tuile
     * @param nom 
     */

    public Aventurier(Tuile tuile, String nom){
        this.tuileCourante = tuile;
        this.nom = nom;
    }
    
    /**
     * Assèche la tuile t dans la grille
     * @param t
     * @param g 
     */
    
    public void assecher(Tuile t, Grille g) {
        g.setTuile(t.getX(), t.getY(), Tuile.ETAT_TUILE_SECHE);
    }

    /**
     * 
     * Méthode inutilisée actuellement. Gardée en prévision.
     */

    public void donnerCarte() {
            throw new UnsupportedOperationException();
    }

    /**
     * Retourne la tuile courante de l'aventurier
     * @return tuileCourante 
     */
    
    public Tuile getTuile() {
        return tuileCourante;
    }
    
    /**
     * Assigne la nouvelle tuileCourante de l'aventurier
     * @param tuile 
     */

    public void setTuile(Tuile tuile) {
        this.tuileCourante = tuile;
        tuile.addAventurier(this);
        
    }
    
    /**
     * Méthode inutilisée actuellement. Gardée en prévision.
     */

    public ArrayList<CarteTresor> getCartes() {
        return cartes;
    }
    
    public void deplacement(Tuile nvTuile, Grille g){
        this.tuileCourante.rmAventurier(this);
        this.setTuile(nvTuile);
    }
    
    /**
     * Retourne tous les déplacments possibles de l'aventurier
     * @param grille
     * @return 
     */

    public ArrayList<Tuile> getDeplacementsPossibles(Grille grille) {
        return grille.getTuilesAdjacentes(this.tuileCourante);
    }
    
    /**
     * Retourne le nom de l'aventurier
     * @return 
     */

    public String getNom() {
        return nom;
    }
    
    /**
     * Méthode abstraite définie dans les spécialisations
     * @return 
     */
    
    public abstract Color getColor();
    
    /**
     * Retourne la couleur de la police d'écriture
     * @return 
     */
    
    public Color getFontColor(){
        return Color.WHITE;
    }
    
    /**
     * Méthode inutilisée actuellement. Gardée en prévision.
     */
    
    public void traiterFinDeTour(){}
}