package net.tfobz.vokabeltrainer.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import net.tfobz.vokabeltrainer.model.VokabeltrainerDB;

public class MainMenu extends JFrame {
	Dimension buttonsize = new Dimension(600, 150);
	
	public MainMenu() {
		this.setTitle("Vokableltrainer - MainMenu");
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new GridLayout(5, 5));
		
		// Setzt das Theme auf dem vom Benutzer ausgewählten Theme, (z.B. gtk+, windows, ...)
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			//Bugfix: Wenn Gtk Theme benutzt wird, dann wird Background nicht benutzt
			if(UIManager.getSystemLookAndFeelClassName() == "com.sun.java.swing.plaf.gtk.GTKLookAndFeel") {
				this.getContentPane().setBackground(new Color(56, 56, 56));
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		JLabel titel = new JLabel("Vokabeltrainer");
		titel.setFont(new Font("Karumbi", Font.BOLD, 85));
		titel.setHorizontalAlignment(JLabel.CENTER);
		
		JButton createLernkartei = new JButton("Neue Lernkartei");
		createLernkartei.setPreferredSize(buttonsize);
		JButton viewLernkartein = new JButton("Lernkarteienübersicht");
		viewLernkartein.setPreferredSize(buttonsize);
		
		for (int i = 0; i < 25; i++) {
			switch (i) {
				case 7: {
					this.add(titel);
					break;
				}
				case 16: {
					this.add(createLernkartei);
					break;
				}
				case 18: {
					this.add(viewLernkartein);
					break;
				}
				default:
					this.add(Box.createRigidArea(buttonsize));
//					this.add(new JButton(""));
			}
		}
		
	}

}
