package model;

import model.aventurier.Aventurier;
import java.util.ArrayList;

public class Tuile {

    // Coordonnées
    private int x;
    private int y;
    // Etat Tuile
    private int etatTuile;
    public static final int ETAT_TUILE_SECHE = 0;
    public static final int ETAT_TUILE_INONDEE = 1;
    public static final int ETAT_TUILE_COULEE = 2;

    public ArrayList<Aventurier> aventuriers = new ArrayList<>();
    public Tresor tresor;

    public String nom;

    /**
     * Définit une nouvelle tuile prenant des coordonnées x et y
     * @param x integer
     * @param y integer
     */
    public Tuile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Définit le nom de la tuile
     * @param nom String
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupère le nom de la tuile
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Permet d'inonder une case, a condition qu'elle soie sèche
     */
    public void setInondee() {
        if (etatTuile == ETAT_TUILE_SECHE) {
            etatTuile = ETAT_TUILE_INONDEE;
        }
    }

    /**
     * Permet de couler une case, a condition qu'elle soie inondée
     */
    public void setCoulee() {
        if (etatTuile == ETAT_TUILE_INONDEE) {
            etatTuile = ETAT_TUILE_COULEE;
        }
    }

    /**
     * Permet d'assécher une case, a condition qu'elle soie inondée
     */
    public void setAssechee() {
        if (etatTuile == ETAT_TUILE_INONDEE) {
            etatTuile = ETAT_TUILE_SECHE;
        }
    }
    
    /**
     * Ultimate megamix 2017 deluxxx edition des 3 précédentes parceque #yolo #RTsiCtrist #Fromage
     * 11/10 IGN "Best method ever"
     * 9/10 JVC "Oué c bi1 mé jem trolé ptdr"
     * 8/8 gr8 b8 m8 would r8 again
     * @see lolTMort
     */
    
    public void setEtatTuile() {
        if (etatTuile == ETAT_TUILE_INONDEE) {
            setCoulee();
        } else {
            setInondee();
        }
    }

    /**
     * Retourne la coordonnée x de la tuile
     *
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * Définit la coordonnée x de la tuile, celle-ci prend la valeur x passée en
     * paramètres
     *
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Retourne la coordonnée y de la tuile
     *
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * Définit la coordonnée y de la tuile, celle-ci prend la valeur y passée en
     * paramètres
     *
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Retourne une arrayList d'aventuriers présents sur la tuile
     *
     * @return aventuriers
     */
    public ArrayList<Aventurier> getAventuriers() {
        return aventuriers;
    }

    /**
     * Ajoute un aventurier sur la tuile
     *
     * @param av Aventurier
     */
    public void addAventurier(Aventurier av) {
        this.aventuriers.add(av);
    }

    /**
     * Retire un aventurier de la tuile
     *
     * @param av Aventurier
     */
    public void rmAventurier(Aventurier av) {
        this.aventuriers.remove(av);
    }

    /**
     * Récupère l'état de la tuile (sèche, inondée, coulée)
     *
     * @return etatTuile
     * @see ETAT_TUILE_SECHE
     * @see ETAT_TUILE_INONDEE
     * @see ETAT_TUILE_COULEE
     */
    public int getEtatTuile() {
        return this.etatTuile;
    }

}
