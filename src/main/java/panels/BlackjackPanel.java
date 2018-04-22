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

	private boolean continueGame = false;

	public BlackjackPanel() {
		initializeComponents();
		setupTitleLabel1();
		setupTitleLabel2();
		continueButton.addActionListener(this);
		setLayouts();
		setPreferredSizes();
		addComponents();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
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
		statsPanel.remove(playerBetPanel);
		Stack newStack = new Stack(bankroll);
		this.bankrollPanel = new StackPanel(newStack);
		statsPanel.add(bankrollPanel);
		statsPanel.add(playerBetPanel);
	}

	public void updatePlayerBetPanel(Double playerBet) {
		statsPanel.remove(playerBetPanel);
		Stack newStack = new Stack(playerBet);
		this.playerBetPanel = new StackPanel(newStack);
		statsPanel.add(playerBetPanel);
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
		this.resultPanel.remove(this.resultStackPanel);
		this.resultStackPanel = new StackPanel(resultStack);
		this.resultPanel.add(this.resultStackPanel);
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

		resultPanel.setBackground(new Color(0, 100, 0));
		resultStackPanel.setBackground(new Color(0, 100, 0));
		middlePanel.setBackground(new Color(100, 0, 0));
		statsPanel.setBackground(new Color(0, 100, 0));

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
		topPanel.setLayout(new GridLayout(1, 2));
		dealerPanel.setLayout(new GridLayout(1, 1));
		shoePanel.setLayout(new GridLayout(1, 2));
		middlePanel.setLayout(new GridLayout(2, 1));
		resultPanel.setLayout(new GridLayout(1, 2));
		bottomPanel.setLayout(new GridLayout(1, 2));
		playerPanel.setLayout(new GridLayout(1, 1));
		statsPanel.setLayout(new GridLayout(1, 3));
	}

	private void setPreferredSizes() {
		this.setPreferredSize(new Dimension(800, 600));
		topPanel.setPreferredSize(new Dimension(800, 275));
		middlePanel.setPreferredSize(new Dimension(800, 50));
		bottomPanel.setPreferredSize(new Dimension(800, 275));
	}

	private void addComponents() {
		shoePanel.add(new CardPanel());
		resultPanel.add(resultLabel);
		resultPanel.add(resultStackPanel);
		shoePanel.add(resultPanel);
		statsPanel.add(continueButton);
		statsPanel.add(bankrollPanel);
		statsPanel.add(playerBetPanel);
		topPanel.add(dealerPanel);
		topPanel.add(shoePanel);
		middlePanel.add(titleLabel1);
		middlePanel.add(titleLabel2);
		bottomPanel.add(playerPanel);
		bottomPanel.add(statsPanel);
		this.add(topPanel);
		this.add(middlePanel);
		this.add(bottomPanel);
	}

}
