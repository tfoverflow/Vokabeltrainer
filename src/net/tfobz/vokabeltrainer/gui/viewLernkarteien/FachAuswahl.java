package net.tfobz.vokabeltrainer.gui.viewLernkarteien;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JPanel;

import net.tfobz.vokabeltrainer.gui.StartVokabeltrainer;
import net.tfobz.vokabeltrainer.model.Fach;
import net.tfobz.vokabeltrainer.model.Lernkartei;
import net.tfobz.vokabeltrainer.model.VokabeltrainerDB;

public class FachAuswahl extends JDialog {

	private static final long serialVersionUID = 3567286090716742665L;
	
	public FachAuswahl(Lernkartei kartei, StartVokabeltrainer startVokabeltrainer) {
		this.setModal(true);
		this.setTitle("Vokabeltrainer - Fachauswahl");
		this.setMinimumSize(new Dimension(500, 500));
		this.setLocationRelativeTo(null); //Öffne in der Bildschirmmitte :3
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		Box box= new Box(BoxLayout.Y_AXIS);
		
//		ArrayList<JPanel> panels = new ArrayList<JPanel>();
		ArrayList<Fach> faecher = (ArrayList<Fach>) VokabeltrainerDB.getFaecher(kartei.getNummer());
		
		//Für jedes Fach ein JPanel hinzufuegen
//		for (int i = 0; i < faecher.size(); i++)
//		panels.add(new JPanel());
		box.add(new JLabel("Test"));
		faecher.forEach((fach)->{
			JPanel newPanel = new JPanel();
			newPanel.setLayout(new GridLayout(1, 4));
			newPanel.setBorder(BorderFactory.createLineBorder(Color.black));
			newPanel.setMaximumSize(new Dimension(400, 50));
			
			JLabel beschreibung = new JLabel(fach.getBeschreibung());
			beschreibung.setHorizontalAlignment(JLabel.CENTER);
			newPanel.add(beschreibung);
			
			JLabel gelerntAm = new JLabel(fach.getGelerntAm() != null ? fach.getGelerntAmEuropaeischString() : "noch nie");
			gelerntAm.setHorizontalAlignment(JLabel.CENTER);
			newPanel.add(gelerntAm);
			
			JLabel erinnerungFaellig = new JLabel();
			if(fach.getGelerntAm() != null) {
				LocalDate erinnerung = LocalDate.of(fach.getGelerntAm().getYear(), fach.getGelerntAm().getMonth(), fach.getGelerntAm().getDay());
				erinnerungFaellig.setText(erinnerung.plusDays(fach.getErinnerungsIntervall()).toString());
			} else {
				erinnerungFaellig.setText("jetzt");
			}
			
			erinnerungFaellig.setHorizontalAlignment(JLabel.CENTER);
			newPanel.add(erinnerungFaellig);
			
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
