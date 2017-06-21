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
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author calixtee
 */
public class FenetreDonCarte extends JFrame {
    
    private IHMDonCarte ihm;
    
    private JPanel mainpanel = new JPanel(new BorderLayout());
    private JPanel panelSouth = new JPanel(new GridLayout(1,4));
    
    private JButton boutonAnnuler = new JButton("Annuler");
    private JButton boutonValider = new JButton("Valider");
    

    FenetreDonCarte(IHMDonCarte ihm) {
        this.ihm = ihm;
        this.setTitle("Don d'une carte");
        this.setSize(new Dimension(800, 500));
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        
        panelSouth.add(boutonAnnuler);
        panelSouth.add(new JLabel(""));
        panelSouth.add(new JLabel(""));
        panelSouth.add(boutonValider);
        
        mainpanel.add(panelSouth, BorderLayout.SOUTH);
        this.add(mainpanel);
        
        boutonAnnuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                traiterClicAnnuler();
            }
        });
        boutonValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        ihm.traiterClicValider();
    }
    
}
