package net.tfobz.vokabeltrainer.gui.Lernansicht;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import net.tfobz.vokabeltrainer.model.Fach;
import net.tfobz.vokabeltrainer.model.Karte;
import net.tfobz.vokabeltrainer.model.Lernkartei;
import net.tfobz.vokabeltrainer.model.VokabeltrainerDB;

public class LernAnsicht extends JPanel {

	private static final long serialVersionUID = -5505440539728124434L;

	private JTextField wortEins = null;
	private JTextField wortZwei = null;

	private Dimension textFieldSize = new Dimension(400, 50);
	private Dimension buttonSize = new Dimension(250, 60);

	private ArrayList<Karte> karten = new ArrayList<Karte>();
	private ArrayList<Karte> richtigeKarten = new ArrayList<Karte>();
	private ArrayList<Karte> falscheKarten = new ArrayList<Karte>();
	private Karte aktuelleKarte;

	public LernAnsicht(Lernkartei kartei, Fach fach) {
		this.setLayout(new GridBagLayout());

		aktuelleKarte = VokabeltrainerDB.getZufaelligeKarte(kartei.getNummer(), fach.getNummer());

		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 0;
		c.weighty = 0.1;
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.fill = GridBagConstraints.BOTH;

		// Wort Eins
		JLabel wortEinsLabel = new JLabel(kartei.getWortEinsBeschreibung());
		wortEinsLabel.setHorizontalAlignment(JLabel.CENTER);
		c.gridx = 2;
		c.gridwidth = 2;
		this.add(wortEinsLabel, c);

		wortEins = new JTextField(aktuelleKarte.getWortEins());
		wortEins.setEditable(false);
		wortEins.setPreferredSize(textFieldSize);
		wortEins.setMinimumSize(textFieldSize);
		c.gridx = 4;
		c.gridwidth = 4;
		this.add(wortEins, c);

		// Wort Zwei
		JLabel wortZweiLabel = new JLabel(kartei.getWortZweiBeschreibung());
		wortZweiLabel.setHorizontalAlignment(JLabel.CENTER);
		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = 2;
		this.add(wortZweiLabel, c);

		wortZwei = new JTextField();
		wortZwei.setPreferredSize(textFieldSize);
		wortZwei.setMinimumSize(textFieldSize);
		wortZwei.requestFocusInWindow();
		c.gridx = 4;
		c.gridwidth = 4;
		this.add(wortZwei, c);

		// Weiter & Auflösen
		c.gridy = 2;
		JButton weiter = new JButton("Weiter");
		weiter.setPreferredSize(buttonSize);
		weiter.setMinimumSize(buttonSize);
		weiter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (aktuelleKarte.getRichtig(wortZwei.getText().trim())) {
					VokabeltrainerDB.setKarteRichtig(aktuelleKarte);
					//TODO Belonung hinzufügen
					
				} else {
					//@formatter:off
					VokabeltrainerDB.setKarteFalsch(aktuelleKarte);
					
					wortZwei.setBorder(BorderFactory.createLineBorder(Color.red));
					JOptionPane.showMessageDialog(null, 
							"Wort wäre " + aktuelleKarte.getWortZwei() + " gewesen.", 
							"Falsche Eingabe", 
							JOptionPane.INFORMATION_MESSAGE, 
							new ImageIcon("src/net/tfobz/vokabeltrainer/gui/assets/info.png"));
					//@formatter:on
				}
			}
		});
		
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
		DefaultTableModel table = new DefaultTableModel(new String[]{"De", "En", "De", "En"}, 1); 
		JTable jTable = new JTable(table);
		
		
		ScrollPane scrollpane = new ScrollPane();
		scrollpane.add(jTable);
		scrollpane.setPreferredSize(new Dimension(1240, 400));
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 10;
		c.gridheight = 10;
		c.insets = new Insets(0, 20, 0, 20);
		c.fill = GridBagConstraints.HORIZONTAL;
		this.add(scrollpane, c);
	}
	
}
