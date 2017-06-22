package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.Observer;
import model.aventurier.Aventurier;
import model.carte.Carte;
import model.Grille;
import model.Message;
import model.Tresor;
import model.Tuile;
import model.TypeTresor;
import model.aventurier.Explorateur;
import model.aventurier.Ingenieur;
import model.aventurier.Messager;
import model.aventurier.Navigateur;
import model.aventurier.Pilote;
import model.aventurier.Plongeur;
import model.carte.CarteBonus;
import model.carte.CarteInondation;
import model.carte.CartePiece;
import model.carte.CarteTresor;
import model.carte.DeckCartesInondation;
import model.carte.DeckCartesTresor;
import static util.Utils.*;
import view.IHMselectionJoueur;
import view.IHMBonne;
import view.IHMDefausse;
import view.IHMDonCarte;


/*
JavaDoc provided

V0.1
 */
public class Controleur implements Observer {

    public static final int OPERATION_AUCUNE = 0;
    public static final int OPERATION_DEPLACEMENT = 1;
    public static final int OPERATION_ASSECHER = 2;
    public static final int OPERATION_DONNER_CARTE = 3;
    public static final int OPERATION_HELICOPTERE = 4;
    public static final int OPERATION_SAC = 5;

    private ArrayList<String> specialisations = new ArrayList();
    private final ArrayList<Carte> cartes = new ArrayList<>();
    private final ArrayList<Aventurier> joueurs = new ArrayList<>();
    private final Grille grille;
    private ArrayList<Tresor> tresorsObtenus = new ArrayList();

    private final DeckCartesTresor cartesTresor = new DeckCartesTresor();
    private final DeckCartesInondation cartesInondation = new DeckCartesInondation();

    private int cranMarqueurNiveau;
    private static final int NIVEAU_EAU_MAX = 10;

    private IHMDefausse vueDefausse;
    private final IHMselectionJoueur vueSelection;
    private  IHMDonCarte vueDonCarte;
    private IHMBonne vueAventurier;
    private Aventurier avCourant;
    private int action;
    public static final int ACTION_NEXT_TOUR = 3;
    public static final int ACTION_NEXT_TOUR_NAVIGATEUR = 4;
    private boolean doubleAssechement = false;

    private static final String SPAWN_EXPLORATEUR = "La Porte de Cuivre";
    private static final String SPAWN_NAVIGATEUR = "La Porte d’Or";
    private static final String SPAWN_INGENIEUR = "La Porte de Bronze";
    private static final String SPAWN_PLONGEUR = "La Porte de Fer";
    private static final String SPAWN_PILOTE = "Heliport";
    private static final String SPAWN_MESSAGER = "La Porte d’Argent";
    private int operationEnCours = OPERATION_AUCUNE;

    private boolean deplacementObligatoire;
    
    public String pktnul; // Sert à afficher pourquoi on a perdu la partie
    

    /*private final String NOMS_SPECIALISATIONS[] = {
        "Ingenieur",
        "Messager",
        "Pilote",
        "Plongeur",
        "Navigateur",
        "Explorateur"
    };*/
     
    
    private ArrayList<String> nomsJoueurs = new ArrayList<>(); 

    /**
     * Instancie un Controleur qui sert de classe principale. Gère la logique du
     * jeu, ainsi que les appels aux vues.
     */
    public Controleur() {
        this.vueSelection = new IHMselectionJoueur();
        this.vueSelection.addObserver(this);
        
        specialisations.add("ingenieur");
        specialisations.add("naviguateur");
        specialisations.add("pilote");
        specialisations.add("explorateur");
        specialisations.add("plongeur");
        specialisations.add("messager");
        
        
        this.action = 0;
        //Initialisation de la grille
        this.grille = new Grille();

        //Création et placement des joueurs
        
        //joueurs.add(new Pilote(grille.getTuile(SPAWN_PILOTE), "Et mille"));
        //joueurs.add(new Navigateur(grille.getTuile(SPAWN_MESSAGER), "Henrie"));

        //Définition de l'aventurier courant
        

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

        //Définition du marqueur de niveau
        cranMarqueurNiveau = 0;

        //Distribution initiale des cartes
        


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
                this.traiterAssechement();
                //assecherTuile();
                this.operationEnCours = OPERATION_ASSECHER;
                break;
                
            case CLIC_BTN_DONNER_CARTE:
                //afficherInformation("Cette fonctionnalité est en chantier ! Merci de revenir plus tard.");
                this.operationEnCours = OPERATION_DONNER_CARTE;
                this.initDonCarte();
                this.afficherTresorsRamassables();
                break;
                
            case CLIC_BTN_ANNULER_DON_CARTE:
                this.operationEnCours = OPERATION_AUCUNE;
                vueDonCarte.cacherFenetre();
                vueAventurier.enableInteraction();
                break;
                
            case CLIC_BTN_VALIDER_DON_CARTE:
                if (m.getAventurier() == null || m.getCarte() == null) {
                    afficherInformation("Veuillez choisir un destinataire et une carte");
                } else {
                    this.traiterDonCarte(m.getAventurier(), m.getCarte());
                    vueDonCarte.cacherFenetre();
                    vueAventurier.enableInteraction();
                    vueAventurier.afficherCartes(avCourant.getCartesPossedees());
                }
                break;
                
            case CLIC_BTN_TERMINER_TOUR:
                this.operationEnCours = OPERATION_AUCUNE;
                vueAventurier.disableBoutons();
                this.finTour();
                this.afficherTresorsRamassables();
                
                break;
                
            case CLIC_CASE:
                if(this.operationEnCours == OPERATION_DEPLACEMENT){
                    this.traiterClicCase(m.x, m.y);
                    vueAventurier.enableInteraction();
                    this.afficherTresorsRamassables();
                }else{
                    this.traiterClicCase(m.x, m.y);
                }
                if (victoire()) {
                    afficherInformation("Bravo ! Vous avez réussi !");
                    vueAventurier.fermerFenetre();
                }
                break;

            case CLIC_COMMENCER:
                this.lancerJeu();
                break;

                
            case CLIC_BTN_TRESOR:

                this.traiterClicBoutonTresor();
                this.afficherTresorsRamassables();
                break;
            case CLIC_BTN_VALIDER_DEFAUSSE:
                this.defausse(m.carte);
                vueDefausse.fermerFenetre();
                vueAventurier.enableInteraction();
                this.defausse(); 
                break;
                
            case CLIC_HELICOPTERE:
                this.traiterClicHelicoptere();
                break;
                
            case CLIC_SAC_DE_SABLE:
                this.traiterClicSacDeSable();
                break;

        }
        if(avCourant.getType().equals("Navigateur")){
            if(this.action >= ACTION_NEXT_TOUR_NAVIGATEUR){
                this.finTour();
            } 
        } else { 
            if(this.action >= ACTION_NEXT_TOUR){
                this.finTour();
            }
        }
        this.updateVueAventurier();
        

    }

    /**
     * Ajoute une action utilisée au joueur courant Si il atteint le nombre
     * d'actions maximal, la fonction joueurSuivant est appelée
     */
    public void ajouterAction() {
        action += 1;
        System.out.println(doubleAssechement);
        if (avCourant.getType().equals("Navigateur")) {
            if (action >= ACTION_NEXT_TOUR_NAVIGATEUR) {
                finTour();
            }
        } else {
            if (action >= ACTION_NEXT_TOUR && !doubleAssechement) {
                finTour();
            }    
        }
        
        if(avCourant.getType().equals("Navigateur")) {
            this.vueAventurier.setNbAct(ACTION_NEXT_TOUR_NAVIGATEUR - action);
        } else {
            this.vueAventurier.setNbAct(ACTION_NEXT_TOUR - action);
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
    
    public void deplacerAventurierCourantGratuitement(Tuile nvTuile){
        if(avCourant.isPouvoirDispo()){
            avCourant.deplacement(nvTuile, this.grille);
            avCourant.setPouvoirDispo(true);
        }else{
            avCourant.deplacement(nvTuile, this.grille);
        }
        
        this.vueAventurier.actualiseAventuriers();
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
        if (gameOver()) {
            afficherInformation("Partie perdue : " + pktnul);
            vueAventurier.fermerFenetre();
        } else {
            if (victoire()) {
                afficherInformation("Bravo ! Vous avez réussi !");
                vueAventurier.fermerFenetre();
            } else {
                joueurSuivant();
                avCourant.setPouvoirDispo(true);
                this.vueAventurier.afficherCartes(avCourant.getCartesPossedees());
                //Si avCourant est sur une tuile inondée, on le déplace d'office
                if (avCourant.tuileCourante.getEtatTuile() == Tuile.ETAT_TUILE_COULEE) {
                    vueAventurier.disableInteraction();
                    deplacementObligatoire = true;
                    this.operationEnCours = OPERATION_DEPLACEMENT;
                    this.traiterBoutonAller();
                }
            }
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
        if(avCourant.getType().equals("Navigateur")) {
            this.vueAventurier.setNbAct(ACTION_NEXT_TOUR_NAVIGATEUR);
        } else {
            this.vueAventurier.setNbAct(ACTION_NEXT_TOUR-action);
        }
        System.out.println(avCourant.getType());
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
            CarteTresor c = cartesTresor.tirerCarte();
            if ("montee_eaux".equals(c.getTypeCarte())) {
                //Actions montée des eaux
                cranMarqueurNiveau++;
                this.vueAventurier.setNiveau(cranMarqueurNiveau);
                cartesInondation.shuffleDefausseCards();
                cartesInondation.fusionDecks();
                cartesTresor.defausserCarte(c);
                System.out.println("Montee des eaux !");
            } else {
                //Ajout de la carte au deck du joueur
                avCourant.ajouterCarte(c);
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
            if (deplacementObligatoire == false) {
            } else {
                deplacementObligatoire = false;
            }
        } else if (operationEnCours == OPERATION_ASSECHER) {
            this.assecherTuile(x, y);
            this.vueAventurier.actualiseTuiles();
        } else if (operationEnCours == OPERATION_HELICOPTERE){
            this.deplacerAventurierCourantGratuitement(grille.getTuile(x, y));
            this.removeCarteHelico();
        }else if (operationEnCours == OPERATION_SAC){
            this.assecherTuileGratuitement(x, y);
        }
        
        this.vueAventurier.disableBoutons();
    }

    //Défausse automatique tant que le joueur a trop de cartes
    private void defausse() {
        while (avCourant.getCartesPossedees().size() > 5) {
            this.afficherCartes();
            System.out.println("Defausse");
            vueDefausse = new IHMDefausse(avCourant.cartes);
            this.vueDefausse.addObserver(this);
            this.vueAventurier.disableBoutons();
            this.vueAventurier.disableInteraction();
            //CarteTresor c = avCourant.cartes.remove(avCourant.getCartes().size() - 1);
            //cartesTresor.defausserCarte(c);
            System.out.println("Défaussé : une carte");
        }
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
    
    /**
     * Retourne vrai si : - l'héliport est coulé - les deux tuiles d'une même
     * relique est inondée sans qu'un joueur ait déjà récupéré la relique - une
     * tuile sombre alors qu'un joueur est dessus et qu'il ne peut pas se
     * déplacer - le niveau de l'eau arrive au max
     */
    private boolean gameOver() {
        /*if (heliportMort()
                || pierreSacreeMort()
                || statueZephyrMort()
                || cristalArdentMort()
                || caliceOndeMort()
                || aventurierMort()
                || eauMax()) {
            return true;
        }*/
        return false;
    }

    private boolean heliportMort() {
        if (grille.getTuile("Heliport").getEtatTuile() == Tuile.ETAT_TUILE_COULEE) {
            if (pktnul == null) {
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
            if (pktnul == null) {
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
            if (pktnul == null) {
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
            if (pktnul == null) {
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
            if (pktnul == null) {
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
                    if (pktnul == null) {
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
        if (cranMarqueurNiveau >= NIVEAU_EAU_MAX) {
            if (pktnul == null) {
                pktnul = "L'île a été submergée";
            } else {
                pktnul = pktnul + " ; L'île a été submergée";
            }
            return true;
        } else {
            return false;
        }
    }
    
    private boolean victoire() {
        if (   tresorsObtenus()
            && quatreAventuriersSurHeliport()) {
            return true;
        } else {
            return false;
        }
    }
    
    private boolean tresorsObtenus() {
        return (tresorsObtenus.size() == 4);
    }
    
    private boolean quatreAventuriersSurHeliport() {
        boolean b = true;
        for (Aventurier aventurier : joueurs) {
            if (aventurier.getTuile().getNom() != "Heliport") {
                b = false;
            }
        }
        return b;
    }
    
    /*private void getTresorFromTuile(){
        //On teste la tuile en cours du joueur : trésor présent ?
        if (avCourant.getTuile().getTresor() != null) {
            TypeTresor typeTresorTuile = avCourant.getTuile().getTresor().getTypeTresor();
            //Trésor présent, on vérifie que le joueur aie assez de cartes
            ArrayList<CarteTresor> buffer = new ArrayList<>();
            //On parcourt ses cartes
            CarteTresor c = null;
            for (int i = 0; i < avCourant.getCartesPossedees().size(); i++) {
                c = avCourant.getCartesPossedees().remove(i);
                if ("tresor".equals(c.getTypeCarte())) {
                    CartePiece d = (CartePiece) c;
                    if (d.getTypeTresor() == typeTresorTuile) {
                        //Carte trésor correspondant au trésor visé ? Oui
                        buffer.add(c);
                    } else {
                        //Carte non correspondante. Retour joueur
                        avCourant.getCartesPossedees().add(c);
                    }
                }
            }
            //On les compte
            if (buffer.size() >= 4) {
                //Assez de cartes
                //Defausse + récupération trésor
                for (int j = 0; j < 4; j++){
                    cartesTresor.defausserCarte(buffer.remove(j));
                }
                avCourant.getCartesPossedees().addAll(buffer);
                this.addTresorsObtenus(avCourant.getTuile().getTresor());
                avCourant.getTuile().wipeTresor();
            } else {
                avCourant.getCartesPossedees().addAll(buffer);
                System.out.println("Lol nope");
            }
        }
    }*/

    private void initDonCarte() {
        Tuile tuileCourante = avCourant.getTuile();
        ArrayList<Aventurier> aventuriersMemeTuile = avCourant.getAventuriersDonPossible(joueurs);
        ArrayList<CartePiece> cartesPossedees = avCourant.getCartesPiecePossedees();
        CarteTresor carteADonner;
        Aventurier destinataire;
        if (aventuriersMemeTuile.size() == 0) {
            afficherInformation("Impossible : Il n'y a aucun aventurier à côté de vous.");
        } else {
            if (cartesPossedees.size() == 0) {
                afficherInformation("Impossible : Vous ne pouvez donner aucune carte.");
            } else {
                vueDonCarte = new IHMDonCarte(aventuriersMemeTuile, cartesPossedees);
                this.vueDonCarte.addObserver(this);
                vueAventurier.disableInteraction();
                vueDonCarte.afficherFenetre();
            }
        }
    }

    private void traiterDonCarte(Aventurier a, CartePiece c) {
        avCourant.retirerCarte(c);
        a.ajouterCarte(c);
    }
    
        



    private void traiterClicBoutonTresor() {
        Tresor tresor = avCourant.getTuile().getTresor();
        ArrayList<CartePiece> cartesPossedees = avCourant.getCartesPiecesPossedees();
        int nbCartesPieces = 0;
        for (CartePiece c : cartesPossedees) {
            if (c.getTypeTresor().equals(tresor.getTypeTresor())) {
                nbCartesPieces++;
            }
        }
        if (nbCartesPieces >= 4) {
            this.addTresorsObtenus(avCourant.getTuile().getTresor());
            this.vueAventurier.afficherTresor(avCourant.getTuile().getTresor());
            this.avCourant.getTuile().tresor = null;
            this.avCourant.rm4cartesPieces(tresor.getTypeTresor());
        } else {
            afficherInformation("Vous n'avez pas assez de cartes pour récupérer ce trésor.");
        }
    }

    private void addTresorsObtenus(Tresor tresor) {
        this.tresorsObtenus.add(tresor);
    }

    private void afficherTresorsRamassables() {
        Tresor t = avCourant.getTuile().getTresor();
        boolean tRamasse = false;
        
        this.vueAventurier.disableTresors();
        if(t != null){
            for(Tresor trO : this.tresorsObtenus){
                if (t.getTypeTresor() == trO.getTypeTresor()){
                    tRamasse = true;
                }
            }
            if(!tRamasse){
                this.vueAventurier.enable(t.getTypeTresor());
            }
        }
    }
    private void lancerJeu() {
        nomsJoueurs = vueSelection.getNomsJoueurs();
        for (int i = 0; i < nomsJoueurs.size(); i++){
            joueurs.add(specialisationAleatoire(nomsJoueurs.get(i)));
            //System.out.println(nomsJoueurs.get(i));
        }

            
            for (Aventurier a : joueurs) {
                for (int j = 1; j <= 2; j++) {
                    CarteTresor d = cartesTresor.tirerCarte();
                    while ("montee_eaux".equals(d.getTypeCarte())) {
                        //Sélectionner une autre carte et replacer la carte précédente
                            cartesTresor.replacerDansLaPile(d);
                            cartesTresor.shuffleCards();

                            d = cartesTresor.tirerCarte();

                    }
                        a.ajouterCarte(d);
                        cartesTresor.shuffleCards();
                        //Ajout de la carte au deck du joueur
            }
        }

        
        
        
        
        avCourant = joueurs.get(0);
        this.vueAventurier = new IHMBonne(joueurs.get(0), 3, grille, joueurs);
        this.vueAventurier.addObserver(this);
        
        vueSelection.fermerFenetre();
        vueAventurier.disableBoutons();
        tirerCarteDebut();
        
        
        //System.out.println(vueAventurier);
        //Tirage des cartes inondation de début de partie
        /*for (int j = 1; j <= 6; j++) {
            CarteInondation c = cartesInondation.tirerCarte();
            System.out.println("Carte tirée : " + c.getTuileConcernee());
            Tuile t = grille.getTuile(c.getTuileConcernee());
            t.mouillerTuile();
            vueAventurier.setEtatTuile(t.getEtatTuile(), t.getX(), t.getY());
            cartesInondation.defausserCarte(c);
        }*/
        
        
        
        this.afficherCartes();
        
        this.afficherTresorsRamassables();
    }
    
    /**
     * tire et innonde les tuiles de début de partie
     */

    private void tirerCarteDebut() {
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

    private Aventurier specialisationAleatoire(String nom) {
        Aventurier aventurier = null;
        String nomAve;
        
        System.out.println(nomsJoueurs.size());   
       
        shuffleSpe();
        nomAve = specialisations.get(0);
        //System.out.println(specialisations.get(0));
        specialisations.remove(0);


        if (nomAve.equals("ingenieur")){
            aventurier = new Ingenieur(grille.getTuile(SPAWN_INGENIEUR), nom);
        }
        else if (nomAve.equals("naviguateur")){
            aventurier = new Navigateur(grille.getTuile(SPAWN_NAVIGATEUR), nom);
        }
        else if (nomAve.equals("pilote")){
            aventurier = new Pilote(grille.getTuile(SPAWN_PILOTE), nom);
        }
        else if (nomAve.equals("explorateur")){
            aventurier = new Explorateur(grille.getTuile(SPAWN_EXPLORATEUR), nom);
        }
        else if (nomAve.equals("plongeur")){
            aventurier = new Plongeur(grille.getTuile(SPAWN_PLONGEUR), nom);
        }
        else if (nomAve.equals("messager")){
            aventurier = new Messager(grille.getTuile(SPAWN_MESSAGER), nom);
        }  

        
        
        return aventurier;
    }

    private void shuffleSpe() {
    
        Collections.shuffle(specialisations);
    }

    private void traiterClicBoutonTresor(TypeTresor t) {
        switch(t){
            case caliceDeLOnde:
                //this.tresorsObtenus.add();
                break;
                
            case cristalArdent:
                break;
                
            case pierreSacree:
                break;
                
            case statueDuZephyr:
                break;
        }
    }

    private void afficherCartes() {
        this.vueAventurier.afficherCartes(avCourant.getCartesPossedees());
    }

    private void defausse(CarteTresor carte) {
            avCourant.getCartes().remove(carte);
            cartesTresor.defausserCarte(carte);
            this.afficherCartes();
    }

    private void traiterClicHelicoptere() {
        for(int x = 0; x < 6; x++){
            for(int y = 0; y < 6; y++){
                if(this.grille.getTuile(x,y) != null 
                        && this.grille.getTuile(x, y).getEtatTuile() != Tuile.ETAT_TUILE_COULEE){
                    this.vueAventurier.enable(x, y);
                }
            }
        }
        this.operationEnCours = OPERATION_HELICOPTERE;
    }

    private void removeCarteHelico() {
        for(CarteTresor c : avCourant.getCartes()){
            if(c.getTypeCarte().equals("action_speciale")){
                CarteBonus d = (CarteBonus) c;
                if (d.getPouvoir().equals("Helicoptere")){
                    avCourant.getCartes().remove(c);
                    break;
                }
            }
        }
        this.vueAventurier.afficherCartes(avCourant.getCartes());
    }
    
    private boolean renvoieFaux() {
        return false;
    }

    private void traiterClicSacDeSable() {
        for(int x = 0; x < 6; x++){
            for(int y = 0; y < 6; y++){
                if(this.grille.getTuile(x,y) != null 
                        && this.grille.getTuile(x, y).getEtatTuile() == Tuile.ETAT_TUILE_INONDEE){
                    this.vueAventurier.enable(x, y);
                }
            }
        }
        this.operationEnCours = OPERATION_SAC;
    }

    private void assecherTuileGratuitement(int x, int y) {
        grille.getTuile(x, y).setAssechee();
        
        vueAventurier.actualiseTuiles();
        this.operationEnCours = OPERATION_AUCUNE;
    }

    private void updateVueAventurier() {
        this.vueAventurier.actualiseAventuriers();
        this.vueAventurier.actualiseTuiles();
        this.vueAventurier.afficherCartes(avCourant.getCartes());
        
    }

}