package net.tfobz.vokabeltrainer.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import net.tfobz.vokabeltrainer.model.Karte;

public class Import extends JDialog {

	JPanel     panels[]    = new JPanel[6];
	JTextField textfield[] = new JTextField[6];
	JLabel     labels[]    = new JLabel[6];
	String[]   labelText   = { "Beschreibung", "1. Sprache", "2. Sprache", "Groß/Kleinschreibung" };

	JButton importButton = null;

	ArrayList<Karte> karten = null;

	public Import() {
		this.setTitle("Vokabeltrainer - Importieren");
		this.setSize(new Dimension(750, 400));
		this.setResizable(false);
		this.setModal(true);

		this.getContentPane().setLayout(new BorderLayout(10, 10));

		for (int i = 0; i < panels.length; i++) {
			panels[i] = new JPanel();
//			panels[i].setBorder(BorderFactory.createLineBorder(Color.gray));
			panels[i].setPreferredSize(new Dimension(700, 30));
			panels[i].setLayout(new FlowLayout(FlowLayout.LEFT));
		}

		// Add & Define der manuellen Eingabe der Lernkarteiendaten
		for (int i = 0; i < 4; i++) {

			labels[i] = new JLabel(labelText[i]);
			labels[i].setPreferredSize(new Dimension(170, 25));
			labels[i].setHorizontalAlignment(SwingConstants.RIGHT);
			panels[i].add(labels[i]);

			textfield[i] = new JTextField();
			textfield[i].setPreferredSize(new Dimension(500, 25));
			labels[i].setLabelFor(textfield[i]);

			panels[i].add(labels[i]);
			if (i != 3)
				panels[i].add(textfield[i]);
//			panels[i].setBorder(BorderFactory.createLineBorder(Color.gray));
		}

		JCheckBox grossKleinschreibung = new JCheckBox();
		panels[3].add(grossKleinschreibung);

		importButton = new JButton("Datei auswählen...");
		JLabel labelSelectedFile = new JLabel();
		importButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileFilter(new FileNameExtensionFilter("csv", "txt"));

				if (fileChooser.showSaveDialog(rootPane) == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					try {
						// Lese alle Karten aus der Datei
						karten = parseLernkartei(selectedFile, grossKleinschreibung.isSelected());
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
		panels[4].setPreferredSize(new Dimension(700, 50));

		JButton saveButton = new JButton("Speichern");
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (karten == null || karten.isEmpty())
					JOptionPane.showMessageDialog(null, "Bitte wählen Sie eine Datei aus", "Fehlende Daten",
							JOptionPane.WARNING_MESSAGE);
			}
		});
		panels[5].add(saveButton);
		panels[5].setPreferredSize(new Dimension(700, 50));
		panels[5].setLayout(new FlowLayout(FlowLayout.RIGHT));

		/*
		 * Platziere alle Komponenten hintereinander.
		 * Bestaetigungsknopf wird am untersten Rand platziert
		 */
		for (int i = 0; i < panels.length; i++) {
			if (i != 5)
				this.add(panels[i], BorderLayout.CENTER);
			else
				this.add(panels[i], BorderLayout.SOUTH);
		}
	}

	public static ArrayList<Karte> parseLernkartei(File input, boolean grossKleinschreibung)
			throws IOException, IllegalArgumentException {
		ArrayList<Karte> ret = new ArrayList<>();

		BufferedReader reader = new BufferedReader(new FileReader(input));
		String         readLine;
		// Gelesener String aufgeteilt auf die einzelbenoetigten Argumente
		String[] readLineSplit;
		while ((readLine = reader.readLine()) != null) {
			readLineSplit = readLine.split(";");
			// Kontrolle, ob richtige Formatierung vorhanden ist
			if (readLineSplit.length < 2 || readLineSplit.length > 3) {
				reader.close();
				throw new IllegalArgumentException("Datei muss eine der folgenden Formatierungen haben:\n"
						+ "Wort1;Wort2\n" + "oder\n" + "Wort1;Wort2;Fachnummer");
			}
			// Karte erstellen und hinzufuegen
			int num = readLineSplit.length == 2 ? 0 : Integer.parseInt(readLineSplit[2].trim());
			ret.add(new Karte(num, readLineSplit[0], readLineSplit[1], false, grossKleinschreibung));
		}
		System.out.println(ret.toString());
		reader.close();
		return ret;
	}

	private void saveLernkartei(ArrayList<Karte> karten,
			String beschreibung,
			String ersteSprache,
			String zweiteSprache) {
		int highestFach = -1;
		// Finde nummer des Letzten Faches
		for (Karte karte : karten) {
			if (highestFach < karte.getNummer())
				highestFach = karte.getNummer();
		}
	}
}
