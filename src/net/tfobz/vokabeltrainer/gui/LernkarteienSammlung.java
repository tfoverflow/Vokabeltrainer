package net.tfobz.vokabeltrainer.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import net.tfobz.vokabeltrainer.model.*;

public class LernkarteienSammlung extends JPanel {
	ArrayList<Lernkartei> sammlung = null;

	public LernkarteienSammlung() {
		this.setLayout(new GridLayout(0, 4, 50, 50));
		reloadLernkarteien();
	}

	public void reloadLernkarteien() {
		sammlung = (ArrayList<Lernkartei>) VokabeltrainerDB.getLernkarteien();
		sammlung.forEach((kartei) -> {
			LernkarteiComponent karteiComponent = new LernkarteiComponent(kartei);
			karteiComponent.setMinimumSize(new Dimension(250, 200));
			this.add(karteiComponent);
		});

		System.out.println(sammlung.toString());
	}
}
