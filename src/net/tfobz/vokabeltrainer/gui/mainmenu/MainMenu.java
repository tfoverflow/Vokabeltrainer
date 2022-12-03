package net.tfobz.vokabeltrainer.gui.mainmenu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatDarkLaf;

import net.tfobz.vokabeltrainer.gui.StartVokabeltrainer;

public class MainMenu extends JPanel {
	private static final long serialVersionUID = -7222145189470706501L;

	private Dimension buttonsize = new Dimension(300, 100);
	StartVokabeltrainer parentFrame = null;
	int height;

	public MainMenu(StartVokabeltrainer parentFrame) {
		this.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				height = (int) getHeight();
			}
		});
		
		this.parentFrame = parentFrame;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// Defniere Komponenten :3
		JButton createLernkartei = new JButton("Neue Lernkartei");
		createLernkartei.setPreferredSize(buttonsize);
		createLernkartei.setMinimumSize(buttonsize);
		createLernkartei.setMaximumSize(buttonsize);
		JButton viewLernkartein = new JButton("Lernkarteienübersicht");
		viewLernkartein.setPreferredSize(buttonsize);
		viewLernkartein.setMinimumSize(buttonsize);
		viewLernkartein.setMaximumSize(buttonsize);
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
//		buttonPanel.setBorder(BorderFactory.createLineBorder(Color.cyan));
		
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
		
		//hinzufügen der Komponenten
		this.add(Box.createRigidArea(new Dimension(1920,300)));
		buttonPanel.add(createLernkartei);
		buttonPanel.add(Box.createRigidArea(new Dimension(buttonsize.width*2,buttonsize.height)));
		buttonPanel.add(viewLernkartein);
		this.add(buttonPanel);
		
//		this.setLayout(new GridBagLayout());
//
//
//		   
//		// Defniere Komponenten :3
//		JLabel titel = new JLabel("");
//		titel.setFont(new Font("Karumbi	", Font.BOLD, 100));
//		titel.setHorizontalAlignment(JLabel.CENTER);
//
//		JButton createLernkartei = new JButton("Neue Lernkartei");
//		createLernkartei.setPreferredSize(buttonsize);
//		JButton viewLernkartein = new JButton("Lernkarteienübersicht");
//		viewLernkartein.setPreferredSize(buttonsize);
//		GridBagConstraints c = new GridBagConstraints();
//
//		createLernkartei.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				parentFrame.changeToCreateLernkartei();
//			}
//		});
//
//		viewLernkartein.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				parentFrame.changeToViewLernkarteien();
//			}
//		});
//
//		// Platziere Komponenten :3
//		//Titel :3
//		c.gridx = 1;
//		c.gridy = 1;
//		c.gridwidth = 3;
//		c.insets = new Insets(0, 0, 100, 0);
//		this.add(titel, c);
//
//		//Erstellen / Ansehen :3
//		c.gridx = 1;
//		c.gridy = 3;
//		c.gridwidth = 1;
//		c.insets = new Insets(0, 0, 0, 100);
//		this.add(createLernkartei, c);
//
//		c.gridx = 3;
//		c.gridy = 3;
//		c.gridwidth = 1;
//		c.insets = new Insets(0, 100, 0, 0);
//		this.add(viewLernkartein, c);
	}

}
