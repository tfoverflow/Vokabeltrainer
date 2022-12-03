package net.tfobz.vokabeltrainer.gui.topbar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class topbar extends JPanel {
	
	private JLabel titel = new JLabel();
	
	public topbar() {
		this.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.GRAY));
		this.setLayout(new BorderLayout());
	
		titel.setHorizontalAlignment(JLabel.CENTER);
		titel.setFont(new Font(titel.getFont().getFontName(),Font.PLAIN, 40));
		
		
		this.add(titel,BorderLayout.CENTER);		
	}

	
	public void setTitel(String t) {
		titel.setName(t);
	}
}
