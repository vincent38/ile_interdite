/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author sangj
 */
public class IHMBonne extends JFrame{
    private JPanel content;
    private JPanel casesPane;
    private JPanel choixActionPane;
    
    private JPanel[][] cases;
    private JButton[][] boutonsCases;
    
    private JButton boutonDeplacer;
    private JButton boutonAssecher;
    private JButton boutonAutreAction;
    
    public IHMBonne(){
        super("l'Île interdite");
        this.setSize(1000,1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        
        content = new JPanel();
        this.setContentPane(content);
        content.setLayout(new BorderLayout());
        
        
        
        casesPane = new JPanel();
        casesPane.setLayout(new GridLayout(6,6));
        content.add(casesPane, BorderLayout.CENTER);
        
        
        
        
        cases = new JPanel[6][6];
        boutonsCases = new JButton[6][6];
        
        
        for(int i = 2; i <= 3; i++){
            cases[0][i] = new JPanel();
            cases[5][i] = new JPanel();
            boutonsCases[0][i] = new JButton();
            boutonsCases[5][i] = new JButton();
        }
        for(int i = 1; i <= 4; i++){
            cases[1][i] = new JPanel();
            cases[4][i] = new JPanel();
            
            boutonsCases[1][i] = new JButton();
            boutonsCases[4][i] = new JButton();
        }
        for(int i = 0; i <= 5; i++){
            cases[2][i] = new JPanel();
            cases[3][i] = new JPanel();
            
            boutonsCases[2][i] = new JButton();
            boutonsCases[3][i] = new JButton();
        }
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                if (cases[i][j] != null){
                    casesPane.add(cases[i][j]);
                    cases[i][j].setLayout(new GridLayout(1,1));
                    cases[i][j].add(boutonsCases[i][j]);
                    
                    
                    boutonsCases[i][j].setEnabled(false);
                    boutonsCases[i][j].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            for (int i = 0; i < 6; i++){
                                for (int j = 0; j < 6; j++){
                                    if (boutonsCases[i][j] == e.getSource()){
                                        traiterClicCase(i,j);
                                    }
                                }
                            }
                        }
                    });
                }
                else{
                    casesPane.add(new JPanel());
                }
            }
        }
        
        
        choixActionPane = new JPanel();
        content.add(choixActionPane, BorderLayout.SOUTH);
        
        choixActionPane.setLayout(new GridLayout(2,2));
        boutonDeplacer = new JButton("Se deplacer");
        boutonAssecher = new JButton("Assecher");
        boutonAutreAction = new JButton("Autre action");
        
        
        this.setVisible(true);
    }
    
    private void traiterClicCase(int x, int y){
        System.out.println(x);
        System.out.println(y);
    }
    
}
