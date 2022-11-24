package net.tfobz.vokabeltrainer.gui;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CreateLernkartei extends JPanel {
	public CreateLernkartei() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.add(Box.createRigidArea(new Dimension(0,30)));
		
		JLabel titel = new JLabel("Create Lernkartei");
		titel.setAlignmentX(CENTER_ALIGNMENT);
		titel.setFont(new Font(titel.getFont().getFontName(),Font.PLAIN, 40));
		this.add(titel);
	}
}
