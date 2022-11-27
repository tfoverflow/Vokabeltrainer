package net.tfobz.vokabeltrainer.gui.viewLernkarteien;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import net.tfobz.vokabeltrainer.gui.StartVokabeltrainer;
import net.tfobz.vokabeltrainer.model.*;

public class LernkarteienSammlung extends JPanel {
	private static final long serialVersionUID = -6493419412532184569L;
	ArrayList<Lernkartei> sammlung = null;

	public LernkarteienSammlung() {
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 20));
		this.setLocation(0, 200);
		this.setMinimumSize(new Dimension(1200, 1000));
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
