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
    private static final int NIVEAU_EAU_MAX = 10;

    public IHMBonne vueAventurier;
    public Aventurier avCourant;
    public int action = 0;
    public static final int ACTION_NEXT_TOUR = 3;
    public static final int ACTION_NEXT_TOUR_NAVIGATEUR = 4;
    private boolean doubleAssechement = false;
    private static final Point SPAWN_EXPLORATEUR = new Point(4, 2);
    private static final Point SPAWN_NAVIGATEUR = new Point(3, 1);
    private static final Point SPAWN_INGENIEUR = new Point(3, 0);
    private static final Point SPAWN_PLONGEUR = new Point(2, 1);
    private static final Point SPAWN_PILOTE = new Point(3, 2);
    private static final Point SPAWN_MESSAGER = new Point(1, 2);

    private int operationEnCours = OPERATION_AUCUNE;

    private boolean deplacementObligatoire;
    public String pktnul = ""; // Sert à afficher pourquoi on a perdu la partie

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
        /*grille.setTuile(3, 0, Tuile.ETAT_TUILE_INONDEE);

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

        grille.setTuile(3, 5, Tuile.ETAT_TUILE_INONDEE);*/
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

        //Tirage des cartes inondation de début de partie
        for (int i = 1; i <= 6; i++) {
            CarteInondation c = cartesInondation.tirerCarte();
            System.out.println("Carte tirée : " + c.getTuileConcernee());
            Tuile t = grille.getTuile(c.getTuileConcernee());
            t.mouillerTuile();
            vueAventurier.setEtatTuile(t.getEtatTuile(), t.getX(), t.getY());
            cartesInondation.defausserCarte(c);
        }

    }

    /**
     * Ajoute une action utilisée au joueur courant Si il atteint le nombre
     * d'actions maximal, la fonction joueurSuivant est appelée
     */
    public void ajouterAction() {
        action += 1;
        System.out.println(doubleAssechement);
        if (action >= ACTION_NEXT_TOUR && !doubleAssechement) {
            finTour();
        }
        this.vueAventurier.setNbAct(ACTION_NEXT_TOUR - action);
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
    

    /**
     * Déplace l'aventurier courant sur la tuile nvTuile depuis une autre
     * position
     *
     * @param nvTuile Tuile où déplacer l'aventurier
     */
    public void deplacerAventurierCourant(Tuile nvTuile) {
        avCourant.deplacement(nvTuile, this.grille);
        this.vueAventurier.actualiseAventuriers();
        this.vueAventurier.disableBoutons();
        this.operationEnCours = OPERATION_AUCUNE;
        this.ajouterAction();
    }

    /**
     * Termine le tour de l'aventurier courant, et change d'aventurier
     */
    public void finTour() {
        doubleAssechement = false;
        tirerCartesTresor();

        tirerCartesInondation();
        if (gameOver()) {
            afficherInformation("Partie perdue : " + pktnul);
        } else {
            joueurSuivant();
            avCourant.setPouvoirDispo(true);
            //Si avCourant est sur une tuile inondée, on le déplace d'office
            if (avCourant.tuileCourante.getEtatTuile() == Tuile.ETAT_TUILE_COULEE) {
                vueAventurier.disableInteraction();
                deplacementObligatoire = true;
                this.operationEnCours = OPERATION_DEPLACEMENT;
                this.traiterBoutonAller();
                deplacementObligatoire = false;
            }
        //}
        }
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
        this.vueAventurier.setNbAct(ACTION_NEXT_TOUR-action);
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
     * Logique gérant le déplacement d'un aventurier sur la grille
     */
    private void traiterBoutonAller() {
        ArrayList<Tuile> tuilesPossibles = avCourant.getDeplacementsPossibles(this.grille);
        for (Tuile t : tuilesPossibles) {
            vueAventurier.enable(t.getX(), t.getY());
        }
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
                System.out.println("Montee des eaux !");
            } else {
                //Ajout de la carte au deck du joueur
                avCourant.ajouterCarte(c);
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {

        Message m = (Message) arg;
        vueAventurier.disableBoutons();
        switch (m.type) {
            case CLIC_BTN_ALLER:
                this.traiterBoutonAller();
                this.operationEnCours = OPERATION_DEPLACEMENT;
                break;
            case CLIC_BTN_ASSECHER:
                this.traiterAssechement();
                //assecherTuile();
                this.operationEnCours = OPERATION_ASSECHER;
                break;
            case CLIC_BTN_AUTRE_ACTION:
                afficherInformation("Cette fonctionnalité est en chantier ! Merci de revenir plus tard.");
                break;
            case CLIC_BTN_TERMINER_TOUR:
                this.operationEnCours = OPERATION_AUCUNE;
                vueAventurier.disableBoutons();
                this.finTour();
                break;
            case CLIC_CASE:
                this.traiterClicCase(m.x, m.y);
                vueAventurier.enableInteraction();
                break;
        }

    }

    private void tirerCartesInondation() {
        int nbCartes = nbCartesInondation();
        for (int i = 1; i <= nbCartes; i++) {
            CarteInondation c = cartesInondation.tirerCarte();
            System.out.println("Carte tirée : " + c.getTuileConcernee());
            Tuile t = grille.getTuile(c.getTuileConcernee());
            t.mouillerTuile();
            vueAventurier.setEtatTuile(t.getEtatTuile(), t.getX(), t.getY());
            if (t.getEtatTuile() == Tuile.ETAT_TUILE_INONDEE) {
                cartesInondation.defausserCarte(c);
            } else if (t.getEtatTuile() == Tuile.ETAT_TUILE_COULEE) {

            }
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
        if (operationEnCours == OPERATION_DEPLACEMENT) {
            this.deplacerAventurierCourant(grille.getTuile(x, y));
            if (deplacementObligatoire == true) {
                this.ajouterAction();
            }
        } else if (operationEnCours == OPERATION_ASSECHER) {
            this.assecherTuile(x, y);
            this.vueAventurier.actualiseTuiles();
        }
    }

    //Défausse automatique tant que le joueur a trop de cartes
    private void defausse() {
        while (avCourant.getCartes().size() > 5) {
            CarteTresor c = avCourant.cartes.remove(avCourant.getCartes().size() - 1);
            cartesTresor.defausserCarte(c);
            System.out.println("Défaussé : une carte");
        }
    }

    /**
     * Retourne vrai si : - l'héliport est coulé - les deux tuiles d'une même
     * relique est inondée sans qu'un joueur ait déjà récupéré la relique - une
     * tuile sombre alors qu'un joueur est dessus et qu'il ne peut pas se
     * déplacer - le niveau de l'eau arrive au max
     */
    private boolean gameOver() {
        if (heliportMort()
                || pierreSacreeMort()
                || statueZephyrMort()
                || cristalArdentMort()
                || caliceOndeMort()
                || aventurierMort()
                || eauMax()) {
            return true;
        }
        return false;
    }

    private void traiterAssechement() {
        ArrayList<Tuile> tuilesAssechables = avCourant.getTuilesAssechables(this.grille);
        for (Tuile t : tuilesAssechables) {
            vueAventurier.enable(t.getX(), t.getY());
        }
    }

    private void assecherTuile(int x, int y) {

        //On récupère les tuiles asséchables depuis le joueur
        ArrayList<Tuile> tuilesAssechables = avCourant.getTuilesAssechables(grille);
        //On quitte si l'arraylist est vide, sinon on continue
        if (!tuilesAssechables.isEmpty()) {
            //TODO - Assecher transféré dans aventurier + ajout action
            boolean assechee = avCourant.assecher(tuilesAssechables, grille, x, y);
            if (assechee) {
                if (avCourant.getType().equalsIgnoreCase("Ingenieur")) {
                    //On teste si il en est à son premier ou deuxième asséchement
                    if (!doubleAssechement) {
                        //Premier asséchement
                        afficherInformation("Vous pouvez assécher une deuxième tuile sans consommer d'action.");
                        doubleAssechement = true;
                        ajouterAction();
                    } else {
                        //Deuxième
                        afficherInformation("Vous avez asséché deux tuiles pour 1 action.");
                        doubleAssechement = false;
                        if (action >= ACTION_NEXT_TOUR) {
                            finTour();
                        }
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

    private boolean heliportMort() {
        if (grille.getTuile("Heliport").getEtatTuile() == Tuile.ETAT_TUILE_COULEE) {
            if (pktnul.equals("")) {
                pktnul = "L'héliport a coulé";
            } else {
                pktnul = pktnul + " ; L'héliport a coulé";
            }
            return true;
        } else {
            return false;
        }
    }
    
   

    private boolean pierreSacreeMort() {
        if ((grille.getTuile("Le Temple de La Lune").getEtatTuile() == Tuile.ETAT_TUILE_COULEE
                && grille.getTuile("Le Temple du Soleil").getEtatTuile() == Tuile.ETAT_TUILE_COULEE) /* et les joueurs n'ont pas le trésor */) {
            if (pktnul.equals("")) {
                pktnul = "Vous ne pouvez plus obtenir la Pierre Sacrée";
            } else {
                pktnul = pktnul + " ; Vous ne pouvez plus obtenir la Pierre Sacrée";
            }
            return true;
        } else {
            return false;
        }
    }

    private boolean statueZephyrMort() {
        if ((grille.getTuile("Le Jardin des Murmures").getEtatTuile() == Tuile.ETAT_TUILE_COULEE
                && grille.getTuile("Le Jardin des Hurlements").getEtatTuile() == Tuile.ETAT_TUILE_COULEE) /* et les joueurs n'ont pas le trésor */) {
            if (pktnul.equals("")) {
                pktnul = "Vous ne pouvez plus obtenir la Statue du Zéphyr";
            } else {
                pktnul = pktnul + " ; Vous ne pouvez plus obtenir la Statue du Zéphyr";
            }
            return true;
        } else {
            return false;
        }
    }

    private boolean cristalArdentMort() {
        if ((grille.getTuile("La Caverne du Brasier").getEtatTuile() == Tuile.ETAT_TUILE_COULEE
                && grille.getTuile("La Caverne des Ombres").getEtatTuile() == Tuile.ETAT_TUILE_COULEE) /* et les joueurs n'ont pas le trésor */) {
            if (pktnul.equals("")) {
                pktnul = "Vous ne pouvez plus obtenir le Cristal Ardent";
            } else {
                pktnul = pktnul + " ; Vous ne pouvez plus obtenir le Cristal Ardent";
            }
            return true;
        } else {
            return false;
        }
    }

    private boolean caliceOndeMort() {
        if ((grille.getTuile("Le Palais de Corail").getEtatTuile() == Tuile.ETAT_TUILE_COULEE
                && grille.getTuile("Le Palais des Marees").getEtatTuile() == Tuile.ETAT_TUILE_COULEE) /* et les joueurs n'ont pas le trésor */) {
            if (pktnul.equals("")) {
                pktnul = "Vous ne pouvez plus obtenir le Calice de l'Onde";
            } else {
                pktnul = pktnul + " ; Vous ne pouvez plus obtenir le Calice de l'Onde";
            }
            return true;
        } else {
            return false;
        }
    }

    private boolean aventurierMort() {
        boolean mort = false;
        Tuile tuileCourante;
        ArrayList<Tuile> tuilesPossibles = new ArrayList<>();
        for (Aventurier a : joueurs) {
            tuileCourante = a.getTuile();
            if (tuileCourante.getEtatTuile() == Tuile.ETAT_TUILE_COULEE) {
                tuilesPossibles = a.getDeplacementsPossibles(grille);
                if (tuilesPossibles.size() == 0) {
                    mort = true;
                    if (pktnul.equals("")) {
                        pktnul = "Un aventurier est décédé";
                    } else {
                        pktnul = pktnul + " ; Un aventurier est décédé";
                    }
                }
            }
        }
        return mort;
    }
    
    
    

    private boolean eauMax() {
        if (cranMarqueurNiveau == NIVEAU_EAU_MAX) {
            if (pktnul.equals("")) {
                pktnul = "L'île a été submergée";
            } else {
                pktnul = pktnul + " ; L'île a été submergée";
            }
            return true;
        } else {
            return false;
        }
    }

}
