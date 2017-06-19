package controller;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import java.util.Scanner;
import model.aventurier.Aventurier;
import model.carte.Carte;
import model.aventurier.Explorateur;
import model.Grille;
import model.Message;
import model.aventurier.Ingenieur;
import model.TypeMessage;
import model.aventurier.Messager;
import model.aventurier.Navigateur;
import model.Observateur;
import model.aventurier.Pilote;
import model.aventurier.Plongeur;
import model.Tresor;
import model.Tuile;
import model.carte.CarteInondation;
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
public class Controleur implements Observer {
    
    public static final int OPERATION_AUCUNE = 0;
    public static final int OPERATION_DEPLACEMENT = 1;
    public static final int OPERATION_ASSECHER = 2;
    
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
    private static final Point SPAWN_EXPLORATEUR = new Point(4, 2);
    private static final Point SPAWN_NAVIGATEUR = new Point(3, 1);
    private static final Point SPAWN_INGENIEUR = new Point(3, 0);
    private static final Point SPAWN_PLONGEUR = new Point(2, 1);
    private static final Point SPAWN_PILOTE = new Point(3, 2);
    private static final Point SPAWN_MESSAGER = new Point(1, 2);
    
    private int operationEnCours = OPERATION_AUCUNE;

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
        //joueurs.add(new Pilote(grille.getTuile((int) SPAWN_PILOTE.getX(), (int) SPAWN_PILOTE.getY()), "Et mille"));
        //joueurs.add(new Navigateur(grille.getTuile((int) SPAWN_NAVIGATEUR.getX(), (int) SPAWN_NAVIGATEUR.getY()), "Henrie"));

        //Définition de l'aventurier courant
        avCourant = joueurs.get(0);

        //Affichage des informations
        
        //this.vueAventurier.setObservateur(this);
        //vueAventurier.setPosition("X : " + this.avCourant.getTuile().getX() + " Y : " + this.avCourant.getTuile().getY() + " - " + avCourant.getTuile().getNom() + " - Action(s) restante(s) : " + (getACTION_NEXT_TOUR() - getAction()));
        //this.vueAventurier.setColor(avCourant.getColor());
        //this.vueAventurier.setFontColor(avCourant.getFontColor());
        //Définition des tuiles inondées et coulées en dur
        grille.setTuile(3, 0, Tuile.ETAT_TUILE_INONDEE);

        grille.setTuile(2, 2, Tuile.ETAT_TUILE_INONDEE);
        grille.setTuile(2, 2, Tuile.ETAT_TUILE_COULEE);

        grille.setTuile(1, 3, Tuile.ETAT_TUILE_INONDEE);

        grille.setTuile(2, 3, Tuile.ETAT_TUILE_INONDEE);
        grille.setTuile(2, 3, Tuile.ETAT_TUILE_COULEE);

        grille.setTuile(3, 3, Tuile.ETAT_TUILE_INONDEE);

        grille.setTuile(4, 3, Tuile.ETAT_TUILE_INONDEE);
        grille.setTuile(4, 3, Tuile.ETAT_TUILE_COULEE);

        grille.setTuile(5, 3, Tuile.ETAT_TUILE_INONDEE);

        grille.setTuile(2, 4, Tuile.ETAT_TUILE_INONDEE);
        grille.setTuile(2, 4, Tuile.ETAT_TUILE_COULEE);

        grille.setTuile(3, 5, Tuile.ETAT_TUILE_INONDEE);
        
        this.vueAventurier = new IHMBonne(joueurs.get(0), 3, grille, joueurs);
        this.vueAventurier.addObserver(this);
        
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
        this.vueAventurier.setNbAct(ACTION_NEXT_TOUR-action);
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
     * boucle de jeu. public void deplacerAventurier(String aNomTuile,
     * Aventurier aAv) { throw new UnsupportedOperationException(); }
     *
     *
     * /**
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
            //this.vueAventurier.setPosition("X : " + this.avCourant.getTuile().getX() + " Y : " + this.avCourant.getTuile().getY() + " - " + avCourant.getTuile().getNom() + " - Action(s) restante(s) : " + (getACTION_NEXT_TOUR() - getAction()));
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
        int xAncien = avCourant.getTuile().getX();
        int yAncien = avCourant.getTuile().getY();
        avCourant.deplacement(nvTuile, this.grille);
        this.vueAventurier.deplacement(avCourant, xAncien, yAncien, nvTuile.getX(), nvTuile.getY());
        this.vueAventurier.disableBoutons();
        this.operationEnCours = OPERATION_AUCUNE;
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
        this.vueAventurier.setAventurier(avCourant);
        this.action = 0;
        defausse();
        //this.vueAventurier.setWindowTitle(avCourant.getNom());
        //this.vueAventurier.setTypeAv(avCourant.getClass().getSimpleName());
        //this.vueAventurier.setPosition("X : " + this.avCourant.getTuile().getX() + " Y : " + this.avCourant.getTuile().getY() + " - " + avCourant.getTuile().getNom() + " - Action(s) restante(s) : " + (getACTION_NEXT_TOUR() - getAction()));
        //this.vueAventurier.setColor(avCourant.getColor());
        //this.vueAventurier.setFontColor(avCourant.getFontColor());
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
    /*@Override
    public void traiterMessage(Object m) {
        switch (m.type) {
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
    }*/
    /**
     * Logique gérant le déplacement d'un aventurier sur la grille
     */
    private void traiterBoutonAller() {
        ArrayList<Tuile> tuilesPossibles = avCourant.getDeplacementsPossibles(this.grille);
        for (Tuile t : tuilesPossibles) {
            vueAventurier.enable(t.getX(), t.getY());
            System.out.println("X : " + t.getX());
            System.out.println("Y : " + t.getY());
            System.out.println();
        }
        System.out.println();
    }

    private void traiterBoutonDonnerCarte() {
        Tuile tuileCourante = avCourant.getTuile();
        ArrayList<Aventurier> aventuriersMemeTuile = tuileCourante.getAventuriers();
        ArrayList<CarteTresor> cartesPossedees = avCourant.getCartesPossedees();
        IHMBonne.choisirDestinataireEtCarte(aventuriersMemeTuile, cartesPossedees);
    }

    //Tirer 2 cartes trésor à la fin du tour
    private void tirerCartesTresor() {
        for (int i = 1; i <= 2; i++) {
            CarteTresor c = cartesTresor.tirerCarte();
            if ("montee_eaux".equals(c.getTypeCarte())) {
                //Actions montée des eaux
                cranMarqueurNiveau++;
                cartesInondation.shuffleDefausseCards();
                cartesInondation.fusionDecks();
                cartesTresor.defausserCarte(c);
            } else {
                //Ajout de la carte au deck du joueur
                avCourant.ajouterCarte(c);
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        Message m = (Message) arg;
        switch (m.type) {
            case CLIC_BTN_ALLER:
                this.traiterBoutonAller();
                this.operationEnCours = OPERATION_DEPLACEMENT;
                break;
            case CLIC_BTN_ASSECHER:
                assecherTuile();
                this.operationEnCours = OPERATION_ASSECHER;
                break;
            case CLIC_BTN_AUTRE_ACTION:
                afficherInformation("Cette fonctionnalité est en chantier ! Merci de revenir plus tard.");
                break;
            case CLIC_BTN_TERMINER_TOUR:
                this.finTour();
                break;
            case CLIC_CASE:
                this.traiterClicCase(m.x, m.y);
                break;
        }
    }

    private void tirerCartesInondation() {   
        int nbCartes = nbCartesInondation();
        for (int i = 1; i <= nbCartes; i++) {
            CarteInondation c = cartesInondation.tirerCarte();
            Tuile t = grille.getTuile(c.getCaseConcernee());
            t.mouillerTuile();
            vueAventurier.mouillerTuile(t.getX(), t.getY());
            cartesInondation.defausserCarte(c);
        }
    }

    //Calcul nombre de cartes inondation à tirer en fonction du cran de la règle
    private int nbCartesInondation() {
        switch (cranMarqueurNiveau) {
            case 1:
            case 2:
                return 2;
            case 3:
            case 4:
            case 5:
                return 3;
            case 6:
            case 7:
                return 4;
            case 8:
            case 9:
                return 5;
        }
        return 2;
    }

    private void traiterClicCase(int x, int y) {
        if(operationEnCours == OPERATION_DEPLACEMENT){
            this.deplacerAventurierCourant(grille.getTuile(x, y));
            this.ajouterAction();
        }
    }
    
    //Défausse automatique tant que le joueur a trop de cartes
    private void defausse() {
        while (avCourant.getCartes().size() > 5){
            CarteTresor c = avCourant.cartes.remove(avCourant.getCartes().size()-1);
            cartesTresor.defausserCarte(c);
            System.out.println("Défaussé : une carte");
        }
    }
}
