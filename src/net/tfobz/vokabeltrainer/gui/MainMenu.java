package net.tfobz.vokabeltrainer.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenu extends JFrame {
	Dimension buttonsize = new Dimension(600, 150);
	
	public MainMenu() {
		this.setTitle("Vokableltrainer - MainMenu");
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout();
		
		JButton createLernkartei = new JButton("Neue Lernkartei");
		createLernkartei.setPreferredSize(buttonsize);
		JButton viewLernkartein = new JButton("pla Lernkartei");
		viewLernkartein.setPreferredSize(buttonsize);
		
		
	}

}
