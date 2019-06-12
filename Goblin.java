package textRPG;

import java.util.Random;

public class Goblin {

	private Random rng = new Random();

	
	
	private final int MAX_HEALTH = 10;
	private final int MIN_HEALTH = 2;
	private final int MAX_DMG = 4;
	private final int MIN_DMG = 1;
	private final int MAX_SPEED = 4;
	private final int MIN_SPEED = 1;
	private final int MAX_ARMOR = 3;
	private final int MIN_ARMOR = 0;


	int health = 0;
	int damage = 0;
	int speed = 0;
	int armor = 0;
	int goblinMaxHealth = 0;
	boolean dead = false;
	boolean isKing = false;



	Goblin()
	{
		setHealth();
		setDmg();
		setSpeed();
		setArmor();
	}

	public void setTakeDamage (int deltDmg)
	{

		if((deltDmg - armor) <= 0)
		{
			health--;
		}
		else
		{
			health = health - (deltDmg - armor);
		}
		if(health <= 0) {
			died();
		}
	}//end of takeDamage
	public void died() 
	{
		dead = true;
	}//end of died


	//setters
	public void setHealth()
	{
		goblinMaxHealth = getRandomNumber(MIN_HEALTH, MAX_HEALTH);
		health = goblinMaxHealth;
	}//end of setHealth
	public void setDmg()
	{	
		damage = getRandomNumber(MIN_DMG, MAX_DMG);
	}//end of setDmg
	public void setSpeed()
	{
		speed = getRandomNumber(MIN_SPEED, MAX_SPEED);
	}//end of setSpeed
	public void setArmor()
	{
		armor = getRandomNumber(MIN_ARMOR, MAX_ARMOR);
	}//end of setArmor


	//getters
	public int getRandomNumber(int borrowedMin, int borrowedMax)
	{
		int tempRandom = 0;
		tempRandom = rng.nextInt(borrowedMax) + borrowedMin;
		if(tempRandom > borrowedMax)
		{
			tempRandom = borrowedMax;
		}
		return tempRandom;
	}
	public int getHealth() 
	{
		return health;
	}
	public int getDmg() 
	{
		return damage;
	}
	public int getArmor()
	{
		return armor;
	}
	public int getSpeed() 
	{
		return speed;
	}
	public int getMaxHealth()
	{
		return goblinMaxHealth;
	}
	public boolean getDeadStatus() 
	{
		return dead;
	}
	public boolean getIsKing()
	{
		return isKing;
	}
	
	

}



