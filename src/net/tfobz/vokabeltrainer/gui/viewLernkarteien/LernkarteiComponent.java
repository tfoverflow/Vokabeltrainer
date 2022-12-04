package net.tfobz.vokabeltrainer.gui.viewLernkarteien;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import net.tfobz.vokabeltrainer.gui.StartVokabeltrainer;
import net.tfobz.vokabeltrainer.model.Fach;
import net.tfobz.vokabeltrainer.model.Lernkartei;
import net.tfobz.vokabeltrainer.model.VokabeltrainerDB;

public class LernkarteiComponent extends JComponent {
	private static final long serialVersionUID = -4572167597910292369L;

	private JLabel percent = null;
	private JLabel name = null;

	public JButton playButton = null;
	
	// Datum an dem die erste Erinnerung eines Faches eintritt
	private Calendar earliestDate;

	public LernkarteiComponent(Lernkartei kartei) {

		this.setBackground(new Color(0, 255, 0));
		this.setMinimumSize(new Dimension(300, 200));
		/* TODO Bissl Performance-Heavy.
		kartei.aktualisiereIsErinnerungfaellig();
		if(kartei.isErinnerungfaellig())
			this.setBorder(BorderFactory.createLineBorder(Color.red));
		else*/
			this.setBorder(BorderFactory.createLineBorder(Color.gray, 2));
		 
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		percent = new JLabel(getPercent(kartei), SwingConstants.CENTER);
		percent.setVerticalAlignment(SwingConstants.CENTER);
		percent.setPreferredSize(new Dimension(300, 100));
		percent.setBorder(new MatteBorder(0, 0, 2, 0, Color.gray));
		percent.setFont(new Font(percent.getFont().getFontName(), Font.PLAIN, 25));

		name = new JLabel(kartei.getBeschreibung());
		name.setPreferredSize(new Dimension(250, 50));
		name.setBorder(new MatteBorder(0, 0, 0, 2, Color.gray));
		name.setHorizontalAlignment(JLabel.CENTER);

		JButton exportButton = new JButton(new ImageIcon("src/net/tfobz/vokabeltrainer/gui/assets/download.png"));
		exportButton.setPreferredSize(new Dimension(50, 50));
		exportButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				export(kartei);
			}
		});
		
		playButton = new JButton();
		playButton.setPreferredSize(new Dimension(40, 40));
		ImageIcon play_arrow = new ImageIcon("src/net/tfobz/vokabeltrainer/gui/assets/play_arrow.png");
		playButton.setIcon(play_arrow);
		
		// I'm not sorry
		playButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				reloadFachAuswahl(kartei, ((StartVokabeltrainer) StartVokabeltrainer.getStartVokabelTrainer((JButton) e.getSource())));
			}
		});


		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 10;
		c.gridheight = 7;
		this.add(percent, c);
		
		c.gridx = 7;
		c.gridy = 3;
		c.gridwidth = 3;
		c.gridheight = 3;
		this.add(exportButton, c);

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

	public void reloadFachAuswahl(Lernkartei kartei, StartVokabeltrainer startVokabeltrainer) {
		
		FachAuswahl fachAuswahl = new FachAuswahl(kartei, this, startVokabeltrainer);
		fachAuswahl.setVisible(true);
	}

	/**
	 * Errechnet earliestDate (Datum an dem die erste Erinnerung eines Faches eintritt)
	 * @param kartei
	 * @return
	 */
	private String getPercent(Lernkartei kartei) {
		ArrayList<Calendar> dates = new ArrayList<Calendar>();
		ArrayList<Fach> faecher = (ArrayList<Fach>) VokabeltrainerDB.getFaecher(kartei.getNummer());
		faecher.forEach((fach)->{
			if (fach.getGelerntAm() != null && fach.getErinnerungsIntervall() != 0) {
				Calendar erinnerungAm = Calendar.getInstance();
				erinnerungAm.setTime(fach.getGelerntAm());
				erinnerungAm.add(Calendar.DAY_OF_MONTH, fach.getErinnerungsIntervall());
				
				dates.add(erinnerungAm);
			}
		});
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		if(dates.isEmpty())
			return "<html><center style=\"font-size: 18;\">keine<br>Erinnerungen</center></html>";
		else
			return "<html><center style=\"font-size: 18;\">Nächste Erinnerung:<br>" + sdf.format(Collections.min(dates).getTime()) + "</html></center>";
	}
	
	private void export(Lernkartei kartei) {
		File path;
		Boolean mitFaechern;
		
		JFileChooser fileChooser = new JFileChooser();
		if(JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(this))
			path = fileChooser.getSelectedFile();
		else
			return;
		
		switch (JOptionPane.showConfirmDialog(this, "Soll der Fortschritt gespeichert bleiben?")) {
			case JOptionPane.YES_OPTION: {
				mitFaechern = true;
			break;
		}
			case JOptionPane.NO_OPTION: {
				mitFaechern = false;
			break;
		}
		default:
			return;
		}
		
		switch (VokabeltrainerDB.exportierenKarten(kartei.getNummer(), path.getAbsolutePath(), mitFaechern)) {
		case -1: {
			JOptionPane.showMessageDialog(this, "Es ist ein Datenbankfehler oder Schreibfehler in Datei aufgetreten", "Fehler", JOptionPane.ERROR_MESSAGE);
			break;
		}
		case -3: {
			//Hier sollte NIEMAND NIEMALS JEMALS IRGENDWIE HINKOMMEN
			JOptionPane.showMessageDialog(this, "Lernkartei existiert nicht", "Fehler", JOptionPane.ERROR_MESSAGE);
			break;
		}
		default:
			if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, "Lernkartei erfolgreich gespeichert! Soll die Datei im Explorer angezeigt werden?","Erfolg", JOptionPane.YES_NO_OPTION))
				try {
					Desktop.getDesktop().open(fileChooser.getCurrentDirectory());
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
}
