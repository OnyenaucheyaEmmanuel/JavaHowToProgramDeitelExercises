/*
 * Objetivo: 14.10 (Exibindo Strings em letras mai�sculas e min�sculas) Elabore um aplicativo que insere uma linha de texto e gera duas vezes a
 * sa�da do texto � uma vez em letras mai�sculas e uma vez em letras min�sculas.
 * 
 * Autor: Gustavo Alves
 * Data: 30/03/2019
 */

package ch14.ex14_10;

import java.util.Scanner;

public class LetrasMaiusculasEMinusculas {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Digite uma linha de texto: ");
		String textLine = input.nextLine();
		input.close();

		System.out.printf("%s%n%s", textLine.toUpperCase(), textLine.toLowerCase());

	}

}
