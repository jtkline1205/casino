package domain;

import panels.BlackjackPanel;
import panels.CardPanel;
import panels.HandPanel;
import panels.SeriesPanel;
import service.BlackjackGame;

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
	}

	public Double play() throws InterruptedException {
		log("----------------New Round----------------");
		Hand playerFirstHand = playerSeries.getHands().get(0);

		log("Player's Starting Hand: ");
		log(playerFirstHand.toString());

		boolean roundOver = false;
		boolean holeCardRevealed = false;

		// Phase 1: Check Blackjacks
		if (playerFirstHand.isBlackjack()) {
			log("Player's hand is a Blackjack!");
			roundOver = true;
			if (!dealerHand.isBlackjack()) {
				// Blackjack pays 3:2
				double winnings = playerFirstHand.getBet() * 1.5;
				log("Player wins " + winnings + " on the blackjack.");
				dealerHandPanel.remove(dealerHoleCardPanelFaceDown);
				dealerHandPanel.add(dealerHoleCardPanelFaceUp);
				holeCardRevealed = true;
				return winnings;
			}
		}

		if (dealerHand.isBlackjack()) {
			log("Dealer has a Blackjack, the round is over.");
			roundOver = true;
			if (!playerFirstHand.isBlackjack()) {
				double winnings = playerFirstHand.getBet() * -1;
				log("Player lost " + (winnings * -1) + " due to dealer blackjack.");
				dealerHandPanel.remove(dealerHoleCardPanelFaceDown);
				dealerHandPanel.add(dealerHoleCardPanelFaceUp);
				holeCardRevealed = true;
				return winnings;
			}
		}

		Boolean dealerMustResolve = false;
		// Phase 2: Player Round
		if (!roundOver) {
			log("Running player Series.");
			dealerMustResolve = runPlayerSeries(playerSeries, dealerHand.getCards().get(0), playerSeriesPanel);
		}

		// Phase 3: Dealer Round
		if (!roundOver) {
			if (dealerMustResolve) {
				log("Running Dealer hand.");
				dealerHandPanel.remove(dealerHoleCardPanelFaceDown);
				dealerHandPanel.add(dealerHoleCardPanelFaceUp);
				holeCardRevealed = true;
				runDealerRound(dealerHand, dealerHandPanel);
			}
		}

		// Phase 4: Analyze Player Hands
		Double winnings = 0.0;
		if (!roundOver) {
			if (!holeCardRevealed) {
				dealerHandPanel.remove(dealerHoleCardPanelFaceDown);
				dealerHandPanel.add(dealerHoleCardPanelFaceUp);
			}
			for (Hand hand : playerSeries.getHands()) {
				if (hand.isBust()) {
					log(hand + " LOST by busting and lost " + hand.getBet());
					winnings -= hand.getBet();
				} else {
					if (dealerHand.isBust()) {
						log(hand + " WON due to dealer bust and won " + hand.getBet());
						winnings += hand.getBet();
					} else {
						if (hand.calculateValue() > dealerHand.calculateValue()) {
							log(hand + " WON by beating the dealer's hand and won " + hand.getBet());
							winnings += hand.getBet();
						} else if (hand.calculateValue() < dealerHand.calculateValue()) {
							log(hand + " LOST by losing to the dealer's hand and lost " + hand.getBet());
							winnings -= hand.getBet();
						} else if (hand.calculateValue() == dealerHand.calculateValue()) {
							log(hand + " PUSHED by tying the dealer's hand");
							winnings += 0.0;
						}
					}
				}
			}
		}
		return winnings;
	}

	private Boolean runPlayerHitsOn16AndUnder(Hand playerHand, HandPanel playerHandPanel) throws InterruptedException {
		// ~40.93% win rate
		while (playerShouldHit16OrUnder(playerHand)) {
			drawCardAndUpdatePlayerHandPanel(playerHand, playerHandPanel);
			if (playerHand.isBust()) {
				return false;
			}
		}
		return true;
	}

	private Boolean runPlayerHitsOn16AndUnderUnlessWeakDealer(Hand playerHand, HandPanel playerHandPanel, Card dealerUpCard)
			throws InterruptedException {
		// ~43.25% win rate
		 while (playerShouldHit16OrUnderUnlessWeakDealer(playerHand, dealerUpCard)) {
			 drawCardAndUpdatePlayerHandPanel(playerHand, playerHandPanel);
			 if (playerHand.isBust()) {
			 	return false;
			 }
		 }
		 return true;
	}

	private boolean playerShouldHit16OrUnder(Hand playerHand) {
		return playerHand.calculateValue() < 17;
	}

	private boolean playerShouldHit16OrUnderUnlessWeakDealer(Hand playerHand, Card dealerUpCard) {
		if (dealerUpCard.getRank().getValue() < 7) {
			if (playerHand.calculateValue() < 12) {
				return true;
			} else if (playerHand.calculateValue() == 12) {
				return (dealerUpCard.getRank().getValue() == 2 || dealerUpCard.getRank().getValue() == 3);
			}
			return false;
		} else {
			return (playerHand.calculateValue() < 17);
		}
	}

	private void drawCardAndUpdatePlayerHandPanel(Hand playerHand, HandPanel playerHandPanel) throws InterruptedException {
		log("Drawing card for Player.");
		Card newCard = shoe.drawCard();
		playerHand.addCard(newCard);
		playerHandPanel.add(new CardPanel(newCard));
		parent.packAndWait();
	}

	private Boolean runPlayerSeries(Series playerSeries, Card dealerUpCard, SeriesPanel playerSeriesPanel)
			throws InterruptedException {
		Hand playerFirstHand = playerSeries.getHands().get(0);
		HandPanel playerFirstHandPanel = playerSeriesPanel.getHandPanels().get(0);

		// return runPlayerHitsOn16AndUnder(playerFirstHand, playerFirstHandPanel);

		// return runPlayerHitsOn16AndUnderUnlessWeakDealer(playerFirstHand, playerFirstHandPanel, dealerUpCard);

		// Basic Strategy Iteration 1
		// ~42.30% win rate

		return runPlayerHand(playerFirstHand, playerFirstHandPanel, playerSeries, playerSeriesPanel, dealerUpCard);
	}

	private Boolean runPlayerHand(Hand playerHand, HandPanel playerHandPanel, Series playerSeries,
			SeriesPanel playerSeriesPanel, Card dealerUpCard) throws InterruptedException {
		Decision decision = blackjackPanel.getLatestDecision();
		if (decision == null) {
			decision = playerHand.getBasicStrategyDecision(dealerUpCard);
		}
		log(playerHand + " decision: " + decision.getName());
		if (decision == Decision.STAND) {
			return true;
		} else if (decision == Decision.DOUBLE) {
			blackjackPanel.updatePlayerBetPanel(playerSeries.getTotalBet() + playerHand.getBet());
			playerHand.doubleBet();
			Card card = shoe.drawCard();
			playerHand.addCard(card);
			playerHandPanel.add(new CardPanel(card));
			blackjackPanel.updatePlayerBankPanel(playerBankroll - playerSeries.getTotalBet());
			parent.packAndWait();
			return true;
		} else if (decision == Decision.SPLIT) {
			// Remove spltting Hand from Series
			playerSeries.getHands().remove(playerHand);

			// Remove splitting HandPanel from SeriesPanel
			playerSeriesPanel.removeHandPanel(playerHandPanel);
			playerSeriesPanel.remove(playerHandPanel);

			// Create child Hand 1 and add to Series
			Card newCard1 = shoe.drawCard();
			Hand newHand1 = new Hand(playerHand.getCards().get(0), newCard1, playerHand.getBet());
			playerSeries.addHand(newHand1);

			// Create child HandPanel 1 and add to SeriesPanel
			CardPanel newCardPanel1 = new CardPanel(playerHand.getCards().get(0));
			CardPanel newCardPanel2 = new CardPanel(newCard1);
			HandPanel newHandPanel1 = new HandPanel();
			newHandPanel1.add(newCardPanel1);
			newHandPanel1.add(newCardPanel2);
			playerSeriesPanel.add(newHandPanel1);
			playerSeriesPanel.addHandPanel(newHandPanel1);

			// Create child Hand 2 and add to Series
			Hand newHand2 = new Hand(playerHand.getCards().get(1), playerHand.getBet());
			playerSeries.addHand(newHand2);

			// Create child HandPanel 2 and add to SeriesPanel
			CardPanel newCardPanel3 = new CardPanel(playerHand.getCards().get(1));
			HandPanel newHandPanel2 = new HandPanel();
			newHandPanel2.add(newCardPanel3);
			playerSeriesPanel.add(newHandPanel2);
			playerSeriesPanel.addHandPanel(newHandPanel2);

			blackjackPanel.updatePlayerBetPanel(playerSeries.getTotalBet());
			blackjackPanel.updatePlayerBankPanel(playerBankroll - playerSeries.getTotalBet());

			// Repaint
			parent.packAndWait();

			// Run child Hand 1 if first card was not an Ace
			Boolean hand1NeedsResolution = true;
			if (!newHand1.getCards().get(0).getRank().equals(Rank.ACE)) {
				hand1NeedsResolution = runPlayerHand(newHand1, newHandPanel1, playerSeries, playerSeriesPanel,
						dealerUpCard);
			}

			// Draw card for child Hand 2
			Card newCard2 = shoe.drawCard();
			newHand2.addCard(newCard2);
			CardPanel newCardPanel4 = new CardPanel(newCard2);
			newHandPanel2.add(newCardPanel4);

			// Repaint
			parent.packAndWait();

			// Run child Hand 2 if first card was not an Ace
			Boolean hand2NeedsResolution = true;
			if (!newHand2.getCards().get(0).getRank().equals(Rank.ACE)) {
				hand2NeedsResolution = runPlayerHand(newHand2, newHandPanel2, playerSeries, playerSeriesPanel,
						dealerUpCard);
			}

			return hand1NeedsResolution || hand2NeedsResolution;
		} else {
			while (decision == Decision.HIT) {
				log(playerHand + " decision: " + decision.getName());
				Card card = shoe.drawCard();
				playerHand.addCard(card);
				playerHandPanel.add(new CardPanel(card));
				parent.packAndWait();
				if (playerHand.isBust()) {
					return false;
				}
				decision = playerHand.getBasicStrategyDecision(dealerUpCard);
			}
			return true;
		}
	}

	private void runDealerRound(Hand dealerHand, HandPanel dealerHandPanel) throws InterruptedException {
		while (dealerHand.calculateValue() < 17) {
			Card card = shoe.drawCard();
			dealerHand.addCard(card);
			dealerHandPanel.add(new CardPanel(card));
			parent.packAndWait();
		}
		log("Dealer ended with this hand: " + dealerHand);
	}

	private void log(String string) {
		System.out.println(string);
	}

}
