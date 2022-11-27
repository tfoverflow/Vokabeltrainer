package net.tfobz.vokabeltrainer.gui.Lernansicht;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.tfobz.vokabeltrainer.model.Lernkartei;

public class LernAnsicht extends JPanel {

	private static final long serialVersionUID = -5505440539728124434L;

	public LernAnsicht(Lernkartei kartei) {
		System.out.println("Hey Hey");
		this.add(new JLabel("Lernansicht - " + kartei.getBeschreibung()));
	}
	
}
