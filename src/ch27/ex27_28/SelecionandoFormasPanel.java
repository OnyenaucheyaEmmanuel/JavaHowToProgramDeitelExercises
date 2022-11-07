/*  
 * Objetivo     : 13.28 (Selecionando formas). 
 * Obs: Esta classe � utilizada para desenher com o m�todo paintComponent herdado de JPanel.
 *
 * Programador  : Gustavo Alves - gustavo.almeida13@fatec.sp.gov.br
 * Data Cria��o : 12 de mar de 2019
  *
 */

package ch27.ex27_28;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.security.SecureRandom;

import javax.swing.JPanel;

public class SelecionandoFormasPanel extends JPanel {
	private int numeroForma = 0;

	// desenha 20 vezes um forma com posi��es e dimens�es aleat�rias no m�todo
	// paintComponent
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(Color.WHITE);

		SecureRandom random = new SecureRandom();

		Graphics2D g2d = (Graphics2D) g;

		// determina qual forma � para desenha e desenha
		switch (numeroForma) {
		case 0: // c�rculo
			for (int i = 0; i < 20; i++)
				g.drawArc(random.nextInt(getWidth()), random.nextInt(getHeight()), 50, 50, 0, 360);
			break;
		case 1: // ret�ngulo
			for (int i = 0; i < 20; i++)
				g.drawRect(random.nextInt(getWidth()), random.nextInt(getHeight()), 50, 30);
			break;
		case 2: // quadrado
			for (int i = 0; i < 20; i++)
				g.drawRect(random.nextInt(getWidth()), random.nextInt(getHeight()), 50, 50);
			break;
		case 3: // oval
			for (int i = 0; i < 20; i++)
				g.drawOval(random.nextInt(getWidth()), random.nextInt(getHeight()), 25, 50);
			break;
		}

	}

	public void desenharForma(int numeroForma) {
		this.numeroForma = numeroForma;
		repaint();
	}

}
