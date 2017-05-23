package model;

import java.util.Vector;

public abstract class Aventurier {
	public Vector<Tresor> _tresorsObtenus = new Vector<Tresor>();
	public Vector<CarteTresor> _cartes = new Vector<CarteTresor>();
	public Tuile _positionCourante;

	public void deplacer(Object aDirection) {
		throw new UnsupportedOperationException();
	}

	public void assecher(Tuile aT) {
		throw new UnsupportedOperationException();
	}

	public void donnerCarte() {
		throw new UnsupportedOperationException();
	}

	public void getTuile() {
		throw new UnsupportedOperationException();
	}

	public Collection<Tuile> getTuilesPossibles(Grille aGrille) {
		throw new UnsupportedOperationException();
	}

	public void setTuile(Tuile aTuile) {
		throw new UnsupportedOperationException();
	}

	public boolean getInondee() {
		throw new UnsupportedOperationException();
	}

	public void getTuilesAssechables() {
		throw new UnsupportedOperationException();
	}
}