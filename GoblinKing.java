package textRPG;

public class GoblinKing extends Goblin
{

	GoblinKing()
	{
		setHealthKing();
		setDmgKing();
		setSpeedKing();
		setArmorKing();
		isKing = true;
	}

	
	private final int MAX_HEALTH_KING = 40;
	private final int MAX_DMG_KING = 5;
	private final int MAX_SPEED_KING = 4;
	private final int MAX_ARMOR_KING = 3;

	public void setHealthKing()
	{
		goblinMaxHealth = MAX_HEALTH_KING;
		health = goblinMaxHealth;
	}//end of setHealth
	public void setDmgKing()
	{	
		damage = MAX_DMG_KING;
	}//end of setDmg
	public void setSpeedKing()
	{
		speed = MAX_SPEED_KING;
	}//end of setSpeed
	public void setArmorKing()
	{
		armor = MAX_ARMOR_KING;
	}//end of setArmor
	
	}//end of goblinKing