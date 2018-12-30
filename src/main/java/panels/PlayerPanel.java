package panels;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class PlayerPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public PlayerPanel() {
		setLayout(new GridLayout(1, 1));
		this.setBackground(BlackjackPanel.FELT_GREEN);
	}

	// @Override
	// protected void paintComponent(Graphics g) {
	// super.paintComponent(g);
	// // g.drawImage(image, 0, 0, null); // see javadoc for more info on the
	// // parameters
	// }
}
