/*
 * Objetivo: 14.8 (Tokenizando n�meros de telefone) Elabore um aplicativo que insere um n�mero de telefone como uma string na forma (555)
 * 555-5555. O aplicativo deve utilizar o m�todo String split para extrair o c�digo de �rea como um token, os tr�s primeiros d�gitos do
 * n�mero de telefone como um segundo token e os �ltimos quatro d�gitos do n�mero de telefone como um terceiro token. Os sete d�gitos do
 * n�mero de telefone devem ser concatenados em uma string. O c�digo de �rea e o n�mero de telefone devem ser impressos. Lembre-se de
 * que voc� que ter� de alterar caracteres delimitadores durante o processo de tokeniza��o.
 * 
 * Autor: Gustavo Alves
 * Data: 30/03/2019
 */

package ch14.ex14_08;

import java.util.Scanner;

public class TokenizandoNumerosTelefones {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Digite um n�mero de telefone na forma (555) 555-5555: ");
		String number = input.nextLine();
		input.close();

		String[] tokens = number.split("[()\\s\\-]");

		for (int i = 0; i < tokens.length; i++)
			System.out.print(tokens[i]);

	}

}
