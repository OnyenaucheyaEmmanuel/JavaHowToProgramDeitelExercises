/*
 * Objetivo: 14.20 (Prote��o de cheque) Os computadores frequentemente empregaram em sistemas de verifica��o de escrita como aplicativos 
 * de folha de pagamento e contas a pagar. Ouvimos muitas hist�rias estranhas relacionadas a cheques de pagamento semanal que s�o impressos 
 * (por engano) com quantias de mais de US$ 1 milh�o. Quantidades incorretas s�o impressas por sistemas computadorizados de preenchimento
 * de cheque por causa de erro humano ou falha de m�quina. Os projetistas de sistemas embutem controles em seus sistemas para evitar a
 * emiss�o desses cheques errados.
 * 		Outro problema s�rio � a altera��o intencional do valor de um cheque por algu�m que planeja receber um cheque de modo fraudulento.
 * Para evitar que uma quantia monet�ria seja alterada, alguns sistemas computadorizados de preenchimento de cheque empregam uma
 * t�cnica chamada prote��o de cheque. Cheques projetados para imprimir por computador cont�m um n�mero fixo de espa�os em que o
 * computador pode imprimir uma quantia. Suponha que um cheque de pagamento contenha oito espa�os em branco em que o computador
 * deve imprimir a quantidade de um cheque de pagamento semanal. Se o valor for alto, ent�o todos os oito espa�os ser�o preenchidos. Por
 * exemplo,
 * 
 * 1,230.60 (valor do cheque)
 * --------
 * 12345678 (n�meros de posi��o)
 * 
 * Por outro lado, se a quantidade for menor que US$ 1000, ent�o v�rios dos espa�os seriam comumente deixados em branco. Por exemplo,
 * 
 *    99.87
 * --------
 * 12345678
 * 
 * cont�m tr�s espa�os em branco. Se um cheque � impresso com espa�os em branco, � mais f�cil que algu�m altere o valor. Para evitar a altera��o,
 * muitos sistemas de escrita de cheque inserem asteriscos � esquerda para proteger o valor como a seguir:
 * 
 * ***99.87
 * --------
 * 12345678
 * 
 * Elabore um aplicativo que insere uma quantia monet�ria que ser� impressa em um cheque e ent�o imprime o valor em formato de cheque
 * protegido com asteriscos iniciais, se necess�rio. Suponha que nove espa�os est�o dispon�veis para imprimir o valor.
 * 
 * Autor: Gustavo Alves
 * Data: 14/04/2019
 */

package ch14.ex14_20;

import java.util.Scanner;

public class ProtecaoDeCheque {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Digite uma quntia mont�ria que ser� impressa em um cheque (Ex. 1,230.60): ");
		String quantia = input.nextLine();
		input.close();

		System.out.println("Quantia protegida: " + protecaoDeCheque(quantia));

	}

	public static String protecaoDeCheque(String quantia) {
		if (quantia.length() == 8)
			return quantia;
		else {
			int espacosEmBranco = 8 - quantia.length();

			StringBuilder quantiaProtegida = new StringBuilder();

			for (int i = 0; i < espacosEmBranco; i++)
				quantiaProtegida.append("*");

			quantiaProtegida.append(quantia);

			return String.valueOf(quantiaProtegida);
		}

	}

}
