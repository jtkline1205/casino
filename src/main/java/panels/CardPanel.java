package panels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import domain.Card;

public class CardPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private BufferedImage image;

	public CardPanel() {
		try {
			image = ImageIO.read(new File("src/main/resources/card_back.png"));
			this.setBackground(BlackjackPanel.FELT_GREEN);
		} catch (IOException ex) {
			System.out.println("Could not find file.");
		}
	}

	public CardPanel(Card card) {
		try {
			image = ImageIO.read(new File("src/main/resources/" + card.getResourceName() + ".png"));
			this.setBackground(BlackjackPanel.FELT_GREEN);
		} catch (IOException ex) {
			System.out.println("Could not find file.");
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null); // see javadoc for more info on the
		// parameters
	}

}
