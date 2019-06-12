package textRPG;

import java.util.Scanner;

/** Generic RPG game
 * Author: Andrew Earl
 * Purpose: Dungeon crawler, mostly for testing my own programming skills. A novice needs to start somewhere.
 * Create Date: 3/8/2019
 * Edited: 4/22/2019 |Created Goblin class
 * Edited: 5/3/2019 |Added more to Main Class and fleshed out stats of the player
 * Edited 5/12/2019 |Corrected some flaws in combatLoop and started playtesting
 * Edited 5/22/2019 |Started working on the game's ending with the goblin king and player stat increases
 * Edited 5/23/2019 |Continuation of yesterday
 * Edited 5/24/2019 |Playtesting and bug fixing, added the isKing boolean to goblin to check if the goblin king is dead and end the game.
 * Edited 6/3/2019  |Continued playtesting and quality of life improvements
 */

public class MainClass {

	static Scanner input = new Scanner(System.in);
	static Player player = new Player();
	static char menuSelection = ' ';
	static boolean kingDeadStatus = false;


	public static void main(String[] args) {

		displayWelcomeBanner();
		displayExposition();
		menuSelection = validateMainMenu(input);
		while (menuSelection != 'Q')
		{
			while (kingDeadStatus == false)
			{
				if(menuSelection == 'A') 
				{
					player.setPlayerName(validateUsername(input));
					displayStats(player.getMaxHealth(), player.getArmor(), player.getDmg(), player.getSpeed());
				}
				else
				{
					if(player.getPlayerName() == "")
					{
						displayNoCharacterError();
					}
					else//actual gameplay loop for a made character
					{
						kingDeadStatus = combatLoop(input, player);//player cannot return to main menu until it dies or defeats the goblin king
					}
				}

				menuSelection = validateMainMenu(input);
			}//end of quit while
		}

		//End of game
		if (player.getPlayerName() != "")
		{
			displayFarewellBanner(player.getPlayerName());
		}
		else
		{
			displayFarewellBanner();
		}
		input.close();
	}



	public static void displayWelcomeBanner()
	{
		System.out.println("------------------------------------------------------------");
		System.out.println("                      Hello Adventurer!                     ");
		System.out.println("     You have been tasked with clearing a local shaft mine  ");
		System.out.println("  invaded with brutal Goblins. You character will be given  ");
		System.out.println("  stats then you will be sent down into the mine to finish  ");
		System.out.println("  your mission. Good Luck!                                  ");
		System.out.println("------------------------------------------------------------");
	}
	public static void displayFarewellBanner(String borrowedUsername)
	{
		System.out.println("------------------------------------------------------------");
		System.out.println("We hope to see you play again " + borrowedUsername + ".");
		System.out.println("         :::Credits:::");
		System.out.println("Everything-----------Andrew C Earl");
		System.out.println("------------------------------------------------------------");
	}
	public static void displayFarewellBanner()
	{
		System.out.println("------------------------------------------------------------");
		System.out.println("We hope to see you play again ");
		System.out.println("         :::Credits:::");
		System.out.println("Everything-----------Andrew C Earl");
		System.out.println("------------------------------------------------------------");
	}
	public static void displayMainMenu()
	{
		System.out.println("\n------------------------------------------------------------");
		System.out.println("------------------------Main-Menu---------------------------");
		System.out.println("------------------------------------------------------------");
		System.out.printf("%-5s%-45s", "[A]", "Begin your adventure (character creation)");
		System.out.printf("\n%-5s%-45s", "[B]", "Enter (or continue) into the Mine Shaft");
		System.out.printf("\n%-5s%-45s", "[Q]", "Quit adventure");
		System.out.println("\n------------------------------------------------------------");
		System.out.print("\nPlease enter here:");
	}
	public static void displayEncounterPrompt(int borrowedHealth, int borrowedArmor, int borrowedDmg, int borrowedSpeed)
	{
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("   As you venture deeper into the mineshaft, you encounter  ");
		System.out.println("  a rouge Goblin.                                           ");
		displayStats(borrowedHealth, borrowedArmor, borrowedDmg, borrowedSpeed);
		System.out.println("------------------------------------------------------------");
	}
	public static void displayKingEncounterPrompt(int borrowedHealth, int borrowedArmor, int borrowedDmg, int borrowedSpeed)
	{
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("    You near the end of your adventure when the Goblin King ");
		System.out.println("approches you, furious at the losses of his minions.        ");
		displayStats(borrowedHealth, borrowedArmor, borrowedDmg, borrowedSpeed);
		System.out.println("          \n'WHAT INFILTRATOR SEEKS THEIR DOOM!!'\n");
		System.out.println("He Screams at you, as he lunges to melee strike you!");
		System.out.println("------------------------------------------------------------");
	}
	public static void displayEncounterWon()
	{
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("        You have achieved victroy over your enemy!");
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
	}
	public static void displayCombatMenu()
	{
		System.out.println("------------------------------------------------------------");
		System.out.println("------------------------Combat-Menu-------------------------");
		System.out.println("------------------------------------------------------------");
		System.out.printf("%-5s%-45s", "[A]", "Attack ");
		System.out.printf("\n%-5s%-45s", "[D]", "Defend (heal and take 1/4 Damage)");
		System.out.printf("\n%-5s%-45s", "[F]", "Flee ");
		System.out.println("\n------------------------------------------------------------");
		System.out.print("\nPlease enter here:");
	}
	public static void displayCombatResults(boolean borrowedDied, int borrowedHealth, int borrowedMaxHealth, int borrowedEnemyHealth, int borrowedEnemyMaxHealth, Player player)
	{
		double tempHealth = 0.0;
		double tempMaxHealth = 0.0;
		double tempEnemyHealth = 0.0;
		double tempEnemyMaxHealth = 0.0;
		tempHealth = Double.parseDouble(String.valueOf(borrowedHealth));
		tempMaxHealth = Double.parseDouble(String.valueOf(borrowedMaxHealth));
		tempEnemyHealth = Double.parseDouble(String.valueOf(borrowedEnemyHealth));
		tempEnemyMaxHealth = Double.parseDouble(String.valueOf(borrowedEnemyMaxHealth));
		if(borrowedDied == false)
		{
			System.out.println("------------------------------------------------------------");
			System.out.println("---------------------Victory-Display------------------------");
			System.out.println("------------------------------------------------------------");
			player.setVictoryUp();
			player.setGainExp(player.getExpPerVictory());
		}
		else
		{
			System.out.println("------------------------------------------------------------");
			System.out.println("----------------------Defeat-Display------------------------");
			System.out.println("------------------------------------------------------------");
		}
		System.out.printf("%-10s%25s%25s", "", "Player", "Enemy");
		System.out.printf("\n%-10s%25d%25d", "Max Health", borrowedMaxHealth, borrowedEnemyMaxHealth);
		System.out.printf("\n%-10s%22.2f%1s%22.2f%1s", "Health %", (tempHealth/tempMaxHealth) * 100,"%", (tempEnemyHealth/tempEnemyMaxHealth) * 100, "%");
		System.out.printf("\n%-10s%25d%25d", "Health", borrowedHealth, borrowedEnemyHealth);
	}
	public static void displayTurnResults(int borrowedHealth, int borrowedMaxHealth, int borrowedEnemyHealth, int borrowedEnemyMaxHealth, int borrowedHealthLostPlayer, int borrowedHealthLostEnemy)
	{
		double tempHealth = 0.0;
		double tempMaxHealth = 0.0;
		double tempEnemyHealth = 0.0;
		double tempEnemyMaxHealth = 0.0;
		tempHealth = Double.parseDouble(String.valueOf(borrowedHealth));
		tempMaxHealth = Double.parseDouble(String.valueOf(borrowedMaxHealth));
		tempEnemyHealth = Double.parseDouble(String.valueOf(borrowedEnemyHealth));
		tempEnemyMaxHealth = Double.parseDouble(String.valueOf(borrowedEnemyMaxHealth));


		System.out.println("------------------------Turn-Results------------------------");
		System.out.printf("%-10s%25s%25s", "", "Player", "Enemy");
		System.out.printf("\n%-10s%25d%25d", "Max Health", borrowedMaxHealth, borrowedEnemyMaxHealth);
		System.out.printf("\n%-10s%23.2f%1s%23.2f%1s", "Health %", (tempHealth/tempMaxHealth) * 100,"%", (tempEnemyHealth/tempEnemyMaxHealth) * 100, "%");
		System.out.printf("\n%-10s%25d%25d", "Health", borrowedHealth, borrowedEnemyHealth);
		System.out.printf("\n%-10s%25d%25d", "HP Change", borrowedHealthLostPlayer, borrowedHealthLostEnemy);
		System.out.println("\n------------------------------------------------------------");
	}
	public static void displayStats(int borrowedHealth, int borrowedArmor, int borrowedDmg, int borrowedSpeed)
	{
		System.out.println("Health " + borrowedHealth);
		System.out.println("Armor " + borrowedArmor);
		System.out.println("Damage " + borrowedDmg);
		System.out.println("Speed " + borrowedSpeed);
	}
	public static void displayLevelUp(int borrowedMaxHealth, int borrowedArmor, int borrowedDmg, int borrowedSpeed)
	{
		System.out.println("\n\nCongratulations Adventurer on your Level Up!");
		System.out.println("Max Health " + borrowedMaxHealth);
		System.out.println("Armor " + borrowedArmor);
		System.out.println("Damage " + borrowedDmg);
		System.out.println("Speed " + borrowedSpeed);
	}
	public static void displayNoCharacterError()
	{
		System.out.println("It seems your character is not properly created.");
		System.out.println("Please create a character before entering the mineshaft.");
	}

	public static boolean combatLoop(Scanner borrowedInput, Player player)
	{
		char menuSelection = ' ';

		Goblin goblin = null;
		//Goblin enemy creation
		if(player.getVictories() == player.getVictoriesNeeded())
		{
			goblin = new GoblinKing();
		}
		else
		{
			goblin = new Goblin();
		}

		int prevHealth = player.getHealth();
		int prevEnemyHealth = goblin.getHealth();
		int healthLostPlayer = 0;
		int healthLostEnemy = 0;
		int playerPreviousLvl = player.getCurrentLvl();


		if(goblin.getIsKing() == true)
		{
			displayKingEncounterPrompt(goblin.getHealth(), goblin.getArmor(), goblin.getDmg(), goblin.getSpeed());
		}
		else
		{
			displayEncounterPrompt(goblin.getHealth(), goblin.getArmor(), goblin.getDmg(), goblin.getSpeed());
		}
		menuSelection = validateCombatMenu(borrowedInput);
		while (menuSelection != 'F' && menuSelection != 'O')
		{
			if(menuSelection == 'A')//attack sinerio
			{
				if(player.getSpeed() >= goblin.getSpeed())
				{
					goblin.setTakeDamage(player.getDmg());
					if(goblin.getDeadStatus() == false)
					{
						player.setTakeDamage(goblin.getDmg());
					}
					else
					{
						menuSelection = 'O';
					}
				}
				else
				{
					player.setTakeDamage(goblin.getDmg());
					if(player.getDeadStatus() == false)
					{
						goblin.setTakeDamage(player.getDmg());
					}	
					else
					{
						menuSelection = 'O';
					}
				}
			}
			else//Defend sinerio
			{
				player.setHeal(player.getHealAmount());
				player.setTakeDamage((int)(goblin.getDmg() * player.getDefendModifier()));
				if(player.getDeadStatus() == true)
				{
					menuSelection = 'O';//game over
				}	
			}
			
			prevHealth = player.getHealth();
			prevEnemyHealth = goblin.getHealth();
			healthLostPlayer = player.getHealth() - prevHealth;
			healthLostEnemy = goblin.getHealth() - prevEnemyHealth;

			displayTurnResults(player.getHealth(), player.getMaxHealth(), goblin.getHealth(), goblin.getMaxHealth(), healthLostPlayer, healthLostEnemy);
			if(goblin.getDeadStatus() == true)
			{
				menuSelection = 'O';//player won
				displayEncounterWon();
			}
			else
			{
				menuSelection = validateCombatMenu(borrowedInput);
			}
		}
		displayCombatResults(player.getDeadStatus(), player.getHealth(), player.getMaxHealth(), goblin.getHealth(), goblin.getMaxHealth(), player);
		if (playerPreviousLvl < player.getCurrentLvl())
		{
			displayLevelUp(player.getPlayerMaxHealth(), player.getArmor(), player.getDmg(), player.getSpeed());
		}

		//checks for goblin king's death and sets the game to end.

		if (goblin.getDeadStatus() == true && goblin.getIsKing() == true)
		{
			return true;
		}
		else
		{
			return false;
		}
	}


	public static char validateMainMenu(Scanner borrowedInput)
	{
		char localSelection = ' ';
		displayMainMenu();
		localSelection = borrowedInput.nextLine().charAt(0);
		localSelection = Character.toUpperCase(localSelection);
		while (localSelection != 'A' && localSelection != 'B' && localSelection != 'Q')
		{
			System.out.println("Please try again");
			displayMainMenu();
			localSelection = borrowedInput.nextLine().charAt(0);
			localSelection = Character.toUpperCase(localSelection);
		}
		return localSelection;
	}
	public static char validateCombatMenu(Scanner borrowedInput)
	{
		char localSelection = ' ';
		displayCombatMenu();
		localSelection = borrowedInput.nextLine().charAt(0);
		localSelection = Character.toUpperCase(localSelection);
		while (localSelection != 'A' && localSelection != 'D' && localSelection != 'F')
		{
			System.out.println("Please try again");
			displayCombatMenu();
			localSelection = borrowedInput.nextLine().charAt(0);
			localSelection = Character.toUpperCase(localSelection);
		}
		return localSelection;
	}
	public static String validateUsername(Scanner borrowedInput)
	{
		String localName = "";
		System.out.println("What is your name Adventurer?");
		System.out.print("\nPlease enter here:");
		localName = borrowedInput.nextLine();
		return localName;
	}




}

