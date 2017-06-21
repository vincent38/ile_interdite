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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.carte.CarteTresor;

/**
 *
 * @author baeijsj
 */
public class FenetreDefausse extends JFrame{
    
    
    IHMDefausse ihm;
    
    JPanel mainPanel;
    JPanel panelSouth;
    JPanel panelCenter;
    
    JButton valider;
    ArrayList<CarteTresor> cartes;
    
    public FenetreDefausse(IHMDefausse ihm, ArrayList<CarteTresor> cartes){
        
        this.ihm = ihm;
        this.cartes = cartes;
        
        
        
        mainPanel = new JPanel(new BorderLayout());
        panelSouth = new JPanel();
        panelCenter = new JPanel(new GridLayout(cartes.size(), 2));
        
        
        this.add(mainPanel);
        mainPanel.add(panelSouth, BorderLayout.SOUTH);
        mainPanel.add(panelCenter, BorderLayout.CENTER);
        
        valider = new JButton("Valider");
        
        panelSouth.add(valider);
        
        this.setTitle("Défausse");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setSize(new Dimension(500, 300));
        this.setResizable(false);
        
        valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                traiterCliqueValider();
            }
        });
    }
    
    public void visible(){
        this.setVisible(true);
    }
    
    private void traiterCliqueValider() {
        ihm.traiterCliqueValider();
    }
}
