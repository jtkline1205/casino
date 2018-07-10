package panels;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class SeriesPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final Color FELT_COLOR = new Color(0, 100, 0);

	private List<HandPanel> handPanels;

	public SeriesPanel() {
		handPanels = new ArrayList<HandPanel>();
		setLayout(new GridLayout(1, 1));
//		setBackground(FELT_COLOR);
	}

	public SeriesPanel(HandPanel handPanel) {
		handPanels = new ArrayList<HandPanel>();
		setLayout(new GridLayout(1, 1));
//		setBackground(FELT_COLOR);
		add(handPanel);
		handPanels.add(handPanel);
	}

	public List<HandPanel> getHandPanels() {
		return this.handPanels;
	}

	public void addHandPanel(HandPanel handPanel) {
		this.handPanels.add(handPanel);
	}

	public void removeHandPanel(HandPanel handPanel) {
		this.handPanels.remove(handPanel);
	}
}
