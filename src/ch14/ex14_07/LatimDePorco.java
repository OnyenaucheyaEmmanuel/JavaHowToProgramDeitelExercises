/*
 * Objetivo: 14.7 (Latim de porco) Elabore um aplicativo que codifica frases da l�ngua inglesa em latim de porco. O Pig Latin � uma forma 
 * de linguagem codificada. H� muitos m�todos diferentes para formar frases em Pig Latin. Para simplificar, utilize o seguinte algoritmo:
 * Para formar uma frase em latim de porco a partir de uma frase em ingl�s, tokenize a frase em palavras com o m�todo String split.
 * Para traduzir cada palavra inglesa em uma palavra do latim de porco, coloque a primeira letra da palavra inglesa no final da palavra e adicione
 * as letras �ay�. Assim, a palavra �jump� torna-se �umpjay�, a palavra �the� torna-se �hetay�, e a palavra �computer� torna-se �omputercay�.
 * Os espa�os entre as palavras permanecem iguais. Suponha o seguinte: a frase inglesa consiste em palavras separadas por espa�os, n�o
 * h� nenhuma marca��o de pontua��o e todas as palavras t�m duas ou mais letras. O m�todo printLatinWord deve exibir cada palavra.
 * Cada token � passado para o m�todo printLatinWord a fim de imprimir a palavra latina porco. Permita que o usu�rio insira a frase.
 * Continue exibindo todas as frases convertidas em uma �rea de texto.
 * 
 * Autor: Gustavo Alves
 * Data: 30/03/2019
 */

package ch14.ex14_07;

import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class LatimDePorco extends JFrame implements KeyListener {
	private static final long serialVersionUID = 1L;

	private JTextArea textAreaEnglish;
	private JTextArea textAreaLatim;

	public LatimDePorco() {
		super("Latim de Porco");
		setLayout(new FlowLayout(FlowLayout.CENTER));

		startGUI();
	}

	public void startGUI() {
		textAreaEnglish = new JTextArea(5, 20);
		textAreaEnglish.setWrapStyleWord(true);
		textAreaEnglish.setLineWrap(true);
		textAreaEnglish.addKeyListener(this);
		add(new JScrollPane(textAreaEnglish));

		add(new JLabel(" >> "));

		textAreaLatim = new JTextArea(5, 20);
		textAreaLatim.setWrapStyleWord(true);
		textAreaLatim.setLineWrap(true);
		add(new JScrollPane(textAreaLatim));
	}

	public void englishToPingLatin() {
		String[] tokens = textAreaEnglish.getText().split(" ");

		StringBuilder latin = new StringBuilder();

		for (int i = 0; i < tokens.length; i++) {
			try {
				latin.append(
						String.format("%s ", tokens[i].substring(1, tokens[i].length()) + tokens[i].charAt(0) + "ay"));
			} catch (Exception e) {
			}

		}
		printLatinWord(latin);

	}

	public void printLatinWord(StringBuilder latin) {
		textAreaLatim.setText(latin.toString());
	}

	public static void main(String[] args) {
		LatimDePorco frame = new LatimDePorco();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 150);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		englishToPingLatin();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		englishToPingLatin();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		englishToPingLatin();
	}

}
