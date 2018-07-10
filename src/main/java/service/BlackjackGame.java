package service;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import domain.*;
import panels.BlackjackPanel;
import panels.CardPanel;
import panels.HandPanel;
import panels.SeriesPanel;

public class BlackjackGame extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final boolean LOG_ENABLED = true;
	private static final boolean INFO_ENABLED = true;
	private static final boolean BUTTON_CONTROLLED = true;

	private static final int DEAL_SPEED = 10;
	private static final int DECKS_IN_SHOE = 1;
	private static final int SHOE_CARD_LIMIT = 10;
	private static final int PLAYER_BANKROLL_LIMIT = 10;
	private static final int TOTAL_ROUNDS = 20;

	private static final String GAME_TITLE = "Blackjack";
	private static final String[] WELCOME_MESSAGES = { "Welcome to Blackjack.", "Blackjacks Pay 3:2",
			"Dealer Stands on All 17s", "Re-splitting Aces is not allowed" };

	private static final Color FELT_COLOR = new Color(0, 100, 0);

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
		int totalRounds = TOTAL_ROUNDS;

		for (int i = 1; i <= totalRounds; i++) {
			log("Creating Shoe for Round " + i);
			shoe = new Shoe(DECKS_IN_SHOE);
			while (shoe.getNumberOfCardsInShoe() >= SHOE_CARD_LIMIT && playerBankroll >= PLAYER_BANKROLL_LIMIT) {
				Double bankrollChange = playAndResolveNewRound();
				playerBankroll += bankrollChange;
				blackjackPanel.updateResultPanel(bankrollChange);
				if (bankrollChange < 0) {
					blackjackPanel.updatePlayerBetPanel(0.00);
				}
				packAndWait();
			}
		}
	}

	public void packAndWait() throws InterruptedException {
		pack();
		blackjackWait();
	}

	private void printWelcome() {
		for (String str : WELCOME_MESSAGES) {
			info(str);
		}
	}

	private void initializeFrame() {
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		setTitle(GAME_TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private Double playAndResolveNewRound() throws InterruptedException {
		initializeNewBlackjackPanel();

		Card playerCard1 = shoe.drawCard();
		Card dealerUpCard = shoe.drawCard();
		Card playerCard2 = shoe.drawCard();
		Card dealerHoleCard = shoe.drawCard();

		Hand playerHand = new Hand(playerCard1, playerCard2, playerBet);

		CardPanel playerCard1Panel = new CardPanel(playerCard1);
		CardPanel playerCard2Panel = new CardPanel(playerCard2);
		CardPanel dealerUpCardPanel = new CardPanel(dealerUpCard);
		CardPanel dealerHoleCardPanelFaceDown = new CardPanel();

		HandPanel playerHandPanel = new HandPanel(playerCard1Panel, playerCard2Panel);

		SeriesPanel playerSeriesPanel = new SeriesPanel(playerHandPanel);

		HandPanel dealerHandPanel = new HandPanel(dealerUpCardPanel, dealerHoleCardPanelFaceDown);

		blackjackPanel.updatePlayerBetPanel(playerHand.getBet());
		blackjackPanel.getDealerPanel().add(dealerHandPanel);
		blackjackPanel.getPlayerPanel().add(playerSeriesPanel);

		playerCard1Panel.setVisible(false);
		playerCard2Panel.setVisible(false);
		dealerUpCardPanel.setVisible(false);
		dealerHoleCardPanelFaceDown.setVisible(false);

		packAndWait();

		playerCard1Panel.setVisible(true);
		blackjackWait();
		dealerUpCardPanel.setVisible(true);
		blackjackWait();
		playerCard2Panel.setVisible(true);
		blackjackWait();
		dealerHoleCardPanelFaceDown.setVisible(true);
		blackjackWait();

		Round round = new Round(new Series(playerHand), new Hand(dealerUpCard, dealerHoleCard), playerSeriesPanel,
				dealerHandPanel, new CardPanel(dealerHoleCard), dealerHoleCardPanelFaceDown, blackjackPanel, this, shoe,
				playerBankroll);
		return round.play();
	}

	private void initializeNewBlackjackPanel() {
		getContentPane().removeAll();
		blackjackPanel = new BlackjackPanel();
		blackjackPanel.updateBankrollPanel(playerBankroll - playerBet);
		add(blackjackPanel);
//		setBackground(FELT_COLOR);
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

	private void blackjackWait() throws InterruptedException {
		if (BUTTON_CONTROLLED) {
			while (!blackjackPanel.getContinueGame()) {
				Thread.sleep(1);
			}
			blackjackPanel.setContinueGame(false);
		} else {
			Thread.sleep(DEAL_SPEED);
		}
	}

}
