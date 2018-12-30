package panels;

import javax.swing.JButton;

import domain.Decision;

public class BlackjackButton extends JButton {
	private String buttonName;
	private int order;
	private boolean pressed;
	private Decision associatedDecision;

	public BlackjackButton(String buttonName, int order) {
		this.buttonName = buttonName;
		this.setText(buttonName);
		this.order = order;
		this.pressed = false;
		this.associatedDecision = Decision.getDecisionFromName(buttonName);
	}

	public String getButtonName() {
		return this.buttonName;
	}

	public int getOrder() {
		return this.order;
	}

	public void setPressed(boolean pressed) {
		this.pressed = pressed;
	}

	public Decision getAssociatedDecision() {
		return this.associatedDecision;
	}

	public boolean getPressed() {
		return this.pressed;
	}
}
