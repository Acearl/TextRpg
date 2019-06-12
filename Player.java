package textRPG;

import java.util.Random;

public class Player {

	private Random rng = new Random();
	
	private final int LEVEL_CAP = 5;
	
	private final int MAX_HEALTH = 25;
	private final int MIN_HEALTH = 18;
	private final int MAX_DMG = 10;
	private final int MIN_DMG = 2;
	private final int MAX_SPEED = 8;
	private final int MIN_SPEED = 2;
	private final int MAX_ARMOR = 5;
	private final int MIN_ARMOR = 1;
	private final int MAX_WINS = 5;
	private final int MIN_WINS = 2;
	private final int STAT_INCREASE = 2;
	private final int expPerVictory = 50;
	
	private double defendModifier = .25;
	private int[] lvls = {1,2,3,4,5};
	private int[] expForLvlUp = {100,200,350,500};
	private int currentExp = 0;
	private int currentLvl = 1;
	private int victoriesNeeded = 0;
	private int victories = 0;
	private int healAmount = 4;
	private String name = "";
	private int damage = 0;
	private int health = 0;
	private int armor = 0;
	private int speed = 0;
	private int playerMaxHealth = 0;
	
	private boolean dead = false;
	
	
	Player()
	{
		setHealth();
		setDmg();
		setSpeed();
		setArmor();
		setVictoryCount();
	}
	
	public void setHealth()
	{
		playerMaxHealth = getRandomNumber(MIN_HEALTH, MAX_HEALTH);
		health = playerMaxHealth;
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
	public void setVictoryCount()
	{
		victoriesNeeded = getRandomNumber(MIN_WINS, MAX_WINS);
	}
	
	public void setVictoryUp()
	{
		victories++;
	}
	public void setPlayerName(String borrowedPlayerName)
	{
		name = borrowedPlayerName;
	}
	public void setTakeDmg(int borrowedTakenDmg)
	{
		health = health - borrowedTakenDmg;
		if (health <= 0)
		{
			died();
		}
	}
	public void setHeal(int borrowedHealHealth)
	{
		health = health + borrowedHealHealth;
	}
	public void setLvlUp()
	{
		if(currentLvl < LEVEL_CAP)
		{
			currentLvl++;
		}
		damage += STAT_INCREASE;
		playerMaxHealth += STAT_INCREASE;
		health += STAT_INCREASE;
		armor += STAT_INCREASE;
		speed += STAT_INCREASE;
	}
	public void setGainExp(int borrowedExp)
	{
		currentExp = currentExp + borrowedExp;
		if((currentLvl < LEVEL_CAP) && (currentExp >= expForLvlUp[currentLvl - 1]))
		{
			//Checks to see if player is at lvl cap and that they have enough exp for the next lvlUp
			setLvlUp();
		}
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
		if(health <= 0) 
		{
			died();
		}
	}
	public void died()
	{
		dead = true;
	}//end of died
	
	
	public int[] getLvls()
	{
		return lvls;
	}
	public int getExpForLvlUp()
	{
		return expForLvlUp[currentLvl - 1];
	}
	public int getCurrentExp()
	{
		return currentExp;
	}
	public int getCurrentLvl()
	{
		return currentLvl;
	}
	public String getPlayerName()
	{
		return name;
	}
	public int getDmg()
	{
		return damage;
	}
	public int getHealth()
	{
		return health;
	}
	public int getArmor()
	{
		return armor;
	}
	public int getSpeed()
	{
		return speed;
	}
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
	public int getPlayerMaxHealth()
	{
		return playerMaxHealth;
	}
	public boolean getDeadStatus()
	{
		return dead;
	}
	public int getMaxHealth()
	{
		return playerMaxHealth;
	}
	public double getDefendModifier()
	{
		return defendModifier;
	}
	public int getVictories()
	{
		return victories;
	}
	public int getVictoriesNeeded()
	{
		return victoriesNeeded;
	}
	public int getExpPerVictory()
	{
		return expPerVictory;
	}
	public int getHealAmount()
	{
		return healAmount;
	}
	
	
}//end of player
