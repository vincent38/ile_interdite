package model.carte;

public class CarteBonus extends CarteTresor {

    private String pouvoir;
    
    public CarteBonus(String pouvoir) {
        super("action_speciale");
        setPouvoir(pouvoir);
    }

    public String getPouvoir() {
        return pouvoir;
    }

    public void setPouvoir(String pouvoir) {
        this.pouvoir = pouvoir;
    }
    
    
}