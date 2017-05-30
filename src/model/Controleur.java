package model;

import java.util.ArrayList;
import java.util.Scanner;

public class Controleur {
	public ArrayList<Carte> cartes = new ArrayList<>();
	public ArrayList<Aventurier> joueurs = new ArrayList<>();
	public Grille grille;
	public ArrayList<Tresor> tresors = new ArrayList<>();
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
            avCourant = joueurs.get(2);
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
            Scanner input = new Scanner(System.in);
            //On récupère les tuiles asséchables
            ArrayList<Tuile> tuilesAssechables = avCourant.getTuilesAssechables();
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
}