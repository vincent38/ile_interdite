package model.aventurier;

import java.awt.Color;
import java.util.ArrayList;
import model.carte.CarteTresor;
import model.Grille;
import model.Tresor;
import model.Tuile;
import model.TypeTresor;
import model.carte.CartePiece;

public abstract class Aventurier {

	public ArrayList<Tresor> tresorsObtenus = new ArrayList<>();
	public ArrayList<CarteTresor> cartes = new ArrayList<>();
	public Tuile tuileCourante;
        public ArrayList<Tuile> tuilesPossibles = new ArrayList();
        private String nom;
        protected boolean pouvoirDispo = true;
        private String type;
        protected int actionMax;

    
     /**
      * Constructeur
      * @param nom 
      */       
    public Aventurier(String nom) {
        this.nom = nom;
        setType("Aventurier");
        this.actionMax = 3;
    }

        /**
     * Constructeur
     * @param tuile
     * @param nom 
     */


    public Aventurier(Tuile tuile, String nom){
        this.tuileCourante = tuile;
        this.tuileCourante.addAventurier(this);
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

    //public ArrayList<CarteTresor> getCartes() {
    //    return cartes;
   // }
    
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
    
    public ArrayList<CartePiece> getCartesPiecesPossedees() {
        ArrayList<CarteTresor> cartesPossedees = this.getCartesPossedees();
        ArrayList<CartePiece> r = new ArrayList<>();
        for (CarteTresor c : cartesPossedees) {
            if (c.getTypeCarte().equals("tresor")) {
                r.add((CartePiece) c);
            }
        }
        return r;
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
    
    public void retirerCarte(int carteASupprimer) {
        this.cartes.remove(carteASupprimer);
    }
    
    public void retirerCarte(CarteTresor carteASupprimer) {
        this.cartes.remove(carteASupprimer);
    }

    public ArrayList<CartePiece> getCartesPiecePossedees() {
        ArrayList<CartePiece> buffer = new ArrayList();
        for(CarteTresor c : cartes) {
            if (c.getTypeCarte().equals("tresor")){
                buffer.add((CartePiece) c);
            }
        }
        return buffer;
    }
    
    public ArrayList<Aventurier> getAventuriersDonPossible(ArrayList<Aventurier> aventuriers) {
    // On passe l'arrayList en paramètres uniquement pour la classe Messager qui Override cette méthode
        ArrayList<Aventurier> r = new ArrayList();
        r = (ArrayList<Aventurier>) this.tuileCourante.getAventuriers().clone();
        r.remove(this);
        return r;
    }

    public ArrayList<CarteTresor> getCartes() {
        return this.cartes;
    }
    public void rm4cartesPieces(TypeTresor typeTresor) {
        int compteur = 4;
        /*for (CartePiece c : this.getCartesPiecePossedees()) {
            if (c.getTypeTresor().equals(typeTresor) && compteur > 0) {
                this.retirerCarte(c);
                compteur--;
            }
        }*/
        for (int i = 0; i < this.cartes.size(); i++) {
            if ("tresor".equals(this.cartes.get(i).getTypeCarte())){
                CartePiece c = (CartePiece) this.cartes.get(i);
                if (c.getTypeTresor().equals(typeTresor) && compteur > 0) {
                    this.retirerCarte(i);
                    compteur--;
                }
            }
        }
    }

    public boolean isPouvoirDispo() {
        return pouvoirDispo;
    }
    
    public int getActionMax(){
        return 3;
    }
    
    
    
}

