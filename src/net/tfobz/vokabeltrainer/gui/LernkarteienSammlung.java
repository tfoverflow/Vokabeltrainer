package net.tfobz.vokabeltrainer.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import net.tfobz.vokabeltrainer.model.*;

public class LernkarteienSammlung extends JPanel {
	ArrayList<Lernkartei> sammlung = null;

	public LernkarteienSammlung() {
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
		this.setMinimumSize(new Dimension(1200,1000));
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
