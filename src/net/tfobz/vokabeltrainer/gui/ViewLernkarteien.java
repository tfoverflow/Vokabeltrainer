package net.tfobz.vokabeltrainer.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ViewLernkarteien extends JPanel {
	LernkarteienSammlung sammlung = null;
	public ViewLernkarteien() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.add(Box.createRigidArea(new Dimension(0,30)));
		JLabel titel = new JLabel("Lernkarteienübersicht");
		titel.setAlignmentX(CENTER_ALIGNMENT);
		titel.setFont(new Font(titel.getFont().getFontName(),Font.PLAIN, 40));
		this.add(titel);
		
		this.add(Box.createRigidArea(new Dimension(0,30)));
		sammlung = new LernkarteienSammlung();
		sammlung.setSize(1920,200);
		sammlung.setLocation(0,100);
		this.add(sammlung); 
//		sammlung.setBackground(new Color(255, 0, 0));
	}
}
