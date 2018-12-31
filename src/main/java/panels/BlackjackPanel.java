package panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import domain.Decision;
import domain.Denomination;
import domain.Stack;

public class BlackjackPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JPanel topPanel;
	private JPanel dealerPanel;
	private JPanel shoePanel;
	private JPanel resultPanel;
	private StackPanel houseBankPanel;

	private JPanel middlePanel;
	private JLabel titleLabel1;
	private JLabel titleLabel2;
	private JLabel titleLabel3;
	private JLabel titleLabel4;

	private JPanel bottomPanel;
	private PlayerPanel playerPanel;
	private JPanel chipButtonPanel;
	private JPanel decisionButtonPanel;
	private JPanel controlPanel;
	private JPanel playerAndControlPanel;
	private StackPanel playerBankPanel;
	private StackPanel playerBetPanel;
	private StackPanel playerWinningsPanel;
	private JPanel playerActiveChipsPanel;

	private Map<Decision, DecisionButton> decisionButtonMap;
	private Map<Denomination, ChipButton> chipButtonMap;

	private Decision latestDecision;
	private Denomination latestIncrement;

	public static final Color FELT_GREEN = new Color(0, 100, 0);
	private final Color GOLD = new Color(182, 182, 29);
	private final Color PURPLE = new Color(177, 82, 182);
	private final Color BLUE = new Color(117, 166, 255);
	private final Color SCARLET = new Color(211, 20, 43);

	public BlackjackPanel() {
		initializeComponents();
		titleLabel1 = setupTitleLabel("BLACKJACK PAYS 3:2", Font.BOLD);
		titleLabel2 = setupTitleLabel("Dealer must draw to 16 and stand on all 17s", Font.ITALIC);
		titleLabel3 = setupTitleLabel("Aces can be split once with one card dealt to each", Font.ITALIC);
		titleLabel4 = setupTitleLabel("Minimum bet: $5  Maximum bet: $500", Font.ITALIC);
		addActionListeners();
		setLayouts();
		setPreferredSizes();
		addComponents();
	}

	public Decision getLatestDecision() {
		return this.latestDecision;
	}

	public Denomination getLatestIncrement() {
		return this.latestIncrement;
	}

	public void resetLatestIncrement() {
		this.latestIncrement = null;
	}

	private void addActionListeners() {
		for (ChipButton button : chipButtonMap.values()) {
			button.addActionListener(this);
		}
		for (DecisionButton button : decisionButtonMap.values()) {
			button.addActionListener(this);
		}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		JButton button = ((JButton) ae.getSource());
		if (button instanceof DecisionButton) {
			((DecisionButton) button).setPressed(true);
			latestDecision = ((DecisionButton) button).getAssociatedDecision();
		}
		if (button instanceof ChipButton) {
			((ChipButton) button).setPressed(true);
			latestIncrement = ((ChipButton) button).getAssociatedDenomination();
		}

	}

	public void addDealerHandPanel(HandPanel dealerHandPanel) {
		this.dealerPanel.add(dealerHandPanel);
	}

	public void addPlayerSeriesPanel(SeriesPanel playerSeriesPanel) {
		this.playerPanel.add(playerSeriesPanel);
	}

	public void updatePlayerBankPanel(Double bankroll) {
		bottomPanel.remove(playerBankPanel);
		playerBankPanel = new StackPanel(new Stack(bankroll), FELT_GREEN);
		bottomPanel.add(playerBankPanel);
	}

	public void updatePlayerBetPanel(Double playerBet) {
		playerActiveChipsPanel.remove(playerBetPanel);
		playerActiveChipsPanel.remove(playerWinningsPanel);
		playerBetPanel = new StackPanel(new Stack(playerBet), BLUE);
		playerActiveChipsPanel.add(playerBetPanel);
		playerActiveChipsPanel.add(playerWinningsPanel);
	}

	public void clearPlayerWinningsPanel() {
		playerActiveChipsPanel.remove(playerBetPanel);
		playerActiveChipsPanel.remove(playerWinningsPanel);
		playerWinningsPanel = new StackPanel(GOLD);
		playerActiveChipsPanel.add(playerBetPanel);
		playerActiveChipsPanel.add(playerWinningsPanel);
	}

	public void clearHouseBankPanel() {
		resultPanel.remove(houseBankPanel);
		// playerActiveChipsPanel.remove(playerBetPanel);
		// playerActiveChipsPanel.remove(playerWinningsPanel);
		resultPanel.remove(playerActiveChipsPanel);

		// if (doubleValue < 0) {
		houseBankPanel = new StackPanel(PURPLE);
		// updatePlayerBetPanel(0.00);
		// } else if (doubleValue > 0) {
		// playerWinningsPanel = new StackPanel(new Stack(doubleValue), GOLD);
		// }

		resultPanel.add(houseBankPanel);
		// playerActiveChipsPanel.add(playerBetPanel);
		// playerActiveChipsPanel.add(playerWinningsPanel);
		resultPanel.add(playerActiveChipsPanel);
	}

	public void updateResultPanel(Double doubleValue) {
		resultPanel.remove(houseBankPanel);
		playerActiveChipsPanel.remove(playerBetPanel);
		playerActiveChipsPanel.remove(playerWinningsPanel);
		resultPanel.remove(playerActiveChipsPanel);

		if (doubleValue < 0) {
			houseBankPanel = new StackPanel(new Stack(doubleValue), PURPLE);
			updatePlayerBetPanel(0.00);
		} else if (doubleValue > 0) {
			playerWinningsPanel = new StackPanel(new Stack(doubleValue), GOLD);
		}

		resultPanel.add(houseBankPanel);
		playerActiveChipsPanel.add(playerBetPanel);
		playerActiveChipsPanel.add(playerWinningsPanel);
		resultPanel.add(playerActiveChipsPanel);
		enableBetButtonOnly();
	}

	private JLabel setupTitleLabel(String text, int fontType) {
		JLabel titleLabel = new JLabel();
		titleLabel.setText(text);
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setFont(new Font("Serif", fontType, 15));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		return titleLabel;
	}

	private void setLayouts() {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		topPanel.setLayout(new GridLayout(1, 2));
		dealerPanel.setLayout(new GridLayout(1, 1));
		shoePanel.setLayout(new GridLayout(1, 1));

		middlePanel.setLayout(new GridLayout(5, 1));
		resultPanel.setLayout(new GridLayout(2, 1));
		playerActiveChipsPanel.setLayout(new GridLayout(1, 2));

		bottomPanel.setLayout(new GridLayout(2, 1));
		playerPanel.setLayout(new GridLayout(1, 1));
		playerAndControlPanel.setLayout(new GridLayout(1, 2));
		chipButtonPanel.setLayout(new GridLayout(6, 1));
		decisionButtonPanel.setLayout(new GridLayout(6, 1));
		controlPanel.setLayout(new GridLayout(1, 2));
	}

	private void setPreferredSizes() {
		this.setPreferredSize(new Dimension(1000, 700));
		topPanel.setPreferredSize(new Dimension(1000, 100));
		middlePanel.setPreferredSize(new Dimension(1000, 300));
		bottomPanel.setPreferredSize(new Dimension(1000, 300));
	}

	private void initializeComponents() {
		initializeJPanels();
		initializeStackPanels();
		initializeButtons();
	}

	private void initializeJPanels() {
		topPanel = new JPanel();
		dealerPanel = new JPanel();
		dealerPanel.setBackground(FELT_GREEN);
		shoePanel = new JPanel();

		resultPanel = new JPanel();
		middlePanel = new JPanel();
		middlePanel.setBackground(SCARLET);
		titleLabel1 = new JLabel();
		titleLabel2 = new JLabel();
		titleLabel3 = new JLabel();
		titleLabel4 = new JLabel();
		playerActiveChipsPanel = new JPanel();

		bottomPanel = new JPanel();
		playerPanel = new PlayerPanel();
		chipButtonPanel = new JPanel();
		decisionButtonPanel = new JPanel();
		controlPanel = new JPanel();
		playerAndControlPanel = new JPanel();
	}

	private void initializeStackPanels() {
		houseBankPanel = new StackPanel(PURPLE);
		playerWinningsPanel = new StackPanel(GOLD);
		playerBetPanel = new StackPanel(BLUE);
		playerBankPanel = new StackPanel(Color.WHITE);
	}

	private void initializeButtons() {
		decisionButtonMap = new HashMap<Decision, DecisionButton>();
		chipButtonMap = new HashMap<Denomination, ChipButton>();
		int order = 0;
		for (Denomination denomination : Denomination.values()) {
			ChipButton button = new ChipButton(denomination, order);
			button.setEnabled(true);
			chipButtonMap.put(denomination, button);
			order++;
		}
		order = 0;
		for (Decision decision : Decision.values()) {
			DecisionButton button = new DecisionButton(decision, order);
			button.setEnabled(false);
			decisionButtonMap.put(decision, button);
			order++;
		}
		decisionButtonMap.get(Decision.BET).setEnabled(true);
	}

	private void addComponents() {
		topPanel.add(dealerPanel);
		shoePanel.add(new CardPanel());
		topPanel.add(shoePanel);

		middlePanel.add(titleLabel1);
		middlePanel.add(titleLabel2);
		middlePanel.add(titleLabel3);
		middlePanel.add(titleLabel4);

		resultPanel.add(houseBankPanel);
		playerActiveChipsPanel.add(playerBetPanel);
		playerActiveChipsPanel.add(playerWinningsPanel);
		resultPanel.add(playerActiveChipsPanel);
		middlePanel.add(resultPanel);

		playerPanel.setBackground(FELT_GREEN);
		playerAndControlPanel.add(playerPanel);

		int counter = 0;
		while (counter < Denomination.values().length) {
			for (ChipButton button : chipButtonMap.values()) {
				if (button.getOrder() == counter) {
					chipButtonPanel.add(button);
					counter++;
				}
			}
		}

		counter = 0;
		while (counter < Decision.values().length) {
			for (DecisionButton button : decisionButtonMap.values()) {
				if (button.getOrder() == counter) {
					decisionButtonPanel.add(button);
					counter++;
				}
			}
		}

		controlPanel.add(chipButtonPanel);
		controlPanel.add(decisionButtonPanel);
		chipButtonPanel.setBackground(FELT_GREEN);
		decisionButtonPanel.setBackground(FELT_GREEN);
		controlPanel.setBackground(FELT_GREEN);
		playerAndControlPanel.add(controlPanel);
		bottomPanel.add(playerAndControlPanel);
		bottomPanel.add(playerBankPanel);

		this.add(topPanel);
		this.add(middlePanel);
		this.add(bottomPanel);
	}

	public boolean getBetButtonPressed() {
		return this.decisionButtonMap.get(Decision.BET).getPressed();
	}

	public void setBetButtonPressed(boolean betButtonPressed) {
		this.decisionButtonMap.get(Decision.BET).setPressed(betButtonPressed);
	}

	public boolean getHandDecisionPressed() {
		for (Decision decision : Decision.values()) {
			if (!decision.equals(Decision.BET)) {
				if (this.decisionButtonMap.get(decision).getPressed()) {
					return true;
				}
			}
		}
		return false;
	}

	public void unpressAllDecisions() {
		for (Decision decision : Decision.values()) {
			if (!decision.equals(Decision.BET)) {
				this.decisionButtonMap.get(decision).setPressed(false);
			}
		}
	}

	public void disableBetAndDealAndEnableDecisions(boolean canAffordToDouble, boolean splitEnabled) {
		for (DecisionButton button : decisionButtonMap.values()) {
			if (button.getAssociatedDecision().equals(Decision.BET)) {
				button.setEnabled(false);
			} else if (button.getAssociatedDecision().equals(Decision.SPLIT)) {
				button.setEnabled(splitEnabled);
			} else if (button.getAssociatedDecision().equals(Decision.DOUBLE)) {
				button.setEnabled(canAffordToDouble);
			} else {
				button.setEnabled(true);
			}
		}
	}

	public void disableBetButton() {
		decisionButtonMap.get(Decision.BET).setEnabled(false);
	}

	public void enableHitStandAndBasicStrategyOnly() {
		for (DecisionButton button : decisionButtonMap.values()) {
			if (button.getAssociatedDecision().equals(Decision.HIT)
					|| button.getAssociatedDecision().equals(Decision.STAND)
					|| button.getAssociatedDecision().equals(Decision.BASIC_STRATEGY)) {
				button.setEnabled(true);
			} else {
				button.setEnabled(false);
			}
		}
	}

	public void enableBetButtonOnly() {
		for (DecisionButton button : decisionButtonMap.values()) {
			if (button.getAssociatedDecision().equals(Decision.BET)) {
				button.setEnabled(true);
			} else {
				button.setEnabled(false);
			}
		}
	}

	public void enableChipButtons(double playerBankroll) {
		for (ChipButton chipButton : chipButtonMap.values()) {
			chipButton.setEnabled(chipButton.getAssociatedDenomination().getValue() <= playerBankroll ? true : false);
		}
	}

	public void disableChipButtons() {
		for (ChipButton chipButton : chipButtonMap.values()) {
			chipButton.setEnabled(false);
		}
	}
}
