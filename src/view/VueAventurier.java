package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.border.MatteBorder;
import model.*;

 
public class VueAventurier  {
     
    private final JPanel panelBoutons ;
    private final JPanel panelCentre ;
    private final JFrame window;
    private final JPanel panelAventurier;
    private final JPanel mainPanel;
    private final JButton btnAller  ;
    private final JButton btnAssecher;
    private final JButton btnAutreAction;
    private final JButton btnTerminerTour;
    private final JTextField position;
    private Observateur observateur;
    private final JLabel typeAv;
    private final Font font;
    JLabel labelPosition;
            
    public VueAventurier (String nomJoueur, String nomAventurier, Color couleur){
        font = new Font("Arial", Font.PLAIN, 16);

        this.window = new JFrame();
        this.window.setSize(600,300);
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.window.setAlwaysOnTop(true);

        
        window.setTitle(nomJoueur);
        mainPanel = new JPanel(new BorderLayout());
        this.window.add(mainPanel);
        
        

        mainPanel.setBackground(new Color(230, 230, 230));
        mainPanel.setBorder(BorderFactory.createLineBorder(couleur, 2)) ;

        // =================================================================================
        // NORD : le titre = nom de l'aventurier + nom du joueur sur la couleurActive du pion

        this.panelAventurier = new JPanel();
        panelAventurier.setBackground(couleur);
        typeAv = new JLabel(nomAventurier,SwingConstants.CENTER );
        typeAv.setFont(font);
        typeAv.setForeground(Color.WHITE);
        panelAventurier.add(typeAv);
        mainPanel.add(panelAventurier, BorderLayout.NORTH);
   
        // =================================================================================
        // CENTRE : 1 ligne pour position courante
        this.panelCentre = new JPanel(new GridLayout(2, 1));
        this.panelCentre.setOpaque(false);
        this.panelCentre.setBorder(new MatteBorder(0, 0, 2, 0, couleur));
        mainPanel.add(this.panelCentre, BorderLayout.CENTER);
        
        labelPosition = new JLabel ("Position", SwingConstants.CENTER);
        labelPosition.setFont(font);
        labelPosition.setForeground(Color.WHITE);
        panelCentre.add(labelPosition);
        position = new  JTextField(30); 
        position.setHorizontalAlignment(CENTER);
        position.setEditable(false);
        panelCentre.add(position);


        // =================================================================================
        // SUD : les boutons
        this.panelBoutons = new JPanel(new GridLayout(2,2));
        this.panelBoutons.setOpaque(false);
        mainPanel.add(this.panelBoutons, BorderLayout.SOUTH);

        this.btnAller = new JButton("Aller") ;
        this.btnAssecher = new JButton( "Assecher");
        this.btnAutreAction = new JButton("Autre Action") ;
        this.btnTerminerTour = new JButton("Terminer Tour") ;
        
        btnAller.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                observateur.traiterMessage(TypeMessage.CLIC_BTN_ALLER);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
               
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        
        btnAssecher.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                observateur.traiterMessage(TypeMessage.CLIC_BTN_ASSECHER);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
               
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        
        btnAutreAction.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                observateur.traiterMessage(TypeMessage.CLIC_BTN_AUTRE_ACTION);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
               
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        
        btnTerminerTour.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                observateur.traiterMessage(TypeMessage.CLIC_BTN_TERMINER_TOUR);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
               
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        
        this.panelBoutons.add(btnAller);
        this.panelBoutons.add(btnAssecher);
        this.panelBoutons.add(btnAutreAction);
        this.panelBoutons.add(btnTerminerTour);

        this.window.setVisible(true);
        mainPanel.repaint();
    }  

     public JButton getBtnAutreAction() {
        return btnAutreAction;
    }

    public void setPosition(String pos) {
        this.position.setText(pos);
    }

    public JButton getBtnAller() {
        return btnAller;
    }
    
    public JButton getBtnAssecher() {
        return btnAssecher;
    }

    public JButton getBtnTerminerTour() {
        return btnTerminerTour;
    }
 

    
     /*public static void main(String [] args) {
         Instanciation de la fenêtre 
        VueAventurier vueAventurier = new VueAventurier ("Janot", "Explorateur",Pion.ROUGE.getCouleur() );
    }*/

    public void setObservateur(Observateur observateur) {
        this.observateur = observateur;
    }

    public void setWindowTitle(String titre) {
        this.window.setTitle(titre);
    }
    
    public void setTypeAv(String t){
        this.typeAv.setText(t);
    }

    public void setColor(Color color) {
        this.mainPanel.setBackground(color);
        this.panelAventurier.setBackground(color);
    }
    
    public void setFontColor(Color color){
        this.typeAv.setForeground(color);
        this.labelPosition.setForeground(color);
    }
    
    
     
}