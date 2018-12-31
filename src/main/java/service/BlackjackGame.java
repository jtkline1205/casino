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

	private static final int DEAL_SPEED = 1000;
	private static final int DECKS_IN_SHOE = 6;
	private static final int SHOE_CARD_LIMIT = 20;
	private static final int PLAYER_BANKROLL_LIMIT = 10;
	private static final int TOTAL_ROUNDS = 1;

	private static final String GAME_TITLE = "Blackjack";

	private Double playerBet = 0.00;
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
			resetBlackjackPanel(false);
			while (shoe.getNumberOfCardsInShoe() >= SHOE_CARD_LIMIT && playerBankroll >= PLAYER_BANKROLL_LIMIT) {
				playerBet = 0.0;
				blackjackPanel.enableChipButtons(playerBankroll);
				waitForBetInput();
				resetBlackjackPanel(true);
				Double bankrollChange = playAndResolveNewRound();
				playerBankroll += bankrollChange;
				blackjackPanel.updateResultPanel(bankrollChange);
				pack();
			}
		}
		info("Player Bankroll at end of session: " + playerBankroll);
	}

	private void resetBlackjackPanel(boolean afterBet) {
		getContentPane().removeAll();
		blackjackPanel = new BlackjackPanel();
		add(blackjackPanel);
		blackjackPanel.updatePlayerBankPanel(playerBankroll);
		blackjackPanel.updatePlayerBetPanel(playerBet);
		if (afterBet) {
			blackjackPanel.disableBetButton();
			blackjackPanel.disableChipButtons();
		} else {
			blackjackPanel.enableChipButtons(playerBankroll);
		}
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

		boolean canAffordToDouble = playerBet * 2 <= playerBankroll;

		Round round = new Round(new Series(playerHand), new Hand(dealerUpCard, dealerHoleCard), playerSeriesPanel,
				dealerHandPanel, new CardPanel(dealerHoleCard), dealerHoleCardPanel, blackjackPanel, this, shoe,
				playerBankroll);
		blackjackPanel.disableBetAndDealAndEnableDecisions(canAffordToDouble, playerHand.isSplittable());
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
			if (blackjackPanel.getLatestIncrement() != null) {
				playerBet += blackjackPanel.getLatestIncrement().getValue();
				blackjackPanel.resetLatestIncrement();
				blackjackPanel.updatePlayerBankPanel(playerBankroll - playerBet);
				blackjackPanel.enableChipButtons(playerBankroll - playerBet);
				blackjackPanel.updatePlayerBetPanel(playerBet);
				blackjackPanel.clearPlayerWinningsPanel();
				blackjackPanel.clearHouseBankPanel();
				pack();
			}
		}
		blackjackPanel.setBetButtonPressed(false);
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
