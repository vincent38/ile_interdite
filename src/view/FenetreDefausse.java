/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author baeijsj
 */
public class FenetreDefausse extends JFrame{
    
    IHMDefausse ihm;
    
    JPanel mainPanel;
    JPanel panelSouth;
    
    JButton valider;
    
    public FenetreDefausse(IHMDefausse ihm){
        
        this.ihm = ihm;
        
        mainPanel = new JPanel(new BorderLayout());
        panelSouth = new JPanel();
        
        this.add(mainPanel);
        mainPanel.add(panelSouth, BorderLayout.SOUTH);
        
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
