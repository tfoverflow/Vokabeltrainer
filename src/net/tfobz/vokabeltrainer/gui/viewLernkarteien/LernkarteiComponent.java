package net.tfobz.vokabeltrainer.gui.viewLernkarteien;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import net.tfobz.vokabeltrainer.gui.StartVokabeltrainer;
import net.tfobz.vokabeltrainer.model.Lernkartei;

public class LernkarteiComponent extends JComponent {
	private static final long serialVersionUID = -4572167597910292369L;

	private JLabel percent = null;
	private JLabel name = null;

	public JButton playButton = null;

	public LernkarteiComponent(Lernkartei kartei) {

		this.setBackground(new Color(0, 255, 0));
		this.setMinimumSize(new Dimension(300, 200));
		/* TODO Bissl Performance-Heavy.
		kartei.aktualisiereIsErinnerungfaellig();
		if(kartei.isErinnerungfaellig())
			this.setBorder(BorderFactory.createLineBorder(Color.red));
		else*/
			this.setBorder(BorderFactory.createLineBorder(Color.gray));
		 
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		percent = new JLabel(getPercent(kartei), SwingConstants.CENTER);
		percent.setVerticalAlignment(SwingConstants.CENTER);
		percent.setPreferredSize(new Dimension(300, 100));
		percent.setBorder(new MatteBorder(0, 0, 1, 0, Color.gray));
		percent.setFont(new Font(percent.getFont().getFontName(), Font.PLAIN, 25));

		name = new JLabel(kartei.getBeschreibung());
		name.setPreferredSize(new Dimension(250, 50));
		name.setBorder(new MatteBorder(0, 0, 0, 1, Color.gray));

		playButton = new JButton();
		playButton.setPreferredSize(new Dimension(40, 40));
		ImageIcon play_arrow = new ImageIcon("src/net/tfobz/vokabeltrainer/gui/assets/play_arrow.png");
		playButton.setIcon(play_arrow);
		
		// I'm sorry
		playButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FachAuswahl fachAuswahl = new FachAuswahl(kartei, ((StartVokabeltrainer) StartVokabeltrainer.getStartVokabelTrainer((JButton) e.getSource())));
				fachAuswahl.setVisible(true);
			}
		});


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

	private String getPercent(Lernkartei kartei) {
		return "69%";
	}
}
