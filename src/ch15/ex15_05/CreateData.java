/*
 * Objetivo: c) Escreva um programa para criar dados a fim de testar o programa. Utilize os dados de exemplo da conta nas figuras 
 * 15.14 e 15.15. Execute o programa para criar os arquivos trans.txt e oldmast.txt a serem utilizados por seu programa de correspond�ncia
 * de arquivos.
 * 
 * Autor: Gustavo Alves
 */

package ch15.ex15_05;

import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;

import javax.swing.JOptionPane;

public class CreateData {
	private static Formatter outputOldMast;
	private static Formatter outputTrans;

	public static void main(String[] args) {
		menuOption();
	}

	public static void menuOption() {
		int option;
		do {
			option = Integer
					.parseInt(JOptionPane.showInputDialog(null,
							String.format("Insira a op��o%n%s%n%s%n%s", "1 - Registrar no arquivo-mestre",
									"2 - Registrar transa��es", "9 - Sair"),
							"Menu de Op��es", JOptionPane.QUESTION_MESSAGE));

			switch (option) {
			case 1:
				openFileOldMast();
				addRegisterOldMast();
				closeFileOldMast();
				break;
			case 2:
				openFileTrans();
				addRegisterTrans();
				closeFileTrans();
				break;
			case 9:
				JOptionPane.showMessageDialog(null, "Encerrando ...");
				break;
			default:
				JOptionPane.showMessageDialog(null, "Op��o inv�lida!", "Error", JOptionPane.ERROR_MESSAGE);
				break;
			}

		} while (option != 9);
	}

	public static void openFileOldMast() {
		try {
			outputOldMast = new Formatter("oldmast.txt");
		} catch (SecurityException e) {
			JOptionPane.showMessageDialog(null, "Permiss�o de grava��o negada!", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Erro ao abrir o arquivo!", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	public static void addRegisterOldMast() {
		while (true) {
			int addOrNot = JOptionPane.showConfirmDialog(null, "Acicionar registro?", "Registrar no arquivo-mestre",
					JOptionPane.YES_NO_OPTION);

			if (addOrNot == 0) {
				try {
					Account account = new Account(Integer.parseInt(JOptionPane.showInputDialog("N�mero de conta: ")),
							JOptionPane.showInputDialog("Primeiro nome:"), JOptionPane.showInputDialog("�ltimo nome:"),
							Double.parseDouble(JOptionPane.showInputDialog("Saldo:")));

					outputOldMast.format("%d %s %s %.2f%n", account.getAccount(), account.getFirstName(),
							account.getLastName(), account.getBalance());

				} catch (FormatterClosedException e) {
					JOptionPane.showMessageDialog(null, "Erro ao gravar no arquivo!", "Error",
							JOptionPane.ERROR_MESSAGE);
				} catch (NoSuchElementException e) {
					JOptionPane.showMessageDialog(null, "Entrada inv�lida. Por favor tente novamente!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			} else if (addOrNot == 1)
				return;
			else
				JOptionPane.showMessageDialog(null, "Op��o inv�lida!", "Error", JOptionPane.ERROR_MESSAGE);

		}
	}

	public static void closeFileOldMast() {
		if (outputOldMast != null)
			outputOldMast.close();

	}

	private static void openFileTrans() {
		try {
			outputTrans = new Formatter("trans.txt");
		} catch (SecurityException e) {
			JOptionPane.showMessageDialog(null, "Permiss�o grava��o negada!", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Erro ao abrir o arquivo!", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	private static void addRegisterTrans() {
		while (true) {
			int addOrNot = JOptionPane.showConfirmDialog(null, "Acicionar registro?", "Registrar Transa��o",
					JOptionPane.YES_NO_OPTION);

			if (addOrNot == 0) {
				TransactionRecord transactionRecord = new TransactionRecord(
						Integer.parseInt(JOptionPane.showInputDialog("N�mero de conta:")),
						Double.parseDouble(JOptionPane.showInputDialog("Quantia da transa��o:")));

				try {
					outputTrans.format("%d %.2f%n", transactionRecord.getAccount(),
							transactionRecord.getValueForTransaction());
				} catch (FormatterClosedException e) {
					JOptionPane.showMessageDialog(null, "Erro ao gravar no arquivo!", "Error",
							JOptionPane.ERROR_MESSAGE);
				} catch (NoSuchElementException e) {
					JOptionPane.showMessageDialog(null, "Entrada inv�lida. Por favor tente novamente!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			} else if (addOrNot == 1)
				return;
			else
				JOptionPane.showMessageDialog(null, "Op��o inv�lida!", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	private static void closeFileTrans() {
		if (outputTrans != null)
			outputTrans.close();

	}

}
