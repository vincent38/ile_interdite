package model;

import java.util.*;

public class Tuile {
	private boolean _inonde;
	public ArrayList<Aventurier> aventuriers = new ArrayList<Aventurier>();
	public Tresor tresor;
        private int inondee; //0 = case asséchée, 1 = case inondée, 2 = case coulée

        public Tuile(){
            
        }
        
        /**
         * Permet d'inonder une case
         * @param aFalse 
         */
	public void setInondee(boolean aFalse) {
		if (inondee == 0) {
                    inondee = 1;
                }
	}
        
        /**
         * Permet de couler une case
         * @param aFalse 
         */
	public void setCoulee(boolean aFalse) {
		if (inondee == 1) {
                    inondee = 2;
                }
	}
        
        /**
         * Permet d'assécher une case
         */
        public void setAssechee() {
            if (inondee == 1) {
                inondee = 0;
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