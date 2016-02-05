///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  AmazonStore.java
// File:             Product.java
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



/**
 * Stores the name, category, price and rating of a product
 */
public class Product {
	
	private String name;
	private String category;
	private int price;
	private float rating;
	
	/**
     * Constructs a Product with a name, category, price and rating. 
     * 
     * @param name name of product
     * @param category category of product
     * @param price price of product in $ 
     * @param rating rating of product out of 5
     */
	public Product(String name, String category, int price, float rating){
		this.name = name;
		this.category = category;
		this.price = price;
		this.rating = rating;
	}
	
	/** 
     * Returns the name of the product
     * @return the name
     */
	public String getName(){
		return this.name;
	}
	
	/** 
     * Returns the category of the product
     * @return the category
     */
	public String getCategory(){
		return this.category;
	}
	
	/** 
     * Returns the price of the product
     * @return the price
     */
	public int getPrice(){
		return this.price;
	}
	
	/** 
     * Returns the rating of the product
     * @return the rating
     */
	public float getRating(){
		return this.rating;
	}
	
	/** 
     * Returns the Product's information in the following format: <NAME> [Price:$<PRICE> Rating:<RATING> stars]
     */
	public String toString(){
		String s = "";
		s = s + this.name +" [Price:$"+this.price+" Rating:"+this.rating+" stars]";
		return s;
	}

}
