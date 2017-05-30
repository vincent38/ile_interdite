package model;

import java.util.ArrayList;

public class Aventurier {
	public ArrayList<Tresor> tresorsObtenus = new ArrayList<>();
	public ArrayList<CarteTresor> cartes = new ArrayList<>();
	public Tuile tuile;
        public ArrayList<Tuile> tuilesPossibles = new ArrayList();

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

    public ArrayList<Tuile> getTuilesAssechables() {
            ArrayList<Tuile> buffer = new ArrayList<Tuile>();
            for(int i = tuile.getX()-1; i <= tuile.getX()+1; i++){
                for(int j = tuile.getY()-1; j <= tuile.getY()+1; j++){
                    if(!(i == tuile.getX()-1 && j == tuile.getY()-1 ||
                        i == tuile.getX()-1 && j == tuile.getY()+1 ||
                        i == tuile.getX()+1 && j == tuile.getY()-1 ||
                        i == tuile.getX()+1 && j == tuile.getY()+1)) {
                        buffer.add(new Tuile(i, j));
                    }
                }
            }
            
            return buffer;
    }
    
    public void deplacement(Tuile nvTuile){
        this.tuile.rmAventurier(this);
        this.setTuile(nvTuile);
        
    }
}