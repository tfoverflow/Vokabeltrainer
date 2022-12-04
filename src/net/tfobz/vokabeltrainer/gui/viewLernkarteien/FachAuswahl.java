package net.tfobz.vokabeltrainer.gui.viewLernkarteien;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import datechooser.beans.DateChooserDialog;
import datechooser.events.CommitEvent;
import datechooser.events.CommitListener;
import net.tfobz.vokabeltrainer.gui.StartVokabeltrainer;
import net.tfobz.vokabeltrainer.model.Fach;
import net.tfobz.vokabeltrainer.model.Lernkartei;
import net.tfobz.vokabeltrainer.model.VokabeltrainerDB;

public class FachAuswahl extends JDialog {

	private static final long serialVersionUID = 3567286090716742665L;
	
	private int indexHier = 1;
	
	public FachAuswahl(Lernkartei kartei, LernkarteiComponent parent, StartVokabeltrainer startVokabeltrainer) {
		//Benutze box, da BoxLayout nicht direkt funktiont. Box ersetzt this
		Box box= new Box(BoxLayout.Y_AXIS);
		
		//Modal = es muss zuerst diese Fenster geschlossen werden, damit Hauptfenster verwendet werden kann
		this.setModal(true);
		this.setResizable(false);
		this.setTitle("Vokabeltrainer - Fachauswahl");
		this.setMinimumSize(new Dimension(500, 500));
		this.setLocationRelativeTo(null); //Öffne in der Bildschirmmitte :3
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		

		ArrayList<Fach> faecher = (ArrayList<Fach>) VokabeltrainerDB.getFaecher(kartei.getNummer());
		
		JButton zuruecksetzen = new JButton("Zurücksetzen!");
		zuruecksetzen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Wollen Sie wirklich alle Karten in das erste Fach verschieben?", "Zurücksetzen?", JOptionPane.YES_NO_OPTION)) {
					VokabeltrainerDB.getFaecher(kartei.getNummer()).forEach((fach)->{
						VokabeltrainerDB.getKarten(fach.getNummer()).forEach((karte)->{
							VokabeltrainerDB.setKarteFalsch(karte);
						});
					});
					JOptionPane.showMessageDialog(null, "Alle Karten wurden ins erste Fach verschoben", "Erfolg!", JOptionPane.INFORMATION_MESSAGE);
					dispose();
					parent.reloadFachAuswahl(kartei, startVokabeltrainer);
				}
			}
		});
		
		
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new GridLayout(1, 1));
		titlePanel.setBorder(BorderFactory.createEtchedBorder());
		titlePanel.setMaximumSize(new Dimension(400, 70));
		titlePanel.add(new JLabel("Gelernt am", JLabel.CENTER));
		titlePanel.add(new JLabel("Erinnerung am", JLabel.CENTER));
		titlePanel.add(new JLabel("", JLabel.CENTER));
		titlePanel.add(zuruecksetzen);
		titlePanel.setMaximumSize(new Dimension(450, 50));
		box.add(Box.createVerticalStrut(10));
		box.add(titlePanel);
		box.add(Box.createVerticalStrut(25));
		
		
		// Erstelle für jedes Fach ein neues JPanel und füge es am Ende zu box hinzu.
		faecher.forEach((fach)->{
			JPanel newPanel = new JPanel();
			newPanel.setLayout(new GridLayout(1, 0));
			
			//Wenn Fach Faellig ist, wird der Rand rot gefaebt;
			Boolean isFaellig = fach.getGelerntAm() != null ? fach.getErinnerungFaellig(): false;
			newPanel.setBorder(BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder((isFaellig ? Color.red : null), null),
	                fach.getBeschreibung() != null ? fach.getBeschreibung() : "Fach "+ indexHier++, //Wenn kein Titel angegeben wird, hat da
	                TitledBorder.CENTER,
	                TitledBorder.TOP));
			newPanel.setPreferredSize(new Dimension(475, 75));
			newPanel.setMaximumSize(new Dimension(475, 75));
			
			//Gelernt am
			JLabel gelerntAm = new JLabel(fach.getGelerntAm() != null ? fach.getGelerntAmEuropaeischString() : "noch nie");
			gelerntAm.setHorizontalAlignment(JLabel.CENTER);
			newPanel.add(gelerntAm);
			
			/**
			 * Button für Erinnerung am
			 * Text ist entweder:
			 * - Datum, wenn Erinnerung föllig wird
			 * - "keine Erinnerung", wenn kein ErinnerungsIntervall gesetzt wurde
			 * - "Jetzt", falls ErinnerungsIntervall existiert
			 */
			JButton erinnerungFaellig = new JButton();
			if(fach.getGelerntAm() != null && fach.getErinnerungsIntervall() != 0) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
				Calendar c = Calendar.getInstance();
				c.setTime(fach.getGelerntAm());
				c.add(Calendar.DAY_OF_MONTH, fach.getErinnerungsIntervall());
				erinnerungFaellig.setText(sdf.format(c.getTime()));
			} else {
				if(fach.getErinnerungsIntervall() == 0)
					erinnerungFaellig.setText("<html><center>keine<br>Erinnerung</center></html>");
				else
					erinnerungFaellig.setText("<html><center>Noch nie<br>gespielt</center></html>");
			}
			
			// Button/DatePicker um ein neues Erinnerungsdatum zu setzen
			Calendar calGelerntAm = Calendar.getInstance();
			if(fach.getGelerntAm() != null)
				calGelerntAm.setTime(fach.getGelerntAm());
			
			DateChooserDialog dateChooser = new DateChooserDialog();
			dateChooser.setCurrent(calGelerntAm);
			dateChooser.setMinDate(calGelerntAm);
			dateChooser.addCommitListener(new CommitListener() {
				@Override
				public void onCommit(CommitEvent arg0) {
					//Setze gelerntAm zurueck
					if(fach.getGelerntAm() != null)
						calGelerntAm.setTime(fach.getGelerntAm());
					
					// Berechne Unterschied zwischen gelerntAm und ausgewählten Datum
					int erinnerungsintervall = (int) ChronoUnit.DAYS.between(calGelerntAm.toInstant(), dateChooser.getSelectedDate().toInstant());
					//Setze Intervall
					fach.setErinnerungsIntervall(erinnerungsintervall);
					VokabeltrainerDB.aendernFach(fach);
					
					//Ersetze Datum im Button
					SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
					calGelerntAm.add(Calendar.DAY_OF_MONTH, erinnerungsintervall);
					erinnerungFaellig.setText(sdf.format(calGelerntAm.getTime()));
				}
			});
			erinnerungFaellig.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dateChooser.showDialog(startVokabeltrainer, true);

				}
			});
			erinnerungFaellig.setHorizontalAlignment(JLabel.CENTER);
			newPanel.add(erinnerungFaellig);
			
			
			
			// nKarten - Wie viele Karten derzeit im Fach sind
			//TODO Very performance-inefficient! Needs Improvements
			JLabel nKarten = new JLabel(VokabeltrainerDB.getKarten(fach.getNummer()).size() + " Karten");
			nKarten.setHorizontalAlignment(JLabel.CENTER);
			newPanel.add(nKarten);
			
			JButton playButton = new JButton(new ImageIcon("src/net/tfobz/vokabeltrainer/gui/assets/play_arrow.png"));
			playButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					startVokabeltrainer.changeToLearnAnsicht(kartei, fach);
				}
			});
			newPanel.add(playButton);
			
			box.add(newPanel);
			box.add(Box.createVerticalStrut(20));
		});
		this.add(new JScrollPane(box));
	}

}
