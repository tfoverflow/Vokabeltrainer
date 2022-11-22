package net.tfobz.vokabeltrainer.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;

public class Import extends JDialog {
	JPanel panels[] = new JPanel[4];
	JLabel labels[] = new JLabel[4];
	JTextField textfield[] = new JTextField[4];
	String[] labelText = {"Beschreibung", "1. Sprache", "2. Sprache", "Groß/Kleinschreibung"};
	
	public Import() {
		this.setTitle("Vokabeltrainer - Importieren");
		this.setSize(new Dimension(1000, 850));
		this.setResizable(false);
		this.setModal(true);
		this.setVisible(true);

		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

		//Add & Define Components
		for(int i = 0; i < labels.length; i++) {
			this.panels[i] = new JPanel();
			labels[i] = new JLabel(labelText[i]);
			panels[i].add(labels[i]);
			
			textfield[i] = new JTextField();
			textfield[i].setSize(500, 25);
			labels[i].setLabelFor(textfield[i]);
			
			panels[i].add(labels[i]);
			panels[i].add(textfield[i]);
			
			this.add(panels[i], BorderLayout.CENTER);
		}
		
	}
}
