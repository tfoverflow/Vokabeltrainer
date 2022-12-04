package net.tfobz.vokabeltrainer.gui.viewLernkarteien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import net.tfobz.vokabeltrainer.gui.createLernkartei.Import;
import net.tfobz.vokabeltrainer.gui.topbar.*;
import net.tfobz.vokabeltrainer.model.Lernkartei;
import net.tfobz.vokabeltrainer.model.VokabeltrainerDB;

public class ViewLernkarteien extends JPanel {	

	private static final long serialVersionUID = 5004672701864541712L;

	Import importDialog = null;
	
	int height = 1080;
	int width = 1920;
	
	private LernkarteienSammlung sammlung = null;
	public ViewLernkarteien() {
		System.out.println(VokabeltrainerDB.getLernkarteien().toString());
		this.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				height = (int) getHeight();
				width = (int) getWidth();
			}
		});
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	
		
		this.add(Box.createRigidArea(new Dimension(0,30)));
		
		sammlung = new LernkarteienSammlung();
//		sammlung.setSize(height,width);
		sammlung.setLocation(0,100);
		sammlung.setMinimumSize(new Dimension(width-800, 8000));
		sammlung.setPreferredSize(new Dimension(width-500, 8000));
		sammlung.setMaximumSize(new Dimension(width-400, 8000));
		this.add(sammlung);	
	}
	
	public LernkarteienSammlung getSammlung() {
		return sammlung;
	}
	
	public void export(Lernkartei kartei) {
		
	}
	
	public void test(ActionEvent e) {
//		importDialog = new Import(sammlung);
		importDialog.setVisible(true);
	}
}
