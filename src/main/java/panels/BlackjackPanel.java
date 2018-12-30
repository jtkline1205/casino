package panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
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

	private JButton betButton;
	private JButton dealButton;
	private JButton basicStrategyButton;
	private JButton hitButton;
	private JButton standButton;
	private JButton splitButton;
	private JButton doubleButton;

	private Decision latestDecision;

	private boolean dealButtonPressed = false;
	private boolean betButtonPressed = false;
	private boolean handDecisionPressed = false;

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
		addActionListeners();
		setLayouts();
		setPreferredSizes();
		addComponents();
	}

	public Decision getLatestDecision() {
		return this.latestDecision;
	}

	private void addActionListeners() {
		betButton.addActionListener(this);
		dealButton.addActionListener(this);
		basicStrategyButton.addActionListener(this);
		hitButton.addActionListener(this);
		standButton.addActionListener(this);
		splitButton.addActionListener(this);
		doubleButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		switch (((JButton) ae.getSource()).getText()) {
		case "Bet":
			betButtonPressed = true;
			break;
		case "Deal":
			dealButtonPressed = true;
			break;
		case "Basic Strategy":
			latestDecision = null;
			handDecisionPressed = true;
			break;
		case "Hit":
			latestDecision = Decision.HIT;
			handDecisionPressed = true;
			break;
		case "Stand":
			latestDecision = Decision.STAND;
			handDecisionPressed = true;
			break;
		case "Split":
			latestDecision = Decision.SPLIT;
			handDecisionPressed = true;
			break;
		case "Double":
			latestDecision = Decision.DOUBLE;
			handDecisionPressed = true;
			break;
		}
	}

	public JPanel getDealerPanel() {
		return dealerPanel;
	}

	public PlayerPanel getPlayerPanel() {
		return playerPanel;
	}

	// public boolean getContinueGame() {
	// return continueGame;
	// }
	//
	// public void setContinueGame(boolean continueGame) {
	// this.continueGame = continueGame;
	// }

	public void updatePlayerBankPanel(Double bankroll) {
		statsPanel.remove(playerBankPanel);
		Stack newStack = new Stack(bankroll);
		playerBankPanel = new StackPanel(newStack, FELT_GREEN);
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
		Stack resultStack = null;
		resultPanel.remove(houseBankPanel);
		resultPanel.remove(playerActiveChipsPanel);
		playerActiveChipsPanel.remove(playerBetPanel);
		playerActiveChipsPanel.remove(playerWinningsPanel);

		if (doubleValue < 0) {
			resultStack = new Stack(-1 * doubleValue);
			houseBankPanel = new StackPanel(resultStack, PURPLE);
		} else if (doubleValue > 0) {
			resultStack = new Stack(doubleValue);
			playerWinningsPanel = new StackPanel(resultStack, GOLD);
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
		betButton = new JButton("Bet");
		dealButton = new JButton("Deal");
		basicStrategyButton = new JButton("Basic Strategy");
		hitButton = new JButton("Hit");
		standButton = new JButton("Stand");
		splitButton = new JButton("Split");
		doubleButton = new JButton("Double");

		dealButton.setEnabled(false);
		basicStrategyButton.setEnabled(false);
		hitButton.setEnabled(false);
		standButton.setEnabled(false);
		splitButton.setEnabled(false);
		doubleButton.setEnabled(false);

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
		statsPanel.add(betButton);
		statsPanel.add(dealButton);
		statsPanel.add(basicStrategyButton);
		statsPanel.add(hitButton);
		statsPanel.add(standButton);
		statsPanel.add(splitButton);
		statsPanel.add(doubleButton);
		statsPanel.setBackground(FELT_GREEN);

		bottomPanel.add(statsPanel);

		this.add(topPanel);
		this.add(middlePanel);
		this.add(bottomPanel);
	}

	public boolean getDealButtonPressed() {
		return dealButtonPressed;
	}

	public void setDealButtonPressed(boolean dealButtonPressed) {
		this.dealButtonPressed = dealButtonPressed;
	}

	public boolean getBetButtonPressed() {
		return betButtonPressed;
	}

	public void setBetButtonPressed(boolean betButtonPressed) {
		this.betButtonPressed = betButtonPressed;
	}

	public boolean getHandDecisionPressed() {
		return handDecisionPressed;
	}

	public void setHandDecisionPressed(boolean handDecisionPressed) {
		this.handDecisionPressed = handDecisionPressed;
	}

	public void disableBetAndDealAndEnableDecisions(boolean splitEnabled) {
		betButton.setEnabled(false);
		dealButton.setEnabled(false);
		basicStrategyButton.setEnabled(true);
		hitButton.setEnabled(true);
		standButton.setEnabled(true);
		splitButton.setEnabled(splitEnabled);
		doubleButton.setEnabled(true);
	}

	public void disableBetButton() {
		betButton.setEnabled(false);
	}

	public void enableHitStandAndBasicStrategyOnly() {
		hitButton.setEnabled(true);
		standButton.setEnabled(true);
		basicStrategyButton.setEnabled(true);

		betButton.setEnabled(false);
		dealButton.setEnabled(false);
		splitButton.setEnabled(false);
		doubleButton.setEnabled(false);
	}

	public void enableBetButtonOnly() {
		betButton.setEnabled(true);
		dealButton.setEnabled(false);
		basicStrategyButton.setEnabled(false);
		hitButton.setEnabled(false);
		standButton.setEnabled(false);
		splitButton.setEnabled(false);
		doubleButton.setEnabled(false);
	}

	public void enableDealButton() {
		dealButton.setEnabled(true);
	}

}
