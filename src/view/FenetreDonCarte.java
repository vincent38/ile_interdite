/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import model.aventurier.Aventurier;
import model.carte.CartePiece;
import model.carte.CarteTresor;

/**
 *
 * @author calixtee
 */
public class FenetreDonCarte extends JFrame {
    
    private IHMDonCarte ihm;
    
    private ArrayList<JRadioButton> boutonsAv = new ArrayList<>();
    private ArrayList<JRadioButton> boutonsCartes = new ArrayList<>();
    
    private JPanel mainpanel = new JPanel(new BorderLayout());
    private JPanel panelCenter = new JPanel(new GridLayout(2,1));
    private JPanel panelCenterAv = new JPanel(new BorderLayout());
    private JPanel panelCenterAvSelection = new JPanel();
    private JPanel panelCenterCa = new JPanel(new BorderLayout());
    private JPanel panelCenterCaSelection = new JPanel();
    private JPanel panelSouth = new JPanel(new GridLayout(1,4));
    
    private JButton boutonAnnuler = new JButton("Annuler");
    private JButton boutonValider = new JButton("Valider");
    private final ArrayList<CartePiece> cartes;
    private final ArrayList<Aventurier> aventuriers;
    private Aventurier av;
    private CartePiece ca;
    

    FenetreDonCarte(IHMDonCarte ihm, ArrayList<Aventurier> aventuriers, ArrayList<CartePiece> cartes) {
        this.ihm = ihm;
        this.setTitle("Don d'une carte");
        this.setSize(new Dimension(800, 500));
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        
        this.aventuriers = aventuriers;
        this.cartes = cartes;
        
        panelCenterAv.add(new JLabel("Sélectionnez l'aventurier destinataire :"), BorderLayout.NORTH);
        // Affichage aventuriers
        panelCenterAv.add(panelCenterAvSelection);
        
        panelCenterCa.add(new JLabel("Sélectionnez la carte à donner :"), BorderLayout.NORTH);
        // Affichage des cartes
        panelCenterCa.add(panelCenterCaSelection);
        
        panelCenter.add(panelCenterAv);
        panelCenter.add(panelCenterCa);
        
        panelSouth.add(boutonAnnuler);
        panelSouth.add(new JLabel(""));
        panelSouth.add(new JLabel(""));
        panelSouth.add(boutonValider);
        
        mainpanel.add(panelCenter, BorderLayout.CENTER);
        mainpanel.add(panelSouth, BorderLayout.SOUTH);
        this.add(mainpanel);
        
        this.initAvProches();
        this.initCartesDonnables();
        
        
        
        boutonAnnuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                traiterClicAnnuler();
            }
        });
        boutonValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Aventurier destinataire;
                CartePiece carte;
                traiterClicValider();
            }
        });

    }
    
    public void afficherFenetre() {
        this.setVisible(true);
    }
    
    private void traiterClicAnnuler() {
        ihm.traiterClicAnnuler();
    }
    
    private void traiterClicValider() {
        for (int i = 0; i < aventuriers.size(); i++){
            if (this.boutonsAv.get(i).isSelected()){
                this.av = this.aventuriers.get(i);
            }
        }
        for (int i = 0; i < cartes.size(); i++){
            if (this.boutonsCartes.get(i).isSelected()) {
                this.ca = this.cartes.get(i);
            }
        }
        ihm.traiterClicValider(av, ca);
    }

    private void initAvProches() {
        ButtonGroup b = new ButtonGroup();
        for (Aventurier a : aventuriers) {
            JRadioButton j = new JRadioButton(a.getNom());
            panelCenterAvSelection.add(j);
            boutonsAv.add(j);
            b.add(j);
        }
    }

    private void initCartesDonnables() {
        ButtonGroup b = new ButtonGroup();
        for (CartePiece c : cartes) {
            JRadioButton j = new JRadioButton(c.getNomTresor());
            panelCenterCaSelection.add(j);
            boutonsCartes.add(j);
            b.add(j);
        }
    }
    
}
