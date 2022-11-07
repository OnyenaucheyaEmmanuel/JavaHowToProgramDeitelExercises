/*
 * Objetivo: 14.25 (Projeto: um gerador de palavras cruzadas) A maioria das pessoas j� brincou de palavras cruzadas, mas poucos tentaram gerar um
 * jogo de palavras cruzadas. Gerar um jogo de palavras cruzadas � sugerido aqui como um projeto de manipula��o de string que requer
 * bastante sofistica��o e esfor�o.
 * H� muitas quest�es que o programador deve resolver para que at� mesmo o mais simples aplicativo gerador de palavras cruzadas funcione.
 * Por exemplo, como voc� representa a grade das palavras cruzadas dentro do computador? Voc� deve utilizar uma s�rie de strings ou
 * arrays bidimensionais?
 * O programador precisa de uma fonte de palavras (isto �, um dicion�rio computadorizado) que possa ser referenciado diretamente pelo
 * aplicativo. De que forma essas palavras devem ser armazenadas para facilitar as complexas manipula��es requeridas pelo aplicativo?
 * Se voc� for realmente ambicioso, vai querer gerar a parte de pistas do quebra-cabe�a, em que breves dicas para palavras na horizontal
 * e na vertical s�o impressas. Meramente imprimir uma vers�o da parte em branco do jogo n�o � um problema simples.
 * 
 * Autor: Gustavo Alves
 * Data: 17/04/2019
 */

package ch14.ex14_25;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GeradorPalavrasCruzadas extends JFrame {
	private static final long serialVersionUID = 1L;

	private JComboBox<String> temas;
	private String[] nomesTemas = { "Pa�ses", "Linguagens de Programa��o" };

	private final String[] palavrasLP = { "JavaScript", "HTML", "CSS", "SQL", "Java", "Shell", "Python", "C#", "PHP",
			"C++", "C", "TypeScript", "Ruby Swift", "Assembly", "Go", "Objective-C", "VB.NET", "R", "Matlab", "VBA",
			"Kotlin", "Scala", "Groovy", "Perl" };

	private final String[] palavrasPaises = { "Afeganist�o", "�frica do Sul", "Akrotiri", "Alb�nia", "Alemanha",
			"Andorra", "Angola", "Anguila", "Ant�rctida", "Ant�gua e Barbuda", "Ar�bia Saudita", "Arctic Ocean",
			"Arg�lia", "Argentina", "Arm�nia", "Aruba", "Ashmore and Cartier Islands", "Atlantic Ocean", "Austr�lia",
			"�ustria", "Azerbaij�o", "Baamas", "Bangladeche", "Barbados", "Bar�m", "B�lgica", "Belize", "Benim",
			"Bermudas", "Bielorr�ssia", "Birm�nia", "Bol�via", "B�snia e Herzegovina", "Botsuana", "Brasil", "Brunei",
			"Bulg�ria", "Burquina Faso", "Bur�ndi", "But�o", "Cabo Verde", "Camar�es", "Camboja", "Canad�", "Catar",
			"Cazaquist�o", "Chade", "Chile", "China", "Chipre", "Clipperton Island", "Col�mbia", "Comores",
			"Congo-Brazzaville", "Congo-Kinshasa", "Coral Sea Islands", "Coreia do Norte", "Coreia do Sul",
			"Costa do Marfim", "Costa Rica", "Cro�cia", "Cuba", "Curacao", "Dhekelia", "Dinamarca", "Dom�nica",
			"Egipto", "Emiratos �rabes Unidos", "Equador", "Eritreia", "Eslov�quia", "Eslov�nia", "Espanha",
			"Estados Unidos", "Est�nia", "Eti�pia", "Faro�", "Fiji", "Filipinas", "Finl�ndia", "Fran�a", "Gab�o",
			"G�mbia", "Gana", "Gaza Strip", "Ge�rgia", "Ge�rgia do Sul e Sandwich do Sul", "Gibraltar", "Granada",
			"Gr�cia", "Gronel�ndia", "Guame", "Guatemala", "Guernsey", "Guiana", "Guin�", "Guin� Equatorial",
			"Guin�-Bissau", "Haiti", "Honduras", "Hong Kong", "Hungria", "I�men", "Ilha Bouvet", "Ilha do Natal",
			"Ilha Norfolk", "Ilhas Caim�o", "Ilhas Cook", "Ilhas dos Cocos", "Ilhas Falkland", "Ilhas Heard e McDonald",
			"Ilhas Marshall", "Ilhas Salom�o", "Ilhas Turcas e Caicos", "Ilhas Virgens Americanas",
			"Ilhas Virgens Brit�nicas", "�ndia", "Indian Ocean", "Indon�sia", "Ir�o", "Iraque", "Irlanda", "Isl�ndia",
			"Israel", "It�lia", "Jamaica", "Jan Mayen", "Jap�o", "Jersey", "Jibuti", "Jord�nia", "Kosovo", "Kuwait",
			"Laos", "Lesoto", "Let�nia", "L�bano", "Lib�ria", "L�bia", "Listenstaine", "Litu�nia", "Luxemburgo",
			"Macau", "Maced�nia", "Madag�scar", "Mal�sia", "Mal�vi", "Maldivas", "Mali", "Malta", "Man, Isle of",
			"Marianas do Norte", "Marrocos", "Maur�cia", "Maurit�nia", "M�xico", "Micron�sia", "Mo�ambique", "Mold�via",
			"M�naco", "Mong�lia", "Monserrate", "Montenegro", "Mundo", "Nam�bia", "Nauru", "Navassa Island", "Nepal",
			"Nicar�gua", "N�ger", "Nig�ria", "Niue", "Noruega", "Nova Caled�nia", "Nova Zel�ndia", "Om�",
			"Pacific Ocean", "Pa�ses Baixos", "Palau", "Panam�", "Papua-Nova Guin�", "Paquist�o", "Paracel Islands",
			"Paraguai", "Peru", "Pitcairn", "Polin�sia Francesa", "Pol�nia", "Porto Rico", "Portugal", "Qu�nia",
			"Quirguizist�o", "Quirib�ti", "Reino Unido", "Rep�blica Centro-Africana", "Rep�blica Checa",
			"Rep�blica Dominicana", "Rom�nia", "Ruanda", "R�ssia", "Salvador", "Samoa", "Samoa Americana",
			"Santa Helena", "Santa L�cia", "S�o Bartolomeu", "S�o Crist�v�o e Neves", "S�o Marinho", "S�o Martinho",
			"S�o Pedro e Miquelon", "S�o Tom� e Pr�ncipe", "S�o Vicente e Granadinas", "Sara Ocidental", "Seicheles",
			"Senegal", "Serra Leoa", "S�rvia", "Singapura", "Sint Maarten", "S�ria", "Som�lia", "Southern Ocean",
			"Spratly Islands", "Sri Lanca", "Suazil�ndia", "Sud�o", "Sud�o do Sul", "Su�cia", "Su��a", "Suriname",
			"Svalbard e Jan Mayen", "Tail�ndia", "Taiwan", "Tajiquist�o", "Tanz�nia",
			"Territ�rio Brit�nico do Oceano �ndico", "Territ�rios Austrais Franceses", "Timor Leste", "Togo", "Tokelau",
			"Tonga", "Trindade e Tobago", "Tun�sia", "Turquemenist�o", "Turquia", "Tuvalu", "Ucr�nia", "Uganda",
			"Uni�o Europeia", "Uruguai", "Usbequist�o", "Vanuatu", "Vaticano", "Venezuela", "Vietname", "Wake Island",
			"Wallis e Futuna", "West Bank", "Z�mbia", "Zimbabu�" };

	private final int TAMANHO = 10;
	private JTextField[][] gradeTextField = new JTextField[TAMANHO][TAMANHO];
	private char[][] gradeMarcador = new char[TAMANHO][TAMANHO];
	private char[][] gradeRespostas = new char[TAMANHO][TAMANHO];

	private JButton jogar;

	private JPanel panelGrade;

	private int QTDE_DE_PALAVRAS = 2;

	private final SecureRandom random = new SecureRandom();

	public GeradorPalavrasCruzadas() {
		super("Gerador de Palavras Cruzadas");

		JPanel panelSettings = new JPanel(new FlowLayout(FlowLayout.CENTER));

		temas = new JComboBox<String>(nomesTemas);

		jogar = new JButton("Come�ar");
		jogar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gerarPalavrasCruzadas(palavrasLP);
				inicializarTabuleiro();
				add(panelGrade, BorderLayout.CENTER);
				repaint();
				revalidate();
			}
		});
		panelSettings.add(temas);
		panelSettings.add(jogar);
		add(panelSettings, BorderLayout.NORTH);

		panelGrade = new JPanel(new GridLayout(TAMANHO, TAMANHO, 0, 0));

		for (int i = 0; i < TAMANHO; i++) {
			for (int j = 0; j < TAMANHO; j++) {
				gradeTextField[i][j] = new JTextField(1);
				gradeTextField[i][j].setHorizontalAlignment(JTextField.CENTER);
				panelGrade.add(gradeTextField[i][j]);
			}
		}

	}

	public void gerarPalavrasCruzadas(String[] palavrasDoTema) {
		// gera a primeira palavra (isto �, a palavra base)
		String palavraBase = palavrasDoTema[random.nextInt(palavrasDoTema.length)];

		// gera coordenada
		int linha = random.nextInt(TAMANHO);
		int coluna = random.nextInt(TAMANHO);

		// gera dire��o
		boolean tentaDeNovo = true;
		do {
			try {
				int direcao = random.nextInt(4);

				switch (direcao) {
				case 0:// esquerda para direita
					for (int i = 0; i < palavraBase.length(); i++) {
						gradeRespostas[linha][coluna] = palavraBase.charAt(i);
						gradeMarcador[linha][coluna] = '*';
						coluna++;
					}
					tentaDeNovo = false;
					break;
				case 1:
					for (int i = 0; i < palavraBase.length(); i++) {
						gradeRespostas[linha][coluna] = palavraBase.charAt(i);
						gradeMarcador[linha][coluna] = '*';
						coluna--;
					}
					tentaDeNovo = false;
					break;
				case 2: // cima para baixo
					for (int i = 0; i < palavraBase.length(); i++) {
						gradeRespostas[linha][coluna] = palavraBase.charAt(i);
						gradeMarcador[linha][coluna] = '*';
						linha++;
					}
					tentaDeNovo = false;
					break;
				case 3: // baixo para cima
					for (int i = 0; i < palavraBase.length(); i++) {
						gradeRespostas[linha][coluna] = palavraBase.charAt(i);
						gradeMarcador[linha][coluna] = '*';
						linha--;
					}
					tentaDeNovo = false;
					break;
				}
			} catch (Exception e) {
				tentaDeNovo = true;
			}
		} while (tentaDeNovo);

		int counter = 1;
		while (counter < QTDE_DE_PALAVRAS) {
			String palavra = palavrasDoTema[random.nextInt(palavrasDoTema.length)];

			boolean flag = false;
			for (int i = 0; i < palavraBase.length(); i++) {
				for (int j = 0; j < palavra.length(); j++) {
					if (palavraBase.charAt(i) == palavra.charAt(j)) {

						flag = true;
						break;
					}
				}
			}

			if (flag)
				counter++;
		}
	}

	public void inicializarTabuleiro() {
		for (int j = 0; j < gradeMarcador.length; j++) {
			for (int j2 = 0; j2 < gradeMarcador.length; j2++) {
				if (gradeMarcador[j][j2] == '*')
					gradeTextField[j][j2].setText(" ");
				else {
					gradeTextField[j][j2].setEditable(false);
					gradeTextField[j][j2].setBackground(Color.BLACK);
				}

			}
		}
	}

	public static void main(String[] args) {
		GeradorPalavrasCruzadas frame = new GeradorPalavrasCruzadas();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setSize(400, 400);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

}
