/*
 * Objetivo     : 13.6 (C�rculos conc�ntricos utilizando o m�todo drawArc) Escreva um aplicativo que desenha uma s�rie de oito c�rculos 
 * conc�ntricos.  Os c�rculos devem ser separados por 10 pixels. Use o m�todo drawArc de Graphics.
 *
 * Programador  : Gustavo Alves - gustavo.almeida13@fatec.sp.gov.br
 * Data Cria��o : 5 de mar de 2019
 */

package ch27.ex27_06;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class CirculosConcentricosDrawArc extends JPanel {

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// desenha 8 c�rculos conc�ntricos separados por 10 pixels
		for (int i = 1; i <= 8; i++) {
			int x = getWidth();
			int y = getHeight();
			g.drawArc(x / 2 - 10 * i, y / 2 - 10 * i, 20 * i, 20 * i, 0, 360);
		}

	}

	public static void main(String[] args) {
		JFrame application = new JFrame("C�rculos Conc�ntricos - m�todo drawArc");
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		CirculosConcentricosDrawArc draw = new CirculosConcentricosDrawArc();
		application.add(draw);
		application.setSize(410, 225);
		application.setLocationRelativeTo(null);
		application.setVisible(true);
	}

}
