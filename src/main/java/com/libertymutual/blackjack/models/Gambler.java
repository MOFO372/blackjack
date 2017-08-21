package com.libertymutual.blackjack.models;

public class Gambler {
	
	private Hand hand; 
	private double wallet; 

	public Gambler() {
		hand = new Hand(); 
		wallet = 50; 
	}

	public void giveCard(Card cardToDeal) {
		hand.addCard(cardToDeal);
		
	}

	public Hand gethHand() {
			
		return hand; 
	}
	
	public double getWalletValue() {
		return wallet; 
	}
	
	public double placeBet(double betValue) {
		wallet -= betValue;
		return wallet; 
	}
	
	public double setWallet(double setWallet) {
		wallet = setWallet; 
		return wallet; 
	}
	
	public void getNewHand() {
		hand = new Hand();
	}

}
