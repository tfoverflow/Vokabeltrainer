package net.tfobz.vokabeltrainer.gui;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ViewLernkarteien extends JPanel {
	LernkarteienSammlung sammlung = null;
	public ViewLernkarteien() {
//		this.add(new JLabel("Lernkarteienuebersicht"));
		sammlung = new LernkarteienSammlung();
		this.add(sammlung);
//		sammlung.setBackground(new Color(255, 0, 0));
	}
}
