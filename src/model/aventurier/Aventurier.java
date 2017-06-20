package model.aventurier;

import java.awt.Color;
import java.util.ArrayList;
import model.carte.CarteTresor;
import model.Grille;
import model.Tresor;
import model.Tuile;

public abstract class Aventurier {

	public ArrayList<Tresor> tresorsObtenus = new ArrayList<>();
	public ArrayList<CarteTresor> cartes = new ArrayList<>();
	public Tuile tuileCourante;
        public ArrayList<Tuile> tuilesPossibles = new ArrayList();
        private String nom;
        protected boolean pouvoirDispo = true;
        private String type;

    
     /**
      * Constructeur
      * @param nom 
      */       
    public Aventurier(String nom) {
        this.nom = nom;
        setType("Aventurier");
    }

        /**
     * Constructeur
     * @param tuile
     * @param nom 
     */


    public Aventurier(Tuile tuile, String nom){
        this.tuileCourante = tuile;
        this.nom = nom;
        setType("Aventurier");
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    /**
     * Assèche la tuile t dans la grille
     * @param t Tuile
     * @param g Grille
     */
    
    public void assecher(Tuile t, Grille g) {
        g.setTuile(t.getX(), t.getY(), Tuile.ETAT_TUILE_SECHE);
    }

    /**
     * 
     * Méthode d'asséchement.
     * @param tuilesAssechables ArrayList<Tuile> 
     * @param g Grille
     * @param x int
     * @param y int
     * @return boolean
     */     

    public boolean assecher(ArrayList<Tuile> tuilesAssechables, Grille g, int x, int y) {
        Tuile tuileVoulue = new Tuile(x, y);
        //On vérifie si elle existe. Existe -> on assèche la tuile
        for (Tuile t : tuilesAssechables) {
            if (t.getX() == tuileVoulue.getX() && t.getY() == tuileVoulue.getY()) {
                //g.setTuile(t.getX(), t.getY(), Tuile.ETAT_TUILE_SECHE);
                t.setAssechee();
                //System.out.println(tuileVoulue.getEtatTuile());
                return true;
            }
        }
        return false;
    }

    public ArrayList<Tuile> getTuilesAssechables(Grille g) {
        Tuile t = getTuile();
        ArrayList<Tuile> buffer = new ArrayList<>();
        for (int i = t.getX() - 1; i <= t.getX() + 1; i++) {
            for (int j = t.getY() - 1; j <= t.getY() + 1; j++) {
                if (!(i == t.getX() - 1 && j == t.getY() - 1 || i == t.getX() - 1 && j == t.getY() + 1 || i == t.getX() + 1 && j == t.getY() - 1 || i == t.getX() + 1 && j == t.getY() + 1)) {
                    Tuile tuBuffer = g.getTuile(i, j);
                    if (tuBuffer != null && tuBuffer.getEtatTuile() == Tuile.ETAT_TUILE_INONDEE) {
                        buffer.add(tuBuffer);
                    }
                }
            }
        }
        return buffer;
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
     * @param tuile Tuile
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
    
    public int countCartes() {
        return cartes.size();
    }
    
    public void ajouterCarte(CarteTresor c){
        cartes.add(c);
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

    public ArrayList<CarteTresor> getCartesPossedees() {
        return cartes;
    }

    public void setPouvoirDispo(boolean b) {
        this.pouvoirDispo = true;
    }

    public ArrayList<Tresor> getTresorsObtenus() {
        return tresorsObtenus;
    }

    public void addTresorsObtenus(Tresor tresorObtenu) {
        tresorsObtenus.add(tresorObtenu);
    }
    
    public void retirerCarte(CarteTresor carteASupprimer) {
        this.cartes.remove(carteASupprimer);
    }
    
    /**
     * Méthode inutilisée actuellement. Gardée en prévision.
     */
    
    
    
}

