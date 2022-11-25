package net.tfobz.vokabeltrainer.gui.viewLernkarteien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.tfobz.vokabeltrainer.gui.createLernkartei.Import;

public class ViewLernkarteien extends JPanel {
	

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
		
		JPanel titelPanel = new JPanel();
		titelPanel.setLayout(new BorderLayout());
		
		this.add(Box.createRigidArea(new Dimension(0,30)));
		JLabel titel = new JLabel("Lernkarteienübersicht");
		JButton settings = new JButton();
		settings.setPreferredSize(new Dimension(50, 50));
		titel.setMaximumSize(new Dimension(200,50));
		titel.setHorizontalAlignment(JLabel.CENTER);
		settings.setIcon(upload);
		
		titelPanel.setMaximumSize(new Dimension(width-width/2,100));
		
	//ActionListener für Buttons
			settings.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					importDialog = new Import();
					importDialog.setVisible(true);
				}
			});
			
			
		titel.setAlignmentX(CENTER_ALIGNMENT);
		titel.setFont(new Font(titel.getFont().getFontName(),Font.PLAIN, 40));
		
		titelPanel.add(titel,BorderLayout.CENTER);
		titelPanel.add(settings, BorderLayout.LINE_END);
		
		
		this.add(titelPanel);
		
		this.add(Box.createRigidArea(new Dimension(0,30)));
		
		sammlung = new LernkarteienSammlung();
		sammlung.setSize(1920,200);
		sammlung.setLocation(0,100);
		
		this.add(sammlung); 
//		sammlung.setBackground(new Color(255, 0, 0));
	}
}
