import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

public class GuenthersMarketApp {
	
	/* Map for tracking the grocery list
	 * Three Arrays:
	 * - itemNames will add items to the checkout list
	 * - itemPrices will add price to item list 
	 * - getName takes add names, and capitalize them*/
	
	private static Map<String, Double> items = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
	private static List<String>itemNames = new ArrayList<>();
	private static List<Double>itemPrices = new ArrayList<>();
	private static List<String>getName = new ArrayList<>();
	private static Scanner scan;
	
	public static void main(String[] args) {
		scan = new Scanner(System.in);
		
		//Glabal vars
		double itemPriceInput = 0.0;
		String itemNameInput = "";
		String userContinue;
		String nameCap = "";
		mapOfItems();
		printMenu();
		boolean valid = true;
		
		/* Welcome message and instructions
		 * name collection */
		System.out.println("");
		System.out.println("Welcome to Guether's Market! You can enter your the item's name.");
		System.out.println("What is your first name? ");
		nameCap = scan.nextLine();
		System.out.println("");
		
		
		//.contain
		//System.out.format("%-25s $%s%n", key, val);
		  
		do {
			  try {
			    mapOfItems();
				printMenu();
				System.out.println("");
				/*The start of taking the order*/
				System.out.println("What would you like to order " + getUserNameToCap(nameCap) + "?");
				itemNameInput = scan.nextLine();           //take user input
				itemPriceInput = items.get(itemNameInput); //get item price via item name
				itemNames.add(itemNameInput);              //add input to itemName arrayList
				itemPrices.add(itemPriceInput);            //add itemPrice to arrayList
				//Output 
				System.out.println("Adding " + getUserNameToCap(itemNameInput) + " to your cart at $" + itemPriceInput);
				System.out.println("");
			  }catch(NullPointerException ex) {
				  System.out.println("That item does not exist. ");
			  }
				
			
			
			//Ask user if they want to order more.
			System.out.println("Would you like to order anything else (y/n)?");
			userContinue=scan.nextLine();
			
			 if(userContinue.equalsIgnoreCase("n")){
				 
				 System.out.println("");
				 System.out.println("Are you all set " + getUserNameToCap(nameCap) + "?");
				 System.out.println("Thank you for your business!");
				 System.out.println("Here's what you got: ");
				 System.out.println("======================");
				 for(int i = 0; i < itemNames.size(); i++ ) {
					 for(int j = 0; j < itemPrices.size(); j++ ) {
						 if(i == j) {
							 System.out.println(itemNames.get(i) + "\t" + "\t" + "$" + itemPrices.get(j));
						 }
					 }
					 
				   }
				 System.out.println("");
				 double getPrice = calculatePrice(itemPrices);
				 System.out.println( "Total cost of your order is " + formatDoublePrice(getPrice));
				 System.out.println("");
				 double getTheAvg = calculateAverage(itemPrices);
				 System.out.println("Average price per item in order was $" + formatDoublePrice(getTheAvg));
				 double itemPricesMost = findMostExpensive();
				 System.out.println("The highest price is " + formatDoublePrice(itemPricesMost));
				 double itemPricesless = findLeastExpensive();
				  System.out.println("The lowest price is " + formatDoublePrice(itemPricesless));         
				 }
				 
				 System.out.println("Thank you for shopping with us!");
				 			  
		}while(userContinue.equalsIgnoreCase("y"));
		scan.close();
		}
		
	  
	
	//Map input for grocery items
	public static void mapOfItems() {
		items.put("Salmon", 16.07);
		items.put("Spinach", 2.55);
		items.put("Bag of Rice", 5.07);
		items.put("Grapes", 3.01);
		items.put("Lamb Chops", 15.05);
		items.put("Garlic", 1.55);
		items.put("Bag of Lemon", 1.75);
		items.put("Butter", 4.08);
	}
	
	//Print the grocery items
	public static void printMenu() {
		System.out.println( " Item" + "\t" + "\t " + " Price" ); 
		System.out.println("========================");
		
		for (Map.Entry<String,Double> entry : items.entrySet()) 
            System.out.println(" " + entry.getKey() +
                             "," + "\t  $" + entry.getValue());
	}
	
	//Capitalize the letter of the first name
	public static String getUserNameToCap(String userName) {
		String output = "";
		getName.add(userName);
		if (userName.length() > 1) {
			output += Character.toUpperCase(userName.charAt(0));
			output += userName.substring(1).toLowerCase();
		}else {
			output = userName.toUpperCase();
		}
		System.out.println("Cool " + output + "!");
		
		return output ;
	}
	
	//Calculate the average
	public static double calculateAverage(List<Double> itemPrices2 ) {
		double avg = 0.0;
		for(int i = 0; i < itemPrices2.size(); i++) {
			avg += itemPrices2.get(i);
		}
		double calculateAverage = avg / itemPrices2.size(); 
		return calculateAverage;
		
	}
	
	//Calculate the total
	public static double calculatePrice(List<Double> itemPrices3 ) {
		double price = 0.0;
		for(int i = 0; i < itemPrices3.size(); i++) {
			price += itemPrices3.get(i);
		}
		return price + itemPrices3.size(); 
		//return calculatePrice;
		
	}
	
	
	//Calculate the highest
	public static int findMostExpensive() {
		int mostExpensive = -1;
		double highestPrice = -1.0;
		for(int i = 0; i < itemPrices.size(); i++) {
			if(itemPrices.get(i) > highestPrice) {
				mostExpensive = i;
				highestPrice = itemPrices.get(i);
			}
		}
		
		return (int) highestPrice;
	}
	
	
	//Calculate the lowest
	public static int findLeastExpensive() {
		int leastExpensive = -1;
		double lowestPrice = Double.MAX_VALUE;
		for(int i = 0; i < itemPrices.size(); i++) {
			if(itemPrices.get(i) < lowestPrice) {
				leastExpensive = i;
				lowestPrice = itemPrices.get(i);
			}
		}
		
		return (int) lowestPrice;
	}
	
	//format price string into double with two decimals
		private static String formatDoublePrice(double price) {
			return String.format("%,.2f", price);
		}
	

}
