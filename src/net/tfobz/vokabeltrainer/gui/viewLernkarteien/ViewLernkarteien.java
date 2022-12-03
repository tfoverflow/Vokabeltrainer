package net.tfobz.vokabeltrainer.gui.viewLernkarteien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.tfobz.vokabeltrainer.gui.createLernkartei.Import;
import net.tfobz.vokabeltrainer.gui.topbar.*;
import net.tfobz.vokabeltrainer.model.Lernkartei;

public class ViewLernkarteien extends JPanel {	

	private static final long serialVersionUID = 5004672701864541712L;

	Import importDialog = null;
	
	int height = 1080;
	int width = 1920;
	ImageIcon upload = new ImageIcon("src/net/tfobz/vokabeltrainer/gui/assets/upload.png");
	
	LernkarteienSammlung sammlung = null;
	public ViewLernkarteien() {
		this.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				height = (int) getHeight();
				width = (int) getWidth();
			}
		});
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JButton settings = new JButton();
		settings.setPreferredSize(new Dimension(50, 50));;
		settings.setIcon(upload);
		
	//ActionListener für Buttons :3
			settings.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					importDialog = new Import(sammlung);
					importDialog.setVisible(true);
				}
			});
		
		this.add(Box.createRigidArea(new Dimension(0,30)));
		
		sammlung = new LernkarteienSammlung();
//		sammlung.setSize(height,width);
		sammlung.setLocation(0,100);
		
		this.add(sammlung); 
//		sammlung.setBackground(new Color(255, 0, 0));
	}
	
	public void export(Lernkartei kartei) {
		
	}
}
