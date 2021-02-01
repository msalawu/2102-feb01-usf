package com.revature.controller;

import java.util.Scanner;
import java.util.Set;

import com.revature.beans.Breed;
import com.revature.beans.Cat;
import com.revature.beans.Person;
import com.revature.beans.Role;
import com.revature.beans.Status;
import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.services.CatService;
import com.revature.services.CatServiceImpl;
import com.revature.services.PersonService;
import com.revature.services.PersonServiceImpl;

public class CatAppController {
	private static Scanner scan;
	private static PersonService personServ = new PersonServiceImpl();
	private static CatService catServ = new CatServiceImpl();

	public static void main(String[] args) {
<<<<<<< HEAD
		// TODO Auto-generated method stub

		
=======
		scan = new Scanner(System.in);
		boolean userActive = true;
		
		mainLoop: while (userActive) {
			System.out.println("Hello! Welcome to CatApp!");
			Person loggedInUser = null;
			
			while (loggedInUser == null) {
				System.out.println("What would you like to do?");
				System.out.println("1. Register\n2. Log in\nother. Exit");
				int userInput = Integer.valueOf(scan.nextLine());
				
				switch (userInput) {
				case 1:
					loggedInUser = registerUser();
					break;
				case 2:
					loggedInUser = logInUser();
					break;
				default:
					userActive = false;
					break mainLoop;
				}
			}
			
			menuLoop: while (true) {
				System.out.println("What would you like to do?");
				System.out.println("1. View available cats\n2. View my cats");
				if (loggedInUser.getRole().getName().equals("User")) {
					System.out.println("other. Log out");
				} else if (loggedInUser.getRole().getName().equals("Employee")) {
					System.out.println("3. Manage cats\n4. Manage users\nother. Log out");
				}
				int userInput = Integer.valueOf(scan.nextLine());
				switch (userInput) {
				case 1:
					loggedInUser = viewAvailableCats(loggedInUser);
					break;
				case 2:
					viewUserCats(loggedInUser);
					break;
				case 3:
					loggedInUser = manageCats(loggedInUser);
					break;
				case 4:
					loggedInUser = manageUsers(loggedInUser);
					break;
				default:
					System.out.println("See you next time!");
					loggedInUser = null;
					break menuLoop;
				}
			}
		}
		scan.close();
>>>>>>> 14c4f16f445f8bc716af654a0929ded9dd89d004
	}

	/*
	 * returns person object if user successfully registered
	 * returns null if user cancelled
	 */
	private static Person registerUser() {
		while (true) {
			Person newAccount = new Person();
			System.out.println("Enter a username: ");
			newAccount.setUsername(scan.nextLine());
			System.out.println("Enter a password: ");
			newAccount.setPassword(scan.nextLine());
			Role r = new Role();
			r.setId(2);
			r.setName("User");
			newAccount.setRole(r);
			System.out.println("Does this look good?");
			System.out.println("Username: " + newAccount.getUsername()
					+ " Password: " + newAccount.getPassword());
			System.out.println("1 to confirm, 2 to start over, other to cancel");
			int input = Integer.valueOf(scan.nextLine());
			switch (input) {
			case 1:
				try {
					newAccount.setId(personServ.addPerson(newAccount));
					System.out.println("Confirmed. Welcome!");
					return newAccount;
				} catch (NonUniqueUsernameException e) {
					System.out.println("Sorry, that username is taken - let's try again.");
				}
				break;
			case 2:
				System.out.println("Okay, let's try again.");
				break;
			default:
				System.out.println("Okay, let's go back.");
				return null;
			}
			
		}
	}
	
	/*
	 * returns person object if successfully logged in
	 * returns null otherwise
	 */
	private static Person logInUser() {
		while (true) {
			System.out.println("Enter username: ");
			String username = scan.nextLine();
			System.out.println("Enter password: ");
			String password = scan.nextLine();
			
			Person user = personServ.getPersonByUsername(username);
			if (user == null) {
				System.out.print("Nobody exists with that username. ");
			} else if (user.getPassword().equals(password)) {
				System.out.println("Welcome back!");
				return user;
			} else {
				System.out.print("That password is incorrect. ");
			}
			System.out.println("Do you want to try again? 1 for yes, other for no.");
			int input = Integer.valueOf(scan.nextLine());
			if (input != 1) {
				break;
			}
		}
		return null;
	}
	
	/*
	 * returns updated person object in case the user adopts a cat
	 */
	private static Person viewAvailableCats(Person user) {
		Set<Cat> availableCats = catServ.getAvailableCats();
		
		for (Cat cat : availableCats) {
			System.out.println(cat);
		}
		
		System.out.println("Would you like to adopt a cat? 1 for yes, other for no");
		int input = Integer.valueOf(scan.nextLine());
		if (input == 1) {
			while (true) {
				System.out.println("Which cat? Type its ID.");
				input = Integer.valueOf(scan.nextLine());
				Cat cat = catServ.getCatById(input);
				if (cat != null && cat.getStatus().getName().equals("Available")) {
					System.out.println(cat);
					System.out.println("You want to adopt " + cat.getName() + "? 1 for yes, other for no");
					input = Integer.valueOf(scan.nextLine());
					if (input == 1) {
						catServ.adoptCat(user, cat);
						System.out.println("You did it! You adopted " + cat.getName() + ".");
						// get the person with their updated cat set
						user = personServ.getPersonById(user.getId());
						break;
					} else {
						System.out.println("Okay, did you want to adopt a different one? 1 for yes, other for no");
						input = Integer.valueOf(scan.nextLine());
						if (input != 1)
							break;
					}
				} else {
					System.out.println("Sorry, that's not an available cat. Do you want to try again?"
							+ " 1 for yes, other for no");
					input = Integer.valueOf(scan.nextLine());
					if (input != 1) {
						System.out.println("Okay, that's fine.");
						break;
					}
				}
			}
		} else {
			System.out.println("Okay, that's fine.");
		}
		
		return user;
	}
	
	private static void viewUserCats(Person user) {
		if (user.getCats().size() > 0) {
			System.out.println("Viewing your cats: ");
			for (Cat cat : user.getCats()) {
				System.out.println(cat);
			}
			boolean petCat = false;
			while (true) {
				System.out.print("...would you like to pet ");
				if (petCat)
					System.out.print("another ");
				System.out.println("one? 1 for yes, other for no");
				int input = Integer.valueOf(scan.nextLine());
				if (input == 1) {
					System.out.println("Which one? Type its ID.");
					input = Integer.valueOf(scan.nextLine());
					Cat cat = catServ.getCatById(input);
					if (cat != null && user.getCats().contains(cat)) {
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
	
	/*
	 * employee-only menu:
	 * returns null if the user is not an employee
	 */
	private static Person manageCats(Person user) {
		if (!(user.getRole().getName().equals("Employee")))
			return null;
		
		while (true) {
			System.out.println("Manage Cats:\n1. Add a cat\n2. Edit a cat\nother. Cancel");
			int input = Integer.valueOf(scan.nextLine());
			
			if (input == 1) {
				Cat newCat = new Cat();
				System.out.println("Enter a name: ");
				newCat.setName(scan.nextLine());
				System.out.println("Enter an age: ");
				newCat.setAge(Integer.valueOf(scan.nextLine()));
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
				newCat.setBreed(breed);
				Status status = new Status();
				status.setId(1);
				status.setName("Available");
				newCat.setStatus(status);
				System.out.println(newCat);
				System.out.println("Look good? 1 to confirm, other to start over");
				input = Integer.valueOf(scan.nextLine());
				if (input == 1) {
					newCat.setId(catServ.addCat(newCat));
					System.out.println("You successfully added " + newCat.getName() + "!");
				}
			} else if (input == 2) {
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
						input = Integer.valueOf(scan.nextLine());
						switch (input) {
						case 1:
							System.out.println("Enter new name: ");
							cat.setName(scan.nextLine());
							break;
						case 2:
							System.out.println("Enter new age: ");
							cat.setAge(Integer.valueOf(scan.nextLine()));
							break;
						case 3:
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
		
		return user;
	}
	
	/*
	 * employee-only menu:
	 * returns null if the user is not an employee
	 */
	private static Person manageUsers(Person user) {
		// if a user is not an employee, then they meant to log out, not go to this menu
		if (!(user.getRole().getName().equals("Employee")))
			return null;
		
		while (true) {
			System.out.println("Manage Users:\n1. Remove a user\n2. Add a user\nother. Cancel");
			int input = Integer.valueOf(scan.nextLine());
			
			if (input == 1) {
				System.out.println("1. Remove by ID\n2. Remove by username\nother. Cancel");
				input = Integer.valueOf(scan.nextLine());
				Person personToRemove = null;
				if (input == 1) {
					System.out.println("Enter the ID of the user you want to remove.");
					personToRemove = personServ.getPersonById(Integer.valueOf(scan.nextLine()));
				} else if (input == 2) {
					System.out.println("Enter the username of the user you want to remove.");
					personToRemove = personServ.getPersonByUsername(scan.nextLine());
				}
				if (input == 1 || input == 2) {
					if (personToRemove != null) {
						System.out.println(personToRemove);
						System.out.println("Remove this user? 1 for yes, other for no");
						input = Integer.valueOf(scan.nextLine());
						if (input == 1) {
							personServ.deletePerson(personToRemove);
							System.out.println("You removed "
									+ personToRemove.getUsername() + " successfully.");
						}
					} else {
						System.out.println("That user doesn't exist.");
					}
				}
			} else if (input == 2) {
				Person newAccount = new Person();
				System.out.println("Enter a username: ");
				newAccount.setUsername(scan.nextLine());
				System.out.println("Enter a password: ");
				newAccount.setPassword(scan.nextLine());
				System.out.println("Choose a role:\n1. Employee\nother. User");
				input = Integer.valueOf(scan.nextLine());
				Role role = new Role();
				if (input == 1) {
					role.setId(1);
					role.setName("Employee");
				} else {
					role.setId(2);
					role.setName("User");
				}
				newAccount.setRole(role);
				System.out.println(newAccount);
				System.out.println("Look good? 1 to confirm, other to cancel");
				input = Integer.valueOf(scan.nextLine());
				if (input == 1) {
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
		
		return user;
	}
}
