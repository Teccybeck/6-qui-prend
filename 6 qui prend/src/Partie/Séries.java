package Partie;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Séries {
	private ArrayList<Cartes> cartes;
	private int IdSérie;
	public static int nbrSéries = 0;
	public static final int nbrSériesMax = 4;
	public static final int nbrCartesMax = 5;
	public static ArrayList<Séries> listeSéries = new ArrayList<>();
	public static final int maxTeteSérie = 50;
	private int teteActu = 0;
	
	public Séries(Random r) {
		cartes = new ArrayList<>();
		nbrSéries += 1;
		IdSérie = nbrSéries;
		cartes.add(new Cartes(Cartes.nombreAlea(r)));
		this.ajouterTete(cartes.get(0).getNbrtete());
	}
	
	public void AjouterCarte(int n) {
		Cartes c = new Cartes(n);
		cartes.add(c);
		this.ajouterTete(c.getNbrtete());
	}
	
	public String toString() {
		String s = new String();
		s = "- série n° " + IdSérie + " : ";
		for(int i=0; i<cartes.size(); i++) {
			Cartes c = cartes.get(i);
			s += c.getNuméro();
			if(c.getNbrtete() > 1) {
				s += " (" + c.getNbrtete() + ")";
			}
			if(i!=cartes.size()-1) {
				s += ", ";
			}
		}
		return s;
	}
	
	public int getIdSérie() {
		return IdSérie;
	}
	
	public ArrayList<Cartes> getCartes() {
		return cartes;
	}
	
	public Séries creerSérie(Random r) {
		Séries s = new Séries(r);
		return s;
	}
	
	public static void AjouterSérieListe(Random r) {
		listeSéries.add(new Séries(r));
	}
	
	public static String afficherSérie(int indice) {
		String s = " ";
		Séries se = listeSéries.get(indice);
		s += se.IdSérie;
		return s;
	}
	
	public int tailleSérie() {
		return cartes.size();
	}
	
	public void retirerCartes() {
		int n = cartes.size();
		for(int i=0; i<n; i++) {
			cartes.remove(0);
		}
		teteActu = 0;
	}
	
	public int getTeteActu() {
		return teteActu;
	}
	
	public void ajouterTete(int n) {
		teteActu += n;
	}
	
	public static boolean verifChoixSérie(int choix) {
		if(choix < 0 || choix > nbrSériesMax - 1) {
			return false;
		}
		return true;
	}
	
	public static String afficherSéries(String s) {
		for(int j=0; j<nbrSériesMax; j++) {
			if(j != nbrSériesMax -1) {
				s += listeSéries.get(j).toString() + "\n";
			}
			else {
				s += listeSéries.get(j).toString();
			}
		}
		return s;
	}
	
	public static void ajoutCarteSérie(ArrayList<Integer> choixJoueur, String tmps, String s, Scanner clavier) { //(Liste choix, affichage clavier, affichage séries, entrée lavier)
		for(int i=0; i<Joueurs.nbrJoueurs; i++) {
			int count = 0;
			int valeur = 0;
			int nSérie = 0;
			int joueur = 0;
			for(int j=0; j<Séries.nbrSériesMax; j++) {
				Séries tmp = Séries.listeSéries.get(j);
				Cartes c = tmp.getCartes().get(tmp.tailleSérie() - 1);
				int n = c.getNuméro();
				if(choixJoueur.get(i) > n) {
					if(n > valeur) {
						valeur = n;
						nSérie = j;
					}
				}
			}
			if(valeur == 0) {
				String str = "Les cartes ";
				for(int l=0; l<Joueurs.nbrJoueurs; l++){
					str += choixJoueur.get(l);
					for(int j=0; j<Joueurs.nbrJoueurs; j++) {
						if(choixJoueur.get(l) == Joueurs.ListeJoueurs.get(j).getChoix()) {
							if(l < Joueurs.nbrJoueurs - 2) {
								str += " (" + Joueurs.ListeJoueurs.get(j).getNom() + "), ";
							}
							else if(l < Joueurs.nbrJoueurs - 1){
								str += " (" + Joueurs.ListeJoueurs.get(j).getNom() + ") et ";
							}
							else {
								str += " (" + Joueurs.ListeJoueurs.get(j).getNom() + ") vont être posées.";
							}
						}
					}
				}
				System.out.println(str);
				System.out.print("Pour poser la carte " + choixJoueur.get(i) + ", ");
				for(int j=0; j<Joueurs.nbrJoueurs; j++) {
					if(choixJoueur.get(i) == Joueurs.ListeJoueurs.get(j).getChoix()) {
						joueur = j;
					}
				}
				System.out.println(Joueurs.afficherJoueur(joueur) + " doit choisir la série qu'il va ramasser.");
				System.out.println(s);
				System.out.print("Saisissez votre choix : ");
				
				nSérie = Séries.nbrSériesMax + 1;
				while(!Séries.verifChoixSérie(nSérie)) {
					if(count > 0) {
						System.out.print("Cette série n'existe pas, saisissez votre choix : ");
						count = 0;
					}
					tmps = clavier.next();
					while(util.Caractere.isStringInteger(tmps) == false) {
						System.out.print("Cette série n'existe pas, saisissez votre choix : ");
						tmps = clavier.next();
					}
					nSérie = Integer.parseInt(tmps) - 1;
					count++;
				}
				Joueurs.ListeJoueurs.get(joueur).ajouterPenalitésTour(Séries.listeSéries.get(nSérie).getTeteActu());
				Séries.listeSéries.get(nSérie).retirerCartes();
			}
			if(Séries.listeSéries.get(nSérie).getCartes().size() == Séries.nbrCartesMax) {
				for(int j=0; j<Joueurs.nbrJoueurs; j++) {
					if(choixJoueur.get(i) == Joueurs.ListeJoueurs.get(j).getChoix()) {
						joueur = j;
					}
				}
				Joueurs.ListeJoueurs.get(joueur).ajouterPenalitésTour(Séries.listeSéries.get(nSérie).getTeteActu());
				for(int k=0; k<Séries.nbrCartesMax; k++) {
					Séries.listeSéries.get(nSérie).retirerCartes();
				}
			}
			Séries.listeSéries.get(nSérie).AjouterCarte(choixJoueur.get(i));
		}
	}
}
