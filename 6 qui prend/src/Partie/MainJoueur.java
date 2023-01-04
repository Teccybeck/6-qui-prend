package Partie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainJoueur {
	private int nbCartes = 10;
	private ArrayList<Cartes> main;
	private  static ArrayList<Integer> num�ro = new ArrayList<>();
	private static Cartes tmp;
	public static ArrayList<MainJoueur> listeMains = new ArrayList<>();
	public static final int cartesMax = 10;
	
	public MainJoueur() {
		main = new ArrayList<>();
		for(int i=0; i<nbCartes; i++) {
			tmp = new Cartes(num�ro.get(i));
			main.add(tmp);
		}
	}
	
	public static void cartesJoueur(Random r) {
		for(int i=0; i<cartesMax; i++) {
			tmp = new Cartes(Cartes.nombreAlea(r));
			num�ro.add(tmp.getNum�ro());
		}
		Collections.sort(num�ro);
	}
	
	public static void clearNum�ro() {
		for(int i = 0; i < cartesMax; i++) {
			num�ro.remove(0);
		}
	}
	
	public void retirerCarte(int n) {
		Cartes c = new Cartes(n);
		for(int i=0; i<main.size(); i++) {
			if(main.get(i).getNum�ro() == c.getNum�ro()) {
				main.remove(main.get(i));
				nbCartes -= 1;
			}
		}
	}
	
	public int getNbCartes() {
		return nbCartes;
	}
	
	public ArrayList<Cartes> getMain(){
		return main;
	}
	
	public String toString(){
	    String s = new String();
	    s = "- Vos cartes : ";
	    for (int i = 0; i < main.size(); ++i ){
	      s += main.get(i).getNum�ro();
	      if (main.get(i).getNbrtete() > 1){
	        s += " (" + main.get(i).getNbrtete() + ")";
	      }
	      if(i != main.size() - 1) {
	    	  s += ", ";
	      }
	    }
	    return s;
	  }
	
	public static void AjouterMainListe(Random r) {
		cartesJoueur(r);
		listeMains.add(new MainJoueur());
		clearNum�ro();
	}
	
	public static String afficherMain(int n) {
		return listeMains.get(n).toString();
	}
	
	public static ArrayList<Cartes> accederCartesMain(int n){
		MainJoueur l = listeMains.get(n);
		return l.main;
	}
	
	public static boolean verifChoix(int choix, int indice) {
		for(int i=0; i<MainJoueur.accederCartesMain(indice).size(); i++) {
			if(choix == MainJoueur.accederCartesMain(indice).get(i).getNum�ro()) {
				return true;
			}
		}
		return false;
	}
	
	public static ArrayList<Integer> getNum�ro(){
		return num�ro;
	}
}
