package com.revature.controller;

import java.util.Scanner;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.Breed;
import com.revature.beans.Cat;
import com.revature.beans.Person;
import com.revature.beans.Role;
import com.revature.beans.Status;
import com.revature.exceptions.CatAlreadyAdoptedException;
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
		Set<Cat> availableCats = catServ.getAvailableCats();
		
		for (Cat cat : availableCats) {
			System.out.println(cat);
		}
		
		System.out.println("Would you like to adopt a cat? 1 for yes, other for no");
		String input = scan.nextLine();
		if ("1".equals(input)) {
			while (true) {
				System.out.println("Which cat? Type its ID.");
				input = scan.nextLine();
				Cat cat = catServ.getCatById(Integer.valueOf(input));
				if (cat != null && cat.getStatus().getName().equals("Available")) {
					System.out.println(cat);
					System.out.println("You want to adopt " + cat.getName() + "? 1 for yes, other for no");
					input = scan.nextLine();
					if ("1".equals(input)) {
						try {
							catServ.adoptCat(loggedInUser, cat);
						} catch (CatAlreadyAdoptedException e) {
							// TODO this needs to be handled better
							System.out.println("Wait, this cat was already adopted...");
							break;
						}
						System.out.println("You did it! You adopted " + cat.getName() + ".");
						// get the person with their updated cat set
						loggedInUser = personServ.getPersonById(loggedInUser.getId());
						break;
					} else {
						System.out.println("Okay, did you want to adopt a different one? 1 for yes, other for no");
						input = scan.nextLine();
						if (input != "1")
							break;
					}
				} else {
					System.out.println("Sorry, that's not an available cat. Do you want to try again?"
							+ " 1 for yes, other for no");
					input = scan.nextLine();
					if (input != "1") {
						System.out.println("Okay, that's fine.");
						break;
					}
				}
			}
		} else {
			System.out.println("Okay, that's fine.");
		}
		
		return loggedInUser;
	}
	
	private static void viewUserCats(Person loggedInUser) {
		if (loggedInUser.getCats().size() > 0) {
			System.out.println("Viewing your cats: ");
			for (Cat cat : loggedInUser.getCats()) {
				System.out.println(cat);
			}
			boolean petCat = false;
			while (true) {
				System.out.print("...would you like to pet ");
				if (petCat)
					System.out.print("another ");
				System.out.println("one? 1 for yes, other for no");
				String input = scan.nextLine();
				if ("1".equals(input)) {
					System.out.println("Which one? Type its ID.");
					input = scan.nextLine();
					Cat cat = catServ.getCatById(Integer.valueOf(input));
					if (cat != null && loggedInUser.getCats().contains(cat)) {
						System.out.println("You pet " + cat.getName() + ". Purrrr");
						petCat = true;
					} else
						System.out.println("Sorry, that's not one of your cats.");
				} else {
					break;
				}
			}
		} else {
			System.out.println("You don't have any cats... yet.");
		}
	}
	
	// returns null if the user is not an employee so that it will log them out
	private static Person manageCats(Person loggedInUser) {
		if (!(loggedInUser.getRole().getName().equals("Employee")))
			return null;
		
		while (true) {
			System.out.println("Manage Cats:\n1. Add a cat\n2. Edit a cat\nother. Cancel");
			String input = scan.nextLine();
			
			if ("1".equals(input)) {
				Cat newCat = new Cat();
				System.out.println("Enter a name: ");
				newCat.setName(scan.nextLine());
				System.out.println("Enter an age: ");
				newCat.setAge(Integer.valueOf(scan.nextLine()));
				System.out.println("Choose a breed by entering its ID:"
						+ "\n1. Persian\n2. Domestic Shorthair\n3. Calico\nother. Other");
				Breed breed = new Breed();
				input = scan.nextLine();
				switch(input) {
				case "1":
					breed.setId(Integer.valueOf(input));
					breed.setName("Persian");
					break;
				case "2":
					breed.setId(Integer.valueOf(input));
					breed.setName("Domestic Shorthair");
					break;
				case "3":
					breed.setId(Integer.valueOf(input));
					breed.setName("Calico");
					break;
				default:
					breed.setName("Other");
					break;
				}
				newCat.setBreed(breed);
				Status status = new Status();
				status.setId(1);
				status.setName("Available");
				newCat.setStatus(status);
				System.out.println(newCat);
				System.out.println("Look good? 1 to confirm, other to start over");
				input = scan.nextLine();
				if ("1".equals(input)) {
					newCat.setId(catServ.addCat(newCat));
					System.out.println("You successfully added " + newCat.getName() + "!");
				}
			} else if ("2".equals(input)) {
				for (Cat cat : catServ.getAvailableCats()) {
					System.out.println(cat);
				}
				System.out.println("Which cat would you like to update? Enter its ID.");
				Cat cat = catServ.getCatById(Integer.valueOf(scan.nextLine()));
				Cat newCat = cat;
				if (cat != null) {
					System.out.println("Editing " + cat.getName());
					System.out.println("Current changes:\nName: " + newCat.getName()
							+ " Age: " + newCat.getAge());
					boolean editing = true;
					while (editing) {
						System.out.println("Edit:\n1. Name\n2. Age\n3. Save changes\nother. Cancel");
						input = scan.nextLine();
						switch (input) {
						case "1":
							System.out.println("Enter new name: ");
							cat.setName(scan.nextLine());
							break;
						case "2":
							System.out.println("Enter new age: ");
							cat.setAge(Integer.valueOf(scan.nextLine()));
							break;
						case "3":
							catServ.updateCat(newCat);
							System.out.println("You updated " + newCat.getName() + " successfully.");
						default:
							editing = false;
							break;
						}
					}
				}
			} else {
				break;
			}
		}
		
		return loggedInUser;
	}
	
	// returns null if the user is not an employee so that it will log them out
	private static Person manageUsers(Person loggedInUser) {
		// if a user is not an employee, then they meant to log out, not go to this menu
		if (!(loggedInUser.getRole().getName().equals("Employee")))
			return null;
		
		while (true) {
			System.out.println("Manage Users:\n1. Remove a user\n2. Add a user\nother. Cancel");
			String input = scan.nextLine();
			
			if ("1".equals(input)) {
				System.out.println("1. Remove by ID\n2. Remove by username\nother. Cancel");
				input = scan.nextLine();
				Person personToRemove = null;
				if ("1".equals(input)) {
					System.out.println("Enter the ID of the user you want to remove.");
					personToRemove = personServ.getPersonById(Integer.valueOf(scan.nextLine()));
				} else if ("2".equals(input)) {
					System.out.println("Enter the username of the user you want to remove.");
					personToRemove = personServ.getPersonByUsername(scan.nextLine());
				}
				if ("1".equals(input) || "2".equals(input)) {
					if (personToRemove != null) {
						System.out.println(personToRemove);
						System.out.println("Remove this user? 1 for yes, other for no");
						input = scan.nextLine();
						if ("1".equals(input)) {
							personServ.deletePerson(personToRemove);
							System.out.println("You removed "
									+ personToRemove.getUsername() + " successfully.");
						}
					} else {
						System.out.println("That user doesn't exist.");
					}
				}
			} else if ("2".equals(input)) {
				Person newAccount = new Person();
				System.out.println("Enter a username: ");
				newAccount.setUsername(scan.nextLine());
				System.out.println("Enter a password: ");
				newAccount.setPassword(scan.nextLine());
				System.out.println("Choose a role:\n1. Employee\nother. User");
				input = scan.nextLine();
				Role role = new Role();
				if ("1".equals(input)) {
					role.setId(1);
					role.setName("Employee");
				} else {
					role.setId(2);
					role.setName("User");
				}
				newAccount.setRole(role);
				System.out.println(newAccount);
				System.out.println("Look good? 1 to confirm, other to cancel");
				input = scan.nextLine();
				if ("1".equals(input)) {
					try {
						personServ.addPerson(newAccount);
						System.out.println("Added " + newAccount.getUsername() + " successfully.");
					} catch (NonUniqueUsernameException e) {
						System.out.println("Sorry, that username is already taken. Try again.");
					}
				}
			} else {
				break;
			}
		}
		
		return loggedInUser;
	}

}
