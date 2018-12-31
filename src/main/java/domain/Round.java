package domain;

import panels.BlackjackPanel;
import panels.CardPanel;
import panels.HandPanel;
import panels.SeriesPanel;
import service.BlackjackGame;
import service.DecisionService;

public class Round {

	private Series playerSeries;
	private Hand dealerHand;
	private SeriesPanel playerSeriesPanel;
	private HandPanel dealerHandPanel;
	private CardPanel dealerHoleCardPanelFaceUp;
	private CardPanel dealerHoleCardPanelFaceDown;
	private BlackjackPanel blackjackPanel;
	private BlackjackGame parent;
	private Shoe shoe;
	private Double playerBankroll;
	private DecisionService decisionService;

	public Round(Series playerSeries, Hand dealerHand, SeriesPanel playerSeriesPanel, HandPanel dealerHandPanel,
			CardPanel dealerHoleCardPanelFaceUp, CardPanel dealerHoleCardPanelFaceDown, BlackjackPanel blackjackPanel,
			BlackjackGame parent, Shoe shoe, Double playerBankroll) {
		this.playerSeries = playerSeries;
		this.dealerHand = dealerHand;
		this.playerSeriesPanel = playerSeriesPanel;
		this.dealerHandPanel = dealerHandPanel;
		this.dealerHoleCardPanelFaceUp = dealerHoleCardPanelFaceUp;
		this.dealerHoleCardPanelFaceDown = dealerHoleCardPanelFaceDown;
		this.blackjackPanel = blackjackPanel;
		this.parent = parent;
		this.shoe = shoe;
		this.playerBankroll = playerBankroll;
		this.decisionService = new DecisionService();
	}

	public Double play() throws InterruptedException {
		log("----------------New Round----------------");
		Hand playerFirstHand = playerSeries.getFirstHand();

		log("Player's Starting Hand: " + playerFirstHand);

		if (playerFirstHand.isBlackjack()) {
			log("Player has Blackjack!");
			if (!dealerHand.isBlackjack()) {
				// Blackjack pays 3:2
				double winnings = playerFirstHand.getBet() * 1.5;
				log("Player wins " + winnings + " on the blackjack.");
				dealerHandPanel.remove(dealerHoleCardPanelFaceDown);
				dealerHandPanel.add(dealerHoleCardPanelFaceUp);
				return winnings;
			} else {
				// Blackjack pays even money against Dealer Blackjack (insurance
				// not implemented)
				double winnings = playerFirstHand.getBet();
				log("Player wins " + winnings + " on the blackjack against dealer blackjack.");
				dealerHandPanel.remove(dealerHoleCardPanelFaceDown);
				dealerHandPanel.add(dealerHoleCardPanelFaceUp);
				return winnings;
			}
		}

		if (dealerHand.isBlackjack()) {
			log("Dealer has Blackjack and the round is over.");
			double winnings = playerFirstHand.getBet() * -1;
			log("Player lost " + (winnings * -1) + " due to dealer blackjack.");
			dealerHandPanel.remove(dealerHoleCardPanelFaceDown);
			dealerHandPanel.add(dealerHoleCardPanelFaceUp);
			return winnings;
		}

		Boolean dealerMustResolve = false;
		log("Running player's first hand.");
		HandPanel playerFirstHandPanel = playerSeriesPanel.getFirstHandPanel();
		dealerMustResolve = runHandAndCheckDealerResolve(playerFirstHand, playerFirstHandPanel,
				dealerHand.getFirstCard());

		dealerHandPanel.remove(dealerHoleCardPanelFaceDown);
		dealerHandPanel.add(dealerHoleCardPanelFaceUp);

		if (dealerMustResolve) {
			log("Running Dealer hand.");
			runDealerRound(dealerHand, dealerHandPanel);
		}

		return calculateWinnings();
	}

	private Double calculateWinnings() {
		Double winnings = 0.0;
		for (Hand hand : playerSeries.getHands()) {
			if (hand.isBust()) {
				log(hand + " LOST by player bust and lost " + hand.getBet());
				winnings -= hand.getBet();
			} else {
				if (dealerHand.isBust()) {
					log(hand + " WON by dealer bust and won " + hand.getBet());
					winnings += hand.getBet();
				} else {
					if (hand.calculateValue() > dealerHand.calculateValue()) {
						log(hand + " WON by beating dealer and won " + hand.getBet());
						winnings += hand.getBet();
					} else if (hand.calculateValue() < dealerHand.calculateValue()) {
						log(hand + " LOST to dealer and lost " + hand.getBet());
						winnings -= hand.getBet();
					} else if (hand.calculateValue() == dealerHand.calculateValue()) {
						log(hand + " PUSHED by tying dealer");
						winnings += 0.0;
					}
				}
			}
		}
		return winnings;
	}

	private Boolean runHandAndCheckDealerResolve(Hand playerHand, HandPanel playerHandPanel, Card dealerUpCard)
			throws InterruptedException {
		parent.waitForHandDecision();
		Decision latestDecision = blackjackPanel.getLatestDecision();
		if (latestDecision == Decision.BASIC_STRATEGY) {
			// Basic Strategy Iteration 1
			// ~42.30% win rate
			log("Basic Strategy Decision chosen.");
			latestDecision = decisionService.getBasicStrategyDecision(playerHand, dealerUpCard);
		}
		log(playerHand + " Decision (Hit/Stand/Double/Split): " + latestDecision.getName());
		if (latestDecision == Decision.STAND) {
			return true;
		} else if (latestDecision == Decision.DOUBLE) {
			return doubleDownAndCheckDealerResolve(playerHand, playerHandPanel);
		} else if (latestDecision == Decision.SPLIT) {
			return splitAndCheckDealerResolve(playerHand, playerHandPanel, dealerUpCard);
		} else {
			return hitAndCheckDealerResolve(playerHand, playerHandPanel, dealerUpCard, latestDecision);
		}
	}

	private boolean hitAndCheckDealerResolve(Hand playerHand, HandPanel playerHandPanel, Card dealerUpCard,
			Decision latestDecision) throws InterruptedException {
		while (latestDecision == Decision.HIT) {
			Card card = shoe.drawCard();
			playerHand.addCard(card);
			playerHandPanel.add(new CardPanel(card));
			parent.pack();
			if (playerHand.isBust()) {
				return false;
			}
			blackjackPanel.enableHitStandAndBasicStrategyOnly();
			parent.waitForHandDecision();
			latestDecision = blackjackPanel.getLatestDecision();
			if (latestDecision == null) {
				log("Basic Strategy Decision chosen.");
				latestDecision = decisionService.getBasicStrategyDecision(playerHand, dealerUpCard);
			}
			log(playerHand + " decision (Hit/Stand): " + latestDecision.getName());
			if (latestDecision == Decision.STAND) {
				return true;
			}
		}
		return true;
	}

	private boolean doubleDownAndCheckDealerResolve(Hand playerHand, HandPanel playerHandPanel)
			throws InterruptedException {
		blackjackPanel.updatePlayerBetPanel(playerSeries.getTotalBet() + playerHand.getBet());
		playerHand.doubleBet();
		Card card = shoe.drawCard();
		playerHand.addCard(card);
		playerHandPanel.add(new CardPanel(card));
		blackjackPanel.updatePlayerBankPanel(playerBankroll - playerSeries.getTotalBet());
		parent.pack();
		if (playerHand.isBust()) {
			return false;
		}
		return true;
	}

	private boolean splitAndCheckDealerResolve(Hand playerHand, HandPanel playerHandPanel, Card dealerUpCard)
			throws InterruptedException {
		blackjackPanel.disableBetAndDealAndEnableDecisions(playerHand.getBet() * 2 <= playerBankroll, false);
		playerSeries.removeHand(playerHand);
		playerSeriesPanel.removeHandPanel(playerHandPanel);

		Card newCard1 = shoe.drawCard();

		Hand newHand1 = new Hand(playerHand.getFirstCard(), newCard1, playerHand.getBet());
		playerSeries.addHand(newHand1);
		HandPanel newHandPanel1 = new HandPanel(new CardPanel(playerHand.getFirstCard()), new CardPanel(newCard1));
		playerSeriesPanel.addHandPanel(newHandPanel1);

		Hand newHand2 = new Hand(playerHand.getSecondCard(), playerHand.getBet());
		playerSeries.addHand(newHand2);
		HandPanel newHandPanel2 = new HandPanel(new CardPanel(playerHand.getSecondCard()));
		playerSeriesPanel.addHandPanel(newHandPanel2);

		blackjackPanel.updatePlayerBetPanel(playerSeries.getTotalBet());
		blackjackPanel.updatePlayerBankPanel(playerBankroll - playerSeries.getTotalBet());

		parent.pack();

		Boolean hand1NeedsResolution = true;
		if (!newHand1.getFirstCard().getRank().equals(Rank.ACE)) {
			hand1NeedsResolution = runHandAndCheckDealerResolve(newHand1, newHandPanel1, dealerUpCard);
		}

		Card newCard2 = shoe.drawCard();
		newHand2.addCard(newCard2);
		newHandPanel2.add(new CardPanel(newCard2));

		parent.pack();

		Boolean hand2NeedsResolution = true;
		if (!newHand2.getFirstCard().getRank().equals(Rank.ACE)) {
			hand2NeedsResolution = runHandAndCheckDealerResolve(newHand2, newHandPanel2, dealerUpCard);
		}

		return hand1NeedsResolution || hand2NeedsResolution;
	}

	private void runDealerRound(Hand dealerHand, HandPanel dealerHandPanel) throws InterruptedException {
		while (dealerHand.calculateValue() < 17) {
			Card card = shoe.drawCard();
			dealerHand.addCard(card);
			dealerHandPanel.add(new CardPanel(card));
			parent.pack();
			parent.dealDelay();
		}
		log("Dealer ended with: " + dealerHand);
	}

	private void log(String string) {
		System.out.println(string);
	}

}
