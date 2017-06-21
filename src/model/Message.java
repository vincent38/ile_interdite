/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import util.Utils;

/**
 *
 * @author sangj
 */
public class Message {

    public TypeMessage type;
    public int x, y;
    public TypeTresor typeTresor;
    
    public Message(TypeMessage type){
        this.type = type;
    }
    
    public Message(TypeMessage type, int x, int y){
        this.type = type;
        this.x = x; this.y = y;
        
    }

    public Message(TypeMessage typeMessage, Tresor tresor) {
        
    }

    public Message(TypeMessage typeMessage, TypeTresor typeTresor) {
        this.type = typeMessage;
        this.typeTresor = typeTresor;
    }
}
