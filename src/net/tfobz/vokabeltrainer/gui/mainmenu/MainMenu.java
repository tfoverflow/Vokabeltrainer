package net.tfobz.vokabeltrainer.gui.mainmenu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import net.tfobz.vokabeltrainer.gui.StartVokabeltrainer;

public class MainMenu extends JPanel {
	private static final long serialVersionUID = -7222145189470706501L;

	private Dimension buttonsize = new Dimension(300, 75);
	StartVokabeltrainer parentFrame = null;

	public MainMenu(StartVokabeltrainer parentFrame) {
		this.parentFrame = parentFrame;
		this.setLayout(new GridBagLayout());

		// Setzt das Theme, wenn möglich, aufs GTK-Theme.
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
			this.setBackground(new Color(56, 56, 56));
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		// Defniere Komponenten :3
		JLabel titel = new JLabel("Vokabeltrainer");
		titel.setFont(new Font("Karumbi	", Font.BOLD, 100));
		titel.setHorizontalAlignment(JLabel.CENTER);

		JButton createLernkartei = new JButton("Neue Lernkartei");
		createLernkartei.setPreferredSize(buttonsize);
		JButton viewLernkartein = new JButton("Lernkarteienübersicht");
		viewLernkartein.setPreferredSize(buttonsize);
		GridBagConstraints c = new GridBagConstraints();

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

		// Platziere Komponenten :3
		//Titel :3
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 3;
		c.insets = new Insets(0, 0, 100, 0);
		this.add(titel, c);

		//Erstellen / Ansehen :3
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
