/*
 * Objetivo     : 13.9 (Tri�ngulos aleat�rios) Escreva um aplicativo que exibe tri�ngulos aleatoriamente gerados em diferentes cores. 
 * Cada tri�ngulo deve ser preenchido com uma cor diferente. Utilize a classe GeneralPath e o m�todo fill da classe Graphics2D para desenhar os tri�ngulos.
 * 
 * Programador  : Gustavo Alves - gustavo.almeida13@fatec.sp.gov.br
 * Data Cria��o : 6 de mar de 2019 
 */

package ch27.ex27_09;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.security.SecureRandom;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class RandomTriangles extends JPanel {

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		setBackground(Color.WHITE);

		SecureRandom random = new SecureRandom();

		GeneralPath triangulo = new GeneralPath();

		// pontos para formar um tri�ngulo
		int[] xPoints = { 10, 50, 30 };
		int[] yPoints = { 50, 50, 15 };

		// configura a coordenada inicial do General Path
		triangulo.moveTo(xPoints[0], yPoints[0]);

		// cria o tri�ngulo -- isso n�o desenha o tri�ngulo
		for (int count = 1; count < yPoints.length; count++)
			triangulo.lineTo(xPoints[count], yPoints[count]);

		triangulo.closePath(); // fecha a forma

		for (int i = 0; i < 100; i++) {
			g2d.translate(random.nextInt(200), random.nextInt(100));
			g2d.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
			g2d.fill(triangulo);

		}

	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Tri�ngulos Aleat�rios");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		RandomTriangles draw = new RandomTriangles();
		frame.add(draw);
		frame.setSize(300, 150);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
	}

}
