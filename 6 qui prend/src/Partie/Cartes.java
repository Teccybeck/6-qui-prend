package Partie;

import java.util.Random;

public class Cartes {
	private int num�ro;
	private int nbrtete;
	public static final int MAX = 104;
	private static int[] cartesExist = new int[MAX];
	
	public Cartes(int n) {
		num�ro = n;
		cartesExist[num�ro - 1] = num�ro;
		if(n % 5 == 0 && n != 55) {
			if(n % 10 == 0) {
				nbrtete = 3;
			}
			else {
				nbrtete = 2;
			}
		}
		else if(n % 11 == 0) {
			if(n == 55) {
				nbrtete = 7;
			}
			else {
				nbrtete = 5;
			}
		}
		else {
			nbrtete = 1;
		}
	}
	
	public static int nombreAlea(Random random) { 
		int nb = random.nextInt(MAX + 1);
		for(int i = 0; i<MAX; i++) {
			if(nb == cartesExist[i]) {
				nb = Cartes.nombreAlea(random);
			}
		}
		return nb;
	}
	
	public Cartes carteAl�atoire(Random random) {
		Cartes c = new Cartes(Cartes.nombreAlea(random));
		return c;
	}
	
	public boolean verifCarteExist() {
		for(int i=0; i<MAX; i++) {
			if(this.num�ro == cartesExist[i]) {
				return true;
			}
		}
		return false;
	}
	
	public int getNbrtete() {
		return nbrtete;
	}
	
	public int getNum�ro() {
		return num�ro;
	}
}
