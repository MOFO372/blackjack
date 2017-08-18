package com.libertymutual.blackjack.controllers;

public interface Card {
	
	String getSuit(); 
	
	String getVisualRepresentation(); 
	
	int[] getValues(); 

}
