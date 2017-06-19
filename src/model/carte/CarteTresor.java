package model.carte;

public class CarteTresor extends Carte {
    
    private String typeCarte;
    
    public CarteTresor(String typeCarte) {
        setTypeCarte(typeCarte);
    }

    public String getTypeCarte() {
        return typeCarte;
    }

    public void setTypeCarte(String typeCarte) {
        this.typeCarte = typeCarte;
    }
     
    
}