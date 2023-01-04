package Partie;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

public class JoueursTest {

	@Test
	public void testJoueurs() {
		while(Joueurs.ListeJoueurs.size() > 0) { // verifier que la liste soit vide
			Joueurs.ListeJoueurs.remove(0);
		}
		Joueurs.nbrJoueurs = 0;
		String s = "Nicolas";
		Joueurs j = new Joueurs(s);
		assertFalse(j.getNom() != s); // test de l'atribution du nom
		for(int i = 0; i<9; i++) {
			@SuppressWarnings("unused")
			Joueurs jo = new Joueurs(s);
		}
		assertFalse(Joueurs.nbrJoueurs != 10); // test du compte de joueurs
	}

	
	@Test
	public void testVerifNombreInf() {
		while(Joueurs.ListeJoueurs.size() > 0) { // verifier que la liste soit vide
			Joueurs.ListeJoueurs.remove(0);
		}
		String s = "N";
		Joueurs j = new Joueurs(s);
		Joueurs.ListeJoueurs.add(j);
		assertFalse(Joueurs.verifNombreInf());
	}

	@Test
	public void testVerifJoueurSup() {
		while(Joueurs.ListeJoueurs.size() > 0) { // verifier que la liste soit vide
			Joueurs.ListeJoueurs.remove(0);
		}
		String s = "N";
		for(int i = 0; i<Joueurs.nbrJoueursMax + 1; i++) {
			Joueurs.ListeJoueurs.add(new Joueurs(s));
		}
		assertFalse(Joueurs.verifJoueurSup());
	}

	@Test
	public void testAjouterJoueurListe() {
		while(Joueurs.ListeJoueurs.size() > 0) { // verifier que la liste soit vide
			Joueurs.ListeJoueurs.remove(0);
		}
		String s = "n";
		Joueurs.AjouterJoueurListe(s);
		assertFalse(Joueurs.ListeJoueurs.get(0).getNom() != s); // verifier que le joueur ajouter soit le bon
		
	}

	@Test
	public void testAfficherJoueur() {
		while(Joueurs.ListeJoueurs.size() > 0) { // verifier que la liste soit vide
			Joueurs.ListeJoueurs.remove(0);
		}
		String s = "n";
		Joueurs.AjouterJoueurListe(s);
		assertFalse(Joueurs.afficherJoueur(0) != s);
	}

	@Test
	public void testConfirmerChoix() {
		while(Joueurs.ListeJoueurs.size() > 0) { // verifier que la liste soit vide
			Joueurs.ListeJoueurs.remove(0);
		}
		Joueurs.nbrJoueurs = 0;
		ArrayList<Integer> l = new ArrayList<>();
		String s = "n";
		for(int j = 0; j<3; j++) {
			Joueurs.AjouterJoueurListe(s);
		}
		Joueurs.ListeJoueurs.get(0).Choisir("" + 4);
		Joueurs.ListeJoueurs.get(1).Choisir("" + 2);
		Joueurs.ListeJoueurs.get(2).Choisir("" + 6);
		Joueurs.confirmerChoix(l);
		for(int i=0; i<3; i++) {
			assertFalse(l.get(i) != (i+1)*2);
		}
	}

	@Test
	public void testAjoutPénalité() {
		while(Joueurs.ListeJoueurs.size() > 0) { // verifier que la liste soit vide
			Joueurs.ListeJoueurs.remove(0);
		}
		Joueurs.nbrJoueurs = 0;
		String s = "n";
		for(int j = 0; j<3; j++) {
			Joueurs.AjouterJoueurListe(s);
			Joueurs.ListeJoueurs.get(j).ajouterPenalitésTour(j);
		}
		Joueurs.ajoutPénalité();
		for(int i=0; i<Joueurs.nbrJoueurs; i++) {
			assertFalse(Joueurs.ListeJoueurs.get(i).getPénalités() != i);
		}
	}

}
