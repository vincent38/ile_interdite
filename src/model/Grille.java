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
        
        //Définition de la grille
        
        for (int i = 2; i <= 3; i++) {
            tuiles.add(new Tuile(i, 0));
        }

        for (int i = 1; i <= 4; i++) {
            tuiles.add(new Tuile(i, 1));
        }

        for (int i = 2; i <= 3; i++) {
            for (int j = 0; j <= 5; j++) {
                tuiles.add(new Tuile(j, i));
            }
        }

        for (int i = 1; i <= 4; i++) {
            tuiles.add(new Tuile(i, 4));
        }

        for (int i = 2; i <= 3; i++) {
            tuiles.add(new Tuile(i, 5));
        }

        //Définition nom des tuiles
        
        for (int i = 0; i < tuiles.size(); i++) {
            tuiles.get(i).setNom(NOMS_TUILES[i]);
        }
        
        //Ajout des trésors aux tuiles
        for (Tuile t : getTuiles()) {
            if (t.getNom().equals("Le Temple de La Lune")
                || t.getNom().equals("Le Temple du Soleil")) {
                Tresor tr = new Tresor();
                tr.typeTresor = TypeTresor.pierreSacree;
                t.setTresor(tr);
            }
            if (t.getNom().equals("Le Jardin des Murmures")
                || t.getNom().equals("Le Jardin des Hurlements")) {
                Tresor tr = new Tresor();
                tr.typeTresor = TypeTresor.statueDuZephyr;
                t.setTresor(tr);
            }
            if (t.getNom().equals("La Caverne du Brasier")
                || t.getNom().equals("La Caverne des Ombres")) {
                Tresor tr = new Tresor();
                tr.typeTresor = TypeTresor.cristalArdent;
                t.setTresor(tr);
            }
            if (t.getNom().equals("Le Palais de Corail")
                || t.getNom().equals("Le Palais des Marees")) {
                Tresor tr = new Tresor();
                tr.typeTresor = TypeTresor.caliceDeLOnde;
                t.setTresor(tr);
            }
            
        }
        
    }
    
    public int estCoulee(Tuile t) {
        return t.getEtatTuile();
    }

       
    private ArrayList<Tuile> getTuiles(){
        return tuiles;
    }
    
    /*
     * @deprecated It is not recommended to call this function for now. Please wait for the release of a newer version of this method.
     * Inutile dans cette configuration : vérifie quelles tuiles ne sont pas encore placées dans le jeu
     */
    /*private void remainingTuilesNames() {
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
    }*/
    
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
     * Retourne un objet Tuile en fonction des coordonées d'entrées
     * @param nomTuile
     * @return 
     */

    public Tuile getTuile(String nomTuile) {
        for (Tuile t : this.tuiles) {
            if (t.getNom().equals(nomTuile)) {
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
        ArrayList<Tuile> tuiles = new ArrayList<Tuile>();
        for (Tuile t : this.tuiles) {
                tuiles.add(t);
        }
        return tuiles;
    }
    
    /*
     * @deprecated getAssechablesParJoueur
     * @param t
     * @return
     */
    /*public ArrayList<Tuile> getTuilesNonSeches(Tuile t) {
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
    }*/
    
 
    /**
     * Méthode inutilisée actuellement. Gardée en prévision.
     * Retourne les tuiles adjacentes à la tuileC incluant les tuiles coulées
     */
    public ArrayList<Tuile> getTuilesAdjacentesEtSombrees(Tuile tuileC) {
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

    public ArrayList<Tuile> getDeplacementsPlongeur(Tuile tuileC) {
        ArrayList<Tuile> tuilesPassage = getTuilesAdjacentesSombreesOuCoulees(tuileC);
        ArrayList<Tuile> r = new ArrayList();
        System.out.println(tuilesPassage.size());

        tuilesPassage.add(tuileC);

        for (int i = 0; i < tuilesPassage.size(); i++) {
            Tuile t = tuilesPassage.get(i);
            for (Tuile t2 : this.getTuilesAdjacentesSombreesOuCoulees(t)) {
                if (!tuilesPassage.contains(t2)) {
                    tuilesPassage.add(t2);
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
