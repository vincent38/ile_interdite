package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import util.Parameters;
 
public class VueNiveau {
    
    private Integer niveau ;
    //private final JFrame window ;
    HashMap<Integer, JPanel> panelsGauches ;
    Integer cellWidth = 50 ;
    Integer cellHeight = (Parameters.HAUTEUR_AUTRES_VUES - 25 - (Parameters.UNDECORATED ? 0 : Parameters.DECORATION_HEIGHT)) / 10 ;
    private final JPanel mainPanel;
        
    public VueNiveau(Integer niveauInitial) {
        this.niveau = niveauInitial;
        panelsGauches = new HashMap<>();
        
        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(new BorderLayout());
        this.mainPanel.setBackground(Color.WHITE);
        this.mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));
        
        JLabel labelTitre = new JLabel("Niveau", JLabel.CENTER);
        this.mainPanel.add(labelTitre, BorderLayout.NORTH);
        // labelTitre.setFont(labelTitre.getFont().deriveFont(Font.BOLD));
        labelTitre.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 14));
        
        JPanel panelNiveaux = new JPanel(new GridBagLayout());
        panelNiveaux.setOpaque(false);
        mainPanel.add(panelNiveaux, BorderLayout.CENTER);
        
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 2 ;
        c.weighty = 10 ;
        c.insets = new Insets(0, 0, 0, 0);
        c.fill = GridBagConstraints.VERTICAL ;
        
        // Insertion de la cellule gauche de niveauInitial 10
        for (int i=0; i < 10; i++) {
            c.gridx = 0 ;
            c.gridy = i ;
            JPanel panelGauche = new JPanel();
            panelGauche.setLayout(new BoxLayout(panelGauche, BoxLayout.Y_AXIS));
            panelGauche.setBackground(getBgColor(10-i));
            panelGauche.setPreferredSize(new Dimension(cellWidth, cellHeight));
            if (i < 9) {
                panelGauche.setBorder(new MatteBorder(0, 0, 1, 0, Color.WHITE));
            } else {
                panelGauche.setBorder(new MatteBorder(1, 0, 0, 0, Color.WHITE));
            }

            panelNiveaux.add(panelGauche, c);

            JLabel labelGauche = new JLabel("", JLabel.LEFT);
            labelGauche.setPreferredSize(new Dimension(cellWidth, cellHeight));
            labelGauche.setForeground(i==0 ? new Color(223, 168, 169) : Color.BLACK);
            labelGauche.setFont(new Font(labelGauche.getFont().getFamily(), labelGauche.getFont().getStyle(), 8));
            labelGauche.setText(getLibelle(10-i));
            panelGauche.add(labelGauche);
            panelsGauches.put((10-i), panelGauche) ;
        }
            
        // Insertion de la cellule droite de niveauInitial 10
        for (int iPanel=0; iPanel < 4; iPanel++) {
            c.gridx = 1 ;
            c.gridy = (iPanel==0 ? 0 : (iPanel==1 ? 3 : (iPanel==2 ? 5 : 8))) ;
            c.gridheight = (iPanel==0 || iPanel==2 ? 3 : 2) ;
            JPanel panelDroit = new JPanel();
            panelDroit.setPreferredSize(new Dimension(cellWidth, cellHeight));
            panelDroit.setLayout(new GridBagLayout());
            panelNiveaux.add(panelDroit, c);

            JLabel labelDroit;
            switch (iPanel) {
                case 0:
                    panelDroit.setBackground(getBgColor(10));
                    labelDroit = new JLabel("5", JLabel.CENTER) ;
                    break;
                case 1:
                    panelDroit.setBackground(getBgColor(7));
                    labelDroit = new JLabel("4", JLabel.CENTER) ;
                    break;
                case 2:
                    panelDroit.setBackground(getBgColor(5));
                    labelDroit = new JLabel("3", JLabel.CENTER) ;
                    break;
                default:
                    panelDroit.setBackground(getBgColor(1));
                    labelDroit = new JLabel("2", JLabel.CENTER) ;
                    break;
            }
            labelDroit.setPreferredSize(new Dimension(cellWidth, cellHeight));
            labelDroit.setForeground(Color.WHITE);
            labelDroit.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 40));
            GridBagConstraints gbc = new GridBagConstraints();
            panelDroit.add(labelDroit, gbc);
        }
        panelsGauches.get(niveauInitial).setBackground(Color.YELLOW);
    }

    public void setNiveau(Integer niveau) {
        System.out.println("VueNiveau_nopic.setNiveau(" + niveau + ")");
        panelsGauches.get(this.niveau).setBackground(getBgColor(this.niveau - 1));
        this.niveau = niveau ;
        panelsGauches.get(this.niveau).setBackground(this.niveau == 10 ? Color.RED : Color.YELLOW);
        this.mainPanel.repaint();
    }

    public Integer getNiveau() {
        return this.niveau ;
    }

    public Integer getColoredNiveau() {
        for (Integer coloredNiveau : panelsGauches.keySet()) {
            if (panelsGauches.get(coloredNiveau).getBackground() == Color.YELLOW) {
                return coloredNiveau;
            }
        }
        return -1 ;
    }

    private Color getBgColor(Integer niveau) {
        if (niveau <= 2)
            return new Color(169, 215, 226) ;
        
        if (niveau <= 5)
            return new Color(129, 194, 212) ;
            
        
        if (niveau <= 7)
            return new Color(67, 119, 204) ;
        
        return new Color(42, 76, 127) ;
    }

    private String getLibelle(int i) {
        switch (i) {
            case 1 : 
                return " novice" ;
            case 2 : 
                return " normal" ;
            case 3 : 
                return " élite" ;
            case 4 : 
                return " légendaire" ;
            case 10 : 
                return " mortel" ;
            default :
                return "" ;
        }
    }
    
    public static void main(String[] args) {   
        VueNiveau vueNiveau = new VueNiveau(1);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Pour passer au niveau 5, appuyer sur entrée");
        String suite = scanner.nextLine();        
        vueNiveau.setNiveau(5);

        System.out.println("Pour passer au niveau 5, appuyer sur entrée");
        suite = scanner.nextLine();
        vueNiveau.setNiveau(10);
    }    

    public JPanel getMainPanel() {
        return mainPanel;
    }
    
    
}