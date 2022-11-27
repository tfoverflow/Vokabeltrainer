package net.tfobz.vokabeltrainer.gui.viewLernkarteien;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import net.tfobz.vokabeltrainer.model.Lernkartei;

public class LernkarteiComponent extends JComponent {
	private static final long serialVersionUID = -4572167597910292369L;
	Lernkartei kartei = null;
	JLabel percent = null;
	JLabel name = null;
	JButton playButton = null;
	public LernkarteiComponent(Lernkartei kartei) {
		this.kartei = kartei;
		
		this.setBackground(new Color(0, 255, 0));
		this.setMinimumSize(new Dimension(300, 200));
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		percent = new JLabel(getPercent(), SwingConstants.CENTER);
		percent.setVerticalAlignment(SwingConstants.CENTER);
		percent.setPreferredSize(new Dimension(300, 100));
		percent.setBorder(new MatteBorder(0,0,1,0,Color.gray));
		percent.setFont(new Font(percent.getFont().getFontName(), Font.PLAIN, 25));
		
		name = new JLabel(kartei.getBeschreibung());
		name.setPreferredSize(new Dimension(250, 50));
		name.setBorder(new MatteBorder(0,0,0,1,Color.gray));
		
		playButton = new JButton();
		playButton.setPreferredSize(new Dimension(40,40));
		ImageIcon play_arrow = new ImageIcon("src/net/tfobz/vokabeltrainer/gui/assets/play_arrow.png");
		playButton.setIcon(play_arrow);
		
		this.setBorder(BorderFactory.createLineBorder(Color.gray));
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 10;
		c.gridheight = 7;
		this.add(percent, c);
		
		c.gridx = 0;
		c.gridy = 7;
		c.gridwidth = 7;
		c.gridheight = 3;
		this.add(name, c);
		
		c.gridx = 7;
		c.gridy = 7;
		c.gridwidth = 3;
		c.gridheight = 3;
		this.add(playButton, c);
	}
	
	private String getPercent() {
		return "69%";
	}
}
