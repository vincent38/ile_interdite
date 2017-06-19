package model.carte;

public class CartePiece extends CarteTresor {

    private String nomTresor;
    
    public CartePiece(String nomTresor) {
        super("tresor");
        setNomTresor(nomTresor);
    }

    public void setNomTresor(String nomTresor) {
        this.nomTresor = nomTresor;
    }

    public String getNomTresor() {
        return nomTresor;
    }
    
    
    
}