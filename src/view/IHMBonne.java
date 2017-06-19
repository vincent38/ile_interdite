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
import model.Observateur;
import model.aventurier.Aventurier;

/**
 *
 * @author sangj
 */
public class IHMBonne extends JFrame{
    private Fenetre fenetre;
    private Observateur observateur;
    
    private JPanel content;
    private JPanel casesPane;
    private JPanel choixActionPane;
    private JPanel joueurPane;
    
    private JPanel[][] cases;
    private JButton[][] boutonsCases;
    
    private JButton boutonDeplacer;
    private JButton boutonAssecher;
    private JButton boutonAutreAction;
    private JButton boutonFinTour;
    
    private Aventurier joueur;
    private int nbActRestantes;
    private JLabel nomJoueur;
    private JLabel classeJoueur;
    private JLabel nbAct;
    
    public IHMBonne(Observateur o, Aventurier firstJoueur, int nbActRestantes){
        super("l'Île interdite");
        
        fenetre = new Fenetre(o, firstJoueur, nbActRestantes);
        
        this.setSize(1000,1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        
        
        this.observateur = o;
        joueur = firstJoueur;
        this.nbActRestantes = nbActRestantes;
        
        
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
        boutonFinTour = new JButton("Fin de tour");
        
        choixActionPane.add(boutonDeplacer);
        choixActionPane.add(boutonAssecher);
        choixActionPane.add(boutonAutreAction);
        choixActionPane.add(boutonFinTour);
        
        boutonDeplacer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                traiterClicDeplacer();
            }
        });
        
        boutonAssecher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                traiterClicAssecher();
            }
        });
        
        boutonAutreAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                traiterClicAutreAction();
            }
        });
        
        boutonFinTour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                traiterClicFinTour();
            }
        });
        
        joueurPane = new JPanel(new GridLayout(1,3));
        content.add(joueurPane, BorderLayout.NORTH);
        this.nomJoueur = new JLabel();
        this.classeJoueur = new JLabel();
        this.nbAct = new JLabel();
        
        
        joueurPane.add(nomJoueur);
        joueurPane.add(classeJoueur);
        joueurPane.add(nbAct);
        
        nomJoueur.setFont(new Font("Arial", Font.PLAIN, 30));
        classeJoueur.setFont(new Font("Arial", Font.PLAIN, 30));
        nbAct.setFont(new Font("Arial", Font.PLAIN, 30));
        
        nomJoueur.setHorizontalAlignment(SwingConstants.CENTER);
        classeJoueur.setHorizontalAlignment(SwingConstants.CENTER);
        nbAct.setHorizontalAlignment(SwingConstants.CENTER);
        
        
        
        this.afficherJoueur();
        
        
        
        this.setVisible(true);
    }
    
    private void traiterClicCase(int x, int y){
        System.out.println(x);
        System.out.println(y);
    }
    

    public static void choisirDestinataireEtCarte(ArrayList<Aventurier> aventuriersMemeTuile, ArrayList<CarteTresor> cartesPossedees) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void traiterClicDeplacer(){
        System.out.println("clic deplacer");
    }
    
    private void traiterClicAssecher(){
        System.out.println("clic assecher");
    }
    
    private void traiterClicAutreAction(){
        System.out.println("clic autre action");
    }
    
    private void traiterClicFinTour(){
        System.out.println("clic fin de tour");
    }

    private void afficherJoueur() {
        nomJoueur.setText(joueur.getNom());
        classeJoueur.setText(joueur.getType());
        nbAct.setText("" + nbActRestantes);
    }
    
}
