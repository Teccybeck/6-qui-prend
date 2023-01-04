package Partie;

import static org.junit.Assert.*;
import java.util.Random;
import org.junit.Test;

public class S�riesTest {

	@Test
	public void testS�ries() {
		S�ries.nbrS�ries = 0;
		Random r = new Random();
		S�ries s = new S�ries(r);
		assertFalse(s.getIdS�rie() != 1);
		assertFalse(s.getTeteActu() == 0);
		S�ries.nbrS�ries = 0;
	}

	@Test
	public void testAjouterCarte() {
		Random r = new Random();
		S�ries s = new S�ries(r);
		int n = r.nextInt(Cartes.MAX + 1);
		s.AjouterCarte(n);
		assertFalse(s.getCartes().get(s.getCartes().size() - 1).getNum�ro() != n);
	}

	@Test
	public void testRetirerCartes() {
		Random r = new Random();
		S�ries s = new S�ries(r);
		for(int i = 0; i< 4; i++) {
			int n = r.nextInt(Cartes.MAX + 1);
			s.AjouterCarte(n);
		}
		s.retirerCartes();
		assertFalse(s.getCartes().size() > 0);
		assertFalse(s.getTeteActu() != 0);
	}

	@Test
	public void testVerifChoixS�rie() {
		for(int i = -1; i<6; i++) {
			if(i<0 || i>S�ries.nbrS�riesMax - 1) {
				assertFalse(S�ries.verifChoixS�rie(i));
			}
			else {
				assertFalse(!S�ries.verifChoixS�rie(i));
			}
		}
	}
}
