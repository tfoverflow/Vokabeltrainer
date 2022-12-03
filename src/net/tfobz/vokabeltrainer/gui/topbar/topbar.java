package net.tfobz.vokabeltrainer.gui.topbar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.tfobz.vokabeltrainer.gui.createLernkartei.Import;

public class topbar extends JPanel {
	private static final long serialVersionUID = -7970622509539947502L;
	private JLabel titel = new JLabel();
	private JPanel importPanel = new JPanel();
	private JPanel space = new JPanel();
	private JButton importButton = new JButton();
	ImageIcon upload = new ImageIcon("src/net/tfobz/vokabeltrainer/gui/assets/upload.png");
	Import importDialog = null;
	
	public topbar() {
		this.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.GRAY));
		this.setLayout(new BorderLayout());
	
		titel.setHorizontalAlignment(JLabel.CENTER);
		titel.setFont(new Font(titel.getFont().getFontName(),Font.PLAIN, 40));

		importPanel.setLayout(new BoxLayout(importPanel, BoxLayout.X_AXIS));
		importPanel.setPreferredSize(new Dimension(150,75));
		importButton.setPreferredSize(new Dimension(50,50));
		importButton.setMaximumSize(new Dimension(50,50));
		importButton.setMinimumSize(new Dimension(50,50));
		importButton.setIcon(upload);
		importButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				importDialog = new Import(importPanel);
				importDialog.setVisible(true);
			}
		});
		
		space.setLayout(new BoxLayout(space, BoxLayout.X_AXIS));
		space.add(Box.createRigidArea(new Dimension(150,75)));

		this.add(space,BorderLayout.LINE_START);
		this.add(titel,BorderLayout.CENTER);	
		this.add(importPanel, BorderLayout.LINE_END);
	}

	
	public void setTitel(String t) {
		titel.setText(t);
	}
	
	public void setImpTrue() {
		importPanel.add(importButton);
		importPanel.add(Box.createRigidArea(new Dimension(100,75)));
		importPanel.repaint();
	}
	
	public void setImpFalse() {
		importPanel.remove(importButton);
		importPanel.remove(Box.createRigidArea(new Dimension(100,75)));
		importPanel.repaint();
	}
}
