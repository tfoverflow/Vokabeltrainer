package net.tfobz.vokabeltrainer.gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ViewLernkarteien extends JPanel {
	LernkarteienSammlung sammlung = null;
	public ViewLernkarteien() {
//		this.add(new JLabel("Lernkarteienuebersicht"));
		sammlung = new LernkarteienSammlung();
		sammlung.setSize(new Dimension(1000,800));
		sammlung.setBorder(BorderFactory.createLineBorder(Color.blue));
		this.add(sammlung);
//		sammlung.setBackground(new Color(255, 0, 0));
	}
}
