package Partie;

import static org.junit.Assert.*;
import java.util.Random;
import org.junit.Test;

public class MainJoueurTest {

	@Test
	public void testMainJoueur() { // Teste que l'odre des cartes est bien croissant
		Random r = new Random();
		MainJoueur.cartesJoueur(r); // Teste en plus carteJoueur()
		MainJoueur m = new MainJoueur();
		for(int i = 0; i<m.getMain().size()-1; i++) {
			assertFalse(m.getMain().get(i).getNum�ro() > m.getMain().get(i+1).getNum�ro());
		}
	}

	@Test
	public void testClearNum�ro() {
		for(int i = 0; i < MainJoueur.cartesMax; i++) {
			MainJoueur.getNum�ro().remove(0);
		}
		Random r = new Random();
		MainJoueur.cartesJoueur(r);
		MainJoueur.clearNum�ro();
		assertFalse(MainJoueur.getNum�ro().size() > 0);
	}

	@Test
	public void testRetirerCarte() {
		Random r = new Random(Cartes.MAX + 1);
		MainJoueur.cartesJoueur(r);
		MainJoueur m = new MainJoueur();
		int n = m.getMain().get(1).getNum�ro();
		m.retirerCarte(n);
		for(int i = 0; i < m.getMain().size(); i++) {
			assertFalse(m.getMain().get(i).getNum�ro() == n);
		}
		MainJoueur.clearNum�ro();
	}

	@Test
	public void testAjouterMainListe() {
		for(int i=0; i<MainJoueur.listeMains.size(); i++) {
			MainJoueur.listeMains.remove(0);
		}
		Random r = new Random(Cartes.MAX + 1);
		MainJoueur.AjouterMainListe(r);
		assertFalse(MainJoueur.listeMains.size() != 1);
		for(int i=0; i<MainJoueur.listeMains.size(); i++) {
			MainJoueur.listeMains.remove(0);
		}
	}
}
