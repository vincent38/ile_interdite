package model;

import java.util.ArrayList;
import java.util.Vector;
import view.*;
import view.VueAventurier;

public class Controleur {
	public ArrayList<Carte> cartes = new ArrayList<>();
	public ArrayList<Aventurier> joueurs = new ArrayList<>();
	public Grille grille;
	public ArrayList<Tresor> tresors = new ArrayList<>();

	public VueAventurier vueAventurier;
        public Aventurier avCourant;
        public int action = 0;

        public Controleur(){
            avCourant = joueurs.get(0);
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
        
        public ArrayList<Tuile> getTuilesAdjacentes(Aventurier av){
            ArrayList<Tuile> r = new ArrayList();
            Tuile tuileC = av.getTuile();
            
            if(grille.getTuile(tuileC.getX() - 1, tuileC.getY()).getEtatTuile() != Tuile.ETAT_TUILE_COULEE)
                r.add(grille.getTuile(tuileC.getX() - 1, tuileC.getY() - 1));
            
            if (grille.getTuile(tuileC.getX(), tuileC.getY() - 1).getEtatTuile() != Tuile.ETAT_TUILE_COULEE)
                r.add(grille.getTuile(tuileC.getX(), tuileC.getY() - 1));
            
            if (grille.getTuile(tuileC.getX() + 1, tuileC.getY()).getEtatTuile() != Tuile.ETAT_TUILE_COULEE)
                r.add(grille.getTuile(tuileC.getX() + 1, tuileC.getY()));
                
            if (grille.getTuile(tuileC.getX(), tuileC.getY() + 1).getEtatTuile() != Tuile.ETAT_TUILE_COULEE)
                r.add(grille.getTuile(tuileC.getX(), tuileC.getY() + 1));
            
            return r;
        }
            
        public void finTour() {
            passerJoueurSuivant();
        }
        
        private void passerJoueurSuivant() {
            int nbJoueurs = joueurs.size();
            if (avCourant.equals(joueurs.get(joueurs.size()))) { // Si le joueur courant est le dernier de l'AL
                avCourant = joueurs.get(0);
            } else {
                int i = 0;
                boolean trouve = false;
                Aventurier j;
                while ((i < joueurs.size()) && !trouve ) { // Recherche du joueur dans l'AL
                    j = joueurs.get(i);
                    if (j.equals(avCourant)) {
                        trouve = true;
                    } else {
                        i =+ 1;
                    }
                }
                avCourant = joueurs.get(i+1);
            }
        }
}