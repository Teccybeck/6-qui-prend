package Partie;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class S�ries {
	private ArrayList<Cartes> cartes;
	private int IdS�rie;
	public static int nbrS�ries = 0;
	public static final int nbrS�riesMax = 4;
	public static final int nbrCartesMax = 5;
	public static ArrayList<S�ries> listeS�ries = new ArrayList<>();
	public static final int maxTeteS�rie = 50;
	private int teteActu = 0;
	
	public S�ries(Random r) {
		cartes = new ArrayList<>();
		nbrS�ries += 1;
		IdS�rie = nbrS�ries;
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
		s = "- s�rie n� " + IdS�rie + " : ";
		for(int i=0; i<cartes.size(); i++) {
			Cartes c = cartes.get(i);
			s += c.getNum�ro();
			if(c.getNbrtete() > 1) {
				s += " (" + c.getNbrtete() + ")";
			}
			if(i!=cartes.size()-1) {
				s += ", ";
			}
		}
		return s;
	}
	
	public int getIdS�rie() {
		return IdS�rie;
	}
	
	public ArrayList<Cartes> getCartes() {
		return cartes;
	}
	
	public S�ries creerS�rie(Random r) {
		S�ries s = new S�ries(r);
		return s;
	}
	
	public static void AjouterS�rieListe(Random r) {
		listeS�ries.add(new S�ries(r));
	}
	
	public static String afficherS�rie(int indice) {
		String s = " ";
		S�ries se = listeS�ries.get(indice);
		s += se.IdS�rie;
		return s;
	}
	
	public int tailleS�rie() {
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
	
	public static boolean verifChoixS�rie(int choix) {
		if(choix < 0 || choix > nbrS�riesMax - 1) {
			return false;
		}
		return true;
	}
	
	public static String afficherS�ries(String s) {
		for(int j=0; j<nbrS�riesMax; j++) {
			if(j != nbrS�riesMax -1) {
				s += listeS�ries.get(j).toString() + "\n";
			}
			else {
				s += listeS�ries.get(j).toString();
			}
		}
		return s;
	}
	
	public static void ajoutCarteS�rie(ArrayList<Integer> choixJoueur, String tmps, String s, Scanner clavier) { //(Liste choix, affichage clavier, affichage s�ries, entr�e lavier)
		for(int i=0; i<Joueurs.nbrJoueurs; i++) {
			int count = 0;
			int valeur = 0;
			int nS�rie = 0;
			int joueur = 0;
			for(int j=0; j<S�ries.nbrS�riesMax; j++) {
				S�ries tmp = S�ries.listeS�ries.get(j);
				Cartes c = tmp.getCartes().get(tmp.tailleS�rie() - 1);
				int n = c.getNum�ro();
				if(choixJoueur.get(i) > n) {
					if(n > valeur) {
						valeur = n;
						nS�rie = j;
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
								str += " (" + Joueurs.ListeJoueurs.get(j).getNom() + ") vont �tre pos�es.";
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
				System.out.println(Joueurs.afficherJoueur(joueur) + " doit choisir la s�rie qu'il va ramasser.");
				System.out.println(s);
				System.out.print("Saisissez votre choix : ");
				
				nS�rie = S�ries.nbrS�riesMax + 1;
				while(!S�ries.verifChoixS�rie(nS�rie)) {
					if(count > 0) {
						System.out.print("Cette s�rie n'existe pas, saisissez votre choix : ");
						count = 0;
					}
					tmps = clavier.next();
					while(util.Caractere.isStringInteger(tmps) == false) {
						System.out.print("Cette s�rie n'existe pas, saisissez votre choix : ");
						tmps = clavier.next();
					}
					nS�rie = Integer.parseInt(tmps) - 1;
					count++;
				}
				Joueurs.ListeJoueurs.get(joueur).ajouterPenalit�sTour(S�ries.listeS�ries.get(nS�rie).getTeteActu());
				S�ries.listeS�ries.get(nS�rie).retirerCartes();
			}
			if(S�ries.listeS�ries.get(nS�rie).getCartes().size() == S�ries.nbrCartesMax) {
				for(int j=0; j<Joueurs.nbrJoueurs; j++) {
					if(choixJoueur.get(i) == Joueurs.ListeJoueurs.get(j).getChoix()) {
						joueur = j;
					}
				}
				Joueurs.ListeJoueurs.get(joueur).ajouterPenalit�sTour(S�ries.listeS�ries.get(nS�rie).getTeteActu());
				for(int k=0; k<S�ries.nbrCartesMax; k++) {
					S�ries.listeS�ries.get(nS�rie).retirerCartes();
				}
			}
			S�ries.listeS�ries.get(nS�rie).AjouterCarte(choixJoueur.get(i));
		}
	}
}
