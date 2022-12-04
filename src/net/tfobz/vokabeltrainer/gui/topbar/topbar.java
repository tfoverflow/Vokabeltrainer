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
import javax.swing.JToggleButton;

import net.tfobz.vokabeltrainer.gui.StartVokabeltrainer;
import net.tfobz.vokabeltrainer.gui.createLernkartei.Import;
import net.tfobz.vokabeltrainer.model.VokabeltrainerDB;

public class topbar extends JPanel {
	private static final long serialVersionUID = -7970622509539947502L;
	private JLabel titel = new JLabel();
	private JPanel importPanel = new JPanel();
	private JPanel homePanel = new JPanel();
	private JButton importButton = new JButton();
	private JToggleButton filterErinnerungButton = new JToggleButton();
	private JButton homeButton = new JButton();
	ImageIcon upload = new ImageIcon("src/net/tfobz/vokabeltrainer/gui/assets/upload.png");
	ImageIcon filter = new ImageIcon("src/net/tfobz/vokabeltrainer/gui/assets/filter.png");
	ImageIcon filter_off = new ImageIcon("src/net/tfobz/vokabeltrainer/gui/assets/filter_off.png");
	ImageIcon home = new ImageIcon("src/net/tfobz/vokabeltrainer/gui/assets/home.png");
	Import importDialog = null;
	Component glue = Box.createHorizontalGlue(), glue2 = Box.createHorizontalGlue();
	
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
		
		// Filter, dass nur Karteien mit vergangenen Erinnerungsdatum angezeigt werden
		filterErinnerungButton.setPreferredSize(new Dimension(50,50));
		filterErinnerungButton.setMaximumSize(new Dimension(50,50));
		filterErinnerungButton.setMinimumSize(new Dimension(50,50));
		filterErinnerungButton.setIcon(filter);
		filterErinnerungButton.setSelectedIcon(filter_off);
		filterErinnerungButton.setToolTipText("Wenn aktiv, werden nur Lernkarteien mit vergangenen Erinnerungsdatum angezeigt.");
		filterErinnerungButton.setSelected(VokabeltrainerDB.getEinstellungenLernkarteienMitErinnerung());
		filterErinnerungButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(VokabeltrainerDB.getEinstellungenLernkarteienMitErinnerung());
				System.out.println(VokabeltrainerDB.setEinstellungenLernkarteienMitErinnerung(filterErinnerungButton.isSelected())); 
				((StartVokabeltrainer) StartVokabeltrainer.getStartVokabelTrainer(homePanel)).changeToViewLernkarteien();
			}
		});
				
		homePanel.setLayout(new BoxLayout(homePanel, BoxLayout.X_AXIS));
		homePanel.add(Box.createRigidArea(new Dimension(150,75)));
		
		homeButton.setPreferredSize(new Dimension(50,50));
		homeButton.setMinimumSize(new Dimension(50,50));
		homeButton.setMaximumSize(new Dimension(50,50));
		homeButton.setIcon(home);
		homeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				((StartVokabeltrainer) StartVokabeltrainer.getStartVokabelTrainer(homePanel)).changeToMainMenu();
			}
		});

		homePanel.add(homeButton);
		
		this.add(homePanel,BorderLayout.LINE_START);
		this.add(titel,BorderLayout.CENTER);	
		this.add(importPanel, BorderLayout.LINE_END);
	}

	
	public void setTitel(String t) {
		titel.setText(t);
		titel.setFont(new Font("Karumbi	", Font.BOLD, 40));
	}
	
	public void setImpTrue() {
		importPanel.add(importButton);
		importPanel.add(glue);
		importPanel.add(filterErinnerungButton);
		importPanel.add(glue2);
		importPanel.repaint();
	}
	
	public void setImpFalse() {
		importPanel.remove(importButton);
		importPanel.remove(glue);
		importPanel.remove(filterErinnerungButton);
		importPanel.remove(glue2);
		importPanel.repaint();
	}
}
