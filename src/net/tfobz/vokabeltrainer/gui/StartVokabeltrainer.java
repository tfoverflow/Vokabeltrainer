package net.tfobz.vokabeltrainer.gui;

import javax.swing.JFrame;

public class StartVokabeltrainer extends JFrame {
	public StartVokabeltrainer() {
		this.setTitle("Vokableltrainer - MainMenu");
		this.setSize(1200, 800); // Default Fenstergroesse, wenn Fenster nicht maximiert ist
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); // Starte Fenster maximiert
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.add(new MainMenu(this));
	}
	public static void main(String[] args) {
//		MainMenu mainMenu = new MainMenu();
//		mainMenu.setVisible(true);
		StartVokabeltrainer f = new StartVokabeltrainer();
		f.setVisible(true);
		
	}
	public void changeToCreateLernkartei() {
		System.out.println("Test1");
	 }
	public void changeToViewLernkarteien() {
		System.out.println("Test2");
	 }

}
