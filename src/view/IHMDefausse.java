/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.Observable;
import model.Message;
import model.TypeMessage;

/**
 *
 * @author baeijsj
 */
public class IHMDefausse extends Observable{
    
    private FenetreDefausse fd;
    
    public IHMDefausse(){
        
        fd = new FenetreDefausse(this);
        fd.visible();
        
    }

    void traiterCliqueValider() {
        this.setChanged();
        this.notifyObservers(new Message(TypeMessage.CLIC_BTN_VALIDER_DEFAUSSE));
        this.clearChanged();
    }
    
}
