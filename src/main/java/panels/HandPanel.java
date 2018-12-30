package panels;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class HandPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public HandPanel() {
		setLayout(new GridLayout(1, 1));
	}

	public HandPanel(CardPanel cardPanel1) {
		this.setBackground(BlackjackPanel.FELT_GREEN);
		setLayout(new GridLayout(1, 1));
		add(cardPanel1);
	}

	public HandPanel(CardPanel cardPanel1, CardPanel cardPanel2) {
		this.setBackground(BlackjackPanel.FELT_GREEN);
		setLayout(new GridLayout(1, 1));
		add(cardPanel1);
		add(cardPanel2);
	}

}
