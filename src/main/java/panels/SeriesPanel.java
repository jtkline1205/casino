package panels;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class SeriesPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private List<HandPanel> handPanels;

	public SeriesPanel() {
		handPanels = new ArrayList<HandPanel>();
		setLayout(new GridLayout(1, 1));
	}

	public SeriesPanel(HandPanel handPanel) {
		handPanels = new ArrayList<HandPanel>();
		setLayout(new GridLayout(1, 1));
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
