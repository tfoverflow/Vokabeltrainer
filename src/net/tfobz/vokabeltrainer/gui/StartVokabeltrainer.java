package net.tfobz.vokabeltrainer.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import com.formdev.flatlaf.FlatDarkLaf;

import net.tfobz.vokabeltrainer.gui.Lernansicht.LernAnsicht;
import net.tfobz.vokabeltrainer.gui.createLernkartei.CreateLernkartei;
import net.tfobz.vokabeltrainer.gui.mainmenu.MainMenu;
import net.tfobz.vokabeltrainer.gui.topbar.topbar;
import net.tfobz.vokabeltrainer.gui.viewLernkarteien.ViewLernkarteien;
import net.tfobz.vokabeltrainer.model.Fach;
import net.tfobz.vokabeltrainer.model.Lernkartei;

public class StartVokabeltrainer extends JFrame {
	private static final long serialVersionUID = -8366371103918318718L;
	
	private MainMenu mainMenu = null;
	private CreateLernkartei createLernkartei = null;
	private ViewLernkarteien viewLernkarteien = null;
	private JPanel content = null;
	private int height = 1080;
	private int width = 1920;
	topbar topbar = new topbar();
	
	public StartVokabeltrainer() {
		this.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				height = (int) getHeight();
				width = (int) getWidth();
			}
		});
		this.setLayout(new GridBagLayout());
		this.setSize(1200, 800); // Default Fenstergroesse, wenn Fenster nicht maximiert ist :3
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); // Starte Fenster maximiert :3
		this.setLocationRelativeTo(null); //Öffne in der Bildschirmmitte :3
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(800,500));
		
		this.setIconImage(new ImageIcon("src/net/tfobz/vokabeltrainer/gui/assets/logo.png").getImage());
		
		mainMenu = new MainMenu(this);
		content = new JPanel();
		this.changeToMainMenu();
		createLernkartei = new CreateLernkartei();
		viewLernkarteien = new ViewLernkarteien();
//		content.setPreferredSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
		
		this.changeToMainMenu();
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.PAGE_START;
		c.weightx = 1;
//		c.weighty = 0.1;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 100;
		c.gridheight = 1;
		topbar.setMaximumSize(new Dimension(1920, 75));
		topbar.setMinimumSize(new Dimension(1920, 75));
		topbar.setPreferredSize(new Dimension(1920, 75));
		this.add(topbar, c);
		
		c.anchor = GridBagConstraints.CENTER;

		c.weighty = 0.9;
		c.gridx = 0;
		c.gridy = 10;
		c.gridwidth = 100;
		c.gridheight = 90;
		this.add(content, c);
		
		/**
		 * KeyBindings
		 */
		
		// Beende Vokabeltrainer, wenn strg + Q gedrückt wird
		topbar.getInputMap().put(KeyStroke.getKeyStroke("ctrl Q"), "quit");
		topbar.getActionMap().put("quit", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("I'm quitting!");
				dispose();
			}
		});
	}
	
	
	
	public static void main(String[] args) {
//		MainMenu mainMenu = new MainMenu();
//		mainMenu.setVisible(true);
		//setzt das Flatlaf theme
		FlatDarkLaf.setup();
		StartVokabeltrainer f = new StartVokabeltrainer();
		f.setVisible(true);
		
	}
	public void changeToMainMenu() {
		content.removeAll();
		content.add(mainMenu);
		this.setTitle("Vokabeltrainer - MainMenu");
		topbar.setTitel("Vokabeltrainer");
		topbar.setImpFalse();
		content.repaint();
	 }
	public void changeToCreateLernkartei() {
		this.content.removeAll();
		content.add(createLernkartei);
		this.setTitle("Vokabeltrainer - Lernkartei erstellen");
		topbar.setTitel("Lernkartei erstellen");
		topbar.setImpFalse();
		content.repaint();
	 }
	public void changeToViewLernkarteien() {
		this.content.removeAll();
		viewLernkarteien.getSammlung().reloadLernkarteien();
		content.add(viewLernkarteien);
		this.setTitle("Vokabeltrainer - Lernkarteienübersicht");
		topbar.setTitel("Lernkarteien");
		topbar.setImpTrue();
		content.repaint();
	 }
	public void changeToLearnAnsicht(Lernkartei kartei, Fach fach) {
		this.content.removeAll();
		content.add(new LernAnsicht(kartei, fach));
		this.setTitle("Vokabeltrainer - " + kartei.getBeschreibung());
		topbar.setTitel("Lernen");
		topbar.setImpFalse();
		content.repaint();
	}
	
	public static Container getStartVokabelTrainer(Container container) {
		container=container.getParent();
		if(container.getClass().getSimpleName().equals("StartVokabeltrainer"))
			return container;
		else
			return getStartVokabelTrainer(container);
	}
	
	public ViewLernkarteien getViewLernkarteien() {
		return viewLernkarteien;
	}
	

}
