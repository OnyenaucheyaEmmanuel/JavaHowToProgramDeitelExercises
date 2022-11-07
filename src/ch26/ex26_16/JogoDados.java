/*
 * Objetivo     : 12.16 (Jogo de dados baseado em GUI) Modifique o aplicativo da Se��o 6.10 para fornecer uma GUI que permite ao usu�rio clicar em um
 * JButton para lan�ar os dados. O aplicativo tamb�m deve exibir quatro JLabels e quatro JTextFields, com um JLabel para cada
 * JTextField. Os JTextFields devem ser utilizados para exibir os valores de cada dado e a somaTextField dos dados depois de cada lan�amento.
 * O ponto deve ser exibido no quarto JTextField quando o usu�rio n�o ganhar ou perder no primeiro lan�amento e deve continuar a ser
 * exibido at� que o jogo seja perdido.
 * 
 * Programador  : Gustavo Alves - gustavo.almeida13@fatec.sp.gov.br
 * Data Cria��o : 2 de mar de 2019
 */

package ch26.ex26_16;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JogoDados extends JFrame {
	private final JLabel addLabel;
	private final JLabel igualLabel;
	private final JLabel pointLabel;

	private final JTextField dado1TextField;
	private final JTextField dado2TextField;
	private final JTextField somaTextField;
	private final JTextField pointTextField;

	private final JButton rollButton;
	private final JButton buttonReset;

	private JLabel statusBar;
	private int counter = 0;

	private JPanel panelNorth;
	private JPanel panelCenter;

	private Font font;

	private static final SecureRandom random = new SecureRandom();

	// constantes que representam lan�amentos comuns dos dados
	private static final int SNAKE_EYES = 2;
	private static final int TREY = 3;
	private static final int SEVEN = 7;
	private static final int YO_LEVEN = 11;
	private static final int BOX_CARS = 12;

	private boolean firstRoll = true;

	public JogoDados() {
		super("Craps");
		getContentPane().setBackground(Color.GREEN);

		font = new Font("Serif", Font.BOLD, 20);

		panelNorth = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelNorth.setBackground(Color.GREEN);

		dado1TextField = new JTextField(2);
		dado1TextField.setEnabled(false);
		dado1TextField.setDisabledTextColor(Color.BLACK);
		dado1TextField.setHorizontalAlignment(JTextField.CENTER);
		dado1TextField.setFont(new Font("Serif", Font.PLAIN, 18));
		panelNorth.add(dado1TextField);

		addLabel = new JLabel("+");
		addLabel.setFont(font);
		panelNorth.add(addLabel);

		dado2TextField = new JTextField(2);
		dado2TextField.setEnabled(false);
		dado2TextField.setDisabledTextColor(Color.BLACK);
		dado2TextField.setHorizontalAlignment(JTextField.CENTER);
		dado2TextField.setFont(new Font("Serif", Font.PLAIN, 18));
		panelNorth.add(dado2TextField);

		igualLabel = new JLabel("=");
		igualLabel.setFont(font);
		panelNorth.add(igualLabel);

		somaTextField = new JTextField(2);
		somaTextField.setEnabled(false);
		somaTextField.setDisabledTextColor(Color.BLACK);
		somaTextField.setHorizontalAlignment(JTextField.CENTER);
		somaTextField.setFont(new Font("Serif", Font.PLAIN, 18));
		panelNorth.add(somaTextField);

		pointLabel = new JLabel("Point is: ");
		pointTextField = new JTextField(2);
		pointTextField.setEnabled(false);
		pointTextField.setDisabledTextColor(Color.BLACK);
		pointTextField.setHorizontalAlignment(JTextField.CENTER);
		pointTextField.setFont(new Font("Serif", Font.PLAIN, 18));
		pointLabel.setVisible(false);
		pointTextField.setVisible(false);

		panelNorth.add(pointLabel);
		panelNorth.add(pointTextField);

		add(panelNorth, BorderLayout.NORTH);

		panelCenter = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelCenter.setBackground(Color.GREEN);

		rollButton = new JButton("Roll");
		rollButton.setToolTipText("Dice roll");
		panelCenter.add(rollButton);
		add(panelCenter);

		statusBar = new JLabel(String.format("%d� release.", counter));
		add(statusBar, BorderLayout.SOUTH);

		// bot�o de reset
		buttonReset = new JButton("Play Again");
		buttonReset.setBackground(Color.YELLOW);
		buttonReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});

		ButtonHandler handler = new ButtonHandler();
		rollButton.addActionListener(handler);
	}

	private class ButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int sumOfDice = rollDice();

			statusBar.setText(String.format("%d� release.", ++counter));

			if (firstRoll) { // primeiro lan�amento
				// determina o estado do jogo e a pontua��o com base no primeiro lan�amento
				firstRoll = false;
				switch (sumOfDice) {
				case SEVEN: // ganha com 7 no primeiro lan�amento
				case YO_LEVEN: // ganha com 11 no primeiro lan�amento
					JOptionPane.showMessageDialog(JogoDados.this, "WON! Congratulations!", "WON",
							JOptionPane.INFORMATION_MESSAGE);
					replaceButtons();
					break;
				case SNAKE_EYES: // perde com 2 no primeiro lan�amento
				case TREY: // perde com 3 no primeiro lan�amento
				case BOX_CARS: // perde com 12 no primeiro lan�amento
					JOptionPane.showMessageDialog(JogoDados.this, "LOST! How unlucky", "LOST",
							JOptionPane.ERROR_MESSAGE);
					replaceButtons();
					break;
				default: // n�o ganhou nem perdeu, portanto registra a pontua��o
					pointTextField.setText("" + sumOfDice);
					pointLabel.setVisible(true);
					pointTextField.setVisible(true);
					panelNorth.repaint();
					panelNorth.validate();
					break;
				}

			} else {
				sumOfDice = rollDice(); // lan�a os dados novamente

				// determina o estado do jogo e a pontua��o apartir do segundo lan�amento
				if (sumOfDice == Integer.parseInt(pointTextField.getText())) { /// vit�ria por pontua��o
					JOptionPane.showMessageDialog(JogoDados.this, "WON! Congratulations!", "WON",
							JOptionPane.INFORMATION_MESSAGE);
					replaceButtons();
				} else if (sumOfDice == SEVEN) { // perde obtendo 7 antes de atingir a pontua��o
					JOptionPane.showMessageDialog(JogoDados.this, "LOST!! it was not this time!", "LOST",
							JOptionPane.ERROR_MESSAGE);
					replaceButtons();
				}

			}

		}
	}

	public int rollDice() {
		int die1 = 1 + random.nextInt(6);
		int die2 = 1 + random.nextInt(6);

		int sum = die1 + die2;

		dado1TextField.setText("" + die1);
		dado2TextField.setText("" + die2);
		somaTextField.setText("" + sum);

		return sum;
	}

	public void reset() {
		dado1TextField.setText("");
		dado2TextField.setText("");
		somaTextField.setText("");
		pointTextField.setText("");
		pointTextField.setVisible(false);
		pointLabel.setVisible(false);
		counter = 0;
		statusBar.setText(String.format("%d� release.", counter));
		panelCenter.remove(buttonReset);
		panelCenter.add(rollButton);
		panelCenter.repaint();
		panelCenter.validate();
		firstRoll = true;
	}

	public void replaceButtons() {
		panelCenter.remove(rollButton);
		panelCenter.add(buttonReset);
		panelCenter.repaint();
		panelCenter.validate();
	}

	public static void main(String[] args) {
		JogoDados frame = new JogoDados();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setResizable(false);
		frame.setSize(300, 125);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
