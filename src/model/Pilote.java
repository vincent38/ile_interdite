package model;

import java.util.ArrayList;

public class Pilote extends Aventurier {

    public Pilote(String nom) {
        super(nom);
    }

    public Pilote(Tuile tuile, String nom){
        super(tuile, nom);
    }

	public void setPouvoirNonDispo() {
            this.pouvoirDispo = false;
        }
        
    @Override
    public ArrayList<Tuile> getDeplacementsPossibles(Grille g){
        if (pouvoirDispo){
            return g.getToutesLesTuiles();
        }
        else
            return super.getDeplacementsPossibles(g);
    }
    
        @Override
    public void deplacement(Tuile nvTuile, Grille g){
        if (!super.getDeplacementsPossibles(g).contains(nvTuile)){
            this.pouvoirDispo = false;
        }
        super.deplacement(nvTuile, g);
    }
    
    @Override
    public void traiterFinDeTour(){
        this.pouvoirDispo = true;
    }
}