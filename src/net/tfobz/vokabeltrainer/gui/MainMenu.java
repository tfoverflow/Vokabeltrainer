package net.tfobz.vokabeltrainer.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import net.tfobz.vokabeltrainer.model.VokabeltrainerDB;

public class MainMenu extends JPanel {
	Dimension buttonsize = new Dimension(300, 75);
	StartVokabeltrainer parentFrame = null;
	Import importDialog = null;
	
	public MainMenu(StartVokabeltrainer parentFrame) {
		this.parentFrame = parentFrame;
		
		this.setLayout(new GridBagLayout());

		// Setzt das Theme auf dem vom Benutzer ausgewählten Theme, (z.B. gtk+, windows,
		// ...)
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			// Bugfix: Wenn Gtk Theme benutzt wird, dann wird Background nicht benutzt
			if (UIManager.getSystemLookAndFeelClassName() == "com.sun.java.swing.plaf.gtk.GTKLookAndFeel")
				this.setBackground(new Color(56, 56, 56));
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		// Defniere Komponenten
		JLabel titel = new JLabel("Vokabeltrainer");
		titel.setFont(new Font("Karumbi	", Font.BOLD, 100));
		titel.setHorizontalAlignment(JLabel.CENTER);

		JButton settings = new JButton();
		settings.setPreferredSize(new Dimension(75, 75));
		JButton createLernkartei = new JButton("Neue Lernkartei");
		createLernkartei.setPreferredSize(buttonsize);
		JButton viewLernkartein = new JButton("Lernkarteienübersicht");
		viewLernkartein.setPreferredSize(buttonsize);
		GridBagConstraints c = new GridBagConstraints();
		
		//ActionListener für Buttons
		settings.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				importDialog = new Import();
				importDialog.setVisible(true);
			}
		});
		
		createLernkartei.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				parentFrame.changeToCreateLernkartei();
			}
		});
		
		viewLernkartein.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				parentFrame.changeToViewLernkarteien();
			}
		});
		

		// Platziere Komponenten
		//Titel
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 3;
		c.insets = new Insets(0, 0, 100, 0);
		this.add(titel, c);
		
		//Settings
		c.gridx = 5;
		c.gridy = 0;
		c.gridwidth = 3;
		c.insets = new Insets(0, 0, 100, 0);
		this.add(settings, c);

		//Erstellen / Ansehen
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 1;
		c.insets = new Insets(0, 0, 0, 100);
		this.add(createLernkartei, c);

		c.gridx = 3;
		c.gridy = 3;
		c.gridwidth = 1;
		c.insets = new Insets(0, 100, 0, 0);
		this.add(viewLernkartein, c);
	}

}
