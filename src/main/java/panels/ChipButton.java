package panels;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import domain.Denomination;

public class ChipButton extends JButton {
	private int order;
	private boolean pressed;
	private Denomination associatedDenomination;

	public ChipButton(Denomination denomination, int order) {
		try {
			Image image = ImageIO.read(new File("src/main/resources/" + denomination.getColor() + "chip.png"));
			ImageIcon icon = new ImageIcon(image);
			this.setIcon(icon);
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.order = order;
		this.pressed = false;
		this.associatedDenomination = denomination;
	}

	public String getButtonName() {
		return "" + this.associatedDenomination.getValue();
	}

	public int getOrder() {
		return this.order;
	}

	public void setPressed(boolean pressed) {
		this.pressed = pressed;
	}

	public Denomination getAssociatedDenomination() {
		return this.associatedDenomination;
	}

	public boolean getPressed() {
		return this.pressed;
	}
}
