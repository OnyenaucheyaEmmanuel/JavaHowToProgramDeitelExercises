/*
 * Objetivo: 14.22 (C�digo Morse) Talvez o mais famoso de todos os esquemas de codifica��o seja o c�digo Morse, desenvolvido por Samuel Morse em 1832
 * para utiliza��o com o sistema de tel�grafo. O c�digo Morse atribui uma s�rie de pontos e tra�os para cada letra do alfabeto, para cada d�gito
 * e alguns caracteres especiais (como ponto, v�rgula, dois-pontos e ponto e v�rgula). Em sistemas orientados para �udio, o ponto representa
 * um som curto e o tra�o representa um som longo. Outras representa��es de pontos e tra�os s�o utilizadas com sistemas baseados em sinais
 * luminosos e sistemas baseados em sinais de bandeira. A separa��o entre palavras � indicada por um espa�o, ou, simplesmente, a aus�ncia
 * de um ponto ou tra�o. Em um sistema orientado a som, um espa�o � indicado por um tempo curto durante o qual nenhum som � transmitido. A 
 * vers�o internacional do c�digo Morse aparece na Figura 14.26.
 * 		Elabore um aplicativo que l� uma frase em ingl�s e a codifica em c�digo Morse. Elabore tamb�m um aplicativo que l� uma frase em
 * c�digo Morse e a converte no equivalente em ingl�s. Utilize um espa�o em branco entre cada letra codificada em Morse e tr�s espa�os em
 * branco entre cada palavra codificada em Morse.
 * 
 * Autor: Gustavo Alves
 * Data: 14/04/2019
 */

package ch14.ex14_22;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class CodigoMorse extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextArea textAreaNormal;
	private JTextArea textAreaMorse;

	private JButton start;

	// private JComboBox<String> comboBox;

	// private String[] items = { "Criptografar", "Descriptografar" };

	public CodigoMorse() {
		super("C�digo Morse");
		setLayout(new FlowLayout(FlowLayout.CENTER));
		getContentPane().setBackground(Color.LIGHT_GRAY);

		// comboBox = new JComboBox<String>(items);

		textAreaNormal = new JTextArea(15, 30);
		add(textAreaNormal);

		add(new JLabel(">>"));

		textAreaMorse = new JTextArea(15, 30);
		add(textAreaMorse);

		start = new JButton("Criptografar");
		add(start);

		ButtonHandler handler = new ButtonHandler();
		start.addActionListener(handler);

	}

	private class ButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			textAreaMorse.setText(toNormal(textAreaNormal.getText()));

		}

	}

	public String toMorse(String frase) {
		String[] alfabetoMorse = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..",
				"--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.." };

		// String[] digitosMorse = { "-----", ".----", "..---", "...--", "....-",
		// ".....", "-....", "--...", "---..", "----.", };

		frase = frase.toUpperCase();

		String[] tokens = frase.split(" ");

		StringBuilder fraseMorse = new StringBuilder();

		for (int i = 0; i < tokens.length; i++) {
			for (int j = 0; j < tokens[i].length(); j++) {
				for (int j2 = 0; j2 < 26; j2++) {
					if (tokens[i].charAt(j) == 'A' + j2)
						fraseMorse.append(alfabetoMorse[j2] + " ");
				}
			}

			fraseMorse.append("   ");
		}

		return fraseMorse.toString();
	}

	public String toNormal(String texto) {
		String[] alfabetoMorse = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..",
				"--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.." };

		String[] tokens = texto.split(" ");

		StringBuilder novoTexto = new StringBuilder();
		for (int i = 0; i < tokens.length; i++) {
			for (int j = 0; j < alfabetoMorse.length; j++) {
				if (alfabetoMorse[j].equals(tokens[i]))
					novoTexto.append(String.format("%c", 'A' + j));
			}
		}

		return novoTexto.toString();
	}

	public static void main(String[] args) {
		CodigoMorse frame = new CodigoMorse();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setSize(750, 350);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

}
