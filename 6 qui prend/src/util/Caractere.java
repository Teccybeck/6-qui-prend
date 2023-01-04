package util;

import java.util.Scanner;

public class Caractere {
	public static boolean isStringInteger(String stringToCheck) {
        Scanner sc = new Scanner(stringToCheck.trim());
        if(!sc.hasNextInt()) return false;
        sc.nextInt();
        return !sc.hasNext();
    }
}
