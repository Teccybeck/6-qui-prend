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
				System.out.println("Nombre de joueurs supp�rieur � 10.");
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
		System.out.print(". Merci de jouer � 6 qui prend !\r\n");
	}
	
	public static void creationS�ries(Random random) {
		for(int i=0; i<S�ries.nbrS�riesMax; i++) {
			S�ries.AjouterS�rieListe(random);
		}
	}
	
	public static void choixCartes(String s, String tmps, Scanner clavier) {
		s = S�ries.afficherS�ries(s);
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
		s = S�ries.afficherS�ries(s);
	}
	
	public static void affichageCartesPos�es(ArrayList<Integer> choixJoueur) {
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
						s += " (" + Joueurs.ListeJoueurs.get(j).getNom() + ") ont �t� pos�es.";
					}
				}
			}
		}
		System.out.println(s);
	}
	
	public static void affichageP�nalit�Tour(ArrayList<Integer> p�nalit�, boolean verif, String s) {
		for(int i=0; i<Joueurs.nbrJoueurs; i++) {
			p�nalit�.add(Joueurs.ListeJoueurs.get(i).getP�nalit�sTour());
		}
		
		Collections.sort(p�nalit�);
		
		while(p�nalit�.size() > 0) {
			for(int i=0; i<Joueurs.nbrJoueurs; i++) {
				if(p�nalit�.size() > 0) {
					if(p�nalit�.get(0) == Joueurs.ListeJoueurs.get(i).getP�nalit�sTour()) {
						if(Joueurs.ListeJoueurs.get(i).getP�nalit�sTour() > 1) {
							s = " a ramass� " + Joueurs.ListeJoueurs.get(i).getP�nalit�sTour() + " t�tes de boeufs";
						}
						else if(Joueurs.ListeJoueurs.get(i).getP�nalit�sTour() == 1) {
							s = " a ramass� " + Joueurs.ListeJoueurs.get(i).getP�nalit�sTour() + " t�te de boeufs";
						}
						if(Joueurs.ListeJoueurs.get(i).getP�nalit�sTour() > 0) {
							System.out.print(Joueurs.ListeJoueurs.get(i).getNom());
							System.out.println(s);
							verif = true;
						}
						if(p�nalit�.size() > 0) {
							p�nalit�.remove(0);
						}
					}
				}
			}
		}
		
		if(verif == false) {
			System.out.println("Aucun joueur ne ramasse de t�te de boeufs.");
		}
	}
	
	public static void resetFinTour(ArrayList<Integer> choixJoueur, ArrayList<Integer> p�nalit�, boolean verif) {
		while(choixJoueur.size()>0 || p�nalit�.size()>0) {
			if(choixJoueur.size() > 0) {
				choixJoueur.remove(0);
			}
			if(p�nalit�.size() > 0) {
				p�nalit�.remove(0);
			}
		}
		
		for(int i=0; i<Joueurs.nbrJoueurs; i++) {
			Joueurs.ListeJoueurs.get(i).resetP�nalit�sTour();
		}
		
		verif = false;
	}
	
	public static void Jouer(String s, String tmps, Scanner clavier, ArrayList<Integer> choixJoueur, ArrayList<Integer> p�nalit�, boolean verif) {
		for(int nbrTour=0; nbrTour<MainJoueur.cartesMax; nbrTour++) {
			
			s = "";
			
			Partie.choixCartes(s, tmps, clavier);
			
			s = S�ries.afficherS�ries(s);
			
			Joueurs.confirmerChoix(choixJoueur);
			
			S�ries.ajoutCarteS�rie(choixJoueur, tmps, s, clavier);
			
			Partie.affichageCartesPos�es(choixJoueur);
			
			s = "";
			
			System.out.println(S�ries.afficherS�ries(s));
			
			Partie.affichageP�nalit�Tour(p�nalit�, verif, s);
			
			Joueurs.ajoutP�nalit�();
			
			Partie.resetFinTour(choixJoueur, p�nalit�, verif);
			
		}
	}
	
	public static void affichageScoreFinal(ArrayList<Integer> choixJoueur) {
		System.out.println("** Score final");
		for(int i=0; i<Joueurs.nbrJoueurs; i++){
		      choixJoueur.add(Joueurs.ListeJoueurs.get(i).getP�nalit�s());
		    }

		Collections.sort(choixJoueur);
		
		while(choixJoueur.size() > 0){
		      for(int i=0; i<Joueurs.nbrJoueurs; i++) {
		        if(choixJoueur.size() > 0){
		          if(Joueurs.ListeJoueurs.get(i).getP�nalit�s() == choixJoueur.get(0)) {
		            System.out.print(Joueurs.ListeJoueurs.get(i).getNom());
					      System.out.print(" a ramass� " + Joueurs.ListeJoueurs.get(i).getP�nalit�s());
					      if(Joueurs.ListeJoueurs.get(i).getP�nalit�s() > 1) {
						      System.out.println(" t�tes de boeufs");
					      }
					      else {
						      System.out.println(" t�te de boeufs");
					      }
		          choixJoueur.remove(0);
		          }
		        }
		      }
		}
	}
}
