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
	private JLabel resultLabel;
	private StackPanel resultStackPanel;

	private JPanel middlePanel;
	private JLabel titleLabel1;
	private JLabel titleLabel2;

	private JPanel bottomPanel;
	private PlayerPanel playerPanel;
	private JPanel statsPanel;
	private StackPanel bankrollPanel;
	private StackPanel playerBetPanel;

	private JButton continueButton;
	private JButton hitButton;
	private JButton standButton;
	private JButton splitButton;
	private JButton doubleButton;

	private Decision latestDecision;

	private boolean continueGame = false;

	public BlackjackPanel() {
		initializeComponents();
		setupTitleLabel1();
		setupTitleLabel2();
		continueButton.addActionListener(this);
		hitButton.addActionListener(this);
		standButton.addActionListener(this);
		splitButton.addActionListener(this);
		doubleButton.addActionListener(this);
		setLayouts();
		setPreferredSizes();
		addComponents();
	}

	public Decision getLatestDecision() {
		return this.latestDecision;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String buttonText = ((JButton)ae.getSource()).getText();
		if (buttonText.equals("Continue")) {
			latestDecision = null;
		} else if (buttonText.equals("Hit")) {
			latestDecision = Decision.HIT;
		} else if (buttonText.equals("Stand")) {
			latestDecision = Decision.STAND;
		} else if (buttonText.equals("Split")) {
			latestDecision = Decision.SPLIT;
		} else if (buttonText.equals("Double")) {
			latestDecision = Decision.DOUBLE;
		}
		continueGame = true;
	}

	public JPanel getTopPanel() {
		return topPanel;
	}

	public JPanel getBottomPanel() {
		return bottomPanel;
	}

	public JPanel getDealerPanel() {
		return dealerPanel;
	}

	public PlayerPanel getPlayerPanel() {
		return playerPanel;
	}

	public JPanel getStatsPanel() {
		return statsPanel;
	}

	public boolean getContinueGame() {
		return continueGame;
	}

	public void setContinueGame(boolean continueGame) {
		this.continueGame = continueGame;
	}

	public void updateBankrollPanel(Double bankroll) {
		statsPanel.remove(bankrollPanel);
		Stack newStack = new Stack(bankroll);
		bankrollPanel = new StackPanel(newStack, new Color(0, 100, 0));
		statsPanel.add(bankrollPanel);
	}

	public void updatePlayerBetPanel(Double playerBet) {
		resultPanel.remove(resultStackPanel);
		resultPanel.remove(playerBetPanel);

		playerBetPanel = new StackPanel(new Stack(playerBet), new Color(182, 182, 29));
		playerBetPanel.setBackground(new Color(182, 182, 29));

		resultPanel.add(resultStackPanel);
		resultPanel.add(playerBetPanel);
	}

	public void updateResultPanel(Double doubleValue) {
		Stack resultStack = null;
		if (doubleValue < 0) {
			this.resultLabel.setText("LOST");
			resultStack = new Stack(-1 * doubleValue);
		} else if (doubleValue > 0) {
			this.resultLabel.setText("WON");
			resultStack = new Stack(doubleValue);
		} else if (doubleValue == 0) {
			this.resultLabel.setText("PUSH");
			resultStack = new Stack(doubleValue);
		}
		resultPanel.remove(resultStackPanel);
		resultPanel.remove(playerBetPanel);

		resultStackPanel = new StackPanel(resultStack, new Color(177, 82, 182));
		resultStackPanel.setBackground(new Color(177, 82, 182));

		resultPanel.add(resultStackPanel);
		resultPanel.add(playerBetPanel);
	}

	private void initializeComponents() {
		topPanel = new JPanel();
		dealerPanel = new JPanel();
		shoePanel = new JPanel();
		resultPanel = new JPanel();
		resultLabel = new JLabel();
		resultStackPanel = new StackPanel();
		middlePanel = new JPanel();
		titleLabel1 = new JLabel();
		titleLabel2 = new JLabel();
		bottomPanel = new JPanel();
		playerPanel = new PlayerPanel();
		statsPanel = new JPanel();
		bankrollPanel = new StackPanel();
		playerBetPanel = new StackPanel();
		continueButton = new JButton("Continue");
		hitButton = new JButton("Hit");
		standButton = new JButton("Stand");
		splitButton = new JButton("Split");
		doubleButton = new JButton("Double");

//		resultPanel.setBackground(new Color(0, 100, 0));
//		resultStackPanel.setBackground(new Color(182, 182, 29));
//		middlePanel.setBackground(new Color(100, 0, 0));
//		statsPanel.setBackground(new Color(0, 100, 0));
//		playerBetPanel.setBackground(new Color(182, 182, 29));

	}

	private void setupTitleLabel1() {
		titleLabel1.setText("BLACKJACK PAYS 3:2");
		titleLabel1.setForeground(Color.WHITE);
		titleLabel1.setFont(new Font("Serif", Font.BOLD, 15));
		titleLabel1.setHorizontalAlignment(SwingConstants.CENTER);
	}

	private void setupTitleLabel2() {
		titleLabel2.setText("Dealer must draw to 16 and stand on all 17s");
		titleLabel2.setForeground(Color.WHITE);
		titleLabel2.setFont(new Font("Serif", Font.ITALIC, 15));
		titleLabel2.setHorizontalAlignment(SwingConstants.CENTER);
	}

	private void setLayouts() {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		topPanel.setLayout(new GridLayout(1, 0));
		dealerPanel.setLayout(new GridLayout(1, 0));
		shoePanel.setLayout(new GridLayout(1, 0));

		middlePanel.setLayout(new GridLayout(3, 0));
		resultPanel.setLayout(new GridLayout(3, 0));

		bottomPanel.setLayout(new GridLayout(0, 2));
		playerPanel.setLayout(new GridLayout(1, 0));
		statsPanel.setLayout(new GridLayout(0, 1));
	}

	private void setPreferredSizes() {
		this.setPreferredSize(new Dimension(800, 700));
		topPanel.setPreferredSize(new Dimension(800, 100));
		middlePanel.setPreferredSize(new Dimension(800, 300));
		bottomPanel.setPreferredSize(new Dimension(800, 300));
	}

	private void addComponents() {
		topPanel.add(dealerPanel);
		shoePanel.add(new CardPanel());
		topPanel.add(shoePanel);

		middlePanel.add(titleLabel1);
		middlePanel.add(titleLabel2);

		resultPanel.add(resultLabel);
		resultPanel.add(resultStackPanel);
		resultPanel.add(playerBetPanel);
		middlePanel.add(resultPanel);

		bottomPanel.add(playerPanel);

		statsPanel.add(bankrollPanel);
		statsPanel.add(continueButton);
		statsPanel.add(hitButton);
		statsPanel.add(standButton);
		statsPanel.add(splitButton);
		statsPanel.add(doubleButton);

		bottomPanel.add(statsPanel);

		this.add(topPanel);
		this.add(middlePanel);
		this.add(bottomPanel);
	}

}
