/*
 * Objetivo: 14.27 (Scanner de spam) Spam (ou e-mail n�o solicitado) custa para organiza��es norte-americanas bilh�es de d�lares por ano em softwares
 * de preven��o de spam, equipamentos, recursos de rede, largura de banda e perda de produtividade. Pesquise on-line algumas das mensagens
 * e palavras de e-mail mais comuns de spam, e verifique sua pasta de lixo eletr�nico. Crie uma lista de 30 palavras e frases comumente
 * encontradas em mensagens de spam. Elabore um aplicativo em que o usu�rio digita uma mensagem de e-mail em uma JTextArea. Ent�o,
 * verifique na mensagem cada uma das 30 palavras-chave ou frases. Para cada ocorr�ncia de uma delas dentro da mensagem, adicione
 * um ponto � �pontua��o de spam� da mensagem. Em seguida, classifique a probabilidade de que a mensagem � spam, com base no n�mero
 * de pontos que ela recebeu.
 * 
 * Autor: Gustavo Alves
 * Data: 16/04/2019
 */

package ch14.ex14_27;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ScannerDeSpam extends JFrame {
	private String[] listaAssunto = { "Compre", "Aproveite", "Gr�tis", "Desconto", "Promo��o", "Oferta", "Imperd�vel",
			"Pre�os", "Valores", "Frete gr�tis", "Despachamos para todo o Brasil", "24 horas", "N�o perca tempo",
			"Hoje", "Urgente", "Abra", "Assine", "Visite o site", "Confira", "Telemarketing", "Divulgue", "B�nus",
			"Ganhe", "Perca peso", "Seja seu pr�prio patr�o", "Curso", "Dinheiro r�pido", "!!!" };

	private String[] listaMensagem = { "LIVRE", "aten��o", "urgentemente", "oi", "imediato", "para voc�", "oferecer",
			"trabalhar em casa", "comprar", "ligue agora", "ganhar dinheiro", "clique aqui", "tempo limitado", "100%",
			"50%", "cr�dito", "marketing", " por favor me ajude", "Viagra", "b�nus em dinheiro", "comprar", "evitar",
			"tranquilo", "produtos", "sucesso", "isso n�o � spam", "oferta de emprego!", "vencedor ", "n�o exclua",
			"Saiba mais", "em casa", "oportunidade" };

	private int pontuacaoDeSpam = 0;

	private JTextField assunto;
	private JTextArea mensagem;

	private JButton analisar;

	public ScannerDeSpam() {
		super("Scanner de Spam");

		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		assunto = new JTextField(55);
		panel.add(new JLabel("Assunto: "));
		panel.add(assunto);
		add(panel, BorderLayout.NORTH);

		JPanel panel2 = new JPanel(new BorderLayout());
		mensagem = new JTextArea(10, 20);
		mensagem.setLineWrap(true);
		mensagem.setWrapStyleWord(true);
		panel2.add(mensagem);
		add(panel2, BorderLayout.CENTER);

		analisar = new JButton("Analisar");
		analisar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				verificarAssunto(assunto.getText());
				verificarMensagem(mensagem.getText());
				verificarPontuacao(assunto.getText());
				verificarPontuacao(mensagem.getText());

				JOptionPane.showMessageDialog(null, "Pontua��o de Spam = " + pontuacaoDeSpam);
				pontuacaoDeSpam = 0;
			}
		});
		add(analisar, BorderLayout.SOUTH);
	}

	public void verificarAssunto(String assunto) {
		assunto = tirarPontuacao(assunto);

		String[] tokStrings = assunto.split(" ");

		for (int i = 0; i < tokStrings.length; i++) {
			for (int j = 0; j < listaAssunto.length; j++) {
				if (listaAssunto[j].equalsIgnoreCase(tokStrings[i]))
					pontuacaoDeSpam++;
			}
		}

	}

	public void verificarMensagem(String mensagem) {
		mensagem = tirarPontuacao(mensagem);

		String[] tokStrings = mensagem.split(" ");

		for (int i = 0; i < tokStrings.length; i++) {
			for (int j = 0; j < listaMensagem.length; j++) {
				if (listaMensagem[j].equalsIgnoreCase(tokStrings[i]))
					pontuacaoDeSpam++;
			}
		}

	}

	public void verificarPontuacao(String texto) {
		texto = texto.replaceAll("[\\d\\w]", " ");

		String[] tokens = texto.split(" ");

		for (int i = 0; i < tokens.length; i++) {
			if (tokens[i].equals("!") || tokens[i].equals("!!!"))
				pontuacaoDeSpam++;
		}
	}

	public String tirarPontuacao(String texto) {
		texto = texto.replaceAll("[,.;:?!-=#\"\'\\d]", "");
		texto = texto.replaceAll("/", " ");
		return texto;
	}

	public static void main(String[] args) {
		ScannerDeSpam frame = new ScannerDeSpam();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setSize(700, 400);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}
}
