package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Grille {

    /**
     * Stocke les noms des tuiles du jeu
     */
    
    private String[] NOMS_TUILES = {"Le Pont des Abimes",
        "La Porte de Bronze",
        "La Caverne des Ombres",
        "La Porte de Fer",
        "La Porte d’Or",
        "Les Falaises de l’Oubli",
        "Le Palais de Corail",
        "La Porte d’Argent",
        "Les Dunes de l’Illusion",
        "Heliport",
        "La Porte de Cuivre",
        "Le Jardin des Hurlements",
        "La Foret Pourpre",
        "Le Lagon Perdu",
        "Le Marais Brumeux",
        "Observatoire",
        "Le Rocher Fantome",
        "La Caverne du Brasier",
        "Le Temple du Soleil",
        "Le Temple de La Lune",
        "Le Palais des Marees",
        "Le Val du Crepuscule",
        "La Tour du Guet",
        "Le Jardin des Murmures"};
    
    private ArrayList<String> remainingNames;

    public ArrayList<Tuile> tuiles = new ArrayList<>();

    /**
     * Constructeur génère la grille, associe à chaque tuile un nom de la liste
     */
    public Grille() {
        //remainingTuilesNames();
        for (int i = 3; i <= 4; i++) {
            tuiles.add(new Tuile(i, 1));
        }

        for (int i = 2; i <= 5; i++) {
            tuiles.add(new Tuile(i, 2));
        }

        for (int i = 3; i <= 4; i++) {
            for (int j = 1; j <= 6; j++) {
                tuiles.add(new Tuile(j, i));
            }
        }

        for (int i = 2; i <= 5; i++) {
            tuiles.add(new Tuile(i, 5));
        }

        for (int i = 3; i <= 4; i++) {
            tuiles.add(new Tuile(i, 6));
        }

        for (int i = 0; i < tuiles.size(); i++) {
            tuiles.get(i).setNom(NOMS_TUILES[i]);
        }
    }

        
    /** 
     * @deprecated It is not recommended to call this function for now. Please wait for the release of a newer version of this method.
     * Inutile dans cette configuration : vérifie quelles tuiles ne sont pas encore placées dans le jeu
     */
    private void remainingTuilesNames() {
        ArrayList<String> allNames = new ArrayList<String>();
        //On add tous les noms dans une arraylist
        for (int i = 0; i < NOMS_TUILES.length; i++) {
            allNames.add(NOMS_TUILES[i]);
        }
        long seed = System.nanoTime();
        Collections.shuffle(allNames, new Random(seed));
        //On disp pour tester
        for (String s : allNames) {
            System.out.println(s);
        }
    }
    
    /**
     * Retourne les tuiles adjacentes à tuileC
     * tuileC = tuile courante de l'aventurier
     * @param tuileC
     * @return 
     */

    public ArrayList<Tuile> getTuilesAdjacentes(Tuile tuileC) {
        ArrayList<Tuile> r = new ArrayList();
        Tuile tuileGauche = this.getTuile(tuileC.getX() - 1, tuileC.getY());
        Tuile tuileDroite = this.getTuile(tuileC.getX() + 1, tuileC.getY());
        Tuile tuileHaut = this.getTuile(tuileC.getX(), tuileC.getY() + 1);
        Tuile tuileBas = this.getTuile(tuileC.getX(), tuileC.getY() - 1);

        if (tuileGauche != null) {
            if (tuileGauche.getEtatTuile() != Tuile.ETAT_TUILE_COULEE) {
                r.add(tuileGauche);
            }
        }

        if (tuileDroite != null) {
            if (tuileDroite.getEtatTuile() != Tuile.ETAT_TUILE_COULEE) {
                r.add(tuileDroite);
            }
        }

        if (tuileHaut != null) {
            if (tuileHaut.getEtatTuile() != Tuile.ETAT_TUILE_COULEE) {
                r.add(tuileHaut);
            }
        }

        if (tuileBas != null) {
            if (tuileBas.getEtatTuile() != Tuile.ETAT_TUILE_COULEE) {
                r.add(tuileBas);
            }
        }

        return r;
    }
    
    /**
     * Retourne un objet Tuile en fonction des coordonées d'entrées
     * @param x
     * @param y
     * @return 
     */

    public Tuile getTuile(int x, int y) {
        for (Tuile t : this.tuiles) {
            if (t.getX() == x && t.getY() == y) {
                return t;
            }
        }
        return null;
    }
    
    /**
     * Permet de changer l'état de la tuile (Normale, Coulée ou Innondée)
     * @param x
     * @param y
     * @param state 
     */

    public void setTuile(int x, int y, int state) {
        Tuile t = this.getTuile(x,y);
            if (t.getX() == x && t.getY() == y) {
                if (state == Tuile.ETAT_TUILE_INONDEE) {
                    t.setInondee();
                }
                if (state == Tuile.ETAT_TUILE_COULEE) {
                    t.setCoulee();
                }
                if (state == Tuile.ETAT_TUILE_SECHE) {
                    t.setAssechee();
                }
        }
    }

    /**
     * Retourne les tuiles adjacentes et diagonales à la tuile t (Explorateur)
     * @param t
     * @return 
     */
    
    public ArrayList<Tuile> getTuilesAdjEtDiag(Tuile t) {
        ArrayList<Tuile> r = this.getTuilesAdjacentes(t);

        Tuile tuileHG = this.getTuile(t.getX() - 1, t.getY() - 1);
        Tuile tuileHD = this.getTuile(t.getX() + 1, t.getY() - 1);
        Tuile tuileBG = this.getTuile(t.getX() - 1, t.getY() + 1);
        Tuile tuileBD = this.getTuile(t.getX() + 1, t.getY() + 1);

        if (tuileHG != null) {
            if (tuileHG.getEtatTuile() != Tuile.ETAT_TUILE_COULEE) {
                r.add(tuileHG);
            }
        }

        if (tuileHD != null) {
            if (tuileHD.getEtatTuile() != Tuile.ETAT_TUILE_COULEE) {
                r.add(tuileHD);
            }
        }

        if (tuileBG != null) {
            if (tuileBG.getEtatTuile() != Tuile.ETAT_TUILE_COULEE) {
                r.add(tuileBG);
            }
        }

        if (tuileBD != null) {
            if (tuileBD.getEtatTuile() != Tuile.ETAT_TUILE_COULEE) {
                r.add(tuileBD);
            }
        }

        return r;
    }
    
    /**
     * Retourne toutes les tuiles encore en jeu (non coulées) (Pilote)
     * @return 
     */

    public ArrayList<Tuile> getToutesLesTuiles() {
        ArrayList<Tuile> tuilesNonCoulees = new ArrayList<Tuile>();
        for (Tuile t : this.tuiles) {
            if (t.getEtatTuile() != Tuile.ETAT_TUILE_COULEE) {
                tuilesNonCoulees.add(t);
            }
        }
        return tuilesNonCoulees;
    }
    
    /**
     * @deprecated getAssechablesParJoueur
     * @param t
     * @return
     */
    public ArrayList<Tuile> getTuilesNonSeches(Tuile t) {
        ArrayList<Tuile> buffer = new ArrayList<Tuile>();
        for (int i = t.getX() - 1; i <= t.getX() + 1; i++) {
            for (int j = t.getY() - 1; j <= t.getY() + 1; j++) {
                if (!(i == t.getX() - 1 && j == t.getY() - 1 || i == t.getX() - 1 && j == t.getY() + 1 || i == t.getX() + 1 && j == t.getY() - 1 || i == t.getX() + 1 && j == t.getY() + 1)) {
                    Tuile tuBuffer = getTuile(i, j);
                    if (tuBuffer != null && tuBuffer.getEtatTuile() == Tuile.ETAT_TUILE_INONDEE) {
                        buffer.add(tuBuffer);
                    }
                }
            }
        }

        return buffer;
    }
    
 
    /**
     * Méthode inutilisée actuellement. Gardée en prévision.
     * Retourne les tuiles adjacentes à la tuileC incluant les tuiles coulées
     */
    ArrayList<Tuile> getTuilesAdjacentesEtSombrees(Tuile tuileC) {
        ArrayList<Tuile> r = new ArrayList();
        Tuile tuileGauche = this.getTuile(tuileC.getX() - 1, tuileC.getY());
        Tuile tuileDroite = this.getTuile(tuileC.getX() + 1, tuileC.getY());
        Tuile tuileHaut = this.getTuile(tuileC.getX(), tuileC.getY() + 1);
        Tuile tuileBas = this.getTuile(tuileC.getX(), tuileC.getY() - 1);

        if (tuileGauche != null) {
            r.add(tuileGauche);
        }

        if (tuileDroite != null) {
            r.add(tuileDroite);
        }

        if (tuileHaut != null) {
            r.add(tuileHaut);
        }

        if (tuileBas != null) {
            r.add(tuileBas);
        }

        return r;
    }
    
    /**
     * Retourne les tuiles adjacentes à la tuileC incluant les tuiles coulées ou innondées
     * @param tuileC
     * @return 
     */

    public ArrayList<Tuile> getTuilesAdjacentesSombreesOuCoulees(Tuile tuileC) {
        ArrayList<Tuile> r = new ArrayList();
        Tuile tuileGauche = this.getTuile(tuileC.getX() - 1, tuileC.getY());
        Tuile tuileDroite = this.getTuile(tuileC.getX() + 1, tuileC.getY());
        Tuile tuileHaut = this.getTuile(tuileC.getX(), tuileC.getY() + 1);
        Tuile tuileBas = this.getTuile(tuileC.getX(), tuileC.getY() - 1);

        if (tuileGauche != null) {
            if (tuileGauche.getEtatTuile() != Tuile.ETAT_TUILE_SECHE) {
                r.add(tuileGauche);
            }
        }

        if (tuileDroite != null) {
            if (tuileDroite.getEtatTuile() != Tuile.ETAT_TUILE_SECHE) {
                r.add(tuileDroite);
            }
        }

        if (tuileHaut != null) {
            if (tuileHaut.getEtatTuile() != Tuile.ETAT_TUILE_SECHE) {
                r.add(tuileHaut);
            }
        }

        if (tuileBas != null) {
            if (tuileBas.getEtatTuile() != Tuile.ETAT_TUILE_SECHE) {
                r.add(tuileBas);
            }
        }

        return r;
    }
    
    /**
     * Retourne les déplacements possibles pour le plongeur
     * @param tuileC
     * @return 
     */

    ArrayList<Tuile> getDeplacementsPlongeur(Tuile tuileC) {
        ArrayList<Tuile> tuilesPassage = getTuilesAdjacentesSombreesOuCoulees(tuileC);
        ArrayList<Tuile> r = new ArrayList();
        System.out.println(tuilesPassage.size());

        tuilesPassage.add(tuileC);

        for (int i = 0; i < tuilesPassage.size(); i++) {
            Tuile t = tuilesPassage.get(i);
            for (Tuile t2 : this.getTuilesAdjacentesSombreesOuCoulees(t)) {
                if (!tuilesPassage.contains(t2)) {
                    tuilesPassage.add(t2);
                    System.out.println("Tuiles passage plongeur : \nNom : " + t2.getNom() + "\nX : " + t2.getX() + "\nY : " + t2.getY());
                }
            }

        }
        System.out.println(tuilesPassage.size() + '*');
        /*for(Tuile t : tuilesPassage){
            System.out.println("x : " + t.getX());
            System.out.println("y : " + t.getY());
        }
        System.out.println("\n\n\n\n");*/

        for (Tuile t : tuilesPassage) {
            if (t.getEtatTuile() != Tuile.ETAT_TUILE_COULEE && !r.contains(t) && !(t.equals(tuileC))) {
                r.add(t);
            }
            for (Tuile t2 : this.getTuilesAdjacentes(t)) {
                if (!r.contains(t2) && !(t2.equals(tuileC))) {
                    r.add(t2);
                }
            }
        }
        return r;
    }

}
