/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.Observable;

/**
 *
 * @author calixtee
 */
public class IHMDonCarte extends Observable {
    private FenetreDonCarte fenetre;
    
    public IHMDonCarte() {
        fenetre = new FenetreDonCarte(this);
    }
}
