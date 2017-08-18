package com.libertymutual.blackjack.controllers;

public class Gambler {
	
	private Hand hand; 
	
	public Gambler() {
		hand = new Hand(); 
	}

	public void giveCard(Card cardToDeal) {
		hand.addCard(cardToDeal);
		
	}


}
