package Appli;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Partie.Partie;

public class Application {

	public static void main(String[] args) throws Exception {
		File texte = new File("Config.txt");
		Scanner scanner = new Scanner(texte);
		Scanner clavier = new Scanner(System.in);
		String s = "";
		String tmps = "";
		boolean verif = false;
		ArrayList<Integer> choixJoueur = new ArrayList<>();
		ArrayList<Integer> pénalité = new ArrayList<>();
		Random random = new Random();
		
		Partie.verificationJoueurs(scanner, s);
		
		Partie.creationMains(random);
		
		Partie.affichageJoueurs();
		
		Partie.creationSéries(random);
		
		Partie.Jouer(s, tmps, clavier, choixJoueur, pénalité, verif);
		
		Partie.affichageScoreFinal(choixJoueur);
	}
}