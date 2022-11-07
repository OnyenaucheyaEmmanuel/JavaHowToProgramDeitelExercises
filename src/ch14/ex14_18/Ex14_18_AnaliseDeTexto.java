/*
 * Objetivo: 14.18 (An�lise de texto) A disponibilidade de computadores com capacidades de manipula��o de string resultou em algumas abordagens
 * bastante interessantes para analisar textos de grandes autores. Muita aten��o foi dada � pol�mica de que William Shakespeare n�o teria
 * existido de fato. Alguns acad�micos acreditam haver evid�ncias substanciais que indicam que Christopher Marlowe realmente escreveu as
 * obras-primas atribu�das a Shakespeare. Os pesquisadores t�m utilizado computadores para encontrar semelhan�as na escrita desses dois
 * autores. Esse exerc�cio examina tr�s m�todos para analisar textos com um computador.
 * 
 * a) Elabore um aplicativo que l� uma linha de texto do teclado e imprime uma tabela que indica o n�mero de ocorr�ncias de cada letra do
 * alfabeto no texto. Por exemplo, a frase
 * 
 *			 To be, or not to be: that is the question:
 *
 * cont�m um �a,� dois �b�, nenhum �c,� e assim por diante.
 * 
 * b) Elabore um aplicativo que l� uma linha de texto e imprime uma tabela que indique o n�mero de palavras de uma letra, palavras de duas 
 * letras, palavras de tr�s letras, e assim por diante, que aparecem no texto.
 * 
 * c) Elabore um aplicativo que l� uma linha de texto e imprime uma tabela que indica o n�mero de ocorr�ncias de cada palavra diferente
 * no texto. O aplicativo deve incluir as palavras na tabela na mesma ordem em que elas aparecem no texto. Por exemplo, as linhas
 * 
 *			To be, or not to be: that is the question:
 *			Whether 'tis nobler in the mind to suffer
 *
 * cont�m a palavra �to� tr�s vezes, a palavra �be� duas vezes, a palavra �or� uma vez etc.
 * 
 * 
 * Autor: Gustavo Alves
 * Data: 13/04/2019
 */

package ch14.ex14_18;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;;

public class Ex14_18_AnaliseDeTexto extends JFrame {
	private static final long serialVersionUID = 1L;

	private JTextArea textAreaEditor;
	private JTextArea textAreaAnalise;

	private JPanel panel;
	private JButton ocorrenciasLetra;
	private JButton comprimentoPalavras;
	private JButton ocorrenciaPalavra;

	public Ex14_18_AnaliseDeTexto() {
		super("An�lise de Texto");

		startGUI();

	}

	public void startGUI() {
		setLayout(new FlowLayout());

		textAreaEditor = new JTextArea(27, 40);
		textAreaEditor.setFont(new Font("Arial", Font.PLAIN, 14));
		textAreaEditor.setLineWrap(true);
		textAreaEditor.setWrapStyleWord(true);
		textAreaEditor.setToolTipText("Digite o texto aqui");
		add(new JScrollPane(textAreaEditor));

		textAreaAnalise = new JTextArea(28, 28);
		textAreaAnalise.setBackground(new Color(240, 255, 255));
		add(new JScrollPane(textAreaAnalise));

		panel = new JPanel(new GridLayout(4, 1, 5, 5));

		ocorrenciasLetra = new JButton("Ocorr�ncias de cada Letra");
		comprimentoPalavras = new JButton("Comprimento de Palavras");
		ocorrenciaPalavra = new JButton("Ocorr�ncias de cada Palavra");

		panel.add(ocorrenciasLetra);
		panel.add(comprimentoPalavras);
		panel.add(ocorrenciaPalavra);
		add(panel);

		ButtonHandler handler = new ButtonHandler();
		ocorrenciasLetra.addActionListener(handler);
		comprimentoPalavras.addActionListener(handler);
		ocorrenciaPalavra.addActionListener(handler);

	}

	private class ButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String resultado = "";
			String texto = textAreaEditor.getText();

			if (e.getSource() == ocorrenciasLetra) {
				int[] ocorrenciaLetras = determinarOcorrenciasDeCadaLetra(texto);

				resultado = String.format("%s %15s", "  LETRA", "OCORR�NCIAS");

				for (int i = 0; i < ocorrenciaLetras.length; i++) {
					resultado += "\n";
					resultado += String.format("%6c%26d", 'A' + i, ocorrenciaLetras[i]);
				}

			} else if (e.getSource() == comprimentoPalavras) {
				int[] letrasCadaPalavra = determinarQuantasLetrasTemCadaPalavra(texto);

				resultado = String.format("%s %15s", "  COMPRIMENTO DE PALAVRA", "OCORR�NCIAS");

				for (int i = 1; i < letrasCadaPalavra.length; i++) {
					resultado += "\n";
					resultado += String.format("           %02d %60d", i, letrasCadaPalavra[i]);
				}

			} else {
				resultado = determinarOcorrenciaDeCadaPalavra(texto);

			}

			textAreaAnalise.setText(resultado);

		}

	}

	public int[] determinarOcorrenciasDeCadaLetra(String texto) {
		int[] frequencia = new int[26];

		texto = texto.toLowerCase();

		for (int i = 0; i < texto.length(); i++) {
			for (int j = 0; j < 26; j++) {
				if ('a' + j == texto.charAt(i))
					frequencia[j]++;
			}
		}

		return frequencia;

	}

	public int[] determinarQuantasLetrasTemCadaPalavra(String texto) {
		int[] comprimento = new int[47];

		texto = retirarPontuacao(texto);

		String[] tokens = texto.split(" ");

		for (int i = 0; i < tokens.length; i++)
			comprimento[tokens[i].length()]++;

		return comprimento;

	}

	public String determinarOcorrenciaDeCadaPalavra(String texto) {
		texto = retirarPontuacao(texto);
		texto = texto.toLowerCase();

		String[] tokens = texto.split(" ");

		ArrayList<Integer> ocorrencia = new ArrayList<Integer>();
		ArrayList<String> palavra = new ArrayList<String>();
		String resultado = String.format("%s %15s", "   PALAVRA", "OCORR�NCIA");

		for (int i = 0; i < tokens.length; i++) {
			boolean flag = false;
			for (int j = 0; j < palavra.size(); j++) {
				if (tokens[i].equals(palavra.get(j)))
					flag = true;
			}
			if (flag)
				continue;

			for (int j = i; j < tokens.length; j++) {
				ocorrencia.add(j, 1);
				if (tokens[j].indexOf(tokens[i], j + 1) == -1)
					break;
				else {
					ocorrencia.add(j, ocorrencia.get(j) + 1);
					j = tokens[j].indexOf(tokens[i], j + 1);
				}
			}
			palavra.add(tokens[i]);
		}

		for (int i = 0; i < palavra.size(); i++)
			resultado += String.format("\n%5s %18d", palavra.get(i), ocorrencia.get(i));

		return resultado;

	}

	public String retirarPontuacao(String texto) {
		texto = texto.replaceAll("[\\d,.:();?!\"#-=]", "");
		texto = texto.replaceAll("/", " ");

		return texto;
	}

	public static void main(String[] args) {
		Ex14_18_AnaliseDeTexto frame = new Ex14_18_AnaliseDeTexto();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// frame.setResizable(false);
		frame.setSize(1000, 520);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

}
