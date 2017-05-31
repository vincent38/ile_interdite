package model;

import java.awt.Color;
import java.util.ArrayList;

import java.util.Scanner;
import static util.Utils.*;

import view.VueAventurier;




public class Controleur implements Observateur{
	public ArrayList<Carte> cartes = new ArrayList<>();
	public ArrayList<Aventurier> joueurs = new ArrayList<>();
	public Grille grille;
	public ArrayList<Tresor> tresors = new ArrayList<>();

	public VueAventurier vueAventurier;
        public Aventurier avCourant;
        public int action = 0;
        public static final int ACTION_NEXT_TOUR = 3;

        public Controleur(){
            
            
            
            

            this.grille = new Grille();
            for(int i=1; i<=4; i++){
                joueurs.add(new Aventurier(grille.getTuile(i, 3), "Aventurier" + i));
            }
            //avCourant = joueurs.get(0);
           
            avCourant = joueurs.get(2);  
            System.out.println("x avCourant : " + avCourant.getTuile().getX());
            System.out.println("y avCourant : " + avCourant.getTuile().getY());
            System.out.println("Actions : " + this.getAction());
            this.vueAventurier = new VueAventurier(this.avCourant.getNom(), "jano", Color.blue);
            this.vueAventurier.setObservateur(this);
            vueAventurier.setPosition("X : " + this.avCourant.getTuile().getX() + " Y : " + this.avCourant.getTuile().getY());
            
            
            
 /*         for(Aventurier a : joueurs){
                System.out.println(a.getTuile().getX());
                System.out.println(a.getTuile().getY() + "\n");
            }
            avCourant = joueurs.get(1);
            assecherTuile(avCourant);
            }*/
            grille.setTuile(2, 3, Tuile.ETAT_TUILE_INONDEE);
            //assecherTuile(avCourant);
        }

	public void ajouterAction() {
            action += 1;
            if(action == ACTION_NEXT_TOUR){
                this.joueurSuivant();
            }
	}
        
        public int getAction(){
            return action;
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
        
        public void addAventurier(Aventurier av){
            this.joueurs.add(av);
        }

	public void assecherTuile(Aventurier aAv) {
            Scanner input = new Scanner(System.in);
            //On récupère la tuile courante de l'aventurier
            Tuile aTuile = avCourant.getTuile();
            //On récupère les tuiles asséchables
            ArrayList<Tuile> tuilesAssechables = grille.getTuilesNonSeches(aTuile);
            //On quitte si l'arraylist est vide, sinon on continue
            if (!tuilesAssechables.isEmpty()) {
                //On les affiche
                String tuilesAssechablesMessageGenerator = "Les tuiles suivantes sont asséchables : \n";
                for(Tuile t : tuilesAssechables) { 
                    tuilesAssechablesMessageGenerator += t.getX()+" - "+t.getY()+"\n";
                }
                vueAventurier.setPosition(tuilesAssechablesMessageGenerator);
                //On demande la tuile à assécher au joueur - A EDITER
                System.out.println("X : ");
                int x = input.nextInt();
                System.out.println("Y : ");
                int y = input.nextInt();
                //Fin d'edit
                Tuile myTuile = new Tuile(x, y);
                //On vérifie si elle existe. Existe -> on assèche la tuile
                for(Tuile t : tuilesAssechables) {
                    if (t.getX() == myTuile.getX() && t.getY() == myTuile.getY()) {
                        avCourant.assecher(myTuile, grille);
                        System.out.println(myTuile.getEtatTuile());
                        ajouterAction();
                        break;
                    }
                }
            } else {
                afficherInformation("Il n'y a aucune tuile à assécher.");
            }
            
	}
        
        public void deplacerAventurierCourant(Tuile nvTuile){
            avCourant.deplacement(nvTuile);
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
            this.action = 0;
            this.vueAventurier.setWindowTitle(avCourant.getNom());
	}
        
        public int getNumJoueur(Aventurier j) {
            int i = 0;
            boolean trouve = false;
            while ((i < joueurs.size()) && !trouve ) { // Recherche du joueur dans l'AL
                j = joueurs.get(i);
                if (j.equals(avCourant)) {
                    trouve = true;
                } else {
                    i += 1;
                }
            }
            return i;
        }

    @Override
    public void traiterMessage(Message m) {
        switch(m){
            case CLIC_BTN_ALLER:
                this.traiterBoutonAller();
                break;
            case CLIC_BTN_ASSECHER:
                assecherTuile(avCourant);
                break;
            case CLIC_BTN_AUTRE_ACTION:
                break;
            case CLIC_BTN_TERMINER_TOUR:
                this.finTour();
                break;
        }
    }

    private void traiterBoutonAller() {
        ArrayList<Tuile> tuilesPossibles = avCourant.getDeplacementsPossibles(this.grille);
                for (Tuile t : tuilesPossibles){
                    System.out.println("x : " + t.getX());
                    System.out.println("y : " + t.getY() + '\n');
                }
                Scanner clavier = new Scanner(System.in);
                System.out.print("selectionner X : ");
                int xVoulu = clavier.nextInt();
                System.out.print("selectionner Y : ");
                int yVoulu = clavier.nextInt();
                Tuile tuileV = grille.getTuile(xVoulu, yVoulu);
                if (tuilesPossibles.contains(tuileV)){
                    avCourant.deplacement(tuileV);
                    this.ajouterAction();
                }
                else{
                    System.out.println("deplacement impossible, deso frr");
                }
                System.out.println("x avCourant : " + avCourant.getTuile().getX());
                System.out.println("y avCourant : " + avCourant.getTuile().getY());
                System.out.println("Actions : " + this.getAction());
                vueAventurier.setPosition("X : " + this.avCourant.getTuile().getX() + " Y : " + this.avCourant.getTuile().getY());
    }
}