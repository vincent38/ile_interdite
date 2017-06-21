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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author baeijsj
 */
public class FenetreJoueur extends JFrame{
    
    private JPanel mainPanel;
    private JPanel panelCenter;
    private JPanel panelSouth;
    
    private JButton commencer;
    
    private JTextField tj1;
    private JTextField tj2;
    private JTextField tj3;
    private JTextField tj4;
    
    
    private IHMselectionJoueur sj;

    private ArrayList<String> noms;
    
    public FenetreJoueur(IHMselectionJoueur sj){
        
        this.sj = sj;
        
        this.setTitle("Sélection des joueurs");
        this.setSize(new Dimension(300, 450));
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        
        noms = new ArrayList();        
        mainPanel = new JPanel(new BorderLayout());
        panelCenter = new JPanel(new GridLayout(4, 2));
        panelSouth = new JPanel();
        
        this.add(mainPanel);
                
        mainPanel.add(panelCenter, BorderLayout.CENTER);
        mainPanel.add(panelSouth, BorderLayout.SOUTH);
        
        for (int i = 0; i <= 8; i++){
            switch (i){
                case 1:
                    JLabel j1 = new JLabel("Joueur 1 :");
                    panelCenter.add(j1);
                    break;
                
                case 2:
                    tj1 = new JTextField();
                    panelCenter.add(tj1);
                    break;
                    
                case 3:
                    JLabel j2 = new JLabel("Joueur 2 :");
                    panelCenter.add(j2);
                    break;
                    
                case 4:
                    tj2 = new JTextField();
                    panelCenter.add(tj2);
                    break;
                    
                case 5:
                    JLabel j3 = new JLabel("Joueur 3 :");
                    panelCenter.add(j3);
                    break;
                
                case 6:    
                    tj3 = new JTextField();
                    panelCenter.add(tj3);
                    break;
                    
                case 7: 
                    JLabel j4 = new JLabel("Joueur 4 :");
                    panelCenter.add(j4);
                    break;
                    
                case 8:
                    tj4 = new JTextField();
                    panelCenter.add(tj4);
                    break;
            }
            
           
        }
        
        commencer = new JButton("Commencer");
        panelSouth.add(commencer);
            
        commencer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                traiterClicCommencer();
            }
        });
        
    }
    
    public void traiterClicCommencer(){
        sj.traiterClicCommencer();
    }
    
    public ArrayList<String> getNomsJoueurs(){
        if (!tj1.getText().isEmpty()){
            noms.add(tj1.getText());
            System.out.println(tj1.getText());
        }
        if (!tj2.getText().isEmpty()){
            noms.add(tj2.getText());
            System.out.println(tj2.getText());
        }
        if (!tj3.getText().isEmpty()){
            noms.add(tj3.getText());
            System.out.println(tj3.getText());
        }
        if (!tj4.getText().isEmpty()){
            noms.add(tj4.getText());
            System.out.println(tj2.getText());
        }
        return noms;
    }

    void visible() {
        this.setVisible(true);
    }
    
}

    

