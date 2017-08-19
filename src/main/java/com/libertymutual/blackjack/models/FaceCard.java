package com.libertymutual.blackjack.models;

public class FaceCard implements Card {
	
	private String visualRepresentation;
	private String suit; 
	private int value = 10; 
	
	public FaceCard(String visualRepresentation, String suit) {
		this.suit = suit; 
		this.visualRepresentation = visualRepresentation; 	
	}
	
	public String getVisualRepresentation() {
		return String.valueOf(visualRepresentation + " " + suit + " (" + value + ")");
	}
	
	public String getSuit() {
		return suit; 
	}

	public int[] getValues() {
		return new int[] {value,value}; 
	}
	
	@Override
	public String toString() {
		return this.getVisualRepresentation() + " of " + this.getSuit(); 
	}
}
