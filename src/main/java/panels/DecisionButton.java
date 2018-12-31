package panels;

import javax.swing.JButton;

import domain.Decision;

public class DecisionButton extends JButton {
	private int order;
	private boolean pressed;
	private Decision associatedDecision;

	public DecisionButton(Decision decision, int order) {
		this.setText(decision.getName());
		this.order = order;
		this.pressed = false;
		this.associatedDecision = decision;
	}

	public String getButtonName() {
		return this.associatedDecision.getName();
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
