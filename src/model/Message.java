/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.aventurier.Aventurier;
import model.carte.CartePiece;
import util.Utils;

/**
 *
 * @author sangj
 */
public class Message {

    public TypeMessage type;
    public int x, y;
    public TypeTresor typeTresor;
    
    // Pour le don de carte
    private Aventurier a;
    private CartePiece c;
    
    public Message(TypeMessage type){
        this.type = type;
    }
    
    public Message(TypeMessage type, int x, int y){
        this.type = type;
        this.x = x; this.y = y;
        
    }

    /*public Message(TypeMessage typeMessage, Tresor tresor) {
        
    }*/
    
    public Message(TypeMessage type, Aventurier a, CartePiece c) {
        this.type = type;
        this.a = a;
        this.c = c;
    }
    
    public Aventurier getAventurier() {
        return a;
    }
    
    public CartePiece getCarte() {
        return c;
    }

}
