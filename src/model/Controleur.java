package model;

import java.awt.Color;
import java.util.ArrayList;

import java.util.Scanner;

import view.VueAventurier;


public class Controleur implements Observateur{
	public ArrayList<Carte> cartes = new ArrayList<>();
	public ArrayList<Aventurier> joueurs = new ArrayList<>();
	public Grille grille;
	public ArrayList<Tresor> tresors = new ArrayList<>();

	public VueAventurier vueAventurier;
        public Aventurier avCourant;
        public int action = 0;

        public Controleur(){
            
            
            this.vueAventurier = new VueAventurier("janot", "jano", Color.blue);
            

            this.grille = new Grille();
            for(int i=1; i<=4; i++){
                joueurs.add(new Aventurier(grille.getTuile(i, 3)));
            }
            avCourant = joueurs.get(0);
           
            avCourant = joueurs.get(2);
            assecherTuile(avCourant);
            
            
            
            this.vueAventurier.setObservateur(this);
            
 /*           for(Aventurier a : joueurs){
                System.out.println(a.getTuile().getX());
                System.out.println(a.getTuile().getY() + "\n");
            }
            avCourant = joueurs.get(1);
            assecherTuile(avCourant);
            }*/
            avCourant = joueurs.get(2);
            grille.setTuile(2, 3, Tuile.ETAT_TUILE_INONDEE);
            assecherTuile(avCourant);
        }

	public void ajouterAction() {
            action += 1;
	}

	public void assecherTuileCourante() {
		throw new UnsupportedOperationException();
	}

	public void actionAutre() {
		throw new UnsupportedOperationException();
	}

	public void deplacerAventurier(String aNomTuile, Aventurier aAv) {
		throw new UnsupportedOperationException();
	}

	public Aventurier getAventurierCourant() {
            return avCourant;
	}
        
        public ArrayList getAventuriers() {
            return joueurs;
        }

	public void assecherTuile(Aventurier aAv) {
            Scanner input = new Scanner(System.in);
            //On récupère la tuile courante de l'aventurier
            Tuile aTuile = avCourant.getTuile();
            //On récupère les tuiles asséchables
            ArrayList<Tuile> tuilesAssechables = grille.getTuilesNonSeches(aTuile);
            //On les affiche
            for(Tuile t : tuilesAssechables) {
                System.out.println(t.getX()+" - "+t.getY());
            }
            //On demande la tuile à assécher au joueur
            System.out.println("X : ");
            int x = input.nextInt();
            System.out.println("Y : ");
            int y = input.nextInt();
            Tuile myTuile = new Tuile(x, y);
            //On vérifie si elle existe. Existe -> on assèche la tuile
            for(Tuile t : tuilesAssechables) {
                if (t.getX() == myTuile.getX() && t.getY() == myTuile.getY()) {
                    System.out.println("Tuile trouvée");
                    avCourant.assecher(myTuile);
                    ajouterAction();
                    break;
                }
            }
	}
        
        public void deplacerAventurierCourant(Tuile nvTuile){
            avCourant.deplacement(nvTuile);
        }
        
        public ArrayList<Tuile> getTuilesAdjacentes(){
            ArrayList<Tuile> r = new ArrayList();
            Tuile tuileC = avCourant.getTuile();
            
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
            joueurSuivant();
        }
        
        public void joueurSuivant() {
            if (avCourant.equals(joueurs.get(joueurs.size()-1))) { // Si le joueur courant est le dernier de l'AL
                avCourant = joueurs.get(0);
            } else {
                avCourant = joueurs.get(getNumJoueur(avCourant)+1);
            }
	}
        
        public int getNumJoueur(Aventurier j) {
            int i = 0;
            boolean trouve = false;
            while ((i < joueurs.size()) && !trouve ) { // Recherche du joueur dans l'AL
                j = joueurs.get(i);
                if (j.equals(avCourant)) {
                    trouve = true;
                } else {
                    i =+ 1;
                }
            }
            return i;
        }

    @Override
    public void traiterMessage(Message m) {
        /*switch(m){
            case CLIC_BTN_ALLER:
                break;
            case CLIC_BTN_ASSECHER:
                break;
            case CLIC_BTN_AUTRE_ACTION:
                break;
            case CLIC_BTN_TERMINER_TOUR:
                break;
        }*/
        System.out.println('*');
    }
}