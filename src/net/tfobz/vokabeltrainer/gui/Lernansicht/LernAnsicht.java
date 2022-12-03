package net.tfobz.vokabeltrainer.gui.Lernansicht;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.DefaultFocusTraversalPolicy;
import java.awt.Dimension;
import java.awt.FocusTraversalPolicy;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
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
		JLabel wortEinsLabel = new JLabel(getWortEinsBeschreibungAccordingRichtung(kartei));
		wortEinsLabel.setHorizontalAlignment(JLabel.CENTER);
		c.gridx = 2;
		c.gridwidth = 2;
		this.add(wortEinsLabel, c);

		wortEins = new JTextField(kartei.getRichtung() ? aktuelleKarte.getWortEins() : aktuelleKarte.getWortZwei());
		wortEins.setEditable(false);
		wortEins.setPreferredSize(textFieldSize);
		wortEins.setMinimumSize(textFieldSize);
//		wortEins.setFocusable(false);
		c.gridx = 4;
		c.gridwidth = 4;
		this.add(wortEins, c);

		// Wort Zwei
		JLabel wortZweiLabel = new JLabel(getWortZweiBeschreibungAccordingRichtung(kartei));
		wortZweiLabel.setHorizontalAlignment(JLabel.CENTER);
		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = 2;
		this.add(wortZweiLabel, c);

		wortZwei = new JTextField();
		wortZwei.setPreferredSize(textFieldSize);
		wortZwei.setMinimumSize(textFieldSize);
		wortZwei.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				wortZwei.setBorder(null);
			}
		});
		c.gridx = 4;
		c.gridwidth = 4;
		this.add(wortZwei, c);

		//Settings
		JPanel settings = new JPanel();
		JCheckBox grossKleinschreibung = new JCheckBox("Groß/Kleinschreibung beachten", kartei.getGrossKleinschreibung());
		grossKleinschreibung.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				kartei.setGrossKleinschreibung(grossKleinschreibung.isSelected());
				VokabeltrainerDB.aendernLernkartei(kartei);
				aktuelleKarte = VokabeltrainerDB.getKarte(aktuelleKarte.getNummer());
				wortEins.setText(kartei.getRichtung() ? aktuelleKarte.getWortEins() : aktuelleKarte.getWortZwei());
			}
		});
		settings.add(grossKleinschreibung);
		JToggleButton richtungButton = new JToggleButton(getWortEinsBeschreibungAccordingRichtung(kartei) + "⇾" + getWortZweiBeschreibungAccordingRichtung(kartei));
		richtungButton.setSelected(kartei.getRichtung());
		richtungButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				kartei.setRichtung(richtungButton.isSelected());
				VokabeltrainerDB.aendernLernkartei(kartei);
				richtungButton.setText(getWortEinsBeschreibungAccordingRichtung(kartei) + "⇾" + getWortZweiBeschreibungAccordingRichtung(kartei));
				
				//Aktualisiere Karte
				aktuelleKarte = VokabeltrainerDB.getKarte(aktuelleKarte.getNummer());
				
				wortEinsLabel.setText(getWortEinsBeschreibungAccordingRichtung(kartei));
				wortZweiLabel.setText(getWortZweiBeschreibungAccordingRichtung(kartei));
				wortEins.setText(getWortEinsAccordingRichtung(kartei));
			}
		});
		settings.add(richtungButton);
		
		
		
		
		c.gridx = 2;
		c.gridy = 2;
		this.add(settings, c);
		
		// Weiter & Auflösen
		c.gridy = 3;
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
								"Wort wäre " + getWortZweiAccordingRichtung(kartei) + " gewesen.", 
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
				//Setze Fokus auf Textfeldeingabe
				weiter.setEnabled(false);
				wortZwei.requestFocusInWindow();
				weiter.setEnabled(true);
			}
		});
		
		wortZwei.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					weiter.doClick();
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {}
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
						"Wort wäre " + getWortZweiAccordingRichtung(kartei) + " gewesen.", 
						"Auflösung", 
						JOptionPane.INFORMATION_MESSAGE, 
						new ImageIcon("src/net/tfobz/vokabeltrainer/gui/assets/info.png"));
				aktuelleKarte = VokabeltrainerDB.getZufaelligeKarte(kartei.getNummer(), fach.getNummer());
				if(aktuelleKarte == null)
					keineKarteImFach(kartei);
					
				wortEins.setText(aktuelleKarte.getWortEins());
				wortZwei.setText("");
				wortZwei.setBorder(null);
				//Setze Fokus auf Textfeldeingabe
				aufloesen.setEnabled(false);
				wortZwei.requestFocusInWindow();
				aufloesen.setEnabled(true);
			}
		});
		
		c.gridx = 4;
		c.gridwidth = 2;
		this.add(aufloesen, c);
		
		
		//Tabelle
		richtigeKarten = new DefaultTableModel(new String[]{kartei.getWortEinsBeschreibung(),kartei.getWortZweiBeschreibung()}, 0);
		falscheKarten = new DefaultTableModel(new String[]{kartei.getWortEinsBeschreibung(),kartei.getWortZweiBeschreibung()}, 0);
		JTable jTableRichtig = new JTable(richtigeKarten);
		jTableRichtig.setBackground(new Color(0, 150, 0));
		jTableRichtig.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		jTableRichtig.setPreferredSize(new Dimension(400, 1000));
		JTable jTableFalsch = new JTable(falscheKarten);
		jTableFalsch.setBackground(new Color(150, 0, 0));
		jTableFalsch.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		jTableFalsch.setPreferredSize(new Dimension(400, 1000));
		
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
		
		jTableFalsch.setForeground(Color.black);
		jTableRichtig.setForeground(Color.black);
		tables.setEnabled(false);
		
		JScrollPane scrollpane = new JScrollPane(tables);
		scrollpane.setBorder(BorderFactory.createLoweredBevelBorder());
		scrollpane.setPreferredSize(new Dimension(1000, 400));
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 10;
		c.gridheight = 10;
		c.insets = new Insets(0, 20, 0, 20);
		c.fill = GridBagConstraints.HORIZONTAL;
		this.add(scrollpane, c);
		
		// Deklariere Tab-Order
		Vector<Component> order = new Vector<Component>(3);
		order.add(wortZwei);
		order.add(weiter);
		order.add(aufloesen);
		FocusTraversalPolicy focusTraversalPolicy = new FocusTraversalPolicy() {
			
			@Override
			public Component getLastComponent(Container aContainer) {
				return order.lastElement();
			}
			
			@Override
			public Component getFirstComponent(Container aContainer) {
				return order.firstElement();
			}
			
			@Override
			public Component getDefaultComponent(Container aContainer) {
				return order.firstElement();
			}
			
			@Override
			public Component getComponentBefore(Container aContainer, Component aComponent) {
				if(aComponent.equals(order.firstElement()))
					return order.lastElement();
				else
					return order.get(order.indexOf(aComponent)-1);
			}
			
			@Override
			public Component getComponentAfter(Container aContainer, Component aComponent) {
				if (aComponent.equals(order.lastElement()))
					return order.firstElement();
				else
					return order.get(order.indexOf(aComponent)+1);
			}
		};
		
		setFocusTraversalPolicy(focusTraversalPolicy);
		setFocusCycleRoot(true);
	}
	
	protected void keineKarteImFach(Lernkartei kartei) {
		JOptionPane.showMessageDialog(this, "Sie haben alle Karten in diesem Fach behandelt.");
		StartVokabeltrainer startVokabeltrainer = (StartVokabeltrainer) StartVokabeltrainer.getStartVokabelTrainer(this);
		startVokabeltrainer.changeToViewLernkarteien();
		FachAuswahl fachAuswahl = new FachAuswahl(kartei, null, startVokabeltrainer);
		fachAuswahl.setVisible(true);
	}
	
	protected String getWortEinsBeschreibungAccordingRichtung(Lernkartei kartei) {
		return kartei.getRichtung() ? kartei.getWortEinsBeschreibung() : kartei.getWortZweiBeschreibung();
	}
	
	protected String getWortZweiBeschreibungAccordingRichtung(Lernkartei kartei) {
		return kartei.getRichtung() ? kartei.getWortZweiBeschreibung() : kartei.getWortEinsBeschreibung();
	}
	
	protected String getWortEinsAccordingRichtung(Lernkartei kartei) {
		return kartei.getRichtung() ? aktuelleKarte.getWortEins() : aktuelleKarte.getWortZwei();
	}
	
	protected String getWortZweiAccordingRichtung(Lernkartei kartei) {
		return kartei.getRichtung() ? aktuelleKarte.getWortZwei() : aktuelleKarte.getWortEins();
	}
	
}
