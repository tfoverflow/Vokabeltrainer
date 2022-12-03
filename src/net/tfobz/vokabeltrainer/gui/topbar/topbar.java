package net.tfobz.vokabeltrainer.gui.topbar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class topbar extends JPanel {
	
	private JLabel titel = new JLabel();
	private JPanel importPanel = new JPanel();
	private JButton importButton = new JButton();
	
	public topbar() {
		this.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.GRAY));
		this.setLayout(new BorderLayout());
	
		titel.setHorizontalAlignment(JLabel.CENTER);
		titel.setFont(new Font(titel.getFont().getFontName(),Font.PLAIN, 40));
		

		importPanel.setLayout(new BoxLayout(importPanel, BoxLayout.X_AXIS));
		importPanel.add(importButton);
		importButton.setPreferredSize(new Dimension(50,50));
		importButton.setMaximumSize(new Dimension(50,50));
		importButton.setMinimumSize(new Dimension(50,50));
		importPanel.setBorder(BorderFactory.createLineBorder(Color.cyan));
		
		this.add(titel,BorderLayout.CENTER);	
		this.add(importPanel, BorderLayout.LINE_END);
	}

	
	public void setTitel(String t) {
		titel.setText(t);
	}
}
