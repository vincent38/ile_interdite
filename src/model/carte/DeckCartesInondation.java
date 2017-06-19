/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.carte;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author aubriotv
 */
public class DeckCartesInondation {
    
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
    
    public ArrayList<CarteInondation> deckCartes;
    private ArrayList<CarteInondation> defausseCartes;

    public DeckCartesInondation() {
        deckCartes = new ArrayList<>();
        defausseCartes = new ArrayList<>();
        
        for (int i = 0; i < 24; i++) {
            deckCartes.add(new CarteInondation(NOMS_TUILES[i]));
        }
        
        shuffleCards();
        
        for (CarteInondation c : deckCartes){
            System.out.println(c.getCaseConcernee());
        }
    }
    
    public void shuffleCards() {
        //if (Parameters.ALEAS) {
            Collections.shuffle(deckCartes);
            Collections.shuffle(deckCartes);
            Collections.shuffle(deckCartes);
        //}
    }
    
    public void shuffleDefausseCards() {
        //if (Parameters.ALEAS) {
            Collections.shuffle(defausseCartes);
            Collections.shuffle(defausseCartes);
            Collections.shuffle(defausseCartes);
        //}
    }
    
    public CarteInondation tirerCarte(){
        refillDeck();
        return deckCartes.remove(0);
    }
    
    public void defausserCarte(CarteInondation c){
        defausseCartes.add(0, c);
    }
    
    public void replacerDansLaPile(CarteInondation c){
        deckCartes.add(0, c);
    }
    
    public void fusionDecks(){
        ArrayList<CarteInondation> buffer = new ArrayList<>();
        buffer.addAll(defausseCartes);
        buffer.addAll(deckCartes);
        deckCartes.clear();
        defausseCartes.clear();
        deckCartes.addAll(buffer);
    }
    
    private void refillDeck(){
        if (deckCartes.isEmpty()) {
            deckCartes.addAll(defausseCartes);
            defausseCartes.clear();
            shuffleCards();
        }
    }
}
