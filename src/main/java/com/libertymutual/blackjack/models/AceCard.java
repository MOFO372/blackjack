package com.libertymutual.blackjack.models;

public class AceCard implements Card {

	private String suit;
	private int value1 = 1; 
	private int value11 = 11; 
	
	public AceCard(String suit) {
		this.suit = suit; 
	}
	
	@Override
	public String toString() {
		return this.getVisualRepresentation() + " of " + this.getSuit(); 
	}
	
	@Override
	public String getSuit() {
		return suit; 
	}
	
	@Override
	public String getVisualRepresentation() {
		return String.valueOf("Ace" + " " + suit + " (" + value1 + "/" + value11 + ")");
	}
	
	@Override
	public int[] getValues() {
		return new int[] {value1,value11};
	}
}
