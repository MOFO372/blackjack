package com.libertymutual.blackjack.models;

public class Pot {
	
	private double potValue; 
	private boolean dealerBJ; 
	private boolean gamblerBJ; 
	private boolean dealerBust; 
	private boolean gamblerBust; 
	private int dealerHandValue = 0;
	private int gamblerHandValue = 0;
	private double lastBet = 0; 
	
	public Pot() {
		potValue = 0; 
		dealerBJ = false;
		gamblerBJ = false;
		dealerBust = false;
		gamblerBust = false;	
	}
	
	public double getPayout(Dealer dealer, Gambler gambler) {
		
		dealerHandValue = dealer.gethHand().getValues()[0] + dealer.gethHand().getValues()[1];
		gamblerHandValue = gambler.gethHand().getValues()[0] + gambler.gethHand().getValues()[1];
				
		if(dealerHandValue == 21) {
			dealerBJ = true; 
		}
		
		if(dealerHandValue > 21) {
			dealerBust = true; 
		}
		
		if(gamblerHandValue == 21) {
			gamblerBJ = true; 
			if(gamblerBJ && !dealerBJ) {
				return 3; 
			}
		}
				
		if(gamblerHandValue > 21) {
			gamblerBust = true; 
			return -1; 
		}
		
		if(gamblerHandValue <= 21 && (dealerHandValue < gamblerHandValue|| dealerBust)) {
			return 2; 
		}
	
		if(dealerHandValue <= 21 && gamblerHandValue < dealerHandValue) {
			return -1;
		}
		
		if((gamblerHandValue == dealerHandValue) || ( dealerBJ && gamblerBJ)) {
			return 0; 
		}
		return 0.0; 
	}
	
	public double getPotValue() {
		return potValue; 
	}
	
	public double placeBet(double betValue) {
		lastBet = betValue; 
		potValue += betValue; 
		return potValue; 
	}
	
	public boolean check21(Gambler gambler) { 
		
		double gamblerHandValue = gambler.gethHand().getValues()[0] + gambler.gethHand().getValues()[1]; 
		
		if(gamblerHandValue == 21) {
			return true; 
		}
		
		return false; 
	} 

	public boolean checkIfEndGame(Gambler gambler, Dealer dealer, boolean standSelected) { 
		
		double payoutFactor = getPayout(dealer, gambler);
		double amountPayout = 0; 
		boolean returnValue = false; 
		
		if(payoutFactor == 0) {
			amountPayout = lastBet; 
		}
		
		else if(payoutFactor == 2) {
			amountPayout = lastBet * 2; 
		}
		
		else if(payoutFactor == 3) {
			amountPayout = lastBet * (3/2); 
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
		
		if (returnValue == true) {
			double newWalletValue; 
			newWalletValue = gambler.getWalletValue() + amountPayout; 
			gambler.setWallet(newWalletValue); 
			
		}
		return returnValue; 
	} 
}







