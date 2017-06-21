/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author calixtee
 */
public class FenetreDonCarte extends JFrame {
    
    private IHMDonCarte ihm;

    FenetreDonCarte(IHMDonCarte ihm) {
        this.ihm = ihm;
        this.setTitle("Sélection des joueurs");
        this.setSize(new Dimension(800, 500));
        this.setAlwaysOnTop(true);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }
    
}
