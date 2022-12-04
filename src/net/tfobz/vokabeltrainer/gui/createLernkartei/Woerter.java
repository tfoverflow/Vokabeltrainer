package net.tfobz.vokabeltrainer.gui.createLernkartei;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.tfobz.vokabeltrainer.gui.StartVokabeltrainer;
import net.tfobz.vokabeltrainer.model.Fach;
import net.tfobz.vokabeltrainer.model.Karte;
import net.tfobz.vokabeltrainer.model.Lernkartei;
import net.tfobz.vokabeltrainer.model.VokabeltrainerDB;

public class Woerter extends JDialog{

	private static final long serialVersionUID = -404240314838103247L;

	public Woerter(Lernkartei kartei, Container parent) {
		Box box= new Box(BoxLayout.Y_AXIS);
		
		this.setModal(true);
		this.setResizable(false);
		this.setMinimumSize(new Dimension(500, 350));
		this.setLocationRelativeTo(null); //Öffne in der Bildschirmmitte :3
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setTitle("Vokabeltrainer - Worteingabe");
		
		
		//Titel
		JLabel titel = new JLabel();
		JPanel titelPanel = new JPanel();
		titel.setText("Bitte definieren Sie die Wörter für diese Lernkartei!");
		titel.setFont(new Font(titel.getFont().toString(), Font.PLAIN, 20));
		titelPanel.add(titel);
		titelPanel.setPreferredSize(new Dimension(500,40));
		titelPanel.setMinimumSize(new Dimension(500,40));
		titelPanel.setMaximumSize(new Dimension(500,40));
//		titelPanel.setBorder(BorderFactory.createLineBorder(Color.cyan));
		
		
		//Wort 1
		JLabel wort1 = new JLabel();
		JPanel wort1Panel = new JPanel();
		wort1Panel.setLayout(new BoxLayout(wort1Panel,BoxLayout.X_AXIS));
		wort1.setText("Wort1(" + kartei.getWortEinsBeschreibung() + ")");
		wort1.setFont(new Font(wort1.getFont().toString(), Font.PLAIN, 15));
		wort1Panel.add(wort1);
		wort1Panel.setPreferredSize(new Dimension(500,30));
		wort1Panel.setMinimumSize(new Dimension(500,30));
		wort1Panel.setMaximumSize(new Dimension(500,30));
//		wort1Panel.setBorder(BorderFactory.createLineBorder(Color.cyan));
		
		JTextField wort1Text = new JTextField();
		JPanel wort1TextPanel = new JPanel();
		wort1Text.setPreferredSize(new Dimension(480,30));
		wort1Text.setMinimumSize(new Dimension(480,30));
		wort1Text.setMaximumSize(new Dimension(480,30));
		wort1Text.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				((JTextField) e.getSource()).setBorder(null);
			}
			
			@Override
			public void keyReleased(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {}
		});
		wort1TextPanel.setPreferredSize(new Dimension(500,35));
		wort1TextPanel.setMinimumSize(new Dimension(500,35));
		wort1TextPanel.setMaximumSize(new Dimension(500,35));
		wort1TextPanel.add(wort1Text);
		
		//Wort 2
		JLabel wort2 = new JLabel();
		JPanel wort2Panel = new JPanel();
		wort2Panel.setLayout(new BoxLayout(wort2Panel,BoxLayout.X_AXIS));
		wort2.setText("Wort2(" + kartei.getWortZweiBeschreibung() + ")");
		wort2.setFont(new Font(wort2.getFont().toString(), Font.PLAIN, 15));
		wort2Panel.add(wort2);
		wort2Panel.setPreferredSize(new Dimension(500,30));
		wort2Panel.setMinimumSize(new Dimension(500,30));
		wort2Panel.setMaximumSize(new Dimension(500,30));
		
		JTextField wort2Text = new JTextField();
		JPanel wort2TextPanel = new JPanel();
		wort2Text.setPreferredSize(new Dimension(480,30));
		wort2Text.setMinimumSize(new Dimension(480,30));
		wort2Text.setMaximumSize(new Dimension(480,30));
		wort2Text.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				((JTextField) e.getSource()).setBorder(null);
			}
			
			@Override
			public void keyReleased(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {}
		});
		wort2TextPanel.setPreferredSize(new Dimension(500,35));
		wort2TextPanel.setMinimumSize(new Dimension(500,35));
		wort2TextPanel.setMaximumSize(new Dimension(500,35));
		wort2TextPanel.add(wort2Text);
		
		//hinzufügen + speichern
		JButton hinzufuegen = new JButton();
		JButton speichern = new JButton();
		JPanel buttonPanel = new JPanel();
		
		buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.X_AXIS));
		
		hinzufuegen.setSize(150,40);
		hinzufuegen.setText("Hinzufügen");
		hinzufuegen.setFont(new Font(hinzufuegen.getFont().toString(), Font.PLAIN, 15));
		hinzufuegen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (wort1Text.getText().trim().isEmpty() || wort2Text.getText().trim().isEmpty()) {
					if (wort1Text.getText().trim().isEmpty()) {
						wort1Text.setBorder(BorderFactory.createLineBorder(Color.red));
					}
					if (wort2Text.getText().trim().isEmpty()) {
						wort2Text.setBorder(BorderFactory.createLineBorder(Color.red));
					}
				} else {
					Karte karte = new Karte(-1,wort1Text.getText(),wort2Text.getText(),true,kartei.getGrossKleinschreibung());
					VokabeltrainerDB.hinzufuegenKarte(kartei.getNummer(), karte);
					wort1Text.setText("");
					wort2Text.setText("");
				}
			}
		});
		
		buttonPanel.add(hinzufuegen);
		
		buttonPanel.add(Box.createRigidArea(new Dimension(150,40)));
		
		speichern.setSize(150,40);
		speichern.setText("Speichern");
		speichern.setFont(new Font(hinzufuegen.getFont().toString(), Font.PLAIN, 15));
		speichern.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Karte karte = new Karte(-1,wort1Text.getText(),wort2Text.getText(),true,kartei.getGrossKleinschreibung());
				VokabeltrainerDB.hinzufuegenKarte(kartei.getNummer(), karte);
				wort1Text.setText("");
				wort2Text.setText("");
				((StartVokabeltrainer) StartVokabeltrainer.getStartVokabelTrainer(parent)).changeToViewLernkarteien();
				dispose();
			}
		});
		buttonPanel.add(speichern);
		
		
		//hinzufügend der Komponenten
		box.add(titelPanel);
		box.add(Box.createRigidArea(new Dimension(500,10)));
		box.add(wort1Panel);
		box.add(wort1TextPanel);
		box.add(Box.createRigidArea(new Dimension(500,10)));
		box.add(wort2Panel);
		box.add(wort2TextPanel);
		box.add(Box.createRigidArea(new Dimension(500,40)));
		box.add(buttonPanel);
		
		this.add(box);
	}
}
