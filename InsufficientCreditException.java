///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  AmazonStore.java
// File:             InsufficentCreditExcrption.java
// Semester:         CS367 Spring 2015
//
// Author:           Richard Chen(Siyu) schen358@wisc.edu
// CS Login:         chens
// Lecturer's Name:  Jim Skrentny
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
// Pair Partner:     Zixin Chi
// Email:            zchi4@wisc.edu
// CS Login:         zixin
// Lecturer's Name:  Jim Skrentny
//
//////////////////// STUDENTS WHO GET HELP FROM OTHER THAN THEIR PARTNER //////
//                   fully acknowledge and credit all sources of help,
//                   other than Instructors and TAs.
//
// Persons:          Identify persons by name, relationship to you, and email.
//                   Describe in detail the the ideas and help they provided.
//
// Online sources:   avoid web searches to solve your problems, but if you do
//                   search, be sure to include Web URLs and description of 
//                   of any information you find.
//////////////////////////// 80 columns wide //////////////////////////////////
public class InsufficientCreditException extends Exception {
	public InsufficientCreditException(String name){
		super("Insufficient funds for "+ name);
	}
}
