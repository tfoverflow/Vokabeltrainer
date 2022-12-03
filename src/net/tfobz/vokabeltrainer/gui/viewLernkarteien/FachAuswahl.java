package net.tfobz.vokabeltrainer.gui.viewLernkarteien;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javax.swing.JToggleButton;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.tfobz.vokabeltrainer.gui.StartVokabeltrainer;
import net.tfobz.vokabeltrainer.model.Fach;
import net.tfobz.vokabeltrainer.model.Lernkartei;
import net.tfobz.vokabeltrainer.model.VokabeltrainerDB;

public class FachAuswahl extends JDialog {

	private static final long serialVersionUID = 3567286090716742665L;
	
	public FachAuswahl(Lernkartei kartei, StartVokabeltrainer startVokabeltrainer) {
		Box box= new Box(BoxLayout.Y_AXIS);
		
		
		this.setModal(true);
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
				}
			}
		});
		
		
//		box.add(zuruecksetzen);
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new GridLayout(1, 1));
		titlePanel.setBorder(BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                "",
                TitledBorder.CENTER,
                TitledBorder.TOP));
		titlePanel.setMaximumSize(new Dimension(400, 70));
		titlePanel.add(new JLabel("Gelernt am", JLabel.CENTER));
		titlePanel.add(new JLabel("Erinnerung am", JLabel.CENTER));
		titlePanel.add(new JLabel("", JLabel.CENTER));
		titlePanel.add(zuruecksetzen);
		titlePanel.setMaximumSize(new Dimension(450, 50));
		box.add(titlePanel);
		
		box.add(Box.createVerticalStrut(25));
		
		faecher.forEach((fach)->{
			JPanel newPanel = new JPanel();
			newPanel.setLayout(new GridLayout(1, 1));
			
			//Wenn Fach Faellig ist, wird der Rand rot gefaebt;
			Boolean isFaellig = fach.getGelerntAm() != null ? fach.getErinnerungFaellig(): false;
			newPanel.setBorder(BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder((isFaellig ? Color.red : null), null),
	                fach.getBeschreibung(),
	                TitledBorder.CENTER,
	                TitledBorder.TOP));
			newPanel.setMaximumSize(new Dimension(450, 70));
			
//			JLabel beschreibung = new JLabel(fach.getBeschreibung());
//			beschreibung.setHorizontalAlignment(JLabel.CENTER);
//			newPanel.add(beschreibung);
			
			JLabel gelerntAm = new JLabel(fach.getGelerntAm() != null ? fach.getGelerntAmEuropaeischString() : "noch nie");
			gelerntAm.setHorizontalAlignment(JLabel.CENTER);
			newPanel.add(gelerntAm);
			
			JLabel erinnerungFaellig = new JLabel();
			if(fach.getGelerntAm() != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
				Calendar c = Calendar.getInstance();
				c.setTime(fach.getGelerntAm());
				c.add(Calendar.DAY_OF_MONTH, fach.getErinnerungsIntervall());
				erinnerungFaellig.setText(sdf.format(c.getTime()));
			} else {
				erinnerungFaellig.setText("jetzt");
			}
			
			erinnerungFaellig.setHorizontalAlignment(JLabel.CENTER);
			newPanel.add(erinnerungFaellig);
			
			//TODO Very performance-inefficient! Needs Improvements
			JLabel nKarten = new JLabel(VokabeltrainerDB.getKarten(fach.getNummer()).size() + " Karten");
			nKarten.setHorizontalAlignment(JLabel.CENTER);
			newPanel.add(nKarten);
			
			JButton playButton = new JButton(new ImageIcon("src/net/tfobz/vokabeltrainer/gui/assets/play_arrow.png"));
			playButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					startVokabeltrainer.changeToLearnAnsicht(kartei, fach);
					setVisible(false);
				}
			});
			newPanel.add(playButton);
			
			box.add(newPanel);
			box.add(Box.createVerticalStrut(20));
		});
		this.add(box);
//		this.setVisible(true);
	}

}
