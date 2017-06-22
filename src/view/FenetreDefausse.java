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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import model.Message;
import model.TypeMessage;
import model.carte.Carte;
import model.carte.CartePiece;
import model.carte.CarteTresor;

/**
 *
 * @author baeijsj
 */
public class FenetreDefausse extends JFrame{
    
    
    private IHMDefausse ihm;
    
    private JPanel mainPanel;
    private JPanel panelSouth;
    private JPanel panelCenter;
    
    private JButton valider;
    
    private ArrayList<CarteTresor> cartes;
    
    private ArrayList<JRadioButton> boutonsCartes;
    private ButtonGroup grpBoutonsCartes;
    
    public FenetreDefausse(IHMDefausse ihm, ArrayList<CarteTresor> cartes){
        
        this.setAlwaysOnTop(true);
        
        this.ihm = ihm;
        this.cartes = cartes;
        
        
        
        
        mainPanel = new JPanel(new BorderLayout());
        panelSouth = new JPanel();
        panelCenter = new JPanel(new GridLayout(cartes.size(), 1));
        
        this.boutonsCartes = new ArrayList();
        this.grpBoutonsCartes = new ButtonGroup();
        
        
        for (CarteTresor c : cartes){
            this.addCarte(c);
        }
        this.boutonsCartes.get(0).setSelected(true);
        
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
                traiterCliValider();
            }
        });
    }
    
    public void visible(){
        this.setVisible(true);
    }
    
    private void traiterCliValider() {
        for(int i = 0; i < this.boutonsCartes.size(); i++){
            if(this.boutonsCartes.get(i).isSelected()){
                this.ihm.traiterMessage(new Message(TypeMessage.CLIC_BTN_VALIDER_DEFAUSSE, this.cartes.get(i)));
            }
        }
    }

    private void addCarte(CarteTresor c) {
        if (c.getTypeCarte().equals("tresor")){
            CartePiece d = (CartePiece) c;
            JRadioButton j = new JRadioButton(d.getNomTresor());
            this.boutonsCartes.add(j);
            this.grpBoutonsCartes.add(j);
            this.panelCenter.add(j);
        }
    }
}
