package service;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import domain.Card;
import domain.Hand;
import domain.Round;
import domain.Series;
import domain.Shoe;
import panels.BlackjackPanel;
import panels.CardPanel;
import panels.HandPanel;
import panels.SeriesPanel;

public class BlackjackGame extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final boolean LOG_ENABLED = true;
	private static final boolean INFO_ENABLED = true;
	private static final boolean BUTTON_CONTROLLED = true;

	private static final int DEAL_SPEED = 50;
	private static final int DECKS_IN_SHOE = 4;
	private static final int SHOE_CARD_LIMIT = 20;
	private static final int PLAYER_BANKROLL_LIMIT = 10;
	private static final int TOTAL_ROUNDS = 10;

	private static final String GAME_TITLE = "Blackjack";

	private Double playerBet = 10.00;
	private Double playerBankroll = 200.00;

	private Shoe shoe;

	private BlackjackPanel blackjackPanel;

	public static void main(String[] args) throws InterruptedException {
		new BlackjackGame();
	}

	public BlackjackGame() throws InterruptedException {
		printWelcome();
		initializeFrame();

		for (int i = 1; i <= TOTAL_ROUNDS; i++) {
			log("Creating Shoe for Round " + i);
			shoe = new Shoe(DECKS_IN_SHOE);
			resetBlackjackPanel();
			while (shoe.getNumberOfCardsInShoe() >= SHOE_CARD_LIMIT && playerBankroll >= PLAYER_BANKROLL_LIMIT) {
				waitForBetInput();
				resetBlackjackPanel();
				blackjackPanel.disableBetButton();
				blackjackPanel.enableDealButton();
				pack();
				Double bankrollChange = playAndResolveNewRound();
				playerBankroll += bankrollChange;
				blackjackPanel.updateResultPanel(bankrollChange);
				if (bankrollChange < 0) {
					blackjackPanel.updatePlayerBetPanel(0.00);
				}
				blackjackPanel.enableBetButtonOnly();
				pack();
			}
		}
		info("Player Bankroll at end of session: " + playerBankroll);
	}

	private void resetBlackjackPanel() {
		getContentPane().removeAll();
		blackjackPanel = new BlackjackPanel();
		add(blackjackPanel);
		blackjackPanel.updatePlayerBankPanel(playerBankroll);
		pack();
	}

	private Double playAndResolveNewRound() throws InterruptedException {
		blackjackPanel.updatePlayerBankPanel(playerBankroll - playerBet);
		blackjackPanel.updatePlayerBetPanel(playerBet);

		pack();

		CardPanel playerCard1Panel = new CardPanel();
		CardPanel playerCard2Panel = new CardPanel();
		CardPanel dealerUpCardPanel = new CardPanel();
		CardPanel dealerHoleCardPanel = new CardPanel();

		HandPanel playerHandPanel = new HandPanel(playerCard1Panel, playerCard2Panel);

		SeriesPanel playerSeriesPanel = new SeriesPanel(playerHandPanel);

		HandPanel dealerHandPanel = new HandPanel(dealerUpCardPanel, dealerHoleCardPanel);

		blackjackPanel.addDealerHandPanel(dealerHandPanel);
		blackjackPanel.addPlayerSeriesPanel(playerSeriesPanel);

		waitForDealInput();

		Card playerCard1 = shoe.drawCard();
		// Card playerCard1 = new Card(8);
		Card dealerUpCard = shoe.drawCard();
		// Card dealerUpCard = new Card(10);
		Card playerCard2 = shoe.drawCard();
		// Card playerCard2 = new Card(8);
		Card dealerHoleCard = shoe.drawCard();
		// Card dealerHoleCard = new Card(11);

		Hand playerHand = new Hand(playerCard1, playerCard2, playerBet);

		pack();

		playerCard1Panel.setCard(playerCard1);
		dealDelay();
		dealerUpCardPanel.setCard(dealerUpCard);
		dealDelay();
		playerCard2Panel.setCard(playerCard2);
		dealDelay();

		Round round = new Round(new Series(playerHand), new Hand(dealerUpCard, dealerHoleCard), playerSeriesPanel,
				dealerHandPanel, new CardPanel(dealerHoleCard), dealerHoleCardPanel, blackjackPanel, this, shoe,
				playerBankroll);
		blackjackPanel.disableBetAndDealAndEnableDecisions(playerHand.isSplittable());
		return round.play();
	}

	private void log(String string) {
		if (LOG_ENABLED) {
			System.out.println(string);
		}
	}

	private void info(String string) {
		if (INFO_ENABLED) {
			System.out.println(string);
		}
	}

	public void waitForBetInput() throws InterruptedException {
		while (!blackjackPanel.getBetButtonPressed()) {
			Thread.sleep(1);
		}
		blackjackPanel.setBetButtonPressed(false);
	}

	public void waitForDealInput() throws InterruptedException {
		while (!blackjackPanel.getDealButtonPressed()) {
			Thread.sleep(1);
		}
		blackjackPanel.setDealButtonPressed(false);
	}

	public void waitForHandDecision() throws InterruptedException {
		while (!blackjackPanel.getHandDecisionPressed()) {
			Thread.sleep(1);
		}
		blackjackPanel.unpressAllDecisions();
	}

	public void dealDelay() throws InterruptedException {
		Thread.sleep(DEAL_SPEED);
	}

	private void printWelcome() {
		info("Welcome to Blackjack.");
	}

	private void initializeFrame() {
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		setTitle(GAME_TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

}
