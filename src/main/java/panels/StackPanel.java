package panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import domain.Chip;
import domain.Stack;

public class StackPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private BufferedImage image;

	public StackPanel() {
//		this.setBackground(new Color(0, 100, 0));
	}

	public StackPanel(Stack stack, Color backgroundColor) {
		this.setLayout(new GridLayout(1, 0));
//		this.setBackground(new Color(0, 100, 0));
		for (Chip chip : stack.getChips()) {
			ChipPanel chipPanel = new ChipPanel(chip);
			chipPanel.setBackground(backgroundColor);
			this.add(chipPanel);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null); // see javadoc for more info on the
										// parameters
	}

}
