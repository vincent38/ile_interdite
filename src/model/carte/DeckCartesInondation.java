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
    
    private ArrayList<CarteInondation> deckCartes;
    private ArrayList<CarteInondation> defausseCartes;

    public DeckCartesInondation() {
        deckCartes = new ArrayList<>();
        defausseCartes = new ArrayList<>();
        
        for (int i = 0; i < 24; i++) {
            deckCartes.add(new CarteInondation(NOMS_TUILES[i]));
        }
        
        shuffleCards();
    }
    
    public void shuffleCards() {
        //if (Parameters.ALEAS) {
            Collections.shuffle(deckCartes);
            Collections.shuffle(deckCartes);
            Collections.shuffle(deckCartes);
        //}
    }
    
}
