package Partie;

import static org.junit.Assert.*;
import java.util.Random;
import org.junit.Test;

public class SériesTest {

	@Test
	public void testSéries() {
		Séries.nbrSéries = 0;
		Random r = new Random();
		Séries s = new Séries(r);
		assertFalse(s.getIdSérie() != 1);
		assertFalse(s.getTeteActu() == 0);
		Séries.nbrSéries = 0;
	}

	@Test
	public void testAjouterCarte() {
		Random r = new Random();
		Séries s = new Séries(r);
		int n = r.nextInt(Cartes.MAX + 1);
		s.AjouterCarte(n);
		assertFalse(s.getCartes().get(s.getCartes().size() - 1).getNuméro() != n);
	}

	@Test
	public void testRetirerCartes() {
		Random r = new Random();
		Séries s = new Séries(r);
		for(int i = 0; i< 4; i++) {
			int n = r.nextInt(Cartes.MAX + 1);
			s.AjouterCarte(n);
		}
		s.retirerCartes();
		assertFalse(s.getCartes().size() > 0);
		assertFalse(s.getTeteActu() != 0);
	}

	@Test
	public void testVerifChoixSérie() {
		for(int i = -1; i<6; i++) {
			if(i<0 || i>Séries.nbrSériesMax - 1) {
				assertFalse(Séries.verifChoixSérie(i));
			}
			else {
				assertFalse(!Séries.verifChoixSérie(i));
			}
		}
	}
}
