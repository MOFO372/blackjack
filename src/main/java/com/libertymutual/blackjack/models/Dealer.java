package com.libertymutual.blackjack.models;

public class Dealer {

	private Hand hand; 
	private Hand displayCard;
	
	public Dealer() {
		hand = new Hand(); 
		displayCard = new Hand(); 
	}

	public void giveCard(Card cardToDeal) {
		hand.addCard(cardToDeal);
		
	}
	
	public Hand gethHand() {
		return hand; 
	}

	public void finishHittingHand(Deck deck) {
		
		int [] values = hand.getValues(); 
		
		while (values[0] < 17 || values[1] < 17) {
			Card theNextCard = deck.getCard(); 
			hand.addCard(theNextCard);
			values = hand.getValues(); 
		}
		displayCard = hand; 
	}
	
	public Hand getDisplayCard() {
		return displayCard; 
	}
	
	public void updateDisplayCard(Card cardToDeal) {
		displayCard.addCard(cardToDeal);
	}
	
//	public boolean isBust() {
//		int[] values = hand.getValues();
//		return values[0] > 21 && values[1] > 21;
//	}
	
}
