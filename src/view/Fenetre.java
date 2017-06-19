/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import model.Grille;
import model.Observateur;
import model.Tuile;
import model.aventurier.Aventurier;

/**
 *
 * @author sangj
 */
public class Fenetre extends JFrame{
    
    private IHMBonne ihm;
    
    private Aventurier joueur;
    
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
    
    private JLabel nomJoueur;
    private JLabel classeJoueur;
    private JLabel nbAct;
    
    private int nbActRestantes;
    
    
    

    Fenetre(IHMBonne ihm, Aventurier firstJoueur, int nbActRestantes) {
        super("L'Île interdite");
        this.ihm = ihm;
        
        this.joueur = firstJoueur;
        
        this.setSize(500,500);
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
            cases[i][0] = new JPanel();
            cases[i][5] = new JPanel();
            boutonsCases[i][0] = new JButton();
            boutonsCases[i][5] = new JButton();
        }
        for(int i = 1; i <= 4; i++){
            cases[i][1] = new JPanel();
            cases[i][4] = new JPanel();
            
            boutonsCases[i][1] = new JButton();
            boutonsCases[i][4] = new JButton();
        }
        for(int i = 0; i <= 5; i++){
            cases[i][2] = new JPanel();
            cases[i][3] = new JPanel();
            
            boutonsCases[i][2] = new JButton();
            boutonsCases[i][3] = new JButton();
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
                    switch(this.ihm.getGrille().getTuile(i, j).getEtatTuile()){
                        case Tuile.ETAT_TUILE_COULEE:
                            boutonsCases[j][i].setBackground(Color.cyan);
                            break;
                        case Tuile.ETAT_TUILE_INONDEE:
                            boutonsCases[j][i].setBackground(Color.YELLOW);
                            break;
                        default:
                            boutonsCases[j][i].setBackground(Color.ORANGE);
                            break;
                    }
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
        
        this.nbActRestantes = nbActRestantes;
        
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

    
    public void traiterClicCase(int i, int j){
        ihm.traiterClicCase(i, j);
    }
    
    public void traiterClicDeplacer(){
        ihm.traiterClicDeplacer();
    }
    
    public void traiterClicAssecher(){
        ihm.traiterClicAssecher();
    }
    
    public void traiterClicAutreAction(){
        ihm.traiterClicAutreAction();
    }
    
    public void traiterClicFinTour(){
        ihm.traiterClicFinTour();
    }
    
    public void afficherJoueur() {
        nomJoueur.setText(joueur.getNom());
        classeJoueur.setText(joueur.getType());
        nbAct.setText("" + nbActRestantes);
    }

    void afficherAventuriers(ArrayList<Aventurier> aventuriers, Grille g) {
        for(Aventurier a : aventuriers){
            Tuile t = a.getTuile();
            for(int i = 0; i <= 6; i++){
                for(int j = 0; j <= 6; j++){
                    if (t == g.getTuile(i, j)){
                        this.boutonsCases[j][i].setBackground(a.getColor());
                    }
                    if(g.getTuile(i,j) != null)
                    this.boutonsCases[j][i].setText(g.getTuile(i,j).getNom());
                }
            }
            //afficher l'aventurier en x,
        }
        
    }

    void deplacement(Aventurier av, int xa, int ya, int xn, int yn) {
        this.setEtatTuile(this.ihm.getGrille().getTuile(xa, ya).getEtatTuile(), xa, ya);
        this.boutonsCases[yn][xn].setBackground(av.getColor());
        
    }

    void setNbAct(int i) {
        this.nbAct.setText(""+i);
    }

    void setAventurier(Aventurier av) {
        this.nomJoueur.setText(av.getNom());
        this.classeJoueur.setText(av.getType());
    }

    void enable(int x, int y) {
        this.boutonsCases[y][x].setEnabled(true);
    }

    void disableBoutons() {
        for(JButton[] js: this.boutonsCases){
            for(JButton j : js){
                if(j != null)
                   j.setEnabled(false);
            }
        }
    }


    void setEtatTuile(int etatTuile, int x, int y) {
        switch(etatTuile){
            case Tuile.ETAT_TUILE_COULEE:
                this.boutonsCases[y][x].setBackground(Color.cyan);
                break;
            case Tuile.ETAT_TUILE_INONDEE:
                this.boutonsCases[y][x].setBackground(Color.YELLOW);
                break;
            default:
                this.boutonsCases[y][x].setBackground(Color.ORANGE);
                break;
        }
        
    }
    
}
