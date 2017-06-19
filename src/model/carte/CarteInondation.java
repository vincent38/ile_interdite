package model.carte;

public class CarteInondation extends Carte {

    private String caseConcernee;
    
    public CarteInondation(String caseConcernee) {
        setCaseConcernee(caseConcernee);
    }

    public String getCaseConcernee() {
        return caseConcernee;
    }

    public void setCaseConcernee(String caseConcernee) {
        this.caseConcernee = caseConcernee;
    }
        
    
}