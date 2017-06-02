package model;

import java.util.ArrayList;
import java.util.Iterator;

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

	public ArrayList<Tuile> getTuilesAdjacentes(Tuile tuileC) {
            ArrayList<Tuile> r = new ArrayList();
            Tuile tuileGauche = this.getTuile(tuileC.getX() - 1, tuileC.getY());
            Tuile tuileDroite = this.getTuile(tuileC.getX() + 1, tuileC.getY());
            Tuile tuileHaut = this.getTuile(tuileC.getX(), tuileC.getY() + 1);
            Tuile tuileBas = this.getTuile(tuileC.getX(), tuileC.getY() - 1);
            
            
            if (tuileGauche != null){
                if(tuileGauche.getEtatTuile() != Tuile.ETAT_TUILE_COULEE)
                    r.add(tuileGauche);
            }
            
            if (tuileDroite != null){
                if (tuileDroite.getEtatTuile() != Tuile.ETAT_TUILE_COULEE)
                    r.add(tuileDroite);
            }
            
            if (tuileHaut != null){
                if (tuileHaut.getEtatTuile() != Tuile.ETAT_TUILE_COULEE)
                    r.add(tuileHaut);
            }
                
            if (tuileBas != null){
                if (tuileBas.getEtatTuile() != Tuile.ETAT_TUILE_COULEE)
                    r.add(tuileBas);
            }
            
            return r;
	}

	public Tuile getTuile(int x, int y) {
            for(Tuile t : this.tuiles){
                if(t.getX() == x && t.getY() == y){
                    return t;
                }
            }
            return null;
	}
        
        public void setTuile(int x, int y, int state) {
            for(Tuile t : this.tuiles){
                if(t.getX() == x && t.getY() == y){
                    if (state == Tuile.ETAT_TUILE_INONDEE) {
                        t.setInondee(true);
                    }
                    if (state == Tuile.ETAT_TUILE_COULEE) {
                        t.setCoulee(true);
                    }
                    if (state == Tuile.ETAT_TUILE_SECHE) {
                        t.setAssechee();
                    }
                }
            }
	}

	public ArrayList<Tuile> getTuilesAdjEtDiag(Tuile t) {
            ArrayList<Tuile> r = this.getTuilesAdjacentes(t);
            
            Tuile tuileHG = this.getTuile(t.getX() - 1, t.getY() - 1);
            Tuile tuileHD = this.getTuile(t.getX() + 1, t.getY() - 1);
            Tuile tuileBG = this.getTuile(t.getX() - 1, t.getY() + 1);
            Tuile tuileBD = this.getTuile(t.getX() + 1, t.getY() + 1);
            
            if (tuileHG != null){
                if (tuileHG.getEtatTuile() != Tuile.ETAT_TUILE_COULEE)
                    r.add(tuileHG);
            }
            
            if (tuileHD != null){
                if (tuileHD.getEtatTuile() != Tuile.ETAT_TUILE_COULEE)
                    r.add(tuileHD);
            }
            
            if (tuileBG != null){
                if (tuileBG.getEtatTuile() != Tuile.ETAT_TUILE_COULEE)
                    r.add(tuileBG);
            }
            
            if (tuileBD != null){
                if (tuileBD.getEtatTuile() != Tuile.ETAT_TUILE_COULEE)
                    r.add(tuileBD);
            }
            
            return r;
	}

	public ArrayList<Tuile> getToutesLesTuiles() {
		return this.tuiles;
	}

	public ArrayList<Tuile> getTuilesNonSeches(Tuile t) {
            ArrayList<Tuile> buffer = new ArrayList<Tuile>();
            for(int i = t.getX()-1; i <= t.getX()+1; i++){
                for(int j = t.getY()-1; j <= t.getY()+1; j++){
                    if(!(i == t.getX()-1 && j == t.getY()-1 || i == t.getX()-1 && j == t.getY()+1 || i == t.getX()+1 && j == t.getY()-1 || i == t.getX()+1 && j == t.getY()+1)) {
                        Tuile tuBuffer = getTuile(i, j);
                        if (tuBuffer.getEtatTuile() == Tuile.ETAT_TUILE_INONDEE) {
                            buffer.add(tuBuffer);
                        }
                    }
                }
            }
            
            return buffer;
	}
        
    ArrayList<Tuile> getTuilesAdjacentesEtSombrees(Tuile tuileC){
        ArrayList<Tuile> r = new ArrayList();
        Tuile tuileGauche = this.getTuile(tuileC.getX() - 1, tuileC.getY());
        Tuile tuileDroite = this.getTuile(tuileC.getX() + 1, tuileC.getY());
        Tuile tuileHaut = this.getTuile(tuileC.getX(), tuileC.getY() + 1);
        Tuile tuileBas = this.getTuile(tuileC.getX(), tuileC.getY() - 1);
            
            
            if (tuileGauche != null){
                    r.add(tuileGauche);
            }
            
            if (tuileDroite != null){
                    r.add(tuileDroite);
            }
            
            if (tuileHaut != null){
                    r.add(tuileHaut);
            }
                
            if (tuileBas != null){
                    r.add(tuileBas);
            }
            
            return r;
    }
    
    public ArrayList<Tuile> getTuilesAdjacentesSombreesOuCoulees(Tuile tuileC) {
            ArrayList<Tuile> r = new ArrayList();
            Tuile tuileGauche = this.getTuile(tuileC.getX() - 1, tuileC.getY());
            Tuile tuileDroite = this.getTuile(tuileC.getX() + 1, tuileC.getY());
            Tuile tuileHaut = this.getTuile(tuileC.getX(), tuileC.getY() + 1);
            Tuile tuileBas = this.getTuile(tuileC.getX(), tuileC.getY() - 1);
            
            
            if (tuileGauche != null){
                if(tuileGauche.getEtatTuile() != Tuile.ETAT_TUILE_SECHE)
                    r.add(tuileGauche);
            }
            
            if (tuileDroite != null){
                if (tuileDroite.getEtatTuile() != Tuile.ETAT_TUILE_SECHE)
                    r.add(tuileDroite);
            }
            
            if (tuileHaut != null){
                if (tuileHaut.getEtatTuile() != Tuile.ETAT_TUILE_SECHE)
                    r.add(tuileHaut);
            }
                
            if (tuileBas != null){
                if (tuileBas.getEtatTuile() != Tuile.ETAT_TUILE_SECHE)
                    r.add(tuileBas);
            }
            
            return r;
	}
    
    

    ArrayList<Tuile> getDeplacementsPlongeur(Tuile tuileC) {
        ArrayList<Tuile> tuilesPassage = getTuilesAdjacentesSombreesOuCoulees(tuileC);
        ArrayList<Tuile> r = new ArrayList();
        
        tuilesPassage.add(tuileC);
        
        for (int i = 0; i < tuilesPassage.size(); i++){
            Tuile t = tuilesPassage.get(i);
                for (Tuile t2 : this.getTuilesAdjacentes(t)){
                    if (!tuilesPassage.contains(t2) && !(t2.equals(tuileC)) && t2.getEtatTuile() != Tuile.ETAT_TUILE_SECHE){
                        tuilesPassage.add(t2);
                    }
                }
            
        }
        /*for(Tuile t : tuilesPassage){
            System.out.println("x : " + t.getX());
            System.out.println("y : " + t.getY());
        }
        System.out.println("\n\n\n\n");*/
        
        
        for(Tuile t : tuilesPassage){
            if (t.getEtatTuile() != Tuile.ETAT_TUILE_COULEE && !r.contains(t) && !(t.equals(tuileC))){
                r.add(t);
            }
            for (Tuile t2 : this.getTuilesAdjacentes(t)){
                if (!r.contains(t2) && !(t2.equals(tuileC)))
                    r.add(t2);
            }
        }
        return r;
    }
        
        
        
        
}
