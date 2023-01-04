package Partie;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Partie {
	public static void verificationJoueurs(Scanner scanner, String s) {
		while(scanner.hasNextLine()) {
			s = scanner.nextLine();
			if(Joueurs.verifJoueurSup()) {
				Joueurs.AjouterJoueurListe(s);
			}
			else {
				System.out.println("Nombre de joueurs suppérieur à 10.");
				System.exit(0);
			}
		}
		
		if(!Joueurs.verifNombreInf()) {
			System.out.println("Nombre de joueurs insuffisant, il doit au moins y avoir 2 joueurs.");
			System.exit(0);
		}
	}
	
	public static void creationMains(Random random) {
		for(int i=0; i<Joueurs.nbrJoueurs; i++) {
			MainJoueur.AjouterMainListe(random);
		}
	}
	
	public static void affichageJoueurs() {
		System.out.print("Les " + Joueurs.nbrJoueurs + " joueurs sont ");
		int b = Joueurs.nbrJoueurs;
		
		for(int i=0; i<Joueurs.nbrJoueurs; i++) {
			System.out.print(Joueurs.afficherJoueur(i));
			b--;
			if(b > 0) {
				if(b > 1) {
					System.out.print(", ");
				}
				else {
					System.out.print(" et ");
				}
			}
		}
		System.out.print(". Merci de jouer à 6 qui prend !\r\n");
	}
	
	public static void creationSéries(Random random) {
		for(int i=0; i<Séries.nbrSériesMax; i++) {
			Séries.AjouterSérieListe(random);
		}
	}
	
	public static void choixCartes(String s, String tmps, Scanner clavier) {
		s = Séries.afficherSéries(s);
		for(int i=0; i<Joueurs.nbrJoueurs; i++) {
			int count = 0;
			
			System.out.print("A " + Joueurs.afficherJoueur(i) + " de jouer.\n");
			
			util.Console.pause();
			
			System.out.println(s);
			
			System.out.println(MainJoueur.afficherMain(i));
			
			System.out.print("Saisissez votre choix : ");
			
			while(!MainJoueur.verifChoix(Joueurs.ListeJoueurs.get(i).getChoix(), i)) {
				if(count > 0) {
					System.out.print("Vous n'avez pas cette carte, saisissez votre choix : ");
					count = 0;
				}
				tmps = clavier.next();
				while(util.Caractere.isStringInteger(tmps) == false) {
					System.out.print("Vous n'avez pas cette carte, saisissez votre choix : ");
					tmps = clavier.next();
				}
				Joueurs.ListeJoueurs.get(i).Choisir(tmps);
				count++;
			}
			Joueurs.ListeJoueurs.get(i).Choisir(tmps);
			
			MainJoueur.listeMains.get(i).retirerCarte(Joueurs.ListeJoueurs.get(i).getChoix());
			
			util.Console.clearScreen();
		}
		s = Séries.afficherSéries(s);
	}
	
	public static void affichageCartesPosées(ArrayList<Integer> choixJoueur) {
		String s = "Les cartes ";
		for(int i=0; i<Joueurs.nbrJoueurs; i++){
			s += choixJoueur.get(i);
			for(int j=0; j<Joueurs.nbrJoueurs; j++) {
				if(choixJoueur.get(i) == Joueurs.ListeJoueurs.get(j).getChoix()) {
					if(i < Joueurs.nbrJoueurs - 2) {
						s += " (" + Joueurs.ListeJoueurs.get(j).getNom() + "), ";
					}
					else if(i < Joueurs.nbrJoueurs - 1){
						s += " (" + Joueurs.ListeJoueurs.get(j).getNom() + ") et ";
					}
					else {
						s += " (" + Joueurs.ListeJoueurs.get(j).getNom() + ") ont été posées.";
					}
				}
			}
		}
		System.out.println(s);
	}
	
	public static void affichagePénalitéTour(ArrayList<Integer> pénalité, boolean verif, String s) {
		for(int i=0; i<Joueurs.nbrJoueurs; i++) {
			pénalité.add(Joueurs.ListeJoueurs.get(i).getPénalitésTour());
		}
		
		Collections.sort(pénalité);
		
		while(pénalité.size() > 0) {
			for(int i=0; i<Joueurs.nbrJoueurs; i++) {
				if(pénalité.size() > 0) {
					if(pénalité.get(0) == Joueurs.ListeJoueurs.get(i).getPénalitésTour()) {
						if(Joueurs.ListeJoueurs.get(i).getPénalitésTour() > 1) {
							s = " a ramassé " + Joueurs.ListeJoueurs.get(i).getPénalitésTour() + " têtes de boeufs";
						}
						else if(Joueurs.ListeJoueurs.get(i).getPénalitésTour() == 1) {
							s = " a ramassé " + Joueurs.ListeJoueurs.get(i).getPénalitésTour() + " tête de boeufs";
						}
						if(Joueurs.ListeJoueurs.get(i).getPénalitésTour() > 0) {
							System.out.print(Joueurs.ListeJoueurs.get(i).getNom());
							System.out.println(s);
							verif = true;
						}
						if(pénalité.size() > 0) {
							pénalité.remove(0);
						}
					}
				}
			}
		}
		
		if(verif == false) {
			System.out.println("Aucun joueur ne ramasse de tête de boeufs.");
		}
	}
	
	public static void resetFinTour(ArrayList<Integer> choixJoueur, ArrayList<Integer> pénalité, boolean verif) {
		while(choixJoueur.size()>0 || pénalité.size()>0) {
			if(choixJoueur.size() > 0) {
				choixJoueur.remove(0);
			}
			if(pénalité.size() > 0) {
				pénalité.remove(0);
			}
		}
		
		for(int i=0; i<Joueurs.nbrJoueurs; i++) {
			Joueurs.ListeJoueurs.get(i).resetPénalitésTour();
		}
		
		verif = false;
	}
	
	public static void Jouer(String s, String tmps, Scanner clavier, ArrayList<Integer> choixJoueur, ArrayList<Integer> pénalité, boolean verif) {
		for(int nbrTour=0; nbrTour<MainJoueur.cartesMax; nbrTour++) {
			
			s = "";
			
			Partie.choixCartes(s, tmps, clavier);
			
			s = Séries.afficherSéries(s);
			
			Joueurs.confirmerChoix(choixJoueur);
			
			Séries.ajoutCarteSérie(choixJoueur, tmps, s, clavier);
			
			Partie.affichageCartesPosées(choixJoueur);
			
			s = "";
			
			System.out.println(Séries.afficherSéries(s));
			
			Partie.affichagePénalitéTour(pénalité, verif, s);
			
			Joueurs.ajoutPénalité();
			
			Partie.resetFinTour(choixJoueur, pénalité, verif);
			
		}
	}
	
	public static void affichageScoreFinal(ArrayList<Integer> choixJoueur) {
		System.out.println("** Score final");
		for(int i=0; i<Joueurs.nbrJoueurs; i++){
		      choixJoueur.add(Joueurs.ListeJoueurs.get(i).getPénalités());
		    }

		Collections.sort(choixJoueur);
		
		while(choixJoueur.size() > 0){
		      for(int i=0; i<Joueurs.nbrJoueurs; i++) {
		        if(choixJoueur.size() > 0){
		          if(Joueurs.ListeJoueurs.get(i).getPénalités() == choixJoueur.get(0)) {
		            System.out.print(Joueurs.ListeJoueurs.get(i).getNom());
					      System.out.print(" a ramassé " + Joueurs.ListeJoueurs.get(i).getPénalités());
					      if(Joueurs.ListeJoueurs.get(i).getPénalités() > 1) {
						      System.out.println(" têtes de boeufs");
					      }
					      else {
						      System.out.println(" tête de boeufs");
					      }
		          choixJoueur.remove(0);
		          }
		        }
		      }
		}
	}
}
