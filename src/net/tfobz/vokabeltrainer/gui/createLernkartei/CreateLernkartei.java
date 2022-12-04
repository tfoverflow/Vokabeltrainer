package net.tfobz.vokabeltrainer.gui.createLernkartei;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.tfobz.vokabeltrainer.gui.StartVokabeltrainer;
import net.tfobz.vokabeltrainer.model.Fach;
import net.tfobz.vokabeltrainer.model.Lernkartei;
import net.tfobz.vokabeltrainer.model.VokabeltrainerDB;

public class CreateLernkartei extends JPanel {
	private static final long serialVersionUID = 6294964067139665479L;
	JLabel sliderZahl = new JLabel();
	private JCheckBox grossKleinschreibung = null;
	ImageIcon checkbox = new ImageIcon("src/net/tfobz/vokabeltrainer/gui/assets/checkbox.png");
	ImageIcon checkbox_checked = new ImageIcon("src/net/tfobz/vokabeltrainer/gui/assets/checkbox_checked.png");
	Fach fach[] = new Fach[5];

	public CreateLernkartei() {

		//Layout
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//beschreibung
		JTextField beschreibung = new JTextField();
		JPanel beschreibungPanel = new JPanel();
		beschreibungPanel.setLayout((new BoxLayout(beschreibungPanel, BoxLayout.Y_AXIS)));
//		beschreibungPanel.setBorder(BorderFactory.createLineBorder(Color.cyan));
		beschreibungPanel.setPreferredSize(new Dimension(1000,50));
		beschreibungPanel.setMinimumSize(new Dimension(1000,50));
		beschreibungPanel.setMaximumSize(new Dimension(1000,50));
		beschreibungPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		beschreibung.setFont(new Font(beschreibung.getFont().toString(), Font.PLAIN, 20));
		beschreibung.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				((JTextField) e.getSource()).setBorder(null);
			}
			
			@Override
			public void keyReleased(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {}
		});
		beschreibungPanel.add(beschreibung);

		//name
		JLabel name = new JLabel("Name");
		JPanel namePanel = new JPanel();
		namePanel.setLayout((new BoxLayout(namePanel, BoxLayout.Y_AXIS)));
		name.setFont(new Font(name.getFont().toString(), Font.PLAIN, 30));
		namePanel.setPreferredSize(new Dimension(1000,50));
		namePanel.setMinimumSize(new Dimension(1000,50));
		namePanel.setMaximumSize(new Dimension(1000,50));
//		namePanel.setBorder(BorderFactory.createLineBorder(Color.cyan));
		namePanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		namePanel.add(name);
		
		//Sprache1
		JLabel sprache1 = new JLabel("Sprache 1");
		JPanel sprache1Panel = new JPanel();
		sprache1Panel.setLayout((new BoxLayout(sprache1Panel, BoxLayout.X_AXIS)));
		sprache1.setFont(new Font(sprache1.getFont().toString(), Font.PLAIN, 30));
		sprache1Panel.setPreferredSize(new Dimension(1000,50));
		sprache1Panel.setMinimumSize(new Dimension(1000,50));
		sprache1Panel.setMaximumSize(new Dimension(1000,50));
		sprache1Panel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		sprache1Panel.add(sprache1);
		
		JTextField sprache1Text = new JTextField();
		JPanel sprache1TextPanel = new JPanel();
		sprache1TextPanel.setLayout((new BoxLayout(sprache1TextPanel, BoxLayout.Y_AXIS)));
		sprache1TextPanel.setPreferredSize(new Dimension(1000,50));
		sprache1TextPanel.setMinimumSize(new Dimension(1000,50));
		sprache1TextPanel.setMaximumSize(new Dimension(1000,50));
		sprache1TextPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		sprache1Text.setFont(new Font(sprache1Text.getFont().toString(), Font.PLAIN, 20));
		sprache1Text.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				((JTextField) e.getSource()).setBorder(null);
			}
			
			@Override
			public void keyReleased(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {}
		});
		sprache1TextPanel.add(sprache1Text);
		
		//Sprache2
		JLabel sprache2 = new JLabel("Sprache 2");
		JPanel sprache2Panel = new JPanel();
		sprache2Panel.setLayout((new BoxLayout(sprache2Panel, BoxLayout.Y_AXIS)));
		sprache2.setFont(new Font(sprache2.getFont().toString(), Font.PLAIN, 30));
		sprache2Panel.setPreferredSize(new Dimension(1000,50));
		sprache2Panel.setMinimumSize(new Dimension(1000,50));
		sprache2Panel.setMaximumSize(new Dimension(1000,50));
		sprache2Panel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		sprache2Panel.add(sprache2);
		
		JTextField sprache2Text = new JTextField();
		JPanel sprache2TextPanel = new JPanel();
		sprache2TextPanel.setLayout((new BoxLayout(sprache2TextPanel, BoxLayout.Y_AXIS)));
		sprache2TextPanel.setPreferredSize(new Dimension(1000,50));
		sprache2TextPanel.setMinimumSize(new Dimension(1000,50));
		sprache2TextPanel.setMaximumSize(new Dimension(1000,50));
		sprache2TextPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		sprache2Text.setFont(new Font(sprache2Text.getFont().toString(), Font.PLAIN, 20));
		sprache2Text.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				((JTextField) e.getSource()).setBorder(null);
			}
			
			@Override
			public void keyReleased(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {}
		});
		sprache2TextPanel.add(sprache2Text);
		
		//Slider
		JSlider slider = new JSlider(2,5);
		slider.setValue(2);
		JPanel sliderPanel = new JPanel();
		sliderPanel.setLayout((new BoxLayout(sliderPanel, BoxLayout.X_AXIS)));
		sliderPanel.setPreferredSize(new Dimension(1000,50));
		sliderPanel.setMinimumSize(new Dimension(1000,50));
		sliderPanel.setMaximumSize(new Dimension(1000,50));
		sliderZahl.setText(Integer.toString(slider.getValue()));
		sliderZahl.setFont(new Font(sliderZahl.getFont().toString(), Font.PLAIN, 20));
		slider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				sliderZahl.setText(Integer.toString(slider.getValue()));
			}
			
		});
		sliderPanel.add(sliderZahl);
		sliderPanel.add(slider);
		
		//Großklein
		JPanel gkPanel = new JPanel();
		gkPanel.setLayout(new BoxLayout(gkPanel, BoxLayout.X_AXIS));
		JLabel gkText = new JLabel();
		grossKleinschreibung = new JCheckBox();
		gkText.setText("Groß-Kleinschreibung");
		gkText.setFont(new Font(gkText.getFont().toString(), Font.PLAIN, 30));
		grossKleinschreibung.setPreferredSize(new Dimension(50,50));
		grossKleinschreibung.setMinimumSize(new Dimension(50,50));
		grossKleinschreibung.setMaximumSize(new Dimension(50,50));
		grossKleinschreibung.setIcon(checkbox);
		grossKleinschreibung.setSelectedIcon(checkbox_checked);
		gkPanel.setPreferredSize(new Dimension(1000,50));
		gkPanel.setMinimumSize(new Dimension(1000,50));
		gkPanel.setMaximumSize(new Dimension(1000,50));
		gkPanel.add(grossKleinschreibung);
		gkPanel.add(gkText);
		
		//Speichern
//		JButton speichern = new JButton("Speichern");
//		JPanel speicherPanel = new JPanel();
//		speichern.setPreferredSize(new Dimension(150, 50));
//		speichern.setFont(new Font(speichern.getFont().toString(), Font.PLAIN, 20));
//		speichern.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				Lernkartei kartei = new Lernkartei(beschreibung.getText().trim(), sprache1Text.getText().trim(), sprache2Text.getText().trim(), true, grossKleinschreibung.isSelected());
//				VokabeltrainerDB.hinzufuegenLernkartei(kartei);
//				for (int i = 0; i < slider.getValue(); i++) {
//					fach[i].setBeschreibung("test");
//					VokabeltrainerDB.hinzufuegenFach(kartei.getNummer(), fach[i]);
//				}
//				setVisible(false);
//				((StartVokabeltrainer) StartVokabeltrainer.getStartVokabelTrainer(speicherPanel)).changeToViewLernkarteien();
////				((StartVokabeltrainer) StartVokabeltrainer.getStartVokabelTrainer(speicherPanel)).getViewLernkarteien().getSammlung().reloadLernkarteien();
////				((StartVokabeltrainer) StartVokabeltrainer.getStartVokabelTrainer(speicherPanel)).getViewLernkarteien().getSammlung().repaint();
//			}
//			
//		});
//		speicherPanel.add(speichern);
		
		//weiter Knopf
		JButton weiter = new JButton();
		JPanel weiterPanel = new JPanel();
		weiter.setPreferredSize(new Dimension(150,50));
		weiter.setText("Weiter");
		weiter.setFont(new Font(weiter.getFont().toString(), Font.PLAIN, 20));
		weiter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (beschreibung.getText().trim().isEmpty() || sprache1Text.getText().trim().isEmpty() || sprache2Text.getText().trim().isEmpty()) {
					if (beschreibung.getText().trim().isEmpty()){
						beschreibung.setBorder(BorderFactory.createLineBorder(Color.red));
					}
					if (sprache1Text.getText().trim().isEmpty()) {
						sprache1Text.setBorder(BorderFactory.createLineBorder(Color.red));
					}
					if (sprache2Text.getText().trim().isEmpty()){
						sprache2Text.setBorder(BorderFactory.createLineBorder(Color.red));
					}
				} else {
					Lernkartei kartei = new Lernkartei(beschreibung.getText().trim(), sprache1Text.getText().trim(), sprache2Text.getText().trim(), true, grossKleinschreibung.isSelected());
					beschreibung.setText("");
					sprache1Text.setText("");
					sprache2Text.setText("");
					grossKleinschreibung.setSelected(false);
					VokabeltrainerDB.hinzufuegenLernkartei(kartei);
					Fach fach[] = new Fach[slider.getValue()];
					for (int i = 0; i < slider.getValue(); i++) {
						fach[i] = new Fach();
						VokabeltrainerDB.hinzufuegenFach(kartei.getNummer(), fach[i]);
					}
					Woerter worteingabe = new Woerter(kartei,weiterPanel);
					worteingabe.setVisible(true);
				}
			}
		});
		
		weiterPanel.add(weiter);
		
		
		
		this.add(Box.createRigidArea(new Dimension(1920,75)));
		this.add(namePanel);
		this.add(beschreibungPanel);
		this.add(Box.createRigidArea(new Dimension(1920,30)));
		this.add(sprache1Panel);
		this.add(sprache1TextPanel);
		this.add(Box.createRigidArea(new Dimension(1920,30)));
		this.add(sprache2Panel);
		this.add(sprache2TextPanel);
		this.add(Box.createRigidArea(new Dimension(1920,30)));
		this.add(sliderPanel);
		this.add(Box.createRigidArea(new Dimension(1920,30)));
		this.add(gkPanel);
		this.add(Box.createRigidArea(new Dimension(1920,100)));
//		this.add(speicherPanel);
		this.add(weiterPanel);		
		}
}
