package model.carte;

import model.TypeTresor;

public class CartePiece extends CarteTresor {

    private String nomTresor;
    private TypeTresor typeTresor;
    
    public CartePiece(String nomTresor, TypeTresor type) {
        super("tresor");
        setNomTresor(nomTresor);
        setTypeTresor(type);
    }

    public void setNomTresor(String nomTresor) {
        this.nomTresor = nomTresor;
    }

    public String getNomTresor() {
        return nomTresor;
    }

    public TypeTresor getTypeTresor() {
        return typeTresor;
    }

    public void setTypeTresor(TypeTresor typeTresor) {
        this.typeTresor = typeTresor;
    }
    
    
    
}