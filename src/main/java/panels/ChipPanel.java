package panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import domain.Chip;

public class ChipPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private BufferedImage image;

	public ChipPanel() {
		try {
			image = ImageIO.read(new File("src/main/resources/whitechip.png"));
//			this.setBackground(new Color(0, 100, 0));
		} catch (IOException ex) {
			System.out.println("Could not find file.");
		}
	}

	public ChipPanel(Chip chip) {
		try {
			image = ImageIO.read(new File("src/main/resources/" + chip.getResourceName() + ".png"));
//			this.setBackground(new Color(0, 100, 0));
		} catch (IOException ex) {
			System.out.println("Could not find file with resourceName: " + chip.getResourceName());
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, 28, 28, null);
	}

}
