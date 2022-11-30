package net.tfobz.vokabeltrainer.gui.Lernansicht;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.ScrollPane;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.tfobz.vokabeltrainer.model.Lernkartei;

public class LernAnsicht extends JPanel {

	private static final long serialVersionUID = -5505440539728124434L;

	private JTextField wortEins = null;
	private JTextField wortZwei = null;
	
	private Dimension textFieldSize = new Dimension(400, 50);
	private Dimension buttonSize    = new Dimension(250, 60);
	
	public LernAnsicht(Lernkartei kartei) {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 0;
		c.weighty = 0.1;
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.fill = GridBagConstraints.BOTH;
		
		//Wort Eins
		JLabel wortEinsLabel= new JLabel(kartei.getWortEinsBeschreibung());
		wortEinsLabel.setHorizontalAlignment(JLabel.CENTER);
		c.gridx = 2;
		c.gridwidth = 2;
		this.add(wortEinsLabel, c);
		
		wortEins = new JTextField(kartei.getWortEinsBeschreibung());
		wortEins.setEditable(false);
		wortEins.setPreferredSize(textFieldSize);
		wortEins.setMinimumSize(textFieldSize);
		c.gridx = 4;
		c.gridwidth = 4;
		this.add(wortEins, c);
		
		//Wort Zwei
		JLabel wortZweiLabel= new JLabel(kartei.getWortZweiBeschreibung());
		wortZweiLabel.setHorizontalAlignment(JLabel.CENTER);
		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = 2;
		this.add(wortZweiLabel, c);
		
		
		wortZwei = new JTextField();
		wortZwei.setPreferredSize(textFieldSize);
		wortZwei.setMinimumSize(textFieldSize);
		c.gridx = 4;
		c.gridwidth = 4;
		this.add(wortZwei, c);
		
		
		
		
		//Weiter & Auflösen
		c.gridy = 2;
		JButton weiter = new JButton("Weiter");
		weiter.setPreferredSize(buttonSize);
		weiter.setMinimumSize(buttonSize);
		
		c.gridx = 2;
		c.gridwidth = 2;
		c.insets = new Insets(25, 50, 25, 50);
		this.add(weiter, c);
		
		
		JButton aufloesen = new JButton("Auflösen");
		aufloesen.setPreferredSize(buttonSize);
		aufloesen.setMinimumSize(buttonSize);
		
		c.gridx = 4;
		c.gridwidth = 2;
		this.add(aufloesen, c);
		
		
		//Tabelle
		ScrollPane scrollpane = new ScrollPane();
		scrollpane.setPreferredSize(new Dimension(1240, 400));
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 10;
		c.gridheight = 10;
		c.insets = new Insets(0, 20, 0, 20);
		c.fill = GridBagConstraints.HORIZONTAL;
		this.add(scrollpane, c);
		
//		c.gridx = 10;
//		c.gridy = 0;
//		c.gridwidth = 1;
//		c.gridheight = 1;
//		this.add(new JButton(), c);
	}
	
}
