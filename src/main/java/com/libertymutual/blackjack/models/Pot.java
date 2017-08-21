package com.libertymutual.blackjack.models;

public class Pot {
	
	private double potValue; 
	private boolean dealerBJ; 
	private boolean gamblerBJ; 
	private boolean dealerBust; 
	private boolean gamblerBust; 
	private int dealerHandValue = 0;
	private int gamblerHandValue = 0;
	private double payoutFactor = 0; 
	//private double lastBet = 0; 
	//private Deck deck; 
	
	public Pot() {
		potValue = 0; 
		dealerBJ = false;
		gamblerBJ = false;
		dealerBust = false;
		gamblerBust = false;	
		//Deck deck = new Deck(); 
	}
	
	public double getPayout(Dealer dealer, Gambler gambler) {
		 
		dealerHandValue = 0;
		gamblerHandValue = 0; 
		
		//gamblerHandValue = gambler.gethHand().getValues()[0] + gambler.gethHand().getValues()[1];
		
		int dealerFirstValue = dealer.gethHand().getValues()[0];
		int dealerSecondValue = dealer.gethHand().getValues()[1];
		int gamblerFirstValue = gambler.gethHand().getValues()[0];
		int gamblerSecondValue = gambler.gethHand().getValues()[1];
		
		// if dealerFirstValue == dealerSecondValue
		if(dealerFirstValue == dealerSecondValue) {
			// dealerHandValue is that value, either of them
			dealerHandValue = dealerFirstValue; 
		}
		
		// else if dealerFirstValue > dealerSecondValue && dealerFirstValue <= 21
		else if(dealerFirstValue > dealerSecondValue && dealerFirstValue <= 21) {
			// then it's dealerFirstValue
			dealerHandValue = dealerFirstValue; 
		}
		
		// else if the same thing for the second value
		else if(dealerFirstValue < dealerSecondValue && dealerSecondValue <= 21) {
			// then it's the second value
			dealerHandValue = dealerSecondValue; 
		}
		
		// otherwise, it's a bust and it doesn't matter
		else {
			// just set it to either one
			dealerHandValue = dealerSecondValue; 
		}
		
		//GAMBLER HAND
		if(gamblerFirstValue == gamblerSecondValue) {
			// dealerHandValue is that value, either of them
			gamblerHandValue = gamblerFirstValue; 
		}
		
		// else if dealerFirstValue > dealerSecondValue && dealerFirstValue <= 21
		else if(gamblerFirstValue > gamblerSecondValue && gamblerFirstValue <= 21) {
			// then it's dealerFirstValue
			gamblerHandValue = gamblerFirstValue; 
		}
		
		// else if the same thing for the second value
		else if(gamblerFirstValue < gamblerSecondValue && gamblerSecondValue <= 21) {
			// then it's the second value
			gamblerHandValue = gamblerSecondValue; 
		}
		
		// otherwise, it's a bust and it doesn't matter
		else {
			// just set it to either one
			gamblerHandValue = gamblerSecondValue; 
		}
		
			
		
		//PAYOUT FACTOR CALCULATIONS		
		if(dealerHandValue == 21) {
			dealerBJ = true; 
		}
		
		if(dealerHandValue > 21) {
			dealerBust = true; 
		}
		
		if(gamblerHandValue == 21) {
			gamblerBJ = true; 
			if(gamblerBJ && !dealerBJ) {
				payoutFactor = 3;
				return payoutFactor; 
			}
		}
				
		if(gamblerHandValue > 21) {
			//dealer.finishHittingHand(Deck deck);
			gamblerBust = true; 
			payoutFactor = -1; 
			return payoutFactor;  
		}
		
		if(gamblerHandValue <= 21 && (dealerHandValue < gamblerHandValue|| dealerBust)) {
			payoutFactor = 2; 
			return payoutFactor; 
		}
	
		if(dealerHandValue <= 21 && gamblerHandValue < dealerHandValue) {
			payoutFactor = -1;
			return payoutFactor; 
		}
		
		if((gamblerHandValue == dealerHandValue) || ( dealerBJ && gamblerBJ)) {
			payoutFactor = 0; 
			System.out.println("this is the equals test");
			return payoutFactor;  
		}
		
		return payoutFactor; 
	}
	
	public double getPotValue() {
		return potValue; 
	}
	
	public double placeBet(double betValue) {
		//lastBet = betValue; 
		potValue += betValue; 
		return potValue; 
	}
	
	public boolean check21(Gambler gambler) { 
		
		//int gamblerFirstValue = gambler.gethHand().getValues()[0];
		//int gamblerSecondValue = gambler.gethHand().getValues()[1];
		
		if(gamblerHandValue == 21) {
			return true; 
		}
		
		return false; 
	} 

	public boolean checkIfEndGame(Gambler gambler, Dealer dealer, boolean standSelected) { 
		
		double amountPayout = 0; 
		boolean returnValue = false; 
		
		getPayout(dealer, gambler);
		
		if(payoutFactor == 0) {
			//amountPayout = lastBet;
			amountPayout = potValue; 
		}
		
		else if(payoutFactor == -1) {
			amountPayout =  0;
		}
		
		else if(payoutFactor == 2) {
			//amountPayout = lastBet * 2; 
			amountPayout = potValue * 2; 
		}
		
		else if(payoutFactor == 3) {
			//amountPayout = lastBet * (3/2);
			System.out.println("test if statement"); 
			amountPayout = (potValue * 3)/2; 
		}
		
		
		if (standSelected) {
			returnValue = true; 
		}
		
		else { 
			if(gamblerBJ) {
				returnValue = true; 
			}
			
			else if(gamblerBust) {
				returnValue = true;
			}
		}
		
		//System.out.println(returnValue); 
		//System.out.println(standSelected);
		
		if (returnValue == true) {
			double newWalletValue; 
			newWalletValue = gambler.getWalletValue() + amountPayout;
			System.out.println("newWalletValue: " + newWalletValue);
			System.out.println("amountPayout: " + amountPayout);
			gambler.setWallet(newWalletValue); 
			
		}
		System.out.println(returnValue);
		return returnValue; 
	} 
}







