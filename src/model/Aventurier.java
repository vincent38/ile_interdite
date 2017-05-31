package model;

import java.util.ArrayList;

public class Aventurier {
	public ArrayList<Tresor> tresorsObtenus = new ArrayList<>();
	public ArrayList<CarteTresor> cartes = new ArrayList<>();
	public Tuile tuile;
        public ArrayList<Tuile> tuilesPossibles = new ArrayList();
        
    public Aventurier() {
        
    }

    public Aventurier(Tuile tuile){
        this.tuile = tuile;
    }
    
    public void assecher(Tuile t) {
        t.setAssechee();
    }


    

    public void donnerCarte() {
            throw new UnsupportedOperationException();
    }

    public Tuile getTuile() {
        return tuile;
    }

    public void setTuile(Tuile tuile) {
        this.tuile = tuile;
        tuile.addAventurier(this);
        
    }

    public ArrayList<CarteTresor> getCartes() {
        return cartes;
    }

    

    public boolean getInondee() {
            throw new UnsupportedOperationException();
    }

    
    public void deplacement(Tuile nvTuile){
        this.tuile.rmAventurier(this);
        this.setTuile(nvTuile);
        
    }

    public ArrayList<Tuile> getDeplacementsPossibles(Grille grille) {
        ArrayList<Tuile> r = new ArrayList();
            Tuile tuileC = this.getTuile();
            
            if(grille.getTuile(tuileC.getX() - 1, tuileC.getY()).getEtatTuile() != Tuile.ETAT_TUILE_COULEE)
                r.add(grille.getTuile(tuileC.getX() - 1, tuileC.getY()));
            
            if (grille.getTuile(tuileC.getX(), tuileC.getY() - 1).getEtatTuile() != Tuile.ETAT_TUILE_COULEE)
                r.add(grille.getTuile(tuileC.getX(), tuileC.getY() - 1));
            
            if (grille.getTuile(tuileC.getX() + 1, tuileC.getY()).getEtatTuile() != Tuile.ETAT_TUILE_COULEE)
                r.add(grille.getTuile(tuileC.getX() + 1, tuileC.getY()));
                
            if (grille.getTuile(tuileC.getX(), tuileC.getY() + 1).getEtatTuile() != Tuile.ETAT_TUILE_COULEE)
                r.add(grille.getTuile(tuileC.getX(), tuileC.getY() + 1));
            
            return r;
    }
}