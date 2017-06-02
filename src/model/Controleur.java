package model;

import java.awt.Color;
import java.util.ArrayList;

import java.util.Scanner;
import static util.Utils.*;

import view.VueAventurier;

/*
JavaDoc provided

V0.1
 */
public class Controleur implements Observateur {

    public ArrayList<Carte> cartes = new ArrayList<>();
    public ArrayList<Aventurier> joueurs = new ArrayList<>();
    public Grille grille;
    public ArrayList<Tresor> tresors = new ArrayList<>();

    public VueAventurier vueAventurier;
    public Aventurier avCourant;
    public int action = 0;
    public static final int ACTION_NEXT_TOUR = 3;

    /**
     * Instancie un Controleur qui sert de classe principale. Gère la logique du
     * jeu, ainsi que les appels aux vues.
     */
    public Controleur() {
        this.grille = new Grille();
        joueurs.add(new Explorateur(grille.getTuile(3, 3), "Jano"));
        joueurs.add(new Messager(grille.getTuile(4, 3), "Jul"));
        joueurs.add(new Ingenieur(grille.getTuile(5, 3), "Vincent"));
        joueurs.add(new Plongeur(grille.getTuile(3, 4), "Clement"));
        joueurs.add(new Pilote(grille.getTuile(3, 4), "Et mille"));

        //avCourant = joueurs.get(0);
        avCourant = joueurs.get(2);
        //System.out.println("x avCourant : " + avCourant.getTuile().getX());
        //System.out.println("y avCourant : " + avCourant.getTuile().getY());
        System.out.println("Actions : " + this.getAction());
        this.vueAventurier = new VueAventurier(this.avCourant.getNom(), avCourant.getClass().getSimpleName(), Color.blue);
        this.vueAventurier.setObservateur(this);
        vueAventurier.setPosition("X : " + this.avCourant.getTuile().getX() + " Y : " + this.avCourant.getTuile().getY() + " - " + avCourant.getTuile().getNom());
        this.vueAventurier.setColor(avCourant.getColor());
        this.vueAventurier.setFontColor(avCourant.getFontColor());

        /*         for(Aventurier a : joueurs){
                System.out.println(a.getTuile().getX());
                System.out.println(a.getTuile().getY() + "\n");
            }
            avCourant = joueurs.get(1);
            assecherTuile(avCourant);
            }*/
        grille.setTuile(4, 3, Tuile.ETAT_TUILE_INONDEE);
        grille.setTuile(4, 2, Tuile.ETAT_TUILE_INONDEE);
        grille.setTuile(2, 3, Tuile.ETAT_TUILE_INONDEE);
        //grille.setTuile(2, 3, Tuile.ETAT_TUILE_COULEE);
        //assecherTuile(avCourant);

    }

    /**
     * Ajoute une action utilisée au joueur courant Si il atteint le nombre
     * d'actions maximal, la fonction joueurSuivant est appelée
     */
    public void ajouterAction() {
        action += 1;
        if (action == ACTION_NEXT_TOUR) {
            this.joueurSuivant();
        }
    }

    /**
     * Retourne le nombre d'actions effectuées par l'utilisateur.
     *
     * @return action
     */
    public int getAction() {
        return action;
    }

    /**
     * Méthode inutilisée actuellement. Gardée en prévision.
     */
    public void actionAutre() {
        throw new UnsupportedOperationException();
    }

    /**
     * Méthode inutilisée actuellement. Gardée en prévision.
     */
    public void deplacerAventurier(String aNomTuile, Aventurier aAv) {
        throw new UnsupportedOperationException();
    }

    /**
     * Retourne l'aventurier courant
     *
     * @return avCourant
     */
    public Aventurier getAventurierCourant() {
        return avCourant;
    }

    /**
     * Retourne une ArrayList d'aventuriers chargés dans le jeu.
     *
     * @return joueurs
     */
    public ArrayList getAventuriers() {
        return joueurs;
    }

    /**
     * Ajoute un aventurier à l'arrayList d'aventuriers, et l'intègre dans la
     * boucle de jeu.
     *
     * @param av Aventurier à ajouter
     */
    public void addAventurier(Aventurier av) {
        this.joueurs.add(av);
    }

    /**
     * Propose au joueur une liste de tuiles à assécher, et assèche la tuile
     * choisie. Retourne un message d'erreur si la fonction n'est pas possible.
     */
    public void assecherTuile() {
        Scanner input = new Scanner(System.in);
        //On récupère la tuile courante de l'aventurier
        Tuile aTuile = avCourant.getTuile();
        //On récupère les tuiles asséchables
        ArrayList<Tuile> tuilesAssechables = grille.getTuilesNonSeches(aTuile);
        //On quitte si l'arraylist est vide, sinon on continue
        if (!tuilesAssechables.isEmpty()) {
            //On les affiche
            String tuilesAssechablesMessageGenerator = "Les tuiles suivantes sont asséchables : \n";
            for (Tuile t : tuilesAssechables) {
                tuilesAssechablesMessageGenerator += "X : " + t.getX() + " - Y : " + t.getY() + " - Nom : " + t.getNom() + "\n";
            }
            System.out.println(tuilesAssechablesMessageGenerator);
            //On demande la tuile à assécher au joueur - A EDITER
            System.out.println("X : ");
            int x = input.nextInt();
            System.out.println("Y : ");
            int y = input.nextInt();
            //Fin d'edit
            Tuile myTuile = new Tuile(x, y);
            //On vérifie si elle existe. Existe -> on assèche la tuile
            for (Tuile t : tuilesAssechables) {
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

    /**
     * Déplace l'aventurier courant sur la tuile nvTuile depuis une autre
     * position
     *
     * @param nvTuile Tuile où déplacer l'aventurier
     */
    public void deplacerAventurierCourant(Tuile nvTuile) {
        avCourant.deplacement(nvTuile, this.grille);
    }

    /**
     * Termine le tour de l'aventurier courant, et change d'aventurier
     */
    public void finTour() {
        avCourant.traiterFinDeTour();
        joueurSuivant();
    }

    /**
     * Change d'aventurier courant en sélectionnant l'aventurier suivant dans la
     * boucle de jeu, et modifie la vue pour afficher ses propriétés (tuile
     * actuelle, classe, ...)
     */
    public void joueurSuivant() {
        if (avCourant.equals(joueurs.get(joueurs.size() - 1))) { // Si le joueur courant est le dernier de l'AL
            avCourant = joueurs.get(0);
        } else {
            avCourant = joueurs.get(getNumJoueur(avCourant) + 1);
        }
        this.action = 0;
        this.vueAventurier.setWindowTitle(avCourant.getNom());
        this.vueAventurier.setTypeAv(avCourant.getClass().getSimpleName());
        vueAventurier.setPosition("X : " + this.avCourant.getTuile().getX() + " Y : " + this.avCourant.getTuile().getY() + " - " + avCourant.getTuile().getNom());
    }

    /**
     * Récupère la position i d'un aventurier j dans l'arrayList joueurs
     *
     * @param j Aventurier recherché
     * @return i
     */
    public int getNumJoueur(Aventurier j) {
        int i = 0;
        boolean trouve = false;
        while ((i < joueurs.size()) && !trouve) { // Recherche du joueur dans l'AL
            j = joueurs.get(i);
            if (j.equals(avCourant)) {
                trouve = true;
            } else {
                i += 1;
            }
        }
        return i;
    }

    /**
     * Interprète les messages reçus de la vue VueAventurier
     *
     * @param m Message reçu
     * @see Observateur
     */
    @Override
    public void traiterMessage(Message m) {
        switch (m) {
            case CLIC_BTN_ALLER:
                this.traiterBoutonAller();
                break;
            case CLIC_BTN_ASSECHER:
                assecherTuile();
                break;
            case CLIC_BTN_AUTRE_ACTION:
                break;
            case CLIC_BTN_TERMINER_TOUR:
                this.finTour();
                break;
        }
    }

    /**
     * Logique gérant le déplacement d'un aventurier sur la grille
     */
    private void traiterBoutonAller() {
        ArrayList<Tuile> tuilesPossibles = avCourant.getDeplacementsPossibles(this.grille);
        System.out.println("Main");
        for (Tuile t : tuilesPossibles) {
            System.out.println("x : " + t.getX());
            System.out.println("y : " + t.getY());
            System.out.println(t.getNom() + '\n');
        }
        Scanner clavier = new Scanner(System.in);
        System.out.print("selectionner X : ");
        int xVoulu = clavier.nextInt();
        System.out.print("selectionner Y : ");
        int yVoulu = clavier.nextInt();
        Tuile tuileV = grille.getTuile(xVoulu, yVoulu);
        if (tuilesPossibles.contains(tuileV)) {
            avCourant.deplacement(tuileV, this.grille);
            this.ajouterAction();
        } else {
            System.out.println("deplacement impossible, deso frr");
        }
        System.out.println("x avCourant : " + avCourant.getTuile().getX());
        System.out.println("y avCourant : " + avCourant.getTuile().getY());
        System.out.println("Actions : " + this.getAction());
        vueAventurier.setPosition("X : " + this.avCourant.getTuile().getX() + " Y : " + this.avCourant.getTuile().getY() + " - " + avCourant.getTuile().getNom());

    }
}
