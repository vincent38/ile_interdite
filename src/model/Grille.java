package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Grille {
    
    private String [] NOMS_TUILES = {"Le Pont des Abimes",
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
        
        public Grille(){
            //remainingTuilesNames();
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
            
            for (int i = 0; i < tuiles.size(); i++) {
                tuiles.get(i).setNom(NOMS_TUILES[i]);
            }
        }
        
        private void remainingTuilesNames() {
            ArrayList<String> allNames = new ArrayList<String>();
            //On add tous les noms dans une arraylist
            for (int i = 0; i < NOMS_TUILES.length; i++){
                allNames.add(NOMS_TUILES[i]);
            }
            long seed = System.nanoTime();
            Collections.shuffle(allNames, new Random(seed));
            //On disp pour tester
            for (String s : allNames) {
                System.out.println(s);
            }
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

	public ArrayList<Tuile> getTuilesAdjEtDiag(Tuile aTuile) {
		throw new UnsupportedOperationException();
	}

	public ArrayList<Tuile> getToutesLesTuiles() {
            ArrayList<Tuile> tuilesNonCoulees = new ArrayList<Tuile>();
            for (Tuile t : this.tuiles) {
                if (t.getEtatTuile() != Tuile.ETAT_TUILE_COULEE) {
                    tuilesNonCoulees.add(t);
                }
            }
            return tuilesNonCoulees;
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
        
        
        
}
