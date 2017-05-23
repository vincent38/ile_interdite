package model;

import java.util.ArrayList;
import java.util.Vector;

public class Tuile {
	private int etatTuile;
        private static final int ETAT_TUILE_SECHE = 0;
        private static final int ETAT_TUILE_INONDEE = 1;
        private static final int ETAT_TUILE_COULEE = 2;
	public ArrayList<Aventurier> aventuriers = new ArrayList<>();
	public Tresor _tresor;

        public Tuile(){
            
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
         * @param aAventurier 
         */

	public void addAventurier(Aventurier aAventurier) {
		throw new UnsupportedOperationException();
	}
}
