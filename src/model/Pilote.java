package model;

import java.util.ArrayList;

public class Pilote extends Aventurier {
	private boolean pouvoirDispo = true;

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
            this.pouvoirDispo = false;
            return g.getToutesLesTuiles();
        }
        else
            return super.getDeplacementsPossibles(g);
    }
    
    @Override
    public void traiterFinDeTour(){
        this.pouvoirDispo = true;
    }
}