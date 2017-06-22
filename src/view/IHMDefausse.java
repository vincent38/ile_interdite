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
import model.carte.CarteTresor;

/**
 *
 * @author baeijsj
 */
public class IHMDefausse extends Observable{
    
    private FenetreDefausse fd;
    private ArrayList<CarteTresor> cartes;

    public IHMDefausse() {
    }
    
    public IHMDefausse(ArrayList<CarteTresor> cartes){
        
        fd = new FenetreDefausse(this, cartes);
        fd.visible();
        this.cartes = cartes;
        
    }

    void traiterCliqueValider() {
        this.setChanged();
        this.notifyObservers(new Message(TypeMessage.CLIC_BTN_VALIDER_DEFAUSSE));
        this.clearChanged();
    }
    
    public void fermerFenetre(){
        fd.dispose();
    }
    
}
