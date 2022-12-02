package net.tfobz.vokabeltrainer.gui.Lernansicht;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.hsqldb.Table;

import net.tfobz.vokabeltrainer.gui.StartVokabeltrainer;
import net.tfobz.vokabeltrainer.gui.viewLernkarteien.FachAuswahl;
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
	private Karte aktuelleKarte;
	
	private DefaultTableModel richtigeKarten, falscheKarten; 

	public LernAnsicht(Lernkartei kartei, Fach fach) {
		this.setLayout(new GridBagLayout());

		
		
		
		aktuelleKarte = VokabeltrainerDB.getZufaelligeKarte(kartei.getNummer(), fach.getNummer());
		if(aktuelleKarte == null) {
			keineKarteImFach(kartei);
		}
		
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
		wortZwei.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				wortZwei.setBorder(null);
			}
		});
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
				if(!wortZwei.getText().trim().isEmpty()) {
					if (aktuelleKarte.getRichtig(wortZwei.getText())) {
						VokabeltrainerDB.setKarteRichtig(aktuelleKarte);
						//TODO Belonung hinzufügen
						richtigeKarten.addRow(new Object[] {aktuelleKarte.getWortEins(), aktuelleKarte.getWortZwei()});
					} else {
						//@formatter:off
						VokabeltrainerDB.setKarteFalsch(aktuelleKarte);
						
						falscheKarten.addRow(new Object[] {aktuelleKarte.getWortEins(), aktuelleKarte.getWortZwei()});
						
						wortZwei.setBorder(BorderFactory.createLineBorder(Color.red));
						JOptionPane.showMessageDialog(null, 
								"Wort wäre " + aktuelleKarte.getWortZwei() + " gewesen.", 
								"Falsche Eingabe", 
								JOptionPane.INFORMATION_MESSAGE, 
								new ImageIcon("src/net/tfobz/vokabeltrainer/gui/assets/info.png"));
						//@formatter:on
					}
					
					aktuelleKarte = VokabeltrainerDB.getZufaelligeKarte(kartei.getNummer(), fach.getNummer());
					if(aktuelleKarte == null)
						keineKarteImFach(kartei);
						
					wortEins.setText(aktuelleKarte.getWortEins());
					wortZwei.setText("");
					wortZwei.setBorder(null);
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
		aufloesen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, 
						"Wort wäre " + aktuelleKarte.getWortZwei() + " gewesen.", 
						"Auflösung", 
						JOptionPane.INFORMATION_MESSAGE, 
						new ImageIcon("src/net/tfobz/vokabeltrainer/gui/assets/info.png"));
				aktuelleKarte = VokabeltrainerDB.getZufaelligeKarte(kartei.getNummer(), fach.getNummer());
				if(aktuelleKarte == null)
					keineKarteImFach(kartei);
					
				wortEins.setText(aktuelleKarte.getWortEins());
				wortZwei.setText("");
				wortZwei.setBorder(null);
			}
		});
		
		c.gridx = 4;
		c.gridwidth = 2;
		this.add(aufloesen, c);
		
		
		//Tabelle
		richtigeKarten = new DefaultTableModel(new String[]{kartei.getWortEinsBeschreibung(),kartei.getWortZweiBeschreibung()}, 0);
		falscheKarten = new DefaultTableModel(new String[]{kartei.getWortEinsBeschreibung(),kartei.getWortZweiBeschreibung()}, 0);
		JTable jTableRichtig = new JTable(richtigeKarten);
		jTableRichtig.setBackground(new Color(0, 255, 0, 100));
		jTableRichtig.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		jTableRichtig.setPreferredSize(new Dimension(640, 400));
		JTable jTableFalsch = new JTable(falscheKarten);
		jTableFalsch.setBackground(new Color(255, 0, 0, 100));
		jTableFalsch.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		jTableFalsch.setPreferredSize(new Dimension(640, 400));
		
		JPanel tables = new JPanel();
		JPanel panelTableRichtig = new JPanel();
		panelTableRichtig.add(jTableRichtig);
		panelTableRichtig.setBorder(BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                "Richtig beantwortet",
                TitledBorder.CENTER,
                TitledBorder.TOP));
		tables.add(panelTableRichtig);
		JPanel panelTableFalsch = new JPanel();
		panelTableFalsch.add(jTableFalsch);
		panelTableFalsch.setBorder(BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                "Falsch beantwortet",
                TitledBorder.CENTER,
                TitledBorder.TOP));
		tables.add(panelTableFalsch);
		
		JScrollPane scrollpane = new JScrollPane(tables);
		scrollpane.setBorder(BorderFactory.createLineBorder(Color.red));
		scrollpane.setPreferredSize(new Dimension(1000, 400));
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 10;
		c.gridheight = 10;
		c.insets = new Insets(0, 20, 0, 20);
		c.fill = GridBagConstraints.HORIZONTAL;
		this.add(scrollpane, c);
	}
	
	private void keineKarteImFach(Lernkartei kartei) {
		JOptionPane.showMessageDialog(this, "Sie haben alle Karten in diesem Fach behandelt.");
		StartVokabeltrainer startVokabeltrainer = (StartVokabeltrainer) StartVokabeltrainer.getStartVokabelTrainer(this);
		startVokabeltrainer.changeToViewLernkarteien();
		FachAuswahl fachAuswahl = new FachAuswahl(kartei, startVokabeltrainer);
		fachAuswahl.setVisible(true);
	}
	
}
