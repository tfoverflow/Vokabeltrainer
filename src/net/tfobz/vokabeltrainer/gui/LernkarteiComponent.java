package net.tfobz.vokabeltrainer.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;

import net.tfobz.vokabeltrainer.model.Lernkartei;

public class LernkarteiComponent extends JComponent {
	Lernkartei kartei = null;
	JLabel percent = null;
	JLabel name = null;
	JButton playButton = null;
	public LernkarteiComponent(Lernkartei kartei) {
		this.kartei = kartei;
		
		this.setBackground(new Color(0, 255, 0));
		this.setMinimumSize(new Dimension(250, 200));
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		percent = new JLabel(getPercent());
		percent.setPreferredSize(new Dimension(250, 100));
		percent.setBorder(BorderFactory.createLineBorder(Color.gray));
		
		name = new JLabel(kartei.getBeschreibung());
		name.setPreferredSize(new Dimension(200, 50));
		name.setBorder(BorderFactory.createLineBorder(Color.gray));
		playButton = new JButton();
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 10;
		c.gridheight = 7;
		this.add(percent, c);
		
		c.gridx = 0;
		c.gridy = 7;
		c.gridwidth = 7;
		c.gridheight = 3;
		this.add(name, c);
		
		c.gridx = 7;
		c.gridy = 7;
		c.gridwidth = 3;
		c.gridheight = 3;
		this.add(playButton, c);
	}
	
	private String getPercent() {
		return "69%";
	}
}
