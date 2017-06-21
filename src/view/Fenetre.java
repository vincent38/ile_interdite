/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import model.Grille;
import model.Tresor;
import model.Tuile;
import model.TypeTresor;
import model.aventurier.Aventurier;
import model.carte.CarteBonus;
import model.carte.CartePiece;
import model.carte.CarteTresor;

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
    private JPanel niveauEauPane;
    private JPanel cartesEtTresorsPane;
    private JPanel paneCartes;
    private JPanel paneWest;
    
    private JPanel[][] cases;
    private JButton[][] boutonsCases;
    private JPanel[][] conteneursAventuriers;
    
    private JButton boutonDeplacer;
    private JButton boutonAssecher;
    private JButton boutonDonnerCarte;
    private JButton boutonFinTour;
    
    private JButton boutonCalice;
    private JButton boutonCristal;
    private JButton boutonPierre;
    private JButton boutonZephyr;
    private JPanel paneBoutonsTresors;
    
    private JLabel nomJoueur;
    private JLabel classeJoueur;
    private JLabel nbAct;
    
    private int nbActRestantes;
    
    private HashMap<Aventurier, JPanel> paneAventuriers;
    private JPanel paneTresors;
    private VueNiveau vueNiv;
    
    
    

    Fenetre(IHMBonne ihm, Aventurier firstJoueur, int nbActRestantes, ArrayList<Aventurier> aventuriers) {
        super("L'Île interdite");
        this.ihm = ihm;
        this.initAventuriers(aventuriers);
        this.joueur = firstJoueur;
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.initCases();
        this.initActionPane();
        this.initJoueursPane();
        this.afficherJoueurPane();
        this.afficherNiveauPane();
        this.initPaneWest();
        
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
        ihm.traiterClicDonCarte();
    }
    
    public void traiterClicFinTour(){
        ihm.traiterClicFinTour();
    }
    
    public void afficherJoueurPane() {
        nomJoueur.setText(joueur.getNom());
        classeJoueur.setText(joueur.getType());
        nbAct.setText("" + nbActRestantes);
    }

    void afficherAventuriers(Grille g) {
        for(Aventurier a : paneAventuriers.keySet()){
            Tuile t = a.getTuile();
            for(int i = 0; i <= 6; i++){
                for(int j = 0; j <= 6; j++){
                    if (t == g.getTuile(i, j)){
                        this.conteneursAventuriers[j][i].add(paneAventuriers.get(a));
                    }
                    if(g.getTuile(i,j) != null)
                    this.boutonsCases[j][i].setText(g.getTuile(i,j).getNom());
                }
            }
        }
        
    }

    void actualiseAventuriers(Grille g) {
        this.rmAventuriers();
        this.afficherAventuriers(g);
        
        
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
                this.boutonsCases[y][x].setBackground(new Color(34, 66, 124));
                break;
            case Tuile.ETAT_TUILE_INONDEE:
                this.boutonsCases[y][x].setBackground(new Color(30, 127, 203));
                break;
            default:
                this.boutonsCases[y][x].setBackground(new Color(240, 195, 0));
                break;
        }
        
    }

    private void initAventuriers(ArrayList<Aventurier> aventuriers) {
        this.paneAventuriers = new HashMap();
        for (Aventurier a : aventuriers){
            JPanel j = new JPanel();
            j.setBackground(a.getColor());
            this.paneAventuriers.put(a, j);
        }
    }

    private void rmAventuriers() {
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                for (JPanel k : paneAventuriers.values()){
                    if (conteneursAventuriers[j][i] != null){
                        conteneursAventuriers[j][i].remove(k);
                    }
                }
            }
            
        }
        this.getContentPane().repaint();
        
    }

    void actualiseTuiles(Grille g) {
        for (int x = 0; x <6; x++){
            for (int y = 0; y < 6; y++){
                if (this.cases[x][y] != null){
                    this.setEtatTuile(g.getTuile(x, y).getEtatTuile(), x, y);
                }
            }
        }
    }
    
    public void disableInteraction(){
        boutonDeplacer.setEnabled(false);
        boutonAssecher.setEnabled(false);
        boutonDonnerCarte.setEnabled(false);
        boutonFinTour.setEnabled(false);
    }
    
    public void enableInteraction(){
        boutonDeplacer.setEnabled(true);
        boutonAssecher.setEnabled(true);
        boutonDonnerCarte.setEnabled(true);
        boutonFinTour.setEnabled(true);
    }

    void setNiveau(int niv) {
        this.vueNiv.setNiveau(niv);
        this.content.repaint();
    }

    private void afficherNiveauPane() {
        this.niveauEauPane = new JPanel(new GridLayout(1,1));
        this.content.add(niveauEauPane, BorderLayout.EAST);
        this.vueNiv = new VueNiveau(1);
        this.niveauEauPane.add(vueNiv.getMainPanel());
    }

    private void initCases() {
        content = new JPanel();
        this.setContentPane(content);
        content.setLayout(new BorderLayout());
        
        casesPane = new JPanel();
        casesPane.setLayout(new GridLayout(6,6));
        content.add(casesPane, BorderLayout.CENTER);
        
        
        
        
        cases = new JPanel[6][6];
        boutonsCases = new JButton[6][6];
        conteneursAventuriers = new JPanel[6][6];
        
        for(int i = 2; i <= 3; i++){
            cases[i][0] = new JPanel();
            cases[i][5] = new JPanel();
            
            boutonsCases[i][0] = new JButton();
            boutonsCases[i][5] = new JButton();
            
            conteneursAventuriers[i][0] = new JPanel();
            conteneursAventuriers[i][5] = new JPanel();
        }
        for(int i = 1; i <= 4; i++){
            cases[i][1] = new JPanel();
            cases[i][4] = new JPanel();
            
            boutonsCases[i][1] = new JButton();
            boutonsCases[i][4] = new JButton();
            
            conteneursAventuriers[i][1] = new JPanel();
            conteneursAventuriers[i][4] = new JPanel();
        }
        for(int i = 0; i <= 5; i++){
            cases[i][2] = new JPanel();
            cases[i][3] = new JPanel();
            
            boutonsCases[i][2] = new JButton();
            boutonsCases[i][3] = new JButton();
            
            conteneursAventuriers[i][2] = new JPanel();
            conteneursAventuriers[i][3] = new JPanel();
        }
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                if (cases[i][j] != null){
                    casesPane.add(cases[i][j]);
                    cases[i][j].setLayout(new BorderLayout());
                    cases[i][j].add(boutonsCases[i][j], BorderLayout.CENTER);
                    cases[i][j].add(conteneursAventuriers[i][j] , BorderLayout.NORTH);
                    
                    
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
                            boutonsCases[j][i].setBackground(new Color(34, 66, 124));
                            break;
                        case Tuile.ETAT_TUILE_INONDEE:
                            boutonsCases[j][i].setBackground(new Color(30, 127, 203));
                            break;
                        default:
                            boutonsCases[j][i].setBackground(new Color(240, 195, 0));
                            break;
                    }
                }
                else{
                    casesPane.add(new JPanel());
                }
            }
        }
    }

    private void initActionPane() {
        choixActionPane = new JPanel();
        content.add(choixActionPane, BorderLayout.SOUTH);
        
        choixActionPane.setLayout(new GridLayout(2,2));
        boutonDeplacer = new JButton("Se deplacer");
        boutonAssecher = new JButton("Assecher");
        boutonDonnerCarte = new JButton("Donner une carte");
        boutonFinTour = new JButton("Fin de tour");
        
        choixActionPane.add(boutonDeplacer);
        choixActionPane.add(boutonAssecher);
        choixActionPane.add(boutonDonnerCarte);
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
        
        boutonDonnerCarte.addActionListener(new ActionListener() {
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
    }

    private void initJoueursPane() {
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
    }

    private void initCartesEtTResorsPane() {
        this.cartesEtTresorsPane = new JPanel(new BorderLayout());
        this.paneWest.add(cartesEtTresorsPane, BorderLayout.CENTER);
        this.paneTresors = new JPanel(new GridLayout(2,2));
        
        this.paneCartes = new JPanel(new GridLayout(1,5));
        this.cartesEtTresorsPane.add(paneTresors, BorderLayout.NORTH);
        this.cartesEtTresorsPane.add(paneCartes, BorderLayout.SOUTH);
        
        
        
        //debug graphique
        //this.cartesEtTresorsPane.setBackground(Color.red);
        //this.panelTresors.setBackground(Color.red);
    }

    private void initPaneBoutonsTresors() {
        this.paneBoutonsTresors = new JPanel(new GridLayout(4,1));
        this.paneWest.add(paneBoutonsTresors, BorderLayout.WEST);
        this.boutonCalice = new JButton("Récupérer le Calice");
        this.boutonCristal = new JButton("Récupérer le Crystal");
        this.boutonPierre = new JButton("Récupérer la Pierre");
        this.boutonZephyr = new JButton("Récupérer le Zéphyr");
        
        this.boutonCalice.setEnabled(false);
        this.boutonCristal.setEnabled(false);
        this.boutonPierre.setEnabled(false);
        this.boutonZephyr.setEnabled(false);
        
        this.paneBoutonsTresors.add(boutonCalice);
        this.paneBoutonsTresors.add(boutonCristal);
        this.paneBoutonsTresors.add(boutonPierre);
        this.paneBoutonsTresors.add(boutonZephyr);
        
        this.boutonCalice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                traiterClicTresor();
            }
        });
        
        this.boutonCristal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                traiterClicTresor();
            }
        });
        
        this.boutonPierre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                traiterClicTresor();
            }
        });
        
        this.boutonZephyr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                traiterClicTresor();
            }
        });
        
        //debug graphique
        //this.paneBoutonsTresors.setBackground(Color.red);
    }
    
    private void traiterClicTresor(){
        ihm.traiterClicTresor();
    }

    private void initPaneWest() {
        this.paneWest = new JPanel(new BorderLayout());
        this.content.add(paneWest, BorderLayout.WEST);
        
        
        this.initCartesEtTResorsPane();
        this.initPaneBoutonsTresors();
        
        //debug graphique
        //this.paneWest.setBackground(Color.red);
    }

    void afficherTresor(Tresor tresor) {
        JLabel img = null;
        switch(tresor.typeTresor){
            case caliceDeLOnde:
                img = new JLabel(new ImageIcon(getClass().getResource("/images/calice.png")));
                
                break;
            case cristalArdent:
                img = new JLabel(new ImageIcon(getClass().getResource("/images/cristal.png")));
                this.paneTresors.add(img);
                this.content.repaint();
                break;
            case pierreSacree:
                img = new JLabel(new ImageIcon(getClass().getResource("/images/pierre.png")));
                this.paneTresors.add(img);
                this.content.repaint();
                break;
            case statueDuZephyr:
                img = new JLabel(new ImageIcon(getClass().getResource("/images/zephyr.png")));
               
        }
        this.paneTresors.add(img);
        this.content.invalidate();
        this.paneTresors.invalidate();
        this.paneTresors.validate();
        this.content.revalidate();
    }

    void enable(TypeTresor typeTresor) {
        switch(typeTresor){
            case caliceDeLOnde:
                this.boutonCalice.setEnabled(true);
                break;
            case cristalArdent:
                this.boutonCristal.setEnabled(true);
                    break;
            case pierreSacree:
                this.boutonPierre.setEnabled(true);
                break;
            case statueDuZephyr:
                this.boutonZephyr.setEnabled(true);
                break;
        }
    }

    void disableTresors() {
        this.boutonCalice.setEnabled(false);
        this.boutonCristal.setEnabled(false);
        this.boutonPierre.setEnabled(false);
        this.boutonZephyr.setEnabled(false);
    }

    void afficherCartes(ArrayList<CarteTresor> cartes) {
        Image i = new ImageIcon(getClass().getResource("/images/Pierre.png")).getImage();
        JButton j;
        for (CarteTresor c : cartes){
            if(c.getTypeCarte().equals("tresor")){
                CartePiece d = (CartePiece) c;
                i = new ImageIcon(getClass().getResource("/images/" + d.getNomTresor() + ".png")).getImage();
            }else if(c.getTypeCarte().equals("action_speciale")){
                CarteBonus d = (CarteBonus) c;
                i = new ImageIcon(getClass().getResource("/images/" + d.getPouvoir() + ".png")).getImage();
            }//else if(c.getTypeCarte().equals){
            
            
            
            
            
            
            
            i = scaleImage(i, 100, 200);
            j = new JButton(new ImageIcon(i));
            
            this.paneCartes.add(j);
        }
    }
    
    public static Image scaleImage(Image source, int width, int height) {
    BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g = (Graphics2D) img.getGraphics();
    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    g.drawImage(source, 0, 0, width, height, null);
    g.dispose();
    return img;
    }
    
  

}
