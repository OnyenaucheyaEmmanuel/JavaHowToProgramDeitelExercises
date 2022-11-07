/*
 * Objetivo: 13.32 (Exibi��o de fontes maiores para pessoas com problemas de vis�o) A acessibilidade dos computadores e a internet para 
 * todas as pessoas, independentemente das defici�ncias, � cada vez mais importante, uma vez que essas ferramentas desempenham pap�is mais
 * fundamentais nas nossas vidas pessoais e profissionais. De acordo com uma estimativa recente da Organiza��o Mundial da Sa�de (www.
 * who.int/mediacentre/factsheets/fs282/en/), 246 milh�es de pessoas no mundo todo t�m problemas de vis�o. Para saber
 * mais sobre problemas de vis�o, confira a simula��o dos problemas de vis�o baseada em GUI em www.webaim.org/simulations/
 * lowvision.php. Pessoas com problemas de vis�o talvez prefiram escolher uma fonte e/ou um tamanho de fonte maior ao ler documentos
 * eletr�nicos e p�ginas web. O Java tem cinco fontes �l�gicas� predefinidas que est�o dispon�veis em qualquer aplicativo Java, incluindo
 * Serif, SansSerif e Monospaced. Escreva um aplicativo gr�fico que fornece uma JTextArea em que o usu�rio pode digitar texto.
 * Permita que o usu�rio selecione Serif, SansSerif ou Monospaced a partir de uma JComboBox. Forne�a um JCheckBox Bold que,
 * se marcado, torna o texto negrito. Inclua os JButtons Increase Font Size e Decrease Font Size, que permitem ao usu�rio aumentar ou
 * reduzir, respectivamente, o tamanho de fonte em um ponto de cada vez. Comece com um tamanho de fonte de 18 pontos. Para os prop�sitos
 * deste exerc�cio, defina o tamanho de fonte em JComboBox, JButtons e JCheckBox como 20 pontos, de modo que uma pessoa com
 * problemas de vis�o seja capaz de ler o texto neles.
 * 
 * Autor: Gustavo Alves
 * Data: 17/03/2019
 */

package ch27.ex27_32;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class FontesMaiores extends JFrame {
	private final JTextArea textArea;
	private String currentFont = "Serif";
	private int currentSize = 18;

	private final JComboBox<String> fonts;
	private final String[] namesFonts = { "Serif", "SansSerif", "Monospaced" };

	private final JCheckBox bold;

	private final JButton increseFontSiza;
	private final JButton decreaseFontSize;

	private final JPanel panelComponents = new JPanel(new FlowLayout());

	public FontesMaiores() {
		super("Exibi��o de Fontes Maiores para Pessoas com Problemas de Vis�o");
		setResizable(false);

		textArea = new JTextArea(10, 15);
		textArea.setFont(new Font(currentFont, Font.PLAIN, currentSize));
		add(textArea, BorderLayout.CENTER);

		fonts = new JComboBox<>(namesFonts);
		fonts.setFont(new Font("Serif", Font.PLAIN, 20));
		fonts.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED)
					textArea.setFont(
							new Font(namesFonts[fonts.getSelectedIndex()], textArea.getFont().getStyle(), currentSize));

				currentFont = namesFonts[fonts.getSelectedIndex()];

			}
		});

		bold = new JCheckBox("Bold");
		bold.setFont(new Font("Serif", Font.PLAIN, 20));
		bold.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (bold.isSelected() == true)
					textArea.setFont(new Font(currentFont, Font.BOLD, currentSize));
				else
					textArea.setFont(new Font(currentFont, Font.PLAIN, currentSize));

			}
		});

		increseFontSiza = new JButton("Increase Font Size");
		increseFontSiza.setFont(new Font("Serif", Font.PLAIN, 20));
		decreaseFontSize = new JButton("Decrease Font Size");
		decreaseFontSize.setFont(new Font("Serif", Font.PLAIN, 20));

		panelComponents.setBackground(Color.BLACK);
		panelComponents.add(fonts);
		panelComponents.add(bold);
		panelComponents.add(increseFontSiza);
		panelComponents.add(decreaseFontSize);
		add(panelComponents, BorderLayout.SOUTH);

		ButtonHandler handler = new ButtonHandler();
		increseFontSiza.addActionListener(handler);
		decreaseFontSize.addActionListener(handler);

	}

	private class ButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == increseFontSiza)
				currentSize++;
			else
				currentSize--;

			textArea.setFont(new Font(currentFont, textArea.getFont().getStyle(), currentSize));

		}

	}

	public static void main(String[] args) {
		FontesMaiores frame = new FontesMaiores();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setSize(650, 400);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

}
