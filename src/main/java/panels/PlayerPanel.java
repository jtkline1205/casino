package panels;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class PlayerPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public PlayerPanel() {
		setLayout(new GridLayout(1, 1));
		this.setBackground(BlackjackPanel.FELT_GREEN);
	}

}
