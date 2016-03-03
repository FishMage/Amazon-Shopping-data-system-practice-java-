
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;


public class AmazonStore {

	//Store record of users and products
	private static ListADT<Product> products = new DLinkedList<Product>();
	private static ListADT<User> users = new DLinkedList<User>();
	private static User currentUser = null;//current user logged in
	private static PrintStream printStream = new PrintStream(System.out);
	private static ListADT<String> category = new DLinkedList<String>();
	private static int currPos;
	//scanner for console input
	public static final Scanner stdin= new Scanner(System.in);


	//main method
	public static void main(String args[]) {


		//Populate the two lists using the input files: Products.txt User1.txt User2.txt ... UserN.txt
		if (args.length < 2) {
			System.out.println("Usage: java AmazonStore [PRODUCT_FILE] [USER1_FILE] [USER2_FILE] ...");
			System.exit(0);
		}

		//load store products
		loadProducts(args[0]);
		//System.out.println("the size of the products: "+ products.size());

		//load users one file at a time
		for(int i=1; i<args.length; i++)
			loadUser(args[i]);


		//User Input for login
		boolean done = false;
		//System.out.println("123");
		while (!done) 
		{
			System.out.print("Enter username : ");
			String username = stdin.nextLine();
			System.out.print("Enter password : ");
			String passwd = stdin.nextLine();

			if(login(username, passwd)!=null)
			{
				//generate random items in stock based on this user's wish list
				ListADT<Product> inStock=currentUser.generateStock();
				//show user menu
				userMenu(inStock);
			}
			else
				System.out.println("Incorrect username or password");

			System.out.println("Enter 'exit' to exit program or anything else to go back to login");
			if(stdin.nextLine().equals("exit"))
				done = true;
		}

	}

	/**
	 * Tries to login for the given credentials. Updates the currentUser if successful login
	 * 
	 * @param username name of user
	 * @param passwd password of user
	 * @returns the currentUser 
	 */
	public static User login(String username, String passwd){
		for(int currPos =0; currPos < users.size(); currPos ++){
			if(users.get(currPos).checkLogin(username, passwd)){
				currentUser =  users.get(currPos);
				return currentUser;
			}
		}
		return null; 
	}

	/**
	 * Reads the specified file to create and load products into the store.
	 * Every line in the file has the format: <NAME>#<CATEGORY>#<PRICE>#<RATING>
	 * Create new products based on the attributes specified in each line and insert them into the products list
	 * Order of products list should be the same as the products in the file
	 * For any problem in reading the file print: 'Error: Cannot access file'
	 * 
	 * @param fileName name of the file to read
	 */
	public static void loadProducts(String fileName){
		File file = new File(fileName);
		try {
			Scanner scnr = new Scanner(file);
			while (scnr.hasNextLine()){

				String [] info = scnr.nextLine().split("#");
				Product product = new Product(info[0],info[1],
						Integer.parseInt(info[2]),Float.parseFloat(info[3]));
				products.add(product);

			}
		} catch (FileNotFoundException e) {
			System.out.println("Error: Cannot access file");
		}
	}

	/**
	 * Reads the specified file to create and load a user into the store.
	 * The first line in the file has the format:<NAME>#<PASSWORD>#<CREDIT>
	 * Every other line after that is a name of a product in the user's wishlist, format:<NAME>
	 * For any problem in reading the file print: 'Error: Cannot access file'
	 * 
	 * @param fileName name of the file to read
	 */

	public static void loadUser(String fileName){

		try {
			File file = new File(fileName);
			Scanner scnr = new Scanner(file);
			String firstLine = scnr.nextLine();
			String [] info = firstLine.split("#");
			// scan the first line that contains the userName and Pwd info
			User user = new User(info[0],info[1],Integer.parseInt(info[2]));	
			users.add(user);
			// the wishlist info of the User
			while(scnr.hasNextLine()){
				String productName = scnr.nextLine();
				//System.out.println(productName);
				for(int i = 0; i< products.size(); i++){
					if(productName.equals(products.get(i).getName())){
						user.addToWishList(products.get(i));

					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error: Cannot access file");
		}
	}

	/**
	 * See sample outputs
	 * Prints the entire store inventory formatted by category
	 * The input text file for products is already grouped by category, use the same order as given in the text file 
	 * format:
	 * <CATEGORY1>
	 * <NAME> [Price:$<PRICE> Rating:<RATING> stars]
	 * ...
	 * <NAME> [Price:$<PRICE> Rating:<RATING> stars]
	 * 
	 * <CATEGORY2>
	 * <NAME> [Price:$<PRICE> Rating:<RATING> stars]
	 * ...
	 * <NAME> [Price:$<PRICE> Rating:<RATING> stars]
	 */
	public static void printByCategory(){

		//add all categories into the list without any duplicate categories
		int pos = 0; 

		// get all categories that the user has
		for(pos = 0; pos < products.size(); pos++){
			if (pos == 0 ){
				category.add(products.get(0).getCategory()); 
			}
			else {
				if(!category.contains(products.get(pos).getCategory())){
					category.add(products.get(pos).getCategory());
				}
			}
		}
		// loop to print all product which in the category
		for(int currPos = 0; currPos < category.size(); currPos ++){
			System.out.println();
			System.out.println(category.get(currPos) + ":");
			for( pos = 0 ; pos < products.size(); pos++ ){
				if( products.get(pos).getCategory().equals(category.get(currPos))){
					System.out.println(products.get(pos));
				}
			}
		}	
	}


	/**
	 * Interacts with the user by processing commands
	 * 
	 * @param inStock list of products that are in stock
	 */
	public static void userMenu(ListADT<Product> inStock){

		boolean done = false;
		while (!done) 
		{
			System.out.print("Enter option : ");
			String input = stdin.nextLine();

			//only do something if the user enters at least one character
			if (input.length() > 0) 
			{
				String[] commands = input.split(":");//split on colon, because names have spaces in them
				if(commands[0].length()>1)
				{
					System.out.println("Invalid Command");
					continue;
				}
				switch(commands[0].charAt(0)){
				case 'v':
					if(commands[1].equals("all")){

						printByCategory();
						System.out.println();
					}
					else if(commands[1].equals("wishlist")){
						currentUser.printWishList(printStream);
					}
					else if(commands[1].equals("instock")){

						for(int currPos = 0; currPos <inStock.size(); currPos++){
							System.out.println(inStock.get(currPos));
						}
					}
					break;

				case 's':
					for(int currPos = 0; currPos < products.size(); currPos ++ ){
						if(products.get(currPos).getName().contains(commands[1])){
							System.out.println(products.get(currPos));
						}
					}
					break;

				case 'a':
					boolean isFound = false;
					for(int currPos = 0; currPos < products.size(); currPos ++ ){
						if(products.get(currPos).getName().equals(commands[1])){
							isFound = true;
							currentUser.addToWishList(products.get(currPos));
						}
					}
					if(!isFound){
						System.out.println("Product not found");
					}
					break;

				case 'r':
					if(currentUser.removeFromWishList(commands[1]) ==null){
						System.out.println("Product not found");
					}
					break;

				case 'b':
					
					try {
						
						for( currPos = 0; currPos < inStock.size(); currPos ++ ){
							boolean s = currentUser.buy(inStock.get(currPos).getName());
							//System.out.println(s + " "+inStock.get(currPos).getName());
							System.out.println("Bought " +inStock.get(currPos).getName());
						} 
					}catch (InsufficientCreditException e) {
							System.out.println("Insufficient funds for "
									+ inStock.get(currPos) );
						}
					
					break;

				case 'c':
					System.out.println("$"+currentUser.getCredit());
					break;

				case 'l':
					done = true;
					System.out.println("Logged Out");
					break;

				default:  //a command with no argument
					System.out.println("Invalid Command");
					break;
				}
			}
		}
	}

}
