package model;

public class Pilote extends Aventurier {
	private boolean pouvoirDispo;

    public Pilote(Tuile position) {
        super(position);
    }

	public void setPouvoirNonDispo() {
		throw new UnsupportedOperationException();
	}
}