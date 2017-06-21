/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import java.util.Observable;
import model.Message;
import model.TypeMessage;
import model.aventurier.Aventurier;
import model.carte.CartePiece;
import model.carte.CarteTresor;

/**
 *
 * @author calixtee
 */
public class IHMDonCarte extends Observable {
    private FenetreDonCarte fenetre;
    
    public IHMDonCarte(ArrayList<Aventurier> aventuriers, ArrayList<CartePiece> cartes) {
        fenetre = new FenetreDonCarte(this, aventuriers, cartes);
    }

    public void traiterClicAnnuler() {
        this.setChanged();
        this.notifyObservers(new Message(TypeMessage.CLIC_BTN_ANNULER_DON_CARTE));
        this.clearChanged();
    }

    public void traiterClicValider(Aventurier a, CartePiece c) {
        this.setChanged();
        this.notifyObservers(new Message(TypeMessage.CLIC_BTN_VALIDER_DON_CARTE, a, c));
        this.clearChanged();
    }
    
    public void afficherFenetre() {
        fenetre.afficherFenetre();
    }

    public void cacherFenetre() {
        fenetre.dispose();
    }
}
