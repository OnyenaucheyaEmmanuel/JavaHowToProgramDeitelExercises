/*
 * Objetivo: 15.8 (Pesquisa entre alunos) A Figura 7.8 cont�m um array de respostas a uma pesquisa que � codificado diretamente no programa. 
 * Suponha que queremos processar os resultados dessa pesquisa que s�o armazenados em um arquivo. Este exerc�cio requer dois programas separados.
 * Primeiro, crie um aplicativo que solicita ao usu�rio respostas � pesquisa e gera a sa�da de cada resposta para um arquivo. Utilize um
 * Formatter para criar um arquivo chamado numbers.txt. Cada inteiro deve ser escrito com o m�todo format. Ent�o modifique o programa que 
 * aparece na Figura 7.8 para ler as respostas � pesquisa a partir de numbers.txt. As respostas devem ser lidas do arquivo utilizando um 
 * Scanner. Use o m�todo nextInt para inserir um n�mero inteiro de cada vez a partir do arquivo. O programa precisa continuar a ler respostas
 * at� alcan�ar o fim desse arquivo. A sa�da dos resultados deve ser gerada no arquivo de texto "output.txt".
 * 
 * Autor: Gustavo Alves
 */

package ch15.ex15_08;

import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;

public class PesquisaEntreAlunos {
	private static Formatter output;

	public static void main(String[] args) {
		int[] responses = gatherResponses();

		openFile();
		addRecords(responses);
		closeFile();
	}

	public static int[] gatherResponses() {
		Scanner scanner = new Scanner(System.in);

		final int AMOUNT = 5;
		int[] responses = new int[AMOUNT];

		for (int i = 0; i < responses.length; i++) {
			System.out.printf("%d� rating: ", i + 1);
			responses[i] = scanner.nextInt();
		}
		scanner.close();
		return responses;
	}

	public static void openFile() {
		try {
			output = new Formatter("numbers.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void addRecords(int[] responses) {
		for (int i = 0; i < responses.length; i++)
			output.format("%d%n", responses[i]);

	}

	public static void closeFile() {
		if (output != null)
			output.close();
	}

}
