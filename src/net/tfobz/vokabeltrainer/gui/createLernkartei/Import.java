package net.tfobz.vokabeltrainer.gui.createLernkartei;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.tfobz.vokabeltrainer.gui.viewLernkarteien.LernkarteienSammlung;
import net.tfobz.vokabeltrainer.model.Karte;
import net.tfobz.vokabeltrainer.model.Lernkartei;
import net.tfobz.vokabeltrainer.model.VokabeltrainerDB;

public class Import extends JDialog {

	private static final long serialVersionUID = -8085120358340962505L;
	
	// Diese Variablen muessen Global sein, da sie von den ActionListener verwendet werden :3
	private JCheckBox grossKleinschreibung = null;
	private JLabel labelSelectedFile = null;
	private JButton importButton = null;
	private ArrayList<Karte> karten = null;

	public Import(LernkarteienSammlung sammlung) {
		/**
		 * panels[0-3] Eingabefelder + GrossKleinschreibung Checkbox :3
		 * panels[4] Datei auswaehlen :3
		 * panels[5] speichern (In Datenbank abspeichern) :3
		 */
		JPanel     panels[]    = new JPanel[6];
		JTextField textfield[] = new JTextField[6];
		JLabel     labels[]    = new JLabel[6];
		String[]   labelText   = { "Beschreibung", "1. Sprache", "2. Sprache", "Groß/Kleinschreibung" };

		
		this.setTitle("Vokabeltrainer - Importieren");
		this.setSize(new Dimension(600, 345));
		this.setLocationRelativeTo(null); //Öffne in der Bildschirmmitte :3
		this.setResizable(false);
		this.setModal(true);

		this.getContentPane().setLayout(new GridBagLayout());

		for (int i = 0; i < panels.length; i++) {
			panels[i] = new JPanel();
//			panels[i].setBorder(BorderFactory.createLineBorder(Color.gray));
			panels[i].setPreferredSize(new Dimension(600, 40));
			panels[i].setLayout(new FlowLayout(FlowLayout.LEFT));
		}

		// Add & Define der manuellen Eingabe der Lernkarteiendaten
		for (int i = 0; i < 4; i++) {

			labels[i] = new JLabel(labelText[i]);
			labels[i].setPreferredSize(new Dimension(160, 35));
			labels[i].setHorizontalAlignment(SwingConstants.RIGHT);
			panels[i].add(labels[i]);

			textfield[i] = new JTextField();
			textfield[i].setPreferredSize(new Dimension(420, 35));
			labels[i].setLabelFor(textfield[i]);
			textfield[i].addKeyListener(new KeyListener() {
				
				@Override
				public void keyTyped(KeyEvent e) {
					((JTextField) e.getSource()).setBorder(null);
				}
				
				@Override
				public void keyReleased(KeyEvent e) {}
				
				@Override
				public void keyPressed(KeyEvent e) {}
			});;

			panels[i].add(labels[i]);
			if (i != 3)
				panels[i].add(textfield[i]);
//			panels[i].setBorder(BorderFactory.createLineBorder(Color.gray));
		}

		grossKleinschreibung = new JCheckBox();
		panels[3].add(grossKleinschreibung);

		importButton = new JButton("Datei auswählen...");
		importButton.setPreferredSize(new Dimension(150, 40));
		labelSelectedFile = new JLabel();
		labelSelectedFile.setHorizontalAlignment(JLabel.RIGHT);
		importButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileFilter(new FileNameExtensionFilter("csv", "txt"));

				if (fileChooser.showSaveDialog(rootPane) == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					try {
						// Lese alle Karten aus der Datei :3
						karten = parseLernkartei(selectedFile, grossKleinschreibung.isSelected());
						labelSelectedFile.setForeground(Color.black);
						labelSelectedFile.setText(selectedFile.getPath());
					} catch (IllegalArgumentException | IOException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), e1.getClass().toString(),
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		panels[4].add(importButton);
		panels[4].add(labelSelectedFile);
		panels[4].setPreferredSize(new Dimension(600, 50));

		JButton saveButton = new JButton("Speichern");
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean isAllDataInserted = true;
				// Kontrolle, ob alle benoetigten Daten eingegeben wurden :3
				for(int i = 0; i < 3; i++) {
					if(textfield[i].getText().trim().isEmpty()) {
						textfield[i].setBorder(BorderFactory.createLineBorder(Color.red));
						isAllDataInserted = false;
					}
				}
				if (karten == null || karten.isEmpty()) {
					importButton.setBorder(BorderFactory.createLineBorder(Color.red));
					labelSelectedFile.setForeground(Color.red);
					labelSelectedFile.setText("Bitte wählen Sie eine Datei aus!");
					isAllDataInserted = false;
				}
				
				if(isAllDataInserted) {
					Lernkartei kartei = new Lernkartei(textfield[0].getText().trim(), textfield[1].getText().trim(), textfield[2].getText().trim(), false, grossKleinschreibung.isSelected());
					VokabeltrainerDB.hinzufuegenLernkartei(kartei);
					VokabeltrainerDB.importierenKarten(kartei.getNummer(), labelSelectedFile.getText());
					setVisible(false);
					sammlung.reloadLernkarteien();
				}
			}
		});
		
		panels[5].add(saveButton);
		panels[5].setPreferredSize(new Dimension(600, 50));
		panels[5].setLayout(new FlowLayout(FlowLayout.RIGHT));

		
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 10;
		c.gridheight = 1;
		
		//Platziere alle Eingabefelder :3
		for (int i = 0; i < 4; i++) {
			this.add(panels[i], c);
			c.gridy++;
		}
		
		//Platziere Importbutton :3
		c.gridy = 6;
		c.insets = new Insets(30, 0, 0, 0);
		this.add(panels[4], c);
		
		//Platziere Savebutton :3
		c.gridy = 10;
		c.insets = new Insets(0, 0, 0, 0);
		this.add(panels[5], c);
		
	}
	
	/**
	 * Liest alle Karten aus einer Datei :3
	 * @param input
	 * @param grossKleinschreibung
	 * @return
	 * @throws IOException
	 * @throws IllegalArgumentException
	 */
	public static ArrayList<Karte> parseLernkartei(File input, boolean grossKleinschreibung)
			throws IOException, IllegalArgumentException {
		ArrayList<Karte> ret = new ArrayList<>();

		BufferedReader reader = new BufferedReader(new FileReader(input));
		String         readLine;
		// Gelesener String aufgeteilt auf die einzelbenoetigten Argumente :3
		String[] readLineSplit;
		while ((readLine = reader.readLine()) != null) {
			readLineSplit = readLine.split(";");
			// Kontrolle, ob richtige Formatierung vorhanden ist :3
			if (readLineSplit.length < 2 || readLineSplit.length > 3) {
				reader.close();
				throw new IllegalArgumentException("Datei muss eine der folgenden Formatierungen haben:\n"
						+ "Wort1;Wort2\n" + "oder\n" + "Wort1;Wort2;Fachnummer");
			}
			// Karte erstellen und hinzufuegen :3
			int num = readLineSplit.length == 2 ? 0 : Integer.parseInt(readLineSplit[2].trim());
			ret.add(new Karte(num, readLineSplit[0], readLineSplit[1], false, grossKleinschreibung));
		}
//		System.out.println(ret.toString());
		reader.close();
		return ret;
	}

	/*
	 * Hab 3 Stunden meines Leben verschwendet, das zu programmieren, 
	 * bis ich herausgefunden, dass importieren schon in VokabeltrainerDB existiert.
	 * Ich werde das ganz sicher nicht löschen! ૮ ˶ᵔ ᵕ ᵔ˶ ა
	 *
	private void saveLernkartei(ArrayList<Karte> karten,
			String beschreibung,
			String ersteSprache,
			String zweiteSprache,
			Boolean grossKleinschreibung) throws DatenBankException {
		int highestFach = -1;
		// Finde nummer des Letzten Faches
		for (Karte karte : karten) {
			if (highestFach < karte.getNummer())
				highestFach = karte.getNummer();
		}
		
		Lernkartei kartei = new Lernkartei(beschreibung, ersteSprache, zweiteSprache, false, grossKleinschreibung);
		switch (VokabeltrainerDB.hinzufuegenLernkartei(kartei)) {
			case -1: {
				throw new DatenBankException("Es ist ein Datenbankfehler aufgetreten :3");
			}
			case -2: {
				throw new DatenBankException("Es ist ein Validierungsfehler aufgetreten :3");
			}
			default:
		}
		
		for(int i = 0; i < highestFach; i++) {
			Fach fach = new Fach(-1, null, 0, new Date());
			switch (VokabeltrainerDB.hinzufuegenFach(kartei.getNummer(), fach)) {
				case -1: {
					throw new DatenBankException("Lernkartei existiert nicht oder Fach ist schon in Datebank enthalten :3");
				}
				default:
			}
		}
		
		for(Karte karte: karten) {
			int fachNummer = karte.getNummer();
			karte.setNummer(-1);
			switch (VokabeltrainerDB.hinzufuegenKarte(kartei.getNummer(), karte)) {
				case -1: {
					throw new DatenBankException("Es ist ein Datenbankfehler aufgetreten :3");
				}
				case -2: {
					throw new DatenBankException("Karte ist nicht vollständig ausgefüllt :3");
				}
				case -3: {
					throw new DatenBankException("Lernkartei existiert nicht :3");
				}
				case -4: {
					throw new DatenBankException("Es existiert kein Fach in der Lernkartei :3");
				}
				case -5: {
					throw new DatenBankException("Dieselbe Karte existiert schon in der Lernkartei in irgendeinem Fach :3");
				}
				default:
			}
		}
	}
	*/
}
