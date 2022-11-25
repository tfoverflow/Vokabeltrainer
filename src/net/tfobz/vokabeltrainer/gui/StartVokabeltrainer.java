package net.tfobz.vokabeltrainer.gui;

import java.awt.Dimension;

import javax.swing.JFrame;

import net.tfobz.vokabeltrainer.gui.createLernkartei.CreateLernkartei;
import net.tfobz.vokabeltrainer.gui.mainmenu.MainMenu;
import net.tfobz.vokabeltrainer.gui.viewLernkarteien.ViewLernkarteien;
import net.tfobz.vokabeltrainer.model.Lernkartei;
import net.tfobz.vokabeltrainer.model.VokabeltrainerDB;

public class StartVokabeltrainer extends JFrame {
	MainMenu mainMenu = null;
	CreateLernkartei createLernkartei = null;
	ViewLernkarteien viewLernkarteien = null;
	
	public StartVokabeltrainer() {
		
		this.setSize(1200, 800); // Default Fenstergroesse, wenn Fenster nicht maximiert ist
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); // Starte Fenster maximiert
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(800,500));
		
		mainMenu = new MainMenu(this);
		createLernkartei = new CreateLernkartei();
		viewLernkarteien = new ViewLernkarteien();
		this.changeToMainMenu();
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
		this.setTitle("Vokableltrainer - MainMenu");
		this.revalidate();
	 }
	public void changeToCreateLernkartei() {
		this.getContentPane().removeAll();
		this.add(createLernkartei);
		this.setTitle("Vokableltrainer - Lernkartei erstellen");
		this.revalidate();
	 }
	public void changeToViewLernkarteien() {
		this.getContentPane().removeAll();
		this.add(viewLernkarteien);
		this.setTitle("Vokableltrainer - Lernkarteienübersicht");
		this.revalidate();
	 }

}
