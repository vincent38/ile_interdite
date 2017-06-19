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
import model.aventurier.Aventurier;

/**
 *
 * @author sangj
 */
public class IHMBonne extends Observable{
    private Fenetre fenetre;
    private Observateur observateur;
    private ArrayList<Aventurier> aventuriers;
    
    
    public IHMBonne(Aventurier firstJoueur, int nbActRestantes, Grille g, ArrayList<Aventurier> aventuriers){
        fenetre = new Fenetre(this, firstJoueur, nbActRestantes);
        fenetre.afficherAventuriers(aventuriers, g);
        
    }
    
    public void traiterClicCase(int x, int y){
        System.out.println(x);
        System.out.println(y);
    }
    
<<<<<<< HEAD
    public void traiterClicDeplacer(){
        this.setChanged();
        this.notifyObservers(new Message(TypeMessage.CLIC_BTN_ALLER));
        this.clearChanged();
=======

    public static void choisirDestinataireEtCarte(ArrayList<Aventurier> aventuriersMemeTuile, ArrayList<CarteTresor> cartesPossedees) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void traiterClicDeplacer(){
        System.out.println("clic deplacer");
>>>>>>> 0265a907460c8a3379298772c7be820a9a9b91fa
    }
    
    public void traiterClicAssecher(){
        this.setChanged();
        this.notifyObservers(new Message(TypeMessage.CLIC_BTN_ASSECHER));
        this.clearChanged();
    }
    
    public void traiterClicAutreAction(){
        this.setChanged();
        this.notifyObservers(new Message(TypeMessage.CLIC_BTN_AUTRE_ACTION));
        this.clearChanged();
    }
    
    public void traiterClicFinTour(){
        this.setChanged();
        this.notifyObservers(new Message(TypeMessage.CLIC_BTN_TERMINER_TOUR));
        this.clearChanged();
    }

    
}
