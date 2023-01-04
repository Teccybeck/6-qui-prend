package Partie;

import java.util.ArrayList;
import java.util.Collections;

public class Joueurs {
	private String nom;
	private int pénalités = 0;
	public static int nbrJoueurs = 0;
	public static final int nbrJoueursMax = 10;
	public static ArrayList<Joueurs> ListeJoueurs = new ArrayList<>();
	private int choix = 0;
	private int pénalitésTour = 0;
	
	public Joueurs(String mot) {
		nom = new String();
		nom = mot;
		nbrJoueurs += 1;
	}
	
	public static boolean verifNombreInf() {
		if(ListeJoueurs.size()<2) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public static boolean verifJoueurSup() {
		if(ListeJoueurs.size() > nbrJoueursMax - 1) {
			return false;
		}
		else {
			return true;
		}
	}

	public static void AjouterJoueurListe(String mot) {
		ListeJoueurs.add(new Joueurs(mot));
	}
	
	public static String afficherJoueur(int indice) {
		String s = "";
		Joueurs j = ListeJoueurs.get(indice);
		s = j.nom;
		return s;
	}
	
	public void Choisir(String s) {
		choix = Integer.parseInt(s);
	}
	
	public String getNom() {
		return nom;
	}
	
	public int getChoix() {
		return choix;
	}
	
	public void ajouterPenalitésTour(int n) {
		pénalitésTour = n;
	}
	
	public void ajouterPenalités(int n) {
		pénalités += n;
	}
	
	public int getPénalitésTour() {
		return pénalitésTour;
	}
	
	public void resetPénalitésTour() {
		pénalitésTour = 0;
	}
	
	public int getPénalités() {
		return pénalités;
	}
	
	public static void confirmerChoix(ArrayList<Integer> choixJoueur) {
		for(int i=0; i<Joueurs.nbrJoueurs; i++) {
			choixJoueur.add(Joueurs.ListeJoueurs.get(i).getChoix());
		}
		
		Collections.sort(choixJoueur);
	}
	
	public static void ajoutPénalité() {
		for(int k=0; k<Joueurs.nbrJoueurs; k++) {
			Joueurs.ListeJoueurs.get(k).ajouterPenalités(Joueurs.ListeJoueurs.get(k).getPénalitésTour());
		}
	}

}
