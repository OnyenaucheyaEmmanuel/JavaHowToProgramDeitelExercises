/*
 * Objetivo     : 12.20 (Professor de digita��o: aprimorando uma habilidade crucial na era da inform�tica) Digitar r�pida e corretamente 
 * � uma habilidade essencial para trabalhar de forma eficaz com computadores e a internet. Neste exerc�cio, voc� construir� um aplicativo 
 * GUI que pode ajudar os usu�rios a aprender a digitar corretamente sem olhar para o teclado. O aplicativo deve exibir um teclado virtual 
 * (Figura 12.50) e permitir que o usu�rio veja o que ele est� digitando na tela sem olhar para o teclado real. Use JButtons para representar as
 * teclas. � medida que o usu�rio pressiona cada tecla, o aplicativo destaca o JButton correspondente na GUI e adiciona o caractere a uma
 * JTextArea que mostra o que o usu�rio digitou at� agora. [Dica: para destacar um JButton, use o m�todo setBackground para
 * mudar a cor de fundo. Quando a tecla � liberada, redefina a cor original do fundo. Voc� pode obter a cor original de fundo do JButton
 * com o m�todo getBackground antes de mudar a cor.]
 * Voc� pode testar seu programa digitando um pangrama � uma frase que cont�m todas as letras do alfabeto pelo menos uma vez �
 * como �The quick brown fox jumps over a lazy dog� ou, em portugu�s, �Um pequeno jabuti xereta viu dez cegonhas felizes�. Voc� pode encontrar
 * outros pangramas na web.
 * Para tornar o programa mais interessante, monitore a precis�o do usu�rio. Voc� pode fazer com que o usu�rio digite frases espec�ficas
 * que voc� pr�-armazenou no seu programa e que voc� exibe na tela acima do teclado virtual. Pode-se monitorar quantos pressionamentos de
 * tecla o usu�rio digita corretamente e quantos s�o digitados incorretamente. Pode-se tamb�m monitorar com quais teclas o usu�rio tem dificuldade
 * e exibir um relat�rio mostrando essas teclas.

 * Programador  : Gustavo Alves - gustavo.almeida13@fatec.sp.gov.br
 * Data Cria��o : 2 de mar de 2019
 */

package ch26.ex26_20;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ProfessorDigitacao extends JFrame implements KeyListener {
	private final JButton[] buttons;

	private final String[] teclas = { "\"", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "-", "+", "Backspace",
			"TAB", "Q", "w", "E", "R", "T", "Y", "U", "I", "O", "P", "�", "[", "]", "Caps Lock", "A", "S", "D", "F",
			"G", "H", "J", "K", "L", "�", "~", "Enter", "Shift", "\\", "Z", "X", "C", "V", "B", "N", "M", ",", ".", ";",
			"/", "Shift", "Ctrl", "Alt", "Espa�o", "Alt Gr", "Win", "Ctrl" };

	private final JTextArea textArea;

	private final JLabel label1;
	private final JLabel label2;
	private final JPanel panelNorth;
	private final JPanel panelSouth;

	private int indexOfTheLastKey; // �ndice da ultima tecla

	public ProfessorDigitacao() {
		super("Typing Application");
		setResizable(false);

		panelNorth = new JPanel(new GridLayout(2, 1));
		label1 = new JLabel(
				"Type some text using your keyboard. The keys you press will be highlighted and the text will be displayed.");
		label2 = new JLabel("Note: Clicking the buttons with your mouse will not perform any action.");
		panelNorth.add(label1);
		panelNorth.add(label2);
		add(panelNorth, BorderLayout.SOUTH);

		textArea = new JTextArea(10, 15);
		textArea.setFont(new Font("Serif", Font.PLAIN, 14));
		add(new JScrollPane(textArea), BorderLayout.NORTH);
		textArea.addKeyListener(this);

		panelSouth = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		buttons = new JButton[teclas.length];

		for (int i = 0; i < teclas.length; i++) {
			buttons[i] = new JButton(teclas[i]);
			panelSouth.add(buttons[i]);
		}

		add(panelSouth);

	}

	// trata pressionamento de qualquer tecla
	@Override
	public void keyPressed(KeyEvent e) {
		buttons[indexOfTheLastKey].setBackground(getBackground());
		for (int i = 0; i < buttons.length; i++) {
			if (buttons[i].getText() == KeyEvent.getKeyText(e.getKeyCode())) {
				buttons[i].setBackground(Color.RED);
				indexOfTheLastKey = i;
				break;
			}
		}

	}

	// trata libera��o de qualquer tecla
	@Override
	public void keyReleased(KeyEvent e) {
		buttons[indexOfTheLastKey].setBackground(getBackground());
	}

	// trata pressionamento de uma tecla de a��o
	@Override
	public void keyTyped(KeyEvent e) {

		for (int i = 0; i < buttons.length; i++) {
			if (buttons[i].getText().equals(String.valueOf(e.getKeyChar()))) {
				buttons[i].setBackground(Color.CYAN);
				indexOfTheLastKey = i;
				break;
			}
		}

	}

	public static void main(String[] args) {
		ProfessorDigitacao frame = new ProfessorDigitacao();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(710, 420);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
