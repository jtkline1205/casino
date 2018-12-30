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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import domain.Decision;
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

	private JPanel bottomPanel;
	private PlayerPanel playerPanel;
	private JPanel statsPanel;
	private StackPanel playerBankPanel;
	private StackPanel playerBetPanel;
	private StackPanel playerWinningsPanel;
	private JPanel playerActiveChipsPanel;

	private BlackjackButton betButton;
	private BlackjackButton dealButton;
	private BlackjackButton basicStrategyButton;
	private BlackjackButton hitButton;
	private BlackjackButton standButton;
	private BlackjackButton splitButton;
	private BlackjackButton doubleButton;

	private Map<String, BlackjackButton> buttonMap;

	private Decision latestDecision;

	public static final Color FELT_GREEN = new Color(0, 100, 0);
	private final Color GOLD = new Color(182, 182, 29);
	private final Color PURPLE = new Color(177, 82, 182);
	private final Color BLUE = new Color(117, 166, 255);
	private final Color SCARLET = new Color(211, 20, 43);

	private final String[] buttonNames = new String[] { "Bet", "Deal", "Basic Strategy", "Hit", "Stand", "Double",
			"Split" };

	public BlackjackPanel() {
		initializeComponents();
		titleLabel1 = setupTitleLabel("BLACKJACK PAYS 3:2", Font.BOLD);
		titleLabel2 = setupTitleLabel("Dealer must draw to 16 and stand on all 17s", Font.ITALIC);
		titleLabel3 = setupTitleLabel("Aces can be split once with one card dealt to each", Font.ITALIC);
		addActionListeners();
		setLayouts();
		setPreferredSizes();
		addComponents();
	}

	public Decision getLatestDecision() {
		return this.latestDecision;
	}

	private void addActionListeners() {
		for (BlackjackButton button : buttonMap.values()) {
			button.addActionListener(this);
		}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		BlackjackButton button = ((BlackjackButton) ae.getSource());
		button.setPressed(true);
		latestDecision = button.getAssociatedDecision();
	}

	public void addDealerHandPanel(HandPanel dealerHandPanel) {
		this.dealerPanel.add(dealerHandPanel);
	}

	public void addPlayerSeriesPanel(SeriesPanel playerSeriesPanel) {
		this.playerPanel.add(playerSeriesPanel);
	}

	public void updatePlayerBankPanel(Double bankroll) {
		statsPanel.remove(playerBankPanel);
		playerBankPanel = new StackPanel(new Stack(bankroll), FELT_GREEN);
		statsPanel.add(playerBankPanel);
	}

	public void updatePlayerBetPanel(Double playerBet) {
		playerActiveChipsPanel.remove(playerBetPanel);
		playerActiveChipsPanel.remove(playerWinningsPanel);
		playerBetPanel = new StackPanel(new Stack(playerBet), BLUE);
		playerActiveChipsPanel.add(playerBetPanel);
		playerActiveChipsPanel.add(playerWinningsPanel);
	}

	public void updateResultPanel(Double doubleValue) {
		resultPanel.remove(houseBankPanel);
		playerActiveChipsPanel.remove(playerBetPanel);
		playerActiveChipsPanel.remove(playerWinningsPanel);
		resultPanel.remove(playerActiveChipsPanel);

		if (doubleValue < 0) {
			houseBankPanel = new StackPanel(new Stack(doubleValue), PURPLE);
		} else if (doubleValue > 0) {
			playerWinningsPanel = new StackPanel(new Stack(doubleValue), GOLD);
		}

		resultPanel.add(houseBankPanel);
		playerActiveChipsPanel.add(playerBetPanel);
		playerActiveChipsPanel.add(playerWinningsPanel);
		resultPanel.add(playerActiveChipsPanel);
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

		topPanel.setLayout(new GridLayout(1, 0));
		dealerPanel.setLayout(new GridLayout(1, 0));
		shoePanel.setLayout(new GridLayout(1, 0));

		middlePanel.setLayout(new GridLayout(4, 0));
		resultPanel.setLayout(new GridLayout(2, 0));
		playerActiveChipsPanel.setLayout(new GridLayout(0, 2));

		bottomPanel.setLayout(new GridLayout(0, 2));
		playerPanel.setLayout(new GridLayout(1, 0));
		statsPanel.setLayout(new GridLayout(0, 1));
	}

	private void setPreferredSizes() {
		this.setPreferredSize(new Dimension(800, 600));
		topPanel.setPreferredSize(new Dimension(800, 100));
		middlePanel.setPreferredSize(new Dimension(800, 200));
		bottomPanel.setPreferredSize(new Dimension(800, 300));
	}

	private void initializeComponents() {
		topPanel = new JPanel();
		dealerPanel = new JPanel();
		dealerPanel.setBackground(FELT_GREEN);
		shoePanel = new JPanel();

		resultPanel = new JPanel();
		houseBankPanel = new StackPanel(PURPLE);
		middlePanel = new JPanel();
		middlePanel.setBackground(SCARLET);
		titleLabel1 = new JLabel();
		titleLabel2 = new JLabel();
		titleLabel3 = new JLabel();
		playerActiveChipsPanel = new JPanel();
		playerWinningsPanel = new StackPanel(GOLD);
		playerBetPanel = new StackPanel(BLUE);

		bottomPanel = new JPanel();
		playerPanel = new PlayerPanel();
		statsPanel = new JPanel();
		playerBankPanel = new StackPanel(Color.WHITE);
		initializeButtons();
	}

	private void initializeButtons() {
		buttonMap = new HashMap<String, BlackjackButton>();
		int order = 0;
		for (String buttonName : buttonNames) {
			BlackjackButton button = new BlackjackButton(buttonName, order);
			button.setEnabled(false);
			buttonMap.put(buttonName, button);
			order++;
		}
		buttonMap.get("Bet").setEnabled(true);
	}

	private void addComponents() {
		topPanel.add(dealerPanel);
		shoePanel.add(new CardPanel());
		topPanel.add(shoePanel);

		middlePanel.add(titleLabel1);
		middlePanel.add(titleLabel2);
		middlePanel.add(titleLabel3);

		resultPanel.add(houseBankPanel);
		playerActiveChipsPanel.add(playerBetPanel);
		playerActiveChipsPanel.add(playerWinningsPanel);
		resultPanel.add(playerActiveChipsPanel);
		middlePanel.add(resultPanel);

		playerPanel.setBackground(FELT_GREEN);
		playerPanel.setOpaque(true);
		bottomPanel.add(playerPanel);

		statsPanel.add(playerBankPanel);

		int counter = 0;
		while (counter < buttonNames.length) {
			for (BlackjackButton button : buttonMap.values()) {
				if (button.getOrder() == counter) {
					statsPanel.add(button);
					counter++;
				}
			}
		}
		statsPanel.setBackground(FELT_GREEN);

		bottomPanel.add(statsPanel);

		this.add(topPanel);
		this.add(middlePanel);
		this.add(bottomPanel);
	}

	public boolean getDealButtonPressed() {
		return this.buttonMap.get("Deal").getPressed();
	}

	public void setDealButtonPressed(boolean dealButtonPressed) {
		this.buttonMap.get("Deal").setPressed(dealButtonPressed);
	}

	public boolean getBetButtonPressed() {
		return this.buttonMap.get("Bet").getPressed();
	}

	public void setBetButtonPressed(boolean betButtonPressed) {
		this.buttonMap.get("Bet").setPressed(betButtonPressed);
	}

	public boolean getHandDecisionPressed() {
		for (String buttonName : buttonNames) {
			if (!buttonName.equals("Bet") && !buttonName.equals("Deal")) {
				if (this.buttonMap.get(buttonName).getPressed()) {
					return true;
				}
			}
		}
		return false;
	}

	public void unpressAllDecisions() {
		for (String buttonName : buttonNames) {
			if (!buttonName.equals("Bet") && !buttonName.equals("Deal")) {
				this.buttonMap.get(buttonName).setPressed(false);
			}
		}
	}

	public void disableBetAndDealAndEnableDecisions(boolean splitEnabled) {
		for (BlackjackButton button : buttonMap.values()) {
			if (button.getButtonName().equals("Bet") || button.getButtonName().equals("Deal")) {
				button.setEnabled(false);
			} else {
				if (button.getButtonName().equals("Split")) {
					button.setEnabled(splitEnabled);
				} else {
					button.setEnabled(true);
				}
			}
		}
	}

	public void disableBetButton() {
		buttonMap.get("Bet").setEnabled(false);
	}

	public void enableHitStandAndBasicStrategyOnly() {
		for (BlackjackButton button : buttonMap.values()) {
			if (button.getButtonName().equals("Hit") || button.getButtonName().equals("Stand")
					|| button.getButtonName().equals("Basic Strategy")) {
				button.setEnabled(true);
			} else {
				button.setEnabled(false);
			}
		}
	}

	public void enableBetButtonOnly() {
		for (BlackjackButton button : buttonMap.values()) {
			if (button.getButtonName().equals("Bet")) {
				button.setEnabled(true);
			} else {
				button.setEnabled(false);
			}
		}
	}

	public void enableDealButton() {
		buttonMap.get("Deal").setEnabled(true);
	}

}
