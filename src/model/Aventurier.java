package model;

import java.awt.Color;
import java.util.ArrayList;

public abstract class Aventurier {

	public ArrayList<Tresor> tresorsObtenus = new ArrayList<>();
	public ArrayList<CarteTresor> cartes = new ArrayList<>();
	public Tuile tuileCourante;
        public ArrayList<Tuile> tuilesPossibles = new ArrayList();
        private String nom;
        protected boolean pouvoirDispo = true;
        private String type;
        
    public Aventurier(String nom) {
        this.nom = nom;
        setType("Aventurier");
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public Aventurier(Tuile tuile, String nom){
        this.tuileCourante = tuile;
        this.nom = nom;
        setType("Aventurier");
    }

    public void assecher(ArrayList<Tuile> tuilesAssechables, Grille g, int x, int y) {
        Tuile myTuile = new Tuile(x, y);
        //On vérifie si elle existe. Existe -> on assèche la tuile
        for (Tuile t : tuilesAssechables) {
            if (t.getX() == myTuile.getX() && t.getY() == myTuile.getY()) {
                g.setTuile(t.getX(), t.getY(), Tuile.ETAT_TUILE_SECHE);
                System.out.println(myTuile.getEtatTuile());
                break;
            }
        }
    }

    public ArrayList<Tuile> getAssechablesParJoueur(Grille g) {
        Tuile t = getTuile();
        ArrayList<Tuile> buffer = new ArrayList<>();
        for (int i = t.getX() - 1; i <= t.getX() + 1; i++) {
            for (int j = t.getY() - 1; j <= t.getY() + 1; j++) {
                if (!(i == t.getX() - 1 && j == t.getY() - 1 || i == t.getX() - 1 && j == t.getY() + 1 || i == t.getX() + 1 && j == t.getY() - 1 || i == t.getX() + 1 && j == t.getY() + 1)) {
                    Tuile tuBuffer = g.getTuile(i, j);
                    if (tuBuffer != null && tuBuffer.getEtatTuile() == Tuile.ETAT_TUILE_INONDEE) {
                        buffer.add(tuBuffer);
                    }
                }
            }
        }
        return buffer;
    }

    public void donnerCarte() {
        throw new UnsupportedOperationException();
    }

    public Tuile getTuile() {
        return tuileCourante;
    }

    public void setTuile(Tuile tuile) {
        this.tuileCourante = tuile;
        tuile.addAventurier(this);

    }

    public ArrayList<CarteTresor> getCartes() {
        return cartes;
    }

    public boolean getInondee() {
        throw new UnsupportedOperationException();
    }

   
    public void deplacement(Tuile nvTuile, Grille g){
        this.tuileCourante.rmAventurier(this);
        this.setTuile(nvTuile);
    }

    public ArrayList<Tuile> getDeplacementsPossibles(Grille grille) {
        return grille.getTuilesAdjacentes(this.tuileCourante);
    }

    public String getNom() {
        return nom;
    }

    public abstract Color getColor();

    public Color getFontColor() {
        return Color.WHITE;
    }

    public void traiterFinDeTour() {
    }
}
