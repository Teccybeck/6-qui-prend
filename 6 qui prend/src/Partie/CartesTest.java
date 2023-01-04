package Partie;

import static org.junit.Assert.*;
import java.util.Random;
import org.junit.Test;

public class CartesTest {
	@Test
	public void testCartes() { // On vérifie l'atribution automatique des têtes de boeufs
		for(int n = 1; n<Cartes.MAX + 1; n++) {
			Cartes c = new Cartes(n);
			if(n % 11 == 0) {
				if(n == 55) {
					assertFalse(c.getNbrtete() != 7);
				}
				else {
					assertFalse(c.getNbrtete() != 5);
				}
			}
			if(n % 5 == 0 && n != 55) {
				if(n % 10 == 0) {
					assertFalse(c.getNbrtete() != 3);
				}
				else {
					assertFalse(c.getNbrtete() != 2);
				}
			}
			else if(n % 11 != 0 && n % 55 != 0 && n % 5 != 0 && n % 10 != 0) {
				assertFalse(c.getNbrtete() != 1);
			}
		}
	}
	
	@Test
	public void testNombreAlea() { // On vérifie que le nombre généré est bien entre à et 104
		Random random = new Random();
		int n = Cartes.nombreAlea(random);
		assertFalse(n < 0 || n > 104);
	}

	@Test
	public void testVerifCarteExist() { // On créer une carte au hasard et on vérifie que la méthode nous indique que le numéro de cette carte existe apres sa creation
		Random r = new Random();
		Cartes c = new Cartes(Cartes.nombreAlea(r));
		assertFalse(!c.verifCarteExist());
	}
}
