package model;

import java.util.ArrayList;

public class Grille {
	public ArrayList<Tuile> tuiles = new ArrayList<>();
        
        public Grille(){
            for(int i = 3; i <= 4; i++)
                tuiles.add(new Tuile(i, 1));
            
            for(int i = 2; i<=5; i++)
                tuiles.add(new Tuile(i, 2));
            
            for (int i = 3; i<=4; i++){
                for(int j = 1; j <= 6; j++)
                    tuiles.add(new Tuile(j,i));
            }
            
            for(int i = 2; i<=5; i++)
                tuiles.add(new Tuile(i, 5));
            
            for(int i = 3; i <= 4; i++)
                tuiles.add(new Tuile(i, 6));
            
        }

	public ArrayList<Tuile> getTuilesAdjacentes(Tuile t) {
		throw new UnsupportedOperationException();
	}

	public Tuile getTuile(int x, int y) {
            for(Tuile t : this.tuiles){
                if(t.getX() == x && t.getY() == y){
                    return t;
                }
            }
            return null;
	}

	public ArrayList<Tuile> getTuilesAdjEtDiag(Tuile aTuile) {
		throw new UnsupportedOperationException();
	}

	public ArrayList<Tuile> getToutesLesTuiles() {
		throw new UnsupportedOperationException();
	}

	public ArrayList<Tuile> getTuilesNonSeches(Tuile t) {
            ArrayList<Tuile> buffer = new ArrayList<Tuile>();
            for(int i = t.getX()-1; i <= t.getX()+1; i++){
                for(int j = t.getY()-1; j <= t.getY()+1; j++){
                    if(!(i == t.getX()-1 && j == t.getY()-1 || i == t.getX()-1 && j == t.getY()+1 || i == t.getX()+1 && j == t.getY()-1 || i == t.getX()+1 && j == t.getY()+1)) {
                        buffer.add(new Tuile(i, j));
                    }
                }
            }
            
            return buffer;
	}
        
}
