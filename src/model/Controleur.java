package model;

import java.util.ArrayList;
import java.util.Vector;

public class Controleur {
	public ArrayList<Carte> cartes = new ArrayList<>();
	public ArrayList<Aventurier> joueurs = new ArrayList<>();
	public Grille grille;
	public ArrayList<Tresor> tresors = new ArrayList<>();
	public VueAventurier vueAventurier;
        public Aventurier avCourant;
        public int action = 0;
        
        public Controleur(){
            this.grille = new Grille();
            for(int i=1; i<=4; i++){
                joueurs.add(new Aventurier(grille.getTuile(i, 3)));
            }
            
            for(Aventurier a : joueurs){
                System.out.println(a.getTuile().getX());
                System.out.println(a.getTuile().getY() + "\n");
            }
        }

	public void ajouterAction() {
		throw new UnsupportedOperationException();
	}

	public void assecherTuileCourante() {
		throw new UnsupportedOperationException();
	}

	public void actionAutre() {
		throw new UnsupportedOperationException();
	}

	public void joueurSuivant() {
		throw new UnsupportedOperationException();
	}

	public void deplacerAventurier(String aNomTuile, Aventurier aAv) {
		throw new UnsupportedOperationException();
	}

	public void getAventurierCourant() {
		throw new UnsupportedOperationException();
	}

	public void assecherTuile(Aventurier aAv) {
		throw new UnsupportedOperationException();
	}
}