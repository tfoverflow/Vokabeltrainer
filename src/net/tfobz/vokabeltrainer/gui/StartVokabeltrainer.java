package net.tfobz.vokabeltrainer.gui;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import net.tfobz.vokabeltrainer.gui.Lernansicht.LernAnsicht;
import net.tfobz.vokabeltrainer.gui.createLernkartei.CreateLernkartei;
import net.tfobz.vokabeltrainer.gui.mainmenu.MainMenu;
import net.tfobz.vokabeltrainer.gui.viewLernkarteien.ViewLernkarteien;
import net.tfobz.vokabeltrainer.model.Fach;
import net.tfobz.vokabeltrainer.model.Lernkartei;

public class StartVokabeltrainer extends JFrame {
	private static final long serialVersionUID = -8366371103918318718L;
	
	private MainMenu mainMenu = null;
	private CreateLernkartei createLernkartei = null;
	private ViewLernkarteien viewLernkarteien = null;
	
	public StartVokabeltrainer() {
		this.setSize(1200, 800); // Default Fenstergroesse, wenn Fenster nicht maximiert ist :3
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); // Starte Fenster maximiert :3
		this.setLocationRelativeTo(null); //Öffne in der Bildschirmmitte :3
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(800,500));
		
		this.setIconImage(new ImageIcon("src/net/tfobz/vokabeltrainer/gui/assets/logo.png").getImage());
		
		mainMenu = new MainMenu(this);
		this.changeToMainMenu();
		createLernkartei = new CreateLernkartei();
		viewLernkarteien = new ViewLernkarteien();
	}
	public static void main(String[] args) {
//		MainMenu mainMenu = new MainMenu();
//		mainMenu.setVisible(true);
		StartVokabeltrainer f = new StartVokabeltrainer();
		f.setVisible(true);
		
	}
	public void changeToMainMenu() {
		this.getContentPane().removeAll();
		this.add(mainMenu);
		this.setTitle("Vokabeltrainer - MainMenu");
		this.revalidate();
	 }
	public void changeToCreateLernkartei() {
		this.getContentPane().removeAll();
		this.add(createLernkartei);
		this.setTitle("Vokabeltrainer - Lernkartei erstellen");
		this.revalidate();
	 }
	public void changeToViewLernkarteien() {
		this.getContentPane().removeAll();
		this.add(viewLernkarteien);
		this.setTitle("Vokabeltrainer - Lernkarteienübersicht");
		this.revalidate();
	 }
	public void changeToLearnAnsicht(Lernkartei kartei, Fach fach) {
		this.getContentPane().removeAll();
		this.add(new LernAnsicht(kartei, fach));
		this.setTitle("Vokabeltrainer - " + kartei.getBeschreibung());
		this.revalidate();
	}
	
	public static Container getStartVokabelTrainer(Container container) {
		container=container.getParent();
		if(container.getClass().getSimpleName().equals("StartVokabeltrainer"))
			return container;
		else
			return getStartVokabelTrainer(container);
	}
	

}
