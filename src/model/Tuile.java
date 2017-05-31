package model;

import java.util.ArrayList;
import java.util.Vector;

public class Tuile {

        // Coordonnées
        private int x;
        private int y;
        // Etat Tuile
	private int etatTuile;
        public static final int ETAT_TUILE_SECHE = 0;
        public static final int ETAT_TUILE_INONDEE = 1;
        public static final int ETAT_TUILE_COULEE = 2;
        
	public ArrayList<Aventurier> aventuriers = new ArrayList<>();
	public Tresor tresor;

        public Tuile(int x, int y){
            this.x = x;
            this.y = y;
        }
        
        /**
         * Permet d'inonder une case
         * @param aFalse 
         */
	public void setInondee(boolean aFalse) {
		if (etatTuile == ETAT_TUILE_SECHE) {
                    etatTuile = ETAT_TUILE_INONDEE;
                }
	}
        
        /**
         * Permet de couler une case
         * @param aFalse 
         */
	public void setCoulee(boolean aFalse) {
		if (etatTuile == ETAT_TUILE_INONDEE) {
                    etatTuile = ETAT_TUILE_COULEE;
                }
	}
        
        /**
         * Permet d'assécher une case
         */
        public void setAssechee() {
            if (etatTuile == ETAT_TUILE_INONDEE) {
                etatTuile = ETAT_TUILE_SECHE;
            }
        }
        
        /**
         * 
         * 
         */


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ArrayList<Aventurier> getAventuriers() {
        return aventuriers;
    }
    
    public void addAventurier(Aventurier av){
        this.aventuriers.add(av);
    }
    
    public void rmAventurier(Aventurier av){
        this.aventuriers.remove(av); 
    }
    
    public int getEtatTuile(){
        return this.etatTuile;
    }
        
        
}
