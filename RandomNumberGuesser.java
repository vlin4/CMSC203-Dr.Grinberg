/*
 *  Class: CMSC203 CRN 202330
 *Program: Assignment #2
 *Instructor: Dr.Grinberg
 *Summary of Description: This application will get a user’s input as a guess and report it if the guess is the random number that is generated. It will then narrow down the choices according to the previous guess and will loop to prompt user to keep guessing until they are correct. 
 *Due Date: 02/20/23 
 *Integrity Pledge: I pledge that I have completed the programming assignment independently.
 *I have not copied the code from a student or any source.
 *Vanessa Lin
 */

import java.util.Scanner;

public class RandomNumberGuesser {

	public static void main(String[] args) {
		// declare variables
		int guess;
		int randInt;
		String repeat = "yes";
		// create scanner object
		Scanner keyboard = new Scanner(System.in);
		// use while loop while repeat = "yes" while ignoring the case
		while (repeat.equalsIgnoreCase("yes")) {
			int maxGuess = 100; //declare and initiate the maxGuess to 100 (current upper bound)
			int minGuess = 1; //declare and initiate minimum guess to 1 (current lower bound)
			RNG.resetCount(); //call RNG.resetCount() method to make sure at beginning of loop the count is at 0

			randInt = RNG.rand(); //call RNG.rand() and store the random number into randInt
			System.out.println("Enter your first guess:"); //Ask user for their first guess
			//create inner while loop to continue to loop while the count is less than the max count (call from RNG class)
			while (RNG.getCount() < RNG.getMaxGuessCount()) { 
				guess = keyboard.nextInt(); //get user input into guess

				if (RNG.inputValidation(guess, minGuess, maxGuess)) { //inner if loop to validate user input is between 1-100
					RNG.incrementCount(); //call to increment from RNG class; only if method returns true

					if (guess < randInt) { //nested if loop to check if guess is lower than the random number
						System.out.println("Your guess is too low");
						minGuess = guess; //the new minimum guess/lower bound will be this guess
					} else if (guess > randInt) { //performs statements if the guess is larger than the random number
						System.out.println("Your guess is too high");
						maxGuess = guess; //the new max guess/upper bound will be this guess
					} else { //other wise, the guess equals the random integer and will congratulates user
						System.out.println("Congratulations, you have guessed correctly\nTry again? (yes or no)");
						repeat = keyboard.next(); //stores user's input if they want to try again into variable repeat
						break; //breaks out of loop
					}

					System.out.println("Number of guesses is: " + RNG.getCount()); //prints out count
					System.out.println("Enter your next guess between " + minGuess + " and " + maxGuess); //asks user to enter new guess between the new lower and upper bound
					
				}

			}

			if (RNG.getCount() == RNG.getMaxGuessCount()) { //checks for if the count is equal to max count which is 7
				guess = keyboard.nextInt(); //user gets to enter their next guess (this is in order to trigger their 8th try)
				System.out.println(
						"You have exceeded the maximum number of guesses, " + RNG.getMaxGuessCount() + ". Try again."); //output message when they are over 7 tries
				repeat = "no"; //outer while loops will end, causing the loop to end here because repeat is now "no"
			}

		}
		keyboard.close(); //closes keyboard
	}
}
