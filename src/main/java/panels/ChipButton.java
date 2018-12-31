package panels;

import javax.swing.JButton;

import domain.Denomination;

public class ChipButton extends JButton {
	private int order;
	private boolean pressed;
	private Denomination associatedDenomination;

	public ChipButton(Denomination denomination, int order) {
		this.setText("" + denomination.getValue());
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
