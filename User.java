///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  AmazonStore.java
// File:             User.java
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
import java.util.Random;
import java.io.PrintStream;

/**
 * The User class uses DLinkedList to build a price ordered list called 'wishlist' of products 
 * Products with higher Price fields should come earlier in the list.
 */
public class User {
	//Random number generator, used for generateStock. DO NOT CHANGE
	private static Random randGen = new Random(1234);

	private String username;
	private String passwd;
	private int credit;
	private ListADT<Product> wishList;


	/**
	 * Constructs a User instance with a name, password, credit and an empty wishlist. 
	 * 
	 * @param username name of user
	 * @param passwd password of user
	 * @param credit amount of credit the user had in $ 
	 */
	public User(String username, String passwd, int credit){
		this.username =username;
		this.passwd = passwd;
		this.credit = credit;
		wishList = new DLinkedList<Product>();
	}

	/**
	 * Checks if login for this user is correct
	 * @param username the name to check
	 * @param passwd the password to check
	 * @return true if credentials correct, false otherwise
	 */
	public boolean checkLogin(String username, String passwd){
		if (username.equals(this.username) && passwd.equals(this.passwd))
			return true;
		return false;
	}

	/**
	 * Adds a product to the user's wishlist. 
	 * Maintain the order of the wishlist from highest priced to lowest priced products.
	 * @param product the Product to add
	 */
	public void addToWishList(Product product){

		int currPos = 0;
		 
		// Invalid input exception
		if(product == null)
			throw new IllegalArgumentException();
		//Special case: Empty List
		if(wishList.isEmpty()){
			wishList.add(product);
		}
		//General case: 
		else{
		    
		while(currPos < wishList.size() ){
			//check the price of product is greater than the curr, if so, add 
			// the product at currPos, ohter wise move the currPos to next position
			if(product.getPrice()>= wishList.get(currPos).getPrice()){
				
				wishList.add(currPos, product);
			break;
			}
			else{
				currPos++;
			}
		}
		// if the currPos move to the rear of the list, it means the price is the lowest
		// of the whole list, Accordingly, add the product to the end of the list
		if(currPos==wishList.size() ){
			wishList.add(product);
		
		}
		}
		
	}

	/**
	 * Removes a product from the user's wishlist. 
	 * Do not charge the user for the price of this product
	 * @param productName the name of the product to remove
	 * @return the product on success, null if no such product found
	 */
	public Product removeFromWishList(String productName){
		int currPos = 0;
		while(currPos < wishList.size() && !wishList.get(currPos).getName().equals(productName)){

			currPos++;
		}
		if(currPos==wishList.size())
			return null;
		else
			return wishList.remove(currPos);

	}

	/**
	 * Print each product in the user's wishlist in its own line using the PrintStream object passed in the argument
	 * @param printStream The printstream object on which to print out the wishlist
	 */
	public void printWishList(PrintStream printStream){
		int currPos = 0;
		while(currPos < wishList.size()){
			printStream.println(wishList.get(currPos));
			currPos++;
		}

	}

	/**
	 * Buys the specified product in the user's wishlist.
	 * Charge the user according to the price of the product by updating the credit
	 * Remove the product from the wishlist as well
	 * Throws an InsufficientCreditException if the price of the product is greater than the credit available.
	 * 
	 * @param productName name of the product
	 * @return true if successfully bought, false if product not found 
	 * @throws InsufficientCreditException if price > credit 
	 */
	public boolean buy(String productName) throws InsufficientCreditException{
		int currPos = 0;
		
		while(currPos < wishList.size() && !wishList.get(currPos).getName().equals(productName)){
			currPos++;
		}
		
		if(currPos==wishList.size()){
			return false;
		}
		if (this.credit < wishList.get(currPos).getPrice()){
			throw new InsufficientCreditException(productName);
		}
		else {
			credit = credit - wishList.get(currPos).getPrice();
			System.out.println("buy: "+wishList.get(currPos).getName());
			wishList.remove(currPos);
			return true;
		}
	}


	/** 
	 * Returns the credit of the user
	 * @return the credit
	 */
	public int getCredit(){
		return credit;
	}

	/**
	 * This method is already implemented for you. Do not change.
	 * Declare the first N items in the currentUser's wishlist to be in stock
	 * N is generated randomly between 0 and size of the wishlist
	 * 
	 * @returns list of products in stock 
	 */
	public ListADT<Product> generateStock() {
		ListADT<Product> inStock= new DLinkedList<Product>();

		int size=wishList.size();
		if(size==0)
			return inStock;

		int n=randGen.nextInt(size+1);//N items in stock where n>=0 and n<size
		//pick first n items from wishList
		for(int ndx=0; ndx<n; ndx++)
			inStock.add(wishList.get(ndx));

		return inStock;
	}

}
