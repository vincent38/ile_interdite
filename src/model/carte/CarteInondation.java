package model.carte;

public class CarteInondation extends Carte {

    private String tuileConcernee;
    
    public CarteInondation(String tuileConcernee) {
        setTuileConcernee(tuileConcernee);
    }

    public String getTuileConcernee() {
        return tuileConcernee;
    }

    public void setTuileConcernee(String tuileConcernee) {
        this.tuileConcernee = tuileConcernee;
    }
        
    
}