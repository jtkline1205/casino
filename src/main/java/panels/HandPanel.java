package panels;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class HandPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final Color FELT_COLOR = new Color(0, 100, 0);

	public HandPanel() {
		setLayout(new GridLayout(1, 1));
//		setBackground(FELT_COLOR);
	}

	public HandPanel(CardPanel cardPanel1, CardPanel cardPanel2) {
		setLayout(new GridLayout(1, 1));
//		setBackground(FELT_COLOR);
		add(cardPanel1);
		add(cardPanel2);
	}

}
