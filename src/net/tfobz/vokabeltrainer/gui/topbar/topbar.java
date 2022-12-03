package net.tfobz.vokabeltrainer.gui.topbar;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class topbar extends JPanel {
	
	public topbar(int w) {
		
//		this.setMinimumSize(new Dimension(1920,100));
//		this.setBackground(Color.DARK_GRAY);
		this.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.GRAY));
	}

}
