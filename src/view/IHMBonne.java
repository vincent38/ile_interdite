/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.aventurier.Aventurier;
import model.carte.CarteTresor;
import javax.swing.SwingConstants;
import model.Grille;
import model.Message;
import model.TypeMessage;
import model.Observateur;
import model.Tuile;
import model.TypeTresor;
import model.aventurier.Aventurier;
import util.Utils;
import util.Utils.Tresor;

/**
 *
 * @author sangj
 */
public class IHMBonne extends Observable{

    public void fermerFenetre() {
        fenetre.dispose();
    }
    private Fenetre fenetre;
    private Observateur observateur;
    private ArrayList<Aventurier> aventuriers;
    private Grille g;
    
    
    public IHMBonne(Aventurier firstJoueur, int nbAct, Grille g, ArrayList<Aventurier> aventuriers){
        this.g = g;
        this.aventuriers = aventuriers;
        fenetre = new Fenetre(this, firstJoueur, nbAct, aventuriers);
        fenetre.afficherAventuriers(g);
        
    }
    
    public void traiterClicCase(int x, int y){
        this.setChanged();
        this.notifyObservers(new Message(TypeMessage.CLIC_CASE, y, x));
        this.clearChanged();
    }
    
    public void traiterClicDeplacer(){
        this.setChanged();
        this.notifyObservers(new Message(TypeMessage.CLIC_BTN_ALLER));
        this.clearChanged();
    }

    public static void choisirDestinataireEtCarte(ArrayList<Aventurier> aventuriersMemeTuile, ArrayList<CarteTresor> cartesPossedees) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void traiterClicAssecher(){
        this.setChanged();
        this.notifyObservers(new Message(TypeMessage.CLIC_BTN_ASSECHER));
        this.clearChanged();
    }
    
    public void traiterClicDonCarte(){
        this.setChanged();
        this.notifyObservers(new Message(TypeMessage.CLIC_BTN_DONNER_CARTE));
        this.clearChanged();
    }
    
    public void traiterClicFinTour(){
        this.setChanged();
        this.notifyObservers(new Message(TypeMessage.CLIC_BTN_TERMINER_TOUR));
        this.clearChanged();
    }

    public void actualiseAventuriers() {
        fenetre.actualiseAventuriers(this.g);
    }

    public void setNbAct(int i) {
        fenetre.setNbAct(i);
    }

    public void setAventurier(Aventurier av) {
        this.fenetre.setAventurier(av);
    }

    public void enable(int x, int y) {
        fenetre.enable(x, y);
    }

    public void disableBoutons() {
        this.fenetre.disableBoutons();
    }
    
    public Grille getGrille(){
        return(this.g);
    }


    
    public void setEtatTuile(int etatTuile, int x, int y) {
        this.fenetre.setEtatTuile(etatTuile, x, y);
    }
    
    public void disableInteraction() {
        fenetre.disableInteraction();
    }
    
    public void enableInteraction() {
        fenetre.enableInteraction();
    }
    
    public void actualiseTuiles() {
        fenetre.actualiseTuiles(g);
    }

    public void setNiveau(int niv) {
        this.fenetre.setNiveau(niv);
    }


    public void afficherTresor(model.Tresor tresor) {
        this.fenetre.afficherTresor(tresor);
    }

    void traiterClicTresor() {
        this.setChanged();
        this.notifyObservers(new Message(TypeMessage.CLIC_BTN_TRESOR));
        this.clearChanged();    
        
    }

    public void enable(TypeTresor typeTresor) {
        this.fenetre.enable(typeTresor);
    }

    public void disableTresors() {
        this.fenetre.disableTresors();
    }

    public void afficherCartes(ArrayList<CarteTresor> cartes) {
        this.fenetre.afficherCartes(cartes);
    }
}
