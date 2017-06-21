/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import java.util.Observable;
import model.Message;
import model.Observateur;
import model.TypeMessage;

/**
 *
 * @author baeijsj
 */
public class IHMselectionJoueur extends Observable{
    
    private FenetreJoueur fj;    
    
    public IHMselectionJoueur(){
        
        fj = new FenetreJoueur(this);
        fj.visible();
    }
    
    public ArrayList<String> getNomsJoueurs(){
       return fj.getNomsJoueurs();
    }
    
    public void fermerFenetre(){
        fj.dispose();
    }
    
    public void traiterClicCommencer(){
        this.setChanged();
        this.notifyObservers(new Message(TypeMessage.CLIC_COMMENCER));
        this.clearChanged();
    }
    
}
        
        