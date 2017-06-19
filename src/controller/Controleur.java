package controller;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import java.util.Scanner;
import model.aventurier.Aventurier;
import model.carte.Carte;
import model.aventurier.Explorateur;
import model.Grille;
import model.aventurier.Ingenieur;
import model.Message;
import model.aventurier.Messager;
import model.aventurier.Navigateur;
import model.Observateur;
import model.aventurier.Pilote;
import model.aventurier.Plongeur;
import model.Tresor;
import model.Tuile;
import model.carte.CarteTresor;
import model.carte.DeckCartesInondation;
import model.carte.DeckCartesTresor;
import static util.Utils.*;
import view.IHM;
import view.IHMBonne;

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
    
    public DeckCartesTresor cartesTresor = new DeckCartesTresor();
    public DeckCartesInondation cartesInondation = new DeckCartesInondation();
    
    public int cranMarqueurNiveau;
    public int banane;

    public IHMBonne vueAventurier;
    public Aventurier avCourant;
    public int action = 0;
    public static final int ACTION_NEXT_TOUR = 3;
    private boolean doubleAssechement = false;
    private static final Point SPAWN_EXPLORATEUR = new Point(5, 3);
    private static final Point SPAWN_NAVIGATEUR = new Point(4, 2);
    private static final Point SPAWN_INGENIEUR = new Point(4, 1);
    private static final Point SPAWN_PLONGEUR = new Point(3, 2);
    private static final Point SPAWN_PILOTE = new Point(4, 3);
    private static final Point SPAWN_MESSAGER = new Point(2, 3);

    /**
     * Instancie un Controleur qui sert de classe principale. Gère la logique du
     * jeu, ainsi que les appels aux vues.
     */
    public Controleur() {
        //Initialisation de la grille
        this.grille = new Grille();

        //Création et placement des joueurs
        joueurs.add(new Explorateur(grille.getTuile((int) SPAWN_EXPLORATEUR.getX(), (int) SPAWN_EXPLORATEUR.getY()), "Jano"));
        joueurs.add(new Messager(grille.getTuile((int) SPAWN_MESSAGER.getX(), (int) SPAWN_MESSAGER.getY()), "Jul"));
        joueurs.add(new Ingenieur(grille.getTuile((int) SPAWN_INGENIEUR.getX(), (int) SPAWN_INGENIEUR.getY()), "Vincent"));
        joueurs.add(new Plongeur(grille.getTuile((int) SPAWN_PLONGEUR.getX(), (int) SPAWN_PLONGEUR.getY()), "Clement"));
        joueurs.add(new Pilote(grille.getTuile((int) SPAWN_PILOTE.getX(), (int) SPAWN_PILOTE.getY()), "Et mille"));
        joueurs.add(new Navigateur(grille.getTuile((int) SPAWN_NAVIGATEUR.getX(), (int) SPAWN_NAVIGATEUR.getY()), "Henrie"));

        //Définition de l'aventurier courant
        avCourant = joueurs.get(0);

        //Affichage des informations
        this.vueAventurier = new IHMBonne(this, joueurs.get(0), 3);
        //this.vueAventurier.setObservateur(this);
        //vueAventurier.setPosition("X : " + this.avCourant.getTuile().getX() + " Y : " + this.avCourant.getTuile().getY() + " - " + avCourant.getTuile().getNom() + " - Action(s) restante(s) : " + (getACTION_NEXT_TOUR() - getAction()));
        //this.vueAventurier.setColor(avCourant.getColor());
        //this.vueAventurier.setFontColor(avCourant.getFontColor());
        //Définition des tuiles inondées et coulées en dur
        grille.setTuile(4, 1, Tuile.ETAT_TUILE_INONDEE);

        grille.setTuile(3, 3, Tuile.ETAT_TUILE_INONDEE);
        grille.setTuile(3, 3, Tuile.ETAT_TUILE_COULEE);

        grille.setTuile(2, 4, Tuile.ETAT_TUILE_INONDEE);

        grille.setTuile(3, 4, Tuile.ETAT_TUILE_INONDEE);
        grille.setTuile(3, 4, Tuile.ETAT_TUILE_COULEE);

        grille.setTuile(4, 4, Tuile.ETAT_TUILE_INONDEE);

        grille.setTuile(5, 4, Tuile.ETAT_TUILE_INONDEE);
        grille.setTuile(5, 4, Tuile.ETAT_TUILE_COULEE);

        grille.setTuile(6, 4, Tuile.ETAT_TUILE_INONDEE);

        grille.setTuile(3, 5, Tuile.ETAT_TUILE_INONDEE);
        grille.setTuile(3, 5, Tuile.ETAT_TUILE_COULEE);

        grille.setTuile(4, 6, Tuile.ETAT_TUILE_INONDEE);
        
        //Définition du marqueur de niveau
        cranMarqueurNiveau = 0;

        //Distribution initiale des cartes
        for (Aventurier a : joueurs) {
            for (int i = 1; i <= 2; i++) {
                CarteTresor c = cartesTresor.tirerCarte();
                if ("montee_eaux".equals(c.getTypeCarte())) {
                    //Sélectionner une autre carte et replacer la carte précédente
                    CarteTresor d = cartesTresor.tirerCarte();
                    while ("montee_eaux".equals(d.getTypeCarte())) {
                        CarteTresor e = cartesTresor.tirerCarte();
                        cartesTresor.replacerDansLaPile(d);
                        d = e;
                    } 
                    a.ajouterCarte(d);
                    cartesTresor.replacerDansLaPile(c);
                    cartesTresor.shuffleCards();
                } else {
                    //Ajout de la carte au deck du joueur
                    a.ajouterCarte(c);
                }
            }
        }
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
     * Retourne le nombre d'actions par tour
     *
     * @return ACTION_NEXT_TOUR
     */
    public static int getACTION_NEXT_TOUR() {
        return ACTION_NEXT_TOUR;
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
    public void deplacerAventurier(String aNomTuile, Aventurier aAv) {
        throw new UnsupportedOperationException();
    }


    /**
     * Propose au joueur une liste de tuiles à assécher, et assèche la tuile
     * choisie. Retourne un message d'erreur si la fonction n'est pas possible.
     */
    public void assecherTuile() {
        Scanner input = new Scanner(System.in);

        //On récupère les tuiles asséchables depuis le joueur
        ArrayList<Tuile> tuilesAssechables = avCourant.getTuilesAssechables(grille);
        //On quitte si l'arraylist est vide, sinon on continue
        if (!tuilesAssechables.isEmpty()) {

            //On les affiche
            System.out.println("Les tuiles suivantes sont asséchables : ");
            for (Tuile t : tuilesAssechables) {
                System.out.println("x : " + t.getX());
                System.out.println("y : " + t.getY());
                System.out.println(t.getNom() + '\n');
            }

            //On demande la tuile à assécher au joueur - A EDITER
            System.out.println("X : ");
            int x = input.nextInt();
            System.out.println("Y : ");
            int y = input.nextInt();
            //Fin d'edit

            //TODO - Assecher transféré dans aventurier + ajout action
            boolean assechee = avCourant.assecher(tuilesAssechables, grille, x, y);
            if (assechee) {
                if (avCourant.getType().equalsIgnoreCase("Ingenieur")) {
                    //On teste si il en est à son premier ou deuxième asséchement
                    if (!doubleAssechement) {
                        //Premier asséchement
                        afficherInformation("Vous pouvez assécher une deuxième tuile sans consommer d'action.");
                        ajouterAction();
                        doubleAssechement = true;
                    } else {
                        //Deuxième
                        afficherInformation("Vous avez asséché deux tuiles pour 1 action.");
                        doubleAssechement = false;
                    }
                } else {
                    //Autre joueur, on ajoute une action
                    ajouterAction();
                }
            } else {
                afficherInformation("Vous ne pouvez pas assécher cette tuile.");
            }
//            this.vueAventurier.setPosition("X : " + this.avCourant.getTuile().getX() + " Y : " + this.avCourant.getTuile().getY() + " - " + avCourant.getTuile().getNom() + " - Action(s) restante(s) : " + (getACTION_NEXT_TOUR() - getAction()));
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
        doubleAssechement = false;
        tirerCartesTresor();
        tirerCartesInondation();
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
//        this.vueAventurier.setWindowTitle(avCourant.getNom());
//        this.vueAventurier.setTypeAv(avCourant.getClass().getSimpleName());
 //       this.vueAventurier.setPosition("X : " + this.avCourant.getTuile().getX() + " Y : " + this.avCourant.getTuile().getY() + " - " + avCourant.getTuile().getNom() + " - Action(s) restante(s) : " + (getACTION_NEXT_TOUR() - getAction()));
//        this.vueAventurier.setColor(avCourant.getColor());
//        this.vueAventurier.setFontColor(avCourant.getFontColor());
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
                afficherInformation("Cette fonctionnalité est en chantier ! Merci de revenir plus tard.");
                break;
            case CLIC_BTN_TERMINER_TOUR:
                this.finTour();
                break;
            case CLIC_BTN_DONNER_CARTE:
                this.traiterBoutonDonnerCarte();
                break;
        }
    }

    /**
     * Logique gérant le déplacement d'un aventurier sur la grille
     */
    private void traiterBoutonAller() {
        ArrayList<Tuile> tuilesPossibles = avCourant.getDeplacementsPossibles(this.grille);
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
        System.out.println("");
        Tuile tuileV = grille.getTuile(xVoulu, yVoulu);
        if (tuilesPossibles.contains(tuileV)) {
            avCourant.deplacement(tuileV, this.grille);
            this.ajouterAction();
        } else {
            afficherInformation("Le déplacement demandé est impossible.");
        }
        //System.out.println("x avCourant : " + avCourant.getTuile().getX());
        //System.out.println("y avCourant : " + avCourant.getTuile().getY());
        //System.out.println("Actions : " + this.getAction());
//       vueAventurier.setPosition("X : " + this.avCourant.getTuile().getX() + " Y : " + this.avCourant.getTuile().getY() + " - " + avCourant.getTuile().getNom() + " - Action(s) restante(s) : " + (getACTION_NEXT_TOUR() - getAction()));

    }
    
    private void traiterBoutonDonnerCarte() {
        Tuile tuileCourante = avCourant.getTuile();
        ArrayList<Aventurier> aventuriersMemeTuile = tuileCourante.getAventuriers();
        ArrayList<CarteTresor> cartesPossedees = avCourant.getCartesPossedees();
        IHMBonne.choisirDestinataireEtCarte(aventuriersMemeTuile, cartesPossedees);
    }
    
    //Tirer 2 cartes trésor à la fin du tour
    private void tirerCartesTresor(){
        for (int i = 1; i <= 2; i++) {
            CarteTresor c = cartesTresor.tirerCarte();
            if ("montee_eaux".equals(c.getTypeCarte())) {
                //Actions montée des eaux
                cranMarqueurNiveau++;
                cartesTresor.defausserCarte(c);
            } else {
                //Ajout de la carte au deck du joueur
                avCourant.ajouterCarte(c);
            }
        }
    }
    
    //Tirer des cartes inondation
    private void tirerCartesInondation(){
        
    }
}
