package com.revature.controller;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.beans.Breed;
import com.revature.beans.Person;
import com.revature.beans.Role;
import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.services.CatService;
import com.revature.services.CatServiceImpl;
import com.revature.services.PersonService;
import com.revature.services.PersonServiceImpl;

public class CatAppController {
	private static Logger log = Logger.getLogger(CatAppController.class);
	private static PersonService personServ = new PersonServiceImpl();
	private static CatService catServ = new CatServiceImpl();
	private static Scanner scan;

	public static void main(String[] args) {
		scan = new Scanner(System.in);
		boolean userActive = true;
		
		mainLoop: while (userActive) {
			System.out.println("Hello! Welcome to CatApp!");
			Person loggedInUser = null;
			
			while (loggedInUser == null) {
				System.out.println("What would you like to do?");
				System.out.println("1. Register\n" 
								+ "2. Log in\n"
								+ "Other. Exit");
				String userChoice = scan.nextLine();
				
				switch (userChoice) {
				case "1":
					log.info("User is registering an account.");
					loggedInUser = registerUser();
					break;
				case "2":
					log.info("User is logging in.");
					loggedInUser = logInUser();
					break;
				default:
					log.info("User is exiting the application.");
					userActive = false;
					break mainLoop;
				}
			}
			
			menuLoop: while (true) {
				System.out.println("What would you like to do?");
				System.out.println("1. View available cats\n2. View my cats");
				if ("User".equals(loggedInUser.getRole().getName()))
					System.out.println("Other. Log out");
				else if ("Employee".equals(loggedInUser.getRole().getName()))
					System.out.println("3. Manage cats\n4. Manage users\nOther. Log out");
				
				String input = scan.nextLine();
				switch (input) {
				case "1":
					// TODO view available cats
					loggedInUser = viewAvailableCats(loggedInUser);
					break;
				case "2":
					// TODO view my cats
					viewUserCats(loggedInUser);
					break;
				case "3":
					// TODO manage cats (only if employee)
					loggedInUser = manageCats(loggedInUser);
					break;
				case "4":
					// TODO manage users (only if employee)
					loggedInUser = manageUsers(loggedInUser);
					break;
				default:
					System.out.println("See you next time!");
					log.info("User logged out.");
					loggedInUser = null;
					break menuLoop;
				}
				if (loggedInUser == null) {
					System.out.println("See you next time!");
					log.info("User logged out.");
					break menuLoop;
				}
			}
		}
		scan.close();
	}
	
	// returns the person that registered.
	private static Person registerUser() {
		while (true) {
			Person newAccount = new Person();
			System.out.println("Enter a username: ");
			newAccount.setUsername(scan.nextLine());
			System.out.println("Enter a password: ");
			newAccount.setPassword(scan.nextLine());
			Role userRole = new Role();
			// TODO get this from the database
			newAccount.setRole(userRole);
			
			System.out.println("Does this look good?");
			System.out.println("Username: " + newAccount.getUsername() + 
					" Password: " + newAccount.getPassword());
			System.out.println("1 to confirm, 2 to start over, other to cancel");
			String input = scan.nextLine();
			switch (input) {
			case "1":
				log.debug("Submitting new user to the database...");
				try {
					newAccount.setId(personServ.addPerson(newAccount));
					log.debug(newAccount);
					System.out.println("Confirmed. Welcome to CatApp.");
					return newAccount;
				} catch (NonUniqueUsernameException e) {
					System.out.println("Sorry, that username is taken. Try again!");
					log.warn("User tried to register with a non-unique username.");
				}
				break;
			case "2":
				System.out.println("Okay, let's try again.");
				break;
			default:
				System.out.println("Okay, let's go back.");
				return null;
			}
		}
	}
	
	// returns the person who logs in
	private static Person logInUser() {
		while (true) {
			System.out.println("Enter username: ");
			String username = scan.nextLine();
			System.out.println("Enter password: ");
			String password = scan.nextLine();
			
			Person user = personServ.getPersonByUsername(username);
			if (user == null) {
				log.debug("User entered a username that doesn't exist.");
				System.out.println("Nobody exists with that username.");
			} else if (user.getPassword().equals(password)) {
				log.debug("User logged in successfully.");
				log.debug(user);
				System.out.println("Welcome back!");
				return user;
			} else {
				log.debug("User entered an incorrect password.");
				System.out.println("That password is incorrect.");
			}
			System.out.println("Do you want to try again? 1 for yes, other for no.");
			String input = scan.nextLine();
			if (!("1".equals(input)))
				return null;
		}
	}
	
	// returns an updated person in case they decide to adopt a cat
	private static Person viewAvailableCats(Person loggedInUser) {
		// TODO implement:
		// show the user the available cats
		// ask if they want to adopt any cats
		// if they do, they can choose one and adopt it
		// if not, go back to the main menu
		return null;
	}
	
	private static void viewUserCats(Person loggedInUser) {
		// TODO implement:
		// show the user their cats
		// give them the option to pet their cats
		// once they're done, return to the main menu
	}
	
	// returns null if the user is not an employee so that it will log them out
	private static Person manageCats(Person loggedInUser) {
		// TODO implement:
		// show menu to either add a cat or edit a cat or cancel
		// when adding a cat, they choose name, age, breed
		System.out.println("Choose a breed by entering its ID:"
				+ "\n1. Persian\n2. Domestic Shorthair\n3. Calico\nother. Other");
		Breed breed = new Breed();
		breed.setId(Integer.valueOf(scan.nextLine()));
		switch(breed.getId()) {
		case 1:
			breed.setName("Persian");
			break;
		case 2:
			breed.setName("Domestic Shorthair");
			break;
		case 3:
			breed.setName("Calico");
			break;
		default:
			breed.setName("Other");
			break;
		}
		// new cats have the status set to id: 1 and name: Available
		// updating a cat just allows you to change the name and/or age and then save changes
		return null;
	}
	
	// returns null if the user is not an employee so that it will log them out
	private static Person manageUsers(Person loggedInUser) {
		// TODO implement:
		// show menu to remove or add users or cancel
		// removing can remove by ID or username
		// adding gives you the option to set them as user or employee
		return null;
	}

}
