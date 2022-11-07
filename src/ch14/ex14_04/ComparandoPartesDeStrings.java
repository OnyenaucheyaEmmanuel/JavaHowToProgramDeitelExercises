/*
 * Objetivo: 14.4 (Comparando partes de Strings) Elabore um aplicativo que utiliza o m�todo String regionMatches para comparar duas
 * entradas de strings pelo usu�rio. O aplicativo deve inserir o n�mero de caracteres que ser� comparado e o �ndice inicial da compara��o.
 * O aplicativo deve declarar se as strings s�o iguais. Ignore a distin��o entre mai�sculas e min�sculas dos caracteres ao realizar a 
 * compara��o.
 * 
 * Autor: Gustavo Alves
 * Data: 30/03/2019
 */

package ch14.ex14_04;

import java.util.Scanner;

public class ComparandoPartesDeStrings {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("String 1: ");
		String s1 = input.nextLine();

		System.out.print("String 2: ");
		String s2 = input.nextLine();
		input.close();

		System.out.printf("As string %s iguais.", s1.regionMatches(true, 0, s2, 0, 2) ? "s�o" : "n�o s�o");

	}

}
