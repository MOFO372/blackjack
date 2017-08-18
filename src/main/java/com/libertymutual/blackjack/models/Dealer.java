package com.libertymutual.blackjack.models;

public class Dealer {

	private Hand hand; 
	
	public Dealer() {
		hand = new Hand(); 
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
	}
	
}
