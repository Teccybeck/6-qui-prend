package Partie;

import java.util.ArrayList;
import java.util.Collections;

public class Joueurs {
	private String nom;
	private int p�nalit�s = 0;
	public static int nbrJoueurs = 0;
	public static final int nbrJoueursMax = 10;
	public static ArrayList<Joueurs> ListeJoueurs = new ArrayList<>();
	private int choix = 0;
	private int p�nalit�sTour = 0;
	
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
	
	public void ajouterPenalit�sTour(int n) {
		p�nalit�sTour = n;
	}
	
	public void ajouterPenalit�s(int n) {
		p�nalit�s += n;
	}
	
	public int getP�nalit�sTour() {
		return p�nalit�sTour;
	}
	
	public void resetP�nalit�sTour() {
		p�nalit�sTour = 0;
	}
	
	public int getP�nalit�s() {
		return p�nalit�s;
	}
	
	public static void confirmerChoix(ArrayList<Integer> choixJoueur) {
		for(int i=0; i<Joueurs.nbrJoueurs; i++) {
			choixJoueur.add(Joueurs.ListeJoueurs.get(i).getChoix());
		}
		
		Collections.sort(choixJoueur);
	}
	
	public static void ajoutP�nalit�() {
		for(int k=0; k<Joueurs.nbrJoueurs; k++) {
			Joueurs.ListeJoueurs.get(k).ajouterPenalit�s(Joueurs.ListeJoueurs.get(k).getP�nalit�sTour());
		}
	}

}
